package co.edu.unbosque.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class AdminDTOTest {

    
    public void testToString() throws ParseException {
        String name = "Marshall Mathers";
        long identificationNumber = 1234567890L;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = dateFormat.parse("1972-10-17");
        String cityOfBorn = "St. Joseph, Missouri";

        AdminDTO adminDTO = new AdminDTO(name, identificationNumber, birthday, cityOfBorn);

        String expectedToString = "The name of this person is: " + name + "\n" +
                "The identification number is: " + identificationNumber + "\n" +
                "The Birthday is: " + birthday + "\n" +
                "It was borned on: " + cityOfBorn + "\n";

        assertEquals(expectedToString, adminDTO.toString());
    }
}

