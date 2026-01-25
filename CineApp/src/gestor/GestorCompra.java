package gestor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pojos.Entrada;
import pojos.Pelicula;
import pojos.Sesion;

public class GestorCompra {
	private final Scanner sc;
	private final List<Entrada> carrito = new ArrayList<>();
	private final ArrayList<Pelicula> pelisElegidas = new ArrayList<>();
	private Sesion sesionElegida;

	public GestorCompra(Scanner sc) {
		this.sc = (sc != null) ? sc : new Scanner(System.in);
	}

	public void setSesionElegida(Sesion sesionElegida) {
		this.sesionElegida = sesionElegida;
	}

	public Sesion getSesionElegida() {
		return sesionElegida;
	}
	
	
	

	public void numerodepersonas() {
		if (sesionElegida == null) {
			System.out.println("Primero debes elegir una sesión.");
			return;
		}
		int numpersonas = 0;
		do {

			System.out.println("   Introducir numero de personas :             ");
			// solo numeros
			while (!sc.hasNextInt()) {
				System.out.print("Por favor, introduce un número válido: ");
				sc.next();
			}
			numpersonas = sc.nextInt();
			sc.nextLine();

			// seguir preguntado si ha escribido 0 o menos de 0
		} while (numpersonas <= 0);

		for (int i = 0; i < numpersonas; i++) {
			Entrada e = new Entrada();
			e.setSesion(sesionElegida);
			e.setPrecio(sesionElegida.getPrecio());
			carrito.add(e);
		}
		System.out.println("Entradas añadidas al carrito:");
		System.out.println("  Cantidad: " + numpersonas);
		System.out.println("  Sesión: " + this.sesionElegida.getFecha() + " | " + this.sesionElegida.getHoraInicio()
				+ " - " + this.sesionElegida.getHoraFin());
		System.out.println("  Sala: " + this.sesionElegida.getSala().getNombre());
		System.out.println("  Precio unitario: " + this.sesionElegida.getPrecio() + " €");
		System.out.println("  Total precio: " + (numpersonas * this.sesionElegida.getPrecio()) + " €");

	}

	public double calcularTotalSinDescuento() {
		double total = 0;
		for (Entrada e : carrito) {
			if (e != null) {
				total += e.getPrecio();
			}
		}
		return total;
	}

	public void calculartotal() {
		if (carrito.isEmpty()) {
			System.out.println("No has añadido entradas al carrito.");
			return;
		}
		double total = 0;
		System.out.println("=== Resumen de entradas ===");

		for (int i = 0; i < carrito.size(); i++) {
			Entrada e = carrito.get(i);
			if (e == null || e.getSesion() == null) {
				System.out.println((i + 1) + ") [Entrada inválida: sesión null]");
				continue;
			}

			total += e.getPrecio();
			System.out.println((i + 1) + ") " + e.getSesion().getFecha() + " | " + e.getSesion().getHoraInicio()
					+ " | " + e.getSesion().getSala().getNombre() + " : " + e.getPrecio() + " €");
		}

		System.out.println("Total sin descuento: " + total + " €");
		double totalConDescuento = aplicarDescuento(total);
		if (totalConDescuento != total) {
			System.out.println("Total con descuento: " + totalConDescuento + " €");
		}
	}

	public void guardarpelis(Pelicula peliElegida) {
		if (peliElegida == null) {
			return;
		}
		if (!pelisElegidas.contains(peliElegida)) {
			pelisElegidas.add(peliElegida);
		}
	}

	/**
	 * Descuento simple por variedad de películas:
	 * - 2 pelis distintas: 5%
	 * - 3+ pelis distintas: 10%
	 */
	public double aplicarDescuento(double totalSinDescuento) {
		int n = pelisElegidas.size();
		if (n >= 3) {
			return totalSinDescuento * 0.90;
		}
		if (n == 2) {
			return totalSinDescuento * 0.95;
		}
		return totalSinDescuento;
	}
}
