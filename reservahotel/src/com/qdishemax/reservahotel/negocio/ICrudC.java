/**
 * 
 */
package com.qdishemax.reservahotel.negocio;

import java.util.List;

/**
 * Interface para definir los métodos CRUD utilizando Collections
 * @author Maximiliano
 * 
 */
public interface ICrudC {

	/**
	 * Definición de método para guardar
	 * @param registro
	 * @return
	 */
	public String guardar(Object registro) throws Exception;
	/**
	 * Definición de método para actualizar
	 * @param registro
	 * @return
	 */
	public String actualizar(Object registro) throws Exception;
	
	/**
	 * Definición de método para eliminar
	 * @param registro
	 * @return
	 */
	public String eliminar(Object registro) throws Exception;
	/**
	 * Definición de método para consultar
	 * @return
	 */
	/*
	 * Con el wildcard ? se devuelve cualquier lista de objetos de algún tipo
	 */
	public List<?> consultarTodos() throws Exception;
}
