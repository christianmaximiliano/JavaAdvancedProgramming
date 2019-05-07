package com.qdishemax.reservahotel.form.util;

import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import com.qdishemax.reservahotel.modelo.TipoHabitacion;

/**
 * @author Maximiliano
 *
 */
public class ComboBoxModelTipoHabitacion implements ComboBoxModel<TipoHabitacion> {
	
	private List<TipoHabitacion> tipoHabitaciones;
	private int indice;
	
	

	/**
	 * @param tipoHabitaciones
	 */
	public ComboBoxModelTipoHabitacion(List<TipoHabitacion> tipoHabitaciones) {
		this.tipoHabitaciones = tipoHabitaciones;
	}

	@Override
	public int getSize() {
		return tipoHabitaciones.size();
	}

	@Override
	public TipoHabitacion getElementAt(int index) {
		indice = index;
		return tipoHabitaciones.get(index);
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
		if (tipoHabitaciones.contains(anItem)) {
			indice = tipoHabitaciones.indexOf(anItem);
		}
		
//		tipoHabitaciones.add((TipoHabitacion) anItem);
	}

	@Override
	public Object getSelectedItem() {
		return tipoHabitaciones.get(indice);
	}

}
