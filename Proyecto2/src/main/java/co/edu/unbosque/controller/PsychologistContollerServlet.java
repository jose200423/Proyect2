package co.edu.unbosque.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import co.edu.unbosque.model.PersonDTO;
import co.edu.unbosque.model.persistence.PsychologistDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Clase controladora para gestionar las solicitudes relacionadas con psicólogos.
 * @author Andres Meneses
 * @author Yishaq Riveros
 * @author Fabian Montano
 * @author Jose Munoz
 * @author Miguel Pineda
 */

public class PsychologistContollerServlet extends HttpServlet {

	private static final long serialVersionUID = 3926245036661512117L;
	private PsychologistDAO psy;
	
	/**
     * Constructor de la clase. Inicializa un objeto PsychologistDAO.
     */
	public PsychologistContollerServlet() {
		psy = new PsychologistDAO();
	}
	
	 /**
     * Maneja las solicitudes HTTP GET. Puede implementarse para recuperar información de psicólogos.
     * @param req Objeto HttpServletRequest que contiene la solicitud HTTP entrante.
     * @param resp Objeto HttpServletResponse que contiene la respuesta HTTP que se enviará al cliente.
  
     */

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	/**
    * Maneja las solicitudes HTTP POST. Puede implementarse para realizar acciones específicas relacionadas con psicólogos.
    * @param req Objeto HttpServletRequest que contiene la solicitud HTTP entrante.
    * @param resp Objeto HttpServletResponse que contiene la respuesta HTTP que se enviará al cliente.
  
    */

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	/**
     * Maneja las solicitudes HTTP PUT. Puede implementarse para actualizar recursos relacionados con psicólogos.
     * @param req Objeto HttpServletRequest que contiene la solicitud HTTP entrante.
     * @param resp Objeto HttpServletResponse que contiene la respuesta HTTP que se enviará al cliente.
    
     */

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	/**
     * Maneja las solicitudes HTTP DELETE. Puede implementarse para eliminar recursos relacionados con psicólogos.
     * @param req Objeto HttpServletRequest que contiene la solicitud HTTP entrante.
     * @param resp Objeto HttpServletResponse que contiene la respuesta HTTP que se enviará al cliente.
  
     */

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	
	/**
     * Método de la superclase.
     * @param arg0 Objeto HttpServletRequest que contiene la solicitud HTTP entrante.
     * @param arg1 Objeto HttpServletResponse que contiene la respuesta HTTP que se enviará al cliente.
  
     */

	@Override
	protected void doHead(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doHead(arg0, arg1);
	}
	
	/**
     * Método de la superclase .
     * @param req Objeto HttpServletRequest que contiene la solicitud HTTP entrante.
     * @param resp Objeto HttpServletResponse que contiene la respuesta HTTP que se enviará al cliente.

     */

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doOptions(req, resp);
	}
	
	/**
     * Método de la superclase .
     * @param arg0 Objeto HttpServletRequest que contiene la solicitud HTTP entrante.
     * @param arg1 Objeto HttpServletResponse que contiene la respuesta HTTP que se enviará al cliente.
 
     */

	@Override
	protected void doTrace(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doTrace(arg0, arg1);
	}

}
