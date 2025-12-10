package GUI;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class VentanaCompra extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnRegresar;
    private JButton btnConfirmarCompra;
    private JComboBox<String> comboCategoria;
    private JSpinner spinnerCantidad;
    private JLabel lblNombreEvento;
    private JLabel lblFechaEvento;
    private JLabel lblTipoEvento;
    private JLabel lblImagenEvento;
    private JLabel lblPrecioTotal;
    private JTextArea txtAreaResumen;
    private JCheckBox chkRequiereAsiento;
    private JSpinner spinnerNumeroSilla;
    private JPanel panelAsientos;
    private JTextField txtNombreComprador;
    private JTextField txtIdentificacion;

    public VentanaCompra() {
        setTitle("Comprar Entrada");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(20, 20));
        
        // Panel superior con título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(0, 0, 160));
        JLabel lblTitulo = new JLabel("COMPRAR ENTRADA");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(Color.WHITE);
        panelTitulo.add(lblTitulo);
        contentPane.add(panelTitulo, BorderLayout.NORTH);
        
        // Panel central dividido en dos columnas
        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 30, 0));
        panelCentral.setBackground(new Color(240, 240, 240));
        
        // COLUMNA IZQUIERDA - Información del evento
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.setBackground(Color.WHITE);
        panelIzquierdo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 0, 160), 2),
            new EmptyBorder(20, 20, 20, 20)
        ));
        
        JLabel lblTituloInfo = new JLabel("INFORMACIÓN DEL EVENTO");
        lblTituloInfo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTituloInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelIzquierdo.add(lblTituloInfo);
        
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0, 20)));
        
        lblImagenEvento = new JLabel();
        lblImagenEvento.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblImagenEvento.setPreferredSize(new Dimension(400, 250));
        lblImagenEvento.setMaximumSize(new Dimension(400, 250));
        panelIzquierdo.add(lblImagenEvento);
        
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0, 20)));
        
        lblNombreEvento = new JLabel("Nombre del Evento");
        lblNombreEvento.setFont(new Font("Arial", Font.BOLD, 22));
        lblNombreEvento.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelIzquierdo.add(lblNombreEvento);
        
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0, 5)));
        
        lblTipoEvento = new JLabel("Tipo de evento");
        lblTipoEvento.setFont(new Font("Arial", Font.ITALIC, 14));
        lblTipoEvento.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTipoEvento.setForeground(new Color(108, 117, 125));
        panelIzquierdo.add(lblTipoEvento);
        
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0, 10)));
        
        lblFechaEvento = new JLabel("Fecha del Evento");
        lblFechaEvento.setFont(new Font("Arial", Font.PLAIN, 16));
        lblFechaEvento.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelIzquierdo.add(lblFechaEvento);
        
        // COLUMNA DERECHA - Formulario de compra
        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
        panelDerecho.setBackground(Color.WHITE);
        panelDerecho.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 0, 160), 2),
            new EmptyBorder(20, 20, 20, 20)
        ));
        
        JLabel lblTituloCompra = new JLabel("REALIZAR COMPRA");
        lblTituloCompra.setFont(new Font("Arial", Font.BOLD, 20));
        lblTituloCompra.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelDerecho.add(lblTituloCompra);
        
        panelDerecho.add(Box.createRigidArea(new Dimension(0, 30)));
        
        // NUEVO: Nombre del comprador
        JLabel lblNombreComprador = new JLabel("Nombre completo:");
        lblNombreComprador.setFont(new Font("Arial", Font.BOLD, 14));
        lblNombreComprador.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelDerecho.add(lblNombreComprador);
        
        panelDerecho.add(Box.createRigidArea(new Dimension(0, 10)));
        
        txtNombreComprador = new JTextField(20);
        txtNombreComprador.setFont(new Font("Arial", Font.PLAIN, 14));
        txtNombreComprador.setMaximumSize(new Dimension(400, 30));
        txtNombreComprador.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelDerecho.add(txtNombreComprador);
        
        panelDerecho.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // NUEVO: Identificación del comprador
        JLabel lblIdentificacion = new JLabel("Identificación:");
        lblIdentificacion.setFont(new Font("Arial", Font.BOLD, 14));
        lblIdentificacion.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelDerecho.add(lblIdentificacion);
        
        panelDerecho.add(Box.createRigidArea(new Dimension(0, 10)));
        
        txtIdentificacion = new JTextField(20);
        txtIdentificacion.setFont(new Font("Arial", Font.PLAIN, 14));
        txtIdentificacion.setMaximumSize(new Dimension(400, 30));
        txtIdentificacion.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelDerecho.add(txtIdentificacion);
        
        panelDerecho.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Categoría
        JLabel lblCategoria = new JLabel("Seleccione la categoría:");
        lblCategoria.setFont(new Font("Arial", Font.BOLD, 14));
        lblCategoria.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelDerecho.add(lblCategoria);
        
        panelDerecho.add(Box.createRigidArea(new Dimension(0, 10)));
        
        comboCategoria = new JComboBox<>();
        comboCategoria.setFont(new Font("Arial", Font.PLAIN, 14));
        comboCategoria.setMaximumSize(new Dimension(400, 30));
        comboCategoria.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelDerecho.add(comboCategoria);
        
        panelDerecho.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Cantidad
        JLabel lblCantidad = new JLabel("Cantidad de entradas:");
        lblCantidad.setFont(new Font("Arial", Font.BOLD, 14));
        lblCantidad.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelDerecho.add(lblCantidad);
        
        panelDerecho.add(Box.createRigidArea(new Dimension(0, 10)));
        
        spinnerCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        spinnerCantidad.setFont(new Font("Arial", Font.PLAIN, 14));
        spinnerCantidad.setMaximumSize(new Dimension(100, 30));
        spinnerCantidad.setAlignmentX(Component.CENTER_ALIGNMENT);
        ((JSpinner.DefaultEditor) spinnerCantidad.getEditor()).getTextField().setEditable(false);
        panelDerecho.add(spinnerCantidad);
        
        panelDerecho.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Panel para asientos (oculto por defecto)
        panelAsientos = new JPanel();
        panelAsientos.setLayout(new BoxLayout(panelAsientos, BoxLayout.Y_AXIS));
        panelAsientos.setBackground(Color.WHITE);
        panelAsientos.setVisible(false);
        
        chkRequiereAsiento = new JCheckBox("Este evento requiere asiento asignado");
        chkRequiereAsiento.setFont(new Font("Arial", Font.BOLD, 13));
        chkRequiereAsiento.setEnabled(false);
        chkRequiereAsiento.setBackground(Color.WHITE);
        chkRequiereAsiento.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAsientos.add(chkRequiereAsiento);
        
        panelAsientos.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JLabel lblSilla = new JLabel("Número de silla:");
        lblSilla.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSilla.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAsientos.add(lblSilla);
        
        panelAsientos.add(Box.createRigidArea(new Dimension(0, 5)));
        
        spinnerNumeroSilla = new JSpinner(new SpinnerNumberModel(1, 1, 500, 1));
        spinnerNumeroSilla.setFont(new Font("Arial", Font.PLAIN, 14));
        spinnerNumeroSilla.setMaximumSize(new Dimension(100, 30));
        spinnerNumeroSilla.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAsientos.add(spinnerNumeroSilla);
        
        panelDerecho.add(panelAsientos);
        panelDerecho.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Precio total
        lblPrecioTotal = new JLabel("TOTAL: $0.00");
        lblPrecioTotal.setFont(new Font("Arial", Font.BOLD, 24));
        lblPrecioTotal.setForeground(new Color(0, 128, 0));
        lblPrecioTotal.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelDerecho.add(lblPrecioTotal);
        
        panelDerecho.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Resumen
        JLabel lblResumen = new JLabel("Resumen de la compra:");
        lblResumen.setFont(new Font("Arial", Font.BOLD, 14));
        lblResumen.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelDerecho.add(lblResumen);
        
        panelDerecho.add(Box.createRigidArea(new Dimension(0, 10)));
        
        txtAreaResumen = new JTextArea(5, 30);
        txtAreaResumen.setEditable(false);
        txtAreaResumen.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtAreaResumen.setLineWrap(true);
        txtAreaResumen.setWrapStyleWord(true);
        JScrollPane scrollResumen = new JScrollPane(txtAreaResumen);
        scrollResumen.setMaximumSize(new Dimension(400, 100));
        scrollResumen.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelDerecho.add(scrollResumen);
        
        panelCentral.add(panelIzquierdo);
        panelCentral.add(panelDerecho);
        
        contentPane.add(panelCentral, BorderLayout.CENTER);
        
        // Panel inferior con botones
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBackground(new Color(240, 240, 240));
        
        btnRegresar = new JButton("REGRESAR");
        btnRegresar.setFont(new Font("Arial", Font.BOLD, 16));
        btnRegresar.setBackground(new Color(220, 53, 69));
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setPreferredSize(new Dimension(180, 50));
        
        btnConfirmarCompra = new JButton("CONFIRMAR COMPRA");
        btnConfirmarCompra.setFont(new Font("Arial", Font.BOLD, 18));
        btnConfirmarCompra.setBackground(new Color(40, 167, 69));
        btnConfirmarCompra.setForeground(Color.WHITE);
        btnConfirmarCompra.setFocusPainted(false);
        btnConfirmarCompra.setPreferredSize(new Dimension(250, 50));
        
        panelInferior.add(btnRegresar, BorderLayout.WEST);
        panelInferior.add(btnConfirmarCompra, BorderLayout.EAST);
        
        contentPane.add(panelInferior, BorderLayout.SOUTH);
    }
    
    // Método para cargar imagen
    public void cargarImagen(String ruta) {
        try {
            java.net.URL imgURL = getClass().getResource("/" + ruta);
            
            if (imgURL != null) {
                ImageIcon icon = new ImageIcon(imgURL);
                Image img = icon.getImage().getScaledInstance(380, 230, Image.SCALE_SMOOTH);
                lblImagenEvento.setIcon(new ImageIcon(img));
            } else {
                File file = new File(ruta);
                if (file.exists()) {
                    ImageIcon icon = new ImageIcon(ruta);
                    Image img = icon.getImage().getScaledInstance(380, 230, Image.SCALE_SMOOTH);
                    lblImagenEvento.setIcon(new ImageIcon(img));
                } else {
                    throw new FileNotFoundException("Imagen no encontrada: " + ruta);
                }
            }
        } catch (Exception e) {
            lblImagenEvento.setText("Sin imagen disponible");
            lblImagenEvento.setHorizontalAlignment(SwingConstants.CENTER);
            lblImagenEvento.setFont(new Font("Arial", Font.PLAIN, 16));
            lblImagenEvento.setForeground(Color.GRAY);
            System.err.println("Error cargando imagen: " + ruta);
        }
    }
    
    // Método para mostrar/ocultar panel de asientos
    public void mostrarPanelAsientos(boolean mostrar) {
        panelAsientos.setVisible(mostrar);
        if (mostrar) {
            chkRequiereAsiento.setSelected(true);
        }
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

    // Getters y Setters
    public JButton getBtnRegresar() {
        return btnRegresar;
    }

    public JButton getBtnConfirmarCompra() {
        return btnConfirmarCompra;
    }

    public JComboBox<String> getComboCategoria() {
        return comboCategoria;
    }

    public JSpinner getSpinnerCantidad() {
        return spinnerCantidad;
    }

    public void setLblNombreEvento(String texto) {
        lblNombreEvento.setText(texto);
    }

    public void setLblFechaEvento(String texto) {
        lblFechaEvento.setText("📅 " + texto);
    }
    
    public void setLblTipoEvento(String tipo) {
        String emoji = obtenerEmojiTipo(tipo);
        lblTipoEvento.setText(emoji + " " + tipo);
    }

    public void setLblPrecioTotal(double precio) {
        lblPrecioTotal.setText(String.format("TOTAL: $%,.0f COP", precio));
    }

    public void setTxtAreaResumen(String texto) {
        txtAreaResumen.setText(texto);
    }

    public int getNumeroSilla() {
        return (int) spinnerNumeroSilla.getValue();
    }

    public int getCantidad() {
        return (int) spinnerCantidad.getValue();
    }

    public String getCategoriaSeleccionada() {
        return (String) comboCategoria.getSelectedItem();
    }
    
    public String getNombreComprador() {
        return txtNombreComprador.getText().trim();
    }

    public String getIdentificacion() {
        return txtIdentificacion.getText().trim();
    }
}