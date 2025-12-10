package modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Vendedor extends Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> idEventos;

	public Vendedor(String nombreUsuario, String contraseña) {
		super(nombreUsuario, contraseña);
		this.idEventos = new ArrayList<String>();
	}

	public void agregarEvento(String nuevoEvento) {
		idEventos.add(nuevoEvento);
	}

	public ArrayList<String> getIdEventos() {
		return idEventos;
	}

	public void setIdEventos(ArrayList<String> idEventos) {
		this.idEventos = idEventos;
	}

}
