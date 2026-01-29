package vista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.PeliculaDAO;
import pojos.Entrada;
import pojos.Pelicula;
import pojos.Sesion;

public class MenuPeliculas {
	private static Scanner sc = null;
	private final PeliculaDAO peliculadao;
	private final List<Pelicula> pelis;
	private final List<Entrada> carrito;

	public MenuPeliculas() {
		this.pelis = new ArrayList<>();
		this.peliculadao = new PeliculaDAO();
		this.carrito = new ArrayList<>();

		sc = new Scanner(System.in);
	}

	public ArrayList<Pelicula> mostrarpeliculas() {
		// getaalpelis katrd lista d pelis logique ndiroha arrylist
		ArrayList<Pelicula> peliculas = peliculadao.getAllpeliculas();

		if (peliculas == null || peliculas.isEmpty()) {
			System.out.println("No hay películas disponibles.");
			return null;
		}
		System.out.println("*******************PELÍCULAS DISPONIBLES**********************");
		for (int i = 0; i < peliculas.size(); i++) {
			// p wlat db film wahd
			System.out.println((i + 1) + ". " + peliculas.get(i).getTitulo());
		}
		// nrj3o la liste
		return peliculas;
	}

	// usuario khso ikhtar peli mn lista peliculas
	public Pelicula elegirPeli(ArrayList<Pelicula> peliculas) {
		// ila khtar no ayb9a null oila khtar si ayrja3 peli
		Pelicula ret = null;

		System.out.print("¿Quiere elegir una película para continuar? (si/no): ");
		// trimn espacio + tolower miniscul y mayuscul
		String resp = sc.nextLine().trim().toLowerCase();

		if (resp.equals("si")) {
			// opcion mosta7il tkon -1 donc kandiroha bash nrj3o que ralat opcion
			int opcion = -1;
			// ila usuario khtar ra9m ralat atb9a tswloooo
			while (opcion < 1 || opcion > peliculas.size()) {
				try {
					System.out.println("Elige una pelicula (1-" + peliculas.size() + "):");
					opcion = Integer.parseInt(sc.nextLine().trim());

				} catch (Exception e) {
					sc.nextLine();
					opcion = -1;
				}
			}
			// aryylist katkhdm b -1
			ret = peliculas.get(opcion - 1);
		}
		return ret;
	}

	private int opcionvalida(int min, int max) {
		int opcion = -1;
		while (opcion < min || opcion > max) {
			try {
				System.out.print("Elige una opción (" + min + "-" + max + "): ");
				// leer el numero
				opcion = Integer.parseInt(sc.nextLine().trim());
			} catch (Exception e) {
//		bash fahs kaydkhl usuaripo abc kayti7 f cathc -1 bash n3lmoh bli khtiyar ralat oyrjaa lwhile		
				opcion = -1;
			}

		}
		return opcion;

	}


}
