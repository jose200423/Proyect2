package co.edu.unbosque.persistence;

import java.util.ArrayList;

import co.edu.unbosque.controller.DBConnection;
import co.edu.unbosque.model.ServiceDTO;

public class ServiceDAO implements CRUDoperation {

	private ArrayList<ServiceDTO> listservices;

	private DBConnection dbcon;

	public ServiceDAO() {
		listservices = new ArrayList<>();
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
