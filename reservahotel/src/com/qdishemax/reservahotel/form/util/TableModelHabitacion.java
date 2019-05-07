package com.qdishemax.reservahotel.form.util;

import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.qdishemax.reservahotel.modelo.Habitacion;

/**
 * Clase que representa el componente modelo que se encargará de gestionar
 * los datos de una fuente de información para mostrarlo en la tabla
 * @author Maximiliano
 *
 */

public class TableModelHabitacion implements TableModel {
	
	private List<String> columnas;
	private List<Habitacion> filas;
		
	
	/**
	 * Constructor para crear un modelo con las columnas y filas
	 * @param columnas
	 * @param filas
	 */
	public TableModelHabitacion(List<String> columnas, List<Habitacion> filas) {
		this.columnas = columnas;
		this.filas = filas;
	}

	@Override
	public int getRowCount() {
		return filas.size();
	}

	@Override
	public int getColumnCount() {
		return columnas.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnas.get(columnIndex);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		//Recuperando un registro de la bdd
		Habitacion hab = filas.get(rowIndex);
		if(columnIndex == 0) {
			return hab.getIdHab();
		}else if(columnIndex == 1) {
			return hab.getNumeroHab();
		}else if(columnIndex == 2){
			return hab.getDescripcionHab();
		}else if(columnIndex == 3){
			return hab.getCaracteristicaHab();
		}else if(columnIndex == 4){
			return hab.getPrecioHab();
		}else if(columnIndex == 5){
			return hab.getPisoHabitacion();
		}else if(columnIndex == 6){
			return hab.getEstadoHabitacion();
		}else {
			return hab.getTipoHabitacion();
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		filas.set(rowIndex, (Habitacion) aValue);
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Método para recuperar el registro seleccionado
	 * @param indSel
	 * @return
	 */
	public Habitacion obtenerFilaSeleccionada(int indSel) {
		return filas.get(indSel);
	}

}
