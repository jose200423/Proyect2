package co.edu.unbosque.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import co.edu.unbosque.model.PersonDTO;
import co.edu.unbosque.model.persistence.ServicesDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/** 
 * Clase controladora para gestionar las solicitudes relacionadas con servicios.
 * @author Andres Meneses
 * @author Yishaq Riveros
 * @author Fabian Montano
 * @author Jose Munoz
 * @author Miguel Pineda
 */
public class ServicesContollerServlet extends HttpServlet {

	private static final long serialVersionUID = -2447718114707713401L;
	private ServicesDAO serv;
	/* 
	 * Constructor de la clase. Inicializa un objeto ServicesDAO.
     */

	public ServicesContollerServlet() {
		serv = new ServicesDAO();
	}
	/**
	 * Maneja las solicitudes HTTP GET. Puede implementarse para recuperar información de servicios.
     * @param req Objeto HttpServletRequest que contiene la solicitud HTTP entrante.
     * @param resp Objeto HttpServletResponse que contiene la respuesta HTTP que se enviará al cliente.

     */

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	 /**
	 * Maneja las solicitudes HTTP POST. Puede implementarse para realizar acciones específicas relacionadas con servicios.
     * @param req Objeto HttpServletRequest que contiene la solicitud HTTP entrante.
     * @param resp Objeto HttpServletResponse que contiene la respuesta HTTP que se enviará al cliente.
    
     */

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	 /**
	 * Maneja las solicitudes HTTP PUT. Puede implementarse para actualizar recursos relacionados con servicios.
     * @param req Objeto HttpServletRequest que contiene la solicitud HTTP entrante.
     * @param resp Objeto HttpServletResponse que contiene la respuesta HTTP que se enviará al cliente.
     * @throws ServletException Excepción de servlet.
     * @throws IOException Excepción de entrada/salida.
     */

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	 /**
	 * Maneja las solicitudes HTTP DELETE. Puede implementarse para eliminar recursos relacionados con servicios.
     * @param req Objeto HttpServletRequest que contiene la solicitud HTTP entrante.
     * @param resp Objeto HttpServletResponse que contiene la respuesta HTTP que se enviará al cliente.
  
     */

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	 /**
	 * Método de la superclase .
     * @param arg0 Objeto HttpServletRequest que contiene la solicitud HTTP entrante.
     * @param arg1 Objeto HttpServletResponse que contiene la respuesta HTTP que se enviará al cliente.

     */

	@Override
	protected void doHead(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doHead(arg0, arg1);
	}
	/**
	 *  Método de la superclase .
     * @param req Objeto HttpServletRequest que contiene la solicitud HTTP entrante.
     * @param resp Objeto HttpServletResponse que contiene la respuesta HTTP que se enviará al cliente.
   
     */

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doOptions(req, resp);
	}
	/**
	 * Método de la superclase.
     * @param arg0 Objeto HttpServletRequest que contiene la solicitud HTTP entrante.
     * @param arg1 Objeto HttpServletResponse que contiene la respuesta HTTP que se enviará al cliente.
   
     */

	@Override
	protected void doTrace(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doTrace(arg0, arg1);
	}

}
