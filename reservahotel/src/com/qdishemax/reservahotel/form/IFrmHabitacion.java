package com.qdishemax.reservahotel.form;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.qdishemax.reservahotel.form.util.ComboBoxModelPisoHabitacion;
import com.qdishemax.reservahotel.form.util.ComboBoxModelEstadoHabitacion;
import com.qdishemax.reservahotel.form.util.ComboBoxModelTipoHabitacion;
import com.qdishemax.reservahotel.form.util.TableModelHabitacion;
import com.qdishemax.reservahotel.modelo.Habitacion;
import com.qdishemax.reservahotel.modelo.PisoHabitacion;
import com.qdishemax.reservahotel.modelo.EstadoHabitacion;
import com.qdishemax.reservahotel.modelo.TipoHabitacion;
import com.qdishemax.reservahotel.negocio.HabitacionTrs;
import com.qdishemax.reservahotel.negocio.PisoHabitacionTrs;
import com.qdishemax.reservahotel.negocio.EstadoHabitacionTrs;
import com.qdishemax.reservahotel.negocio.TipoHabitacionTrs;

public class IFrmHabitacion extends JInternalFrame {
	private JTextField txtNumHab;
	private ComboBoxModelPisoHabitacion modeloComboPisHab;
	private ComboBoxModelTipoHabitacion modeloComboTipHab;
	private ComboBoxModelEstadoHabitacion modeloComboEstHab;
	private JTextArea txaDesHab;
	private JTextArea txaCarHab;
	private JFormattedTextField fxtPreHab;
	private PisoHabitacion pisHabSel;// Selección Combo
	private EstadoHabitacion estHabSel;// Selección Combo
	private TipoHabitacion tipHabSel;// Selección Combo
	private Habitacion habSel; // Selección Tabla
	private TableModelHabitacion myModeloHab;
	private JTable tabHab;
	private JTextField txtBusPorHab;
	private JTabbedPane tabbedPane;
	private JComboBox<PisoHabitacion> cmbPisHab;
	private JComboBox<EstadoHabitacion> cmbEstHab;
	private JComboBox<TipoHabitacion> cmbTipHab;
	private JButton btnEdiHab;
	private JButton btnEliHab;

