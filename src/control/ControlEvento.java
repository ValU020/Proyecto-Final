package control;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import modelo.Evento;

public class ControlEvento {

	private ArrayList<Evento> eventos;
	private final String RUTA_ARCHIVO = "data/eventos.dat";
	private int contadorEventos;

	public ControlEvento() {
		eventos = new ArrayList<>();
		contadorEventos = 1;
		
		// Crear carpeta data si no existe
		File carpeta = new File("data");
		if (!carpeta.exists()) {
			carpeta.mkdirs();
		}
		
		cargarEventos();
		
		// Si no hay eventos, crear los de ejemplo
		if (eventos.isEmpty()) {
			eventos.add(new Evento("EV001", "Concierto Sinfónico Anime", "2025-12-24", "Música", 
					1500000, 350, true, "imagenes/anime.jpg"));

			eventos.add(new Evento("EV002", "Feria de Comida Internacional", "2026-01-15", "Gastronomía", 
					350000, 500, false, "imagenes/comida.jpg"));

			eventos.add(new Evento("EV003", "Exposición de Realidad Virtual", "2026-02-10", "Tecnología", 
					800000, 200, true, "imagenes/vr.jpg"));
			
			contadorEventos = 4;
			guardarEventos();
		} else {
			// Actualizar contador según eventos existentes
			contadorEventos = eventos.size() + 1;
		}
	}
	
	// GUARDAR EVENTOS
	public void guardarEventos() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_ARCHIVO))) {
			oos.writeObject(eventos);
		} catch (Exception e) {
			System.err.println("Error al guardar eventos: " + e.getMessage());
		}
	}
	
	// CARGAR EVENTOS
	@SuppressWarnings("unchecked")
	public void cargarEventos() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_ARCHIVO))) {
			eventos = (ArrayList<Evento>) ois.readObject();
		} catch (FileNotFoundException e) {
			eventos = new ArrayList<>();
		} catch (Exception e) {
			System.err.println("Error al cargar eventos: " + e.getMessage());
			eventos = new ArrayList<>();
		}
	}

	public Map<String, Object> mostrarEvento(int indice) {
		if (indice < 0 || indice >= eventos.size()) {
			throw new IndexOutOfBoundsException("Índice de evento inválido: " + indice);
		}
		
		Evento e = eventos.get(indice);
		Map<String, Object> datos = new HashMap<>();
		datos.put("id", e.getIdEvento());
		datos.put("nombre", e.getNombreEv());
		datos.put("fecha", e.getFechaEv());
		datos.put("tipo", e.getTipoEv());
		datos.put("precio", e.getPrecioEv());
		datos.put("cantTickets", e.getCantTickets());
		datos.put("ticketsDisponibles", e.getTicketsDisponibles());
		datos.put("conSillas", e.isEventoConSillas());
		datos.put("imagen", e.getImagenEvento());

		return datos;
	}
	
	// BUSCAR EVENTO POR ID
	public Evento buscarEventoPorId(String idEvento) throws Exception {
		for (Evento evento : eventos) {
			if (evento.getIdEvento().equals(idEvento)) {
				return evento;
			}
		}
		throw new Exception("No se encontró el evento con ID: " + idEvento);
	}

	// AÑADIR EVENTO
	public String añadirEvento(String nombre, String fecha, String tipoEvento, double precio, 
			int tickets, boolean conSillas, String imagen) throws Exception {
		
		if (nombre == null || nombre.trim().isEmpty()) {
			throw new Exception("El nombre del evento no puede estar vacío");
		}
		
		if (fecha == null || fecha.trim().isEmpty()) {
			throw new Exception("La fecha del evento no puede estar vacía");
		}
		
		if (precio < 0) {
			throw new Exception("El precio no puede ser negativo");
		}
		
		if (tickets <= 0) {
			throw new Exception("La cantidad de tickets debe ser mayor a 0");
		}
		
		// Generar ID único
		String idEvento = "EV" + String.format("%03d", contadorEventos);
		contadorEventos++;
		
		Evento nuevoEvento = new Evento(idEvento, nombre, fecha, tipoEvento, precio, tickets, conSillas, imagen);
		eventos.add(nuevoEvento);
		guardarEventos();
		
		return idEvento;
	}
	
	// ELIMINAR EVENTO
	public void eliminarEvento(String idEvento) throws Exception {
		Evento evento = buscarEventoPorId(idEvento);
		
		if (evento.getTicketsVendidos() > 0) {
			throw new Exception("No se puede eliminar un evento con tickets vendidos");
		}
		
		eventos.remove(evento);
		guardarEventos();
	}
	
	// MODIFICAR EVENTO
	public void modificarEvento(String idEvento, String nombre, String fecha, String tipo, 
			double precio, int cantTickets, boolean conSillas, String imagen) throws Exception {
		
		Evento evento = buscarEventoPorId(idEvento);
		
		if (nombre != null && !nombre.trim().isEmpty()) {
			evento.setNombreEv(nombre);
		}
		
		if (fecha != null && !fecha.trim().isEmpty()) {
			evento.setFechaEv(fecha);
		}
		
		if (tipo != null && !tipo.trim().isEmpty()) {
			evento.setTipoEv(tipo);
		}
		
		if (precio >= 0) {
			evento.setPrecioEv(precio);
		}
		
		if (cantTickets > 0) {
			evento.setCantTickets(cantTickets);
		}
		
		evento.setEventoConSillas(conSillas);
		
		if (imagen != null && !imagen.trim().isEmpty()) {
			evento.setImagenEvento(imagen);
		}
		
		guardarEventos();
	}
	
	// VENDER TICKETS
	public void venderTickets(String idEvento, int cantidad) throws Exception {
		Evento evento = buscarEventoPorId(idEvento);
		evento.venderTickets(cantidad);
		guardarEventos();
	}
	
	// OBTENER TODOS LOS EVENTOS
	public ArrayList<Evento> getEventos() {
		return eventos;
	}
	
	// OBTENER CANTIDAD DE EVENTOS
	public int getCantidadEventos() {
		return eventos.size();
	}
	
	// OBTENER REPORTE DE EVENTO
	public Map<String, Object> obtenerReporteEvento(String idEvento) throws Exception {
		Evento evento = buscarEventoPorId(idEvento);
		
		Map<String, Object> reporte = new HashMap<>();
		reporte.put("id", evento.getIdEvento());
		reporte.put("nombre", evento.getNombreEv());
		reporte.put("fecha", evento.getFechaEv());
		reporte.put("tipo", evento.getTipoEv());
		reporte.put("capacidadTotal", evento.getCantTickets());
		reporte.put("ticketsVendidos", evento.getTicketsVendidos());
		reporte.put("ticketsDisponibles", evento.getTicketsDisponibles());
		reporte.put("precioBase", evento.getPrecioEv());
		reporte.put("ingresosPotenciales", evento.getPrecioEv() * evento.getCantTickets());
		reporte.put("ingresosActuales", evento.getPrecioEv() * evento.getTicketsVendidos());
		reporte.put("porcentajeOcupacion", 
				(evento.getTicketsVendidos() * 100.0) / evento.getCantTickets());
		
		return reporte;
	}
}