package com.qdishemax.reservahotel.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qdishemax.reservahotel.modelo.ConexionBdd;
import com.qdishemax.reservahotel.modelo.EstadoHabitacion;
import com.qdishemax.reservahotel.modelo.Habitacion;
import com.qdishemax.reservahotel.modelo.PisoHabitacion;
import com.qdishemax.reservahotel.modelo.TipoHabitacion;
import com.qdishemax.reservahotel.modelo.Usuario;

/**
 * Clase que administra las operaciones relacionadas con habitacion
 * @author Maximiliano
 *
 */
public class HabitacionTrs implements ICrudC{

	@Override
	public String guardar(Object registro) throws Exception {
		String mensaje = null;
		//1.Recuperar la Conexión
		try (Connection con = ConexionBdd.conectarBdd()){  //Cerrando automáticamente el recurso
		//2.Establecer la operación: DDL o DML
		//Si colocan 0 en un campo autoincrement automáticamente coloca el valor
		String sqlInsHab = "INSERT INTO `habitacion`\r\n" + 
				"(`id_hab`,\r\n" + 
				"`numero_hab`,\r\n" + 
				"`descripcion_hab`,\r\n" + 
				"`caracteristicas_hab`,\r\n" + 
				"`precio_diario_hab`,\r\n" + 
				"`pisohabitacion_id_pishab`,\r\n" + 
				"`estadohabitacion_id_esthab`,\r\n" + 
				"`tipohabitacion_id_tiphab`)\r\n" + 
				"VALUES\r\n" + 
				"(0,?,?,?,?,?,?,?);";
		//3.Elegir el tipo de Objeto JDBC a utilizar
		try(PreparedStatement ptInsHab = con.prepareStatement(sqlInsHab)) {
			/*
			 * 4.Proceder a ejecutar la sentencia SQL
			 * a) execute -> DDL -> boolean
			 * b) executeUpdate -> DML excepto el Select -> Insert, Update, Delete -> int
			 * c) executeQuery -> Select -> ResultSet (Tabla de Información)
			 */
			//4.1 Setear parámetros
			Habitacion hab = (Habitacion) registro;
			ptInsHab.setString(1, hab.getNumeroHab());
			ptInsHab.setString(2, hab.getDescripcionHab());
			ptInsHab.setString(3, hab.getCaracteristicaHab());
			ptInsHab.setBigDecimal(4, hab.getPrecioHab());
			
			ptInsHab.setInt(5, hab.getPisoHabitacion().getIdPisHab());
			ptInsHab.setInt(6, hab.getEstadoHabitacion().getIdEstHab());
			ptInsHab.setInt(7, hab.getTipoHabitacion().getIdTipHab());
			//4.2 Ejecutar sentencia
			int numFilAfe = ptInsHab.executeUpdate();
			/*
			 * 5.Procesar la información, tabla se convierte en un puntero
			 * a) Varios registros -> while
			 * b) Un solo -> if
			 */
			if (numFilAfe > 0) {
				mensaje = "Registro guardado correctamente";
			}
			
			
		} catch (Exception e) {
			throw new Exception("Error al cerrar el PreparedStatement " + e.getMessage());	
			}
	}catch (Exception e) {
		throw new Exception("Error al cerrar la conexión función Guardar " + e.getMessage());
	}
		
		return mensaje;
	}

	@Override
	public String actualizar(Object registro) throws Exception {
		String mensaje = null;
		try (Connection con = ConexionBdd.conectarBdd()){  

			String sqlUpdHab = "UPDATE `habitacion`\r\n" + 
					"SET\r\n" + 
					"`numero_hab` = ?,\r\n" + 
					"`descripcion_hab` = ?,\r\n" + 
					"`caracteristicas_hab` = ?,\r\n" + 
					"`precio_diario_hab` = ?,\r\n" + 
					"`pisohabitacion_id_pishab` = ?,\r\n" + 
					"`estadohabitacion_id_esthab` = ?,\r\n" + 
					"`tipohabitacion_id_tiphab` = ?\r\n" + 
					"WHERE `id_hab` = ?;";
		
		
		try(PreparedStatement ptUpdHab = con.prepareStatement(sqlUpdHab))  {
			
			Habitacion hab = (Habitacion) registro;
			ptUpdHab.setString(1, hab.getNumeroHab());
			ptUpdHab.setString(2, hab.getDescripcionHab());
			ptUpdHab.setString(3, hab.getCaracteristicaHab());
			ptUpdHab.setBigDecimal(4, hab.getPrecioHab());
			
//			PisoHabitacion pisHab = (PisoHabitacion) registro;
//			ptUpdHab.setInt(5, pisHab.getIdPisHab());
			ptUpdHab.setInt(5, hab.getPisoHabitacion().getIdPisHab());
			
			
//			EstadoHabitacion estHab = (EstadoHabitacion) registro;
//			ptUpdHab.setInt(6, estHab.getIdEstHab());
			ptUpdHab.setInt(6, hab.getEstadoHabitacion().getIdEstHab());
				
						
//			TipoHabitacion tiphab = (TipoHabitacion) registro;
//			ptUpdHab.setInt(7, tiphab.getIdTipHab());
			ptUpdHab.setInt(7, hab.getTipoHabitacion().getIdTipHab());
			
			
			ptUpdHab.setInt(8, hab.getIdHab());

			int numFilAfe = ptUpdHab.executeUpdate();
			
			if (numFilAfe > 0) {
				mensaje = "Registro actualizado correctamente";
				
			}
		} catch (Exception e) {
			throw new Exception("Error al cerrar el pt:" + e.getMessage());	
			}
	}catch (Exception e) {
		throw new Exception("Error al cerrar la conexión" + e.getMessage());
	}
		
		return mensaje;
	}

