package vista;

import java.util.ArrayList;
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

public class MenuSesiones {
	private static Scanner sc = null;
	MenuSesiones () {
		sc = new Scanner(System.in);
		
	}
	public Sesion sesionelegida(ArrayList<Sesion> sesiones) {
		if (sesiones == null || sesiones.isEmpty()) {
			System.out.println("No hay sesiones disponibles.");
			return null;
		}
		System.out.print("¿Podemos continuar seleccionando una sesión? (si/no): ");
		String resp = sc.nextLine().trim().toLowerCase();
		if (!resp.equals("si")) {
			return null;
		} else {
			System.out.print(" ");

		}
		System.out.println("Elige una sesion  (1-" + sesiones.size() + "):");
		try {
			int opcion = Integer.parseInt(sc.nextLine().trim());
			if (opcion < 1 || opcion > sesiones.size()) {
				System.out.println("Opción inválida.");

				return null;
			}
			return sesiones.get(opcion - 1);
		} catch (Exception e) {

			System.out.println("Opción inválida.");

			return null;
		}

	}

	public ArrayList<Sesion> mostrarsesiones(Pelicula peliElegida) {
		if (peliElegida == null) {
			System.out.println("No has elegido ninguna película.");
			return null;
		}
		SesionDAO sesiondao = new SesionDAO();
		// hemos cogido el id de la pelicula que ha elegido el usuario
		ArrayList<Sesion> sesiones = sesiondao.getSesionesPorPeliculaOrdenadas(peliElegida.getId());
		if (sesiones == null || sesiones.isEmpty()) {
			System.out.println("No hay Sesiones  disponibles.");
			return null;
		}
		System.out.println("******************** las fechas disponibles *********************");
		for (int i = 0; i < sesiones.size(); i++) {
			Sesion s = sesiones.get(i);
			System.out.println((i + 1) + ") " + s.getFecha() + " | " + s.getHoraInicio() + " | " + s.getPrecio());

		}

		return sesiones;
	}
}
