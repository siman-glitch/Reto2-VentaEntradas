package vista;

import java.util.Scanner;

import dao.ClienteDAO;
import gestor.GestorCompra;
import pojos.Cliente;
import utils.Validaciones;

public class MenuPrincipal {
	private static Scanner sc = null;
	private ClienteDAO clientedao = new ClienteDAO();
	private Cliente cliente;

	public MenuPrincipal() {
	    sc = new Scanner(System.in);
	}


	public static void inicio() {
		System.out.println("*****************************************");
		System.out.println("*             BIENVENIDO                *");
		System.out.println("*              A MyCine                 *");
		System.out.println("*                                       *");
	    System.out.println("*   Pulsa ENTER para continuar...       *");
		System.out.println("*---------------------------------------*");
		System.out.println("*****************************************");
		sc.nextLine();
	}

	/**
	 * Gestiona el inicio de sesion del cliente.
	 * Si el login es correcto, entra al menu de compra.
	 * Si el login falla, pregunta si el usuario quiere registrarse.
	 */
	public void login() {
		inicio();
		boolean continuar = true;
		while (continuar) {
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

					// menu de compra
					GestorCompra gestor = new GestorCompra(sc);
					MenuCompra compra = new MenuCompra(sc, cliente);
					compra.menucompra();

					continuar = false;
				} else {
					System.out.println("Error al iniciar sesión ");

					boolean salir = preguntarRegistro();
					if (salir) {
						continuar = false;
					}
				}
			}
		}

	}
	/**
	 * Pregunta al usuario si quiere registrarse.
	 * 
	 * @return true si se quiere salir del programa, false para volver a iniciar sesion
	 */

	public static boolean preguntarRegistro() {
		System.out.print("¿Quieres registrarte y disfrutar de ventajas exclusivas? (si/no?): ");
		String respuesta = sc.nextLine().trim().toLowerCase();

		if (respuesta.equals("si")) {
			Registrar();
			return false;

		} else {

			System.out.println("Gracias por usar MyCine. Volviendo a iniciar sesión...");
			return false;
		}

	}
	/**
	 * Registra un nuevo cliente pidiendo sus datos y guardandolos en la BDD.
	 */
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

		c.setDni(pedirDNI());
		c.setNombre(pedirNombre());
		c.setApellidos(pedirApellido());
		c.setCorreoElectronico(pedirCorreo());
		c.setContrasena(pedirContrasena());
		try {
			clientedao.insertCliente(c);
			System.out.println("nRegistro completado correctamente.");
		} catch (Exception e) {
			System.out.println("Error al registrar.");
		}

	}
	/**
	 * Pide y valida el DNI del cliente.
	 * 
	 * @return DNI valido
	 */
	public static String pedirDNI() {
		String dni;
		while (true) {
			System.out.print("Introduce tu DNI (12345678Z): ");
			dni = sc.nextLine().trim();

			if (Validaciones.validarDNI(dni)) {
				return dni;
			}
		}
	}
	/**
	 * Pide y valida el nombre del cliente.
	 * 
	 * @return nombre valido
	 */

	public static String pedirNombre() {
		String nombre;
		while (true) {
			System.out.print("Introduce tu Nombre: ");
			nombre = sc.nextLine().trim();

			if (Validaciones.validarNombre(nombre)) {
				return nombre;
			}
		}
	}
	/**
	 * Pide y valida los apellidos del cliente.
	 * 
	 * @return apellidos validos
	 */

	public static String pedirApellido() {
		String apellido;
		while (true) {
			System.out.print("Introduce tus Apellidos: ");
			apellido = sc.nextLine().trim();

			if (Validaciones.validarNombre(apellido)) {
				return apellido;
			}
		}
	}
	/**
	 * Pide y valida el correo del cliente.
	 * 
	 * @return correo valido
	 */
	public static String pedirCorreo() {
		String correo;
		while (true) {
			System.out.print("Introduce tu correo: ");
			correo = sc.nextLine().trim();

			if (Validaciones.validarCorreo(correo)) {
				return correo;
			}
		}
	}
	/**
	 * Pide y valida la contrasena del cliente.
	 * 
	 * @return contrasena valida
	 */
	public static String pedirContrasena() {
		String pass;
		while (true) {
			System.out.print("Introduce la contrasena: ");
			pass = sc.nextLine().trim();

			if (Validaciones.validarContrasena(pass)) {
				return pass;
			}
		}
	}

}