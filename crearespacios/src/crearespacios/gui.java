package crearespacios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JDesktopPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class gui extends JFrame {
	private JTextField textField_1;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui frame = new gui();
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
	public gui() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 316);
		getContentPane().setLayout(null);
		
		
		JPanel Promos = new JPanel();
		Promos.setBackground(new Color(255, 255, 255));
		Promos.setVisible(false);
		
		JPanel Reservas = new JPanel();
		Reservas.setVisible(false);
		
		
		JPanel Inicio = new JPanel();
		Inicio.setBackground(new Color(255, 255, 255));
		Inicio.setLayout(null);
		Inicio.setBounds(0, 0, 434, 277);
		getContentPane().add(Inicio);
		JButton VR = new JButton("Ver reservas");
		VR.setBounds(160, 175, 110, 23);
		Inicio.add(VR);
		JButton R = new JButton("Reservas");
		R.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Inicio.setVisible(false);
				Reservas.setVisible(true);
			}
		});
		R.setBounds(10, 175, 110, 23);
		Inicio.add(R);
		JButton P = new JButton("Promos");
		P.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				Promos.setVisible(true);
				Inicio.setVisible(false);
			}
		});
		P.setBounds(311, 175, 110, 23);
		Inicio.add(P);
		Reservas.setBackground(new Color(255, 255, 255));
		Reservas.setBounds(0, 0, 434, 277);
		getContentPane().add(Reservas);
		Reservas.setLayout(null);
		
		JButton Regresar = new JButton("\uD83C\uDFE0");
		Regresar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Inicio.setVisible(true);
				Reservas.setVisible(false);
			}
		});
		Regresar.setBorder(null);
		Regresar.setBackground(Color.WHITE);
		Regresar.setBounds(0, 0, 51, 23);
		Reservas.add(Regresar);
		
		JLabel GR = new JLabel("Generar Reservas");
		GR.setHorizontalAlignment(SwingConstants.CENTER);
		GR.setFont(new Font("Arial", Font.PLAIN, 20));
		GR.setBounds(0, 0, 434, 70);
		Reservas.add(GR);
		
		JLabel NroR = new JLabel("Nro Reserva: ");
		NroR.setBounds(10, 81, 72, 26);
		Reservas.add(NroR);
		
		JLabel NomC = new JLabel("Nombre del cliente: ");
		NomC.setBounds(10, 106, 101, 20);
		Reservas.add(NomC);
		
		textField = new JTextField();
		textField.setBounds(106, 106, 86, 20);
		Reservas.add(textField);
		textField.setColumns(10);
		
		JLabel FE = new JLabel("Fecha de entrega:");
		FE.setBounds(10, 137, 101, 20);
		Reservas.add(FE);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(106, 137, 86, 20);
		Reservas.add(textField_2);
		
		JLabel S = new JLabel("Se\u00F1a:");
		S.setBounds(10, 196, 101, 20);
		Reservas.add(S);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(106, 196, 86, 20);
		Reservas.add(textField_3);
		
		JLabel lblNewLabel_4 = new JLabel("Monto: ");
		lblNewLabel_4.setBounds(10, 168, 46, 14);
		Reservas.add(lblNewLabel_4);
		Promos.setBounds(0, 0, 434, 277);
		getContentPane().add(Promos);
		Promos.setLayout(null);
		

		/**
		 * Etiqueta que representa la seccion de la nueva promo
		 */
		
		JLabel NewP = new JLabel("Nueva Promo:");
		NewP.setFont(new Font("Arial", Font.PLAIN, 16));
		NewP.setHorizontalAlignment(SwingConstants.LEFT);
		NewP.setBounds(10, 140, 152, 20);
		Promos.add(NewP);
		/**
		 * Etiqueta que representa el contenido de las promos
		 */
		JLabel ConP = new JLabel("Contenido:");
		ConP.setFont(new Font("Arial", Font.PLAIN, 16));
		ConP.setBounds(10, 178, 96, 17);
		Promos.add(ConP);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(90, 203, 86, 20);
		Promos.add(textField_1);
		/**
		 * Etiqueta que representa el precio de las promos
		 */
		JLabel PP = new JLabel("Precio:");
		PP.setFont(new Font("Arial", Font.PLAIN, 16));
		PP.setBounds(10, 203, 66, 17);
		Promos.add(PP);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(90, 177, 86, 22);
		Promos.add(comboBox);
		
		
		/**
		 * Etiqueta que representa el estado de las promos
		 */
		JLabel EP = new JLabel("Estado:");
		EP.setFont(new Font("Arial", Font.PLAIN, 16));
		EP.setBounds(190, 143, 66, 14);
		Promos.add(EP);
		

		
		
		/**
		 * Boton para regresar al inicio. Esta aca porque si no se rompe
		 */
		
		JButton btnNewButton_3 = new JButton("\uD83C\uDFE0");
		btnNewButton_3.setBorder(null);
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Promos.setVisible(false);
				Inicio.setVisible(true);
			}
		});
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.setBounds(0, 0, 51, 23);
		Promos.add(btnNewButton_3);
		
		/**
		 * Etiqueta para representar la seccion de promos
		 */
		JLabel CtrlP_1 = new JLabel("Controlar Promos");
		CtrlP_1.setToolTipText("");
		CtrlP_1.setHorizontalAlignment(SwingConstants.CENTER);
		CtrlP_1.setFont(new Font("Arial", Font.PLAIN, 20));
		CtrlP_1.setBounds(0, 0, 434, 101);
		Promos.add(CtrlP_1);
		
		/**
		 * Boton que lleva a la seccion de ver las reservas
		 */
		
		/**
		 * Boton que lleva a la seccion de gestion de las reservas
		 */
		/**
		 * Boton que lleva a la seccion de ver y gestionar las promos
		 */
	}
}
