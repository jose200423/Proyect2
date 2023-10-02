package co.edu.unbosque.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.edu.unbosque.controller.AdminContollerServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminControllerServletTest {

	HttpServletRequest request;

	HttpServletResponse response;

	jakarta.servlet.RequestDispatcher requestDispatcher;

	private AdminContollerServlet servlet;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		servlet = new AdminContollerServlet();
	}

	@Test
	public void testDoPost() throws jakarta.servlet.ServletException, IOException {
		// Se simula una llamada al servlet con un método POST
		if (request != null) {
			when(request.getRequestDispatcher("/result.jsp")).thenReturn(requestDispatcher);
			servlet.doPost(request, response);

			verify(requestDispatcher).forward(request, response);
		} else {
			// La solicitud HTTP es nula
			System.out.println("La solicitud HTTP es nula");
		}

	}
}
