package pojos;

import java.util.ArrayList;
import java.util.Objects;

public class Compra {

	  private int idcompra;
	    private String fecha;
	    private String hora;
	    private double preciototal; 
	    private double descuento;
	    
	    private Cliente cliente;
	    private ArrayList<Entrada> entradas;
	    
		public  Compra() {
		entradas = new  ArrayList<>();
			
		}

		

		public Compra(String fecha, String hora, double preciototal, double descuento, Cliente cliente,
				ArrayList<Entrada> entradas) {
			super();
			this.fecha = fecha;
			this.hora = hora;
			this.preciototal = preciototal;
			this.descuento = descuento;
			this.cliente = cliente;
			this.entradas = entradas;
		}



		public int getIdcompra() {
			return idcompra;
		}

		public void setIdcompra(int idcompra) {
			this.idcompra = idcompra;
		}

		public String getFecha() {
			return fecha;
		}

		public void setFecha(String fecha) {
			this.fecha = fecha;
		}

		public String getHora() {
			return hora;
		}

		public void setHora(String hora) {
			this.hora = hora;
		}

		public double getPreciototal() {
			return preciototal;
		}

		public void setPreciototal(double preciototal) {
			this.preciototal = preciototal;
		}

		public double getDescuento() {
			return descuento;
		}

		public void setDescuento(double descuento) {
			this.descuento = descuento;
		}

		public Cliente getCliente() {
			return cliente;
		}

		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}

		public ArrayList<Entrada> getEntradas() {
			return entradas;
		}

		@Override
		public int hashCode() {
			return Objects.hash(cliente);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Compra other = (Compra) obj;
			return Objects.equals(cliente, other.cliente);
		}

		@Override
		public String toString() {
			return "Compra [idcompra=" + idcompra + ", fecha=" + fecha + ", hora=" + hora + ", preciototal="
					+ preciototal + ", descuento=" + descuento + ", cliente=" + cliente + ", entradas=" + entradas
					+ "]";
		}

		public void setEntradas(ArrayList<Entrada> entradas) {
			this.entradas = entradas;
		}
	
	
}
