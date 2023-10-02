package co.edu.unbosque.model;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class ServicesDTOTest {

	@Test
    public void testToString() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = dateFormat.parse("1972-10-17");
        ServicesDTO servicesDTO = new ServicesDTO("Marshal Mathers", 123456789, birthday, "St. Joseph, Missouri", 5000, 30);

        String expected = "The name of this person is: Marshal Mathers\n" +
                          "The identification number is: 123456789\n" +
                          "The Birthday is: Tue Oct 17 00:00:00 COT 1972\n" +
                          "It was borned on: St. Joseph, Missouri\n" +
                          "The salary is: 5000\n" +
                          "Number of sessions cleaned: 30\n";

        assertEquals(expected, servicesDTO.toString());
    }



	@Test
    public void testGetSalary() {
        ServicesDTO servicesDTO = new ServicesDTO();
        servicesDTO.setSalary(5000);

        assertEquals(5000, servicesDTO.getSalary());
    }

	@Test
    public void testGetSessionsCleaned() {
        ServicesDTO servicesDTO = new ServicesDTO();
        servicesDTO.setSessionsCleaned(30);

        assertEquals(30, servicesDTO.getSessionsCleaned());
    }
}

