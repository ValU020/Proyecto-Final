package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;

import GUI.*;
import modelo.Comprador;
import modelo.Evento;
import modelo.Ticket;
import modelo.Usuario;
import modelo.Vendedor;

public class ControlGUI implements ActionListener, ChangeListener {

	private ControlGeneral controlGen;
	
	// Ventanas existentes
	private VentanaCompra ventanaComp;
	private VentanaInicio ventanaInc;
	private VentanaPrincipal ventanaPrin;
	private VentanaRegistro ventanaReg;
	private VentanaSeleccionEvento ventanaSel;
	private VentanaConfirmacion ventanaConf;
	
	// NUEVAS ventanas para vendedores
	private VentanaGestionEventos ventanaGestion;
	private VentanaEstadisticasEvento ventanaEstadisticas;
	private VentanaCrearEvento ventanaCrear;
	
	private String usuarioActual;
	private String contraseñaActual;
	private int eventoSeleccionado = -1;
	private double precioUnitario = 0.0;
	private boolean esVendedor = false;

	public ControlGUI() {
		JOptionPane.showMessageDialog(null, "Bienvenido, debes iniciar sesión antes de continuar");
		
		// Inicializar control y ventanas
		controlGen = new ControlGeneral();
		ventanaComp = new VentanaCompra();
		ventanaInc = new VentanaInicio();
		ventanaPrin = new VentanaPrincipal();
		ventanaReg = new VentanaRegistro();
		ventanaSel = new VentanaSeleccionEvento();
		ventanaConf = new VentanaConfirmacion();
		
		// NUEVAS ventanas
		ventanaGestion = new VentanaGestionEventos();
		ventanaEstadisticas = new VentanaEstadisticasEvento();
		ventanaCrear = new VentanaCrearEvento();

		// Listeners ventana de inicio
		ventanaInc.getBtnSalir().addActionListener(this);
		ventanaInc.getBtnIniciarSesion().addActionListener(this);
		ventanaInc.getBtnRegistrarse().addActionListener(this);

		// Listeners ventana principal
		ventanaPrin.getBtnSalir().addActionListener(this);
		ventanaPrin.getBtnIrTienda().addActionListener(this);

		// Listeners ventana de registro
		ventanaReg.getBtnRegistrarse().addActionListener(this);
		ventanaReg.getBtnRegresar().addActionListener(this);
		
		// Listeners ventana selección de eventos
		ventanaSel.getBtnRegresar().addActionListener(this);
		// Los botones de eventos se agregan dinámicamente en cargarEventosEnVentana()
		
		// Listeners ventana de compra
		ventanaComp.getBtnRegresar().addActionListener(this);
		ventanaComp.getBtnConfirmarCompra().addActionListener(this);
		ventanaComp.getSpinnerCantidad().addChangeListener(this);
		ventanaComp.getComboCategoria().addActionListener(this);
		
		// Listeners ventana confirmación
		ventanaConf.getBtnVolver().addActionListener(this);
		ventanaConf.getBtnVerMisTickets().addActionListener(this);
		
		// Listeners para gestión de eventos
		ventanaGestion.getBtnRegresar().addActionListener(this);
		ventanaGestion.getBtnCrearEvento().addActionListener(this);
		ventanaGestion.getBtnVerEstadisticas().addActionListener(this);
		ventanaGestion.getBtnEditarEvento().addActionListener(this);
		ventanaGestion.getBtnEliminarEvento().addActionListener(this);
		
		// Listeners ventana estadísticas
		ventanaEstadisticas.getBtnCerrar().addActionListener(this);
		
		// Listeners ventana crear evento
		ventanaCrear.getBtnCancelar().addActionListener(this);
		ventanaCrear.getBtnCrear().addActionListener(this);
		// Botón de seleccionar imagen removido
		
		// Cargar eventos en la ventana de selección
		cargarEventosEnVentana();
		
		ventanaInc.setVisible(true);
	}
	
