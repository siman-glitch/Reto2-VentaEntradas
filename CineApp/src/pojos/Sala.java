package pojos;

import java.util.Objects;

public class Sala {
	private int idSala;
	private String nombre;

	
	
	
	public Sala() {

	}
	public Sala(String nombre) {
		super();
		this.nombre = nombre;
	}

	

	public int getId() {
		return idSala;
	}

	public void setId(int id) {
		this.idSala = id;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idSala);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sala other = (Sala) obj;
		return idSala == other.idSala;
	}

	@Override
	public String toString() {
		return "Sala [nombre=" + nombre + "]";
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Sala(int id, String nombre) {
		super();
		this.idSala = id;
		this.nombre = nombre;
	}

}
