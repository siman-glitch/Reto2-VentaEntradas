package vista;

import java.util.ArrayList;

import dao.PeliculaDAO;
import pojos.Pelicula;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.ClienteDAO;
import dao.PeliculaDAO;
import pojos.Cliente;
import pojos.Entrada;
import dao.SesionDAO;
import gestor.GestorCompra;
import pojos.Pelicula;
import pojos.Sesion;

public class MenuPeliculas {
	private static Scanner sc = null;
	 MenuPeliculas() {
		sc = new Scanner(System.in);
		
	}

	public ArrayList<Pelicula> mostrarpeliculas() {

		PeliculaDAO pelidao = new PeliculaDAO();
		// hna hit getallpli katrjaa arry list maxi peli whda
		ArrayList<Pelicula> peliculas = pelidao.getAllpeliculas();
		if (peliculas == null || peliculas.isEmpty()) {
			System.out.println("No hay películas disponibles.");
			return null;
		}
		System.out.println("*******************PELÍCULAS DISPONIBLES**********************");
		for (int i = 0; i < peliculas.size(); i++) {
			// p wlat db film wahd
			Pelicula p = peliculas.get(i);
			System.out.println((i + 1) + ". " + peliculas.get(i).getTitulo());
		}
		// nrj3o la liste
		return peliculas;
	}

	public Pelicula elegirPeli(ArrayList<Pelicula> peliculas) {
		System.out.print("¿Quiere elegir una película para continuar? (si/no): ");
		String resp = sc.nextLine().trim().toLowerCase();
		if (!resp.equals("si")) {
			return null;
		}
		System.out.println("Elige una pelicula (1-" + peliculas.size() + "):");
		try {
			int opcion = Integer.parseInt(sc.nextLine().trim());
			if (opcion < 1 || opcion > peliculas.size()) {
				return null;
			}
			return peliculas.get(opcion - 1);
		} catch (Exception e) {

			return null;
		}

	}

}
