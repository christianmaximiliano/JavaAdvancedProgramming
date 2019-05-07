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

import com.qdishemax.reservahotel.form.util.TableModelTipoHabitacion;
import com.qdishemax.reservahotel.modelo.TipoHabitacion;
import com.qdishemax.reservahotel.negocio.TipoHabitacionTrs;
import javax.swing.ListSelectionModel;

public class IFrmTipoHabitacion extends JInternalFrame {
	private JTextField txtNomTipHab;
	private JTextArea txaDesTipHab;
	private JTable tabLisTipHab;
	private JTextField txtValBus;
	private TableModelTipoHabitacion myModeloTipHab;
	private TipoHabitacion tipHabSel;
	private JTabbedPane tabbedPane;
	private JButton btnEliTipHab;
	private JButton btnEdiTipHab;
	private JTable tabTipHab;
	
	/**
	 * Create the frame.
	 */
	public IFrmTipoHabitacion() {
		inicializar();
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setTitle("::Administraci\u00F3n Tipo Habitaci\u00F3n::");
		setBounds(100, 100, 450, 300);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setMargin(new Insets(10, 0, 10, 0));
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JButton btnNueTipHab = new JButton("Nuevo");
		btnNueTipHab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNomTipHab.setText("");
				txaDesTipHab.setText("");
			}
		});
		btnNueTipHab.setIcon(new ImageIcon(IFrmTipoHabitacion.class.getResource("/com/qdishemax/reservahotel/resource/imagenHabitacionNueva.png")));
		toolBar.add(btnNueTipHab);
		
		JButton btnGuaTipHab = new JButton("Guardar");
		btnGuaTipHab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 1.Verificar que no tenga campos nulos en base a la definición de la bdd
					if (txtNomTipHab.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Nombre requerido", "Errores", JOptionPane.ERROR_MESSAGE);
					} else {
						// 2. Recuperar los valores y armar el objeto
						TipoHabitacion tipHabitacion = new TipoHabitacion();
						tipHabitacion.setNombreTipHab(txtNomTipHab.getText());
						tipHabitacion.setDescripcionTipHab(txaDesTipHab.getText());
						TipoHabitacionTrs admTipHab = new TipoHabitacionTrs();
						String mensaje = null;
						if (tipHabSel == null) {// Guardar
							// 3. Llamar al controlador
							mensaje = admTipHab.guardar(tipHabitacion);
						}else {
							// 2.1 Setear el id para actualizar
							tipHabitacion.setIdTipHab(tipHabSel.getIdTipHab());
							mensaje = admTipHab.actualizar(tipHabitacion);
						}
						JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
						// 4. Limpiar el formulario
						txtNomTipHab.setText("");
						txaDesTipHab.setText("");
						//5. Encerar la selección y actualizar la tabla
						tipHabSel = null; // Encero la selección
						inicializar(); // Actualizan el modelo
						tabTipHab.setModel(myModeloTipHab);// Actualizan el componente gráfico
						btnEdiTipHab.setEnabled(false);
						btnEliTipHab.setEnabled(false);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error al guardar", "Errores", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnGuaTipHab.setIcon(new ImageIcon(IFrmTipoHabitacion.class.getResource("/com/qdishemax/reservahotel/resource/imagenGuardar.png")));
		toolBar.add(btnGuaTipHab);
		
		btnEdiTipHab = new JButton("Editar");
		btnEdiTipHab.setIcon(new ImageIcon(IFrmTipoHabitacion.class.getResource("/com/qdishemax/reservahotel/resource/iconoEditar.png")));
		btnEdiTipHab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tipHabSel != null) {// Verificar que este seleccionado
					tabbedPane.setSelectedIndex(0);// Cambiar de tab
					txtNomTipHab.setText(tipHabSel.getNombreTipHab());
					txaDesTipHab.setText(tipHabSel.getDescripcionTipHab());
				}else {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún registro", "Errores",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEdiTipHab.setEnabled(false);
		toolBar.add(btnEdiTipHab);
		
		btnEliTipHab = new JButton("Eliminar");
		btnEliTipHab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (tipHabSel != null) { // Esta seleccionado el registro
						int valCon = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el registro?",
								"Confirmación", JOptionPane.YES_NO_OPTION);
						if (valCon == 0) {
							TipoHabitacionTrs admTipPro = new TipoHabitacionTrs();
							String mensaje = admTipPro.eliminar(tipHabSel);
							JOptionPane.showMessageDialog(null, mensaje, "Información",
									JOptionPane.INFORMATION_MESSAGE);
							tipHabSel = null; // Encero la selección
							inicializar(); // Actualizan el modelo
							tabTipHab.setModel(myModeloTipHab);// Actualizan el componente gráfico
							btnEdiTipHab.setEnabled(false);
							btnEliTipHab.setEnabled(false);
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
		btnEliTipHab.setIcon(new ImageIcon(IFrmTipoHabitacion.class.getResource("/com/qdishemax/reservahotel/resource/imagenHabitacionEliminarT.png")));
		btnEliTipHab.setEnabled(false);
		toolBar.add(btnEliTipHab);
		
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
		
		txtNomTipHab = new JTextField();
		GridBagConstraints gbc_txtNomTipHab = new GridBagConstraints();
		gbc_txtNomTipHab.insets = new Insets(0, 0, 5, 0);
		gbc_txtNomTipHab.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNomTipHab.gridx = 2;
		gbc_txtNomTipHab.gridy = 1;
		tabIngTipHab.add(txtNomTipHab, gbc_txtNomTipHab);
		txtNomTipHab.setColumns(10);
		
		JLabel lblDesTipHab = new JLabel("Descripci\u00F3n:");
		GridBagConstraints gbc_lblDesTipHab = new GridBagConstraints();
		gbc_lblDesTipHab.insets = new Insets(0, 0, 0, 5);
		gbc_lblDesTipHab.gridx = 1;
		gbc_lblDesTipHab.gridy = 2;
		tabIngTipHab.add(lblDesTipHab, gbc_lblDesTipHab);
		
		txaDesTipHab = new JTextArea();
		GridBagConstraints gbc_txaDesTipHab = new GridBagConstraints();
		gbc_txaDesTipHab.fill = GridBagConstraints.BOTH;
		gbc_txaDesTipHab.gridx = 2;
		gbc_txaDesTipHab.gridy = 2;
		tabIngTipHab.add(txaDesTipHab, gbc_txaDesTipHab);
		
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
					TipoHabitacionTrs admTipPro = new TipoHabitacionTrs();
					
					List<String> columnas = new ArrayList<>();
					columnas.add("Id");
					columnas.add("Nombre");
					columnas.add("Descripción");
					List<TipoHabitacion> filas = admTipPro.consultarPorNombreDescripcion(txtValBus.getText());

					myModeloTipHab = new TableModelTipoHabitacion(columnas, filas);
					tabTipHab.setModel(myModeloTipHab);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			
			}
		});
		btnConTipHab.setIcon(new ImageIcon(IFrmTipoHabitacion.class.getResource("/com/qdishemax/reservahotel/resource/imagenBuscar.png")));
		pnlBusTipHab.add(btnConTipHab);
		
		tabTipHab = new JTable();
		tabTipHab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabTipHab.setModel(myModeloTipHab);
		JScrollPane spTipHab = new JScrollPane(tabTipHab); //Se incluye a mano
		tabLisTipHab.add(spTipHab, BorderLayout.CENTER);
		
		//Implementar la selección de la tabla
		tabTipHab.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// Recuperar el modelo de Seleccion
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				// Recuperar el indice del registro seleccionado
				int indSel = lsm.getMaxSelectionIndex();
				// Verificar que se haya seleccionado un valor
				if (indSel >= 0) {
					TableModelTipoHabitacion modelo = (TableModelTipoHabitacion) tabTipHab.getModel();
					tipHabSel = modelo.obtenerFilaSeleccionada(indSel);
					btnEdiTipHab.setEnabled(true);
					btnEliTipHab.setEnabled(true);
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

			List<TipoHabitacion> filas = new ArrayList<TipoHabitacion>();
			TipoHabitacionTrs admTipHab = new TipoHabitacionTrs();
			filas = admTipHab.consultarTodos();

			myModeloTipHab = new TableModelTipoHabitacion(columnas, filas);
		} catch (Exception e) {

		}

	}

}
