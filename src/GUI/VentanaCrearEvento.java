package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class VentanaCrearEvento extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNombre;
    private JTextField txtFecha;
    private JComboBox<String> comboTipo;
    private JTextField txtPrecio;
    private JSpinner spinnerCapacidad;
    private JCheckBox chkConSillas;
    private JTextField txtImagen;
    private JButton btnCancelar;
    private JButton btnCrear;
    private JButton btnSeleccionarImagen;

    public VentanaCrearEvento() {
        setTitle("Crear Nuevo Evento");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 600);
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
        
        JLabel lblTitulo = new JLabel("➕ CREAR NUEVO EVENTO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(Color.WHITE);
        panelSuperior.add(lblTitulo);
        
        contentPane.add(panelSuperior, BorderLayout.NORTH);
        
        // Panel central con formulario
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new BoxLayout(panelFormulario, BoxLayout.Y_AXIS));
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
            new EmptyBorder(30, 40, 30, 40)
        ));
        
        // Nombre del evento
        panelFormulario.add(crearLabel("Nombre del evento:"));
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 5)));
        txtNombre = new JTextField();
        txtNombre.setFont(new Font("Arial", Font.PLAIN, 14));
        txtNombre.setMaximumSize(new Dimension(550, 35));
        panelFormulario.add(txtNombre);
        
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Fecha
        panelFormulario.add(crearLabel("Fecha (YYYY-MM-DD):"));
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 5)));
        txtFecha = new JTextField();
        txtFecha.setFont(new Font("Arial", Font.PLAIN, 14));
        txtFecha.setMaximumSize(new Dimension(550, 35));
        txtFecha.setToolTipText("Formato: 2025-12-31");
        panelFormulario.add(txtFecha);
        
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Tipo de evento
        panelFormulario.add(crearLabel("Tipo de evento:"));
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 5)));
        String[] tipos = {"Música", "Gastronomía", "Tecnología", "Deportes", 
                         "Arte", "Conferencia", "Teatro", "Otro"};
        comboTipo = new JComboBox<>(tipos);
        comboTipo.setFont(new Font("Arial", Font.PLAIN, 14));
        comboTipo.setMaximumSize(new Dimension(550, 35));
        panelFormulario.add(comboTipo);
        
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Precio base
        panelFormulario.add(crearLabel("Precio base (COP):"));
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 5)));
        txtPrecio = new JTextField();
        txtPrecio.setFont(new Font("Arial", Font.PLAIN, 14));
        txtPrecio.setMaximumSize(new Dimension(550, 35));
        txtPrecio.setToolTipText("Ingrese solo números, sin puntos ni comas");
        panelFormulario.add(txtPrecio);
        
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Capacidad
        panelFormulario.add(crearLabel("Capacidad total:"));
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 5)));
        spinnerCapacidad = new JSpinner(new SpinnerNumberModel(100, 10, 10000, 10));
        spinnerCapacidad.setFont(new Font("Arial", Font.PLAIN, 14));
        spinnerCapacidad.setMaximumSize(new Dimension(550, 35));
        ((JSpinner.DefaultEditor) spinnerCapacidad.getEditor()).getTextField().setEditable(true);
        panelFormulario.add(spinnerCapacidad);
        
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Checkbox para sillas
        chkConSillas = new JCheckBox("Este evento tiene asientos asignados");
        chkConSillas.setFont(new Font("Arial", Font.PLAIN, 14));
        chkConSillas.setBackground(Color.WHITE);
        chkConSillas.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelFormulario.add(chkConSillas);
        
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Imagen (opcional)
        panelFormulario.add(crearLabel("/Resources/imagenes/anime.jpg"));
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 5)));
        
        JPanel panelImagen = new JPanel();
        panelImagen.setLayout(new BoxLayout(panelImagen, BoxLayout.X_AXIS));
        panelImagen.setBackground(Color.WHITE);
        panelImagen.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelImagen.setMaximumSize(new Dimension(550, 35));
        
        txtImagen = new JTextField();
        txtImagen.setFont(new Font("Arial", Font.PLAIN, 14));
        txtImagen.setToolTipText("Deja vacío para usar imagen por defecto");
        txtImagen.setText("imagenes/default.jpeg");
        panelImagen.add(txtImagen);
        
        btnSeleccionarImagen = new JButton("📁");
        btnSeleccionarImagen.setFont(new Font("Arial", Font.PLAIN, 14));
        btnSeleccionarImagen.setFocusPainted(false);
        btnSeleccionarImagen.setPreferredSize(new Dimension(50, 35));
        btnSeleccionarImagen.setToolTipText("Seleccionar imagen");
        panelImagen.add(Box.createRigidArea(new Dimension(5, 0)));
        panelImagen.add(btnSeleccionarImagen);
        
        panelFormulario.add(panelImagen);
        
        JLabel lblNotaImagen = new JLabel("💡 Si no tienes imagen, se usará una por defecto");
        lblNotaImagen.setFont(new Font("Arial", Font.ITALIC, 11));
        lblNotaImagen.setForeground(Color.GRAY);
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 3)));
        panelFormulario.add(lblNotaImagen);
        
        JScrollPane scrollFormulario = new JScrollPane(panelFormulario);
        scrollFormulario.setBorder(null);
        scrollFormulario.getVerticalScrollBar().setUnitIncrement(16);
        
        contentPane.add(scrollFormulario, BorderLayout.CENTER);
        
        // Panel inferior con botones
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelInferior.setBackground(new Color(240, 240, 240));
        
        btnCancelar = new JButton("CANCELAR");
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 16));
        btnCancelar.setBackground(new Color(220, 53, 69));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setPreferredSize(new Dimension(180, 45));
        
        btnCrear = new JButton("CREAR EVENTO");
        btnCrear.setFont(new Font("Arial", Font.BOLD, 16));
        btnCrear.setBackground(new Color(40, 167, 69));
        btnCrear.setForeground(Color.WHITE);
        btnCrear.setFocusPainted(false);
        btnCrear.setPreferredSize(new Dimension(200, 45));
        
        panelInferior.add(btnCancelar);
        panelInferior.add(btnCrear);
        
        contentPane.add(panelInferior, BorderLayout.SOUTH);
    }
    
    private JLabel crearLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }
    
    // Métodos para obtener valores
    public String getNombre() {
        return txtNombre.getText().trim();
    }
    
    public String getFecha() {
        return txtFecha.getText().trim();
    }
    
    public String getTipo() {
        return (String) comboTipo.getSelectedItem();
    }
    
    public String getPrecioTexto() {
        return txtPrecio.getText().trim();
    }
    
    public int getCapacidad() {
        return (int) spinnerCapacidad.getValue();
    }
    
    public boolean getConSillas() {
        return chkConSillas.isSelected();
    }
    
    public String getImagen() {
        String ruta = txtImagen.getText().trim();
        return ruta.isEmpty() ? "imagenes/default.png" : ruta;
    }
    
    // Métodos para limpiar campos
    public void limpiarCampos() {
        txtNombre.setText("");
        txtFecha.setText("");
        comboTipo.setSelectedIndex(0);
        txtPrecio.setText("");
        spinnerCapacidad.setValue(100);
        chkConSillas.setSelected(false);
        txtImagen.setText("imagenes/default.png");
    }

    // Getters de botones
    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public JButton getBtnCrear() {
        return btnCrear;
    }
    
    public JButton getBtnSeleccionarImagen() {
        return btnSeleccionarImagen;
    }
}