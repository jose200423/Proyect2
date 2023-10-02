package co.edu.unbosque.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.google.protobuf.TextFormat.ParseException;

import co.edu.unbosque.model.PersonDTO;
import co.edu.unbosque.model.persistence.AdminDAO;
import co.edu.unbosque.model.persistence.PersonDAO;
import co.edu.unbosque.view.View;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Clase controladora para gestionar las solicitudes relacionadas con las personas.
 * @author Andres Meneses
 * @author Yishaq Riveros
 * @author Fabian Montano
 * @author Jose Munoz
 * @author Miguel Pineda
 */

public class PersonControllerServlet extends HttpServlet{

	
	private static final long serialVersionUID = -1551947553685760812L;
	private AdminDAO aDAO;
	private PersonDAO pDao;
	private View v;
	/**
     * Constructor de la clase PersonControllerServlet. Inicializa objetos AdminDAO, PersonDAO y View.
     */
	public PersonControllerServlet() {
		aDAO = new AdminDAO();
		pDao = new PersonDAO();
		v = new View();
	}
	 /**
     * Maneja las solicitudes HTTP GET. Recupera datos de personas y los muestra en la respuesta HTTP.
     * @param req Objeto HttpServletRequest que contiene la solicitud HTTP entrante.
     * @param resp Objeto HttpServletResponse que contiene la respuesta HTTP que se enviará al cliente.
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		pDao.read();
		for (PersonDTO u : pDao.getUsers()) {
			out.write(u.toString());
		}
		out.close();

	}
	/**
     * Maneja las solicitudes HTTP POST. Realiza diversas acciones según la acción especificada en la solicitud.
     * @param req Objeto HttpServletRequest que contiene la solicitud HTTP entrante.
     * @param resp Objeto HttpServletResponse que contiene la respuesta HTTP que se enviará al cliente.
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 resp.setContentType("text/html");
		 String action = req.getParameter("action");
		 if ("create".equals(action)) {
		 String name = req.getParameter("name");
		    long cc = Long.parseLong(req.getParameter("cc"));
		    java.util.Date utilDate = null;
			try {
				utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("date"));
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
		    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		    String cityOfBorn = req.getParameter("city");
			log(name);
			log(String.valueOf(cc));
			log(String.valueOf(sqlDate));
			log(cityOfBorn);
		    PrintWriter out = resp.getWriter();
		    boolean is =  pDao.create(new PersonDTO(name, cc, sqlDate, cityOfBorn));
		    if(is) {
		    	RequestDispatcher rd = req.getRequestDispatcher("create.jsp");
				rd.forward(req, resp);
		    }else {
		    	RequestDispatcher rd = req.getRequestDispatcher("login-error.jsp");
				rd.forward(req, resp);
		    }
		    out.close();
		 } else if ("update".equals(action)) {
			 String name = req.getParameter("name");
			    long cc = Long.parseLong(req.getParameter("cc"));
			    java.util.Date utilDate = null;
				try {
					utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("date"));
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}
			    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			    String cityOfBorn = req.getParameter("city");
				log(name);
				log(String.valueOf(cc));
				log(String.valueOf(sqlDate));
				log(cityOfBorn);
			    PrintWriter out = resp.getWriter();
			    String[] args = new String[]{ name ,String.valueOf(sqlDate), cityOfBorn};
			    
			    int i = pDao.updateByCc(cc, args);
			    if(i == 0) {
			    	RequestDispatcher rd = req.getRequestDispatcher("create.jsp");
					rd.forward(req, resp);
			    }else {
			    	RequestDispatcher rd = req.getRequestDispatcher("login-error.jsp");
					rd.forward(req, resp);
			    }
			    out.close();
		    }else if ("delete".equals(action)) {
		
			    long cc = Long.parseLong(req.getParameter("cc"));
				log(String.valueOf(cc));
			    PrintWriter out = resp.getWriter();
			    int i = pDao.deleteByCc(cc);
			    if(i == 0) {
			    	RequestDispatcher rd = req.getRequestDispatcher("create.jsp");
					rd.forward(req, resp);
			    }else {
			    	RequestDispatcher rd = req.getRequestDispatcher("login-error.jsp");
					rd.forward(req, resp);
			    }
			    out.close();
		    }
	}
	/**
     * Maneja las solicitudes HTTP PUT. Puede ser implementado para actualizar recursos en el servidor.
     * @param req Objeto HttpServletRequest que contiene la solicitud HTTP entrante.
     * @param resp Objeto HttpServletResponse que contiene la respuesta HTTP que se enviará al cliente.
     */

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
	    
	}
	
	/**
     * Maneja las solicitudes HTTP DELETE. Puede ser implementado para eliminar recursos en el servidor.
     * @param req Objeto HttpServletRequest que contiene la solicitud HTTP entrante.
     * @param resp Objeto HttpServletResponse que contiene la respuesta HTTP que se enviará al cliente.
     */

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	
	/**
     * Método Head
     * @param arg0 Objeto HttpServletRequest que contiene la solicitud HTTP entrante.
     * @param arg1 Objeto HttpServletResponse que contiene la respuesta HTTP que se enviará al cliente.
     */

	@Override
	protected void doHead(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doHead(arg0, arg1);
	}
	
	  /**
     * Método Option
     * @param req Objeto HttpServletRequest que contiene la solicitud HTTP entrante.
     * @param resp Objeto HttpServletResponse que contiene la respuesta HTTP que se enviará al cliente.
     */

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doOptions(req, resp);
	}
	/**
     * Método doTrace.
     * @param arg0 Objeto HttpServletRequest que contiene la solicitud HTTP entrante.
     * @param arg1 Objeto HttpServletResponse que contiene la respuesta HTTP que se enviará al cliente.
     */

	@Override
	protected void doTrace(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doTrace(arg0, arg1);
	}

}
