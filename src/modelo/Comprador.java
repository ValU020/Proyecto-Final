package modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Comprador extends Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> idTickets;

	public Comprador(String nombreUsuario, String contraseña) {
		super(nombreUsuario, contraseña);
		this.idTickets = new ArrayList<>();

	}

	public void agregarTicket(String idTicket) {
		idTickets.add(idTicket);
	}

	public ArrayList<String> getIdTickets() {
		return idTickets;
	}

	public void setIdTickets(ArrayList<String> idTickets) {
		this.idTickets = idTickets;
	}

}
