package co.edu.unbosque.model.persistence;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import co.edu.unbosque.controller.DBConnection;
import co.edu.unbosque.model.AdminDTO;
import co.edu.unbosque.model.AlcoholicDTO;
import co.edu.unbosque.model.PersonDTO;
import co.edu.unbosque.model.PsychologistDTO;
import co.edu.unbosque.model.ServicesDTO;

/**
 * Esta clase implementa operaciones CRUD para objetos ServicesDTO.
 * Interactúa con una base de datos para realizar operaciones de creación, lectura, actualización y eliminación.
 * 
 * @author Andres Meneses
 * @author Jose Munoz
 * @author Fabian Montano
 * @author Miguel pineda 
 * @author Yishaq Riveros
 */
public class ServicesDAO implements CRUDoperation {
	private ArrayList<ServicesDTO> serv;
	private AlcoholicDAO ahdao;
	private DBConnection dbcon;
	/**
	 * constructor
	 */

	public ServicesDAO() {
		serv = new ArrayList<ServicesDTO>();
		ahdao = new AlcoholicDAO();
		dbcon = new DBConnection();
		read();
	}
	/**
     * Crea un nuevo objeto ServicesDTO en la base de datos.
     * @param obj El objeto ServicesDTO a crear.
     */

	@Override
	public boolean create(Object obj) {
		ServicesDTO newUser = (ServicesDTO) obj;
		for (ServicesDTO sDTO : serv) {
			if (sDTO.getIdentificationNumber() == newUser.getIdentificationNumber()) {
				return false;
			}
		}
		dbcon.initConnection();
		try {
			dbcon.setPreparedstatement(dbcon.getConect().prepareStatement(
					"INSERT INTO services (allname, cc, birthdate, city, salary, cleanup) VALUES(?,?,?,?,?,?)"));
			dbcon.getPreparedstatement().setString(1, newUser.getName());
			dbcon.getPreparedstatement().setLong(2, newUser.getIdentificationNumber());
			dbcon.getPreparedstatement().setDate(3, (Date) newUser.getBirthday());
			dbcon.getPreparedstatement().setString(4, newUser.getCityOfBorn());
			dbcon.getPreparedstatement().setInt(5, newUser.getSalary());
			dbcon.getPreparedstatement().setInt(6, newUser.getSessionsCleaned());
			dbcon.getPreparedstatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		serv.add(newUser);
		return true;
	}
	/**
     * Lee todos los objetos ServicesDTO de la base de datos.
     */

	@Override
	public String readAll() {
		serv.clear();
		dbcon.initConnection();
		try {
			dbcon.setStatement(dbcon.getConect().createStatement());
			dbcon.setResulset(dbcon.getStatement().executeQuery("SELECT * FROM services"));
			while (dbcon.getResulset().next()) {
				String name = dbcon.getResulset().getString("allname");
				long cedula = dbcon.getResulset().getLong("cc");
				Date fecha = dbcon.getResulset().getDate("birthdate");
				String city = dbcon.getResulset().getString("city");
				int salary = dbcon.getResulset().getInt("salary");
				int sessions = dbcon.getResulset().getInt("cleanup");
				serv.add(new ServicesDTO(name, cedula, fecha, city, salary, sessions));
			}
			dbcon.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String temporal = "";
		for (ServicesDTO usuario : serv) {
			temporal += usuario.toString();
		}
		return temporal;
	}
	/**
     * Lee un objeto ServicesDTO por su número de identificación (cc).
     * @param cc El número de identificación a buscar.
     */

	@Override
	public String readByCc(long cc) {
		dbcon.initConnection();
		try {
			dbcon.setStatement(dbcon.getConect().createStatement());
			dbcon.setResulset(dbcon.getStatement().executeQuery("SELECT * FROM services WHERE cc = " + cc));
			if (dbcon.getResulset().next()) {
				String name = dbcon.getResulset().getString("allname");
				long cedula = dbcon.getResulset().getLong("cc");
				Date nivel = dbcon.getResulset().getDate("birthdate");
				String city = dbcon.getResulset().getString("city");
				int salary = dbcon.getResulset().getInt("salary");
				int cleanup = dbcon.getResulset().getInt("cleanup");
				dbcon.close();
				ServicesDTO person = new ServicesDTO(name, cedula, nivel, city, salary, cleanup);
				return person.toString();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbcon.close();
		return "NO INFO";
	}
	/**
     * Actualiza un objeto ServicesDTO por su número de identificación (cc).
     * @param cc El número de identificación a buscar.
     * @param args Los nuevos valores para el objeto.
     */

	
	
	public int updateByCcAttrs(long cc, String name, long doc, Date bth, String city, int salary, int sessions) {
		Date fecha = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/mm/dd");
		try {

			dbcon.initConnection();
			dbcon.setPreparedstatement(dbcon.getConect().prepareStatement(
					"UPDATE services SET  allname=?, cc=?, birthdate=?, city=?, salary=?, cleanup=? WHERE cc=" + cc));
			dbcon.getPreparedstatement().setString(1, name);
			dbcon.getPreparedstatement().setLong(2, cc);
			dbcon.getPreparedstatement().setDate(3, bth);
			dbcon.getPreparedstatement().setString(4, city);
			dbcon.getPreparedstatement().setInt(5, salary);
			dbcon.getPreparedstatement().setInt(6, sessions);
			dbcon.getPreparedstatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < serv.size(); i++) {
			if (serv.get(i).getIdentificationNumber() == cc) {
				serv.get(i).setName(name);
				serv.get(i).setIdentificationNumber(cc);
				serv.get(i).setBirthday(bth);
				serv.get(i).setCityOfBorn(city);
				serv.get(i).setSalary(salary);
				serv.get(i).setSessionsCleaned(sessions);
				return 0;
			}

		}
		return 1;
	}
	/**
     * Elimina un objeto ServicesDTO por su número de identificación (cc).
     * @param cc El número de identificación a buscar.
     */

	@Override
	public int deleteByCc(long cc) {
		dbcon.initConnection();
		try {
			dbcon.setPreparedstatement(dbcon.getConect().prepareStatement("DELETE FROM services WHERE cc=?"));
			dbcon.getPreparedstatement().setLong(1, cc);
			dbcon.getPreparedstatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}

		for (int i = 0; i < serv.size(); i++) {
			if (serv.get(i).getIdentificationNumber() == cc) {
				serv.remove(i);
				return 0;
			}

		}
		return 1;
	}
	/**
     * Lee los datos de la base de datos y actualiza la lista de servicios.
     */

	public void read() {
		serv.clear();
		dbcon.initConnection();
		try {
			dbcon.setStatement(dbcon.getConect().createStatement());
			dbcon.setResulset(dbcon.getStatement().executeQuery("SELECT * FROM services"));
			while (dbcon.getResulset().next()) {
				String name = dbcon.getResulset().getString("allname");
				long cedula = dbcon.getResulset().getLong("cc");
				Date fecha = dbcon.getResulset().getDate("birthdate");
				String city = dbcon.getResulset().getString("city");
				int salary = dbcon.getResulset().getInt("salary");
				int sessions = dbcon.getResulset().getInt("cleanup");
				serv.add(new ServicesDTO(name, cedula, fecha, city, salary, sessions));
			}
			dbcon.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	/**
     * Valida si un nombre y número de identificación (cc) ya existen en la lista de servicios.
     * @param name El nombre a validar.
     * @param cc El número de identificación a validar.
     */
	
	public boolean validate(String name, long cc) {
		for (ServicesDTO u : serv) {
			if (u.getName().equals(name) && u.getIdentificationNumber() == cc) {
				return true;
			}
		}
		return false;
	}
	/**
     * Crea objetos de tipo AlcoholicDAO en base a un objeto proporcionado.
     * @param obj El objeto a crear en la lista de alcohólicos.
     */

 boolean createAlcoholics(Object obj) {
		boolean is = ahdao.create(obj);
		if (is) {
			return true;
		} else {
			return false;
		}
	}
 /**
  * Lee todos los objetos de la lista de alcohólicos.
  */

	public String readAllAlcoholics() {
		return ahdao.readAll();
	}
	/**
     * Elimina un objeto de la lista de alcohólicos por su número de identificación (cc).
     * @param cc El número de identificación a buscar.
     */

	public int deleteAlcoholics(long cc) {
		  int i = ahdao.deleteByCc(cc);
	      if(i == 0) {
	    	  return 0;
	      }else{
	    	  return 1;
	      }
	}
	/**
     * Actualiza un objeto de la lista de alcohólicos por su número de identificación (cc).
     * @param cc El número de identificación a buscar.
     * @param args Los nuevos valores para el objeto.
     * @return 0 si la actualización fue exitosa, 1 en caso contrario.
     */
	
	public int updateByCcAlcoholics(long cc, String... args) {
		int i = ahdao.updateByCc(cc,args);
	      if(i == 0) {
	    	  return 0;
	      }else{
	    	  return 1;
	      }
	}
	

	public ArrayList<ServicesDTO> getServ() {
		return serv;
	}

	public void setServ(ArrayList<ServicesDTO> serv) {
		this.serv = serv;
	}

	public AlcoholicDAO getAhdao() {
		return ahdao;
	}

	public void setAhdao(AlcoholicDAO ahdao) {
		this.ahdao = ahdao;
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
