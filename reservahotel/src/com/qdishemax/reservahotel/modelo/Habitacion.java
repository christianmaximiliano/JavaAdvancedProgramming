package com.qdishemax.reservahotel.modelo;

import java.math.BigDecimal;

/**
 *  Clase que representa la tabla de habitacion
 * @author Maximiliano
 *
 */
public class Habitacion {
	private int idHab;
	private String numeroHab;
	private String descripcionHab;
	private String caracteristicaHab;
	private BigDecimal precioHab;
	// Se asocia con un atributo (Relación 1)
	private PisoHabitacion pisoHabitacion;
	private EstadoHabitacion estadoHabitacion;
	private TipoHabitacion tipoHabitacion;
	
	public Habitacion() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @param idHab
	 * @param numeroHab
	 * @param descripcionHab
	 * @param caracteristicaHab
	 * @param precioHab
	 * @param pisoHabitacion
	 * @param estadoHabitacion
	 * @param tipoHabitacion
	 */
	public Habitacion(int idHab, String numeroHab, String descripcionHab, String caracteristicaHab,
			BigDecimal precioHab, PisoHabitacion pisoHabitacion, EstadoHabitacion estadoHabitacion,
			TipoHabitacion tipoHabitacion) {
		super();
		this.idHab = idHab;
		this.numeroHab = numeroHab;
		this.descripcionHab = descripcionHab;
		this.caracteristicaHab = caracteristicaHab;
		this.precioHab = precioHab;
		this.pisoHabitacion = pisoHabitacion;
		this.estadoHabitacion = estadoHabitacion;
		this.tipoHabitacion = tipoHabitacion;
	}


	/**
	 * @return the idHab
	 */
	public int getIdHab() {
		return idHab;
	}


	/**
	 * @param idHab the idHab to set
	 */
	public void setIdHab(int idHab) {
		this.idHab = idHab;
	}


	/**
	 * @return the numeroHab
	 */
	public String getNumeroHab() {
		return numeroHab;
	}


	/**
	 * @param numeroHab the numeroHab to set
	 */
	public void setNumeroHab(String numeroHab) {
		this.numeroHab = numeroHab;
	}


	/**
	 * @return the descripcionHab
	 */
	public String getDescripcionHab() {
		return descripcionHab;
	}


	/**
	 * @param descripcionHab the descripcionHab to set
	 */
	public void setDescripcionHab(String descripcionHab) {
		this.descripcionHab = descripcionHab;
	}


	/**
	 * @return the caracteristicaHab
	 */
	public String getCaracteristicaHab() {
		return caracteristicaHab;
	}


	/**
	 * @param caracteristicaHab the caracteristicaHab to set
	 */
	public void setCaracteristicaHab(String caracteristicaHab) {
		this.caracteristicaHab = caracteristicaHab;
	}


	/**
	 * @return the precioHab
	 */
	public BigDecimal getPrecioHab() {
		return precioHab;
	}


	/**
	 * @param string the precioHab to set
	 */
	public void setPrecioHab(BigDecimal string) {
		this.precioHab = string;
	}


	/**
	 * @return the pisoHabitacion
	 */
	public PisoHabitacion getPisoHabitacion() {
		return pisoHabitacion;
	}


	/**
	 * @param pisoHabitacion the pisoHabitacion to set
	 */
	public void setPisoHabitacion(PisoHabitacion pisoHabitacion) {
		this.pisoHabitacion = pisoHabitacion;
	}


	/**
	 * @return the estadoHabitacion
	 */
	public EstadoHabitacion getEstadoHabitacion() {
		return estadoHabitacion;
	}


	/**
	 * @param estadoHabitacion the estadoHabitacion to set
	 */
	public void setEstadoHabitacion(EstadoHabitacion estadoHabitacion) {
		this.estadoHabitacion = estadoHabitacion;
	}


	/**
	 * @return the tipoHabitacion
	 */
	public TipoHabitacion getTipoHabitacion() {
		return tipoHabitacion;
	}


	/**
	 * @param tipoHabitacion the tipoHabitacion to set
	 */
	public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idHab;
		result = prime * result + ((numeroHab == null) ? 0 : numeroHab.hashCode());
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
		Habitacion other = (Habitacion) obj;
		if (idHab != other.idHab)
			return false;
		if (numeroHab == null) {
			if (other.numeroHab != null)
				return false;
		} else if (!numeroHab.equals(other.numeroHab))
			return false;
		return true;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Habitacion [idHab=" + idHab + ", numeroHab=" + numeroHab + ", descripcionHab=" + descripcionHab
				+ ", caracteristicaHab=" + caracteristicaHab + ", precioHab=" + precioHab + ", getPisoHabitacion()="
				+ getPisoHabitacion().getNombrePisHab() + ", getEstadoHabitacion()=" + getEstadoHabitacion().getNombreEstHab() + ", getTipoHabitacion()="
				+ getTipoHabitacion().getNombreTipHab() + "]";
	}





	
}
