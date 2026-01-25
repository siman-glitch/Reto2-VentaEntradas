package vista;

import java.util.ArrayList;
import java.util.Scanner;

import gestor.GestorCompra;
import pojos.Pelicula;
import pojos.Sesion;

/**
 * Menú de compra: coordina la selección de Película -> Sesión -> Nº de entradas.
 *
 * IMPORTANTE:
 * - La lógica de “carrito / total / descuento” vive en GestorCompra.
 * - Aquí solo pedimos datos al usuario y llamamos a los otros menús/gestor.
 */
public class MenuCompra {
	private final Scanner sc;
	private final MenuPeliculas menuPeliculas;
	private final MenuSesiones menuSesiones;
	private final GestorCompra gestorCompra;

	public MenuCompra(Scanner sc, MenuPeliculas menuPeliculas, MenuSesiones menuSesiones, GestorCompra gestorCompra) {
		this.sc = sc;
		this.menuPeliculas = menuPeliculas;
		this.menuSesiones = menuSesiones;
		this.gestorCompra = gestorCompra;
	}

	public void iniciar() {
		while (true) {
			ArrayList<Pelicula> peliculas = menuPeliculas.mostrarpeliculas();
			if (peliculas == null || peliculas.isEmpty()) {
				return;
			}

			Pelicula peliElegida = menuPeliculas.elegirPeli(peliculas);
			// Si el usuario no quiere seguir eligiendo pelis, salimos al resumen.
			if (peliElegida == null) {
				break;
			}

			System.out.println("Has elegido: " + peliElegida.getTitulo());
			ArrayList<Sesion> sesiones = menuSesiones.mostrarsesiones(peliElegida);
			if (sesiones == null || sesiones.isEmpty()) {
				continue;
			}

			Sesion sesionElegida = menuSesiones.sesionelegida(sesiones);
			if (sesionElegida == null) {
				continue;
			}

			gestorCompra.setSesionElegida(sesionElegida);
			gestorCompra.guardarpelis(peliElegida);

			System.out.println("Has elegido la sesión:");
			System.out.println("  Fecha: " + sesionElegida.getFecha());
			System.out.println(
					"  Hora: " + sesionElegida.getHoraInicio() + " - " + sesionElegida.getHoraFin());
			System.out.println("  Sala: " + sesionElegida.getSala().getNombre());
			System.out.println("  Precio: " + sesionElegida.getPrecio() + " €");

			gestorCompra.numerodepersonas();

			System.out.print("¿Quieres elegir otra película? (si/no): ");
			String r = sc.nextLine().trim().toLowerCase();
			if (r.equals("no")) {
				break;
			}
		}

		// Resumen final
		gestorCompra.calculartotal();
	}
}
