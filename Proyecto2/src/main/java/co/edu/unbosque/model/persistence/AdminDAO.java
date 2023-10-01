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

public class AdminDAO implements CRUDoperation {
	private ArrayList<AdminDTO> listadmins;
	private PsychologistDAO pydao;
	private AlcoholicDAO ahdao;
	private ServicesDAO serdao;
	private DBConnection dbcon;

	public AdminDAO() {
		listadmins = new ArrayList<AdminDTO>();
		pydao = new PsychologistDAO();
		ahdao = new AlcoholicDAO();
		serdao = new ServicesDAO();
		dbcon = new DBConnection();
		read();
	}

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

	@Override
	public int updateByCc(long cc, String... args) {
		Date fecha = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/mm/dd");
		try {

			dbcon.initConnection();
			dbcon.setPreparedstatement(dbcon.getConect()
					.prepareStatement("UPDATE administrador SET  allname=?, cc=?, birthdate=?, city=? WHERE cc=?"));
			dbcon.getPreparedstatement().setString(1, args[0]);
			dbcon.getPreparedstatement().setLong(2, cc);
			dbcon.getPreparedstatement().setDate(3, Date.valueOf(args[2]));
			dbcon.getPreparedstatement().setString(4, args[3]);
			dbcon.getPreparedstatement().setLong(5, cc);

			dbcon.getPreparedstatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}

		for (int i = 0; i < listadmins.size(); i++) {
			if (listadmins.get(i).getIdentificationNumber() == cc) {
				listadmins.get(i).setName(args[0]);
				listadmins.get(i).setIdentificationNumber(cc);
				listadmins.get(i).setBirthday(Date.valueOf(args[2]));
				listadmins.get(i).setCityOfBorn(args[3]);
				return 0;
			}

		}
		return 1;
	}

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

	public boolean validate(String name, long cc) {
		for (AdminDTO u : listadmins) {
			if (u.getName().equals(name) && u.getIdentificationNumber() == cc) {
				return true;
			}
		}
		return false;
	}

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
	
	public boolean createPsychologist(Object obj) {
		boolean is = pydao.create(obj);
		if(is) {
			return true;
		}else {
			return false;
		}
	}

	public boolean createServices(Object obj) {
		boolean is = serdao.create(obj);
		if(is) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean createAlcoholics(Object obj) {
		boolean is = ahdao.create(obj);
		if(is) {
			return true;
		}else {
			return false;
		}
	}

	public String listPsychologist() {
		return pydao.readAll();
	}

	public String listServices() {
		return serdao.readAll();
	}

	public String listAlcoholics() {
		return ahdao.readAll();
	}
	
	
	public int deleteServices(long cc) {
      int i = serdao.deleteByCc(cc);
      if(i == 0) {
    	  return 0;
      }else{
    	  return 1;
      }
	}

	public int deleteePsychologist(long cc) {
		 int i = pydao.deleteByCc(cc);
	      if(i == 0) {
	    	  return 0;
	      }else{
	    	  return 1;
	      }
	}

	public int deletelistAlcoholics(long cc) {
		 int i = ahdao.deleteByCc(cc);
	      if(i == 0) {
	    	  return 0;
	      }else{
	    	  return 1;
	      }
	}
	
	public int updateByCcServices(long cc, String... args) {
		int i = serdao.updateByCc(cc,args);
	      if(i == 0) {
	    	  return 0;
	      }else{
	    	  return 1;
	      }
	}
	
	public int updateByCcPsychologist(long cc, String... args) {
		int i = pydao.updateByCc(cc,args);
	      if(i == 0) {
	    	  return 0;
	      }else{
	    	  return 1;
	      }
	}
	
	public int updateByCcAlcoholics(long cc, String... args) {
		int i = ahdao.updateByCc(cc,args);
	      if(i == 0) {
	    	  return 0;
	      }else{
	    	  return 1;
	      }
	}
	
	public int calcularDias(Date fechaInicial) {
		LocalDate fechaIniciallocal = fechaInicial.toLocalDate();
		LocalDate fechafinal = LocalDate.now();
		long diferencia = ChronoUnit.DAYS.between(fechaIniciallocal, fechafinal);
		System.out.println(diferencia);
		System.out.println((int) diferencia);
		diferencia = diferencia / (24 * 60 * 60 * 1000);
		return (int) diferencia;
	}

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

}
