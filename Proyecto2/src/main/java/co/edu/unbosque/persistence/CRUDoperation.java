package co.edu.unbosque.persistence;

public interface CRUDoperation {
	
	public boolean create(Object o);
	public String readAll();
	public int delateById(long identificationNumber);

}
