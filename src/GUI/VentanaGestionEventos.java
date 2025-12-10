package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class VentanaGestionEventos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tableEventos;
    private DefaultTableModel modeloTabla;
    private JButton btnRegresar;
    private JButton btnCrearEvento;
    private JButton btnVerEstadisticas;
    private JButton btnEditarEvento;
    private JButton btnEliminarEvento;
    private JLabel lblCantidadEventos;
    private JLabel lblIngresosTotal;

    public VentanaGestionEventos() {
        setTitle("Gestión de Eventos - Panel Vendedor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 20));
        
        // Panel superior con título y estadísticas
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(new Color(0, 0, 160));
        panelSuperior.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        JLabel lblTitulo = new JLabel("📊 MIS EVENTOS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(Color.WHITE);
        panelSuperior.add(lblTitulo, BorderLayout.WEST);
        
        // Panel de estadísticas
        JPanel panelEstadisticas = new JPanel(new GridLayout(2, 1, 5, 5));
        panelEstadisticas.setBackground(new Color(0, 0, 160));
        
        lblCantidadEventos = new JLabel("Total eventos: 0");
        lblCantidadEventos.setFont(new Font("Arial", Font.PLAIN, 16));
        lblCantidadEventos.setForeground(Color.WHITE);
        
        lblIngresosTotal = new JLabel("Ingresos totales: $0 COP");
        lblIngresosTotal.setFont(new Font("Arial", Font.PLAIN, 16));
        lblIngresosTotal.setForeground(new Color(144, 238, 144));
        
        panelEstadisticas.add(lblCantidadEventos);
        panelEstadisticas.add(lblIngresosTotal);
        
        panelSuperior.add(panelEstadisticas, BorderLayout.EAST);
        
        contentPane.add(panelSuperior, BorderLayout.NORTH);
        
        // Panel central con tabla
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBackground(Color.WHITE);
        panelCentral.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
            new EmptyBorder(20, 20, 20, 20)
        ));
        
        String[] columnas = {"ID", "Nombre del Evento", "Fecha", "Tipo", "Precio Base", 
                            "Capacidad", "Vendidos", "Disponibles", "Ingresos"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableEventos = new JTable(modeloTabla);
        tableEventos.setFont(new Font("Arial", Font.PLAIN, 13));
        tableEventos.setRowHeight(30);
        tableEventos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tableEventos.getTableHeader().setBackground(new Color(0, 0, 160));
        tableEventos.getTableHeader().setForeground(Color.WHITE);
        tableEventos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Ajustar ancho de columnas
        tableEventos.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
        tableEventos.getColumnModel().getColumn(1).setPreferredWidth(200); // Nombre
        tableEventos.getColumnModel().getColumn(2).setPreferredWidth(100); // Fecha
        tableEventos.getColumnModel().getColumn(3).setPreferredWidth(100); // Tipo
        tableEventos.getColumnModel().getColumn(4).setPreferredWidth(100); // Precio
        tableEventos.getColumnModel().getColumn(5).setPreferredWidth(80);  // Capacidad
        tableEventos.getColumnModel().getColumn(6).setPreferredWidth(80);  // Vendidos
        tableEventos.getColumnModel().getColumn(7).setPreferredWidth(90);  // Disponibles
        tableEventos.getColumnModel().getColumn(8).setPreferredWidth(120); // Ingresos
        
        JScrollPane scrollPane = new JScrollPane(tableEventos);
        panelCentral.add(scrollPane, BorderLayout.CENTER);
        
        contentPane.add(panelCentral, BorderLayout.CENTER);
        
        // Panel inferior con botones
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBackground(new Color(240, 240, 240));
        
        // Botones izquierda
        JPanel panelBotonesIzq = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelBotonesIzq.setBackground(new Color(240, 240, 240));
        
        btnRegresar = new JButton("← REGRESAR");
        btnRegresar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegresar.setBackground(new Color(220, 53, 69));
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setPreferredSize(new Dimension(150, 45));
        
        panelBotonesIzq.add(btnRegresar);
        
        // Botones derecha
        JPanel panelBotonesDer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelBotonesDer.setBackground(new Color(240, 240, 240));
        
        btnCrearEvento = new JButton("CREAR EVENTO");
        btnCrearEvento.setFont(new Font("Arial", Font.BOLD, 14));
        btnCrearEvento.setBackground(new Color(40, 167, 69));
        btnCrearEvento.setForeground(Color.WHITE);
        btnCrearEvento.setFocusPainted(false);
        btnCrearEvento.setPreferredSize(new Dimension(180, 45));
        
        btnVerEstadisticas = new JButton("ESTADÍSTICAS");
        btnVerEstadisticas.setFont(new Font("Arial", Font.BOLD, 14));
        btnVerEstadisticas.setBackground(new Color(0, 123, 255));
        btnVerEstadisticas.setForeground(Color.WHITE);
        btnVerEstadisticas.setFocusPainted(false);
        btnVerEstadisticas.setPreferredSize(new Dimension(180, 45));
        
        btnEditarEvento = new JButton("EDITAR");
        btnEditarEvento.setFont(new Font("Arial", Font.BOLD, 14));
        btnEditarEvento.setBackground(new Color(255, 193, 7));
        btnEditarEvento.setForeground(Color.BLACK);
        btnEditarEvento.setFocusPainted(false);
        btnEditarEvento.setPreferredSize(new Dimension(130, 45));
        
        btnEliminarEvento = new JButton("ELIMINAR");
        btnEliminarEvento.setFont(new Font("Arial", Font.BOLD, 14));
        btnEliminarEvento.setBackground(new Color(220, 53, 69));
        btnEliminarEvento.setForeground(Color.WHITE);
        btnEliminarEvento.setFocusPainted(false);
        btnEliminarEvento.setPreferredSize(new Dimension(150, 45));
        
        panelBotonesDer.add(btnEditarEvento);
        panelBotonesDer.add(btnEliminarEvento);
        panelBotonesDer.add(btnVerEstadisticas);
        panelBotonesDer.add(btnCrearEvento);
        
        panelInferior.add(panelBotonesIzq, BorderLayout.WEST);
        panelInferior.add(panelBotonesDer, BorderLayout.EAST);
        
        contentPane.add(panelInferior, BorderLayout.SOUTH);
    }
    
    // Método para agregar un evento a la tabla
    public void agregarEventoATabla(String id, String nombre, String fecha, String tipo,
                                     double precio, int capacidad, int vendidos, int disponibles) {
        double ingresos = precio * vendidos;
        Object[] fila = {
            id,
            nombre,
            fecha,
            tipo,
            String.format("$%,.0f", precio),
            capacidad,
            vendidos,
            disponibles,
            String.format("$%,.0f", ingresos)
        };
        modeloTabla.addRow(fila);
        actualizarEstadisticas();
    }
    
    // Método para limpiar la tabla
    public void limpiarTabla() {
        modeloTabla.setRowCount(0);
        actualizarEstadisticas();
    }
    
    // Método para actualizar estadísticas
    private void actualizarEstadisticas() {
        int cantidadEventos = modeloTabla.getRowCount();
        lblCantidadEventos.setText("Total eventos: " + cantidadEventos);
        
        double ingresosTotal = 0;
        for (int i = 0; i < cantidadEventos; i++) {
            String ingresosStr = (String) modeloTabla.getValueAt(i, 8);
            ingresosStr = ingresosStr.replace("$", "").replace(",", "");
            try {
                ingresosTotal += Double.parseDouble(ingresosStr);
            } catch (NumberFormatException e) {
                // Ignorar errores de conversión
            }
        }
        
        lblIngresosTotal.setText(String.format("Ingresos totales: $%,.0f COP", ingresosTotal));
    }
    
    // Método para obtener el ID del evento seleccionado
    public String getEventoSeleccionado() {
        int filaSeleccionada = tableEventos.getSelectedRow();
        if (filaSeleccionada != -1) {
            return (String) modeloTabla.getValueAt(filaSeleccionada, 0);
        }
        return null;
    }
    
    // Método para obtener la fila seleccionada
    public int getFilaSeleccionada() {
        return tableEventos.getSelectedRow();
    }

    // Getters
    public JButton getBtnRegresar() {
        return btnRegresar;
    }

    public JButton getBtnCrearEvento() {
        return btnCrearEvento;
    }

    public JButton getBtnVerEstadisticas() {
        return btnVerEstadisticas;
    }

    public JButton getBtnEditarEvento() {
        return btnEditarEvento;
    }

    public JButton getBtnEliminarEvento() {
        return btnEliminarEvento;
    }
    
    public JTable getTableEventos() {
        return tableEventos;
    }
}
