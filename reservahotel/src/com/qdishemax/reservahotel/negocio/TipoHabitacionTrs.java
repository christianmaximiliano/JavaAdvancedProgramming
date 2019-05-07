/**
 * 
 */
package com.qdishemax.reservahotel.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qdishemax.reservahotel.modelo.ConexionBdd;
import com.qdishemax.reservahotel.modelo.PisoHabitacion;
import com.qdishemax.reservahotel.modelo.TipoHabitacion;

/**
 * @author Maximiliano
 *
 */
public class TipoHabitacionTrs implements ICrudC{

	@Override
	public String guardar(Object registro) throws Exception {
		String mensaje = null;

		// 1.Recuperar la Conexión
		try (Connection con = ConexionBdd.conectarBdd()) { //Cerrando automáticamente el recurso

			// 2.Establecer la operación: DDL o DML
			// Si colocan 0 en un campo autoincrement automáticamente coloca el valor
			String sqlInsTipHab = "INSERT INTO `tipo_habitacion`\r\n" + 
					"(`id_tiphab`,`nombre_tiphab`,`descripcion_tiphab`)\r\n" + 
					"VALUES\r\n" + 
					"(0,?,?);";

			// 3.Elegir el tipo de Objeto JDBC a utilizar
			try (PreparedStatement ptInstipHab = con.prepareStatement(sqlInsTipHab)){
				/*
				 * 4.Proceder a ejecutar la sentencia SQL a) execute -> DDL -> boolean b)
				 * executeUpdate -> DML excepto el Select -> Insert, Update, Delete -> int c)
				 * executeQuery -> Select -> ResultSet (Tabla de Información)
				 */
				// 4.1 Setear parámetros
				TipoHabitacion tipHab = (TipoHabitacion) registro;
				ptInstipHab.setString(1, tipHab.getNombreTipHab());
				ptInstipHab.setString(2, tipHab.getDescripcionTipHab());

				// 4.2 Ejecutar sentencia
				int numFilAfe = ptInstipHab.executeUpdate();

				/*
				 * 5.Procesar la información, tabla se convierte en un puntero a) Varios
				 * registros -> while b) Un solo -> if
				 */
				if (numFilAfe > 0) {
					mensaje = "Registro guardado correctamente";
				}
			} catch (Exception e) {
				throw new Exception("Error al cerrar el pt");
			}
		} catch (Exception e) {
			throw new Exception("Error al cerrar la conexión");
		}
		return mensaje;
	}

	@Override
	public String actualizar(Object registro) throws Exception {
		String mensaje = null;

		try (Connection con = ConexionBdd.conectarBdd()) { 

			String sqlUpdTipHab = "UPDATE `tipo_habitacion`\r\n" + 
					"SET\r\n" + 
					"`nombre_tiphab` = ?,\r\n" + 
					"`descripcion_tiphab` = ?\r\n" + 
					"WHERE `id_tiphab` = ?;";

			try (PreparedStatement ptUpdTipHab = con.prepareStatement(sqlUpdTipHab)){
				TipoHabitacion tipHab = (TipoHabitacion) registro;
				ptUpdTipHab.setString(1, tipHab.getNombreTipHab());
				ptUpdTipHab.setString(2, tipHab.getDescripcionTipHab());
				ptUpdTipHab.setInt(3, tipHab.getIdTipHab());

				int numFilAfe = ptUpdTipHab.executeUpdate();

				if (numFilAfe > 0) {
					mensaje = "Registro actualizado correctamente";
				}
			} catch (Exception e) {
				throw new Exception("Error al cerrar el pt");
			}
		} catch (Exception e) {
			throw new Exception("Error al cerrar la conexión");
		}
		return mensaje;
	}

