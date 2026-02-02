package tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import connection.DBConnection;
import pojos.Cliente;

public class TestDBConnection {

	/**
	 * Comprobamos que se realiza la conexion correctamente con la Base de Datos.
	 */

	@Test
	public void conexionRealizada() {
		Connection conn = null;

		try {
			// El Driver que vamos a usar
			Class.forName(DBConnection.DRIVER);

			// Abrimos la conexion con BBDD
			conn = DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASS);
			assertNotNull(conn);

			conn.close();

		} catch (Exception e) {
			fail(" Error conexion: " + e.getMessage());
		}

	}

	/**
	 * Comprobamos que la conexion se cierra correctamente.
	 */
	@Test
	public void conexionCerrada() {
		Connection conn = null;

		try {
			// El Driver que vamos a usar
			Class.forName(DBConnection.DRIVER);

			// Abrimos la conexion con BBDD
			conn = DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASS);
			conn.close();
			assertTrue(conn.isClosed());

		} catch (Exception e) {
			fail(" Error: " + e.getMessage());
		}

	}
	/**
	 * Comprobamos que falla la conexion cuando la Base de Datos no existe.
	 */
	@Test
	public void conexionConBDNoExiste() {

		try {
			// El Driver que vamos a usar
			Class.forName(DBConnection.DRIVER);

			// Abrimos la conexion con BBDD
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3311/BD_noexiste", DBConnection.USER,
					DBConnection.PASS);
			fail("Test debe fallar! ");

		} catch (Exception e) {
			assertTrue(true);
		}

	}
}
