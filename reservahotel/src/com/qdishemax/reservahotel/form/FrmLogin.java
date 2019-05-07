package com.qdishemax.reservahotel.form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.qdishemax.reservahotel.modelo.Usuario;
import com.qdishemax.reservahotel.negocio.UsuarioTrs;
import com.sun.org.apache.xml.internal.utils.UnImplNode;

import java.awt.Color;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class FrmLogin extends JFrame {

	private JPanel contentPane;
	private JPasswordField ptxtClaUsu;
	private JTextField txtNomUsu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Se puede cambiar los colores del look and feel
					//UIManager.put("control", new Color(255,255,255));
					//Setear el look and feel
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					//Crea y muestra el formulario
					FrmLogin frame = new FrmLogin();
					frame.setVisible(true);
					//Se ubica siempre en el centro
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmLogin() {
		setResizable(false);
		setTitle("::Sistema de Ventas Reserva Hotel::");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 513, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panLog = new JPanel();
		panLog.setBounds(5, 5, 491, 269);
		panLog.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Iniciar sesi\u00F3n en Sistema de Reserva Hotel", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		contentPane.add(panLog);
		panLog.setLayout(new MigLayout("", "[40px][95px,grow][226px]", "[49.00px][15.00px][20px][8.00px][32.00][49.00px]"));
		
		JPanel panel_1 = new JPanel();
		panLog.add(panel_1, "cell 2 0 1 6,grow");
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FrmLogin.class.getResource("/com/qdishemax/reservahotel/resource/loginImagen.png")));
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		
		JLabel lblNomUsu = new JLabel("Usuario:");
		lblNomUsu.setFont(new Font("Tahoma", Font.BOLD, 11));
		panLog.add(lblNomUsu, "cell 0 1,alignx trailing");
		
		txtNomUsu = new JTextField();
		txtNomUsu.setColumns(10);
		panLog.add(txtNomUsu, "cell 1 1,growx");
		
		JLabel lblClaUsu = new JLabel("Clave:");
		lblClaUsu.setFont(new Font("Tahoma", Font.BOLD, 11));
		panLog.add(lblClaUsu, "cell 0 2,alignx right,aligny center");
		
		ptxtClaUsu = new JPasswordField();
		panLog.add(ptxtClaUsu, "cell 1 2,growx,aligny top");
		
		JButton btnIngLog = new JButton("Iniciar sesi\u00F3n");
		btnIngLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					// 1.Verificar los campos obligatorios según la bdd
					if (!txtNomUsu.getText().equals("") && !ptxtClaUsu.getText().equals("")) {
						// 2.Recuperar esos campos y llamar al controlador
						UsuarioTrs admUsu = new UsuarioTrs();
						Usuario usuario = admUsu.validarUsuario(txtNomUsu.getText(), ptxtClaUsu.getText());
						// 3. Verificar respuesta
						if (usuario != null) {
							// Cerrar la pantalla del login
							FrmLogin.this.dispose();
							// Llamar a la otra clase
							FrmPrincipal frmPrincipal = new FrmPrincipal();
							frmPrincipal.setVisible(true);
							// Maximizar la pantalla
							frmPrincipal.setExtendedState(MAXIMIZED_BOTH);
						}else {
							JOptionPane.showMessageDialog(null, "Usuario no encontrado ", "Errores", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Error credenciales ", "Errores", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error Ingreso: " + e1.getMessage(), "Errores",
							JOptionPane.ERROR_MESSAGE);
				}

			
			}
		});
		btnIngLog.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnIngLog.setIcon(new ImageIcon(FrmLogin.class.getResource("/com/qdishemax/reservahotel/resource/imagenLlave.png")));
		panLog.add(btnIngLog, "cell 1 4");
	}

}
