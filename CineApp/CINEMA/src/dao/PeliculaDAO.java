package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.DBConnection;
import pojos.Pelicula;
/**
 * Esta clase gestiona el acceso a datos de la tabla pelicula.
 * Permite consultar todas las peliculas disponibles.
 */

public class PeliculaDAO {
	/**
	 * Retorna todas las peliculas disponibles en la base de datos.
	 * 
	 * Si la consulta no devuelve nada, retorna NULL.
	 * 
	 * @return lista de peliculas o null
	 */
	public ArrayList<Pelicula> getAllpeliculas() {
		ArrayList<Pelicula> pelis = null;
		// SQL que queremos lanzar
		String sql =
			    "SELECT p.* " +
			    "FROM pelicula p " +
			    "JOIN sesion s ON s.idpelicula = p.idpelicula " +
			    "GROUP BY p.idpelicula, p.titulo, p.duracion, p.genero, p.precio " +
			    "ORDER BY MIN(CONCAT(s.fecha,' ',s.horainicio)) ASC";

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
				if (null == pelis)
					pelis = new ArrayList<Pelicula>();

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
				pelis.add(peli);
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
		return pelis;
	}

}
