package co.edu.unbosque.model.persistence;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import co.edu.unbosque.controller.DBConnection;
import co.edu.unbosque.model.AdminDTO;
import co.edu.unbosque.model.AlcoholicDTO;
import co.edu.unbosque.model.PsychologistDTO;
import co.edu.unbosque.model.ServicesDTO;
import co.edu.unbosque.model.AdminDTO;

public class AdminDAO implements CRUDoperation {
	private ArrayList<AdminDTO> users;
	private PsychologistDAO pydao;
	private AlcoholicDAO ahdao;
	private ServicesDAO serdao;
	private DBConnection dbcon;

	public AdminDAO() {
		users = new ArrayList<AdminDTO>();
		pydao = new PsychologistDAO();
		ahdao = new AlcoholicDAO();
		serdao = new ServicesDAO();
		dbcon = new DBConnection();
	}

	public void createPsychologist(Object obj) {
		pydao.createParticipants(obj);
	}

	public void createServices(Object obj) {
		serdao.createServices(obj);
	}

	public void createParticipants(Object obj) {
		ahdao.create(obj);
	}

	public String listPsychologist() {
		return pydao.readAll();
	}

	public String listServices() {
		return serdao.readAllServices();
	}

	public String listParticipants() {
		return ahdao.readAll();
	}

	@Override
	public boolean create(Object obj) {
		AdminDTO newUser = (AdminDTO) obj;
		read();
		for (AdminDTO adminDTO : users) {
			if (adminDTO.getIdentificationNumber() == newUser.getIdentificationNumber()) {
				return false;
			}
		}
		dbcon.initConnection();
		try {
			// insercion y cambios
			dbcon.setPreparedstatement(dbcon.getConect()
					.prepareStatement("INSERT INTO administrador (allname, cc, birthdate, city) VALUES(?,?,?,?)"));
			dbcon.getPreparedstatement().setString(1, newUser.getName());
			dbcon.getPreparedstatement().setLong(2, newUser.getIdentificationNumber());
			dbcon.getPreparedstatement().setDate(3, (Date) newUser.getbirthday());
			dbcon.getPreparedstatement().setString(4, newUser.getCityOfBorn());
			dbcon.getPreparedstatement().executeUpdate();// vaya y ponga eso en el MySQL
		} catch (SQLException e) {
			e.printStackTrace();
		}

		users.add((AdminDTO) obj);
		return true;
	}

	@Override
	public String readAll() {
		users.clear();
		// solicitudes
		dbcon.initConnection();
		try {
			dbcon.setStatement(dbcon.getConect().createStatement());
			dbcon.setResulset(dbcon.getStatement().executeQuery("SELECT * FROM administrador"));
			while (dbcon.getResulset().next()) {
				String name = dbcon.getResulset().getString("allname");
				int cedula = dbcon.getResulset().getInt("cc");
				Date fecha = dbcon.getResulset().getDate("birthdate");
				String city = dbcon.getResulset().getString("city");
				users.add(new AdminDTO(name, cedula, fecha, city));
			}
			dbcon.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String temporal = "";
		for (AdminDTO usuario : users) {
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
			// insercion y cambios
			dbcon.setPreparedstatement(dbcon.getConect()
					.prepareStatement("UPDATE administrador SET  allname=?, cc=?, birthdate=?, city=? WHERE cc=?"));
			dbcon.getPreparedstatement().setString(1, args[0]);
			dbcon.getPreparedstatement().setLong(2, cc);
			dbcon.getPreparedstatement().setDate(3, Date.valueOf(args[2]));
			dbcon.getPreparedstatement().setString(4, args[3]);
			dbcon.getPreparedstatement().setLong(5, cc);

			dbcon.getPreparedstatement().executeUpdate();// vaya y ponga eso en el MySQL
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getIdentificationNumber() == cc) {
				users.get(i).setName(args[0]);
				users.get(i).setIdentificationNumber(cc);
				users.get(i).setbirthday(Date.valueOf(args[2]));
				users.get(i).setCityOfBorn(args[3]);
				return 0;
			}

		}
		return 1;
	}

	@Override
	public int deleteByCc(long id) {
		dbcon.initConnection();
		try {
			// insercion y cambios
			dbcon.setPreparedstatement(dbcon.getConect().prepareStatement("DELETE FROM administrador WHERE cc=?"));
			dbcon.getPreparedstatement().setLong(1, id);
			dbcon.getPreparedstatement().executeUpdate();// vaya y ponga eso en el MySQL
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getIdentificationNumber() == id) {
				users.remove(i);
			}

		}
		return 1;
	}

	public boolean validate(String name, String cc) {
		int ccInt = Integer.parseInt(cc);
		for (AdminDTO u : users) {
			if (u.getName().equals(name) && u.getIdentificationNumber() == ccInt) {
				return true;
			}
		}
		return false;
	}

	public void read() {
		users.clear();
		// solicitudes
		dbcon.initConnection();
		try {
			dbcon.setStatement(dbcon.getConect().createStatement());
			dbcon.setResulset(dbcon.getStatement().executeQuery("SELECT * FROM administrador"));
			while (dbcon.getResulset().next()) {
				String name = dbcon.getResulset().getString("allname");
				int cedula = dbcon.getResulset().getInt("cc");
				Date fecha = dbcon.getResulset().getDate("birthdate");
				String city = dbcon.getResulset().getString("city");
				users.add(new AdminDTO(name, cedula, fecha, city));
			}
			dbcon.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<AdminDTO> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<AdminDTO> users) {
		this.users = users;
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
