package GUI;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;


public class VentanaSeleccionEvento extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnRegresar;
    private JPanel panelEventos;
    private ArrayList<JButton> botonesEventos;
    private ArrayList<String> idsEventos;

    public VentanaSeleccionEvento() {
        setTitle("Seleccionar Evento");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
        
        botonesEventos = new ArrayList<>();
        idsEventos = new ArrayList<>();
        
        // Panel superior con título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(0, 0, 160));
        JLabel lblTitulo = new JLabel("🎉 EVENTOS DISPONIBLES");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(Color.WHITE);
        panelTitulo.add(lblTitulo);
        contentPane.add(panelTitulo, BorderLayout.NORTH);
        
        // Panel central con scroll para eventos dinámicos
        panelEventos = new JPanel();
        panelEventos.setBackground(new Color(240, 240, 240));
        panelEventos.setBorder(new EmptyBorder(30, 30, 30, 30));
        
        JScrollPane scrollPane = new JScrollPane(panelEventos);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        // Panel inferior con botón regresar
        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(new Color(240, 240, 240));
        panelInferior.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        btnRegresar = new JButton("← REGRESAR");
        btnRegresar.setFont(new Font("Arial", Font.BOLD, 16));
        btnRegresar.setBackground(new Color(220, 53, 69));
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setPreferredSize(new Dimension(180, 45));
        panelInferior.add(btnRegresar);
        
        contentPane.add(panelInferior, BorderLayout.SOUTH);
    }
    
    // Método para limpiar todos los eventos
    public void limpiarEventos() {
        panelEventos.removeAll();
        botonesEventos.clear();
        idsEventos.clear();
    }
    
    // Método para establecer el layout según cantidad de eventos
    public void configurarLayout(int cantidadEventos) {
        int columnas = Math.min(3, cantidadEventos); // Máximo 3 columnas
        if (cantidadEventos == 0) columnas = 1;
        
        int filas = (int) Math.ceil((double) cantidadEventos / 3);
        if (filas == 0) filas = 1;
        
        panelEventos.setLayout(new GridLayout(filas, columnas, 30, 30));
    }
    
    // Método para agregar un evento a la vista
 // Método para agregar un evento a la vista
    public void agregarEvento(String idEvento, String nombre, String fecha, String tipo, 
                              double precio, String imagen) {
        
        // Crear panel del evento
        JPanel panelEvento = new JPanel();
        panelEvento.setLayout(new BoxLayout(panelEvento, BoxLayout.Y_AXIS));
        panelEvento.setBackground(Color.WHITE);
        panelEvento.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
            new EmptyBorder(30, 20, 30, 20)
        ));
        
        // Centrar el panel
        panelEvento.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelEvento.setAlignmentY(Component.CENTER_ALIGNMENT);
        
        // Glue para centrar verticalmente
        panelEvento.add(Box.createVerticalGlue());
        
        // Imagen
        JLabel lblImagen = new JLabel();
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagen.setPreferredSize(new Dimension(350, 220));
        lblImagen.setMaximumSize(new Dimension(350, 220));
        cargarImagen(lblImagen, imagen);
        lblImagen.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelEvento.add(lblImagen);
        
        panelEvento.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Nombre
        JLabel lblNombre = new JLabel(nombre);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 18));
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelEvento.add(lblNombre);
        
        panelEvento.add(Box.createRigidArea(new Dimension(0, 5)));
        
        // Tipo de evento
        JLabel lblTipo = new JLabel(obtenerEmojiTipo(tipo) + " " + tipo);
        lblTipo.setFont(new Font("Arial", Font.PLAIN, 13));
        lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTipo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTipo.setForeground(new Color(108, 117, 125));
        panelEvento.add(lblTipo);
        
        panelEvento.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // Fecha
        JLabel lblFecha = new JLabel("📅 " + fecha);
        lblFecha.setFont(new Font("Arial", Font.PLAIN, 14));
        lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
        lblFecha.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelEvento.add(lblFecha);
        
        panelEvento.add(Box.createRigidArea(new Dimension(0, 5)));
        
        // Precio
        JLabel lblPrecio = new JLabel("💵 Desde $" + String.format("%,.0f", precio) + " COP");
        lblPrecio.setFont(new Font("Arial", Font.BOLD, 16));
        lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
        lblPrecio.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPrecio.setForeground(new Color(0, 128, 0));
        panelEvento.add(lblPrecio);
        
        panelEvento.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Botón de compra
        JButton btnComprar = new JButton("COMPRAR ENTRADA");
        btnComprar.setFont(new Font("Arial", Font.BOLD, 14));
        btnComprar.setBackground(new Color(0, 0, 160));
        btnComprar.setForeground(Color.WHITE);
        btnComprar.setFocusPainted(false);
        btnComprar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnComprar.setMaximumSize(new Dimension(250, 40));
        
        panelEvento.add(btnComprar);
        
        // Glue para centrar verticalmente
        panelEvento.add(Box.createVerticalGlue());
        
        // Agregar a las listas
        botonesEventos.add(btnComprar);
        idsEventos.add(idEvento);
        
        // Agregar el panel al contenedor principal
        panelEventos.add(panelEvento);
    }
    
    // Método para obtener emoji según tipo de evento
    private String obtenerEmojiTipo(String tipo) {
        switch (tipo.toLowerCase()) {
            case "música": return "🎵";
            case "gastronomía": return "🍽️";
            case "tecnología": return "💻";
            case "deportes": return "⚽";
            case "arte": return "🎨";
            case "conferencia": return "🎤";
            case "teatro": return "🎭";
            default: return "🎪";
        }
    }
    
    // Método para cargar imagen
    private void cargarImagen(JLabel label, String ruta) {
        try {
            // Intentar cargar desde recursos internos del JAR
            java.net.URL imgURL = getClass().getResource("/" + ruta);
            
            if (imgURL != null) {
                ImageIcon icon = new ImageIcon(imgURL);
                Image img = icon.getImage().getScaledInstance(350, 220, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(img));
            } else {
                // Si no existe, intentar cargar desde archivo externo
                File file = new File(ruta);
                if (file.exists()) {
                    ImageIcon icon = new ImageIcon(ruta);
                    Image img = icon.getImage().getScaledInstance(350, 220, Image.SCALE_SMOOTH);
                    label.setIcon(new ImageIcon(img));
                } else {
                    throw new FileNotFoundException("Imagen no encontrada: " + ruta);
                }
            }
        } catch (Exception e) {
            label.setText("📷 Sin imagen");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            label.setForeground(Color.GRAY);
            System.err.println("Error cargando imagen: " + ruta + " - " + e.getMessage());
        }
    }    
    // Método para refrescar la vista
    public void refrescarVista() {
        panelEventos.revalidate();
        panelEventos.repaint();
    }

    // Getters
    public JButton getBtnRegresar() {
        return btnRegresar;
    }
    
    public ArrayList<JButton> getBotonesEventos() {
        return botonesEventos;
    }
    
    public String getIdEventoPorBoton(JButton boton) {
        int index = botonesEventos.indexOf(boton);
        if (index != -1 && index < idsEventos.size()) {
            return idsEventos.get(index);
        }
        return null;
    }
    
    public int getIndicePorIdEvento(String idEvento) {
        return idsEventos.indexOf(idEvento);
    }
}