package vista;

import java.util.ArrayList;
import java.util.Scanner;

import dao.ClienteDAO;
import dao.PeliculaDAO;
import pojos.Cliente;
import pojos.Pelicula;

public class Menu {

	private static Scanner sc = null;

	public Menu() {
		sc = new Scanner(System.in);
	}

	/**
	 * Muestra el mensaje de bienvenida inicial.
	 */
	public static void inicio() {
		System.out.println("*****************************************");
		System.out.println("*             BIENVENIDO                *");
		System.out.println("*              A MyCine                 *");
		System.out.println("*                                       *");
		System.out.println("*            iniciar sesión             *");
		System.out.println("*---------------------------------------*");
		System.out.println("*****************************************");
	}

	public void login() {
		inicio();
		ClienteDAO clientedao = new ClienteDAO();
		boolean salir = false;
		while (!salir) {
			System.out.println(" Introduce tu correo electronico:  ");
			String correo = sc.nextLine().trim();
			System.out.print("Introduce tu contrasena: ");
			String contrasena = sc.nextLine().trim();

			if (correo.isEmpty() || contrasena.isEmpty()) {
				System.out.println("Correo o contraseña vacíos. Reintenta." + ".");
			} else {
				Cliente cliente = clientedao.logincliente(correo, contrasena);
				if (cliente != null) {
					System.out.println("Bienvenido" + " " + cliente.getNombre());
					// method siempe se guarda en una varibale y arry list porque elgir pelicula
					// necessita lista paga que se elegi

					ArrayList<Pelicula> peliculas = mostrarpeliculas();
					Pelicula peliElegida = elegirPeli(peliculas);

					if (peliElegida != null) {
						System.out.println("Has elegido: " + peliElegida.getTitulo());
					}

					salir = true;// login true
				} else {
					System.out.println("Error al iniciar sesión ");
					salir = preguntarRegistro();

				}
			}

		}

	}

	private void menuPeliculas() {

		ArrayList<Pelicula> peliculas = mostrarpeliculas();
		Pelicula peliElegida = elegirPeli(peliculas);
		if (peliElegida != null) {
			System.out.println("Has elegido: " + peliElegida.getTitulo());
			// Aquí se continuará con la selección de sesiones

		}

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

	public static boolean preguntarRegistro() {
		System.out.print("¿Quieres registrarte y disfrutar de ventajas exclusivas? (si/no?): ");
		String respuesta = sc.nextLine().trim().toLowerCase();

		if (respuesta.equals("si")) {
			Registrar();
			return false;

		} else {

			System.out.println("Gracias por usar MyCine. ¡Hasta pronto!");
			return true;
		}

	}

	public static void Registrar() {
		System.out.println("*****************************************");
		System.out.println("*              REGÍSTRATE               *");
		System.out.println("*               EN MyCine               *");
		System.out.println("*                                       *");
		System.out.println("*  Regalo de cumpleaños                 *");
		System.out.println("*  Día del socio                        *");
		System.out.println("*  Descuentos exclusivos                *");
		System.out.println("*  Regalo de bienvenida                 *");
		System.out.println("*                                       *");
		System.out.println("*  ¡Únete a un mundo de ventajas        *");
		System.out.println("*  y vive el cine por menos!            *");
		System.out.println("*---------------------------------------*");
		System.out.println("*****************************************");

//pedir datos
		Cliente c = new Cliente();
		ClienteDAO clientedao = new ClienteDAO();

		System.out.println("Introduce tu DNI: ");
		c.setDni(sc.nextLine().trim());

		System.out.println("Introduce tu Nombre: ");
		c.setNombre(sc.nextLine().trim());

		System.out.println("Introduce tu apellidos: ");
		c.setApellidos(sc.nextLine().trim());

		System.out.println("Introduce tu Correo electrónico: ");
		c.setCorreoElectronico(sc.nextLine().trim());

		System.out.print("Contraseña: ");
		c.setContrasena(sc.nextLine().trim());
		try {
			clientedao.insert(c);
			System.out.println("\nRegistro completado correctamente.\n");
		} catch (Exception e) {
			System.out.println("\nError al registrar.\n");
		}

	}

}