	private void cargarEventosEnVentana() {
		try {
			ventanaSel.limpiarEventos();
			
			ArrayList<Evento> eventos = controlGen.getControlEvento().getEventos();
			int cantidadEventos = eventos.size();
			
			if (cantidadEventos == 0) {
				JOptionPane.showMessageDialog(null, 
					"No hay eventos disponibles en este momento.\n" +
					"¡Vuelve pronto para ver nuevos eventos!", 
					"Sin eventos", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			ventanaSel.configurarLayout(cantidadEventos);
			
			// Agregar todos los eventos disponibles
			for (Evento evento : eventos) {
				ventanaSel.agregarEvento(
					evento.getIdEvento(),
					evento.getNombreEv(),
					evento.getFechaEv(),
					evento.getTipoEv(),
					evento.getPrecioEv(),
					evento.getImagenEvento()
				);
			}
			
			// Configurar listeners para los botones de compra
			ArrayList<JButton> botones = ventanaSel.getBotonesEventos();
			for (JButton boton : botones) {
				boton.addActionListener(this);
			}
			
			ventanaSel.refrescarVista();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, 
				"Error al cargar eventos: " + e.getMessage(), 
				"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void cargarTicketsUsuario() {
		try {
			ventanaPrin.limpiarTablaTickets();
			
			ArrayList<Usuario> usuarios = controlGen.getControlUsuario().getUsuarios();
			for (Usuario user : usuarios) {
				if (user.getNombreUsuario().equals(usuarioActual) && user instanceof Comprador) {
					Comprador comprador = (Comprador) user;
					ArrayList<String> idsTickets = comprador.getIdTickets();
					
					ArrayList<Ticket> tickets = controlGen.getControlTicket().obtenerTicketsUsuario(idsTickets);
					
					for (Ticket ticket : tickets) {
						String nombreEvento = "Evento desconocido";
						try {
							Evento evento = controlGen.getControlEvento().buscarEventoPorId(ticket.getIdEvento());
							nombreEvento = evento.getNombreEv();
						} catch (Exception e) {
							// Mantener el nombre por defecto
						}
						
						ventanaPrin.agregarTicketATabla(
							ticket.getIdTicket(),
							nombreEvento,
							ticket.getFechaCompra(),
							ticket.getPrecioPagado(),
							ticket.getNumeroSilla()
						);
					}
					break;
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, 
				"Error al cargar tickets: " + e.getMessage(), 
				"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// NUEVO: Cargar eventos del vendedor
	private void cargarEventosVendedor() {
		try {
			ventanaGestion.limpiarTabla();
			
			ArrayList<Usuario> usuarios = controlGen.getControlUsuario().getUsuarios();
			for (Usuario user : usuarios) {
				if (user.getNombreUsuario().equals(usuarioActual) && user instanceof Vendedor) {
					Vendedor vendedor = (Vendedor) user;
					ArrayList<String> idsEventos = vendedor.getIdEventos();
					
					// Cargar cada evento del vendedor
					for (String idEvento : idsEventos) {
						try {
							Evento evento = controlGen.getControlEvento().buscarEventoPorId(idEvento);
							
							ventanaGestion.agregarEventoATabla(
								evento.getIdEvento(),
								evento.getNombreEv(),
								evento.getFechaEv(),
								evento.getTipoEv(),
								evento.getPrecioEv(),
								evento.getCantTickets(),
								evento.getTicketsVendidos(),
								evento.getTicketsDisponibles()
							);
						} catch (Exception e) {
							System.err.println("Error al cargar evento " + idEvento + ": " + e.getMessage());
						}
					}
					break;
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, 
				"Error al cargar eventos del vendedor: " + e.getMessage(), 
				"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// ==================== VENTANA INICIO ====================
		if (e.getSource() == ventanaInc.getBtnIniciarSesion()) {
			String nombre = ventanaInc.getTextoUsuario();
			String contraseña = ventanaInc.getContraseña();
			
			if (controlGen.buscarUsuario(nombre, contraseña)) {
				usuarioActual = nombre;
				contraseñaActual = contraseña;
				ventanaInc.setVisible(false);
				
				// Verificar si es comprador o vendedor
				if (controlGen.definirInstancia(nombre)) {
					// Es comprador
					esVendedor = false;
					cargarTicketsUsuario();
					ventanaPrin.setVisible(true);
				} else {
					// Es vendedor - mostrar ventana de gestión de eventos
					esVendedor = true;
					ventanaPrin.mostrarVistaVendedor();
					ventanaPrin.setVisible(true);
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Error, asegurese de ingresar correctamente el usuario y contraseña", 
						"Error al iniciar", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		if (e.getSource() == ventanaInc.getBtnRegistrarse()) {
			ventanaInc.setVisible(false);
			ventanaReg.setVisible(true);
		}
		
		if (e.getSource() == ventanaInc.getBtnSalir()) {
			JOptionPane.showMessageDialog(null, "Gracias por haber usado el programa, que tenga buen día ;)");
			System.exit(0);
		}
		
		// ==================== VENTANA REGISTRO ====================
		if (e.getSource() == ventanaReg.getBtnRegistrarse()) {
			String nombre = ventanaReg.getNombreEspacio();
			String contraseña = ventanaReg.getContraseñaEspacio();
			boolean esComprador = ventanaReg.getRdbtnParaCompras().isSelected();
			boolean esVendedor = ventanaReg.getRdbtnParaVentas().isSelected();
			
			if (nombre.isEmpty() || contraseña.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Debe llenar todos los campos antes de registrarse.",
						"Campos vacíos", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			if (!esComprador && !esVendedor) {
				JOptionPane.showMessageDialog(null, "Debe seleccionar si es comprador o vendedor.",
						"Selección requerida", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			controlGen.guardarNuevoUsuario(nombre, contraseña, esComprador);
			JOptionPane.showMessageDialog(null, "Usuario registrado con éxito ;)", 
					"Registro completo", JOptionPane.INFORMATION_MESSAGE);
			ventanaReg.setVisible(false);
			ventanaInc.setVisible(true);
		}
		
		if (e.getSource() == ventanaReg.getBtnRegresar()) {
			ventanaReg.setVisible(false);
			ventanaInc.setVisible(true);
		}
		
		// ==================== VENTANA PRINCIPAL ====================
		if (e.getSource() == ventanaPrin.getBtnSalir()) {
			ventanaPrin.setVisible(false);
			ventanaInc.setVisible(true);
			usuarioActual = null;
			contraseñaActual = null;
			esVendedor = false;
		}
		
		if (e.getSource() == ventanaPrin.getBtnIrTienda()) {
			ventanaPrin.setVisible(false);
			
			if (esVendedor) {
				// Mostrar ventana de gestión de eventos
				cargarEventosVendedor();
				ventanaGestion.setVisible(true);
			} else {
				// Mostrar tienda para comprar
				ventanaSel.setVisible(true);
			}
		}
		
		// ==================== VENTANA SELECCIÓN EVENTOS ====================
		// Manejar clics en botones de eventos dinámicos
		ArrayList<JButton> botonesEventos = ventanaSel.getBotonesEventos();
		for (JButton boton : botonesEventos) {
			if (e.getSource() == boton) {
				String idEvento = ventanaSel.getIdEventoPorBoton(boton);
				if (idEvento != null) {
					try {
						Evento evento = controlGen.getControlEvento().buscarEventoPorId(idEvento);
						int indice = controlGen.getControlEvento().getEventos().indexOf(evento);
						eventoSeleccionado = indice;
						abrirVentanaCompra(evento);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null,
							"Error al abrir evento: " + ex.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				return;
			}
		}
		
		if (e.getSource() == ventanaSel.getBtnRegresar()) {
			ventanaSel.setVisible(false);
			ventanaPrin.setVisible(true);
		}
		
		// ==================== VENTANA COMPRA ====================
		if (e.getSource() == ventanaComp.getBtnRegresar()) {
			ventanaComp.setVisible(false);
			ventanaSel.setVisible(true);
		}
		
		if (e.getSource() == ventanaComp.getBtnConfirmarCompra()) {
			procesarCompra();
		}
		
		if (e.getSource() == ventanaComp.getComboCategoria()) {
			actualizarPrecioTotal();
		}
		
		// ==================== VENTANA CONFIRMACIÓN ====================
		if (e.getSource() == ventanaConf.getBtnVolver()) {
			ventanaConf.setVisible(false);
			ventanaSel.setVisible(true);
		}
		
		if (e.getSource() == ventanaConf.getBtnVerMisTickets()) {
			ventanaConf.setVisible(false);
			cargarTicketsUsuario();
			ventanaPrin.mostrarPestañaTickets();
			ventanaPrin.setVisible(true);
		}
		
		// ==================== VENTANA GESTIÓN EVENTOS (VENDEDOR) ====================
		if (e.getSource() == ventanaGestion.getBtnRegresar()) {
			ventanaGestion.setVisible(false);
			ventanaPrin.setVisible(true);
		}
		
		if (e.getSource() == ventanaGestion.getBtnCrearEvento()) {
			ventanaGestion.setVisible(false);
			ventanaCrear.limpiarCampos();
			ventanaCrear.setVisible(true);
		}
		
		if (e.getSource() == ventanaGestion.getBtnVerEstadisticas()) {
			String idEvento = ventanaGestion.getEventoSeleccionado();
			
			if (idEvento == null) {
				JOptionPane.showMessageDialog(null,
					"Debe seleccionar un evento de la tabla",
					"Ningún evento seleccionado", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			mostrarEstadisticasEvento(idEvento);
		}
		
		if (e.getSource() == ventanaGestion.getBtnEditarEvento()) {
			String idEvento = ventanaGestion.getEventoSeleccionado();
			
			if (idEvento == null) {
				JOptionPane.showMessageDialog(null,
					"Debe seleccionar un evento de la tabla",
					"Ningún evento seleccionado", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			JOptionPane.showMessageDialog(null,
				"Función de edición en desarrollo.\nPróximamente podrás editar eventos.",
				"En desarrollo", JOptionPane.INFORMATION_MESSAGE);
		}
		
		if (e.getSource() == ventanaGestion.getBtnEliminarEvento()) {
			String idEvento = ventanaGestion.getEventoSeleccionado();
			
			if (idEvento == null) {
				JOptionPane.showMessageDialog(null,
					"Debe seleccionar un evento de la tabla",
					"Ningún evento seleccionado", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			int opcion = JOptionPane.showConfirmDialog(null,
				"¿Está seguro de que desea eliminar este evento?\n" +
				"Esta acción no se puede deshacer.",
				"Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			
			if (opcion == JOptionPane.YES_OPTION) {
				try {
					controlGen.getControlEvento().eliminarEvento(idEvento);
					
					// Eliminar el ID del vendedor
					ArrayList<Usuario> usuarios = controlGen.getControlUsuario().getUsuarios();
					for (Usuario user : usuarios) {
						if (user.getNombreUsuario().equals(usuarioActual) && user instanceof Vendedor) {
							Vendedor vendedor = (Vendedor) user;
							vendedor.getIdEventos().remove(idEvento);
							controlGen.getControlUsuario().guardarUsuarios();
							break;
						}
					}
					
					JOptionPane.showMessageDialog(null,
						"Evento eliminado exitosamente",
						"Éxito", JOptionPane.INFORMATION_MESSAGE);
					
					cargarEventosVendedor();
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,
						"Error al eliminar evento: " + ex.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		// ==================== VENTANA ESTADÍSTICAS ====================
		if (e.getSource() == ventanaEstadisticas.getBtnCerrar()) {
			ventanaEstadisticas.setVisible(false);
			ventanaGestion.setVisible(true);
		}
		
		// ==================== VENTANA CREAR EVENTO ====================
		if (e.getSource() == ventanaCrear.getBtnCancelar()) {
			ventanaCrear.setVisible(false);
			ventanaGestion.setVisible(true);
		}
		
		if (e.getSource() == ventanaCrear.getBtnCrear()) {
			crearNuevoEvento();
		}
		
		// Listener de selección de imagen removido
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == ventanaComp.getSpinnerCantidad()) {
			actualizarPrecioTotal();
		}
	}
	
	// NUEVO: Crear nuevo evento
	private void crearNuevoEvento() {
	    try {
	        String nombre = ventanaCrear.getNombre();
	        String fecha = ventanaCrear.getFecha();
	        String tipo = ventanaCrear.getTipo();
	        String precioTexto = ventanaCrear.getPrecioTexto();
	        int capacidad = ventanaCrear.getCapacidad();
	        boolean conSillas = ventanaCrear.getConSillas();
	        String imagen = ventanaCrear.getImagen(); 
	        
	        // Validaciones
	        if (nombre.isEmpty()) {
	            JOptionPane.showMessageDialog(null,
	                "El nombre del evento no puede estar vacío",
	                "Campo requerido", JOptionPane.WARNING_MESSAGE);
	            return;
	        }
	        
	        if (fecha.isEmpty()) {
	            JOptionPane.showMessageDialog(null,
	                "La fecha no puede estar vacía",
	                "Campo requerido", JOptionPane.WARNING_MESSAGE);
	            return;
	        }
	        
	        if (precioTexto.isEmpty()) {
	            JOptionPane.showMessageDialog(null,
	                "El precio no puede estar vacío",
	                "Campo requerido", JOptionPane.WARNING_MESSAGE);
	            return;
	        }
	        
	        // Convertir precio
	        double precio;
	        try {
	            precio = Double.parseDouble(precioTexto);
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(null,
	                "El precio debe ser un número válido",
	                "Formato inválido", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        
	        if (precio <= 0) {
	            JOptionPane.showMessageDialog(null,
	                "El precio debe ser mayor a 0",
	                "Precio inválido", JOptionPane.WARNING_MESSAGE);
	            return;
	        }
	        
	        // Crear evento con la imagen (será default.png si no se especificó otra)
	        String idEvento = controlGen.getControlEvento().añadirEvento(
	            nombre, fecha, tipo, precio, capacidad, conSillas, imagen);
	        
	        // Agregar el ID al vendedor
	        controlGen.añadirId(usuarioActual, contraseñaActual, idEvento);
	        
	        JOptionPane.showMessageDialog(null,
	            "¡Evento creado exitosamente!\n\n" +
	            "ID del evento: " + idEvento + "\n" +
	            "Nombre: " + nombre + "\n" +
	            "Fecha: " + fecha,
	            "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        
	        ventanaCrear.setVisible(false);
	        cargarEventosVendedor();
	        cargarEventosEnVentana(); // Actualizar también la tienda
	        ventanaGestion.setVisible(true);
	        
	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(null,
	            "Error al crear evento: " + ex.getMessage(),
	            "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	// NUEVO: Mostrar estadísticas de un evento
	private void mostrarEstadisticasEvento(String idEvento) {
		try {
			Map<String, Object> reporte = controlGen.getControlEvento().obtenerReporteEvento(idEvento);
			
			ventanaEstadisticas.limpiarTablaVentas();
			
			// Establecer información general
			ventanaEstadisticas.setNombreEvento((String) reporte.get("nombre"));
			ventanaEstadisticas.setFechaEvento((String) reporte.get("fecha"));
			ventanaEstadisticas.setCapacidadTotal((int) reporte.get("capacidadTotal"));
			ventanaEstadisticas.setTicketsVendidos((int) reporte.get("ticketsVendidos"));
			ventanaEstadisticas.setTicketsDisponibles((int) reporte.get("ticketsDisponibles"));
			ventanaEstadisticas.setPorcentajeOcupacion((double) reporte.get("porcentajeOcupacion"));
			ventanaEstadisticas.setIngresosActuales((double) reporte.get("ingresosActuales"));
			ventanaEstadisticas.setIngresosPotenciales((double) reporte.get("ingresosPotenciales"));
			
			// Crear resumen
			double precioBase = (double) reporte.get("precioBase");
			int vendidos = (int) reporte.get("ticketsVendidos");
			int disponibles = (int) reporte.get("ticketsDisponibles");
			double porcentaje = (double) reporte.get("porcentajeOcupacion");
			
			String resumen = String.format(
				"══════════════════════════════════\n" +
				"ANÁLISIS DEL EVENTO\n" +
				"══════════════════════════════════\n\n" +
				"Estado de ventas:\n" +
				"• Vendidos: %d entradas (%.1f%%)\n" +
				"• Disponibles: %d entradas\n" +
				"• Precio base: $%,.0f COP\n\n" +
				"Proyección:\n" +
				"• Si se venden todas las entradas,\n" +
				"  los ingresos totales serán de:\n" +
				"  $%,.0f COP\n\n" +
				"══════════════════════════════════",
				vendidos, porcentaje, disponibles, precioBase,
				(double) reporte.get("ingresosPotenciales")
			);
			
			ventanaEstadisticas.setResumen(resumen);
			
			// Agregar datos de ventas por categoría (simulado)
			ventanaEstadisticas.agregarFilaVentas("General", 
				(int)(vendidos * 0.6), 
				precioBase * (vendidos * 0.6));
			ventanaEstadisticas.agregarFilaVentas("VIP", 
				(int)(vendidos * 0.3), 
				(precioBase * 1.5) * (vendidos * 0.3));
			ventanaEstadisticas.agregarFilaVentas("Platino", 
				(int)(vendidos * 0.1), 
				(precioBase * 2.0) * (vendidos * 0.1));
			
			ventanaGestion.setVisible(false);
			ventanaEstadisticas.setVisible(true);
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,
				"Error al mostrar estadísticas: " + ex.getMessage(),
				"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void abrirVentanaCompra(Evento evento) {
		try {
			ventanaComp.setLblNombreEvento(evento.getNombreEv());
			ventanaComp.setLblFechaEvento(evento.getFechaEv());
			ventanaComp.setLblTipoEvento(evento.getTipoEv());
			ventanaComp.cargarImagen(evento.getImagenEvento());
			
			ventanaComp.getComboCategoria().removeAllItems();
			double precio = evento.getPrecioEv();
			ventanaComp.getComboCategoria().addItem("General - $" + String.format("%,.0f", precio) + " COP");
			ventanaComp.getComboCategoria().addItem("VIP - $" + String.format("%,.0f", precio * 1.5) + " COP");
			ventanaComp.getComboCategoria().addItem("Platino - $" + String.format("%,.0f", precio * 2.0) + " COP");
			
			precioUnitario = precio;
			
			ventanaComp.mostrarPanelAsientos(evento.isEventoConSillas());
			
			actualizarPrecioTotal();
			
			ventanaSel.setVisible(false);
			ventanaComp.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, 
				"Error al abrir ventana de compra: " + e.getMessage(), 
				"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void actualizarPrecioTotal() {
		int cantidad = ventanaComp.getCantidad();
		String categoria = ventanaComp.getCategoriaSeleccionada();
		
		double multiplicador = 1.0;
		if (categoria != null) {
			if (categoria.contains("VIP")) {
				multiplicador = 1.5;
			} else if (categoria.contains("Platino")) {
				multiplicador = 2.0;
			}
		}
		
		double total = precioUnitario * multiplicador * cantidad;
		ventanaComp.setLblPrecioTotal(total);
		
		String resumen = String.format(
			"Categoría: %s\n" +
			"Cantidad: %d entrada(s)\n" +
			"Precio unitario: $%,.0f COP\n" +
			"─────────────────────────\n" +
			"TOTAL: $%,.0f COP",
			categoria != null ? categoria.split(" - ")[0] : "N/A",
			cantidad,
			precioUnitario * multiplicador,
			total
		);
		
		ventanaComp.setTxtAreaResumen(resumen);
	}
	
	private void procesarCompra() {
	    try {
	        if (usuarioActual == null) {
	            JOptionPane.showMessageDialog(null, 
	                "Error: No hay usuario logueado", 
	                "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        
	        // NUEVO: Validar nombre e identificación
	        String nombreComprador = ventanaComp.getNombreComprador();
	        String identificacion = ventanaComp.getIdentificacion();
	        
	        if (nombreComprador.isEmpty()) {
	            JOptionPane.showMessageDialog(null, 
	                "Por favor ingrese su nombre completo", 
	                "Campo requerido", JOptionPane.WARNING_MESSAGE);
	            return;
	        }
	        
	        if (identificacion.isEmpty()) {
	            JOptionPane.showMessageDialog(null, 
	                "Por favor ingrese su identificación", 
	                "Campo requerido", JOptionPane.WARNING_MESSAGE);
	            return;
	        }
	        
	        // Obtener el evento desde la lista usando el índice
	        Evento evento = controlGen.getControlEvento().getEventos().get(eventoSeleccionado);
	        String idEvento = evento.getIdEvento();
	        String nombreEvento = evento.getNombreEv();
	        int cantidad = ventanaComp.getCantidad();
	        String categoria = ventanaComp.getCategoriaSeleccionada();
	        boolean conSillas = evento.isEventoConSillas();
	        
	        double multiplicador = 1.0;
	        if (categoria.contains("VIP")) {
	            multiplicador = 1.5;
	        } else if (categoria.contains("Platino")) {
	            multiplicador = 2.0;
	        }
	        
	        double precioTotal = precioUnitario * multiplicador;
	        int numeroSilla = conSillas ? ventanaComp.getNumeroSilla() : -1;
	        
	        int disponibles = evento.getTicketsDisponibles();
	        if (cantidad > disponibles) {
	            JOptionPane.showMessageDialog(null, 
	                "No hay suficientes tickets disponibles.\n" +
	                "Disponibles: " + disponibles + "\n" +
	                "Solicitados: " + cantidad, 
	                "Sin disponibilidad", JOptionPane.WARNING_MESSAGE);
	            return;
	        }
	        
	        StringBuilder idsTickets = new StringBuilder();
	        String primerTicketId = "";
	        
	        for (int i = 0; i < cantidad; i++) {
	            String idTicket = controlGen.crearNuevoTicket(
	                idEvento, 
	                nombreComprador,  // ← Ahora usa el nombre ingresado
	                precioTotal, 
	                conSillas ? (numeroSilla + i) : -1
	            );
	            
	            if (i == 0) {
	                primerTicketId = idTicket;
	                idsTickets.append(idTicket);
	            }
	            
	            controlGen.añadirId(usuarioActual, contraseñaActual, idTicket);
	        }
	        
	        controlGen.getControlEvento().venderTickets(idEvento, cantidad);
	        
	        // MODIFICADO: Detalles con nombre e identificación
	        String detalles = String.format(
	            "══════════════════════════════════\n" +
	            "       COMPRA EXITOSA\n" +
	            "══════════════════════════════════\n\n" +
	            "Evento: %s\n" +
	            "Comprador: %s\n" +
	            "Identificación: %s\n" +
	            "Usuario: %s\n" +
	            "Categoría: %s\n" +
	            "Cantidad: %d entrada(s)\n" +
	            "Precio por entrada: $%,.0f COP\n" +
	            "Total pagado: $%,.0f COP\n" +
	            "%s\n" +
	            "Fecha de compra: Ahora\n\n" +
	            "══════════════════════════════════\n" +
	            "Guarda este código para el acceso",
	            nombreEvento,
	            nombreComprador,
	            identificacion,
	            usuarioActual,
	            categoria.split(" - ")[0],
	            cantidad,
	            precioTotal,
	            precioTotal * cantidad,
	            conSillas ? "Asiento(s): " + numeroSilla + " al " + (numeroSilla + cantidad - 1) : "Sin asiento asignado"
	        );
	        
	        ventanaConf.setIdTicket(primerTicketId);
	        ventanaConf.setDetalles(detalles);
	        
	        ventanaComp.setVisible(false);
	        ventanaConf.setVisible(true);
	        
	        JOptionPane.showMessageDialog(null, 
	            "¡Compra realizada con éxito!\n" +
	            "Se " + (cantidad > 1 ? "han generado " + cantidad + " tickets" : "ha generado 1 ticket"), 
	            "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        
	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(null, 
	            "Error al procesar la compra: " + ex.getMessage(), 
	            "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
}	