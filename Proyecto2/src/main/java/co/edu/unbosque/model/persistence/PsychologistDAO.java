package co.edu.unbosque.model.persistence;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import co.edu.unbosque.controller.DBConnection;
import co.edu.unbosque.model.AdminDTO;
import co.edu.unbosque.model.AlcoholicDTO;
import co.edu.unbosque.model.PersonDTO;
import co.edu.unbosque.model.PsychologistDTO;
import co.edu.unbosque.model.ServicesDTO;

/**
 * Esta clase mantiene una lista de objetos `PsychologistDTO`, una conexión a la base de datos (`DBConnection`), así como instancias de clases relacionadas, como `AlcoholicDAO` y `ServicesDAO`. 
 * Proporciona métodos para crear nuevos registros de psicólogos, leer información de psicólogos por número de identificación (cc), actualizar registros existentes y eliminar registros de la base de datos. También permite la lectura de todos los registros de psicólogos y la validación de nombres y números de identificación.
 * 
 * @author Andres Meneses
 * @author Jose Munoz
 * @author Fabian Montano
 * @author Miguel pineda 
 * @author Yishaq Riveros
 */
public class PsychologistDAO implements CRUDoperation {

	private ArrayList<PsychologistDTO> listpys;
	private AlcoholicDAO ahdao;
	private ServicesDAO serv;
	private DBConnection dbcon;
	/**
	 * Constructor de la clase.
	 * Inicializa la lista de psicólogos, la conexión a la base de datos y otras clases relacionadas, y lee datos de la base de datos.
	 */

	public PsychologistDAO() {
		listpys = new ArrayList<PsychologistDTO>();
		ahdao = new AlcoholicDAO();
		serv = new ServicesDAO();
		dbcon = new DBConnection();
		read();
	}
	/**
	 * Crea un nuevo registro de psicólogo en la base de datos si el número de identificación no existe previamente.
	 * @param obj El objeto PsychologistDTO que se va a crear e insertar.
	 */

