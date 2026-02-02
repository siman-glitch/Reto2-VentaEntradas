package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import dao.SesionDAO;
import pojos.Sesion;

public class TestSesionDAO {

	private SesionDAO sesdao;

	/**
	 * Comprobamos que el metodo getSesionesPorPeliculaOrdenadas() devuelve
	 * correctamente las sesiones asociadas a una pelicula existente.
	 * 
	 * La lista no debe ser null, no debe estar vacia y las sesiones deben contener
	 * datos validos (idSesion, precio y sala).
	 */
	@Test
	public void getSesionesPorPeliculaOrdenadasCorrectamente() {
		sesdao = new SesionDAO();
		ArrayList<Sesion> sesiones = sesdao.getSesionesPorPeliculaOrdenadas(1);
		assertNotNull(sesiones);
		assertFalse(sesiones.isEmpty());

		for (Sesion s : sesiones) {
			assertTrue(s.getIdSesion() > 0);
			assertTrue(s.getPrecio() >= 0);
			assertNotNull(s.getSala());
			assertTrue(s.getSala().getId() > 0);
			assertNotNull(s.getSala().getNombre());

		}

	}

	/**
	 * Comprobamos que el metodo devuelve una lista vacia cuando la pelicula no
	 * existe o no tiene sesiones asociadas.
	 */
	@Test
	public void getSesionesPorPeliculaOrdenadasListaVacia() {
		sesdao = new SesionDAO();
		ArrayList<Sesion> sesiones = sesdao.getSesionesPorPeliculaOrdenadas(100000);
		assertNotNull(sesiones);
		assertTrue(sesiones.isEmpty());

	}

}
