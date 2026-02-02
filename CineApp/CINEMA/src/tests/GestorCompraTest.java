package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Scanner;

import org.junit.Test;

import gestor.GestorCompra;
import pojos.Pelicula;
import pojos.Sesion;

public class GestorCompraTest {

	/*
	 * ¿Cómo hago tests?
	 * Para cada método importante pruebo:
	 * 1) Normal: funciona correctamente
	 * 2) Error: input incorrecto / caso especial
	 * 3) Excepción: si debe lanzar un error
	 */

	// =========================================================
	// TESTS peliculasDiferentes()
	// =========================================================

	/**
	 * Comprobamos que si el carrito está vacío, el número de películas diferentes
	 * es 0.
	 * 
	 * Caso: Normal / Edge
	 */
	@Test
	public void peliculasDiferentesCarritoVacio() {
		GestorCompra gestor = new GestorCompra(new Scanner(""));

		int numPeliculas = gestor.peliculasDiferentes();

		assertEquals(0, numPeliculas);
	}

	/**
	 * Comprobamos que si añadimos varias sesiones de la misma película, el número
	 * de películas diferentes sigue siendo 1.
	 * 
	 * Caso: Normal
	 */
	@Test
	public void peliculasDiferentesMismaPelicula() {
		GestorCompra gestor = new GestorCompra(new Scanner(""));

		Pelicula p1 = new Pelicula();
		p1.setId(1);
		p1.setTitulo("Matrix");

		Sesion s1 = new Sesion();
		s1.setPrecio(8.0);
		s1.setPelicula(p1);

		Sesion s2 = new Sesion();
		s2.setPrecio(10.0);
		s2.setPelicula(p1);

		gestor.addalCarrito(s1, 1);
		gestor.addalCarrito(s2, 2);

		int numPeliculas = gestor.peliculasDiferentes();

		assertEquals(1, numPeliculas);
	}

	/**
	 * Comprobamos que si añadimos dos películas diferentes, el número de películas
	 * diferentes es 2.
	 * 
	 * Caso: Normal
	 */
	@Test
	public void peliculasDiferentesDosPeliculas() {
		GestorCompra gestor = new GestorCompra(new Scanner(""));

		Pelicula p1 = new Pelicula();
		p1.setId(1);
		p1.setTitulo("Matrix");

		Pelicula p2 = new Pelicula();
		p2.setId(2);
		p2.setTitulo("Inception");

		Sesion s1 = new Sesion();
		s1.setPrecio(8.0);
		s1.setPelicula(p1);

		Sesion s2 = new Sesion();
		s2.setPrecio(9.0);
		s2.setPelicula(p2);

		gestor.addalCarrito(s1, 1);
		gestor.addalCarrito(s2, 1);

		int numPeliculas = gestor.peliculasDiferentes();

		assertEquals(2, numPeliculas);
	}

	/**
	 * Comprobamos que si hay elementos null dentro del carrito no se produce error
	 * y el método devuelve correctamente el número de películas diferentes.
	 * 
	 * Caso: Error / Edge
	 */
	@Test
	public void peliculasDiferentesConNullNoDaError() {
		GestorCompra gestor = new GestorCompra(new Scanner(""));

		Pelicula p1 = new Pelicula();
		p1.setId(1);
		p1.setTitulo("Matrix");

		Sesion s1 = new Sesion();
		s1.setPrecio(8.0);
		s1.setPelicula(p1);

		gestor.addalCarrito(s1, 1);

		// Insertamos un null en el carrito manualmente
		gestor.getCarrito().add(null);

		int numPeliculas = gestor.peliculasDiferentes();

		assertEquals(1, numPeliculas);
	}

	// =========================================================
	// TESTS calcularDescuento()
	// =========================================================

	/**
	 * Comprobamos que si no hay películas o solo hay una película, el descuento es
	 * 0%.
	 * 
	 * Caso: Normal / Edge
	 */
	@Test
	public void calcularDescuentoCeroOUnaPelicula() {
		GestorCompra gestor = new GestorCompra(new Scanner(""));

		// Caso 0 películas
		double descuento0 = gestor.calcularDescuento();
		assertEquals(0.0, descuento0, 0.0001);

		// Caso 1 película
		Pelicula p1 = new Pelicula();
		p1.setId(1);
		p1.setTitulo("Matrix");

		Sesion s1 = new Sesion();
		s1.setPrecio(8.0);
		s1.setPelicula(p1);

		gestor.addalCarrito(s1, 2);

		double descuento1 = gestor.calcularDescuento();
		assertEquals(0.0, descuento1, 0.0001);
	}

	/**
	 * Comprobamos que si hay dos películas diferentes, el descuento es 20%.
	 * 
	 * Caso: Normal
	 */
	@Test
	public void calcularDescuentoDosPeliculas() {
		GestorCompra gestor = new GestorCompra(new Scanner(""));

		Pelicula p1 = new Pelicula();
		p1.setId(1);
		p1.setTitulo("Matrix");

		Pelicula p2 = new Pelicula();
		p2.setId(2);
		p2.setTitulo("Inception");

		Sesion s1 = new Sesion();
		s1.setPrecio(8.0);
		s1.setPelicula(p1);

		Sesion s2 = new Sesion();
		s2.setPrecio(9.0);
		s2.setPelicula(p2);

		gestor.addalCarrito(s1, 1);
		gestor.addalCarrito(s2, 1);

		double descuento = gestor.calcularDescuento();

		assertEquals(0.20, descuento, 0.0001);
	}

