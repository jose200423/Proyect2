package co.edu.unbosque.model.persistence;

public interface CRUDoperation {

	public void create(Object obj);

	public String readAll();

	public String readByCc(int cc);

	public int updateByCc(int cc, String... args);

	public int deleteByCc(int cc);

}
