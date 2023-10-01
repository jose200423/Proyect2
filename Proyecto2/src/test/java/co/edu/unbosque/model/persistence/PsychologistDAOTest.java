package co.edu.unbosque.model.persistence;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import co.edu.unbosque.model.PsychologistDTO;
import co.edu.unbosque.model.persistence.PsychologistDAO;

public class PsychologistDAOTest {

    private PsychologistDAO psychologistDAO;
    private PsychologistDTO marshallMathers;

   
    public void setUp() {
        psychologistDAO = new PsychologistDAO();

       
        marshallMathers = new PsychologistDTO();
        marshallMathers.setName("Marshall Mathers");
        marshallMathers.setIdentificationNumber(1234567890L);
        marshallMathers.setBirthday(new Date(72, 9, 17)); 
        marshallMathers.setCityOfBorn("Detroit");
        marshallMathers.setGraduationYear(new Date(100, 4, 1)); 
        marshallMathers.setSupportedSessions(500);
        marshallMathers.setSalary(100000);
    }

  
    public void testCreate() {
        assertTrue(psychologistDAO.create(marshallMathers));
    }

    
    public void testReadByCc() {
        String result = psychologistDAO.readByCc(marshallMathers.getIdentificationNumber());
        assertNotNull(result);
        assertTrue(result.contains("Marshall Mathers"));
    }

    
    public void testUpdateByCc() {
        String newCity = "New York";
        assertEquals(0, psychologistDAO.updateByCc(marshallMathers.getIdentificationNumber(), "Marshall Mathers", "1972/10/17", newCity,
                "2000/05/01", "2000", "600", "200000"));
        String result = psychologistDAO.readByCc(marshallMathers.getIdentificationNumber());
        assertNotNull(result);
        assertTrue(result.contains(newCity));
    }

   
    public void testDeleteByCc() {
        assertEquals(0, psychologistDAO.deleteByCc(marshallMathers.getIdentificationNumber()));
        String result = psychologistDAO.readByCc(marshallMathers.getIdentificationNumber());
        assertEquals("NO INFO", result);
    }
}


