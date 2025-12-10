package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnSalir;
	private JButton btnIrTienda;
	private JTabbedPane tabs;
	private JPanel panelTienda;
	private JPanel panelTickets;
	private JTable tableTickets;
	private DefaultTableModel modeloTablaTickets;

	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Tamaño de la pantalla
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = screenSize.width;
		int alto = screenSize.height;

		// ALTURA fija para el área del TabbedPane
		int alturaTabs = alto - 120;

		// Crear TabbedPane
		tabs = new JTabbedPane(JTabbedPane.TOP);
		tabs.setBounds(0, 0, ancho, alturaTabs);

		// ==================== PANEL TIENDA ====================
		panelTienda = new JPanel();
		panelTienda.setLayout(new BorderLayout());
		panelTienda.setBackground(new Color(240, 240, 240));
		
		JPanel panelTituloTienda = new JPanel();
		panelTituloTienda.setBackground(new Color(0, 0, 160));
		panelTituloTienda.setPreferredSize(new Dimension(ancho, 100));
		
		JLabel lblTituloTienda = new JLabel("🛒 TIENDA DE EVENTOS");
		lblTituloTienda.setFont(new Font("Arial", Font.BOLD, 36));
		lblTituloTienda.setForeground(Color.WHITE);
		panelTituloTienda.add(lblTituloTienda);
		
		panelTienda.add(panelTituloTienda, BorderLayout.NORTH);
		
		JPanel panelContenidoTienda = new JPanel();
		panelContenidoTienda.setLayout(new GridBagLayout());
		panelContenidoTienda.setBackground(new Color(240, 240, 240));
		
		btnIrTienda = new JButton("VER EVENTOS DISPONIBLES");
		btnIrTienda.setFont(new Font("Arial", Font.BOLD, 24));
		btnIrTienda.setBackground(new Color(0, 0, 160));
		btnIrTienda.setForeground(Color.WHITE);
		btnIrTienda.setFocusPainted(false);
		btnIrTienda.setPreferredSize(new Dimension(400, 80));
		
		panelContenidoTienda.add(btnIrTienda);
		panelTienda.add(panelContenidoTienda, BorderLayout.CENTER);

		// ==================== PANEL TICKETS ====================
		panelTickets = new JPanel();
		panelTickets.setLayout(new BorderLayout(0, 20));
		panelTickets.setBackground(new Color(240, 240, 240));
		panelTickets.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		JPanel panelTituloTickets = new JPanel();
		panelTituloTickets.setBackground(new Color(0, 0, 160));
		panelTituloTickets.setPreferredSize(new Dimension(ancho, 80));
		
		JLabel lblTituloTickets = new JLabel("🎫 MIS TICKETS");
		lblTituloTickets.setFont(new Font("Arial", Font.BOLD, 32));
		lblTituloTickets.setForeground(Color.WHITE);
		panelTituloTickets.add(lblTituloTickets);
		
		panelTickets.add(panelTituloTickets, BorderLayout.NORTH);
		
		// Tabla de tickets
		String[] columnasTickets = {"ID Ticket", "Evento", "Fecha Compra", "Precio", "Asiento"};
		modeloTablaTickets = new DefaultTableModel(columnasTickets, 0) {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tableTickets = new JTable(modeloTablaTickets);
		tableTickets.setFont(new Font("Arial", Font.PLAIN, 16));
		tableTickets.setRowHeight(35);
		tableTickets.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		tableTickets.getTableHeader().setBackground(new Color(0, 0, 160));
		tableTickets.getTableHeader().setForeground(Color.WHITE);
		tableTickets.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollTickets = new JScrollPane(tableTickets);
		scrollTickets.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));
		panelTickets.add(scrollTickets, BorderLayout.CENTER);

		tabs.setFont(new Font("Arial", Font.BOLD, 18));
		tabs.addTab("Tienda", panelTienda);
		tabs.addTab("Mis Tickets", panelTickets);

		btnSalir = new JButton("Cerrar Sesión");
		btnSalir.setFont(new Font("Arial", Font.BOLD, 18));
		btnSalir.setBackground(new Color(220, 53, 69));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFocusPainted(false);

		// Ubicación en la parte inferior izquierda
		btnSalir.setBounds(20, alturaTabs + 5, 200, 45);

		// Agregar componentes
		getContentPane().add(tabs);
		getContentPane().add(btnSalir);
	}
	
	// Método para agregar un ticket a la tabla
	public void agregarTicketATabla(String idTicket, String nombreEvento, String fechaCompra, 
									 double precio, int numeroSilla) {
		String asiento = numeroSilla == -1 ? "General" : "Silla " + numeroSilla;
		Object[] fila = {idTicket, nombreEvento, fechaCompra, String.format("$%,.0f COP", precio), asiento};
		modeloTablaTickets.addRow(fila);
	}
	
	// Método para limpiar la tabla de tickets
	public void limpiarTablaTickets() {
		modeloTablaTickets.setRowCount(0);
	}
	
	// Método para cambiar a la pestaña de tickets
	public void mostrarPestañaTickets() {
		tabs.setSelectedComponent(panelTickets);
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}
	
	public JButton getBtnIrTienda() {
		return btnIrTienda;
	}

	public void mostrarVistaVendedor() {
		// Cambiar el título de "Mis Tickets" a "Mis Eventos"
		int indexTickets = tabs.indexOfComponent(panelTickets);
		if (indexTickets != -1) {
			tabs.setTitleAt(indexTickets, "Mis Eventos");
		}
	}
}