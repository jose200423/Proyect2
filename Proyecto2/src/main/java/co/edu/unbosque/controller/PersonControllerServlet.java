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

public class PersonControllerServlet extends HttpServlet{

	
	private static final long serialVersionUID = -1551947553685760812L;
	private AdminDAO aDAO;
	private PersonDAO pDao;
	private View v;

	public PersonControllerServlet() {
		aDAO = new AdminDAO();
		pDao = new PersonDAO();
		v = new View();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		pDao.read();
		for (PersonDTO u : pDao.getUsers()) {
			out.write(u.toString());
		}
		out.close();

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 resp.setContentType("text/html");
		    String name = req.getParameter("name");
		    long cc = Long.parseLong(req.getParameter("cc"));
		    java.util.Date utilDate = null;
			try {
				utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("date"));
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		    String cityOfBorn = req.getParameter("city");
		  
		    
		    
			log(name);
			log(String.valueOf(cc));
			log(String.valueOf(sqlDate));
			log(cityOfBorn);
		    PrintWriter out = resp.getWriter();
		   pDao.create(new PersonDTO(name, cc, sqlDate, cityOfBorn));
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