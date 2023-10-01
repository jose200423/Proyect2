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

public class AlcoholicDAO implements CRUDoperation {
	private ArrayList<AlcoholicDTO> listahcos;
	private DBConnection dbcon;

	public AlcoholicDAO() {
		listahcos = new ArrayList<AlcoholicDTO>();
		dbcon = new DBConnection();
		read();
	}

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

	@Override
	public int updateByCc(long cc, String... args) {
		Date fecha = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/mm/dd");
		try {

			dbcon.initConnection();
			dbcon.setPreparedstatement(dbcon.getConect().prepareStatement(
					"UPDATE alcoholic SET  allname=?, cc=?, birthdate=?, city=?, sessions=?, nickname=? WHERE cc=?"));
			dbcon.getPreparedstatement().setString(1, args[0]);
			dbcon.getPreparedstatement().setLong(2, cc);
			dbcon.getPreparedstatement().setDate(3, Date.valueOf(args[2]));
			dbcon.getPreparedstatement().setString(4, args[3]);
			dbcon.getPreparedstatement().setInt(5, Integer.parseInt(args[4]));
			dbcon.getPreparedstatement().setString(6, args[5]);
			dbcon.getPreparedstatement().setLong(7, cc);
			dbcon.getPreparedstatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
		for (int i = 0; i < listahcos.size(); i++) {
			if (listahcos.get(i).getIdentificationNumber() == cc) {
				listahcos.get(i).setName(args[0]);
				listahcos.get(i).setIdentificationNumber(cc);
				listahcos.get(i).setBirthday(Date.valueOf(args[2]));
				listahcos.get(i).setCityOfBorn(args[3]);
				listahcos.get(i).setSessionsPresent(Integer.parseInt(args[4]));
				listahcos.get(i).setNickname(args[5]);
				return 0;
			}

		}
		return 1;
	}

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
	
	public boolean validate(String name, long cc) {
		for (AlcoholicDTO u : listahcos) {
			if (u.getName().equals(name) && u.getIdentificationNumber() == cc) {
				return true;
			}
		}
		return false;
	}

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

}
