package control;

import java.io.*;
import java.util.ArrayList;

import modelo.Comprador;
import modelo.Usuario;
import modelo.Vendedor;

public class ControlUsuario {

	private ArrayList<Usuario> usuarios;

	private final String RUTA_ARCHIVO = "data/usuarios.dat";

	public ControlUsuario() {
		usuarios = new ArrayList<>();

		// 1. Crear carpeta data si no existe
		File carpeta = new File("data");
		if (!carpeta.exists()) {
			carpeta.mkdirs();
		}

		// 2. Cargar usuarios existentes (si los hay)
		cargarUsuarios();

		// 3. Si está vacío, crear datos por defecto
		if (usuarios.isEmpty()) {
			Vendedor vendedor = new Vendedor("vendedor1", "1234");
			vendedor.agregarEvento("EV001");

			Comprador comprador = new Comprador("alex", "abcd");
			comprador.agregarTicket("TK100");

			usuarios.add(vendedor);
			usuarios.add(comprador);

			guardarUsuarios();
		}
	}

	// GUARDAR LISTA COMPLETA DE USUARIOS
	public void guardarUsuarios() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_ARCHIVO))) {

			oos.writeObject(usuarios);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// CARGAR LISTA COMPLETA DE USUARIOS
	@SuppressWarnings("unchecked")
	public void cargarUsuarios() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_ARCHIVO))) {

			usuarios = (ArrayList<Usuario>) ois.readObject();

		} catch (FileNotFoundException e) {
			// Si no existe el archivo todavía, iniciamos lista vacía
			usuarios = new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
			usuarios = new ArrayList<>();
		}
	}

	// GUARDAR UN NUEVO ID SEA DE EVENTO O TICKET DEPENDENDIENDO DE LA INSTANCIA DEL
	// OBJETO
	public void guardarId(String nombre, String contraseña, String id) {
		for (Usuario u : usuarios) {
			if (u.getNombreUsuario().equals(nombre) && u.getContraseña().equals(contraseña)) {
				if (u instanceof Comprador) {
					((Comprador) u).agregarTicket(id);
				} else if (u instanceof Vendedor) {
					((Vendedor) u).agregarEvento(id);
				}
				// Muy importante
				guardarUsuarios();
				return; // ya no hace falta seguir buscando
			}
		}
	}

	// REGISTRAR UN USUARIO Y GUARDAR AUTOMÁTICAMENTE
	public void registrarUsuario(String nombre, String contraseña, boolean esComprador) {

		Usuario user;

		if (esComprador) {
			Comprador comp = new Comprador(nombre, contraseña);
			user = comp;
		} else {
			Vendedor vend = new Vendedor(nombre, contraseña);
			user = vend;
		}

		usuarios.add(user);
		guardarUsuarios();
	}

	public boolean validarInicio(String nombre, String contraseña) {
		for (Usuario u : usuarios) {
			if (u.getNombreUsuario().equals(nombre) && u.getContraseña().equals(contraseña)) {
				return true;
			}
		}
		return false; // no encontrado
	}

	public boolean verificarInstancia(String nombre) {
		for (Usuario user : usuarios) {
			if (user.getNombreUsuario().equals(nombre)) {
				return user instanceof Comprador;
			}
		}
		return false;
	}

	public boolean validarRegistro(String nombre) {
		for (Usuario user : usuarios) {
			if (user.getNombreUsuario().equals(nombre)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}
}
