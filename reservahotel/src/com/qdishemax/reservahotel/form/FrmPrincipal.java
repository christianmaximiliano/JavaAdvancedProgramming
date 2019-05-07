package com.qdishemax.reservahotel.form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.Font;

public class FrmPrincipal extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desPanPri;

	

	/**
	 * Create the frame.
	 */
	public FrmPrincipal() {
		setTitle("::Sistema de Ventas Reserva Hotel:: Men\u00FA General");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 466);
		
		JMenuBar menBarPri = new JMenuBar();
		setJMenuBar(menBarPri);
		
		JMenu menAdm = new JMenu("Administraci\u00F3n");
		menAdm.setFont(new Font("Serif", Font.BOLD, 12));
		menAdm.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qdishemax/reservahotel/resource/iconoTrabajador.png")));
		menBarPri.add(menAdm);
		
		JMenuItem menIteTipHab = new JMenuItem("Tipo Habitaci\u00F3n");
		menIteTipHab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//Crear Pantalla
				IFrmTipoHabitacion iFrmTipoHabitacion = new IFrmTipoHabitacion();
				iFrmTipoHabitacion.setVisible(true);
				//Añadirle al contenedor
				desPanPri.add(iFrmTipoHabitacion);
			}
		});
		menIteTipHab.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qdishemax/reservahotel/resource/imagenHabitacionNueva.png")));
		menAdm.add(menIteTipHab);
		
		JMenuItem menItePisHab = new JMenuItem("Piso Habitaci\u00F3n");
		menItePisHab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//Crear Pantalla
				IFrmPisoHabitacion iFrmPisoHabitacion = new IFrmPisoHabitacion();
				iFrmPisoHabitacion.setVisible(true);
				//Añadirle al contenedor
				desPanPri.add(iFrmPisoHabitacion);
			}
		});
		
		JMenuItem menIteEstHab = new JMenuItem("Estado Habitaci\u00F3n");
		menIteEstHab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//Crear Pantalla
				IFrmEstadoHabitacion iFrmEstadoHabitacion = new IFrmEstadoHabitacion();
				iFrmEstadoHabitacion.setVisible(true);
				//Añadirle al contenedor
				desPanPri.add(iFrmEstadoHabitacion);
			}
		});
		menIteEstHab.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qdishemax/reservahotel/resource/imagenHabitacionNueva.png")));
		menAdm.add(menIteEstHab);
		menItePisHab.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qdishemax/reservahotel/resource/iconoHotelPiso.png")));
		menAdm.add(menItePisHab);
		
		JMenuItem menIteSal = new JMenuItem("Salir");
		menIteSal.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qdishemax/reservahotel/resource/imagenSalir.png")));
		menAdm.add(menIteSal);
		
		JMenu menSeg = new JMenu("Seguridad");
		menSeg.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menSeg.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qdishemax/reservahotel/resource/iconoSeguridad.png")));
		menBarPri.add(menSeg);
		
		JMenuItem menIteUsu = new JMenuItem("Usuario");
		menIteUsu.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qdishemax/reservahotel/resource/iconousuarios.png")));
		menSeg.add(menIteUsu);
		
		JMenu menHot = new JMenu("Hotel");
		menHot.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menHot.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qdishemax/reservahotel/resource/iconohotel1.png")));
		menBarPri.add(menHot);
		
		JMenuItem menIteHab = new JMenuItem("Habitaci\u00F3n");
		menIteHab.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qdishemax/reservahotel/resource/imagenHabitacionNueva.png")));
		menIteHab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Crear Pantalla
				IFrmHabitacion iFrmHabitacion = new IFrmHabitacion();
				iFrmHabitacion.setVisible(true);
				//Añadirle al contenedor
				desPanPri.add(iFrmHabitacion);
			}
		});
		menHot.add(menIteHab);
		
		JMenuItem menIteCliente = new JMenuItem("Cliente");
		menIteCliente.setVerifyInputWhenFocusTarget(false);
		menIteCliente.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qdishemax/reservahotel/resource/iconAreaCliente (1).png")));
		menIteCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Crear Pantalla
				IFrmCliente iFrmCliente = new IFrmCliente();
				iFrmCliente.setVisible(true);
				//Añadirle al contenedor
				desPanPri.add(iFrmCliente);
				
			}
		});
		menHot.add(menIteCliente);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("");
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		lblNewLabel.setAlignmentY(0.0f);
		lblNewLabel.setInheritsPopupMenu(false);
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		
		desPanPri = new JDesktopPane();
		desPanPri.setBackground(SystemColor.activeCaption);
		contentPane.add(desPanPri, BorderLayout.CENTER);
	}
}