	/**
	 * Comprobamos que si hay tres o más películas diferentes, el descuento es 30%.
	 * 
	 * Caso: Normal
	 */
	@Test
	public void calcularDescuentoTresOMasPeliculas() {
		GestorCompra gestor = new GestorCompra(new Scanner(""));

		Pelicula p1 = new Pelicula();
		p1.setId(1);
		p1.setTitulo("Matrix");

		Pelicula p2 = new Pelicula();
		p2.setId(2);
		p2.setTitulo("Inception");

		Pelicula p3 = new Pelicula();
		p3.setId(3);
		p3.setTitulo("Interstellar");

		Sesion s1 = new Sesion();
		s1.setPrecio(8.0);
		s1.setPelicula(p1);

		Sesion s2 = new Sesion();
		s2.setPrecio(9.0);
		s2.setPelicula(p2);

		Sesion s3 = new Sesion();
		s3.setPrecio(10.0);
		s3.setPelicula(p3);

		gestor.addalCarrito(s1, 1);
		gestor.addalCarrito(s2, 1);
		gestor.addalCarrito(s3, 1);

		double descuento = gestor.calcularDescuento();

		assertEquals(0.30, descuento, 0.0001);
	}

	// =========================================================
	// TESTS CalcularTotal() y calcularTotalFinal()
	// =========================================================

	/**
	 * Comprobamos que el total sin descuento se calcula sumando los subtotales del
	 * carrito.
	 * 
	 * Caso: Normal
	 */
	@Test
	public void calcularTotalCorrecto() {
		GestorCompra gestor = new GestorCompra(new Scanner(""));

		Pelicula p1 = new Pelicula();
		p1.setId(1);
		p1.setTitulo("Matrix");

		Pelicula p2 = new Pelicula();
		p2.setId(2);
		p2.setTitulo("Inception");

		Sesion s1 = new Sesion();
		s1.setPrecio(8.0);
		s1.setPelicula(p1);

		Sesion s2 = new Sesion();
		s2.setPrecio(10.0);
		s2.setPelicula(p2);

		// subtotal1 = 8 * 2 = 16
		gestor.addalCarrito(s1, 2);

		// subtotal2 = 10 * 1 = 10
		gestor.addalCarrito(s2, 1);

		double total = gestor.CalcularTotal();

		assertEquals(26.0, total, 0.0001);
	}

	/**
	 * Comprobamos que el total final se calcula aplicando el descuento
	 * correctamente (20% para 2 películas diferentes).
	 * 
	 * Caso: Normal
	 */
	@Test
	public void calcularTotalFinalConDescuento20() {
		GestorCompra gestor = new GestorCompra(new Scanner(""));

		Pelicula p1 = new Pelicula();
		p1.setId(1);
		p1.setTitulo("Matrix");

		Pelicula p2 = new Pelicula();
		p2.setId(2);
		p2.setTitulo("Inception");

		Sesion s1 = new Sesion();
		s1.setPrecio(10.0);
		s1.setPelicula(p1);

		Sesion s2 = new Sesion();
		s2.setPrecio(20.0);
		s2.setPelicula(p2);

		gestor.addalCarrito(s1, 1); // 10
		gestor.addalCarrito(s2, 1); // 20

		// total = 30
		// descuento = 20%
		// totalFinal = 30 * (1 - 0.20) = 24
		double totalFinal = gestor.calcularTotalFinal();

		assertEquals(24.0, totalFinal, 0.0001);
	}

	/**
	 * Comprobamos que el total final se calcula aplicando el descuento
	 * correctamente (30% para 3 películas diferentes).
	 * 
	 * Caso: Normal
	 */
	@Test
	public void calcularTotalFinalConDescuento30() {
		GestorCompra gestor = new GestorCompra(new Scanner(""));

		Pelicula p1 = new Pelicula();
		p1.setId(1);
		p1.setTitulo("A");

		Pelicula p2 = new Pelicula();
		p2.setId(2);
		p2.setTitulo("B");

		Pelicula p3 = new Pelicula();
		p3.setId(3);
		p3.setTitulo("C");

		Sesion s1 = new Sesion();
		s1.setPrecio(10.0);
		s1.setPelicula(p1);

		Sesion s2 = new Sesion();
		s2.setPrecio(10.0);
		s2.setPelicula(p2);

		Sesion s3 = new Sesion();
		s3.setPrecio(10.0);
		s3.setPelicula(p3);

		gestor.addalCarrito(s1, 1); // 10
		gestor.addalCarrito(s2, 1); // 10
		gestor.addalCarrito(s3, 1); // 10

		// total = 30
		// descuento = 30%
		// totalFinal = 30 * (1 - 0.30) = 21
		double totalFinal = gestor.calcularTotalFinal();

		assertEquals(21.0, totalFinal, 0.0001);
	}

	/**
	 * Comprobamos que si el carrito está vacío, el total y el total final son 0.
	 * 
	 * Caso: Edge
	 */
	@Test
	public void totalYTotalFinalCarritoVacio() {
		GestorCompra gestor = new GestorCompra(new Scanner(""));

		double total = gestor.CalcularTotal();
		double totalFinal = gestor.calcularTotalFinal();

		assertEquals(0.0, total, 0.0001);
		assertEquals(0.0, totalFinal, 0.0001);

		assertTrue(gestor.getCarrito().isEmpty());
	}

}
