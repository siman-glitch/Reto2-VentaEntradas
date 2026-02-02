package vista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

import gestor.GestorCompra;
import pojos.Cliente;
import pojos.Entrada;
import pojos.Pelicula;
import pojos.Sesion;
import dao.EntradaDAO;

import dao.CompraDAO;

/**
 * Constructor del menu de compra.
 * 
 * @param sc      Scanner para leer datos del usuario
 * @param cliente Cliente que realiza la compra
 */
public class MenuCompra {

	private final Scanner sc;
	private final MenuPeliculas menupelis;
	private final GestorCompra gestcompra;
	private final MenuSesiones menusesion;
	private final List<Entrada> carrito;

	private final MenuEntradas menuentradas;

	private Cliente cliente;

	public MenuCompra(Scanner sc, Cliente cliente) {
		this.sc = sc;
		this.gestcompra = new GestorCompra(sc);
		this.menupelis = new MenuPeliculas(sc);
		this.menusesion = new MenuSesiones(sc);
		this.menuentradas = new MenuEntradas();
		this.carrito = new ArrayList<>();

		this.cliente = cliente;
	}

	/**
	 * Ejecuta el proceso de compra.
	 * 
	 * Permite seleccionar peliculas y sesiones, añadir entradas al carrito,
	 * confirmar la compra y guardarla en la base de datos.
	 */

