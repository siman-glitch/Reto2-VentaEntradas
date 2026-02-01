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

import dao.CompraDAO;
import dao.EntradaDAO;

public class MenuCompra {

	private final Scanner sc;
	private final MenuPeliculas menupelis;
	private final GestorCompra gestcompra;
	private final MenuSesiones menusesion;
	private final MenuEntradas menuentradas;
	private Cliente cliente;

	private final List<Sesion> sesiones;

	public MenuCompra(Scanner sc, Cliente cliente) {
		this.sc = sc;
		this.gestcompra = new GestorCompra(sc);
		this.menupelis = new MenuPeliculas(sc);
		this.menusesion = new MenuSesiones(sc);
		this.menuentradas = new MenuEntradas();
		this.sesiones = new ArrayList<>();
		this.cliente = cliente;
	}

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
			    System.out.println("Selección finalizada.");

			    if (gestcompra.getCarrito() != null && !gestcompra.getCarrito().isEmpty()) {
			        System.out.println("Pasando al resumen...");
			        gestcompra.mostrarResumen();
			    } else {
			        System.out.println("No hay sesiones seleccionadas.");
			    }
			    return;
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
					System.out.println("  Hora: " + sesionElegida.getHoraInicio() + " == " + sesionElegida.getHoraFin());
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

					if (resp.equals("si")) {

						continuar = true;
					} else {
						// resumen
						double totalSin = gestcompra.CalcularTotal();
						double desc = gestcompra.calcularDescuento();
						double totalFinal = gestcompra.calcularTotalFinal();

						System.out.println("Selección finalizada.");
						gestcompra.mostrarResumen();

						// confirmar la compra?
						System.out.print("¿Confirmir Compra (si/no): ");
						String confirmar = sc.nextLine().trim().toLowerCase();
						if (!confirmar.equals("si")) {
							System.out.println("Compra cancelada. Volviendo a elegir películas...");
							continuar = true;
							continue;
						}

						// ====== AQUI: GUARDAR COMPRA + ENTRADAS EN BBDD (BESIT) ======
						if (cliente != null) {

							String fecha = LocalDate.now().toString();
							String hora = LocalTime.now().withNano(0).toString();

							CompraDAO compraDAO = new CompraDAO();
							int idCompra = compraDAO.insertarCompra(cliente.getDni(), fecha, hora, totalSin, desc);

							if (idCompra != -1) {

								EntradaDAO entradaDAO = new EntradaDAO();

								for (Entrada e : gestcompra.getCarrito()) {
									int idSesion = e.getSesion().getIdSesion();
									int numP = e.getNumpersonas();
									double precio = e.getPrecio();

									entradaDAO.insertarEntrada(idCompra, idSesion, numP, precio, desc);
								}

								System.out.println("Compra guardada en la BBDD con id: " + idCompra);

							} else {
								System.out.println("No se pudo guardar la compra en la BBDD.");
							}

						} else {
							System.out.println("Cliente no disponible. No se guardara la compra en BBDD.");
						}
						// ====== FIN GUARDAR EN BBDD ======

						// guardar ticket?
						System.out.print("¿Quieres guardar el ticket (si/no): ");
						String guardar = sc.nextLine().trim().toLowerCase();

						if (guardar.equals("si")) {
							gestcompra.guardarTicket();
							System.out.println("Ticket guardado correctamente.");
						} else {
							System.out.println("No se ha guardado el ticket.");
						}
						gestcompra.resetCarrito();
						System.out.println("Volviendo al inicio...");
						continuar = true;
					}
				}
			}
		}

	}

}
