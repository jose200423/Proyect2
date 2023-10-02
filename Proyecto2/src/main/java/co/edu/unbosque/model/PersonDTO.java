package co.edu.unbosque.model;

import java.util.Date;

/**
 * Clase que representa a una persona con información básica.
 * @autor Andres Meneses
 * @author Jose Munoz
 * @author Fabian Montano
 * @author Miguel pineda 
 * @author Yishaq Riveros
 */
public class PersonDTO {
	protected String name;
	private long identificationNumber;
	private Date birthday;
	private String cityOfBorn;
	/**
	 * metodo constructor
	 */

	public PersonDTO() {
		// TODO Auto-generated constructor stub
	}
	/**
     * Constructor que crea un objeto PersonDTO con información específica.
     * @param name                Nombre de la persona.
     * @param identificationNumber Número de identificación de la persona.
     * @param birthday            Fecha de nacimiento de la persona.
     * @param cityOfBorn          Ciudad de nacimiento de la persona.
     */

	public PersonDTO(String name, long identificationNumber, Date birthday, String cityOfBorn) {
		super();
		this.name = name;
		this.identificationNumber = identificationNumber;
		this.birthday = birthday;
		this.cityOfBorn = cityOfBorn;
	}
	/**
     * Obtiene el nombre del servicio.
     * @return El nombre del servicio.
     */
	

	public String getName() {
		return name;
	}
	/**
     * Establece el nombre del servicio.
     * @param name El nombre del servicio a establecer.
     */

	public void setName(String name) {
		this.name = name;
	}
	/**
     * Obtiene el número de identificación del servicio.
     * @return El número de identificación del servicio.
     */

	public long getIdentificationNumber() {
		return identificationNumber;
	}
	/**
     * Establece el número de identificación del servicio.
     * @param identificationNumber El número de identificación del servicio a establecer.
     */

	public void setIdentificationNumber(long identificationNumber) {
		this.identificationNumber = identificationNumber;
	}
	/**
     * Obtiene la fecha de nacimiento del servicio.
     * @return La fecha de nacimiento del servicio.
     */

	public Date getBirthday() {
		return birthday;
	}
	/**
     * Establece la fecha de nacimiento del servicio.
     * @param birthday La fecha de nacimiento del servicio a establecer.
     */

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
     * Obtiene la ciudad de nacimiento del servicio.
     * @return La ciudad de nacimiento del servicio.
     */

	public String getCityOfBorn() {
		return cityOfBorn;
	}
	 /**
     * Establece la ciudad de nacimiento del servicio.
     * @param cityOfBorn La ciudad de nacimiento del servicio a establecer.
     */

	public void setCityOfBorn(String cityOfBorn) {
		this.cityOfBorn = cityOfBorn;
	}
	/**
     * Representación en cadena de caracteres del objeto PersonDTO.
     * @return Una cadena de caracteres que describe el objeto PersonDTO.
     */

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("The name of this person is: " + getName() + "\n");
		sb.append("The identification number is: " + getIdentificationNumber() + "\n");
		sb.append("The Birthday is: " + getBirthday() + "\n");
		sb.append("It was borned on: " + getCityOfBorn() + "\n");
		return sb.toString();
	}

}
