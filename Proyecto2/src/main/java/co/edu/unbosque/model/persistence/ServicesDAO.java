package co.edu.unbosque.model.persistence;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import co.edu.unbosque.controller.DBConnection;
import co.edu.unbosque.model.AlcoholicDTO;
import co.edu.unbosque.model.PersonDTO;
import co.edu.unbosque.model.PsychologistDTO;
import co.edu.unbosque.model.ServicesDTO;

public class ServicesDAO {
	private ArrayList<ServicesDTO> serv;
	private ArrayList<AlcoholicDTO> ahdao;
	private DBConnection dbcon;

	public ServicesDAO() {
		serv = new ArrayList<ServicesDTO>();
		ahdao = new ArrayList<AlcoholicDTO>();
		dbcon = new DBConnection();
	}

	public void createServices(Object obj) {
		ServicesDTO newUser = (ServicesDTO) obj;
		dbcon.initConnection();
		try {
			// insercion y cambios
			dbcon.setPreparedstatement(dbcon.getConect().prepareStatement(
					"INSERT INTO services (allname, cc, birthdate, city, salary, cleanup) VALUES(?,?,?,?,?,?)"));
			dbcon.getPreparedstatement().setString(1, newUser.getName());
			dbcon.getPreparedstatement().setLong(2, newUser.getIdentificationNumber());
			dbcon.getPreparedstatement().setDate(3, (Date) newUser.getbirthday());
			dbcon.getPreparedstatement().setString(4, newUser.getCityOfBorn());
			dbcon.getPreparedstatement().setInt(5, newUser.getSalary());
			dbcon.getPreparedstatement().setInt(6, newUser.getSessionsCleaned());
			dbcon.getPreparedstatement().executeUpdate();// vaya y ponga eso en el MySQL
		} catch (SQLException e) {
			e.printStackTrace();
		}

		serv.add((ServicesDTO) obj);
	}

	public String readAllServices() {
		serv.clear();
		// solicitudes
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

	public void createParticipants(Object obj) {
		AlcoholicDTO newUser = (AlcoholicDTO) obj;
		dbcon.initConnection();
		try {
			// insercion y cambios
			dbcon.setPreparedstatement(dbcon.getConect().prepareStatement(
					"INSERT INTO alcoholic (allname, cc, birthdate, city, sessions, nickname) VALUES(?,?,?,?,?,?)"));
			dbcon.getPreparedstatement().setString(1, newUser.getName());
			dbcon.getPreparedstatement().setLong(2, newUser.getIdentificationNumber());
			dbcon.getPreparedstatement().setDate(3, (Date) newUser.getbirthday());
			dbcon.getPreparedstatement().setString(4, newUser.getCityOfBorn());
			dbcon.getPreparedstatement().setInt(5, newUser.getSessionsPresent());
			dbcon.getPreparedstatement().setString(6, newUser.getNickname());
			dbcon.getPreparedstatement().executeUpdate();// vaya y ponga eso en el MySQL
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ahdao.add((AlcoholicDTO) obj);
	}

	public String readAllAlcoholics() {
		ahdao.clear();
		// solicitudes
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
				ahdao.add(new AlcoholicDTO(name, cedula, fecha, city, sessions, nickname));
			}
			dbcon.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String temporal = "";
		for (AlcoholicDTO usuario : ahdao) {
			temporal += usuario.toString();
		}
		return temporal;
	}
	/*
	 * ------------------------------------------------------------------
	 */

	public String readByCc(int cc) {
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

	public int updateByCc(int cc, String... args) {
		Date fecha = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/mm/dd");
		try {

			dbcon.initConnection();
			// insercion y cambios
			dbcon.setPreparedstatement(dbcon.getConect().prepareStatement(
					"UPDATE services SET  allname=?, cc=?, birthdate=?, city=?, salary=?, cleanup=? WHERE cc=?"));
			dbcon.getPreparedstatement().setString(1, args[0]);
			dbcon.getPreparedstatement().setLong(2, cc);
			dbcon.getPreparedstatement().setDate(3, Date.valueOf(args[2]));
			dbcon.getPreparedstatement().setString(4, args[3]);
			dbcon.getPreparedstatement().setInt(5, Integer.valueOf(args[4]));
			dbcon.getPreparedstatement().setInt(6, Integer.valueOf(args[5]));
			dbcon.getPreparedstatement().setLong(7, cc);

			dbcon.getPreparedstatement().executeUpdate();// vaya y ponga eso en el MySQL
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < serv.size(); i++) {
			if (serv.get(i).getIdentificationNumber() == cc) {
				serv.get(i).setName(args[0]);
				serv.get(i).setIdentificationNumber(cc);
				serv.get(i).setbirthday(Date.valueOf(args[2]));
				serv.get(i).setCityOfBorn(args[3]);
				serv.get(i).setSalary(Integer.valueOf(args[4]));
				serv.get(i).setSessionsCleaned(Integer.valueOf(args[5]));
				return 0;
			}

		}
		return 1;
	}

	public int deleteByCc(int cc) {
		dbcon.initConnection();
		try {
			// insercion y cambios
			dbcon.setPreparedstatement(dbcon.getConect().prepareStatement("DELETE FROM services WHERE cc=?"));
			dbcon.getPreparedstatement().setLong(1, cc);
			dbcon.getPreparedstatement().executeUpdate();// vaya y ponga eso en el MySQL
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < serv.size(); i++) {
			if (serv.get(i).getIdentificationNumber() == cc) {
				serv.remove(i);
			}

		}
		return 1;
	}
	public void readServices() {
		serv.clear();
		// solicitudes
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
	
	

}
