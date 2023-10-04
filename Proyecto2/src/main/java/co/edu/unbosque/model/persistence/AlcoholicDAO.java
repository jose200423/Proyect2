package co.edu.unbosque.model.persistence;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import co.edu.unbosque.controller.DBConnection;
import co.edu.unbosque.model.AdminDTO;
import co.edu.unbosque.model.AlcoholicDTO;
import co.edu.unbosque.model.PersonDTO;

/**
 * Clase que implementa la interfaz CRUDoperation para operaciones relacionadas con alcohólicos.
 * @author Andres Meneses
 * @author Jose Munoz
 * @author Fabian Montano
 * @author Miguel pineda 
 * @author Yishaq Riveros
 */
public class AlcoholicDAO implements CRUDoperation {
	private ArrayList<AlcoholicDTO> listahcos;
	private DBConnection dbcon;
	/**
	 * Constructor
     * Inicializa la lista de alcohólicos y establece una conexión con la base de datos, luego llama a la función read() para cargar los datos.
     *
	 */

	public AlcoholicDAO() {
		listahcos = new ArrayList<AlcoholicDTO>();
		dbcon = new DBConnection();
		read();
	}
	 /**
     * Crea un nuevo alcohólico en la base de datos y en la lista de alcohólicos en memoria.
     *
     * @param obj El objeto AlcoholicDTO que se desea crear.
     * @return true si se crea correctamente, de lo contrario false.
   
     */

