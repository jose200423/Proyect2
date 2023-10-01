package co.edu.unbosque.model.persistence;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import co.edu.unbosque.model.ServicesDTO;
import co.edu.unbosque.model.persistence.ServicesDAO;

public class ServicesDAOTest {

    private ServicesDAO servicesDAO;
    private ServicesDTO marshallMathersServices;

    @Before
    public void setUp() {
        servicesDAO = new ServicesDAO();

        // Llenar con la información de Marshall Mathers (Eminem)
        marshallMathersServices = new ServicesDTO();
        marshallMathersServices.setName("Marshall Mathers");
        marshallMathersServices.setIdentificationNumber(1234567890L);
        marshallMathersServices.setBirthday(new Date(72, 9, 17)); // Ajustar la fecha según sea necesario
        marshallMathersServices.setCityOfBorn("Detroit");
        marshallMathersServices.setSalary(100000);
        marshallMathersServices.setSessionsCleaned(500);
    }

    @Test
    public void testCreate() {
        assertTrue(servicesDAO.create(marshallMathersServices));
    }

    @Test
    public void testReadByCc() {
        String result = servicesDAO.readByCc(marshallMathersServices.getIdentificationNumber());
        assertNotNull(result);
        assertTrue(result.contains("Marshall Mathers"));
    }

    @Test
    public void testUpdateByCc() {
        String newCity = "New York";
        assertEquals(0, servicesDAO.updateByCc(marshallMathersServices.getIdentificationNumber(), "Marshall Mathers", "1972/10/17", newCity, "200000", "600"));
        String result = servicesDAO.readByCc(marshallMathersServices.getIdentificationNumber());
        assertNotNull(result);
        assertTrue(result.contains(newCity));
    }

    @Test
    public void testDeleteByCc() {
        assertEquals(0, servicesDAO.deleteByCc(marshallMathersServices.getIdentificationNumber()));
        String result = servicesDAO.readByCc(marshallMathersServices.getIdentificationNumber());
        assertEquals("NO INFO", result);
    }
}


