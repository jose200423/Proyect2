package co.edu.unbosque.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import co.edu.unbosque.model.PersonDTO;
import co.edu.unbosque.model.persistence.AlcoholicDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Esta clase representa un servlet de controlador para la gestión de alcohólicos.
 * Proporciona métodos para manejar las solicitudes HTTP como GET, POST, PUT y DELETE.
 * @author Andres Meneses
 * @author Yishaq Riveros
 * @author Fabian Montano
 * @author Jose Munoz
 * @author Miguel Pineda
 */

public class AlcoholicContollerServlet extends HttpServlet {

	private static final long serialVersionUID = -6485018410802914785L;
	private AlcoholicDAO alco;
	
	/**
     * Constructor por defecto que inicializa la instancia de AlcoholicDAO.
     */

	public AlcoholicContollerServlet() {
		alco = new AlcoholicDAO();
	}
	
	/**
     * Maneja las solicitudes GET.
     * 
     * @param req  El objeto HttpServletRequest que representa la solicitud.
     * @param resp El objeto HttpServletResponse que representa la respuesta.
     */
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	/**
     * Maneja las solicitudes POST.
     * 
     * @param req  El objeto HttpServletRequest que representa la solicitud.
     * @param resp El objeto HttpServletResponse que representa la respuesta.
     */

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	/**
     * Maneja las solicitudes PUT.
     * 
     * @param req  El objeto HttpServletRequest que representa la solicitud.
     * @param resp El objeto HttpServletResponse que representa la respuesta.
     */

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	/**
     * Maneja las solicitudes DELETE.
     * 
     * @param req  El objeto HttpServletRequest que representa la solicitud.
     * @param resp El objeto HttpServletResponse que representa la respuesta.
     */

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	
	/**
     * Maneja las solicitudes HEAD.
     * 
     * @param arg0 El objeto HttpServletRequest que representa la solicitud.
     * @param arg1 El objeto HttpServletResponse que representa la respuesta.
     */

	@Override
	protected void doHead(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doHead(arg0, arg1);
	}
	
	  /**
     * Maneja las solicitudes OPTIONS.
     * 
     * @param req  El objeto HttpServletRequest que representa la solicitud.
     * @param resp El objeto HttpServletResponse que representa la respuesta.
     */

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doOptions(req, resp);
	}
	
	/**
     * Maneja las solicitudes TRACE.
     * 
     * @param arg0 El objeto HttpServletRequest que representa la solicitud.
     * @param arg1 El objeto HttpServletResponse que representa la respuesta.
     */

	@Override
	protected void doTrace(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doTrace(arg0, arg1);
	}


}
