package co.edu.unbosque.controller;

import java.io.IOException;
import java.io.PrintWriter;

import co.edu.unbosque.model.AdminDTO;
import co.edu.unbosque.model.PersonDTO;
import co.edu.unbosque.model.persistence.AdminDAO;
import co.edu.unbosque.model.persistence.AlcoholicDAO;
import co.edu.unbosque.model.persistence.PersonDAO;
import co.edu.unbosque.model.persistence.PsychologistDAO;
import co.edu.unbosque.model.persistence.ServicesDAO;
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
	private PsychologistDAO psy;
	private AlcoholicDAO alh;
	private ServicesDAO ser;
	private View v;
	/**
	 * Constructor de la clase UserControllerServlet
	 * Inicializa las instancias de AdminDAO, PersonDAO, PsychologistDAO.AlcoholicDAO,ServicesDAO .
  */

	public UserControllerServlet() {
		aDAO = new AdminDAO();
		pDao = new PersonDAO();
		psy = new PsychologistDAO();
		alh = new AlcoholicDAO();
		ser = new ServicesDAO();

		v = new View();
	}
	/**
	 * Maneja las solicitudes HTTP GET. Recupera información de usuarios y la imprime en la respuesta.
	 * @param req Objeto HttpServletRequest que contiene la solicitud HTTP entrante.
	 * @param resp Objeto HttpServletResponse que contiene la respuesta HTTP que se enviará al cliente.
	
	 */

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		for (PersonDTO u : pDao.getUsers()) {
			out.write(u.toString());
		}
		out.close();

	}
	/**
	 * Maneja las solicitudes HTTP POST. Realiza la autenticación de usuarios y redirige a páginas correspondientes según el tipo de usuario.
	 * @param req Objeto HttpServletRequest que contiene la solicitud HTTP entrante.
	 * @param resp Objeto HttpServletResponse que contiene la respuesta HTTP que se enviará al cliente.
	 */

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		String name = req.getParameter("username");
		long password = Long.parseLong(req.getParameter("password"));
		v.printJump(aDAO.readAll());
		boolean status = aDAO.validate(name, password);
		boolean statusp = psy.validate(name, password);
		boolean statusa = alh.validate(name, password);
		boolean statusser = ser.validate(name, password);
		log(name);
		log(String.valueOf(password));
		PrintWriter out = resp.getWriter();
		if (status) {
			RequestDispatcher rd = req.getRequestDispatcher("login-success.jsp");
			rd.forward(req, resp);
		} else if (statusp) {
			RequestDispatcher rd = req.getRequestDispatcher("psy.jsp");
			rd.forward(req, resp);
		} else if (statusa) {
			RequestDispatcher rd = req.getRequestDispatcher("alh.jsp");
			rd.forward(req, resp);
		} else if (statusser) {
			RequestDispatcher rd = req.getRequestDispatcher("ser.jsp");
			rd.forward(req, resp);
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("login-error.jsp");
			rd.forward(req, resp);
		}
		out.close();
	}
	/**
	 * Este método se utiliza para actualizar recursos en el servidor .
	 * @param req Objeto HttpServletRequest que contiene la solicitud HTTP entrante.
	 * @param resp Objeto HttpServletResponse que contiene la respuesta HTTP que se enviará al cliente.
	 */

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	/**
	 * Maneja las peticiones DELETE para eliminar recursos en el servidor.
	 * @param req Objeto HttpServletRequest que contiene la solicitud HTTP entrante.
	 * @param resp Objeto HttpServletResponse que contiene la respuesta HTTP que se enviará al cliente.
	
	 */

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	/**
	 * Maneja las peticiones HEAD para obtener información sobre un recurso sin recuperar su contenido.
	 * @param arg0 Objeto HttpServletRequest que contiene la solicitud HTTP entrante.
	 * @param arg1 Objeto HttpServletResponse que contiene la respuesta HTTP que se enviará al cliente.
	
	 */

	@Override
	protected void doHead(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doHead(arg0, arg1);
	}
	/**
	 * Maneja las peticiones OPTIONS para obtener información sobre las opciones de comunicación
	 * disponibles para un recurso en el servidor.
	 * @param req Objeto HttpServletRequest que contiene la solicitud HTTP entrante.
	 * @param resp Objeto HttpServletResponse que contiene la respuesta HTTP que se enviará al cliente.
	
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
	 * @param arg0 Objeto HttpServletRequest que contiene la solicitud HTTP entrante.
	 * @param arg1 Objeto HttpServletResponse que contiene la respuesta HTTP que se enviará al cliente.
	
	 */

	@Override
	protected void doTrace(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doTrace(arg0, arg1);
	}
}
