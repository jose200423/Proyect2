package co.edu.unbosque.model;

import java.util.Date;

/**
 * Clase que representa a un psicólogo con información adicional.
 * Extiende la clase PersonDTO.
 * @autor Andres Meneses
 * @author Jose Munoz
 * @author Fabian Montano
 * @author Miguel pineda 
 * @author Yishaq Riveros
 */
public class PsychologistDTO extends PersonDTO {
	private Date graduationYear;
	private int daysSinceGraduation;
	private int supportedSessions;
	private int salary;
	/**
	 * Constructor
	 */

	public PsychologistDTO() {
	}
	/**
     * Constructor que crea un objeto PsychologistDTO con información básica.
     * @param name                Nombre del psicólogo.
     * @param identificationNumber Número de identificación del psicólogo.
     * @param birthday            Fecha de nacimiento del psicólogo.
     * @param cityOfBorn          Ciudad de nacimiento del psicólogo.
     */

	public PsychologistDTO(String name, long identificationNumber, Date birthday, String cityOfBorn) {
		super(name, identificationNumber, birthday, cityOfBorn);
	}
	/**
     * Constructor que crea un objeto PsychologistDTO con información detallada.
     * @param name                Nombre del psicólogo.
     * @param identificationNumber Número de identificación del psicólogo.
     * @param birthday            Fecha de nacimiento del psicólogo.
     * @param cityOfBorn          Ciudad de nacimiento del psicólogo.
     * @param graduationYear      Año de graduación del psicólogo.
     * @param daysSinceGraduation Días desde la graduación del psicólogo.
     * @param supportedSessions   Número de sesiones apoyadas por el psicólogo.
     * @param salary              Salario del psicólogo.
     */

	public PsychologistDTO(String name, long identificationNumber, Date birthday, String cityOfBorn,
			Date graduationYear, int daysSinceGraduation, int supportedSessions, int salary) {
		super(name, identificationNumber, birthday, cityOfBorn);
		this.graduationYear = graduationYear;
		this.daysSinceGraduation = daysSinceGraduation;
		this.supportedSessions = supportedSessions;
		this.salary = salary;
	}
	 /**
     * Constructor que crea un objeto PsychologistDTO con información sobre la graduación y apoyo a sesiones.
     * @param graduationYear      Año de graduación del psicólogo.
     * @param daysSinceGraduation Días desde la graduación del psicólogo.
     * @param supportedSessions   Número de sesiones apoyadas por el psicólogo.
     * @param salary              Salario del psicólogo.
     */

	public PsychologistDTO(Date graduationYear, int daysSinceGraduation, int supportedSessions, int salary) {
		super();
		this.graduationYear = graduationYear;
		this.daysSinceGraduation = daysSinceGraduation;
		this.supportedSessions = supportedSessions;
		this.salary = salary;
	}
	
	/**
     * Obtiene la cantidad de días transcurridos desde la graduación.
     */

	public int getDaysSinceGraduation() {
		return daysSinceGraduation;
	}
	/**
     * Establece la cantidad de días transcurridos desde la graduación.
     * @param daysSinceGraduation Los días transcurridos desde la graduación a establecer.
     */

	public void setDaysSinceGraduation(int daysSinceGraduation) {
		this.daysSinceGraduation = daysSinceGraduation;
	}
	 /**
     * Obtiene el número de sesiones de apoyo proporcionadas por el graduado.
     */

	public int getSupportedSessions() {
		return supportedSessions;
	}
	/**
     * Establece el número de sesiones de apoyo proporcionadas por el graduado.
     * @param supportedSessions El número de sesiones de apoyo a establecer.
     */

	public void setSupportedSessions(int supportedSessions) {
		this.supportedSessions = supportedSessions;
	}
	 /**
     * Obtiene el salario del graduado.
     * @return El salario del graduado.
     */

	public int getSalary() {
		return salary;
	}
	/**
     * Establece el salario del graduado.
     * @param salary El salario a establecer.
     */

	public void setSalary(int salary) {
		this.salary = salary;
	}
	/**
     * Obtiene el año de graduación del graduado.
     */

	public Date getGraduationYear() {
		return graduationYear;
	}
	/**
     * Establece el año de graduación del graduado.
     * @param graduationYear El año de graduación a establecer.
     */

	public void setGraduationYear(Date graduationYear) {
		this.graduationYear = graduationYear;
	}
	  /**
     * Representación en cadena de caracteres del objeto PsychologistDTO.
     * @return Una cadena de caracteres que describe el objeto PsychologistDTO.
     */

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		sb.append("Graduation's year: " + getGraduationYear() + "\n");
		sb.append("Days since graduation: " + getDaysSinceGraduation() + "\n");
		sb.append("Number of sessions supported: " + getSupportedSessions() + "\n");
		sb.append("Salary is : " + getSalary() + "\n");
		return sb.toString();
	}
}