	@Override
	public boolean create(Object obj) {
		AlcoholicDTO newUser = (AlcoholicDTO) obj;
		for (AlcoholicDTO aDTO : listahcos) {
			if (aDTO.getIdentificationNumber() == newUser.getIdentificationNumber()) {
				return false;
			}
		}
		dbcon.initConnection();
		try {
			dbcon.setPreparedstatement(dbcon.getConect().prepareStatement(
					"INSERT INTO alcoholic (allname, cc, birthdate, city, sessions, nickname) VALUES(?,?,?,?,?,?)"));
			dbcon.getPreparedstatement().setString(1, newUser.getName());
			dbcon.getPreparedstatement().setLong(2, newUser.getIdentificationNumber());
			dbcon.getPreparedstatement().setDate(3, (Date) newUser.getBirthday());
			dbcon.getPreparedstatement().setString(4, newUser.getCityOfBorn());
			dbcon.getPreparedstatement().setInt(5, newUser.getSessionsPresent());
			dbcon.getPreparedstatement().setString(6, newUser.getNickname());
			dbcon.getPreparedstatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		listahcos.add(newUser);
		return true;
	}
	/**
     * Lee todos los alcohólicos desde la base de datos y los agrega a la lista en memoria.
     *
     * @return Una cadena con la representación de todos los alcohólicos.

     */

	@Override
	public String readAll() {
		listahcos.clear();
		dbcon.initConnection();
		try {
			dbcon.setStatement(dbcon.getConect().createStatement());
			dbcon.setResulset(dbcon.getStatement().executeQuery("SELECT * FROM alcoholic"));
			while (dbcon.getResulset().next()) {
				String name = dbcon.getResulset().getString("allname");
				long cedula = dbcon.getResulset().getLong("cc");
				Date fecha = dbcon.getResulset().getDate("birthdate");
				String city = dbcon.getResulset().getString("city");
				int sessions = dbcon.getResulset().getInt("sessions");
				String nickname = dbcon.getResulset().getString("nickname");
				listahcos.add(new AlcoholicDTO(name, cedula, fecha, city, sessions, nickname));
			}
			dbcon.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String temporal = "";
		for (AlcoholicDTO usuario : listahcos) {
			temporal += usuario.toString();
		}
		return temporal;
	}
	/**
     * Elimina un alcohólico de la base de datos y de la lista de alcohólicos en memoria por su cédula.
     *
     * @param id La cédula del alcohólico que se desea eliminar.
     * @return 0 si se elimina correctamente, de lo contrario 1.
   
     */

	@Override
	public int deleteByCc(long id) {
		dbcon.initConnection();
		try {
			dbcon.setPreparedstatement(dbcon.getConect().prepareStatement("DELETE FROM alcoholic WHERE cc=?"));
			dbcon.getPreparedstatement().setLong(1, id);
			dbcon.getPreparedstatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}

		for (int i = 0; i < listahcos.size(); i++) {
			if (listahcos.get(i).getIdentificationNumber() == id) {
				listahcos.remove(i);
				return 0;
			}
		}
		return 1;
	}
	 /**
     * Lee un alcohólico desde la base de datos por su cédula y lo devuelve como una cadena.
     *
     * @param cc La cédula del alcohólico que se desea leer.
     * @return Una cadena con la representación del alcohólico o "NO INFO" si no se encuentra.
   
     */

	@Override
	public String readByCc(long cc) {
		dbcon.initConnection();
		try {
			dbcon.setStatement(dbcon.getConect().createStatement());
			dbcon.setResulset(dbcon.getStatement().executeQuery("SELECT * FROM alcoholic WHERE cc = " + cc));
			if (dbcon.getResulset().next()) {
				String name = dbcon.getResulset().getString("allname");
				long cedula = dbcon.getResulset().getLong("cc");
				Date nivel = dbcon.getResulset().getDate("birthdate");
				String city = dbcon.getResulset().getString("city");
				int sessions = dbcon.getResulset().getInt("sessions");
				String nickname = dbcon.getResulset().getString("nickname");
				dbcon.close();
				AlcoholicDTO person = new AlcoholicDTO(name, cedula, nivel, city, sessions, nickname);
				return person.toString();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbcon.close();
		return "NO INFO";
	}
	/**
     * Actualiza los datos de un alcohólico en la base de datos y en la lista de alcohólicos en memoria por su cédula.
     *
     * @param cc   La cédula del alcohólico que se desea actualizar.
     * @param args Arreglo de argumentos con los nuevos valores.
     * @return 0 si se actualiza correctamente, de lo contrario 1.
     
     */

	
	public int updateByCcAttrs(long cc, String name, long doc, Date bth, String city, int sessions, String nickname) {
		Date fecha = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/mm/dd");
		try {

			dbcon.initConnection();
			dbcon.setPreparedstatement(dbcon.getConect().prepareStatement(
					"UPDATE alcoholic SET  allname=?, cc=?, birthdate=?, city=?, sessions=?, nickname=? WHERE cc="
							+ cc));
			dbcon.getPreparedstatement().setString(1, name);
			dbcon.getPreparedstatement().setLong(2, cc);
			dbcon.getPreparedstatement().setDate(3, bth);
			dbcon.getPreparedstatement().setString(4, city);
			dbcon.getPreparedstatement().setInt(5, sessions);
			dbcon.getPreparedstatement().setString(6, nickname);
			dbcon.getPreparedstatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < listahcos.size(); i++) {
			if (listahcos.get(i).getIdentificationNumber() == cc) {
				listahcos.get(i).setName(name);
				listahcos.get(i).setIdentificationNumber(cc);
				listahcos.get(i).setBirthday(bth);
				listahcos.get(i).setCityOfBorn(city);
				listahcos.get(i).setSessionsPresent(sessions);
				listahcos.get(i).setNickname(nickname);
				return 0;
			}

		}
		return 1;
	}
	/**
     * Lee los alcohólicos desde la base de datos y los agrega a la lista en memoria.
   
     */

	public void read() {
		listahcos.clear();
		dbcon.initConnection();
		try {
			dbcon.setStatement(dbcon.getConect().createStatement());
			dbcon.setResulset(dbcon.getStatement().executeQuery("SELECT * FROM alcoholic"));
			while (dbcon.getResulset().next()) {
				String name = dbcon.getResulset().getString("allname");
				long cedula = dbcon.getResulset().getLong("cc");
				Date fecha = dbcon.getResulset().getDate("birthdate");
				String city = dbcon.getResulset().getString("city");
				int sessions = dbcon.getResulset().getInt("sessions");
				String nickname = dbcon.getResulset().getString("nickname");
				listahcos.add(new AlcoholicDTO(name, cedula, fecha, city, sessions, nickname));
			}
			dbcon.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
     * Valida si un alcohólico con el nombre y la cédula especificados existe en la lista.
     *
     * @param name El nombre del alcohólico a validar.
     * @param cc   La cédula del alcohólico a validar.
     * @return true si el alcohólico existe en la lista, de lo contrario false.
   
     */
	
	public boolean validate(String name, long cc) {
		for (AlcoholicDTO u : listahcos) {
			if (u.getName().equals(name) && u.getIdentificationNumber() == cc) {
				return true;
			}
		}
		return false;
	}
	/**
	 * getters and setters
	 * @return
	 */

	public ArrayList<AlcoholicDTO> getAhdao() {
		return listahcos;
	}

	public void setAhdao(ArrayList<AlcoholicDTO> ahdao) {
		this.listahcos = ahdao;
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
