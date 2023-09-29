package co.edu.unbosque.controller;

import java.io.IOException;
import java.io.PrintWriter;
import co.edu.unbosque.model.AdminDTO;
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
 * Esta clase representa un controlador servlet para gestionar las peticiones HTTP
 * relacionadas con usuarios y administradores.
 * @author Andres Meneses
 * @author Jose Munoz
 * @author Fabian Montano
 * @author Miguel pineda 
 * @author Yishaq Riveros
 */
public class UserControllerServlet extends HttpServlet {

	private static final long serialVersionUID = -4331680861219300600L;
	private AdminDAO aDAO;
	private PersonDAO pDao;
	private View v;
	
	/**
	 * Constructor de la clase UserControllerServlet
	 * Inicializa las instancias de AdminDAO, PersonDAO y View.
  */

	public UserControllerServlet() {
		aDAO = new AdminDAO();
		pDao = new PersonDAO();
		v = new View();
	}
	
	 /**
     * Maneja las peticiones GET para recuperar y mostrar información de usuarios.
     *
     * @param req  HttpServletRequest que representa la solicitud.
     * @param resp HttpServletResponse que representa la respuesta.
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
     * Maneja las peticiones POST para validar el inicio de sesión de administradores.
     *
     * @param req  HttpServletRequest que representa la solicitud.
     * @param resp HttpServletResponse que representa la respuesta.
     */

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		String name = req.getParameter("username");
		String password = req.getParameter("password");
		aDAO.read();
		v.printJump(aDAO.readAll());
		boolean status = aDAO.validate(name,password);
		log(name);
		log(password);
		PrintWriter out = resp.getWriter();
		if (status) {
			//out.write("jajaj lo encontre :V");
			RequestDispatcher rd = req.getRequestDispatcher("login-success.jsp");
			
			rd.forward(req, resp);
		} else {
			// out.write("Usuario o contraseña incorrecta");
			RequestDispatcher rd = req.getRequestDispatcher("login-error.jsp");
			rd.forward(req, resp);
		}
		out.close();
	}
	
	/**
	 * Este método se utiliza para actualizar recursos en el servidor
	 * @param req  HttpServletRequest que representa la solicitud.
     * @param resp HttpServletResponse que representa la respuesta. 
	 */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	
	/**
	 * Maneja las peticiones DELETE para eliminar recursos en el servidor.
	 *
	 * @param req  HttpServletRequest que representa la solicitud.
	 * @param resp HttpServletResponse que representa la respuesta.
	 */
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	
	/**
	 * Maneja las peticiones HEAD para obtener información sobre un recurso sin recuperar su contenido.
	 *
	 * @param req  HttpServletRequest que representa la solicitud.
	 * @param resp HttpServletResponse que representa la respuesta.
	 */

	@Override
	protected void doHead(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doHead(arg0, arg1);
	}
	
	/**
	 * Maneja las peticiones OPTIONS para obtener información sobre las opciones de comunicación
	 * disponibles para un recurso en el servidor.
	 *
	 * @param req  HttpServletRequest que representa la solicitud.
	 * @param resp HttpServletResponse que representa la respuesta.
	 */

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doOptions(req, resp);
	}
	
	/**
	 * Maneja las peticiones TRACE, que se utilizan para depurar o diagnosticar problemas
	 * de comunicación entre el cliente y el servidor. Este método simplemente llama a la
	 * implementación predeterminada de HttpServlet y no realiza ninguna acción adicional.
	 *
	 * @param req  HttpServletRequest que representa la solicitud TRACE.
	 * @param resp HttpServletResponse que representa la respuesta a la solicitud TRACE.
	 * @throws ServletException Si ocurre una excepción de tipo ServletException durante el procesamiento.
	 * @throws IOException      Si ocurre una excepción de tipo IOException durante el procesamiento.
	 */

	@Override
	protected void doTrace(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doTrace(arg0, arg1);
	}
}
