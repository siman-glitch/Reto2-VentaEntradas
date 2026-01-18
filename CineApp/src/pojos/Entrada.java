package pojos;

import java.util.Objects;

public class Entrada {
	 private int identrada;
	    private int numpersonas;
	    private double precio;
	    private double descuento ; 
	    
	    private Sesion sesion;
	    private Compra compra;
	    
	  

		

		public Entrada(int numpersonas, double precio, double descuento, Sesion sesion, Compra compra) {
			super();
			this.numpersonas = numpersonas;
			this.precio = precio;
			this.descuento = descuento;
			this.sesion = sesion;
			this.compra = compra;
		}

		public int getIdentrada() {
			return identrada;
		}

		public void setIdentrada(int identrada) {
			this.identrada = identrada;
		}

		public int getNumpersonas() {
			return numpersonas;
		}

		public void setNumpersonas(int numpersonas) {
			this.numpersonas = numpersonas;
		}

		public double getPrecio() {
			return precio;
		}

		public void setPrecio(double precio) {
			this.precio = precio;
		}

		public double getDescuento() {
			return descuento;
		}

		public void setDescuento(double descuento) {
			this.descuento = descuento;
		}

		public Sesion getSesion() {
			return sesion;
		}

		public void setSesion(Sesion sesion) {
			this.sesion = sesion;
		}

		public Compra getCompra() {
			return compra;
		}

		public void setCompra(Compra compra) {
			this.compra = compra;
		}

		public Entrada() {
	    }
		  @Override
			public String toString() {
				return "Entrada [identrada=" + identrada + ", numpersonas=" + numpersonas + ", precio=" + precio
						+ ", descuento=" + descuento + ", sesion=" + sesion + ", compra=" + compra + "]";
			}

		  @Override
		  public int hashCode() {
			return Objects.hash(identrada);
		  }

		  @Override
		  public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Entrada other = (Entrada) obj;
			return identrada == other.identrada;
		  }
	    
	    
	    }
