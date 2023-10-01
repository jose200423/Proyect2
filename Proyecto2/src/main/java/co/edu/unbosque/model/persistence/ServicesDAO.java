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

public class ServicesDAO implements CRUDoperation {
	private ArrayList<ServicesDTO> serv;
	private AlcoholicDAO ahdao;
	private DBConnection dbcon;

	public ServicesDAO() {
		serv = new ArrayList<ServicesDTO>();
		ahdao = new AlcoholicDAO();
		dbcon = new DBConnection();
		read();
	}

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

	@Override
	public int updateByCc(long cc, String... args) {
		read();
		Date fecha = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/mm/dd");
		try {

			dbcon.initConnection();
			dbcon.setPreparedstatement(dbcon.getConect().prepareStatement(
					"UPDATE services SET  allname=?, cc=?, birthdate=?, city=?, salary=?, cleanup=? WHERE cc=?"));
			dbcon.getPreparedstatement().setString(1, args[0]);
			dbcon.getPreparedstatement().setLong(2, cc);
			dbcon.getPreparedstatement().setDate(3, Date.valueOf(args[2]));
			dbcon.getPreparedstatement().setString(4, args[3]);
			dbcon.getPreparedstatement().setInt(5, Integer.valueOf(args[4]));
			dbcon.getPreparedstatement().setInt(6, Integer.valueOf(args[5]));
			dbcon.getPreparedstatement().setLong(7, cc);
			dbcon.getPreparedstatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}

		for (int i = 0; i < serv.size(); i++) {
			if (serv.get(i).getIdentificationNumber() == cc) {
				serv.get(i).setName(args[0]);
				serv.get(i).setIdentificationNumber(cc);
				serv.get(i).setBirthday(Date.valueOf(args[2]));
				serv.get(i).setCityOfBorn(args[3]);
				serv.get(i).setSalary(Integer.valueOf(args[4]));
				serv.get(i).setSessionsCleaned(Integer.valueOf(args[5]));
				return 0;
			}

		}
		return 1;
	}

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
	
	public boolean validate(String name, long cc) {
		for (ServicesDTO u : serv) {
			if (u.getName().equals(name) && u.getIdentificationNumber() == cc) {
				return true;
			}
		}
		return false;
	}
	
	public boolean createAlcoholics(Object obj) {
		boolean is = ahdao.create(obj);
		if (is) {
			return true;
		} else {
			return false;
		}
	}

	public String readAllAlcoholics() {
		return ahdao.readAll();
	}

	public int deleteAlcoholics(long cc) {
		  int i = ahdao.deleteByCc(cc);
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

}
