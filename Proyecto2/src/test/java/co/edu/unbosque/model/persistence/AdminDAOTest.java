package co.edu.unbosque.model.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import co.edu.unbosque.model.AdminDTO;
import co.edu.unbosque.model.PersonDTO;

public class AdminDAOTest {
	
	private AdminDAO adminDAO;
    private AdminDTO marshallMathers;

    @Before
	public void setUp() {
        adminDAO = new AdminDAO();

        
        marshallMathers = new AdminDTO();
        marshallMathers.setName("Marshall Mathers");
        marshallMathers.setIdentificationNumber(1234567890L);
        marshallMathers.setBirthday(Date.valueOf("1972-10-17"));
        marshallMathers.setCityOfBorn("Detroit");
    }

    @Test
    public void testCreate() {
        assertTrue(adminDAO.create(marshallMathers));
    }

    @Test
    public void testReadAll() {
        String allPersons = adminDAO.readAll();
        assertNotNull(allPersons);
        assertTrue(allPersons.contains("Marshall Mathers"));
    }

    @Test
    public void testReadByCc() {
        String result = adminDAO.readByCc(marshallMathers.getIdentificationNumber());
        assertNotNull(result);
        assertTrue(result.contains("Marshall Mathers"));
    }

    @Test
    public void testUpdateByCc() {
       
        String newCity = "New York";
        assertEquals(0, adminDAO.updateByCc(marshallMathers.getIdentificationNumber(), "Marshall Mathers", "1234567890","1972-10-17", newCity));
        String result = adminDAO.readByCc(marshallMathers.getIdentificationNumber());
        assertNotNull(result);
        assertTrue(result.contains(newCity));
    }

    @Test
    public void testDeleteByCc() {
        assertEquals(0, adminDAO.deleteByCc(marshallMathers.getIdentificationNumber()));
        String result = adminDAO.readByCc(marshallMathers.getIdentificationNumber());
        assertEquals("NO INFO", result);
    }
}


