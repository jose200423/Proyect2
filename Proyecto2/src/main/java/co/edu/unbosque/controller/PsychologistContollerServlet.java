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

public class PsychologistContollerServlet extends HttpServlet {

	private static final long serialVersionUID = 3926245036661512117L;
	private PsychologistDAO psy;

	public PsychologistContollerServlet() {
		psy = new PsychologistDAO();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
