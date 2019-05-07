/**
 * 
 */
package com.qdishemax.reservahotel.modelo;

import java.util.List;

/**
 * @author Maximiliano
 *
 */
public class PisoHabitacion {
	private int idPisHab;
	private String nombrePisHab;
	private String descripcionPisHab;
	// Relación de Muchos - N
	private List<Habitacion> habitaciones;

	public PisoHabitacion() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the idPisHab
	 */
	public int getIdPisHab() {
		return idPisHab;
	}

	/**
	 * @param idPisHab the idPisHab to set
	 */
	public void setIdPisHab(int idPisHab) {
		this.idPisHab = idPisHab;
	}

	/**
	 * @return the nombrePisHab
	 */
	public String getNombrePisHab() {
		return nombrePisHab;
	}

	/**
	 * @param nombrePisHab the nombrePisHab to set
	 */
	public void setNombrePisHab(String nombrePisHab) {
		this.nombrePisHab = nombrePisHab;
	}

	/**
	 * @return the descripcionPisHab
	 */
	public String getDescripcionPisHab() {
		return descripcionPisHab;
	}

	/**
	 * @param descripcionPisHab the descripcionPisHab to set
	 */
	public void setDescripcionPisHab(String descripcionPisHab) {
		this.descripcionPisHab = descripcionPisHab;
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
		result = prime * result + idPisHab;
		result = prime * result + ((nombrePisHab == null) ? 0 : nombrePisHab.hashCode());
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
		PisoHabitacion other = (PisoHabitacion) obj;
		if (idPisHab != other.idPisHab)
			return false;
		if (nombrePisHab == null) {
			if (other.nombrePisHab != null)
				return false;
		} else if (!nombrePisHab.equals(other.nombrePisHab))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return nombrePisHab;
	}
	
	
	
}
