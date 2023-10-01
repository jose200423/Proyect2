package co.edu.unbosque.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import co.edu.unbosque.model.PersonDTO;
import co.edu.unbosque.model.persistence.PersonDAO;
import co.edu.unbosque.view.View;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ControllerTest {

    private Controller controller;
    private View view;
    private PersonDAO personDAO;

    
    public void setUp() {
        view = mock(View.class);
        personDAO = mock(PersonDAO.class);
        controller = new Controller();
    }

    @Test
    public void testIniciar() {
       
        when(view.readInt())
                .thenReturn(1)  
                .thenReturn(2)  
                .thenReturn(5)  
                .thenReturn(6); 

        
        when(view.readAllLine()).thenReturn("John Doe", "1234567890", "2000/01/01");

       
        when(personDAO.create(any(PersonDTO.class))).thenReturn(true);
        when(personDAO.readAll()).thenReturn("Person 1\nPerson 2\n");

       
        controller.iniciar();

     
        verify(personDAO, times(1)).create(any(PersonDTO.class));
        verify(personDAO, times(1)).readAll();
        verify(view, times(1)).printJump("was added successfully");
        verify(view, times(1)).printJump("Person 1\nPerson 2\n");
    }

  
}
