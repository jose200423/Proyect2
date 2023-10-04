package co.edu.unbosque.model.persistence;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import co.edu.unbosque.controller.DBConnection;
import co.edu.unbosque.model.AdminDTO;
import co.edu.unbosque.model.AlcoholicDTO;
import co.edu.unbosque.model.PsychologistDTO;
import co.edu.unbosque.model.ServicesDTO;
import co.edu.unbosque.model.AdminDTO;

/**
 * Esta clase implementa la interfaz CRUDoperation y gestiona la lógica de acceso a datos para los administradores.
 * Proporciona métodos para crear, leer, actualizar y eliminar administradores en la base de datos.
 *
 * @autor Andres Meneses
 * @author Jose Munoz
 * @author Fabian Montano
 * @author Miguel pineda 
 * @author Yishaq Riveros
 */
public class AdminDAO implements CRUDoperation {
	private ArrayList<AdminDTO> listadmins;
	private PsychologistDAO pydao;
	private AlcoholicDAO ahdao;
	private ServicesDAO serdao;
	private DBConnection dbcon;
	
	/**
	 * Constructor
	 */

	public AdminDAO() {
		listadmins = new ArrayList<AdminDTO>();
		pydao = new PsychologistDAO();
		ahdao = new AlcoholicDAO();
		serdao = new ServicesDAO();
		dbcon = new DBConnection();
		read();
	}
	/**
	 * Crea un nuevo administrador en la base de datos y agrega un objeto AdminDTO a la lista de administradores si no existe
	 * previamente un administrador con la misma cédula.
	 *
	 * @param obj El objeto AdminDTO que representa al nuevo administrador.
	 * @return true si se crea correctamente, de lo contrario false.
	 */

