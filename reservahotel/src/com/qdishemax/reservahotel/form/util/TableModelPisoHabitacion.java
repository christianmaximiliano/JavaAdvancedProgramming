/**
 * 
 */
package com.qdishemax.reservahotel.form.util;

import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.qdishemax.reservahotel.modelo.PisoHabitacion;

/**
 * Clase que representa el componente modelo que se encargará de gestionar
 * los datos de una fuente de información para mostrarlo en la tabla
 * @author Maximiliano
 *
 */
public class TableModelPisoHabitacion implements TableModel{
	
	private List<String> columnas;
	private List<PisoHabitacion> filas;
	
	

	/**
	 * Constructor para crear un modelo con las columnas y filas
	 * @param columnas
	 * @param filas
	 */
	public TableModelPisoHabitacion(List<String> columnas, List<PisoHabitacion> filas) {
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
		PisoHabitacion tipHab = filas.get(rowIndex);
		if(columnIndex == 0) {
			return tipHab.getIdPisHab();
		}else if(columnIndex == 1) {
			return tipHab.getNombrePisHab();
		}else {
			return tipHab.getDescripcionPisHab();
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		filas.set(rowIndex, (PisoHabitacion) aValue);
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
	public PisoHabitacion obtenerFilaSeleccionada(int indSel) {
		return filas.get(indSel);
	}


}
