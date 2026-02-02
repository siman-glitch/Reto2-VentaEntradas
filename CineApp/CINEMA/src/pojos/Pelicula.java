package pojos;

import java.util.Objects;

public class Pelicula {
	
	  private int idpelicula;
	    private String titulo;
	    private int duracion;
	    private String genero; 
	    private double precio;
	    
	    public  Pelicula() {
	    	
	    }

		

		public int getId() {
			return idpelicula;
		}

		public void setId(int id) {
			this.idpelicula = id;
		}

		public String getTitulo() {
			return titulo;
		}

		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}

		public int getDuracion() {
			return duracion;
		}

		public void setDuracion(int duracion) {
			this.duracion = duracion;
		}

		public String getGenero() {
			return genero;
		}

		public void setGenero(String genero) {
			this.genero = genero;
		}

		public double getPrecio() {
			return precio;
		}

		public void setPrecio(double precio) {
			this.precio = precio;
		}

	



		



		@Override
		public String toString() {
			return "Pelicula [titulo=" + titulo + "]";
		}



		@Override
		public int hashCode() {
			return Objects.hash(idpelicula);
		}



		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pelicula other = (Pelicula) obj;
			return idpelicula == other.idpelicula;
		}
	
	    
}
