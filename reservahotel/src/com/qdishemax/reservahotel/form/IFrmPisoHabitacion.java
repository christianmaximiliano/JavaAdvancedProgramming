package com.qdishemax.reservahotel.form;

import java.awt.EventQueue;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.qdishemax.reservahotel.form.util.TableModelPisoHabitacion;
import com.qdishemax.reservahotel.modelo.PisoHabitacion;
import com.qdishemax.reservahotel.negocio.PisoHabitacionTrs;
import javax.swing.ListSelectionModel;

public class IFrmPisoHabitacion extends JInternalFrame {
	private JTextField txtNomPisHab;
	private JTextArea txaDesPisHab;
	private JTable tabLisPisHab;
	private JTextField txtValBus;
	private TableModelPisoHabitacion myModeloPisHab; //aqui
	private PisoHabitacion pisHabSel;  //aqui
	private JTabbedPane tabbedPane;
	private JButton btnEliPisHab;
	private JButton btnEdiPisHab;
	private JTable tabPisHab;
	
	/**
	 * Create the frame.
	 */
	public IFrmPisoHabitacion() {
		inicializar();
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setTitle("::Administraci\u00F3n Piso Habitaci\u00F3n::");
		setBounds(100, 100, 450, 300);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setMargin(new Insets(10, 0, 10, 0));
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JButton btnNueTipHab = new JButton("Nuevo");
		btnNueTipHab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNomPisHab.setText("");
				txaDesPisHab.setText("");
			}
		});
		btnNueTipHab.setIcon(new ImageIcon(IFrmPisoHabitacion.class.getResource("/com/qdishemax/reservahotel/resource/imagenHabitacionNueva.png")));
		toolBar.add(btnNueTipHab);
		
		JButton btnGuaTipHab = new JButton("Guardar");
		btnGuaTipHab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 1.Verificar que no tenga campos nulos en base a la definición de la bdd
					if (txtNomPisHab.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Nombre requerido", "Errores", JOptionPane.ERROR_MESSAGE);
					} else {
						// 2. Recuperar los valores y armar el objeto
						PisoHabitacion tipHabitacion = new PisoHabitacion();
						tipHabitacion.setNombrePisHab(txtNomPisHab.getText());
						tipHabitacion.setDescripcionPisHab(txaDesPisHab.getText());
						PisoHabitacionTrs admTipHab = new PisoHabitacionTrs();
						String mensaje = null;
						if (pisHabSel == null) {// Guardar
							// 3. Llamar al controlador
							mensaje = admTipHab.guardar(tipHabitacion);
						}else {
							// 2.1 Setear el id para actualizar
							tipHabitacion.setIdPisHab(pisHabSel.getIdPisHab());
							mensaje = admTipHab.actualizar(tipHabitacion);
						}
						JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
						// 4. Limpiar el formulario
						txtNomPisHab.setText("");
						txaDesPisHab.setText("");
						//5. Encerar la selección y actualizar la tabla
						pisHabSel = null; // Encero la selección
						inicializar(); // Actualizan el modelo
						tabPisHab.setModel(myModeloPisHab);// Actualizan el componente gráfico
						btnEdiPisHab.setEnabled(false);
						btnEliPisHab.setEnabled(false);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error al guardar", "Errores", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnGuaTipHab.setIcon(new ImageIcon(IFrmPisoHabitacion.class.getResource("/com/qdishemax/reservahotel/resource/imagenGuardar.png")));
		toolBar.add(btnGuaTipHab);
		
		btnEdiPisHab = new JButton("Editar");
		btnEdiPisHab.setIcon(new ImageIcon(IFrmPisoHabitacion.class.getResource("/com/qdishemax/reservahotel/resource/iconoEditar.png")));
		btnEdiPisHab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pisHabSel != null) {// Verificar que este seleccionado
					tabbedPane.setSelectedIndex(0);// Cambiar de tab
					txtNomPisHab.setText(pisHabSel.getNombrePisHab());
					txaDesPisHab.setText(pisHabSel.getDescripcionPisHab());
				}else {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún registro", "Errores",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEdiPisHab.setEnabled(false);
		toolBar.add(btnEdiPisHab);
		
		btnEliPisHab = new JButton("Eliminar");
		btnEliPisHab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (pisHabSel != null) { // Esta seleccionado el registro
						int valCon = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el registro?",
								"Confirmación", JOptionPane.YES_NO_OPTION);
						if (valCon == 0) {
							PisoHabitacionTrs admTipPro = new PisoHabitacionTrs();
							String mensaje = admTipPro.eliminar(pisHabSel);
							JOptionPane.showMessageDialog(null, mensaje, "Información",
									JOptionPane.INFORMATION_MESSAGE);
							pisHabSel = null; // Encero la selección
							inicializar(); // Actualizan el modelo
							tabPisHab.setModel(myModeloPisHab);// Actualizan el componente gráfico
							btnEdiPisHab.setEnabled(false);
							btnEliPisHab.setEnabled(false);
						}

					} else {
						JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún registro", "Errores",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error al eliminar", "Errores", JOptionPane.ERROR_MESSAGE);
				}
			
			}
		});
		btnEliPisHab.setIcon(new ImageIcon(IFrmPisoHabitacion.class.getResource("/com/qdishemax/reservahotel/resource/imagenHabitacionEliminarT.png")));
		btnEliPisHab.setEnabled(false);
		toolBar.add(btnEliPisHab);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel tabIngTipHab = new JPanel();
		tabbedPane.addTab("Ingresar", null, tabIngTipHab, null);
		GridBagLayout gbl_tabIngTipHab = new GridBagLayout();
		gbl_tabIngTipHab.columnWidths = new int[]{0, 0, 0, 0};
		gbl_tabIngTipHab.rowHeights = new int[]{0, 0, 118, 0};
		gbl_tabIngTipHab.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_tabIngTipHab.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		tabIngTipHab.setLayout(gbl_tabIngTipHab);
		
		JLabel lblNomTipHab = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNomTipHab = new GridBagConstraints();
		gbc_lblNomTipHab.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomTipHab.anchor = GridBagConstraints.EAST;
		gbc_lblNomTipHab.gridx = 1;
		gbc_lblNomTipHab.gridy = 1;
		tabIngTipHab.add(lblNomTipHab, gbc_lblNomTipHab);
		
		txtNomPisHab = new JTextField();
		GridBagConstraints gbc_txtNomTipHab = new GridBagConstraints();
		gbc_txtNomTipHab.insets = new Insets(0, 0, 5, 0);
		gbc_txtNomTipHab.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNomTipHab.gridx = 2;
		gbc_txtNomTipHab.gridy = 1;
		tabIngTipHab.add(txtNomPisHab, gbc_txtNomTipHab);
		txtNomPisHab.setColumns(10);
		
		JLabel lblDesTipHab = new JLabel("Descripci\u00F3n:");
		GridBagConstraints gbc_lblDesTipHab = new GridBagConstraints();
		gbc_lblDesTipHab.insets = new Insets(0, 0, 0, 5);
		gbc_lblDesTipHab.gridx = 1;
		gbc_lblDesTipHab.gridy = 2;
		tabIngTipHab.add(lblDesTipHab, gbc_lblDesTipHab);
		
		txaDesPisHab = new JTextArea();
		GridBagConstraints gbc_txaDesTipHab = new GridBagConstraints();
		gbc_txaDesTipHab.fill = GridBagConstraints.BOTH;
		gbc_txaDesTipHab.gridx = 2;
		gbc_txaDesTipHab.gridy = 2;
		tabIngTipHab.add(txaDesPisHab, gbc_txaDesTipHab);
		
		JPanel tabLisTipHab = new JPanel();
		tabbedPane.addTab("Listar", null, tabLisTipHab, null);
		tabLisTipHab.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlBusTipHab = new JPanel();
		tabLisTipHab.add(pnlBusTipHab, BorderLayout.NORTH);
		
		JLabel lblBusPorTipHab = new JLabel("Buscar Nombre/Descripci\u00F3n:");
		pnlBusTipHab.add(lblBusPorTipHab);
		
		txtValBus = new JTextField();
		pnlBusTipHab.add(txtValBus);
		txtValBus.setColumns(10);
		
		JButton btnConTipHab = new JButton("Buscar");
		btnConTipHab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PisoHabitacionTrs admTipPro = new PisoHabitacionTrs();
					
					List<String> columnas = new ArrayList<>();
					columnas.add("Id");
					columnas.add("Nombre");
					columnas.add("Descripción");
					List<PisoHabitacion> filas = admTipPro.consultarPorNombreDescripcion(txtValBus.getText());

					myModeloPisHab = new TableModelPisoHabitacion(columnas, filas);
					tabPisHab.setModel(myModeloPisHab);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			
			}
		});
		btnConTipHab.setIcon(new ImageIcon(IFrmPisoHabitacion.class.getResource("/com/qdishemax/reservahotel/resource/imagenBuscar.png")));
		pnlBusTipHab.add(btnConTipHab);
		
		tabPisHab = new JTable();
		tabPisHab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabPisHab.setModel(myModeloPisHab);
		JScrollPane spTipHab = new JScrollPane(tabPisHab); //Se incluye a mano
		tabLisTipHab.add(spTipHab, BorderLayout.CENTER);
		
		//Implementar la selección de la tabla
		tabPisHab.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// Recuperar el modelo de Seleccion
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				// Recuperar el indice del registro seleccionado
				int indSel = lsm.getMaxSelectionIndex();
				// Verificar que se haya seleccionado un valor
				if (indSel >= 0) {
					TableModelPisoHabitacion modelo = (TableModelPisoHabitacion) tabPisHab.getModel();
					pisHabSel = modelo.obtenerFilaSeleccionada(indSel);
					btnEdiPisHab.setEnabled(true);
					btnEliPisHab.setEnabled(true);
				}

			}
		});
		

	}
	
	private void inicializar() {
		try {
			List<String> columnas = new ArrayList<>();
			columnas.add("Id");
			columnas.add("Nombre");
			columnas.add("Descripción");

			List<PisoHabitacion> filas = new ArrayList<PisoHabitacion>();
			PisoHabitacionTrs admTipHab = new PisoHabitacionTrs();
			filas = admTipHab.consultarTodos();

			myModeloPisHab = new TableModelPisoHabitacion(columnas, filas);
		} catch (Exception e) {

		}

	}

}
