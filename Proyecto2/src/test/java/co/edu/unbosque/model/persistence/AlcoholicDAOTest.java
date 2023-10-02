package co.edu.unbosque.model.persistence;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import co.edu.unbosque.model.AlcoholicDTO;
import co.edu.unbosque.model.persistence.AlcoholicDAO;

public class AlcoholicDAOTest {

    private AlcoholicDAO alcoholicDAO;
    private AlcoholicDTO marshallMathers;

   
    @Before
    public void setUp() {
        alcoholicDAO = new AlcoholicDAO();

        marshallMathers = new AlcoholicDTO();
        
        marshallMathers.setName("Marshall Mathers");
        marshallMathers.setIdentificationNumber(1234567890);
        marshallMathers.setBirthday(Date.valueOf("1972-10-17"));
        marshallMathers.setCityOfBorn("Detroit");
        marshallMathers.setSessionsPresent(50);
        marshallMathers.setNickname("Slim Shady");
    }

    @Test
    public void testCreate() {
        assertTrue(alcoholicDAO.create(marshallMathers));
    }

    @Test
    public void testReadAll() {
        String allAlcoholics = alcoholicDAO.readAll();
        assertNotNull(allAlcoholics);
        assertTrue(allAlcoholics.contains("Marshall Mathers"));
    }

    @Test
    public void testReadByCc() {
        String result = alcoholicDAO.readByCc(marshallMathers.getIdentificationNumber());
        assertNotNull(result);
        assertTrue(result.contains("Marshall Mathers"));
    }

    @Test
    public void testUpdateByCc() {
       
        String newCity = "New York";
        assertEquals(0, alcoholicDAO.updateByCc(marshallMathers.getIdentificationNumber(), "Marshall Mathers", "1234567890", "1972-10-17", newCity, "50", "Slim Shady"));
        String result = alcoholicDAO.readByCc(marshallMathers.getIdentificationNumber());
        assertNotNull(result);
        assertTrue(result.contains(newCity));
    }

    @Test
    public void testDeleteByCc() {
        assertEquals(0, alcoholicDAO.deleteByCc(marshallMathers.getIdentificationNumber()));
        String result = alcoholicDAO.readByCc(marshallMathers.getIdentificationNumber());
        assertEquals("NO INFO", result);
    }
}
