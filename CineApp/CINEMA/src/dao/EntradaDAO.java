package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import connection.DBConnection;
/**
 * Esta clase gestiona el acceso a datos de la tabla entrada.
 * Permite insertar entradas asociadas a una compra y una sesion.
 */
public class EntradaDAO {
	/**
	 * Inserta una entrada en la tabla entrada.
	 * 
	 * @param idCompra id de la compra
	 * @param idSesion id de la sesion
	 * @param numPersonas numero de personas
	 * @param precio precio total de la entrada
	 * @param descuento descuento aplicado
	 * @return true si se inserta correctamente o false si hay error
	 */
	public boolean insertarEntrada(int idCompra, int idSesion, int numPersonas, double precio, double descuento) {

		String sql = "INSERT INTO entrada (numpersonas, precio, descuento, idcompra, idsesion) " +
				     "VALUES (" + numPersonas + "," + precio + "," + descuento + "," + idCompra + "," + idSesion + ")";

		Connection connection = null;
		Statement statement = null;

		try {
			Class.forName(DBConnection.DRIVER);
			connection = DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASS);

			statement = connection.createStatement();
			statement.executeUpdate(sql);

			return true;

		} catch (Exception e) {
			System.out.println("Error con la BBDD - " + e.getMessage());
		} finally {
			try { if (statement != null) statement.close(); } catch (Exception e) {}
			try { if (connection != null) connection.close(); } catch (Exception e) {}
		}

		return false;
	}
}
