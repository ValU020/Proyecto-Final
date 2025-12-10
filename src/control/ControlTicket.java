package control;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import modelo.Ticket;

public class ControlTicket {
    
    private ArrayList<Ticket> tickets;
    private final String RUTA_ARCHIVO = "data/tickets.dat";
    private int contadorTickets;
    
    public ControlTicket() {
        tickets = new ArrayList<>();
        contadorTickets = 1;
        
        // Crear carpeta data si no existe
        File carpeta = new File("data");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
        
        cargarTickets();
        
        // Actualizar contador según tickets existentes
        if (!tickets.isEmpty()) {
            contadorTickets = tickets.size() + 1;
        }
    }
    
    // GUARDAR TICKETS
    public void guardarTickets() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_ARCHIVO))) {
            oos.writeObject(tickets);
        } catch (Exception e) {
            System.err.println("Error al guardar tickets: " + e.getMessage());
        }
    }
    
    // CARGAR TICKETS
    @SuppressWarnings("unchecked")
    public void cargarTickets() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_ARCHIVO))) {
            tickets = (ArrayList<Ticket>) ois.readObject();
        } catch (FileNotFoundException e) {
            tickets = new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Error al cargar tickets: " + e.getMessage());
            tickets = new ArrayList<>();
        }
    }
    
    // CREAR NUEVO TICKET
    public String crearTicket(String idEvento, String nombreComprador, double precio, int numeroSilla) 
            throws Exception {
        
        if (idEvento == null || idEvento.trim().isEmpty()) {
            throw new Exception("El ID del evento no puede estar vacío");
        }
        
        if (nombreComprador == null || nombreComprador.trim().isEmpty()) {
            throw new Exception("El nombre del comprador no puede estar vacío");
        }
        
        if (precio < 0) {
            throw new Exception("El precio no puede ser negativo");
        }
        
        // Generar ID único
        String idTicket = "TK" + String.format("%04d", contadorTickets);
        contadorTickets++;
        
        // Obtener fecha actual
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaCompra = ahora.format(formato);
        
        // Crear ticket
        Ticket nuevoTicket = new Ticket(idTicket, idEvento, nombreComprador, precio, fechaCompra, numeroSilla);
        tickets.add(nuevoTicket);
        guardarTickets();
        
        return idTicket;
    }
    
    // OBTENER TICKETS DE UN USUARIO
    public ArrayList<Ticket> obtenerTicketsUsuario(ArrayList<String> idsTickets) {
        ArrayList<Ticket> ticketsUsuario = new ArrayList<>();
        
        for (String idTicket : idsTickets) {
            for (Ticket ticket : tickets) {
                if (ticket.getIdTicket().equals(idTicket)) {
                    ticketsUsuario.add(ticket);
                    break;
                }
            }
        }
        
        return ticketsUsuario;
    }
    
    // BUSCAR TICKET POR ID
    public Ticket buscarTicketPorId(String idTicket) throws Exception {
        for (Ticket ticket : tickets) {
            if (ticket.getIdTicket().equals(idTicket)) {
                return ticket;
            }
        }
        throw new Exception("No se encontró el ticket con ID: " + idTicket);
    }
    
    // ANULAR TICKET (para cancelaciones)
    public void anularTicket(String idTicket) throws Exception {
        Ticket ticket = buscarTicketPorId(idTicket);
        tickets.remove(ticket);
        guardarTickets();
    }
    
    // OBTENER TODOS LOS TICKETS
    public ArrayList<Ticket> getTickets() {
        return tickets;
    }
    
    // OBTENER TICKETS POR EVENTO
    public ArrayList<Ticket> obtenerTicketsPorEvento(String idEvento) {
        ArrayList<Ticket> ticketsEvento = new ArrayList<>();
        
        for (Ticket ticket : tickets) {
            if (ticket.getIdEvento().equals(idEvento)) {
                ticketsEvento.add(ticket);
            }
        }
        
        return ticketsEvento;
    }
}
