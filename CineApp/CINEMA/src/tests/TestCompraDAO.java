package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dao.CompraDAO;
import pojos.Compra;

public class TestCompraDAO {

	private CompraDAO compradao;

	/**
	 * Comprobamos que se inserta correctamente una compra en la base de datos
	 * cuando el cliente existe.
	 */
	@Test
	public void insertarCompraCorrectamente() {
		compradao = new CompraDAO();
		int id = compradao.insertarCompra("45678901D", "2026-02-02", "12:00:00", 15.0, 0.0);

		assertTrue("No se inserto la compra (id = -1)", id > 0);
	}

	/**
	 * Comprobamos que falla la insercion de una compra cuando el DNI del cliente no
	 * existe en la tabla cliente.
	 */

	@Test
	public void insertarCompraIncorrectamente() {
		compradao = new CompraDAO();
		int id = compradao.insertarCompra("DNI_NO_EXISTE", "2022-02-02", "12:00:00", 11.0, 0.0);

		assertEquals(-1, id);
	}
}
