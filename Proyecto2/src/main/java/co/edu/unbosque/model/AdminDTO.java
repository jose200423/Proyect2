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
public class AdminDTO extends PersonDTO {
	/**
     * Metodo Constructor.
     */

	public AdminDTO() {

	}
	/**
     * Constructor que crea un objeto AdminDTO con información específica.
     * @param name                Nombre del administrador.
     * @param identificationNumber Número de identificación del administrador.
     * @param birthday            Fecha de nacimiento del administrador.
     * @param cityOfBorn          Ciudad de nacimiento del administrador.
     */

	public AdminDTO(String name, long identificationNumber, Date birthday, String cityOfBorn) {
		super(name, identificationNumber, birthday, cityOfBorn);

	}
	 /**
     * Representación en cadena de caracteres del objeto AdminDTO.
     * @return Una cadena de caracteres que describe el objeto AdminDTO.
     */

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		return sb.toString();
	}

}
