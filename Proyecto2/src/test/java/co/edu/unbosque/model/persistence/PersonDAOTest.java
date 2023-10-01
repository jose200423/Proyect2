package co.edu.unbosque.model.persistence;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import co.edu.unbosque.model.PersonDTO;
import co.edu.unbosque.model.persistence.PersonDAO;

public class PersonDAOTest {

    private PersonDAO personDAO;
    private PersonDTO marshallMathers;

 
    public void setUp() {
        personDAO = new PersonDAO();

        
        marshallMathers = new PersonDTO();
        marshallMathers.setName("Marshall Mathers");
        marshallMathers.setIdentificationNumber(1234567890L);
        marshallMathers.setBirthday(Date.valueOf("1972-10-17"));
        marshallMathers.setCityOfBorn("Detroit");
    }

   
    public void testCreate() {
        assertTrue(personDAO.create(marshallMathers));
    }

   
    public void testReadAll() {
        String allPersons = personDAO.readAll();
        assertNotNull(allPersons);
        assertTrue(allPersons.contains("Marshall Mathers"));
    }

   
    public void testReadByCc() {
        String result = personDAO.readByCc(marshallMathers.getIdentificationNumber());
        assertNotNull(result);
        assertTrue(result.contains("Marshall Mathers"));
    }

   
    public void testUpdateByCc() {
       
        String newCity = "New York";
        assertEquals(0, personDAO.updateByCc(marshallMathers.getIdentificationNumber(), "Marshall Mathers", "1972-10-17", newCity));
        String result = personDAO.readByCc(marshallMathers.getIdentificationNumber());
        assertNotNull(result);
        assertTrue(result.contains(newCity));
    }

  
    public void testDeleteByCc() {
        assertEquals(0, personDAO.deleteByCc(marshallMathers.getIdentificationNumber()));
        String result = personDAO.readByCc(marshallMathers.getIdentificationNumber());
        assertEquals("NO INFO", result);
    }
}


