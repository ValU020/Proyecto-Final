package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class VentanaEstadisticasEvento extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnCerrar;
    private JLabel lblNombreEvento;
    private JLabel lblFechaEvento;
    private JLabel lblCapacidadTotal;
    private JLabel lblTicketsVendidos;
    private JLabel lblTicketsDisponibles;
    private JLabel lblPorcentajeOcupacion;
    private JLabel lblIngresosActuales;
    private JLabel lblIngresosPotenciales;
    private JProgressBar progressBarOcupacion;
    private JTable tableVentas;
    private DefaultTableModel modeloTabla;
    private JTextArea txtAreaResumen;

    public VentanaEstadisticasEvento() {
        setTitle("Estadísticas del Evento");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 700);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 20));
        
        // Panel superior con título
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(0, 0, 160));
        panelSuperior.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        JLabel lblTitulo = new JLabel("📊 ESTADÍSTICAS DEL EVENTO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(Color.WHITE);
        panelSuperior.add(lblTitulo);
        
        contentPane.add(panelSuperior, BorderLayout.NORTH);
        
        // Panel central dividido en dos columnas
        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 20, 0));
        panelCentral.setBackground(new Color(240, 240, 240));
        
        // COLUMNA IZQUIERDA - Información general
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.setBackground(Color.WHITE);
        panelIzquierdo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 0, 160), 2),
            new EmptyBorder(20, 20, 20, 20)
        ));
        
        JLabel lblSeccionInfo = new JLabel("INFORMACIÓN GENERAL");
        lblSeccionInfo.setFont(new Font("Arial", Font.BOLD, 18));
        lblSeccionInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelIzquierdo.add(lblSeccionInfo);
        
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0, 20)));
        
        lblNombreEvento = new JLabel("Nombre del Evento");
        lblNombreEvento.setFont(new Font("Arial", Font.BOLD, 20));
        lblNombreEvento.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelIzquierdo.add(lblNombreEvento);
        
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0, 10)));
        
        lblFechaEvento = new JLabel("📅 Fecha");
        lblFechaEvento.setFont(new Font("Arial", Font.PLAIN, 14));
        lblFechaEvento.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelIzquierdo.add(lblFechaEvento);
        
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0, 30)));
        
        // Estadísticas de capacidad
        JPanel panelCapacidad = crearPanelEstadistica("🎫 Capacidad Total:", "0");
        lblCapacidadTotal = (JLabel) panelCapacidad.getComponent(1);
        panelIzquierdo.add(panelCapacidad);
        
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JPanel panelVendidos = crearPanelEstadistica("✅ Tickets Vendidos:", "0");
        lblTicketsVendidos = (JLabel) panelVendidos.getComponent(1);
        lblTicketsVendidos.setForeground(new Color(40, 167, 69));
        panelIzquierdo.add(panelVendidos);
        
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JPanel panelDisponibles = crearPanelEstadistica("⏳ Tickets Disponibles:", "0");
        lblTicketsDisponibles = (JLabel) panelDisponibles.getComponent(1);
        lblTicketsDisponibles.setForeground(new Color(255, 193, 7));
        panelIzquierdo.add(panelDisponibles);
        
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Barra de ocupación
        JLabel lblOcupacion = new JLabel("Ocupación:");
        lblOcupacion.setFont(new Font("Arial", Font.BOLD, 14));
        panelIzquierdo.add(lblOcupacion);
        
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0, 5)));
        
        progressBarOcupacion = new JProgressBar(0, 100);
        progressBarOcupacion.setStringPainted(true);
        progressBarOcupacion.setFont(new Font("Arial", Font.BOLD, 12));
        progressBarOcupacion.setForeground(new Color(0, 123, 255));
        progressBarOcupacion.setMaximumSize(new Dimension(400, 30));
        panelIzquierdo.add(progressBarOcupacion);
        
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0, 5)));
        
        lblPorcentajeOcupacion = new JLabel("0%");
        lblPorcentajeOcupacion.setFont(new Font("Arial", Font.PLAIN, 12));
        lblPorcentajeOcupacion.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelIzquierdo.add(lblPorcentajeOcupacion);
        
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0, 30)));
        
        // Ingresos
        JPanel panelIngresosAct = crearPanelEstadistica("💰 Ingresos Actuales:", "$0");
        lblIngresosActuales = (JLabel) panelIngresosAct.getComponent(1);
        lblIngresosActuales.setForeground(new Color(40, 167, 69));
        panelIzquierdo.add(panelIngresosAct);
        
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JPanel panelIngresosPot = crearPanelEstadistica("💎 Ingresos Potenciales:", "$0");
        lblIngresosPotenciales = (JLabel) panelIngresosPot.getComponent(1);
        lblIngresosPotenciales.setForeground(new Color(0, 123, 255));
        panelIzquierdo.add(panelIngresosPot);
        
        // COLUMNA DERECHA - Resumen y tabla
        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
        panelDerecho.setBackground(Color.WHITE);
        panelDerecho.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 0, 160), 2),
            new EmptyBorder(20, 20, 20, 20)
        ));
        
        JLabel lblSeccionResumen = new JLabel("ANÁLISIS Y RESUMEN");
        lblSeccionResumen.setFont(new Font("Arial", Font.BOLD, 18));
        lblSeccionResumen.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelDerecho.add(lblSeccionResumen);
        
        panelDerecho.add(Box.createRigidArea(new Dimension(0, 15)));
        
        txtAreaResumen = new JTextArea(8, 30);
        txtAreaResumen.setEditable(false);
        txtAreaResumen.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtAreaResumen.setLineWrap(true);
        txtAreaResumen.setWrapStyleWord(true);
        txtAreaResumen.setBackground(new Color(248, 249, 250));
        JScrollPane scrollResumen = new JScrollPane(txtAreaResumen);
        scrollResumen.setMaximumSize(new Dimension(450, 180));
        panelDerecho.add(scrollResumen);
        
        panelDerecho.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JLabel lblSeccionVentas = new JLabel("DETALLE DE VENTAS POR CATEGORÍA");
        lblSeccionVentas.setFont(new Font("Arial", Font.BOLD, 14));
        panelDerecho.add(lblSeccionVentas);
        
        panelDerecho.add(Box.createRigidArea(new Dimension(0, 10)));
        
        String[] columnas = {"Categoría", "Vendidos", "Ingresos"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableVentas = new JTable(modeloTabla);
        tableVentas.setFont(new Font("Arial", Font.PLAIN, 13));
        tableVentas.setRowHeight(25);
        tableVentas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tableVentas.getTableHeader().setBackground(new Color(0, 0, 160));
        tableVentas.getTableHeader().setForeground(Color.WHITE);
        
        JScrollPane scrollTabla = new JScrollPane(tableVentas);
        scrollTabla.setMaximumSize(new Dimension(450, 200));
        panelDerecho.add(scrollTabla);
        
        panelCentral.add(panelIzquierdo);
        panelCentral.add(panelDerecho);
        
        contentPane.add(panelCentral, BorderLayout.CENTER);
        
        // Panel inferior con botón
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelInferior.setBackground(new Color(240, 240, 240));
        
        btnCerrar = new JButton("CERRAR");
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnCerrar.setBackground(new Color(0, 0, 160));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setPreferredSize(new Dimension(200, 45));
        
        panelInferior.add(btnCerrar);
        
        contentPane.add(panelInferior, BorderLayout.SOUTH);
    }
    
    private JPanel crearPanelEstadistica(String etiqueta, String valor) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBackground(Color.WHITE);
        panel.setMaximumSize(new Dimension(450, 30));
        
        JLabel lblEtiqueta = new JLabel(etiqueta);
        lblEtiqueta.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Arial", Font.BOLD, 16));
        
        panel.add(lblEtiqueta, BorderLayout.WEST);
        panel.add(lblValor, BorderLayout.EAST);
        
        return panel;
    }
    
    // Métodos para establecer valores
    public void setNombreEvento(String nombre) {
        lblNombreEvento.setText(nombre);
    }
    
    public void setFechaEvento(String fecha) {
        lblFechaEvento.setText("📅 " + fecha);
    }
    
    public void setCapacidadTotal(int capacidad) {
        lblCapacidadTotal.setText(String.valueOf(capacidad));
    }
    
    public void setTicketsVendidos(int vendidos) {
        lblTicketsVendidos.setText(String.valueOf(vendidos));
    }
    
    public void setTicketsDisponibles(int disponibles) {
        lblTicketsDisponibles.setText(String.valueOf(disponibles));
    }
    
    public void setPorcentajeOcupacion(double porcentaje) {
        int porcentajeInt = (int) porcentaje;
        progressBarOcupacion.setValue(porcentajeInt);
        lblPorcentajeOcupacion.setText(String.format("%.1f%%", porcentaje));
        
        // Cambiar color según ocupación
        if (porcentaje < 30) {
            progressBarOcupacion.setForeground(new Color(220, 53, 69));
        } else if (porcentaje < 70) {
            progressBarOcupacion.setForeground(new Color(255, 193, 7));
        } else {
            progressBarOcupacion.setForeground(new Color(40, 167, 69));
        }
    }
    
    public void setIngresosActuales(double ingresos) {
        lblIngresosActuales.setText(String.format("$%,.0f COP", ingresos));
    }
    
    public void setIngresosPotenciales(double ingresos) {
        lblIngresosPotenciales.setText(String.format("$%,.0f COP", ingresos));
    }
    
    public void setResumen(String resumen) {
        txtAreaResumen.setText(resumen);
    }
    
    public void agregarFilaVentas(String categoria, int vendidos, double ingresos) {
        Object[] fila = {
            categoria,
            vendidos,
            String.format("$%,.0f", ingresos)
        };
        modeloTabla.addRow(fila);
    }
    
    public void limpiarTablaVentas() {
        modeloTabla.setRowCount(0);
    }

    public JButton getBtnCerrar() {
        return btnCerrar;
    }
}