	@Override
	public boolean create(Object obj) {
		AdminDTO newUser = (AdminDTO) obj;
		for (AdminDTO adminDTO : listadmins) {
			if (adminDTO.getIdentificationNumber() == newUser.getIdentificationNumber()) {
				return false;
			}
		}
		dbcon.initConnection();
		try {
			dbcon.setPreparedstatement(dbcon.getConect()
					.prepareStatement("INSERT INTO administrador (allname, cc, birthdate, city) VALUES(?,?,?,?)"));
			dbcon.getPreparedstatement().setString(1, newUser.getName());
			dbcon.getPreparedstatement().setLong(2, newUser.getIdentificationNumber());
			dbcon.getPreparedstatement().setDate(3, (Date) newUser.getBirthday());
			dbcon.getPreparedstatement().setString(4, newUser.getCityOfBorn());
			dbcon.getPreparedstatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		listadmins.add((AdminDTO) obj);
		return true;
	}
	/**
	 * Lee todos los administradores desde la base de datos y los agrega a la lista de administradores.
	 *
	 * @return Una cadena que representa la información de todos los administradores.
	 */

	@Override
	public String readAll() {
		listadmins.clear();
		dbcon.initConnection();
		try {
			dbcon.setStatement(dbcon.getConect().createStatement());
			dbcon.setResulset(dbcon.getStatement().executeQuery("SELECT * FROM administrador"));
			while (dbcon.getResulset().next()) {
				String name = dbcon.getResulset().getString("allname");
				int cedula = dbcon.getResulset().getInt("cc");
				Date fecha = dbcon.getResulset().getDate("birthdate");
				String city = dbcon.getResulset().getString("city");
				listadmins.add(new AdminDTO(name, cedula, fecha, city));
			}
			dbcon.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String temporal = "";
		for (AdminDTO usuario : listadmins) {
			temporal += usuario.toString();
		}
		return temporal;
	}
	/**
	 * Lee un administrador desde la base de datos por su cédula.
	 *
	 * @param cc La cédula del administrador a buscar.
	 * @return Una cadena que representa la información del administrador encontrado o "NO INFO" si no se encuentra.
	 */

	@Override
	public String readByCc(long cc) {
		dbcon.initConnection();
		try {
			dbcon.setStatement(dbcon.getConect().createStatement());
			dbcon.setResulset(dbcon.getStatement().executeQuery("SELECT * FROM administrador WHERE cc = " + cc));
			if (dbcon.getResulset().next()) {
				String name = dbcon.getResulset().getString("allname");
				int cedula = dbcon.getResulset().getInt("cc");
				Date nivel = dbcon.getResulset().getDate("birthdate");
				String city = dbcon.getResulset().getString("city");
				dbcon.close();
				AdminDTO person = new AdminDTO(name, cedula, nivel, city);
				return person.toString();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbcon.close();
		return "NO INFO";
	}
	/**
	 * Actualiza un administrador en la base de datos y en la lista de administradores por su cédula.
	 *
	 * @param cc   La cédula del administrador a actualizar.
	 * @param args Los nuevos argumentos para actualizar el administrador.
	 * @return 0 si se actualiza correctamente, de lo contrario 1.
	 */

	
	
	public int updateByCcAttrs(long cc, String name, long doc, Date bth, String city) {
		Date fecha = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/mm/dd");
		try {

			dbcon.initConnection();
			dbcon.setPreparedstatement(dbcon.getConect()
					.prepareStatement("UPDATE administrador SET  allname=?, cc=?, birthdate=?, city=? WHERE cc="+cc));
			dbcon.getPreparedstatement().setString(1, name);
			dbcon.getPreparedstatement().setLong(2, cc);
			dbcon.getPreparedstatement().setDate(3, bth);
			dbcon.getPreparedstatement().setString(4, city);
			dbcon.getPreparedstatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < listadmins.size(); i++) {
			if (listadmins.get(i).getIdentificationNumber() == cc) {
				listadmins.get(i).setName(name);
				listadmins.get(i).setIdentificationNumber(cc);
				listadmins.get(i).setBirthday(bth);
				listadmins.get(i).setCityOfBorn(city);
				return 0;
			}

		}
		return 1;
	}
	
	/**
	 * Elimina un administrador de la base de datos y de la lista de administradores en memoria por su cédula.
	 *
	 * @param id La cédula del administrador que se desea eliminar.
	 * @return 0 si se elimina correctamente, de lo contrario 1.
	 */

	@Override
	public int deleteByCc(long id) {
		dbcon.initConnection();
		try {
			dbcon.setPreparedstatement(dbcon.getConect().prepareStatement("DELETE FROM administrador WHERE cc=?"));
			dbcon.getPreparedstatement().setLong(1, id);
			dbcon.getPreparedstatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}

		for (int i = 0; i < listadmins.size(); i++) {
			if (listadmins.get(i).getIdentificationNumber() == id) {
				listadmins.remove(i);
				return 0;
			}

		}
		return 1;
	}
	/**
     * Valida si un administrador con el nombre y la cédula especificados existe en la lista.
     *
     * @param name El nombre del administrador a validar.
     * @param cc   La cédula del administrador a validar.
     * @return true si el administrador existe en la lista, de lo contrario false.
     */

	public boolean validate(String name, long cc) {
		for (AdminDTO u : listadmins) {
			if (u.getName().equals(name) && u.getIdentificationNumber() == cc) {
				return true;
			}
		}
		return false;
	}
	/**
     * Lee los administradores desde la base de datos y los agrega a la lista.
     */

	public void read() {
		listadmins.clear();
		dbcon.initConnection();
		try {
			dbcon.setStatement(dbcon.getConect().createStatement());
			dbcon.setResulset(dbcon.getStatement().executeQuery("SELECT * FROM administrador"));
			while (dbcon.getResulset().next()) {
				String name = dbcon.getResulset().getString("allname");
				int cedula = dbcon.getResulset().getInt("cc");
				Date fecha = dbcon.getResulset().getDate("birthdate");
				String city = dbcon.getResulset().getString("city");
				listadmins.add(new AdminDTO(name, cedula, fecha, city));
			}
			dbcon.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	/**
     * Crea un psicólogo utilizando el DAO de psicólogos.
     *
     * @param obj El objeto PsychologistDTO que representa al psicólogo a crear.
     * @return true si se crea correctamente, de lo contrario false.
     */
	
	public boolean createPsychologist(Object obj) {
		boolean is = pydao.create(obj);
		if(is) {
			return true;
		}else {
			return false;
		}
	}
	/**
     * Crea un servicio utilizando el DAO de servicios.
     *
     * @param obj El objeto ServicesDTO que representa el servicio a crear.
     * @return true si se crea correctamente, de lo contrario false.
     */

	public boolean createServices(Object obj) {
		boolean is = serdao.create(obj);
		if(is) {
			return true;
		}else {
			return false;
		}
	}
	/**
     * Crea un alcohólico utilizando el DAO de alcohólicos.
     *
     * @param obj El objeto AlcoholicDTO que representa al alcohólico a crear.
     * @return true si se crea correctamente, de lo contrario false.
     */
	
	public boolean createAlcoholics(Object obj) {
		boolean is = ahdao.create(obj);
		if(is) {
			return true;
		}else {
			return false;
		}
	}
	/**
     * Obtiene una lista de psicólogos utilizando el DAO de psicólogos.
     *
     * @return Una cadena que representa la lista de psicólogos.
     */

	public String listPsychologist() {
		return pydao.readAll();
	}
	/**
     * Obtiene una lista de servicios utilizando el DAO de servicios.
     *
     * @return Una cadena que representa la lista de servicios.
     */

	public String listServices() {
		return serdao.readAll();
	}
	 /**
     * Obtiene una lista de alcohólicos utilizando el DAO de alcohólicos.
     *
     * @return Una cadena que representa la lista de alcohólicos.
     */

	public String listAlcoholics() {
		return ahdao.readAll();
	}
	/**
     * Elimina un servicio utilizando el DAO de servicios.
     *
     * @param cc La cédula de la persona asociada al servicio a eliminar.
     * @return 0 si se elimina correctamente, de lo contrario 1.
     */
	
	
	public int deleteServices(long cc) {
      int i = serdao.deleteByCc(cc);
      if(i == 0) {
    	  return 0;
      }else{
    	  return 1;
      }
	}
	/**
     * Elimina un psicólogo utilizando el DAO de psicólogos.
     *
     * @param cc La cédula de la persona asociada al psicólogo a eliminar.
     * @return 0 si se elimina correctamente, de lo contrario 1.
     */

	public int deleteePsychologist(long cc) {
		 int i = pydao.deleteByCc(cc);
	      if(i == 0) {
	    	  return 0;
	      }else{
	    	  return 1;
	      }
	}
	/**
     * Elimina un alcohólico utilizando el DAO de alcohólicos.
     *
     * @param cc La cédula de la persona asociada al alcohólico a eliminar.
     * @return 0 si se elimina correctamente, de lo contrario 1.
     */

	public int deletelistAlcoholics(long cc) {
		 int i = ahdao.deleteByCc(cc);
	      if(i == 0) {
	    	  return 0;
	      }else{
	    	  return 1;
	      }
	}
	/**
     * Actualiza un servicio utilizando el DAO de servicios.
     *
     * @param cc    La cédula de la persona asociada al servicio a actualizar.
     * @param args  Los nuevos argumentos para actualizar el servicio.
     * @return 0 si se actualiza correctamente, de lo contrario 1.
     */
	
	public int updateByCcServices(long cc, String... args) {
		int i = serdao.updateByCc(cc,args);
	      if(i == 0) {
	    	  return 0;
	      }else{
	    	  return 1;
	      }
	}
	/**
	 * Actualiza un psicólogo utilizando el DAO de psicólogos.
	 *
	 * @param cc    La cédula de la persona asociada al psicólogo a actualizar.
	 * @param args  Los nuevos argumentos para actualizar el psicólogo.
	 * @return 0 si se actualiza correctamente, de lo contrario 1.
	 */
	
	public int updateByCcPsychologist(long cc, String... args) {
		int i = pydao.updateByCc(cc,args);
	      if(i == 0) {
	    	  return 0;
	      }else{
	    	  return 1;
	      }
	}
	/**
	 * Actualiza un alcohólico utilizando el DAO de alcohólicos.
	 *
	 * @param cc    La cédula de la persona asociada al alcohólico a actualizar.
	 * @param args  Los nuevos argumentos para actualizar el alcohólico.
	 * @return 0 si se actualiza correctamente, de lo contrario 1.
	 */
	
	public int updateByCcAlcoholics(long cc, String... args) {
		int i = ahdao.updateByCc(cc,args);
	      if(i == 0) {
	    	  return 0;
	      }else{
	    	  return 1;
	      }
	}
	/**
	 * Calcula la diferencia en días entre una fecha inicial y la fecha actual.
	 *
	 * @param fechaInicial La fecha inicial para calcular la diferencia en días.
	 * @return La diferencia en días entre la fecha inicial y la fecha actual.
	 */
	
	public int calcularDias(Date fechaInicial) {
		LocalDate fechaIniciallocal = fechaInicial.toLocalDate();
		LocalDate fechafinal = LocalDate.now();
		long diferencia = ChronoUnit.DAYS.between(fechaIniciallocal, fechafinal);
		System.out.println(diferencia);
		System.out.println((int) diferencia);
		diferencia = diferencia / (24 * 60 * 60 * 1000);
		return (int) diferencia;
	}
	/**
	 * Setters and Getters
	 * @return
	 */

	public ArrayList<AdminDTO> getUsers() {
		return listadmins;
	}

	public void setUsers(ArrayList<AdminDTO> users) {
		this.listadmins = users;
	}

	public PsychologistDAO getPydao() {
		return pydao;
	}

	public void setPydao(PsychologistDAO pydao) {
		this.pydao = pydao;
	}

	public AlcoholicDAO getAhdao() {
		return ahdao;
	}

	public void setAhdao(AlcoholicDAO ahdao) {
		this.ahdao = ahdao;
	}

	public ServicesDAO getSerdao() {
		return serdao;
	}

	public void setSerdao(ServicesDAO serdao) {
		this.serdao = serdao;
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
