package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class VentanaConfirmacion extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnVolver;
    private JButton btnVerMisTickets;
    private JLabel lblIdTicket;
    private JTextArea txtAreaDetalles;

    public VentanaConfirmacion() {
        setTitle("Compra Exitosa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 550);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(30, 30, 30, 30));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 20));
        
        // Panel superior con ícono de éxito
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(Color.WHITE);
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS));
        
        JLabel lblIcono = new JLabel("✓");
        lblIcono.setFont(new Font("Arial", Font.BOLD, 80));
        lblIcono.setForeground(new Color(40, 167, 69));
        lblIcono.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelSuperior.add(lblIcono);
        
        panelSuperior.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JLabel lblTitulo = new JLabel("¡COMPRA EXITOSA!");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(new Color(40, 167, 69));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelSuperior.add(lblTitulo);
        
        panelSuperior.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JLabel lblMensaje = new JLabel("Tu entrada ha sido generada correctamente");
        lblMensaje.setFont(new Font("Arial", Font.PLAIN, 16));
        lblMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelSuperior.add(lblMensaje);
        
        contentPane.add(panelSuperior, BorderLayout.NORTH);
        
        // Panel central con detalles
        JPanel panelCentral = new JPanel();
        panelCentral.setBackground(Color.WHITE);
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 0, 160), 2),
            new EmptyBorder(20, 20, 20, 20)
        ));
        
        JLabel lblTicketTitle = new JLabel("ID de tu ticket:");
        lblTicketTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblTicketTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(lblTicketTitle);
        
        panelCentral.add(Box.createRigidArea(new Dimension(0, 10)));
        
        lblIdTicket = new JLabel("TK0000");
        lblIdTicket.setFont(new Font("Monospaced", Font.BOLD, 28));
        lblIdTicket.setForeground(new Color(0, 0, 160));
        lblIdTicket.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(lblIdTicket);
        
        panelCentral.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JLabel lblDetallesTitle = new JLabel("Detalles de la compra:");
        lblDetallesTitle.setFont(new Font("Arial", Font.BOLD, 14));
        panelCentral.add(lblDetallesTitle);
        
        panelCentral.add(Box.createRigidArea(new Dimension(0, 10)));
        
        txtAreaDetalles = new JTextArea(8, 40);
        txtAreaDetalles.setEditable(false);
        txtAreaDetalles.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtAreaDetalles.setBackground(new Color(248, 249, 250));
        txtAreaDetalles.setBorder(new EmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(txtAreaDetalles);
        panelCentral.add(scrollPane);
        
        contentPane.add(panelCentral, BorderLayout.CENTER);
        
        // Panel inferior con botones
        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(Color.WHITE);
        panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        
        btnVolver = new JButton("VOLVER A LA TIENDA");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 14));
        btnVolver.setBackground(new Color(0, 0, 160));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        btnVolver.setPreferredSize(new Dimension(220, 45));
        panelInferior.add(btnVolver);
        
        btnVerMisTickets = new JButton("VER MIS TICKETS");
        btnVerMisTickets.setFont(new Font("Arial", Font.BOLD, 14));
        btnVerMisTickets.setBackground(new Color(40, 167, 69));
        btnVerMisTickets.setForeground(Color.WHITE);
        btnVerMisTickets.setFocusPainted(false);
        btnVerMisTickets.setPreferredSize(new Dimension(220, 45));
        panelInferior.add(btnVerMisTickets);
        
        contentPane.add(panelInferior, BorderLayout.SOUTH);
    }
    
    public void setIdTicket(String id) {
        lblIdTicket.setText(id);
    }
    
    public void setDetalles(String detalles) {
        txtAreaDetalles.setText(detalles);
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public JButton getBtnVerMisTickets() {
        return btnVerMisTickets;
    }
}
