package co.edu.unbosque.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase para gestionar la conexión a una base de datos MySQL.
 * 
 * @author Andres Meneses
 * @author Yishaq Riveros
 * @author Fabian Montano
 * @author Jose Munoz
 * @author Miguel Pineda
 */
public class DBConnection {

	private Connection conect = null;
	private Statement statement = null;
	private PreparedStatement preparedstatement = null;
	private ResultSet resulset = null;

	private final String IP = "127.0.0.1";
	private final String PORT = "3306";
	private final String DATABASE = "anonimos";
	private final String USERNAME = "root";
	private final String PASSWORD = "famp";

	/*
	 * Constructor de la clase.
	 */

	public DBConnection() {

	}

	/*
	 * Métodos getters y setters para los campos
	 */

	public Connection getConect() {
		return conect;
	}

	public void setConect(Connection conect) {
		this.conect = conect;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public PreparedStatement getPreparedstatement() {
		return preparedstatement;
	}

	public void setPreparedstatement(PreparedStatement preparedstatement) {
		this.preparedstatement = preparedstatement;
	}

	public ResultSet getResulset() {
		return resulset;
	}

	public void setResulset(ResultSet resulset) {
		this.resulset = resulset;
	}

	public String getIP() {
		return IP;
	}

	public String getPORT() {
		return PORT;
	}

	public String getDATABASE() {
		return DATABASE;
	}

	public String getUSERNAME() {
		return USERNAME;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	/**
	 * Inicializa la conexión a la base de datos. Cargar librerias
	 */
	public void initConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conect = DriverManager.getConnection("jdbc:mysql://" + IP + ":" + PORT + "/" + DATABASE, USERNAME,
					PASSWORD);
			System.out.println("Conexion realizada con exito!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cierra la conexión y los recursos relacionados.
	 */

	public void close() {
		try {
			if (resulset != null) {
				resulset.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (conect != null) {
				conect.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
