package com.demo.project.musicapp;

import com.demo.project.musicapp.model.Artista;
import com.demo.project.musicapp.model.Cancion;
import com.demo.project.musicapp.service.ArtistaService;
import com.demo.project.musicapp.service.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class MusicappApplication implements CommandLineRunner{

	private ArtistaService artistaService;
	private CancionService cancionService;

	public static void main(String[] args) {
		SpringApplication.run(MusicappApplication.class, args);
	}

	@Autowired
	public void MusicAppApplication(ArtistaService artistaService, CancionService cancionService) {
		this.artistaService = artistaService;
		this.cancionService = cancionService;
	}


	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;

		while (!exit) {
			mostrarMenu();
			int opcion = scanner.nextInt();
			scanner.nextLine();  // Limpiar el buffer

			switch (opcion) {
				case 1:
					agregarArtista(scanner);
					break;
				case 2:
					listarArtistas();
					break;
				case 3:
					agregarCancion(scanner);
					break;
				case 4:
					listarCancionesPorArtista(scanner);
					break;
				case 5:
					System.out.println("Saliendo...");
					exit = true;
					break;
				default:
					System.out.println("Opción no válida. Intenta nuevamente.");
					break;
			}
		}
	}

	private void mostrarMenu() {
		System.out.println("\n--- Menú de Opciones ---");
		System.out.println("1. Agregar Artista");
		System.out.println("2. Listar Artistas");
		System.out.println("3. Agregar Canción");
		System.out.println("4. Listar Canciones por Artista");
		System.out.println("5. Salir");
		System.out.print("Elige una opción: ");
	}

	private void agregarArtista(Scanner scanner) {
		System.out.print("Nombre del Artista: ");
		String nombre = scanner.nextLine();
		System.out.print("Nacionalidad del Artista: ");
		String nacionalidad = scanner.nextLine();
		System.out.print("Genero musical: ");
		String genero = scanner.nextLine();

		Artista artista = new Artista();
		artista.setNombre(nombre);
		artista.setNacionalidad(nacionalidad);
		artista.setGenero(genero);

		artistaService.guardarArtista(artista);
		System.out.println("Artista agregado correctamente.");
	}

	private void listarArtistas() {
		List<Artista> artistas = artistaService.obtenerTodosLosArtistas();
		if (artistas.isEmpty()) {
			System.out.println("No hay artistas disponibles.");
		} else {
			System.out.println("\nArtistas:");
			artistas.forEach(artista -> System.out.println("ID: " + artista.getId() + ", Nombre: " + artista.getNombre() + ", Genero: " + artista.getGenero()));
		}
	}

	private void agregarCancion(Scanner scanner) {
		System.out.print("Título de la Canción: ");
		String titulo = scanner.nextLine();
		System.out.print("Género de la Canción: ");
		String genero = scanner.nextLine();
		System.out.print("Duración de la Canción: ");
		String duracion = scanner.nextLine();
		System.out.print("ID del Artista (para asociar la canción): ");
		Long artistaId = scanner.nextLong();
		scanner.nextLine();  // Limpiar el buffer

		Artista artista = artistaService.obtenerArtistaPorId(artistaId);
		if (artista == null) {
			System.out.println("Artista no encontrado.");
			return;
		}

		Cancion cancion = new Cancion();
		cancion.setTitulo(titulo);
		cancion.setGenero(genero);
		cancion.setArtista(artista);

		cancionService.guardarCancion(cancion);
		System.out.println("Canción agregada correctamente.");
	}

	private void listarCancionesPorArtista(Scanner scanner) {
		System.out.print("ID del Artista para listar canciones: ");
		Long artistaId = scanner.nextLong();
		scanner.nextLine();  // Limpiar el buffer

		List<Cancion> canciones = cancionService.obtenerCancionesPorArtista(artistaId);
		if (canciones.isEmpty()) {
			System.out.println("No hay canciones para este artista.");
		} else {
			System.out.println("\nCanciones del Artista:");
			canciones.forEach(cancion -> System.out.println(
					"ID: " + cancion.getId() + ", Artista: " + cancion.getNombreArtista() +
					", Título: " + cancion.getTitulo() +", Género: " + cancion.getGenero()));
		}
	}
}
