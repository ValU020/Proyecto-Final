package modelo;

import java.io.Serializable;

public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String idTicket;
    private String idEvento;
    private String nombreComprador;
    private double precioPagado;
    private String fechaCompra;
    private int numeroSilla; // -1 si no tiene asiento asignado
    
    public Ticket(String idTicket, String idEvento, String nombreComprador, 
                  double precioPagado, String fechaCompra, int numeroSilla) {
        this.idTicket = idTicket;
        this.idEvento = idEvento;
        this.nombreComprador = nombreComprador;
        this.precioPagado = precioPagado;
        this.fechaCompra = fechaCompra;
        this.numeroSilla = numeroSilla;
    }

    // Getters y Setters
    public String getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(String idTicket) {
        this.idTicket = idTicket;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }

    public double getPrecioPagado() {
        return precioPagado;
    }

    public void setPrecioPagado(double precioPagado) {
        this.precioPagado = precioPagado;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public int getNumeroSilla() {
        return numeroSilla;
    }

    public void setNumeroSilla(int numeroSilla) {
        this.numeroSilla = numeroSilla;
    }

    @Override
    public String toString() {
        return "Ticket ID: " + idTicket + " - Evento: " + idEvento + 
               " - Comprador: " + nombreComprador + " - Precio: $" + precioPagado +
               (numeroSilla != -1 ? " - Silla: " + numeroSilla : " - Sin asiento asignado");
    }
}
