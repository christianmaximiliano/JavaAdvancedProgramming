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
import com.qdishemax.reservahotel.modelo.EstadoHabitacion;
import com.qdishemax.reservahotel.modelo.PisoHabitacion;
import com.qdishemax.reservahotel.modelo.TipoHabitacion;

/**
 * @author Maximiliano
 *
 */
public class EstadoHabitacionTrs implements ICrudC{

	@Override
	public String guardar(Object registro) throws Exception {
		String mensaje = null;

		// 1.Recuperar la Conexión
		try (Connection con = ConexionBdd.conectarBdd()) { //Cerrando automáticamente el recurso

			// 2.Establecer la operación: DDL o DML
			// Si colocan 0 en un campo autoincrement automáticamente coloca el valor
			String sqlInsEstHab = "INSERT INTO `estado_habitacion`\r\n" + 
					"(`id_esthab`,\r\n" + 
					"`nombre_esthab`,\r\n" + 
					"`descripcion_esthab`)\r\n" + 
					"VALUES\r\n" + 
					"(0,?,?);";

			// 3.Elegir el tipo de Objeto JDBC a utilizar
			try (PreparedStatement ptInsestHab = con.prepareStatement(sqlInsEstHab)){
				/*
				 * 4.Proceder a ejecutar la sentencia SQL a) execute -> DDL -> boolean b)
				 * executeUpdate -> DML excepto el Select -> Insert, Update, Delete -> int c)
				 * executeQuery -> Select -> ResultSet (Tabla de Información)
				 */
				// 4.1 Setear parámetros
				EstadoHabitacion estHab = (EstadoHabitacion) registro;
				ptInsestHab.setString(1, estHab.getNombreEstHab());
				ptInsestHab.setString(2, estHab.getDescripcionEstHab());

				// 4.2 Ejecutar sentencia
				int numFilAfe = ptInsestHab.executeUpdate();

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

			String sqlUpdEstHab = " UPDATE `estado_habitacion`\r\n" + 
					"SET\r\n" + 
					"`nombre_esthab` = ?,\r\n" + 
					"`descripcion_esthab` = ?\r\n" + 
					"WHERE `id_esthab` = ?;";

			try (PreparedStatement ptUpdEstHab = con.prepareStatement(sqlUpdEstHab)){
				EstadoHabitacion estHab = (EstadoHabitacion) registro;
				ptUpdEstHab.setString(1, estHab.getNombreEstHab());
				ptUpdEstHab.setString(2, estHab.getDescripcionEstHab());
				ptUpdEstHab.setInt(3, estHab.getIdEstHab());

				int numFilAfe = ptUpdEstHab.executeUpdate();

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

			String sqlDelEstHab = "DELETE FROM `estado_habitacion`\r\n" + 
					"WHERE id_esthab = ?;";

			try (PreparedStatement ptDelEstHab = con.prepareStatement(sqlDelEstHab)){
				EstadoHabitacion estHab = (EstadoHabitacion) registro;
				ptDelEstHab.setInt(1, estHab.getIdEstHab());

				int numFilAfe = ptDelEstHab.executeUpdate();

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
	public List<EstadoHabitacion> consultarTodos() throws Exception {
		List<EstadoHabitacion> listaEstadoHabitaciones = new ArrayList<>();
		try (Connection con = ConexionBdd.conectarBdd()){
			String sqlConEstPro = "SELECT * FROM estado_habitacion;";
			try (Statement stConEstPro = con.createStatement();
				 ResultSet rs = stConEstPro.executeQuery(sqlConEstPro);) {
				while (rs.next()) {
					EstadoHabitacion estHab = new EstadoHabitacion();
					estHab.setIdEstHab(rs.getInt(1));
					estHab.setNombreEstHab(rs.getString(2));
					estHab.setDescripcionEstHab(rs.getString(3));
					listaEstadoHabitaciones.add(estHab);
				}
			} catch (Exception e) {
				throw new Exception("Error al cerrar el pt");
			}
		} catch (Exception e) {
			throw new Exception("Error al cerrar la conexión");
		}

		return listaEstadoHabitaciones;
	}

	public List<EstadoHabitacion> consultarPorNombreDescripcion(String text) throws Exception {
		List<EstadoHabitacion> listaEstadoHabitaciones = new ArrayList<>();
		try(Connection con = ConexionBdd.conectarBdd()) {
			String sqlConEstHab = "SELECT * FROM `estado_habitacion` where nombre_esthab"
					+ " LIKE ? or descripcion_esthab LIKE ?;";
			try (PreparedStatement stConEstHab = con.prepareStatement(sqlConEstHab);){
				stConEstHab.setString(1, "%" + text + "%");
				stConEstHab.setString(2, "%" + text + "%");
				try {
					ResultSet rs = stConEstHab.executeQuery();

					while (rs.next()) {
						EstadoHabitacion estHab = new EstadoHabitacion();
						estHab.setIdEstHab(rs.getInt(1));
						estHab.setNombreEstHab(rs.getString(2));
						estHab.setDescripcionEstHab(rs.getString(3));
						listaEstadoHabitaciones.add(estHab);
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

		return listaEstadoHabitaciones;
	}

	public EstadoHabitacion consultaPorId(int idEstHab) throws Exception {
		EstadoHabitacion estHab = null;
		try(Connection con = ConexionBdd.conectarBdd()) {
			String sqlConEstHab = "SELECT * FROM `estado_habitacion` WHERE `id_esthab` = ?;";
			try (PreparedStatement stConEstHab = con.prepareStatement(sqlConEstHab);){
				stConEstHab.setInt(1,  idEstHab);
				try {
					ResultSet rs = stConEstHab.executeQuery();

					if (rs.next()) {
						estHab = new EstadoHabitacion();
						estHab.setIdEstHab(rs.getInt(1));
						estHab.setNombreEstHab(rs.getString(2));
						estHab.setDescripcionEstHab(rs.getString(3));
						
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
		return estHab;
	}

	
}
