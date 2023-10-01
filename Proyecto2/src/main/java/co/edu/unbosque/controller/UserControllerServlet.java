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

public class UserControllerServlet extends HttpServlet {

	private static final long serialVersionUID = -4331680861219300600L;
	private AdminDAO aDAO;
	private PersonDAO pDao;
	private PsychologistDAO psy;
	private AlcoholicDAO alh;
	private ServicesDAO ser;
	private View v;

	public UserControllerServlet() {
		aDAO = new AdminDAO();
		pDao = new PersonDAO();
		psy = new PsychologistDAO();
		alh = new AlcoholicDAO();
		ser = new ServicesDAO();

		v = new View();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		for (PersonDTO u : pDao.getUsers()) {
			out.write(u.toString());
		}
		out.close();

	}

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

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doHead(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doHead(arg0, arg1);
	}

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doOptions(req, resp);
	}

	@Override
	protected void doTrace(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doTrace(arg0, arg1);
	}
}
