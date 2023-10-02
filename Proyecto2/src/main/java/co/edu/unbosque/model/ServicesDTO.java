package co.edu.unbosque.model;

import java.util.Date;

/**
 * Esta clase representa un objeto ServicesDTO que hereda atributos de la clase PersonDTO.
 * Contiene información sobre servicios realizados, incluyendo salario y número de sesiones limpiadas.
 * 
 * @autor Andres Meneses
 * @author Jose Munoz
 * @author Fabian Montano
 * @author Miguel pineda 
 * @author Yishaq Riveros
 */
public class ServicesDTO extends PersonDTO {
	private int salary;
	private int sessionsCleaned;
	/**
	 * constructor
	 */

	public ServicesDTO() {
	}
	/**
     * Constructor de ServicesDTO con salario y número de sesiones limpiadas.
     *
     * @param salary El salario del proveedor de servicios.
     * @param sessionsCleaned El número de sesiones limpiadas por el proveedor.
     */

	public ServicesDTO(int salary, int sessionsCleaned) {
		super();
		this.salary = salary;
		this.sessionsCleaned = sessionsCleaned;
	}
	/**
     * Constructor de ServicesDTO con información básica de una persona.
     *
     * @param name El nombre de la persona.
     * @param identificationNumber El número de identificación de la persona.
     * @param birthday La fecha de nacimiento de la persona.
     * @param cityOfBorn La ciudad de nacimiento de la persona.
     */

	public ServicesDTO(String name, long identificationNumber, Date birthday, String cityOfBorn) {
		super(name, identificationNumber, birthday, cityOfBorn);
	}
	/**
     * Constructor de ServicesDTO con información completa de una persona y detalles de servicios.
     *
     * @param name El nombre de la persona.
     * @param identificationNumber El número de identificación de la persona.
     * @param birthday La fecha de nacimiento de la persona.
     * @param cityOfBorn La ciudad de nacimiento de la persona.
     * @param salary El salario del proveedor de servicios.
     * @param sessionsCleaned El número de sesiones limpiadas por el proveedor.
     */

	public ServicesDTO(String name, long identificationNumber, Date birthday, String cityOfBorn, int salary,
			int sessionsCleaned) {
		super(name, identificationNumber, birthday, cityOfBorn);
		this.salary = salary;
		this.sessionsCleaned = sessionsCleaned;
	}
	/**
     * Obtiene el salario del servicio.
     */

	public int getSalary() {
		return salary;
	}
	/**
     * Establece el salario del servicio.
     */

	public void setSalary(int salary) {
		this.salary = salary;
	}
	/**
     * Obtiene el número de sesiones de limpieza realizadas por el servicio.
     */

	public int getSessionsCleaned() {
		return sessionsCleaned;
	}
	/**
     * Establece el número de sesiones de limpieza realizadas por el servicio.
     * @param sessionsCleaned El número de sesiones de limpieza a establecer.
     */

	public void setSessionsCleaned(int sessionsCleaned) {
		this.sessionsCleaned = sessionsCleaned;
	}
	/**
     * Devuelve una representación de cadena de este objeto ServicesDTO.
     *
     * @return Una cadena que representa este objeto ServicesDTO.
     */

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		sb.append("The salary is: " + getSalary() + "\n");
		sb.append("Number of sessions cleaned: " + getSessionsCleaned() + "\n");
		return sb.toString();
	}

}
