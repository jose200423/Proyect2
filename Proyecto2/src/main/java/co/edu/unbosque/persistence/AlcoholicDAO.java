package co.edu.unbosque.persistence;

import java.util.ArrayList;

import co.edu.unbosque.controller.DBConnection;
import co.edu.unbosque.model.AlcoholicDTO;

public class AlcoholicDAO implements CRUDoperation{

	private ArrayList<AlcoholicDTO> listalcoholis;
	private DBConnection dbcon;
	
	public AlcoholicDAO() {
		listalcoholis = new ArrayList<>();
		dbcon = new DBConnection();
	}
	
	
	@Override
	public boolean create(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delateById(long identificationNumber) {
		// TODO Auto-generated method stub
		return 0;
	}

}
