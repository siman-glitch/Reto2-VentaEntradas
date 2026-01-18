package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.DBConnection;
import pojos.Cliente;
import pojos.Pelicula;

public class PeliculaDAO {

	public ArrayList<Pelicula> getAllpeliculas() {
		ArrayList<Pelicula> ret = null;
		// SQL que queremos lanzar
		String sql = "select * from pelicula";

		// La conexion con BBDD
		Connection connection = null;

		// Vamos a lanzar una sentencia SQL contra la BBDD
		// Result set va a contener todo lo que devuelve la BBDD
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// El Driver que vamos a usar
			Class.forName(DBConnection.DRIVER);

			// Abrimos la conexion con BBDD
			connection = DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASS);

			// Vamos a lanzar la sentencia...
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			// Recorremos resultSet, que tiene las filas de la tabla
			while (resultSet.next()) {

				// Hay al menos una fila en el cursos, inicializamos el ArrayList
				if (null == ret)
					ret = new ArrayList<Pelicula>();

				// nuevo object de cada row
				Pelicula peli = new Pelicula();
				// Sacamos las columnas del resultSetç
				// n9ra mn bd b get
				// Metemos los datos en pelicula b set

				peli.setId(resultSet.getInt("idpelicula"));
				peli.setTitulo(resultSet.getString("titulo"));
				peli.setDuracion(resultSet.getInt("duracion"));
				peli.setGenero(resultSet.getString("genero"));
				peli.setPrecio(resultSet.getDouble("precio"));

				// Lo guardamos en la lista
				ret.add(peli);
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
		return ret;
	}

}