	/**
	 * Create the frame.
	 */
	public IFrmHabitacion() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);

		inicializar();
		setTitle("::Administraci\u00F3n de Habitaciones::");
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setBounds(100, 100, 611, 550);

		JToolBar toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);

		JButton btnNueHab = new JButton("Nuevo");
		btnNueHab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumHab.setText("");
				txaDesHab.setText("");
				txaCarHab.setText("");
				fxtPreHab.setText("");

			}
		});
		btnNueHab.setIcon(new ImageIcon(
				IFrmHabitacion.class.getResource("/com/qdishemax/reservahotel/resource/imagenHabitacionNueva.png")));
		toolBar.add(btnNueHab);

		JButton btnGuaHab = new JButton("Guardar");
		btnGuaHab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 1.Verificar que no tenga campos nulos en base a la definición de la bdd
					if (txtNumHab.getText().equals("")) {
						JOptionPane.showMessageDialog(null, " Campo Número requerido", "Errores",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Habitacion habitacion = new Habitacion();
						habitacion.setNumeroHab(txtNumHab.getText());
						habitacion.setDescripcionHab(txaDesHab.getText());
						habitacion.setCaracteristicaHab(txaCarHab.getText());
						habitacion.setPrecioHab(new BigDecimal(fxtPreHab.getText()));
						/******************************************************
						 * Bloque para relacionar el objeto
						 ********************************************************/
						habitacion.setPisoHabitacion(pisHabSel);
						habitacion.setEstadoHabitacion(estHabSel);
						habitacion.setTipoHabitacion(tipHabSel);
						/********************************************************/

						HabitacionTrs admHabTrs = new HabitacionTrs();
						String mensaje = null;

						if (habSel == null) {// Guardar
							// 3. Llamar al controlador
							mensaje = admHabTrs.guardar(habitacion);
						} else {
							// 2.1 Setear el id para actualizar
							habitacion.setIdHab(habSel.getIdHab());
							mensaje = admHabTrs.actualizar(habitacion);
						}

						JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
						// 4. Limpiar el formulario
						txtNumHab.setText("");
						txaDesHab.setText("");
						txaCarHab.setText("");
						fxtPreHab.setText("");
						// 5. Encerar la selección y actualizar la tabla
						habSel = null; // Encero la selección
						pisHabSel = null;
						estHabSel = null;
						tipHabSel = null;
						inicializar(); // Actualizan el modelo
						tabHab.setModel(myModeloHab);// Actualizan el componente gráfico
						cmbPisHab.setModel(modeloComboPisHab);
						cmbEstHab.setModel(modeloComboEstHab);
						cmbTipHab.setModel(modeloComboTipHab);
						btnEdiHab.setEnabled(false);
						btnEliHab.setEnabled(false);

					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error al guardar: " + e1.getMessage(), "Errores",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnGuaHab.setIcon(new ImageIcon(
				IFrmHabitacion.class.getResource("/com/qdishemax/reservahotel/resource/imagenGuardar.png")));
		toolBar.add(btnGuaHab);

		btnEdiHab = new JButton("Editar");
		btnEdiHab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (habSel != null) {// Verificar que este seleccionado
					tabbedPane.setSelectedIndex(0);// Cambiar de tab
					txtNumHab.setText(habSel.getNumeroHab());
					txaDesHab.setText(habSel.getDescripcionHab());
					txaCarHab.setText(habSel.getCaracteristicaHab());
					fxtPreHab.setValue(habSel.getPrecioHab());
					modeloComboPisHab.setSelectedItem(habSel.getPisoHabitacion());
					cmbPisHab.setSelectedItem(modeloComboPisHab.getSelectedItem());
					modeloComboEstHab.setSelectedItem(habSel.getEstadoHabitacion());
					cmbEstHab.setSelectedItem(modeloComboEstHab.getSelectedItem());
					modeloComboTipHab.setSelectedItem(habSel.getTipoHabitacion());
					cmbTipHab.setSelectedItem(modeloComboTipHab.getSelectedItem());

				} else {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún registro", "Errores",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEdiHab.setIcon(new ImageIcon(
				IFrmHabitacion.class.getResource("/com/qdishemax/reservahotel/resource/iconoEditar.png")));
		btnEdiHab.setEnabled(false);
		toolBar.add(btnEdiHab);

		btnEliHab = new JButton("Eliminar");
		btnEliHab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (habSel != null) { // Esta seleccionado el registro
						int valCon = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el registro?",
								"Confirmación", JOptionPane.YES_NO_OPTION);
						if (valCon == 0) {
							HabitacionTrs admHab = new HabitacionTrs();
							String mensaje = admHab.eliminar(habSel);
							JOptionPane.showMessageDialog(null, mensaje, "Información",
									JOptionPane.INFORMATION_MESSAGE);
							habSel = null; // Encero la selección
							pisHabSel = null;
							estHabSel = null;
							tipHabSel = null;
							inicializar(); // Actualizan el modelo
							tabHab.setModel(myModeloHab);// Actualizan el componente gráfico
							btnEdiHab.setEnabled(false);
							btnEliHab.setEnabled(false);
						}

					} else {
						JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún registro ", "Errores",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error al eliminar", "Errores", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEliHab.setIcon(new ImageIcon(IFrmHabitacion.class
				.getResource("/com/qdishemax/reservahotel/resource/imagenHabitacionEliminarT.png")));
		btnEliHab.setEnabled(false);
		toolBar.add(btnEliHab);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel pnlIngHab = new JPanel();
		tabbedPane.addTab("Ingresar",
				new ImageIcon(
						IFrmHabitacion.class.getResource("/com/qdishemax/reservahotel/resource/iconoingresar3.png")),
				pnlIngHab, null);
		GridBagLayout gbl_pnlIngHab = new GridBagLayout();
		gbl_pnlIngHab.columnWidths = new int[] { 30, 75, 400, 0 };
		gbl_pnlIngHab.rowHeights = new int[] { 30, 20, 53, 51, 1, 44, 60, 20, 20, 20, 20, 0 };
		gbl_pnlIngHab.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_pnlIngHab.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		pnlIngHab.setLayout(gbl_pnlIngHab);

		JLabel lblNumHab = new JLabel("N\u00FAmero:");
		GridBagConstraints gbc_lblNumHab = new GridBagConstraints();
		gbc_lblNumHab.anchor = GridBagConstraints.EAST;
		gbc_lblNumHab.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumHab.gridx = 1;
		gbc_lblNumHab.gridy = 1;
		pnlIngHab.add(lblNumHab, gbc_lblNumHab);

		txtNumHab = new JTextField();
		GridBagConstraints gbc_txtNumHab = new GridBagConstraints();
		gbc_txtNumHab.anchor = GridBagConstraints.NORTHWEST;
		gbc_txtNumHab.insets = new Insets(0, 0, 5, 0);
		gbc_txtNumHab.gridx = 2;
		gbc_txtNumHab.gridy = 1;
		pnlIngHab.add(txtNumHab, gbc_txtNumHab);
		txtNumHab.setColumns(10);

		JLabel lblDesHab = new JLabel("Descripci\u00F3n:");
		GridBagConstraints gbc_lblDesHab = new GridBagConstraints();
		gbc_lblDesHab.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblDesHab.insets = new Insets(0, 0, 5, 5);
		gbc_lblDesHab.gridx = 1;
		gbc_lblDesHab.gridy = 2;
		pnlIngHab.add(lblDesHab, gbc_lblDesHab);

		txaDesHab = new JTextArea();
		GridBagConstraints gbc_txaDesHab = new GridBagConstraints();
		gbc_txaDesHab.fill = GridBagConstraints.BOTH;
		gbc_txaDesHab.insets = new Insets(0, 0, 5, 0);
		gbc_txaDesHab.gridheight = 3;
		gbc_txaDesHab.gridx = 2;
		gbc_txaDesHab.gridy = 2;
		pnlIngHab.add(txaDesHab, gbc_txaDesHab);

		txaCarHab = new JTextArea();
		GridBagConstraints gbc_txaCarHab = new GridBagConstraints();
		gbc_txaCarHab.fill = GridBagConstraints.BOTH;
		gbc_txaCarHab.insets = new Insets(0, 0, 5, 0);
		gbc_txaCarHab.gridheight = 3;
		gbc_txaCarHab.gridx = 2;
		gbc_txaCarHab.gridy = 4;
		pnlIngHab.add(txaCarHab, gbc_txaCarHab);

		JLabel lblCarHab = new JLabel("Caracteristicas:");
		GridBagConstraints gbc_lblCarHab = new GridBagConstraints();
		gbc_lblCarHab.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblCarHab.insets = new Insets(0, 0, 5, 5);
		gbc_lblCarHab.gridx = 1;
		gbc_lblCarHab.gridy = 6;
		pnlIngHab.add(lblCarHab, gbc_lblCarHab);

		JLabel lblPreHab = new JLabel("Precio:");
		GridBagConstraints gbc_lblPreHab = new GridBagConstraints();
		gbc_lblPreHab.anchor = GridBagConstraints.EAST;
		gbc_lblPreHab.insets = new Insets(0, 0, 5, 5);
		gbc_lblPreHab.gridx = 1;
		gbc_lblPreHab.gridy = 7;
		pnlIngHab.add(lblPreHab, gbc_lblPreHab);

		fxtPreHab = new JFormattedTextField();
		GridBagConstraints gbc_fxtPreHab = new GridBagConstraints();
		gbc_fxtPreHab.fill = GridBagConstraints.HORIZONTAL;
		gbc_fxtPreHab.anchor = GridBagConstraints.NORTH;
		gbc_fxtPreHab.insets = new Insets(0, 0, 5, 0);
		gbc_fxtPreHab.gridx = 2;
		gbc_fxtPreHab.gridy = 7;
		pnlIngHab.add(fxtPreHab, gbc_fxtPreHab);

		JLabel lblPisHab = new JLabel("Piso:");
		GridBagConstraints gbc_lblPisHab = new GridBagConstraints();
		gbc_lblPisHab.anchor = GridBagConstraints.EAST;
		gbc_lblPisHab.insets = new Insets(0, 0, 5, 5);
		gbc_lblPisHab.gridx = 1;
		gbc_lblPisHab.gridy = 8;
		pnlIngHab.add(lblPisHab, gbc_lblPisHab);

		cmbPisHab = new JComboBox<>();
		cmbPisHab.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				pisHabSel = (PisoHabitacion) modeloComboPisHab.getSelectedItem();
				System.out.println(pisHabSel);
			}
		});
		cmbPisHab.setModel(modeloComboPisHab);
		GridBagConstraints gbc_cmbPisHab = new GridBagConstraints();
		gbc_cmbPisHab.anchor = GridBagConstraints.NORTHWEST;
		gbc_cmbPisHab.insets = new Insets(0, 0, 5, 0);
		gbc_cmbPisHab.gridx = 2;
		gbc_cmbPisHab.gridy = 8;
		pnlIngHab.add(cmbPisHab, gbc_cmbPisHab);

		JLabel lblEstHab = new JLabel("Estado:");
		GridBagConstraints gbc_lblEstHab = new GridBagConstraints();
		gbc_lblEstHab.anchor = GridBagConstraints.EAST;
		gbc_lblEstHab.insets = new Insets(0, 0, 5, 5);
		gbc_lblEstHab.gridx = 1;
		gbc_lblEstHab.gridy = 9;
		pnlIngHab.add(lblEstHab, gbc_lblEstHab);

		cmbEstHab = new JComboBox<>();
		cmbEstHab.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				estHabSel = (EstadoHabitacion) modeloComboEstHab.getSelectedItem();
				System.out.println(estHabSel);
			}
		});
		cmbEstHab.setModel(modeloComboEstHab);
		GridBagConstraints gbc_cmbEstHab = new GridBagConstraints();
		gbc_cmbEstHab.anchor = GridBagConstraints.NORTHWEST;
		gbc_cmbEstHab.insets = new Insets(0, 0, 5, 0);
		gbc_cmbEstHab.gridx = 2;
		gbc_cmbEstHab.gridy = 9;
		pnlIngHab.add(cmbEstHab, gbc_cmbEstHab);

		JLabel lblTipHab = new JLabel("Tipo:");
		GridBagConstraints gbc_lblTipHab = new GridBagConstraints();
		gbc_lblTipHab.anchor = GridBagConstraints.EAST;
		gbc_lblTipHab.insets = new Insets(0, 0, 0, 5);
		gbc_lblTipHab.gridx = 1;
		gbc_lblTipHab.gridy = 10;
		pnlIngHab.add(lblTipHab, gbc_lblTipHab);

		cmbTipHab = new JComboBox<>();
		cmbTipHab.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				tipHabSel = (TipoHabitacion) modeloComboTipHab.getSelectedItem();
				System.out.println(tipHabSel);
			}
		});
		cmbTipHab.setModel(modeloComboTipHab);
		GridBagConstraints gbc_cmbTipHab = new GridBagConstraints();
		gbc_cmbTipHab.anchor = GridBagConstraints.NORTHWEST;
		gbc_cmbTipHab.gridx = 2;
		gbc_cmbTipHab.gridy = 10;
		pnlIngHab.add(cmbTipHab, gbc_cmbTipHab);

		JPanel pnlLisHab = new JPanel();
		tabbedPane.addTab("Listar",
				new ImageIcon(IFrmHabitacion.class.getResource("/com/qdishemax/reservahotel/resource/Lista.png")),
				pnlLisHab, null);
		pnlLisHab.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		pnlLisHab.add(panel, BorderLayout.NORTH);

		JLabel lblBusPorHab = new JLabel("N\u00FAmero/Piso:");
		panel.add(lblBusPorHab);

		txtBusPorHab = new JTextField();
		panel.add(txtBusPorHab);
		txtBusPorHab.setColumns(10);

		JButton btnBusHab = new JButton("Buscar");
		btnBusHab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					HabitacionTrs admHab = new HabitacionTrs();

					List<String> columnas = new ArrayList<>();
					columnas.add("Id");
					columnas.add("Número");
					columnas.add("Descripción");
					columnas.add("Caracteristicas");
					columnas.add("Precio");
					columnas.add("Piso Habitacion");
					columnas.add("Estado Habitacion");
					columnas.add("Tipo Habitacion");

					List<Habitacion> filas = admHab.consultarPorNombreDescripcion(txtBusPorHab.getText());

					myModeloHab = new TableModelHabitacion(columnas, filas);
					tabHab.setModel(myModeloHab);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		btnBusHab.setIcon(new ImageIcon(
				IFrmHabitacion.class.getResource("/com/qdishemax/reservahotel/resource/imagenBuscar.png")));
		panel.add(btnBusHab);

		tabHab = new JTable();
		tabHab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabHab.setModel(myModeloHab);
		JScrollPane spHab = new JScrollPane(tabHab);
		pnlLisHab.add(spHab, BorderLayout.CENTER);

		tabHab.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				int indSel = lsm.getMaxSelectionIndex();
				if (indSel >= 0) {
					TableModelHabitacion modelo = (TableModelHabitacion) tabHab.getModel();
					habSel = modelo.obtenerFilaSeleccionada(indSel);
					btnEdiHab.setEnabled(true);
					btnEliHab.setEnabled(true);
				}
			}
		});

	}

	private void inicializar() {
		try {
			
			pisHabSel = new PisoHabitacion();
			pisHabSel.setIdPisHab(-1);
			pisHabSel.setNombrePisHab("Seleccione un piso");
			
			List<PisoHabitacion> filasPisHab = new ArrayList<PisoHabitacion>();
			PisoHabitacionTrs adminPisHab = new PisoHabitacionTrs();
			filasPisHab = adminPisHab.consultarTodos();
			filasPisHab.add(pisHabSel);
			modeloComboPisHab = new ComboBoxModelPisoHabitacion(filasPisHab);
			
			estHabSel = new EstadoHabitacion();
			estHabSel.setIdEstHab(-1);
			estHabSel.setNombreEstHab("Seleccione el estado");

			List<EstadoHabitacion> filasEstHab = new ArrayList<EstadoHabitacion>();
			EstadoHabitacionTrs adminEstHab = new EstadoHabitacionTrs();
			filasEstHab = adminEstHab.consultarTodos();
			filasEstHab.add(estHabSel);
			modeloComboEstHab = new ComboBoxModelEstadoHabitacion(filasEstHab);
			
			tipHabSel = new TipoHabitacion();
			tipHabSel.setIdTipHab(-1);
			tipHabSel.setNombreTipHab("Seleccione el estado");


			List<TipoHabitacion> filasTipHab = new ArrayList<TipoHabitacion>();
			TipoHabitacionTrs admTipHab = new TipoHabitacionTrs();
			filasTipHab = admTipHab.consultarTodos();
			filasTipHab.add(tipHabSel);
			modeloComboTipHab = new ComboBoxModelTipoHabitacion(filasTipHab);

			// Inicializar la tabla
			List<String> columnas = new ArrayList<>();
			columnas.add("Id");
			columnas.add("Número");
			columnas.add("Descripción");
			columnas.add("Caracteristicas");
			columnas.add("Precio");
			columnas.add("Piso Habitacion");
			columnas.add("Estado Habitacion");
			columnas.add("Tipo Habitacion");

			List<Habitacion> filasTabla = new ArrayList<Habitacion>();
			HabitacionTrs admHab = new HabitacionTrs();
			filasTabla = admHab.consultarTodos();
			myModeloHab = new TableModelHabitacion(columnas, filasTabla);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se pudo inicializar las estructuras de datos", "Errores",
					JOptionPane.ERROR_MESSAGE);
		}

	}

}
