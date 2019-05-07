/**
 * 
 */
package com.qdishemax.reservahotel.modelo;

import java.util.List;

/**
 * @author Maximiliano
 *
 */
public class TipoHabitacion {
	private int idTipHab;
	private String nombreTipHab;
	private String descripcionTipHab;
	// Relación de Muchos - N
	private List<Habitacion> habitaciones;

	public TipoHabitacion() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the idTipHab
	 */
	public int getIdTipHab() {
		return idTipHab;
	}

	/**
	 * @param idTipHab the idTipHab to set
	 */
	public void setIdTipHab(int idTipHab) {
		this.idTipHab = idTipHab;
	}

	/**
	 * @return the nombreTipHab
	 */
	public String getNombreTipHab() {
		return nombreTipHab;
	}

	/**
	 * @param nombreTipHab the nombreTipHab to set
	 */
	public void setNombreTipHab(String nombreTipHab) {
		this.nombreTipHab = nombreTipHab;
	}

	/**
	 * @return the descripcionTipHab
	 */
	public String getDescripcionTipHab() {
		return descripcionTipHab;
	}

	/**
	 * @param descripcionTipHab the descripcionTipHab to set
	 */
	public void setDescripcionTipHab(String descripcionTipHab) {
		this.descripcionTipHab = descripcionTipHab;
	}

	/**
	 * @return the habitaciones
	 */
	public List<Habitacion> getHabitaciones() {
		return habitaciones;
	}

	/**
	 * @param habitaciones the habitaciones to set
	 */
	public void setHabitaciones(List<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTipHab;
		result = prime * result + ((nombreTipHab == null) ? 0 : nombreTipHab.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoHabitacion other = (TipoHabitacion) obj;
		if (idTipHab != other.idTipHab)
			return false;
		if (nombreTipHab == null) {
			if (other.nombreTipHab != null)
				return false;
		} else if (!nombreTipHab.equals(other.nombreTipHab))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return nombreTipHab;
	}
	
	
}
