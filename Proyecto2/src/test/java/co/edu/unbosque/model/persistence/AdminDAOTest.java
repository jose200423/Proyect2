package co.edu.unbosque.model.persistence;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import co.edu.unbosque.model.AdminDTO;

public class AdminDAOTest {

 
    public void testReadAll() throws ParseException {
        String name = "Marshall Mathers";
        long identificationNumber = 1234567890L;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = dateFormat.parse("1972-10-17");
        String cityOfBorn = "St. Joseph, Missouri";

        AdminDTO adminDTO = new AdminDTO(name, identificationNumber, birthday, cityOfBorn);
        AdminDAO adminDAO = new AdminDAO();

        adminDAO.create(adminDTO);

        String expectedToString = "The name of this person is: " + name + "\n" +
                "The identification number is: " + identificationNumber + "\n" +
                "The Birthday is: " + birthday + "\n" +
                "It was borned on: " + cityOfBorn + "\n";

        assertEquals(expectedToString, adminDAO.readAll());
    }

   
    public void testReadByCc() throws ParseException {
        String name = "Marshall Mathers";
        long identificationNumber = 1234567890L;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = dateFormat.parse("1972-10-17");
        String cityOfBorn = "St. Joseph, Missouri";

        AdminDTO adminDTO = new AdminDTO(name, identificationNumber, birthday, cityOfBorn);
        AdminDAO adminDAO = new AdminDAO();

        
        adminDAO.create(adminDTO);

        String expectedToString = "The name of this person is: " + name + "\n" +
                "The identification number is: " + identificationNumber + "\n" +
                "The Birthday is: " + birthday + "\n" +
                "It was borned on: " + cityOfBorn + "\n";

        assertEquals(expectedToString, adminDAO.readByCc(identificationNumber));
    }
}


