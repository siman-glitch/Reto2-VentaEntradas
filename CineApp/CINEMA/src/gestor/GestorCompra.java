package gestor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pojos.Entrada;
import pojos.Pelicula;
import pojos.Sesion;
import vista.MenuSesiones;
import vista.MenuPeliculas;
/**
 * Esta clase gestiona la compra del cliente.
 * Controla el carrito de entradas, calcula totales y descuentos,
 * muestra el resumen y permite guardar el ticket.
 */

public class GestorCompra {
	private final Scanner sc;
	private final List<Sesion> sesiones;
	private final List<Entrada> carrito;
	private final MenuSesiones seselegida;
	private final MenuPeliculas menupelis;
	/**
	 * Constructor del gestor de compra.
	 * 
	 * @param sc Scanner para leer datos del usuario
	 */
	public GestorCompra(Scanner sc) {
		this.sc = sc;
		this.carrito = new ArrayList<>();
		this.seselegida = new MenuSesiones(sc);
		this.menupelis = new MenuPeliculas(sc);

		this.sesiones = new ArrayList<>();

	}

	/**
	 * Añade una sesion al carrito creando una entrada con su numero de personas.
	 * 
	 * @param sesion sesion seleccionada
	 * @param numPersonas numero de personas
	 */
	public void addalCarrito(Sesion sesion, int numPersonas) {
		//bhala kat9ol method anan kna9bl ri had 2 x y 
		//fash an3yto 3la maethod gestor.adddentr 3ad kayt3mro had sesionElegida, numPersonas);
		//sesion = sesionElegida;
		//numPersonas = numPersonas;jaav hadxi likatfhm 
	    Entrada entrada = new Entrada();
	    entrada.setSesion(sesion);
	    entrada.setNumpersonas(numPersonas);

	    double subtotal = sesion.getPrecio() * numPersonas;
	    entrada.setPrecio(subtotal);

	    carrito.add(entrada);
	}
	/**
	 * Retorna el carrito de entradas.
	 * 
	 * @return lista de entradas del carrito
	 */
	public List<Entrada> getCarrito() {
		return carrito;
	}
	/**
	 * Vacía el carrito de compra.
	 */
	public void borraCarrito() {
	    carrito.clear();
	}
	/**
	 * Calcula el total sin descuento sumando los subtotales del carrito.
	 * 
	 * @return total sin descuento
	 */
	
	public double CalcularTotal() {
		// doz ela kol ticket jma3 total dyalo
		// kola persona =entrada
		// entrada=precio
		double total = 0;
		for (Entrada entrada : carrito) {
			total += entrada.getPrecio();
		}

		return total;
	}
	/**
	 * Calcula el descuento segun el numero de peliculas diferentes.
	 * 
	 * @return descuento aplicado (0.0, 0.20 o 0.30)
	 */
	public double calcularDescuento() {
		int numpelis = peliculasDiferentes();

		if (numpelis == 2)
			return 0.20;
		if (numpelis >= 3)
			return 0.30;
		return 0.0;

	}
	/**
	 * Cuenta el numero de peliculas diferentes dentro del carrito.
	 * 
	 * @return numero de peliculas distintas
	 */
	public int peliculasDiferentes() {
		ArrayList<Pelicula> peliculas = new ArrayList<>();

		for (Entrada entrada : carrito) {

			if (entrada != null && entrada.getSesion() != null && entrada.getSesion().getPelicula() != null) {

				Pelicula p = entrada.getSesion().getPelicula();

				boolean existe = false;
				for (Pelicula peli : peliculas) {
					if (peli.getId() == p.getId()) {
						existe = true;
						break;
					}
				}

				if (!existe) {
					peliculas.add(p);
				}
			}
		}

		return peliculas.size();
	}
	/**
	 * Calcula el total final aplicando el descuento al total.
	 * 
	 * @return total final con descuento
	 */
	public double calcularTotalFinal() {
		// total=40euro
		// totlfinal=40(1-0.20)2pelis
		double total = CalcularTotal();
		double Tfinale = total * (1 - calcularDescuento());
		return Tfinale;

	}
	/**
	 * Muestra por pantalla el resumen de la compra.
	 * Imprime sesiones, personas, subtotales, total y descuento.
	 */
	public void mostrarResumen() {

	    System.out.println("\n================= RESUMEN COMPRA =================");

	    if (carrito == null || carrito.isEmpty()) {
	        System.out.println("No hay sesiones seleccionadas.");
	        System.out.println("==================================================");
	        return;
	    }

	    for (int i = 0; i < carrito.size(); i++) {

	        Entrada e = carrito.get(i);
	        Sesion s = e.getSesion();

	        System.out.println((i + 1) + ") " + s.getPelicula().getTitulo());
	        System.out.println("   Fecha: " + s.getFecha());
	        System.out.println("   Hora: " + s.getHoraInicio() + " - " + s.getHoraFin());
	        System.out.println("   Sala: " + s.getSala().getNombre());
	        System.out.println("   Precio sesión: " + s.getPrecio() + " €");
	        System.out.println("   Personas: " + e.getNumpersonas());
	        System.out.println("   Subtotal: " + e.getPrecio() + " €");
	        System.out.println("--------------------------------------------------");
	    }

	    System.out.println("Total sin descuento: " + CalcularTotal() + " €");
	    System.out.println("Descuento aplicado: " + (calcularDescuento() * 100) + " %");
	    System.out.println("TOTAL FINAL: " + calcularTotalFinal() + " €");
	    System.out.println("==================================================\n");
	}
	/**
	 * Guarda el ticket en un fichero con las entradas del carrito y los totales.
	 */
	public void guardarTicket() {
		// para guardar ticket
		GestorTicketFichero gestorfile = new GestorTicketFichero();
		gestorfile.actualizarFichero("************************************************");
		gestorfile.actualizarFichero("*                 MyCine TICKET                *");
		gestorfile.actualizarFichero("************************************************");
		// donde se guardan todas la entradas de el cleinte
		for (Entrada entrada : carrito) {
			String titulo = entrada.getSesion().getPelicula().getTitulo();
			double precio = entrada.getPrecio();

			gestorfile.actualizarFichero("Pelicula" + titulo + "||" + "Precio" + precio + " €");

		}
		gestorfile.actualizarFichero("==========================================================");
		gestorfile.actualizarFichero("Total sin descuento :" + CalcularTotal());
		gestorfile.actualizarFichero("Descuento:" + calcularDescuento() * 100 + "%");
		gestorfile.actualizarFichero("Total finale :"+calcularTotalFinal()+"€");
		gestorfile.actualizarFichero("==========================================================");

	}

}
