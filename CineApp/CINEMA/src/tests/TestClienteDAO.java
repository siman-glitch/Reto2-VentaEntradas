package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import dao.ClienteDAO;
import pojos.Cliente;

public class TestClienteDAO {
	private static ClienteDAO clientedao;

	/**
	 * Comprobamos que el login funciona correctamente cuando los datos existen en
	 * la BD.
	 */
	@Test
	public void loginclientecorrecto() {
		clientedao = new ClienteDAO();

		Cliente c = clientedao.logincliente("elena@correo.es", "1234");
		// no debe ser null
		assertNotNull(c);
		// mismo correo y contrasnea
		assertEquals("elena@correo.es", c.getCorreoElectronico());
	}

	/**
	 * Comprobamos que el login devuelve null cuando la contrasena es incorrecta.
	 */

	@Test
	public void loginclienteincorrecto() {
		clientedao = new ClienteDAO();
		Cliente c = clientedao.logincliente("elena@correo.es", "0000"); // pass غلط
		// khso login irja3 null
		assertNull(c);

	}

	/**
	 * Comprobamos que se genera una excepcion NullPointerException cuando el objeto
	 * DAO es null.
	 */
	@Test(expected = NullPointerException.class)
	public void login() {
		// hna rayti7 exption hit nul 3dna donc hna w93 lralat
		ClienteDAO clientedao = null;
		clientedao.logincliente("elena@correo.es", "1234");

	}

	/**
	 * Comprobamos que se inserta un cliente correctamente en la BD.
	 */
	@Test
	public void insertClienteCorrectamente() {
		clientedao = new ClienteDAO();
		Cliente c = new Cliente();
		c.setDni("00002343o");
		c.setNombre("siman");
		c.setApellidos("idrissi");
		c.setCorreoElectronico("simanedrissi11@gmail.com");
		c.setContrasena("123456789");

		// insert
		clientedao.insertCliente(c);
		// comprobamos con login que el cliente existe
		Cliente login = clientedao.logincliente("simanedrissi11@gmail.com", "123456789");

		assertNotNull(login);
		assertEquals("simanedrissi11@gmail.com", login.getCorreoElectronico());
	}

	/**
	 * Comprobamos que falla el insert cuando el DNI ya existe (clave primaria
	 * duplicada).
	 */
	@Test
	public void insertClienteFallado() {
		clientedao = new ClienteDAO();
		Cliente c = new Cliente();
		c.setDni("00002343o"); // DNI duplicado
		c.setNombre("test");
		c.setApellidos("test");
		c.setCorreoElectronico("test_test@gmail.com");
		c.setContrasena("1234");

		// insert (debe fallar)
		clientedao.insertCliente(c);
		// comprobamos que NO se ha insertado

		Cliente login = clientedao.logincliente("test_test@gmail.com", "1234");

	}
}
