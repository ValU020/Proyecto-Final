package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class VentanaInicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textoUsuario;
	private JPasswordField contraseña;
	private JButton btnSalir;
	private JButton btnIniciarSesion;
	private JButton btnRegistrarse;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio frame = new VentanaInicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaInicio() {
		setTitle("EventGo - Iniciar Sesión");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		setLocationRelativeTo(null);
		
		// Establecer el icono de la ventana usando recursos del classpath
		try {
		    java.net.URL urlIcono = getClass().getResource("/Resources/imagenes/logoEg.jpeg");
		    if (urlIcono != null) {
		        ImageIcon icon = new ImageIcon(urlIcono);
		        setIconImage(icon.getImage());
		    } else {
		        System.err.println("No se encontró el recurso del icono");
		    }
		} catch (Exception e) {
		    System.err.println("No se pudo cargar el icono: " + e.getMessage());
		}
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 160));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// LOGO EN LA PARTE SUPERIOR
		JLabel lblLogo = new JLabel();
		lblLogo.setBounds(200, 20, 200, 120);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		try {
			ImageIcon logoIcon = new ImageIcon("imagenes/logoEg.jpeg");
			Image img = logoIcon.getImage().getScaledInstance(180, 100, Image.SCALE_SMOOTH);
			lblLogo.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			lblLogo.setText("EventGo");
			lblLogo.setFont(new Font("Arial", Font.BOLD, 32));
			lblLogo.setForeground(Color.WHITE);
			System.err.println("No se pudo cargar el logo: " + e.getMessage());
		}
		contentPane.add(lblLogo);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsuario.setForeground(new Color(255, 255, 255));
		lblUsuario.setBounds(100, 170, 136, 34);
		contentPane.add(lblUsuario);

		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblContrasea.setForeground(Color.WHITE);
		lblContrasea.setBounds(100, 230, 164, 34);
		contentPane.add(lblContrasea);

		textoUsuario = new JTextField();
		textoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textoUsuario.setBounds(250, 175, 200, 25);
		contentPane.add(textoUsuario);
		textoUsuario.setColumns(10);

		contraseña = new JPasswordField();
		contraseña.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contraseña.setBounds(250, 235, 200, 25);
		contentPane.add(contraseña);

		btnIniciarSesion = new JButton("Iniciar sesión");
		btnIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnIniciarSesion.setBackground(new Color(40, 167, 69));
		btnIniciarSesion.setForeground(Color.WHITE);
		btnIniciarSesion.setFocusPainted(false);
		btnIniciarSesion.setBounds(215, 290, 170, 35);
		contentPane.add(btnIniciarSesion);

		JLabel lblPregunta = new JLabel("¿No tiene una cuenta?");
		lblPregunta.setForeground(new Color(255, 255, 255));
		lblPregunta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPregunta.setBounds(220, 360, 160, 20);
		contentPane.add(lblPregunta);

		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRegistrarse.setBackground(new Color(0, 123, 255));
		btnRegistrarse.setForeground(Color.WHITE);
		btnRegistrarse.setFocusPainted(false);
		btnRegistrarse.setBounds(230, 385, 140, 30);
		contentPane.add(btnRegistrarse);

		btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSalir.setBackground(new Color(220, 53, 69));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFocusPainted(false);
		btnSalir.setBounds(10, 425, 100, 25);
		contentPane.add(btnSalir);
	}

	public String getTextoUsuario() {
		return textoUsuario.getText();
	}

	public String getContraseña() {
		String pass = new String(contraseña.getPassword());
		return pass;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public JButton getBtnIniciarSesion() {
		return btnIniciarSesion;
	}

	public JButton getBtnRegistrarse() {
		return btnRegistrarse;
	}
}