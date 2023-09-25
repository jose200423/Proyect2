package co.edu.unbosque.persistence;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import co.edu.unbosque.controller.DBConnection;
import co.edu.unbosque.model.PersonDTO;

public class PersonDAO implements CRUDoperation{
	
	private ArrayList<PersonDTO> listpersons;
	private DBConnection dbcon;
	
	public PersonDAO() {
		listpersons = new ArrayList<>();
		dbcon = new DBConnection(); 
	}

	@Override
	public boolean create(Object o) {
	    PersonDTO newPerson = (PersonDTO) o;
	    dbcon.initConnection();
	    try {
	        dbcon.setPreparedStatement(dbcon.getConnect().prepareStatement("INSERT INTO person (allname, cc, birthdate, city) VALUES (?,?,?,?);"));
	        dbcon.getPreparedStatement().setString(1, newPerson.getName());
	        dbcon.getPreparedStatement().setLong(2, newPerson.getIdentificationNumber());
	        dbcon.getPreparedStatement().setDate(3, (Date) newPerson.getBirthday());
	        dbcon.getPreparedStatement().setString(4, newPerson.getCityOfBorn());
	        dbcon.getPreparedStatement().executeUpdate();
	        dbcon.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    listpersons.add(newPerson);
	    return true;
	}

	@Override
	public String readAll() {
		String exit = "";
		listpersons.clear();
		dbcon.initConnection();
		try {
			dbcon.setStatement(dbcon.getConnect().createStatement());
			dbcon.setResultset(dbcon.getStatement().executeQuery("SELECT * FROM person;"));
			while (dbcon.getResultset().next()) {
				int  id = dbcon.getResultset().getInt("id");
				String name = dbcon.getResultset().getString("allname");
				long  identificationNumber = dbcon.getResultset().getLong("cc");
				Date birthday = dbcon.getResultset().getDate("birthdate");
				String cityOfBorn = dbcon.getResultset().getString("city");
				listpersons.add(new PersonDTO(name, identificationNumber, birthday, cityOfBorn));
			}
			dbcon.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (PersonDTO person : listpersons) {
			exit += person.toString();
		}

		return exit;
		
	}

	@Override
	public int delateById(long identificationNumber) {
		dbcon.initConnection();
		try {
			dbcon.setPreparedStatement(dbcon.getConnect().prepareStatement("DELETE FROM person WHERE cc=?;"));
			dbcon.getPreparedStatement().setLong(1,identificationNumber);
			dbcon.getPreparedStatement().executeUpdate();
			dbcon.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < listpersons.size(); i++) {
			if (listpersons.get(i).getIdentificationNumber()==identificationNumber) {
				listpersons.remove(i);
				return 0;
			}
		}
		return 1;
	}

	public ArrayList<PersonDTO> getListpersons() {
		return listpersons;
	}

	public void setListpersons(ArrayList<PersonDTO> listpersons) {
		this.listpersons = listpersons;
	}

	public DBConnection getDbcon() {
		return dbcon;
	}

	public void setDbcon(DBConnection dbcon) {
		this.dbcon = dbcon;
	}
	
	

}
