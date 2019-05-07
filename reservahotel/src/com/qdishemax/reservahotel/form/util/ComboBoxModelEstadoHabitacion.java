package com.qdishemax.reservahotel.form.util;

import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import com.qdishemax.reservahotel.modelo.EstadoHabitacion;

/**
 * @author Maximiliano
 *
 */
public class ComboBoxModelEstadoHabitacion implements ComboBoxModel<EstadoHabitacion> {

	private List<EstadoHabitacion> estadoHabitaciones;
	private int indice;

	/**
	 * @param estadoHabitaciones
	 */
	public ComboBoxModelEstadoHabitacion(List<EstadoHabitacion> estadoHabitaciones) {
		this.estadoHabitaciones = estadoHabitaciones;
	}
	
	@Override
	public int getSize() {
		return estadoHabitaciones.size();
	}


	@Override
	public EstadoHabitacion getElementAt(int index) {
		indice =index;
		return estadoHabitaciones.get(index);
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
		if (estadoHabitaciones.contains(anItem)) {
			indice = estadoHabitaciones.indexOf(anItem);
		}
//		estadoHabitaciones.add((EstadoHabitacion) anItem);
	}

	@Override
	public Object getSelectedItem() {
		return estadoHabitaciones.get(indice);
	}
}
