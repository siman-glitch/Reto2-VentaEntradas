package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import dao.PeliculaDAO;
import pojos.Pelicula;

public class TestPeliculaDAO {

	private PeliculaDAO pelidao;

	/**
	 * Comprobamos que el metodo getAllpeliculas() devuelve una lista de peliculas
	 * correctamente desde la base de datos.
	 * 
	 * La lista no debe ser null y debe contener al menos una pelicula.
	 */
	@Test
	public void getAllpeliculascorrectamente() {
		pelidao = new PeliculaDAO();
		ArrayList<Pelicula> pelis = pelidao.getAllpeliculas();
		assertNotNull(pelis);
		assertFalse(pelis.isEmpty());
		for (Pelicula p : pelis) {
			assertTrue(p.getId() > 0);
			assertTrue(p.getPrecio() >= 0);
			assertNotNull(p.getTitulo());
			assertFalse(p.getTitulo().isEmpty());
		}

	}



}
