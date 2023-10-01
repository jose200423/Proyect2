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

  
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        servlet = new AdminContollerServlet();
    }

    
    public void testDoPost() throws jakarta.servlet.ServletException, IOException {
        // Se simula una llamada al servlet con un método POST
        when(request.getRequestDispatcher("/result.jsp")).thenReturn(requestDispatcher);
        servlet.doPost(request, response);

        // Se verifica que se haya hecho un forward a "result.jsp"
        verify(requestDispatcher).forward(request, response);

       
    }
}

