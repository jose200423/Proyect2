package co.edu.unbosque.model.persistence;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import co.edu.unbosque.controller.DBConnection;
import co.edu.unbosque.model.AdminDTO;
import co.edu.unbosque.model.PersonDTO;

public class PersonDAO implements CRUDoperation {
	private ArrayList<PersonDTO> users;
	private DBConnection dbcon;

	public PersonDAO() {
		users = new ArrayList<PersonDTO>();
		dbcon = new DBConnection();
	}

	@Override
	public boolean create(Object obj) {
		 read();
		PersonDTO newUser = (PersonDTO) obj;
	    for (PersonDTO u : users) {
	        // Compara el número de cédula del usuario existente con el del nuevo usuario
	        if (u.getIdentificationNumber() == newUser.getIdentificationNumber()) {
	            return false;
	        }
	    }

	    dbcon.initConnection();
	    try {
	        // inserción y cambios
	        dbcon.setPreparedstatement(dbcon.getConect()
	                .prepareStatement("INSERT INTO person (allname, cc, birthdate, city) VALUES(?,?,?,?)"));
	        dbcon.getPreparedstatement().setString(1, newUser.getName());
	        dbcon.getPreparedstatement().setLong(2, newUser.getIdentificationNumber());
	        dbcon.getPreparedstatement().setDate(3, (Date) newUser.getbirthday());
	        dbcon.getPreparedstatement().setString(4, newUser.getCityOfBorn());
	        dbcon.getPreparedstatement().executeUpdate(); // Realizar la inserción en MySQL
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }

	    // Agregar el nuevo usuario a la lista
	    users.add(newUser);
	    return true;
	}


	@Override
	public String readAll() {
		users.clear();
		// solicitudes
		dbcon.initConnection();
		try {
			dbcon.setStatement(dbcon.getConect().createStatement());
			dbcon.setResulset(dbcon.getStatement().executeQuery("SELECT * FROM person"));
			while (dbcon.getResulset().next()) {
				String name = dbcon.getResulset().getString("allname");
				long cedula = dbcon.getResulset().getLong("cc");
				Date fecha = dbcon.getResulset().getDate("birthdate");
				String city = dbcon.getResulset().getString("city");
				users.add(new PersonDTO(name, cedula, fecha, city));
			}
			dbcon.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String temporal = "";
		for (PersonDTO usuario : users) {
			temporal += usuario.toString();
		}
		return temporal;
	}

	@Override
	public String readByCc(long cc) {
		dbcon.initConnection();
		try {
			dbcon.setStatement(dbcon.getConect().createStatement());
			dbcon.setResulset(dbcon.getStatement().executeQuery("SELECT * FROM person WHERE cc = " + cc));
			if (dbcon.getResulset().next()) {
				String name = dbcon.getResulset().getString("allname");
				long cedula = dbcon.getResulset().getLong("cc");
				Date nivel = dbcon.getResulset().getDate("birthdate");
				String city = dbcon.getResulset().getString("city");
				dbcon.close();
				PersonDTO person = new PersonDTO(name, cedula, nivel, city);
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
		SimpleDateFormat sf = new SimpleDateFormat("yyyy//dd");
		try {

			dbcon.initConnection();
			// insercion y cambios
			dbcon.setPreparedstatement(dbcon.getConect()
					.prepareStatement("UPDATE person SET  allname=?, cc=?, birthdate=?, city=? WHERE cc=?"));
			dbcon.getPreparedstatement().setString(1, args[0]);
			dbcon.getPreparedstatement().setLong(2, cc);
			dbcon.getPreparedstatement().setDate(3, Date.valueOf(args[1]));
			dbcon.getPreparedstatement().setString(4, args[2]);
			dbcon.getPreparedstatement().setLong(5, cc);

			dbcon.getPreparedstatement().executeUpdate();// vaya y ponga eso en el MySQL
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getIdentificationNumber() == cc) {
				users.get(i).setName(args[0]);
				users.get(i).setIdentificationNumber(cc);
				users.get(i).setbirthday(Date.valueOf(args[1]));
				users.get(i).setCityOfBorn(args[2]);
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
			dbcon.setPreparedstatement(dbcon.getConect().prepareStatement("DELETE FROM person WHERE cc=?"));
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

	public void read() {
		users.clear();
		// solicitudes
		dbcon.initConnection();
		try {
			dbcon.setStatement(dbcon.getConect().createStatement());
			dbcon.setResulset(dbcon.getStatement().executeQuery("SELECT * FROM person"));
			while (dbcon.getResulset().next()) {
				String name = dbcon.getResulset().getString("allname");
				long cedula = dbcon.getResulset().getLong("cc");
				Date fecha = dbcon.getResulset().getDate("birthdate");
				String city = dbcon.getResulset().getString("city");
				users.add(new PersonDTO(name, cedula, fecha, city));
			}
			dbcon.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<PersonDTO> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<PersonDTO> users) {
		this.users = users;
	}

	public DBConnection getDbcon() {
		return dbcon;
	}

	public void setDbcon(DBConnection dbcon) {
		this.dbcon = dbcon;
	}

}
