package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.DBConnection;
import pojos.Cliente;


public class ClienteDAO {
	public Cliente  logincliente(String correo,String contrasnea ) {
		Cliente c=null;

		// SQL que queremos lanzar
		String sql = "SELECT * FROM cliente WHERE correoElectronico='"  + correo + "' AND contrasena='" + contrasnea + "'";
		// La conexion con BBDD
				Connection connection = null;
		        Statement statement = null;

				// Vamos a lanzar una sentencia SQL contra la BBDD
				// Result set va a contener todo lo que devuelve la BBDD
				ResultSet resultSet = null;

				try {
					// El Driver que vamos a usar
					Class.forName(DBConnection.DRIVER);

					// Abrimos la conexion con BBDD
					connection = DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASS);

					// Vamos a lanzar la sentencia...
					statement = connection.createStatement();
					resultSet = statement.executeQuery(sql);

		            // Si existe cliente
					if (resultSet.next()) {
						// Sacamos las columnas del resultSet
						c = new Cliente();
						c.setDni(resultSet.getString("dni"));
						c.setNombre(resultSet.getString("nombre"));
						c.setApellidos(resultSet.getString("apellidos"));
						c.setCorreoElectronico(resultSet.getString("correoElectronico"));
						return c;
					
					}
				} catch (SQLException sqle) {
					System.out.println("Error con la BBDD - " + sqle.getMessage());
				} catch (Exception e) {
					System.out.println("Error generico - " + e.getMessage());
				} finally {
					// Cerramos al reves de como las abrimos
					try {
						if (resultSet != null)
							resultSet.close();
					} catch (Exception e) {
						// No hace falta
					}
					try {
						if (statement != null)
							statement.close();
					} catch (Exception e) {
						// No hace falta
					}
					try {
						if (connection != null)
							connection.close();
					} catch (Exception e) {
						// No hace falta
					}
				}
				return c;
			}
	public void insert(	Cliente cliente) {
		// La conexion con BBDD
		Connection connection = null;

		// Vamos a lanzar una sentencia SQL contra la BBDD
		Statement statement = null;

		try {
			// El Driver que vamos a usar
			Class.forName(DBConnection.DRIVER);

			// Abrimos la conexion con BBDD
			connection = DriverManager.getConnection(DBConnection.URL, DBConnection.USER,DBConnection.PASS);

			// Vamos a lanzar la sentencia...
			statement = connection.createStatement();

			// Montamos la SQL. Esta es una forma simple de hacerlo, hay otra mejor...
		       String sql = "INSERT INTO cliente (dni, nombre, apellidos, correoElectronico, contrasena) VALUES ('"
		                + cliente.getDni() + "', '"
		                + cliente.getNombre() + "', '"
		                + cliente.getApellidos() + "', '"
		                + cliente.getCorreoElectronico() + "', '"
		                + cliente.getContrasena() + "')";
			// La ejecutamos...
			statement.executeUpdate(sql);

		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// No hace falta
			}
		}
		
	}

		}
