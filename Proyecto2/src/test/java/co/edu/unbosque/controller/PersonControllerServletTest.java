package co.edu.unbosque.controller;

import co.edu.unbosque.model.PersonDTO;
import co.edu.unbosque.model.persistence.PersonDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Date;

import static org.mockito.Mockito.*;

public class PersonControllerServletTest {

    
    private HttpServletRequest request;

 
    private HttpServletResponse response;

  
    private RequestDispatcher requestDispatcher;

    private PersonControllerServlet servlet;
    private PersonDAO personDAO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        PersonDAO personDAO = mock(PersonDAO.class);
        
    }

    @Test
    public void testDoGet() throws Exception {
      
    }
    @Test
    public void testDoPostCreate() throws Exception {
       
        when(request.getParameter("action")).thenReturn("create");
        when(request.getParameter("name")).thenReturn("John Doe");
        when(request.getParameter("cc")).thenReturn("123456789");
        when(request.getParameter("date")).thenReturn("2023-09-30");
        when(request.getParameter("city")).thenReturn("New York");

        
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        
        servlet.doPost(request, response);
        
        verify(personDAO).create(any(PersonDTO.class));
    }

    @Test
    public void testDoPostUpdate() throws Exception {
      
        when(request.getParameter("action")).thenReturn("update");
        when(request.getParameter("name")).thenReturn("Updated Name");
        when(request.getParameter("cc")).thenReturn("123456789");
        when(request.getParameter("date")).thenReturn("2023-09-30");
        when(request.getParameter("city")).thenReturn("Updated City");

        
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

      
        servlet.doPost(request, response);

        
        verify(personDAO).updateByCc(eq(123456789L), any(String[].class));
    }

    @Test
    public void testDoPostDelete() throws Exception {
    
        when(request.getParameter("action")).thenReturn("delete");
        when(request.getParameter("cc")).thenReturn("123456789");

    
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

  
        servlet.doPost(request, response);

     
        verify(personDAO).deleteByCc(eq(123456789L));
    }
}
