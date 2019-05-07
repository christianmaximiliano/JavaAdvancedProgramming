package com.qdishemax.reservahotel.negocio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.qdishemax.reservahotel.modelo.ConexionBdd;
import com.qdishemax.reservahotel.modelo.Usuario;

public class UsuarioTrs implements ICrudC {

	@Override
	public String guardar(Object registro) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String actualizar(Object registro) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String eliminar(Object registro) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> consultarTodos() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Usuario validarUsuario(String usuario, String clave) throws Exception {
		Usuario usuDev = null;
		
		//1.Recuperar la Conexión
		Connection con = ConexionBdd.conectarBdd();
		
		//2.Establecer la operación: DDL o DML
		String sqlConUsu = "SELECT * FROM usuario\r\n" + "WHERE nombre_usu = '" + usuario + "' and \r\n"
				+ "      clave_usu = '" + clave + "';";
		
		//3.Elegir el tipo de Objeto JDBC a utilizar
		Statement stConUsu = con.createStatement();
		
		/*
		 * 4.Proceder a ejecutar la sentencia SQL
		 * a) execute -> DDL -> boolean
		 * b) executeUpdate -> DML excepto el Select -> Insert, Update, Delete -> int
		 * c) executeQuery -> Select -> ResultSet (Tabla de Información)
		 */
		ResultSet rs = stConUsu.executeQuery(sqlConUsu);
		
		/*
		 * 5.Procesar la información, tabla se convierte en un puntero
		 * a) Varios registros -> while
		 * b) Un solo -> if
		 */
		if(rs.next()) {
			usuDev = new Usuario();
			usuDev.setNombreUsu(rs.getString(2)); //posicion de la columna
			usuDev.setClaveUsu(rs.getString("clave_usu")); // nombre de la columna
		}
		
		return usuDev;
	}
	
//	public static void main(String[] args) {
//		try {
//			UsuarioTrs usTr = new UsuarioTrs();
//			Usuario usuDev = usTr.validarUsuario("max", "1234");
//			System.out.println(usuDev);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