	@Override
	public String eliminar(Object registro) throws Exception {
		String mensaje = null;
		try (Connection con = ConexionBdd.conectarBdd()){  

			String sqlDelHab = "DELETE FROM `habitacion` WHERE `id_hab` = ?;";
		
		
		try(PreparedStatement ptDelHab = con.prepareStatement(sqlDelHab))  {
			
			Habitacion hab = (Habitacion) registro;
			ptDelHab.setInt(1, hab.getIdHab());

			int numFilAfe = ptDelHab.executeUpdate();
			
			if (numFilAfe > 0) {
				mensaje = "Registro eliminado correctamente";
				
			}
		} catch (Exception e) {
			throw new Exception("Error al cerrar el pt:" + e.getMessage());	
			}
	}catch (Exception e) {
		throw new Exception("Error al cerrar la conexión" + e.getMessage());
	}
		
		return mensaje;
	}

	@Override
	public List<Habitacion> consultarTodos() throws Exception {
		PisoHabitacionTrs admPisHabTrs = new  PisoHabitacionTrs();
		EstadoHabitacionTrs admEstHabTrs = new EstadoHabitacionTrs();
		TipoHabitacionTrs admTipHabTrs = new TipoHabitacionTrs();
		List<Habitacion> listaHabitaciones = new ArrayList<>();
		try (Connection con = ConexionBdd.conectarBdd()){
			String sqlConHab = "SELECT * FROM `habitacion`;";
			try (Statement stConHab = con.createStatement();
				 ResultSet rs = stConHab.executeQuery(sqlConHab);) {
				while (rs.next()) {
					Habitacion hab = new Habitacion();
					hab.setIdHab(rs.getInt(1));
					hab.setNumeroHab(rs.getString(2));
					hab.setDescripcionHab(rs.getString(3));
					hab.setCaracteristicaHab(rs.getString(4));
					hab.setPrecioHab(rs.getBigDecimal(5));
					hab.setPisoHabitacion(admPisHabTrs.consultarPorId(rs.getInt(6)));
					hab.setEstadoHabitacion(admEstHabTrs.consultaPorId(rs.getInt(7)));
					hab.setTipoHabitacion(admTipHabTrs.consultaPorId(rs.getInt(8)));			
					
					listaHabitaciones.add(hab);
				}
			} catch (Exception e) {
				throw new Exception("Error al cerrar el pt");
			}
		} catch (Exception e) {
			throw new Exception("Error al cerrar la conexión Consulta todo");
		}

		return listaHabitaciones;
	}
	
	public List<Habitacion> consultarPorNombreDescripcion(String text) throws Exception {
		PisoHabitacionTrs admPisHabTrs = new  PisoHabitacionTrs();
		EstadoHabitacionTrs admEstHabTrs = new  EstadoHabitacionTrs();
		TipoHabitacionTrs admTipHabTrs = new  TipoHabitacionTrs();
		List<Habitacion> listaHabitaciones = new ArrayList<>();
		try(Connection con = ConexionBdd.conectarBdd()) {
			String sqlConHab = "SELECT * FROM `habitacion`   WHERE `numero_hab`  LIKE ? OR `descripcion_hab` LIKE ?;";
			try (PreparedStatement stConHab = con.prepareStatement(sqlConHab);){
				stConHab.setString(1, "%" + text + "%");
				stConHab.setString(2, "%" + text + "%");
				try {
					ResultSet rs = stConHab.executeQuery();

					while (rs.next()) {
						Habitacion hab = new Habitacion();
						hab.setIdHab(rs.getInt(1));
						hab.setNumeroHab(rs.getString(2));
						hab.setDescripcionHab(rs.getString(3));
						hab.setCaracteristicaHab(rs.getString(4));
						hab.setPrecioHab(rs.getBigDecimal(5));
						hab.setPisoHabitacion(admPisHabTrs.consultarPorId(rs.getInt(6)));
						hab.setEstadoHabitacion(admEstHabTrs.consultaPorId(rs.getInt(7)));
						hab.setTipoHabitacion(admTipHabTrs.consultaPorId(rs.getInt(8)));
						listaHabitaciones.add(hab);
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

		return listaHabitaciones;
	}
}