	public void menucompra() {
		// ila kan continuar true:kayrj3 lwhile ila false makayrj3ch while
		boolean continuar = true;
		while (continuar) {
			// proseccco de elegir peli
			// mostrar pelis
			ArrayList<Pelicula> peliculas = menupelis.mostrarpeliculas();
			if (peliculas == null || peliculas.isEmpty()) {
				System.out.println("No hay películas disponibles.");
				return;
			}
			// eelegir pelicula
			Pelicula pelielegida = menupelis.elegirPeli(peliculas);

			if (pelielegida == null) {

				if (gestcompra.getCarrito() == null || gestcompra.getCarrito().isEmpty()) {
					System.out.println("No se ha seleccionado ninguna película.");
					continue;
				}

				System.out.print("¿Quieres finalizar la selección y pasar al resumen? (si/no): ");
				String fin = sc.nextLine().trim().toLowerCase();

				while (!fin.equals("si") && !fin.equals("no")) {
					System.out.print("Por favor responde 'si' o 'no': ");
					fin = sc.nextLine().trim().toLowerCase();
				}

				if (fin.equals("si")) {
					System.out.println("Selección finalizada.");
					System.out.println("Pasando al resumen...");
					gestcompra.mostrarResumen();

					// confirmar la compra?
					System.out.print("¿Confirmir Compra (si/no): ");
					String confirmar = sc.nextLine().trim().toLowerCase();

					while (!confirmar.equals("si") && !confirmar.equals("no")) {
						System.out.print("Por favor responde 'si' o 'no': ");
						confirmar = sc.nextLine().trim().toLowerCase();
					}

					if (!confirmar.equals("si")) {
						gestcompra.borraCarrito();
						System.out.println("Compra cancelada. Volviendo a elegir películas...");
						continuar = true;
						continue;
					}

					// totals
					double totalSin = gestcompra.CalcularTotal();
					double desc = gestcompra.calcularDescuento();
					double totalFinal = gestcompra.calcularTotalFinal();

					CompraDAO cdao = new CompraDAO();
					int idCompra = cdao.insertarCompra(cliente.getDni(), LocalDate.now().toString(),
							LocalTime.now().toString(), totalSin, desc);

					if (idCompra == -1) {
						System.out.println("Error: no se pudo guardar la compra en BDD.");
						continuar = true;
						continue;
					}

					System.out.println("ID COMPRA = " + idCompra);

					EntradaDAO edao = new EntradaDAO();
					for (Entrada e : gestcompra.getCarrito()) {
						int idSesion = e.getSesion().getIdSesion();
						int numPersonas1 = e.getNumpersonas();
						double precio = e.getPrecio();

						boolean ok = edao.insertarEntrada(idCompra, idSesion, numPersonas1, precio, desc);
						if (!ok)
							System.out.println("Error guardando una entrada en BDD.");
					}

					System.out.println("Entradas guardadas correctamente..");

					// guardar ticket?
					System.out.print("¿Quieres guardar el ticket (si/no): ");
					String guardar = sc.nextLine().trim().toLowerCase();

					while (!guardar.equals("si") && !guardar.equals("no")) {
						System.out.print("Por favor responde 'si' o 'no': ");
						guardar = sc.nextLine().trim().toLowerCase();
					}

					if (guardar.equals("si")) {
						gestcompra.guardarTicket();
						System.out.println("Ticket guardado correctamente.");
					} else {
						System.out.println("No se ha guardado el ticket.");
					}

					System.out.println("Volviendo al inicio...");
					gestcompra.borraCarrito();
					continuar = true;
					continue;

				} else {
					continue;
				}
			}

			System.out.println("Has elegido:" + pelielegida.getTitulo());

			// mostrar las sesiones
			ArrayList<Sesion> sesiones = menusesion.mostrarsesiones(pelielegida);

			if (sesiones == null || sesiones.isEmpty()) {
				System.out.println("No hay sesiones disponibles para esta pelicula.");
				continuar = true;
			} else {
				// elegir sesion
				Sesion sesionElegida = menusesion.sesionelegida(sesiones);
				if (sesionElegida == null) {
					System.out.println("No se ha seleccionado ninguna sesión. Volviendo a películas...");
					continuar = true;

				} else {

					System.out.println("Has elegido la sesión:");
					System.out.println("  Fecha: " + sesionElegida.getFecha());
					System.out
							.println("  Hora: " + sesionElegida.getHoraInicio() + " == " + sesionElegida.getHoraFin());
					System.out.println("  Sala: " + sesionElegida.getSala().getNombre());
					System.out.println("  Precio: " + sesionElegida.getPrecio());
					// numero de personas
					int numPersonas = menuentradas.numerodepersonas();
					// Anadir al carrito
					sesionElegida.setPelicula(pelielegida);
					gestcompra.addalCarrito(sesionElegida, numPersonas);
					System.out.println("Se han añadido " + numPersonas + " entradas al carrito.");

					// otra pelicula?
					System.out.print("¿Quieres elegir otra película? (si/no): ");
					String resp = sc.nextLine().trim().toLowerCase();

					while (!resp.equals("si") && !resp.equals("no")) {
						System.out.print("Por favor responde 'si' o 'no': ");
						resp = sc.nextLine().trim().toLowerCase();
					}

					if (resp.equals("si")) {
						continuar = true;
					} else {
						continuar = false;
						// resumen
						double totalSin = gestcompra.CalcularTotal();
						double desc = gestcompra.calcularDescuento();
						double totalFinal = gestcompra.calcularTotalFinal();

						System.out.println("Selección finalizada.");
						gestcompra.mostrarResumen();

						// confirmar la compra?
						System.out.print("¿Confirmir Compra (si/no): ");
						String confirmar = sc.nextLine().trim().toLowerCase();

						while (!confirmar.equals("si") && !confirmar.equals("no")) {
							System.out.print("Por favor responde 'si' o 'no': ");
							confirmar = sc.nextLine().trim().toLowerCase();
						}

						if (!confirmar.equals("si")) {
							gestcompra.borraCarrito();
							System.out.println("Compra cancelada. Volviendo a elegir películas...");
							continuar = true;
							continue;
						}

						CompraDAO cdao = new CompraDAO();

						int idCompra = cdao.insertarCompra(cliente.getDni(), LocalDate.now().toString(),
								LocalTime.now().toString(), totalSin, desc);

						if (idCompra == -1) {
							System.out.println("Error: no se pudo guardar la compra en BDD.");
							continuar = true;
							continue;
						}

						System.out.println("ID COMPRA = " + idCompra);
						EntradaDAO edao = new EntradaDAO();

						for (Entrada e : gestcompra.getCarrito()) {

							int idSesion = e.getSesion().getIdSesion();
							int numPersonas1 = e.getNumpersonas();
							double precio = e.getPrecio();

							boolean ok = edao.insertarEntrada(idCompra, idSesion, numPersonas1, precio, desc);

							if (!ok) {
								System.out.println("Error guardando una entrada en BDD.");
							}
						}

						System.out.println(" Entradas guardadas correctamente..");

						// guardar ticket?
						System.out.print("¿Quieres guardar el ticket (si/no): ");
						String guardar = sc.nextLine().trim().toLowerCase();

						while (!guardar.equals("si") && !guardar.equals("no")) {
							System.out.print("Por favor responde 'si' o 'no': ");
							guardar = sc.nextLine().trim().toLowerCase();
						}

						if (guardar.equals("si")) {
							gestcompra.guardarTicket();
							System.out.println("Ticket guardado correctamente.");
						} else {
							System.out.println("No se ha guardado el ticket.");
						}

						System.out.println("Volviendo al inicio...");
						gestcompra.borraCarrito();
						continuar = true;
					}
				}
			}
		}
	}
}
