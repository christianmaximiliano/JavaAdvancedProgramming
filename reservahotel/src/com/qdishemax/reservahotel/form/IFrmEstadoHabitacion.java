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

import com.qdishemax.reservahotel.form.util.TableModelEstadoHabitacion;
import com.qdishemax.reservahotel.modelo.EstadoHabitacion;
import com.qdishemax.reservahotel.negocio.EstadoHabitacionTrs;
import javax.swing.ListSelectionModel;

public class IFrmEstadoHabitacion extends JInternalFrame {
	private JTextField txtNomEstHab;
	private JTextArea txaDesEstHab;
	private JTable tabLisEstHab;
	private JTextField txtValBus;
	private TableModelEstadoHabitacion myModeloEstHab;
	private EstadoHabitacion estHabSel;
	private JTabbedPane tabbedPane;
	private JButton btnEliEstHab;
	private JButton btnEdiEstHab;
	private JTable tabEstHab;
	
	/**
	 * Create the frame.
	 */
	public IFrmEstadoHabitacion() {
		inicializar();
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setTitle("::Administraci\u00F3n Estado Habitaci\u00F3n::");
		setBounds(100, 100, 450, 300);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setMargin(new Insets(10, 0, 10, 0));
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JButton btnNueEstHab = new JButton("Nuevo");
		btnNueEstHab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNomEstHab.setText("");
				txaDesEstHab.setText("");
			}
		});
		btnNueEstHab.setIcon(new ImageIcon(IFrmEstadoHabitacion.class.getResource("/com/qdishemax/reservahotel/resource/imagenHabitacionNueva.png")));
		toolBar.add(btnNueEstHab);
		
		JButton btnGuaEstHab = new JButton("Guardar");
		btnGuaEstHab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 1.Verificar que no tenga campos nulos en base a la definición de la bdd
					if (txtNomEstHab.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Nombre requerido", "Errores", JOptionPane.ERROR_MESSAGE);
					} else {
						// 2. Recuperar los valores y armar el objeto
						EstadoHabitacion estHabitacion = new EstadoHabitacion();
						estHabitacion.setNombreEstHab(txtNomEstHab.getText());
						estHabitacion.setDescripcionEstHab(txaDesEstHab.getText());
						EstadoHabitacionTrs admEstHab = new EstadoHabitacionTrs();
						String mensaje = null;
						if (estHabSel == null) {// Guardar
							// 3. Llamar al controlador
							mensaje = admEstHab.guardar(estHabitacion);
						}else {
							// 2.1 Setear el id para actualizar
							estHabitacion.setIdEstHab(estHabSel.getIdEstHab());
							mensaje = admEstHab.actualizar(estHabitacion);
						}
						JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
						// 4. Limpiar el formulario
						txtNomEstHab.setText("");
						txaDesEstHab.setText("");
						//5. Encerar la selección y actualizar la tabla
						estHabSel = null; // Encero la selección
						inicializar(); // Actualizan el modelo
						tabEstHab.setModel(myModeloEstHab);// Actualizan el componente gráfico
						btnEdiEstHab.setEnabled(false);
						btnEliEstHab.setEnabled(false);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error al guardar", "Errores", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnGuaEstHab.setIcon(new ImageIcon(IFrmEstadoHabitacion.class.getResource("/com/qdishemax/reservahotel/resource/imagenGuardar.png")));
		toolBar.add(btnGuaEstHab);
		
		btnEdiEstHab = new JButton("Editar");
		btnEdiEstHab.setIcon(new ImageIcon(IFrmEstadoHabitacion.class.getResource("/com/qdishemax/reservahotel/resource/iconoEditar.png")));
		btnEdiEstHab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (estHabSel != null) {// Verificar que este seleccionado
					tabbedPane.setSelectedIndex(0);// Cambiar de tab
					txtNomEstHab.setText(estHabSel.getNombreEstHab());
					txaDesEstHab.setText(estHabSel.getDescripcionEstHab());
				}else {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún registro", "Errores",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEdiEstHab.setEnabled(false);
		toolBar.add(btnEdiEstHab);
		
		btnEliEstHab = new JButton("Eliminar");
		btnEliEstHab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (estHabSel != null) { // Esta seleccionado el registro
						int valCon = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el registro?",
								"Confirmación", JOptionPane.YES_NO_OPTION);
						if (valCon == 0) {
							EstadoHabitacionTrs admEstPro = new EstadoHabitacionTrs();
							String mensaje = admEstPro.eliminar(estHabSel);
							JOptionPane.showMessageDialog(null, mensaje, "Información",
									JOptionPane.INFORMATION_MESSAGE);
							estHabSel = null; // Encero la selección
							inicializar(); // Actualizan el modelo
							tabEstHab.setModel(myModeloEstHab);// Actualizan el componente gráfico
							btnEdiEstHab.setEnabled(false);
							btnEliEstHab.setEnabled(false);
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
		btnEliEstHab.setIcon(new ImageIcon(IFrmEstadoHabitacion.class.getResource("/com/qdishemax/reservahotel/resource/imagenHabitacionEliminarT.png")));
		btnEliEstHab.setEnabled(false);
		toolBar.add(btnEliEstHab);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel tabIngEstHab = new JPanel();
		tabbedPane.addTab("Ingresar", null, tabIngEstHab, null);
		GridBagLayout gbl_tabIngEstHab = new GridBagLayout();
		gbl_tabIngEstHab.columnWidths = new int[]{0, 0, 0, 0};
		gbl_tabIngEstHab.rowHeights = new int[]{0, 0, 118, 0};
		gbl_tabIngEstHab.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_tabIngEstHab.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		tabIngEstHab.setLayout(gbl_tabIngEstHab);
		
		JLabel lblNomEstHab = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNomEstHab = new GridBagConstraints();
		gbc_lblNomEstHab.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomEstHab.anchor = GridBagConstraints.EAST;
		gbc_lblNomEstHab.gridx = 1;
		gbc_lblNomEstHab.gridy = 1;
		tabIngEstHab.add(lblNomEstHab, gbc_lblNomEstHab);
		
		txtNomEstHab = new JTextField();
		GridBagConstraints gbc_txtNomEstHab = new GridBagConstraints();
		gbc_txtNomEstHab.insets = new Insets(0, 0, 5, 0);
		gbc_txtNomEstHab.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNomEstHab.gridx = 2;
		gbc_txtNomEstHab.gridy = 1;
		tabIngEstHab.add(txtNomEstHab, gbc_txtNomEstHab);
		txtNomEstHab.setColumns(10);
		
		JLabel lblDesEstHab = new JLabel("Descripci\u00F3n:");
		GridBagConstraints gbc_lblDesEstHab = new GridBagConstraints();
		gbc_lblDesEstHab.insets = new Insets(0, 0, 0, 5);
		gbc_lblDesEstHab.gridx = 1;
		gbc_lblDesEstHab.gridy = 2;
		tabIngEstHab.add(lblDesEstHab, gbc_lblDesEstHab);
		
		txaDesEstHab = new JTextArea();
		GridBagConstraints gbc_txaDesEstHab = new GridBagConstraints();
		gbc_txaDesEstHab.fill = GridBagConstraints.BOTH;
		gbc_txaDesEstHab.gridx = 2;
		gbc_txaDesEstHab.gridy = 2;
		tabIngEstHab.add(txaDesEstHab, gbc_txaDesEstHab);
		
		JPanel tabLisEstHab = new JPanel();
		tabbedPane.addTab("Listar", null, tabLisEstHab, null);
		tabLisEstHab.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlBusEstHab = new JPanel();
		tabLisEstHab.add(pnlBusEstHab, BorderLayout.NORTH);
		
		JLabel lblBusPorEstHab = new JLabel("Buscar Nombre/Descripci\u00F3n:");
		pnlBusEstHab.add(lblBusPorEstHab);
		
		txtValBus = new JTextField();
		pnlBusEstHab.add(txtValBus);
		txtValBus.setColumns(10);
		
		JButton btnConEstHab = new JButton("Buscar");
		btnConEstHab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					EstadoHabitacionTrs admEstPro = new EstadoHabitacionTrs();
					
					List<String> columnas = new ArrayList<>();
					columnas.add("Id");
					columnas.add("Nombre");
					columnas.add("Descripción");
					List<EstadoHabitacion> filas = admEstPro.consultarPorNombreDescripcion(txtValBus.getText());

					myModeloEstHab = new TableModelEstadoHabitacion(columnas, filas);
					tabEstHab.setModel(myModeloEstHab);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			
			}
		});
		btnConEstHab.setIcon(new ImageIcon(IFrmEstadoHabitacion.class.getResource("/com/qdishemax/reservahotel/resource/imagenBuscar.png")));
		pnlBusEstHab.add(btnConEstHab);
		
		tabEstHab = new JTable();
		tabEstHab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabEstHab.setModel(myModeloEstHab);
		JScrollPane spEstHab = new JScrollPane(tabEstHab); //Se incluye a mano
		tabLisEstHab.add(spEstHab, BorderLayout.CENTER);
		
		//Implementar la selección de la tabla
		tabEstHab.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// Recuperar el modelo de Seleccion
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				// Recuperar el indice del registro seleccionado
				int indSel = lsm.getMaxSelectionIndex();
				// Verificar que se haya seleccionado un valor
				if (indSel >= 0) {
					TableModelEstadoHabitacion modelo = (TableModelEstadoHabitacion) tabEstHab.getModel();
					estHabSel = modelo.obtenerFilaSeleccionada(indSel);
					btnEdiEstHab.setEnabled(true);
					btnEliEstHab.setEnabled(true);
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

			List<EstadoHabitacion> filas = new ArrayList<EstadoHabitacion>();
			EstadoHabitacionTrs admEstHab = new EstadoHabitacionTrs();
			filas = admEstHab.consultarTodos();

			myModeloEstHab = new TableModelEstadoHabitacion(columnas, filas);
		} catch (Exception e) {

		}

	}

}