	@Override
	public boolean create(Object obj) {
		PsychologistDTO newUser = (PsychologistDTO) obj;
		for (PsychologistDTO pDTO : listpys) {
			if (pDTO.getIdentificationNumber() == newUser.getIdentificationNumber()) {
				return false;
			}
		}
		dbcon.initConnection();
		try {
			dbcon.setPreparedstatement(dbcon.getConect().prepareStatement(
					"INSERT INTO psychologist (allname, cc, birthdate, city, graduation, days, supportedsessions, salary) VALUES(?,?,?,?,?,?,?,?)"));
			dbcon.getPreparedstatement().setString(1, newUser.getName());
			dbcon.getPreparedstatement().setLong(2, newUser.getIdentificationNumber());
			dbcon.getPreparedstatement().setDate(3, (Date) newUser.getBirthday());
			dbcon.getPreparedstatement().setString(4, newUser.getCityOfBorn());
			dbcon.getPreparedstatement().setDate(5, (Date) newUser.getGraduationYear());
			
			
			LocalDate graduationDate = ((Date) newUser.getGraduationYear()).toLocalDate();
		    LocalDate currentDate = LocalDate.now();
		    long diasDesdeGraduacion = ChronoUnit.DAYS.between(graduationDate, currentDate);
		        
		    dbcon.getPreparedstatement().setInt(6, (int) diasDesdeGraduacion);
			dbcon.getPreparedstatement().setInt(7, newUser.getSupportedSessions());
			dbcon.getPreparedstatement().setInt(8, newUser.getSalary());
			dbcon.getPreparedstatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		listpys.add(newUser);
		return true;
	}
	
	/**
	 * Lee un registro de psicólogo de la base de datos basado en el número de identificación proporcionado.
	 * @param cc El número de identificación (cc) a buscar.
	 */

	@Override
	public String readByCc(long cc) {
		dbcon.initConnection();
		try {
			dbcon.setStatement(dbcon.getConect().createStatement());
			dbcon.setResulset(dbcon.getStatement().executeQuery("SELECT * FROM psychologist WHERE cc = " + cc));
			if (dbcon.getResulset().next()) {
				String name = dbcon.getResulset().getString("allname");
				long cedula = dbcon.getResulset().getLong("cc");
				Date nivel = dbcon.getResulset().getDate("birthdate");
				String city = dbcon.getResulset().getString("city");
				Date grade = dbcon.getResulset().getDate("graduation");
				int days = dbcon.getResulset().getInt("days");
				int sessions = dbcon.getResulset().getInt("supportedsessions");
				int salary = dbcon.getResulset().getInt("salary");
				dbcon.close();
				PsychologistDTO person = new PsychologistDTO(name, cedula, nivel, city, grade, days, sessions, salary);
				return person.toString();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbcon.close();
		return "NO INFO";
	}
	/**
	 * Actualiza un registro de psicólogo en la base de datos basado en el número de identificación proporcionado (cc).
	 * @param cc El número de identificación (cc) del registro que se va a actualizar.
	 * @param args Un arreglo de cadenas que contiene los valores actualizados (nombre, fecha de nacimiento, ciudad, etc.).
	 */

	@Override
	public int updateByCc(long cc, String... args) {
		Date fecha = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/mm/dd");
		try {
			dbcon.initConnection();
			dbcon.setPreparedstatement(dbcon.getConect().prepareStatement(
					"UPDATE psychologist SET  allname=?, cc=?, birthdate=?, city=?, graduation=?, days=?, supportedsessions=?, salary=? WHERE cc=?"));
			dbcon.getPreparedstatement().setString(1, args[0]);
			dbcon.getPreparedstatement().setLong(2, cc);
			dbcon.getPreparedstatement().setDate(3, Date.valueOf(args[2]));
			dbcon.getPreparedstatement().setString(4, args[3]);
			dbcon.getPreparedstatement().setInt(5, Integer.valueOf(args[4]));
			dbcon.getPreparedstatement().setInt(6, Integer.valueOf(args[5]));
			dbcon.getPreparedstatement().setInt(7, Integer.valueOf(args[6]));
			dbcon.getPreparedstatement().setInt(8, Integer.valueOf(args[7]));

			dbcon.getPreparedstatement().setLong(9, cc);

			dbcon.getPreparedstatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}

		for (int i = 0; i < listpys.size(); i++) {
			if (listpys.get(i).getIdentificationNumber() == cc) {
				listpys.get(i).setName(args[0]);
				listpys.get(i).setIdentificationNumber(cc);
				listpys.get(i).setBirthday(Date.valueOf(args[2]));
				listpys.get(i).setCityOfBorn(args[3]);
				listpys.get(i).setGraduationYear(Date.valueOf(args[4]));
				listpys.get(i).setDaysSinceGraduation(Integer.valueOf(args[5]));
				listpys.get(i).setSalary(Integer.valueOf(args[6]));
				listpys.get(i).setSupportedSessions(Integer.valueOf(args[7]));

				return 0;
			}

		}
		return 1;
	}
	/**
	 * Elimina un registro de psicólogo de la base de datos basado en el número de identificación proporcionado (cc).
	 * @param cc El número de identificación (cc) del registro que se va a eliminar.
	 * @return 0 si la eliminación fue exitosa, 1 si hubo un error.
	 */

	@Override
	public int deleteByCc(long cc) {
		dbcon.initConnection();
		try {
			dbcon.setPreparedstatement(dbcon.getConect().prepareStatement("DELETE FROM psychologist WHERE cc=?"));
			dbcon.getPreparedstatement().setLong(1, cc);
			dbcon.getPreparedstatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}

		for (int i = 0; i < listpys.size(); i++) {
			if (listpys.get(i).getIdentificationNumber() == cc) {
				listpys.remove(i);
				return 0;
			}
		}
		return 1;
	}
	/**
	 * Lee todos los registros de psicólogos desde la base de datos y llena la lista de psicólogos con ellos.
	 */

	public String readAll() {
		listpys.clear();
		dbcon.initConnection();
		try {
			dbcon.setStatement(dbcon.getConect().createStatement());
			dbcon.setResulset(dbcon.getStatement().executeQuery("SELECT * FROM psychologist"));
			while (dbcon.getResulset().next()) {
				String name = dbcon.getResulset().getString("allname");
				long cedula = dbcon.getResulset().getLong("cc");
				Date fecha = dbcon.getResulset().getDate("birthdate");
				String city = dbcon.getResulset().getString("city");
				Date graduation = dbcon.getResulset().getDate("graduation");
				int days = dbcon.getResulset().getInt("days");
				int salary = dbcon.getResulset().getInt("salary");
				int sessions = dbcon.getResulset().getInt("supportedsessions");
				listpys.add(new PsychologistDTO(name, cedula, fecha, city, graduation, days, salary, sessions));
			}
			dbcon.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String temporal = "";
		for (PsychologistDTO usuario : listpys) {
			temporal += usuario.toString();
		}
		return temporal;
	}
	/**
	 * Lee todos los registros de psicólogos desde la base de datos. Llamado internamente durante la inicialización.
	 */

	public void read() {
		listpys.clear();
		dbcon.initConnection();
		try {
			dbcon.setStatement(dbcon.getConect().createStatement());
			dbcon.setResulset(dbcon.getStatement().executeQuery("SELECT * FROM psychologist"));
			while (dbcon.getResulset().next()) {
				String name = dbcon.getResulset().getString("allname");
				long cedula = dbcon.getResulset().getLong("cc");
				Date fecha = dbcon.getResulset().getDate("birthdate");
				String city = dbcon.getResulset().getString("city");
				Date graduation = dbcon.getResulset().getDate("graduation");
				int days = dbcon.getResulset().getInt("days");
				int salary = dbcon.getResulset().getInt("salary");
				int sessions = dbcon.getResulset().getInt("supportedsessions");
				listpys.add(new PsychologistDTO(name, cedula, fecha, city, graduation, days, salary, sessions));
			}
			dbcon.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Valida si existe un registro de psicólogo con el nombre y número de identificación (cc) proporcionados en la lista de psicólogos.
	 * @param name El nombre a validar.
	 * @param cc El número de identificación (cc) a validar.
	 */
	
	public boolean validate(String name, long cc) {
		for (PsychologistDTO u : listpys) {
			if (u.getName().equals(name) && u.getIdentificationNumber() == cc) {
				return true;
			}
		}
		return false;
	}

// ----------------------------------------------------------------------------------------------------------------------------------------------------
    
	/**
	 * Crea un nuevo registro de servicios en la base de datos a través de la clase ServicesDAO.
	 * @param obj El objeto ServicesDTO que se va a crear e insertar.
	 */
	public boolean createServices(Object obj) {
		boolean is = serv.create(obj);
		if (is) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Crea un nuevo registro de alcohólicos en la base de datos a través de la clase AlcoholicDAO.
	 * @param obj El objeto AlcoholicDTO que se va a crear e insertar.
	 */
	
	public boolean createAlcoholics(Object obj) {
		boolean is = ahdao.create(obj);
		if (is) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Lee todos los registros de servicios desde la base de datos a través de la clase ServicesDAO.
	 */

	public String readAllServices() {
		return serv.readAll();
	}
	/**
	 * Lee todos los registros de alcohólicos desde la base de datos a través de la clase AlcoholicDAO.
	 */
	
	public String readAllAlcoholics() {
		return ahdao.readAll();
	}
	/**
	 * Elimina un registro de servicios de la base de datos a través de la clase ServicesDAO.
	 * @param cc El número de identificación (cc) del registro que se va a eliminar.
	 */

	public int deleteServices(long cc) {
		 int i = serv.deleteByCc(cc);
	      if(i == 0) {
	    	  return 0;
	      }else{
	    	  return 1;
	      }
	}
	/**
	 * Elimina un registro de alcohólicos de la base de datos a través de la clase AlcoholicDAO.
	 * @param cc El número de identificación (cc) del registro que se va a eliminar.
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
	 * Actualiza un registro de servicios en la base de datos a través de la clase ServicesDAO.
	 * @param cc El número de identificación (cc) del registro que se va a actualizar.
	 * @param args Un arreglo de cadenas que contiene los valores actualizados.
	 */
	
	public int updateByCcServices(long cc, String... args) {
		int i = serv.updateByCc(cc,args);
	      if(i == 0) {
	    	  return 0;
	      }else{
	    	  return 1;
	      }
	}
	/**
	 * Actualiza un registro de alcohólicos en la base de datos a través de la clase AlcoholicDAO.
	 * @param cc El número de identificación (cc) del registro que se va a actualizar.
	 * @param args Un arreglo de cadenas que contiene los valores actualizados.
	 */
	
	
	public int updateByCcAlcoholics(long cc, String... args) {
		int i = ahdao.updateByCc(cc,args);
	      if(i == 0) {
	    	  return 0;
	      }else{
	    	  return 1;
	      }
	}
	

	public ArrayList<PsychologistDTO> getPydao() {
		return listpys;
	}

	public void setPydao(ArrayList<PsychologistDTO> pydao) {
		this.listpys = pydao;
	}

	public AlcoholicDAO getAhdao() {
		return ahdao;
	}

	public void setAhdao(AlcoholicDAO ahdao) {
		this.ahdao = ahdao;
	}

	public ServicesDAO getServ() {
		return serv;
	}

	public void setServ(ServicesDAO serv) {
		this.serv = serv;
	}

	public DBConnection getDbcon() {
		return dbcon;
	}

	public void setDbcon(DBConnection dbcon) {
		this.dbcon = dbcon;
	}

}
