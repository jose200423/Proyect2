package co.edu.unbosque.model;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class PersonDTOTest {

   
    public void testToString() throws ParseException {
        String name = "Marshal Mathers";
        long identificationNumber = 987654321;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = dateFormat.parse("1972-10-17");
        String cityOfBorn = "St. Joseph, Missouri";

        PersonDTO personDTO = new PersonDTO(name, identificationNumber, birthday, cityOfBorn);

        String expectedToString = "The name of this person is: " + name + "\n" +
                "The identification number is: " + identificationNumber + "\n" +
                "The Birthday is: " + birthday + "\n" +
                "It was borned on: " + cityOfBorn + "\n";

        assertEquals(expectedToString, personDTO.toString());
    }

   
    public void testGetterAndSetter() throws ParseException {
        String name = "Marshal Mathers";
        long identificationNumber = 987654321;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = dateFormat.parse("1972-10-17");
        String cityOfBorn = "St. Joseph, Missouri";

        PersonDTO personDTO = new PersonDTO(name, identificationNumber, birthday, cityOfBorn);

        assertEquals(name, personDTO.getName());
        assertEquals(identificationNumber, personDTO.getIdentificationNumber());
        assertEquals(birthday, personDTO.getBirthday());
        assertEquals(cityOfBorn, personDTO.getCityOfBorn());

        String newName = "Eminem";
        long newIdentificationNumber = 123456789;
        Date newBirthday = dateFormat.parse("2000-01-01");
        String newCityOfBorn = "Detroit, Michigan";

        personDTO.setName(newName);
        personDTO.setIdentificationNumber(newIdentificationNumber);
        personDTO.setBirthday(newBirthday);
        personDTO.setCityOfBorn(newCityOfBorn);

        assertEquals(newName, personDTO.getName());
        assertEquals(newIdentificationNumber, personDTO.getIdentificationNumber());
        assertEquals(newBirthday, personDTO.getBirthday());
        assertEquals(newCityOfBorn, personDTO.getCityOfBorn());
    }
}

