package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import connection.DBConnection;

public class CompraDAO {

	public int insertarCompra(String dniCliente, String fecha, String hora, double total, double descuento) {

		int idGenerado = -1;

		String sql = "INSERT INTO compra (dni, fecha, hora, preciototal, descuento) " +
		             "VALUES ('" + dniCliente + "','" + fecha + "','" + hora + "'," + total + "," + descuento + ")";

		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;

		try {
			Class.forName(DBConnection.DRIVER);
			connection = DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASS);

			statement = connection.createStatement();
			statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			rs = statement.getGeneratedKeys();
			if (rs.next()) {
				idGenerado = rs.getInt(1);
			}

		} catch (Exception e) {
			System.out.println("Error con la BBDD - " + e.getMessage());
		} finally {
			try { if (rs != null) rs.close(); } catch (Exception e) {}
			try { if (statement != null) statement.close(); } catch (Exception e) {}
			try { if (connection != null) connection.close(); } catch (Exception e) {}
		}

		return idGenerado;
	}
}
