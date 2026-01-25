package vista;

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
import vista.MenuCompra;

public class Menu {

	private static Scanner sc = null;
	private MenuCompra compra;
	private MenuPeliculas pelis;
	private GestorCompra gest;
	private MenuSesiones ses;


	public Menu() {
		sc = new Scanner(System.in);
		pelis = new MenuPeliculas();
		gest = new GestorCompra(sc);
		ses=new MenuSesiones();
		compra = new MenuCompra(sc, pelis, ses, gest);
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
		while (true) {
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
					// Después del login, arrancamos el flujo de compra
					compra.iniciar();
					break;

				} else {
					System.out.println("Error al iniciar sesión ");
					boolean salir = preguntarRegistro();
					if (salir)
						break;

				}
			}
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
			clientedao.insertCliente(c);
			System.out.println("\nRegistro completado correctamente.\n");
		} catch (Exception e) {
			System.out.println("\nError al registrar.\n");
		}

	}


		
}
		
		

