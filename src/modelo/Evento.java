package modelo;

import java.io.Serializable;

public class Evento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String idEvento;
	private String nombreEv;
	private String fechaEv;
	private String tipoEv;
	private double precioEv;
	private int cantTickets;
	private boolean eventoConSillas;
	private String imagenEvento;
	private int ticketsVendidos;

	public Evento(String idEvento, String nombreEv, String fechaEv, String tipoEv, 
			double precioEv, int cantTickets, boolean eventoConSillas, String imagenEvento) {
		this.idEvento = idEvento;
		this.nombreEv = nombreEv;
		this.fechaEv = fechaEv;
		this.tipoEv = tipoEv;
		this.precioEv = precioEv;
		this.cantTickets = cantTickets;
		this.eventoConSillas = eventoConSillas;
		this.imagenEvento = imagenEvento;
		this.ticketsVendidos = 0;
	}
	
	// Método para vender tickets con manejo de excepciones
	public void venderTickets(int cantidad) throws Exception {
		if (cantidad <= 0) {
			throw new Exception("La cantidad debe ser mayor a 0");
		}
		
		if (ticketsVendidos + cantidad > cantTickets) {
			throw new Exception("No hay suficientes tickets disponibles. " +
					"Disponibles: " + (cantTickets - ticketsVendidos));
		}
		
		ticketsVendidos += cantidad;
	}
	
	// Método para devolver tickets (en caso de cancelación)
	public void devolverTickets(int cantidad) throws Exception {
		if (cantidad <= 0) {
			throw new Exception("La cantidad debe ser mayor a 0");
		}
		
		if (ticketsVendidos - cantidad < 0) {
			throw new Exception("No se pueden devolver más tickets de los vendidos");
		}
		
		ticketsVendidos -= cantidad;
	}
	
	// Método para obtener tickets disponibles
	public int getTicketsDisponibles() {
		return cantTickets - ticketsVendidos;
	}

	// Getters y Setters
	public String getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(String idEvento) {
		this.idEvento = idEvento;
	}

	public String getNombreEv() {
		return nombreEv;
	}

	public void setNombreEv(String nombreEv) {
		this.nombreEv = nombreEv;
	}

	public String getFechaEv() {
		return fechaEv;
	}

	public void setFechaEv(String fechaEv) {
		this.fechaEv = fechaEv;
	}

	public String getTipoEv() {
		return tipoEv;
	}

	public void setTipoEv(String tipoEv) {
		this.tipoEv = tipoEv;
	}

	public double getPrecioEv() {
		return precioEv;
	}

	public void setPrecioEv(double precioEv) throws Exception {
		if (precioEv < 0) {
			throw new Exception("El precio no puede ser negativo");
		}
		this.precioEv = precioEv;
	}

	public int getCantTickets() {
		return cantTickets;
	}

	public void setCantTickets(int cantTickets) throws Exception {
		if (cantTickets < ticketsVendidos) {
			throw new Exception("La nueva capacidad no puede ser menor a los tickets ya vendidos");
		}
		this.cantTickets = cantTickets;
	}

	public boolean isEventoConSillas() {
		return eventoConSillas;
	}

	public void setEventoConSillas(boolean eventoConSillas) {
		this.eventoConSillas = eventoConSillas;
	}

	public String getImagenEvento() {
		return imagenEvento;
	}

	public void setImagenEvento(String imagenEvento) {
		this.imagenEvento = imagenEvento;
	}
	
	public int getTicketsVendidos() {
		return ticketsVendidos;
	}

	public void setTicketsVendidos(int ticketsVendidos) throws Exception {
		if (ticketsVendidos < 0) {
			throw new Exception("Los tickets vendidos no pueden ser negativos");
		}
		if (ticketsVendidos > cantTickets) {
			throw new Exception("Los tickets vendidos no pueden exceder la capacidad total");
		}
		this.ticketsVendidos = ticketsVendidos;
	}
	
	@Override
	public String toString() {
		return String.format("Evento [%s] %s - %s | Precio: $%.2f | Disponibles: %d/%d",
				idEvento, nombreEv, fechaEv, precioEv, getTicketsDisponibles(), cantTickets);
	}
}
