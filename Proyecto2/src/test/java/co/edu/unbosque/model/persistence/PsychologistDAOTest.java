package co.edu.unbosque.model.persistence;
import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import co.edu.unbosque.model.PsychologistDTO;
import co.edu.unbosque.model.persistence.PsychologistDAO;

public class PsychologistDAOTest {

    private PsychologistDAO psychologistDAO;
    private PsychologistDTO marshallMathers;

    @Before
    public void setUp() {
        psychologistDAO = new PsychologistDAO();

       
        marshallMathers = new PsychologistDTO();
        marshallMathers.setName("Marshall Mathers");
        marshallMathers.setIdentificationNumber(1234567890L);
        marshallMathers.setBirthday(Date.valueOf("1972-10-17")); 
        marshallMathers.setCityOfBorn("Detroit");
        marshallMathers.setGraduationYear(Date.valueOf("2000-05-01")); 
        marshallMathers.setSupportedSessions(500);
        marshallMathers.setSalary(100000);
    }

    @Test
    public void testCreate() {
        assertTrue(psychologistDAO.create(marshallMathers));
    }

    @Test
    public void testReadByCc() {
        String result = psychologistDAO.readByCc(marshallMathers.getIdentificationNumber());
        assertNotNull(result);
        assertTrue(result.contains("Marshall Mathers"));
    }

    @Test
    public void testUpdateByCc() {
        String newCity = "New York";
        assertEquals(0, psychologistDAO.updateByCc(marshallMathers.getIdentificationNumber(), "Marshall Mathers","1234567890" ,"1972-10-17", newCity,
                "2000-05-01", "2000", "600", "200000"));
        String result = psychologistDAO.readByCc(marshallMathers.getIdentificationNumber());
        assertNotNull(result);
        assertTrue(result.contains(newCity));
    }

    @Test
    public void testDeleteByCc() {
        assertEquals(0, psychologistDAO.deleteByCc(marshallMathers.getIdentificationNumber()));
        String result = psychologistDAO.readByCc(marshallMathers.getIdentificationNumber());
        assertEquals("NO INFO", result);
    }
    
    @Test
    public void testReadAll() {
        String allPersons = psychologistDAO.readAll();
        assertNotNull(allPersons);
        assertTrue(allPersons.contains("Marshall Mathers"));
    }
}