	@Override
	public String eliminar(Object registro) throws Exception {
		String mensaje = null;

		try (Connection con = ConexionBdd.conectarBdd()) { 

			String sqlDelTipHab = "DELETE FROM `tipo_habitacion`\r\n" + 
					"WHERE id_tiphab = ?;";

			try (PreparedStatement ptDelTipHab = con.prepareStatement(sqlDelTipHab)){
				TipoHabitacion tipHab = (TipoHabitacion) registro;
				ptDelTipHab.setInt(1, tipHab.getIdTipHab());

				int numFilAfe = ptDelTipHab.executeUpdate();

				if (numFilAfe > 0) {
					mensaje = "Registro eliminado correctamente";
				}
			} catch (Exception e) {
				throw new Exception("Error al cerrar el pt");
			}
		} catch (Exception e) {
			throw new Exception("Error al cerrar la conexión");
		}
		return mensaje;
	}

	@Override
	public List<TipoHabitacion> consultarTodos() throws Exception {
		List<TipoHabitacion> listaTipoHabitaciones = new ArrayList<>();
		try (Connection con = ConexionBdd.conectarBdd()){
			String sqlConTipPro = "SELECT * FROM tipo_habitacion;";
			try (Statement stConTipPro = con.createStatement();
				 ResultSet rs = stConTipPro.executeQuery(sqlConTipPro);) {
				while (rs.next()) {
					TipoHabitacion tipHab = new TipoHabitacion();
					tipHab.setIdTipHab(rs.getInt(1));
					tipHab.setNombreTipHab(rs.getString(2));
					tipHab.setDescripcionTipHab(rs.getString(3));
					listaTipoHabitaciones.add(tipHab);
				}
			} catch (Exception e) {
				throw new Exception("Error al cerrar el pt");
			}
		} catch (Exception e) {
			throw new Exception("Error al cerrar la conexión");
		}

		return listaTipoHabitaciones;
	}

	public List<TipoHabitacion> consultarPorNombreDescripcion(String text) throws Exception {
		List<TipoHabitacion> listaTipoHabitaciones = new ArrayList<>();
		try(Connection con = ConexionBdd.conectarBdd()) {
			String sqlConTipHab = "SELECT * FROM tipo_habitacion where nombre_tiphab"
					+ " LIKE ? or descripcion_tiphab LIKE ?;";
			try (PreparedStatement stConTipHab = con.prepareStatement(sqlConTipHab);){
				stConTipHab.setString(1, "%" + text + "%");
				stConTipHab.setString(2, "%" + text + "%");
				try {
					ResultSet rs = stConTipHab.executeQuery();

					while (rs.next()) {
						TipoHabitacion tipHab = new TipoHabitacion();
						tipHab.setIdTipHab(rs.getInt(1));
						tipHab.setNombreTipHab(rs.getString(2));
						tipHab.setDescripcionTipHab(rs.getString(3));
						listaTipoHabitaciones.add(tipHab);
					}
				} catch (Exception e) {
					throw new Exception("Error al cerrar el rs");
				}
			} catch (Exception e) {
				throw new Exception("Error al cerrar el pt");
			}
		} catch (Exception e) {
			throw new Exception("Error al cerrar la conexión");
		}

		return listaTipoHabitaciones;
	}

	public TipoHabitacion consultaPorId(int idTipHab) throws Exception {
		TipoHabitacion tipHab = null;
		try(Connection con = ConexionBdd.conectarBdd()) {
			String sqlConTipHab = "SELECT * FROM `tipo_habitacion` WHERE `id_tiphab` = ?;";
			try (PreparedStatement stConTipHab = con.prepareStatement(sqlConTipHab);){
				stConTipHab.setInt(1,  idTipHab);
				try {
					ResultSet rs = stConTipHab.executeQuery();

					if (rs.next()) {
						tipHab = new TipoHabitacion();
						tipHab.setIdTipHab(rs.getInt(1));
						tipHab.setNombreTipHab(rs.getString(2));
						tipHab.setDescripcionTipHab(rs.getString(3));
						
					}
				} catch (Exception e) {
					throw new Exception("Error al cerrar el rs");
				}
			} catch (Exception e) {
				throw new Exception("Error al cerrar el pt");
			}
		} catch (Exception e) {
			throw new Exception("Error al cerrar la conexión");
		}
		return tipHab;
	}
	
}
