package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class VentanaRegistro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nombreEspacio;
	private JTextField contraseñaEspacio;
	private JButton btnRegresar;
	private JButton btnRegistrarse;
	private JRadioButton rdbtnParaCompras;
	private JRadioButton rdbtnParaVentas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistro frame = new VentanaRegistro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaRegistro() {
		setFont(new Font("Dialog", Font.PLAIN, 13));
		setTitle("Registro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 160));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(21, 22, 1491, 845);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);

		JLabel lblNewLabel = new JLabel("Nombre de usuario:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(37, 122, 313, 58);
		panel.add(lblNewLabel);

		JLabel lblContrasea_2 = new JLabel("Contraseña:");
		lblContrasea_2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblContrasea_2.setBounds(37, 280, 313, 58);
		panel.add(lblContrasea_2);

		btnRegresar = new JButton("Regresar");
		btnRegresar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegresar.setBounds(10, 720, 167, 45);
		panel.add(btnRegresar);

		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegistrarse.setBounds(1314, 720, 167, 45);
		panel.add(btnRegistrarse);

		JLabel lblContrasea_1_1 = new JLabel("Tipo de cuenta:");
		lblContrasea_1_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblContrasea_1_1.setBounds(37, 564, 313, 58);
		panel.add(lblContrasea_1_1);

		nombreEspacio = new JTextField();
		nombreEspacio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nombreEspacio.setBounds(303, 133, 390, 43);
		panel.add(nombreEspacio);
		nombreEspacio.setColumns(10);

		contraseñaEspacio = new JTextField();
		contraseñaEspacio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contraseñaEspacio.setColumns(10);
		contraseñaEspacio.setBounds(303, 291, 390, 43);
		panel.add(contraseñaEspacio);

		rdbtnParaCompras = new JRadioButton("Para compras");
		rdbtnParaCompras.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnParaCompras.setBounds(303, 575, 190, 42);

		rdbtnParaVentas = new JRadioButton("Para ventas");
		rdbtnParaVentas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnParaVentas.setBounds(495, 575, 190, 42);

		// Crear el grupo
		ButtonGroup grupo = new ButtonGroup();

		// Se añaden los RadioButton al grupo
		grupo.add(rdbtnParaVentas);
		grupo.add(rdbtnParaCompras);

		// Se añaden los botones a la ventana
		panel.add(rdbtnParaVentas);
		panel.add(rdbtnParaCompras);
	}

	public String getNombreEspacio() {
		return nombreEspacio.getText();
	}

	public String getContraseñaEspacio() {
		return contraseñaEspacio.getText();
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	public JButton getBtnRegistrarse() {
		return btnRegistrarse;
	}

	public JRadioButton getRdbtnParaCompras() {
		return rdbtnParaCompras;
	}

	public JRadioButton getRdbtnParaVentas() {
		return rdbtnParaVentas;
	}
}
