/**
 * 
 */
package com.qdishemax.reservahotel.modelo;

import java.util.List;

/**
 * @author Maximiliano
 *
 */
public class EstadoHabitacion {
	private int idEstHab;
	private String nombreEstHab;
	private String descripcionEstHab;
	// Relación de Muchos - N
	private List<Habitacion> habitaciones;
	
	public EstadoHabitacion() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the idEstHab
	 */
	public int getIdEstHab() {
		return idEstHab;
	}

	/**
	 * @param idEstHab the idEstHab to set
	 */
	public void setIdEstHab(int idEstHab) {
		this.idEstHab = idEstHab;
	}

	/**
	 * @return the nombreEstHab
	 */
	public String getNombreEstHab() {
		return nombreEstHab;
	}

	/**
	 * @param nombreEstHab the nombreEstHab to set
	 */
	public void setNombreEstHab(String nombreEstHab) {
		this.nombreEstHab = nombreEstHab;
	}

	/**
	 * @return the descripcionEstHab
	 */
	public String getDescripcionEstHab() {
		return descripcionEstHab;
	}

	/**
	 * @param descripcionEstHab the descripcionEstHab to set
	 */
	public void setDescripcionEstHab(String descripcionEstHab) {
		this.descripcionEstHab = descripcionEstHab;
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
		result = prime * result + idEstHab;
		result = prime * result + ((nombreEstHab == null) ? 0 : nombreEstHab.hashCode());
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
		EstadoHabitacion other = (EstadoHabitacion) obj;
		if (idEstHab != other.idEstHab)
			return false;
		if (nombreEstHab == null) {
			if (other.nombreEstHab != null)
				return false;
		} else if (!nombreEstHab.equals(other.nombreEstHab))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return nombreEstHab;
	}
	
	
		
}
