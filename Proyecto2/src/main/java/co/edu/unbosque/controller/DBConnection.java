package co.edu.unbosque.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Esta clase proporciona una conexión a una base de datos MySQL y métodos para
   interactuar con ella.
 * @author Andres Meneses
 * @author Jose Munoz
 * @author Fabian Montano
 * @author Miguel pineda 
 * @author Yishaq Riveros
 */
public class DBConnection {
   
	/*Declaracion de variables miembro */
	

	private Connection conect = null;
	private Statement statement = null;
	private PreparedStatement preparedstatement = null;
	private ResultSet resulset = null;

	private final String IP = "127.0.0.1";
	private final String PORT = "3306";
	private final String DATABASE = "anonimos";
	private final String USERNAME = "root";
	private final String PASSWORD = "Gokumia2004.";
    
	/* Constructor de la clase*/
	public DBConnection() {

	}
	
	/*Metodos getters y setters para aceeder a las variables miembro */

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

	/* cargar libreria */
	public void initConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		/*establece la conexion utilizando los datos de configuracion */
		try {
			conect = DriverManager.getConnection("jdbc:mysql://" + IP + ":" + PORT + "/" + DATABASE, USERNAME,
					PASSWORD);
			System.out.println("Conexion realizada con exito!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*Cierra la conexion y otros recursos de la base de datos*/

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
