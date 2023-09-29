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

public class PsychologistDAO {

	private ArrayList<ServicesDTO> serv;
	private ArrayList<PsychologistDTO> pydao;
	private ArrayList<AlcoholicDTO> ahdao;
	private DBConnection dbcon;

	public PsychologistDAO() {
		serv = new ArrayList<ServicesDTO>();
		pydao = new ArrayList<PsychologistDTO>();
		ahdao = new ArrayList<AlcoholicDTO>();
		dbcon = new DBConnection();
	}

	public void createPsychologist(Object obj) {
		PsychologistDTO newUser = (PsychologistDTO) obj;
		dbcon.initConnection();
		try {
			// insercion y cambios
			dbcon.setPreparedstatement(dbcon.getConect().prepareStatement(
					"INSERT INTO psychologist (allname, cc, birthdate, city, graduation, days, supportedsessions, salary) VALUES(?,?,?,?,?,?,?,?)"));
			dbcon.getPreparedstatement().setString(1, newUser.getName());
			dbcon.getPreparedstatement().setLong(2, newUser.getIdentificationNumber());
			dbcon.getPreparedstatement().setDate(3, (Date) newUser.getbirthday());
			dbcon.getPreparedstatement().setString(4, newUser.getCityOfBorn());
			dbcon.getPreparedstatement().setInt(5, newUser.getGraduationYear());
			dbcon.getPreparedstatement().setInt(6, newUser.getDaysSinceGraduation());
			dbcon.getPreparedstatement().setInt(7, newUser.getSupportedSessions());
			dbcon.getPreparedstatement().setInt(8, newUser.getSalary());
			dbcon.getPreparedstatement().executeUpdate();// vaya y ponga eso en el MySQL
		} catch (SQLException e) {
			e.printStackTrace();
		}

		pydao.add((PsychologistDTO) obj);
	}

	public String readByCc(int cc) {
		dbcon.initConnection();
		try {
			dbcon.setStatement(dbcon.getConect().createStatement());
			dbcon.setResulset(dbcon.getStatement().executeQuery("SELECT * FROM psychologist WHERE cc = " + cc));
			if (dbcon.getResulset().next()) {
				String name = dbcon.getResulset().getString("allname");
				long cedula = dbcon.getResulset().getLong("cc");
				Date nivel = dbcon.getResulset().getDate("birthdate");
				String city = dbcon.getResulset().getString("city");
				int grade = dbcon.getResulset().getInt("graduation");
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

	public int updateByCc(int cc, String... args) {
		Date fecha = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/mm/dd");
		try {

			dbcon.initConnection();
			// insercion y cambios
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

			dbcon.getPreparedstatement().executeUpdate();// vaya y ponga eso en el MySQL
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < pydao.size(); i++) {
			if (pydao.get(i).getIdentificationNumber() == cc) {
				pydao.get(i).setName(args[0]);
				pydao.get(i).setIdentificationNumber(cc);
				pydao.get(i).setbirthday(Date.valueOf(args[2]));
				pydao.get(i).setCityOfBorn(args[3]);
				pydao.get(i).setGraduationYear(Integer.valueOf(args[4]));
				pydao.get(i).setDaysSinceGraduation(Integer.valueOf(args[5]));
				pydao.get(i).setSalary(Integer.valueOf(args[6]));
				pydao.get(i).setSupportedSessions(Integer.valueOf(args[7]));

				return 0;
			}

		}
		return 1;
	}

	public int deleteByCc(int cc) {
		dbcon.initConnection();
		try {
			// insercion y cambios
			dbcon.setPreparedstatement(dbcon.getConect().prepareStatement("DELETE FROM psychologist WHERE cc=?"));
			dbcon.getPreparedstatement().setLong(1, cc);
			dbcon.getPreparedstatement().executeUpdate();// vaya y ponga eso en el MySQL
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < pydao.size(); i++) {
			if (pydao.get(i).getIdentificationNumber() == cc) {
				pydao.remove(i);
			}

		}
		return 1;
	}

	public String readAll() {
		pydao.clear();
		// solicitudes
		dbcon.initConnection();
		try {
			dbcon.setStatement(dbcon.getConect().createStatement());
			dbcon.setResulset(dbcon.getStatement().executeQuery("SELECT * FROM psychologist"));
			while (dbcon.getResulset().next()) {
				String name = dbcon.getResulset().getString("allname");
				long cedula = dbcon.getResulset().getLong("cc");
				Date fecha = dbcon.getResulset().getDate("birthdate");
				String city = dbcon.getResulset().getString("city");
				int graduation = dbcon.getResulset().getInt("graduation");
				int days = dbcon.getResulset().getInt("days");
				int salary = dbcon.getResulset().getInt("salary");
				int sessions = dbcon.getResulset().getInt("supportedsessions");
				pydao.add(new PsychologistDTO(name, cedula, fecha, city, graduation, days, salary, sessions));
			}
			dbcon.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String temporal = "";
		for (PsychologistDTO usuario : pydao) {
			temporal += usuario.toString();
		}
		return temporal;
	}

	// -----------------------------------------------------------------------

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

	public void read() {
		pydao.clear();
		// solicitudes
		dbcon.initConnection();
		try {
			dbcon.setStatement(dbcon.getConect().createStatement());
			dbcon.setResulset(dbcon.getStatement().executeQuery("SELECT * FROM psychologist"));
			while (dbcon.getResulset().next()) {
				String name = dbcon.getResulset().getString("allname");
				long cedula = dbcon.getResulset().getLong("cc");
				Date fecha = dbcon.getResulset().getDate("birthdate");
				String city = dbcon.getResulset().getString("city");
				int graduation = dbcon.getResulset().getInt("graduation");
				int days = dbcon.getResulset().getInt("days");
				int salary = dbcon.getResulset().getInt("salary");
				int sessions = dbcon.getResulset().getInt("supportedsessions");
				pydao.add(new PsychologistDTO(name, cedula, fecha, city, graduation, days, salary, sessions));
			}
			dbcon.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
