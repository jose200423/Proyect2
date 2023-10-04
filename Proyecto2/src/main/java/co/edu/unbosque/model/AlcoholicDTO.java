package co.edu.unbosque.model;

import java.util.Date;

/**
 * Clase que representa a un administrador, que es un tipo de persona.
 * @author Andres Meneses
 * @author Jose Munoz
 * @author Fabian Montano
 * @author Miguel pineda 
 * @author Yishaq Riveros
 */
public class AlcoholicDTO extends PersonDTO {
	private int sessionsPresent;
	private String nickname;
	/**
	 * Metodo constructor
	 */

	public AlcoholicDTO() {
	}
	/**
     * Constructor que crea un objeto AlcoholicDTO con información específica.
     * @param sessionsPresent Número de sesiones a las que ha asistido el alcohólico.
     * @param nickname        Apodo o alias del alcohólico.
     */

	public AlcoholicDTO(int sessionsPresent, String nickname) {
		super();
		this.sessionsPresent = sessionsPresent;
		this.nickname = nickname;
	}
	
	/**
     * Constructor que crea un objeto AlcoholicDTO con información específica.
     * @param name                Nombre del alcohólico.
     * @param identificationNumber Número de identificación del alcohólico.
     * @param birthday            Fecha de nacimiento del alcohólico.
     * @param cityOfBorn          Ciudad de nacimiento del alcohólico.
     * @param sessionsPresent     Número de sesiones a las que ha asistido el alcohólico.
     * @param nickname            Apodo o alias del alcohólico.
     */

	public AlcoholicDTO(String name, long identificationNumber, Date birthday, String cityOfBorn, int sessionsPresent,
			String nickname) {
		super(name, identificationNumber, birthday, cityOfBorn);
		this.sessionsPresent = sessionsPresent;
		this.nickname = nickname;
	}
	/**
     * Constructor que crea un objeto AlcoholicDTO con información específica.
     * @param name                Nombre del alcohólico.
     * @param identificationNumber Número de identificación del alcohólico.
     * @param birthday            Fecha de nacimiento del alcohólico.
     * @param cityOfBorn          Ciudad de nacimiento del alcohólico.
     */

	public AlcoholicDTO(String name, long identificationNumber, Date birthday, String cityOfBorn) {
		super(name, identificationNumber, birthday, cityOfBorn);
	}
	 /**
     * Obtiene el número de sesiones a las que ha asistido el alcohólico.
     * @return El número de sesiones a las que ha asistido el alcohólico.
     */

	public int getSessionsPresent() {
		return sessionsPresent;
	}
	/**
     * Establece el número de sesiones a las que ha asistido el alcohólico.
     * @param sessionsPresent El número de sesiones a las que ha asistido el alcohólico.
     */

	public void setSessionsPresent(int sessionsPresent) {
		this.sessionsPresent = sessionsPresent;
	}
	/**
     * Obtiene el apodo o alias del alcohólico.
     * @return El apodo o alias del alcohólico.
     */

	public String getNickname() {
		return nickname;
	}
	
	/**
     * Establece el apodo o alias del alcohólico.
     * @param nickname El apodo o alias del alcohólico.
     */

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	 /**
     * Representación en cadena de caracteres del objeto AlcoholicDTO.
     * @return Una cadena de caracteres que describe el objeto AlcoholicDTO.
     */

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		sb.append("Person has been in: " + getSessionsPresent() + " sessions \n");
		sb.append("Nickname of this person is: " + getNickname() + "\n");
		return sb.toString();
	}

}
