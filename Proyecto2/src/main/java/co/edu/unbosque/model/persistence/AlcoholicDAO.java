package co.edu.unbosque.model.persistence;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import co.edu.unbosque.controller.DBConnection;
import co.edu.unbosque.model.AlcoholicDTO;
import co.edu.unbosque.model.PersonDTO;

public class AlcoholicDAO {
	private ArrayList<AlcoholicDTO> ahdao;
	private DBConnection dbcon;

	public AlcoholicDAO() {
		ahdao = new ArrayList<AlcoholicDTO>();
		dbcon = new DBConnection();
	}

	public void create(Object obj) {
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

	public String readAll() {
		ahdao.clear();
		// solicitudes
		dbcon.initConnection();
		try {
			dbcon.setStatement(dbcon.getConect().createStatement());
			dbcon.setResulset(dbcon.getStatement().executeQuery("SELECT * FROM alcoholic"));
			while (dbcon.getResulset().next()) {
				String name = dbcon.getResulset().getString("allname");
				int cedula = dbcon.getResulset().getInt("cc");
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

	public int deleteByCc(int id) {
		dbcon.initConnection();
		try {
			// insercion y cambios
			dbcon.setPreparedstatement(dbcon.getConect().prepareStatement("DELETE FROM alcoholic WHERE cc=?"));
			dbcon.getPreparedstatement().setInt(1, id);
			dbcon.getPreparedstatement().executeUpdate();// vaya y ponga eso en el MySQL
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < ahdao.size(); i++) {
			if (ahdao.get(i).getIdentificationNumber() == id) {
				ahdao.remove(i);
			}

		}
		return 1;
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public String readByCc(int cc) {
		dbcon.initConnection();
		try {
			dbcon.setStatement(dbcon.getConect().createStatement());
			dbcon.setResulset(dbcon.getStatement().executeQuery("SELECT * FROM alcoholic WHERE cc = " + cc));
			if (dbcon.getResulset().next()) {
				String name = dbcon.getResulset().getString("allname");
				int cedula = dbcon.getResulset().getInt("cc");
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

	public int updateByCc(int cc, String... args) {
		Date fecha = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/mm/dd");
		try {

			dbcon.initConnection();
			// insercion y cambios
			dbcon.setPreparedstatement(dbcon.getConect().prepareStatement(
					"UPDATE alcoholic SET  allname=?, cc=?, birthdate=?, city=?, sessions=?, nickname=? WHERE cc=?"));
			dbcon.getPreparedstatement().setString(1, args[0]);
			dbcon.getPreparedstatement().setInt(2, cc);
			dbcon.getPreparedstatement().setDate(3, Date.valueOf(args[2]));
			dbcon.getPreparedstatement().setString(4, args[3]);
			dbcon.getPreparedstatement().setInt(5, Integer.parseInt(args[4]));
			dbcon.getPreparedstatement().setString(6, args[5]);
			dbcon.getPreparedstatement().setInt(7, cc);

			dbcon.getPreparedstatement().executeUpdate();// vaya y ponga eso en el MySQL
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < ahdao.size(); i++) {
			if (ahdao.get(i).getIdentificationNumber() == cc) {
				ahdao.get(i).setName(args[0]);
				ahdao.get(i).setIdentificationNumber(cc);
				ahdao.get(i).setbirthday(Date.valueOf(args[2]));
				ahdao.get(i).setCityOfBorn(args[3]);
				ahdao.get(i).setSessionsPresent(Integer.parseInt(args[4]));
				ahdao.get(i).setNickname(args[5]);
				return 0;
			}

		}
		return 1;
	}

}
