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

/**
 * @author Maximiliano
 *
 */
public class PisoHabitacionTrs implements ICrudC{

	@Override
	public String guardar(Object registro) throws Exception {
		String mensaje = null;

		// 1.Recuperar la Conexión
		try (Connection con = ConexionBdd.conectarBdd()) { //Cerrando automáticamente el recurso

			// 2.Establecer la operación: DDL o DML
			// Si colocan 0 en un campo autoincrement automáticamente coloca el valor
			String sqlInsPisHab = "INSERT INTO `piso_habitacion`\r\n" + 
					"(`id_pishab`,\r\n" + 
					"`nombre_pishab`,\r\n" + 
					"`descripcion_pishab`)\r\n" + 
					"VALUES\r\n" + 
					"(0,?,?);";

			// 3.Elegir el tipo de Objeto JDBC a utilizar
			try (PreparedStatement ptInspisHab = con.prepareStatement(sqlInsPisHab)){
				/*
				 * 4.Proceder a ejecutar la sentencia SQL a) execute -> DDL -> boolean b)
				 * executeUpdate -> DML excepto el Select -> Insert, Update, Delete -> int c)
				 * executeQuery -> Select -> ResultSet (Tabla de Información)
				 */
				// 4.1 Setear parámetros
				PisoHabitacion pisHab = (PisoHabitacion) registro;
				ptInspisHab.setString(1, pisHab.getNombrePisHab());
				ptInspisHab.setString(2, pisHab.getDescripcionPisHab());

				// 4.2 Ejecutar sentencia
				int numFilAfe = ptInspisHab.executeUpdate();

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

			String sqlUpdPisHab = "UPDATE `piso_habitacion`\r\n" + 
					"SET\r\n" + 
					"`nombre_pishab` = ?,\r\n" + 
					"`descripcion_pishab` = ?\r\n" + 
					"WHERE `id_pishab` = ?;";

			try (PreparedStatement ptUpdPisHab = con.prepareStatement(sqlUpdPisHab)){
				PisoHabitacion pisHab = (PisoHabitacion) registro;
				ptUpdPisHab.setString(1, pisHab.getNombrePisHab());
				ptUpdPisHab.setString(2, pisHab.getDescripcionPisHab());
				ptUpdPisHab.setInt(3, pisHab.getIdPisHab());

				int numFilAfe = ptUpdPisHab.executeUpdate();

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

			String sqlDelPisHab = "DELETE FROM `piso_habitacion`\r\n" + 
					"WHERE id_pishab = ?;";

			try (PreparedStatement ptDelPisHab = con.prepareStatement(sqlDelPisHab)){
				PisoHabitacion pisHab = (PisoHabitacion) registro;
				ptDelPisHab.setInt(1, pisHab.getIdPisHab());

				int numFilAfe = ptDelPisHab.executeUpdate();

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
	public List<PisoHabitacion> consultarTodos() throws Exception {
		List<PisoHabitacion> listaPisoHabitaciones = new ArrayList<>();
		try (Connection con = ConexionBdd.conectarBdd()){
			String sqlConPisPro = "SELECT * FROM piso_habitacion;";
			try (Statement stConPisPro = con.createStatement();
				 ResultSet rs = stConPisPro.executeQuery(sqlConPisPro);) {
				while (rs.next()) {
					PisoHabitacion pisHab = new PisoHabitacion();
					pisHab.setIdPisHab(rs.getInt(1));
					pisHab.setNombrePisHab(rs.getString(2));
					pisHab.setDescripcionPisHab(rs.getString(3));
					listaPisoHabitaciones.add(pisHab);
				}
			} catch (Exception e) {
				throw new Exception("Error al cerrar el pt");
			}
		} catch (Exception e) {
			throw new Exception("Error al cerrar la conexión");
		}

		return listaPisoHabitaciones;
	}

	public List<PisoHabitacion> consultarPorNombreDescripcion(String text) throws Exception {
		List<PisoHabitacion> listaPisoHabitaciones = new ArrayList<>();
		try(Connection con = ConexionBdd.conectarBdd()) {
			String sqlConTipHab = "SELECT * FROM piso_habitacion where nombre_pishab"
					+ " LIKE ? or descripcion_pishab LIKE ?;";
			try (PreparedStatement stConPisHab = con.prepareStatement(sqlConTipHab);){
				stConPisHab.setString(1, "%" + text + "%");
				stConPisHab.setString(2, "%" + text + "%");
				try {
					ResultSet rs = stConPisHab.executeQuery();

					while (rs.next()) {
						PisoHabitacion pisHab = new PisoHabitacion();
						pisHab.setIdPisHab(rs.getInt(1));
						pisHab.setNombrePisHab(rs.getString(2));
						pisHab.setDescripcionPisHab(rs.getString(3));
						listaPisoHabitaciones.add(pisHab);
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

		return listaPisoHabitaciones;
	}

	public PisoHabitacion consultarPorId(int idPisHab) throws Exception {
		PisoHabitacion pisHab = null;
		try(Connection con = ConexionBdd.conectarBdd()) {
			String sqlConPisHab = "SELECT * FROM `piso_habitacion` WHERE `id_pishab` = ?;";
			try (PreparedStatement stConPisHab = con.prepareStatement(sqlConPisHab);){
				stConPisHab.setInt(1,  idPisHab);
				try {
					ResultSet rs = stConPisHab.executeQuery();

					if (rs.next()) {
						pisHab = new PisoHabitacion();
						pisHab.setIdPisHab(rs.getInt(1));
						pisHab.setNombrePisHab(rs.getString(2));
						pisHab.setDescripcionPisHab(rs.getString(3));
						
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
		return pisHab;
	}


	
}
