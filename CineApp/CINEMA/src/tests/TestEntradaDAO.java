package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dao.EntradaDAO;

public class TestEntradaDAO {
	private EntradaDAO entaradadao;
	/**
	 * Comprobamos que se inserta correctamente una entrada cuando los datos son validos.
	 */
	@Test
	public void insertarEntradaCorrectamente() {
		entaradadao=new EntradaDAO();

		boolean entcorecta =entaradadao.insertarEntrada(1, 1, 2, 6.00, 0.00);
		
		assertTrue(entcorecta);
	}

	/**
	 * Comprobamos que falla la insercion de una entrada cuando los ids no existen
	 * o los datos no son correctos.
	 */
	@Test
	public void insertarEntradainsCorrectamente() {
		entaradadao=new EntradaDAO();

		boolean ent =entaradadao.insertarEntrada(9999, 99999, 0, 0.00, 1.00);
		
		assertFalse(ent);
	}

}
