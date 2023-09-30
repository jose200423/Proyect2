package co.edu.unbosque.model.persistence;

public interface CRUDoperation {

	public boolean create(Object obj);

	public String readAll();

	public String readByCc(long cc);

	public int updateByCc(long cc, String... args);

	public int deleteByCc(long cc);

}
