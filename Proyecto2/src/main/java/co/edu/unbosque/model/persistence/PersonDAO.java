package co.edu.unbosque.model.persistence;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import co.edu.unbosque.controller.DBConnection;
import co.edu.unbosque.model.AdminDTO;
import co.edu.unbosque.model.PersonDTO;

/**
 * Esta clase administra una lista de usuarios  y una conexión a la base de datos , permitiendo la creación, lectura, actualizacion y eliminación de registros de personas en la base de datos.
 * 
 * Funciona como un intermediario entre la lógica de la aplicación y la base de datos, proporcionando métodos para interactuar con los datos de manera eficiente y segura.
 * @author Andres Meneses
 * @author Jose Munoz
 * @author Fabian Montano
 * @author Miguel pineda 
 * @author Yishaq Riveros
 */
public class PersonDAO implements CRUDoperation {
	private ArrayList<PersonDTO> users;
	private DBConnection dbcon;
	/**
	 * Constructor. Inicializa la lista de usuarios y la conexión a la base de datos, y lee datos de la base de datos.
	 */

	public PersonDAO() {
		users = new ArrayList<PersonDTO>();
		dbcon = new DBConnection();
		read();
	}
	/**
	 * Crea un nuevo objeto PersonDTO e lo inserta en la base de datos si el número de identificación no existe en la base de datos.
	 * @param obj El objeto PersonDTO que se va a crear e insertar.
	 */

	@Override
	public boolean create(Object obj) {
		PersonDTO newUser = (PersonDTO) obj;
		for (PersonDTO u : users) {

			if (u.getIdentificationNumber() == newUser.getIdentificationNumber()) {
				return false;
			}
		}
		dbcon.initConnection();
		try {
			dbcon.setPreparedstatement(dbcon.getConect()
					.prepareStatement("INSERT INTO person (allname, cc, birthdate, city) VALUES(?,?,?,?)"));
			dbcon.getPreparedstatement().setString(1, newUser.getName());
			dbcon.getPreparedstatement().setLong(2, newUser.getIdentificationNumber());
			dbcon.getPreparedstatement().setDate(3, (Date) newUser.getBirthday());
			dbcon.getPreparedstatement().setString(4, newUser.getCityOfBorn());
			dbcon.getPreparedstatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		users.add(newUser);
		return true;
	}
	/**
	 * Lee todos los registros de PersonDTO desde la base de datos y llena la lista de usuarios con ellos.
	 */
	@Override
	public String readAll() {
		users.clear();
		dbcon.initConnection();
		try {
			dbcon.setStatement(dbcon.getConect().createStatement());
			dbcon.setResulset(dbcon.getStatement().executeQuery("SELECT * FROM person"));
			while (dbcon.getResulset().next()) {
				String name = dbcon.getResulset().getString("allname");
				long cedula = dbcon.getResulset().getLong("cc");
				Date fecha = dbcon.getResulset().getDate("birthdate");
				String city = dbcon.getResulset().getString("city");
				users.add(new PersonDTO(name, cedula, fecha, city));
			}
			dbcon.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String temporal = "";
		for (PersonDTO usuario : users) {
			temporal += usuario.toString();
		}
		return temporal;
	}
	/**
	 * Lee un registro de PersonDTO de la base de datos basado en el número de identificación proporcionado.
	 * @param cc El número de identificación (cc) a buscar.
	 */

	@Override
	public String readByCc(long cc) {
		dbcon.initConnection();
		try {
			dbcon.setStatement(dbcon.getConect().createStatement());
			dbcon.setResulset(dbcon.getStatement().executeQuery("SELECT * FROM person WHERE cc = " + cc));
			if (dbcon.getResulset().next()) {
				String name = dbcon.getResulset().getString("allname");
				long cedula = dbcon.getResulset().getLong("cc");
				Date nivel = dbcon.getResulset().getDate("birthdate");
				String city = dbcon.getResulset().getString("city");
				dbcon.close();
				PersonDTO person = new PersonDTO(name, cedula, nivel, city);
				return person.toString();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbcon.close();
		return "NO INFO";
	}
	/**
	 * Actualiza un registro de PersonDTO en la base de datos basado en el número de identificación proporcionado (cc).
	 * @param cc El número de identificación (cc) del registro que se va a actualizar.
	 * @param args Un arreglo de cadenas que contiene los valores actualizados (nombre, fecha de nacimiento, ciudad).
	 */

	
	public int updateByCcAttrs(long cc, String name, long doc, Date bth, String city) {
		Date fecha = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy//dd");
		try {
			dbcon.initConnection();
			dbcon.setPreparedstatement(dbcon.getConect()
					.prepareStatement("UPDATE person SET  allname=?, cc=?, birthdate=?, city=? WHERE cc=" + cc));
			dbcon.getPreparedstatement().setString(1, name);
			dbcon.getPreparedstatement().setLong(2, cc);
			dbcon.getPreparedstatement().setDate(3, bth);
			dbcon.getPreparedstatement().setString(4, city);
			dbcon.getPreparedstatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getIdentificationNumber() == cc) {
				users.get(i).setName(name);
				users.get(i).setIdentificationNumber(cc);
				users.get(i).setBirthday(bth);
				users.get(i).setCityOfBorn(city);
				return 0;
			}

		}
		return 1;
	}
	/**
	 * Elimina un registro de PersonDTO de la base de datos basado en el número de identificación proporcionado (cc).
	 * @param id El número de identificación (cc) del registro que se va a eliminar.
	 */

	@Override
	public int deleteByCc(long id) {
		dbcon.initConnection();
		try {
			dbcon.setPreparedstatement(dbcon.getConect().prepareStatement("DELETE FROM person WHERE cc=?"));
			dbcon.getPreparedstatement().setLong(1, id);
			dbcon.getPreparedstatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}

		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getIdentificationNumber() == id) {
				users.remove(i);
				return 0;
			}

		}
		return 1;
	}
	/**
	 * Lee todos los registros de PersonDTO desde la base de datos y llena la lista de usuarios con ellos. Llamado internamente durante la inicialización.
	 */

	public void read() {
		users.clear();
		dbcon.initConnection();
		try {
			dbcon.setStatement(dbcon.getConect().createStatement());
			dbcon.setResulset(dbcon.getStatement().executeQuery("SELECT * FROM person"));
			while (dbcon.getResulset().next()) {
				String name = dbcon.getResulset().getString("allname");
				long cedula = dbcon.getResulset().getLong("cc");
				Date fecha = dbcon.getResulset().getDate("birthdate");
				String city = dbcon.getResulset().getString("city");
				users.add(new PersonDTO(name, cedula, fecha, city));
			}
			dbcon.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Valida si existe un registro de PersonDTO con el nombre y número de identificación (cc) proporcionados en la lista de usuarios.
	 * @param name El nombre a validar.
	 * @param cc El número de identificación (cc) a validar.
	 */
	
	public boolean validate(String name, long cc) {
		for (PersonDTO u : users) {
			if (u.getName().equals(name) && u.getIdentificationNumber() == cc) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<PersonDTO> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<PersonDTO> users) {
		this.users = users;
	}

	public DBConnection getDbcon() {
		return dbcon;
	}

	public void setDbcon(DBConnection dbcon) {
		this.dbcon = dbcon;
	}
	
	@Override
	public int updateByCc(long cc, String... args) {
		
		return 1;
	}

}
