package com.qdishemax.reservahotel.form.util;

import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import com.qdishemax.reservahotel.modelo.PisoHabitacion;

/**
 * Clase que representa el modelo para administrar los valores del combo box
 * pisohabitacion
 * @author Maximiliano
 *
 */
public class ComboBoxModelPisoHabitacion implements ComboBoxModel<PisoHabitacion> {

	private List<PisoHabitacion> pisoHabitaciones;
	private int indice;

	/**
	 * @param pisoHabitaciones
	 */
	public ComboBoxModelPisoHabitacion(List<PisoHabitacion> pisoHabitaciones) {
		this.pisoHabitaciones = pisoHabitaciones;
	}


	@Override
	public int getSize() {
		return pisoHabitaciones.size();
	}


	@Override
	public PisoHabitacion getElementAt(int index) {
		indice = index;
		return pisoHabitaciones.get(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSelectedItem(Object anItem) {
		if (pisoHabitaciones.contains(anItem)) {
			indice = pisoHabitaciones.indexOf(anItem);
		}
//		pisoHabitaciones.add((PisoHabitacion) anItem);
	}

	@Override
	public Object getSelectedItem() {
		return pisoHabitaciones.get(indice);
	}
}
