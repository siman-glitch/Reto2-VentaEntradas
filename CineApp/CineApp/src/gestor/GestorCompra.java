package gestor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pojos.Entrada;
import pojos.Pelicula;
import pojos.Sesion;
import vista.MenuSesiones;
import vista.MenuPeliculas;

public class GestorCompra {
	private final Scanner sc;
	private final List<Sesion> sesiones;
	private final List<Entrada> carrito;
	private final MenuSesiones seselegida;
	private final MenuPeliculas menupelis;

	public GestorCompra(Scanner sc) {
		this.sc = sc;
		this.carrito = new ArrayList<>();
		this.seselegida = new MenuSesiones(sc);
		this.menupelis = new MenuPeliculas(sc);

		this.sesiones = new ArrayList<>();

	}
//bhala kat9ol method anan kna9bl ri had 2 x y 
//fash an3yto 3la maethod gestor.adddentr 3ad kayt3mro had sesionElegida, numPersonas);
//sesion = sesionElegida;
//numPersonas = numPersonas;jaav hadxi likatfhm 

	public void addalCarrito(Sesion sesion, int numPersonas) {
	    Entrada entrada = new Entrada();
	    entrada.setSesion(sesion);
	    entrada.setNumpersonas(numPersonas);

	    double subtotal = sesion.getPrecio() * numPersonas;
	    entrada.setPrecio(subtotal);

	    carrito.add(entrada);
	}
	public List<Entrada> getCarrito() {
		return carrito;
	}
	public void resetCarrito() {
	    carrito.clear();
	}
	// doz ela kol ticket jma3 total dyalo
	// kola persona =entrada
	// entrada=precio
	public double CalcularTotal() {
		double total = 0;
		for (Entrada entrada : carrito) {
			total += entrada.getPrecio();
		}

		return total;
	}

	public double calcularDescuento() {
		int numpelis = peliculasDiferentes();

		if (numpelis == 2)
			return 0.20;
		if (numpelis >= 3)
			return 0.30;
		return 0.0;

	}

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

	public double calcularTotalFinal() {
		// total=40euro
		// totlfinal=40(1-0.20)2pelis
		double total = CalcularTotal();
		double Tfinale = total * (1 - calcularDescuento());
		return Tfinale;

	}
	
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
