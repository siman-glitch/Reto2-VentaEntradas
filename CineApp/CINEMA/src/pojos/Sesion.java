package pojos;

import java.util.Objects;

public class Sesion {
	private int idSesion;
	private String fecha;
	private String horaInicio;
	private String horaFin;
	private double precio;
	private int numespectadores;

	private Pelicula pelicula;
	private Sala sala;
	public Sesion() {
	}

	public Sesion(String fecha, String horaInicio, String horaFin, double precio, int numespectadores,
			Pelicula pelicula, Sala sala) {
		super();
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.precio = precio;
		this.numespectadores = numespectadores;
		this.pelicula = pelicula;
		this.sala = sala;
	}

	public int getIdSesion() {
		return idSesion;
	}
	public void setIdSesion(int idSesion) {
		this.idSesion = idSesion;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getNumespectadores() {
		return numespectadores;
	}
	public void setNumespectadores(int numespectadores) {
		this.numespectadores = numespectadores;
	}
	public Pelicula getPelicula() {
		return pelicula;
	}
	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	@Override
	public String toString() {
		return "Sesion [fecha=" + fecha + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", pelicula="
				+ pelicula + ", sala=" + sala + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(idSesion);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sesion other = (Sesion) obj;
		return idSesion == other.idSesion;
	}
	
}
