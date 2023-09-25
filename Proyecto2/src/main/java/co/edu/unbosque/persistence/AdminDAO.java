package co.edu.unbosque.persistence;

import java.util.ArrayList;

import co.edu.unbosque.controller.DBConnection;
import co.edu.unbosque.model.AdminDTO;

public class AdminDAO implements CRUDoperation{

	private ArrayList<AdminDTO> listAdmins;
	private DBConnection dbcon;
	
	public AdminDAO() {
		listAdmins = new ArrayList<>();
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
