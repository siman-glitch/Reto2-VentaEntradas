package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import connection.DBConnection;

public class EntradaDAO {

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
