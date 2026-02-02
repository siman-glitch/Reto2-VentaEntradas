package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import connection.DBConnection;

/**
 * Esta clase gestiona el acceso a datos de la tabla compra. Permite insertar
 * compras en la base de datos.
 */
public class CompraDAO {
	/**
	 * Inserta una compra en la tabla compra.
	 * 
	 * @param dniCliente DNI del cliente que realiza la compra
	 * @param fecha      fecha de la compra
	 * @param hora       hora de la compra
	 * @param total      precio total sin descuento
	 * @param descuento  descuento aplicado
	 * @return id generado de la compra o -1 si hay error
	 */
	public int insertarCompra(String dniCliente, String fecha, String hora, double total, double descuento) {
		// si insert a fallado devuelve -1
		int idGenerado = -1;

		String sql = "INSERT INTO compra (dni, fecha, hora, preciototal, descuento) " + "VALUES ('" + dniCliente + "','"
				+ fecha + "','" + hora + "'," + total + "," + descuento + ")";

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
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
		}
//se no ha falllado el insert devuelve id 
		return idGenerado;
	}
}
