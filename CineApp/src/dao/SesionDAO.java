package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connection.DBConnection;
import pojos.Sesion;
import pojos.Sala;

public class SesionDAO {

    public ArrayList<Sesion> getSesionesPorPeliculaOrdenadas(int idPelicula) {

        ArrayList<Sesion> sesiones = new ArrayList<>();

        String sql =
            "SELECT idsesion, fecha, horainicio, horafin, precio, numEspectadores, " +
            "s.idsala, sa.nombresala " +
            "FROM sesion s, sala sa " +
            "WHERE s.idsala = sa.idsala AND s.idpelicula = " + idPelicula + " " +
            "ORDER BY fecha, horainicio";

        try {
            Class.forName(DBConnection.DRIVER);
            Connection con = DriverManager.getConnection(
                    DBConnection.URL,
                    DBConnection.USER,
                    DBConnection.PASS
            );

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Sesion sesion = new Sesion();
                sesion.setIdSesion(rs.getInt("idsesion"));
                sesion.setFecha(rs.getString("fecha"));
                sesion.setHoraInicio(rs.getString("horainicio"));
                sesion.setHoraFin(rs.getString("horafin"));
                sesion.setPrecio(rs.getDouble("precio"));
                sesion.setNumespectadores(rs.getInt("numEspectadores"));

                // SALA
                Sala sala = new Sala();
                sala.setId(rs.getInt("idsala"));
                sala.setNombre(rs.getString("nombresala"));

                sesion.setSala(sala);

                sesiones.add(sesion);
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sesiones;
    }
}
