package co.edu.unbosque.model.persistence;

/**
 * Interfaz que define las operaciones básicas de creación, lectura, actualización y eliminación (CRUD) para una entidad.
 * @autor Andres Meneses
 * @author Jose Munoz
 * @author Fabian Montano
 * @author Miguel pineda 
 * @author Yishaq Riveros
 */
public interface CRUDoperation {
	/**
     * Crea un nuevo objeto en la fuente de datos.
     *
     * @param obj El objeto que se desea crear.
    
     */

	public boolean create(Object obj);
	/**
     * Lee todos los objetos de la fuente de datos y los devuelve como una cadena.
     *
     */

	public String readAll();
	/**
     * Lee un objeto de la fuente de datos por un identificador específico y lo devuelve como una cadena.
     *
     * @param cc El identificador del objeto que se desea leer.
   
     */

	public String readByCc(long cc);
	/**
     * Actualiza un objeto en la fuente de datos por un identificador específico.
     *
     * @param cc   El identificador del objeto que se desea actualizar.
     * @param args Arreglo de argumentos con los nuevos valores.
     */

	public int updateByCc(long cc, String... args);
	/**
     * Elimina un objeto de la fuente de datos por un identificador específico.
     *
     * @param cc El identificador del objeto que se desea eliminar.
     */

	public int deleteByCc(long cc);

}
