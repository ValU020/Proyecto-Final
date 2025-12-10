package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class VentanaMisTickets extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tableTickets;
    private DefaultTableModel modeloTabla;
    private JButton btnRegresar;
    private JButton btnVerDetalles;
    private JLabel lblCantidadTickets;

    public VentanaMisTickets() {
        setTitle("Mis Tickets");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 20));
        
        // Panel superior con título
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(new Color(0, 0, 160));
        panelSuperior.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        JLabel lblTitulo = new JLabel("EVENTOS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(Color.WHITE);
        panelSuperior.add(lblTitulo, BorderLayout.WEST);
        
        lblCantidadTickets = new JLabel("Total: 0 tickets");
        lblCantidadTickets.setFont(new Font("Arial", Font.PLAIN, 18));
        lblCantidadTickets.setForeground(Color.WHITE);
        panelSuperior.add(lblCantidadTickets, BorderLayout.EAST);
        
        contentPane.add(panelSuperior, BorderLayout.NORTH);
        
        // Panel central con tabla
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBackground(Color.WHITE);
        panelCentral.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
            new EmptyBorder(20, 20, 20, 20)
        ));
        
        String[] columnas = {"ID Ticket", "Evento", "Fecha Compra", "Precio", "Asiento"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableTickets = new JTable(modeloTabla);
        tableTickets.setFont(new Font("Arial", Font.PLAIN, 14));
        tableTickets.setRowHeight(30);
        tableTickets.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tableTickets.getTableHeader().setBackground(new Color(0, 0, 160));
        tableTickets.getTableHeader().setForeground(Color.WHITE);
        tableTickets.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(tableTickets);
        panelCentral.add(scrollPane, BorderLayout.CENTER);
        
        contentPane.add(panelCentral, BorderLayout.CENTER);
        
        // Panel inferior con botones
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBackground(new Color(240, 240, 240));
        
        btnRegresar = new JButton("← REGRESAR");
        btnRegresar.setFont(new Font("Arial", Font.BOLD, 16));
        btnRegresar.setBackground(new Color(220, 53, 69));
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setPreferredSize(new Dimension(180, 50));
        
        btnVerDetalles = new JButton("VER DETALLES");
        btnVerDetalles.setFont(new Font("Arial", Font.BOLD, 16));
        btnVerDetalles.setBackground(new Color(0, 123, 255));
        btnVerDetalles.setForeground(Color.WHITE);
        btnVerDetalles.setFocusPainted(false);
        btnVerDetalles.setPreferredSize(new Dimension(200, 50));
        
        panelInferior.add(btnRegresar, BorderLayout.WEST);
        panelInferior.add(btnVerDetalles, BorderLayout.EAST);
        
        contentPane.add(panelInferior, BorderLayout.SOUTH);
    }
    
    // Método para agregar un ticket a la tabla
    public void agregarTicketATabla(String idTicket, String nombreEvento, String fechaCompra, 
                                     double precio, int numeroSilla) {
        String asiento = numeroSilla == -1 ? "General" : "Silla " + numeroSilla;
        Object[] fila = {idTicket, nombreEvento, fechaCompra, String.format("$%.2f", precio), asiento};
        modeloTabla.addRow(fila);
        actualizarContador();
    }
    
    // Método para limpiar la tabla
    public void limpiarTabla() {
        modeloTabla.setRowCount(0);
        actualizarContador();
    }
    
    // Método para actualizar el contador
    private void actualizarContador() {
        int cantidad = modeloTabla.getRowCount();
        lblCantidadTickets.setText("Total: " + cantidad + " ticket" + (cantidad != 1 ? "s" : ""));
    }
    
    // Método para obtener el ticket seleccionado
    public String getTicketSeleccionado() {
        int filaSeleccionada = tableTickets.getSelectedRow();
        if (filaSeleccionada != -1) {
            return (String) modeloTabla.getValueAt(filaSeleccionada, 0);
        }
        return null;
    }

    public JButton getBtnRegresar() {
        return btnRegresar;
    }

    public JButton getBtnVerDetalles() {
        return btnVerDetalles;
    }
    
    public JTable getTableTickets() {
        return tableTickets;
    }
}