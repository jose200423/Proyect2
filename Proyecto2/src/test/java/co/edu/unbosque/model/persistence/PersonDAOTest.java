package co.edu.unbosque.model.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import co.edu.unbosque.model.PersonDTO;

public class PersonDAOTest {

    private PersonDAO personDAO;

    @Before
    public void setUp() {
        personDAO = new PersonDAO();
    }

    @Test
    public void testCreate() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = new Date(dateFormat.parse("1980-10-17").getTime());
        PersonDTO personDTO = new PersonDTO("Marshal Mathers", 123456789, birthday, "St. Joseph, Missouri");

        assertTrue(personDAO.create(personDTO));
    }

    @Test
    public void testReadAll() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = new Date(dateFormat.parse("1980-10-17").getTime());
        PersonDTO personDTO = new PersonDTO("Marshal Mathers", 123456789, birthday, "St. Joseph, Missouri");

        personDAO.create(personDTO);
        String expected = personDTO.toString() + "\n";

        assertEquals(expected, personDAO.readAll());
    }

    @Test
    public void testReadByCc() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = new Date(dateFormat.parse("1980-10-17").getTime());
        PersonDTO personDTO = new PersonDTO("Marshal Mathers", 123456789, birthday, "St. Joseph, Missouri");

        personDAO.create(personDTO);
        String expected = personDTO.toString() + "\n";

        assertEquals(expected, personDAO.readByCc(123456789));
    }

    @Test
    public void testUpdateByCc() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = new Date(dateFormat.parse("1980-10-17").getTime());
        PersonDTO personDTO = new PersonDTO("Marshal Mathers", 123456789, birthday, "St. Joseph, Missouri");
        personDAO.create(personDTO);

        Date newBirthday = new Date(dateFormat.parse("1972-10-17").getTime());
        assertTrue(personDAO.updateByCc(123456789, "Eminem", newBirthday.toString(), "Detroit, Michigan") == 0);
    }

    @Test
    public void testDeleteByCc() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = new Date(dateFormat.parse("1980-10-17").getTime());
        PersonDTO personDTO = new PersonDTO("Marshal Mathers", 123456789, birthday, "St. Joseph, Missouri");
        personDAO.create(personDTO);

        assertTrue(personDAO.deleteByCc(123456789) == 0);
        assertTrue(personDAO.readByCc(123456789).equals("NO INFO"));
    }

    @Test
    public void testValidate() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = new Date(dateFormat.parse("1980-10-17").getTime());
        PersonDTO personDTO = new PersonDTO("Marshal Mathers", 123456789, birthday, "St. Joseph, Missouri");
        personDAO.create(personDTO);

        assertTrue(personDAO.validate("Marshal Mathers", "123456789"));
        assertFalse(personDAO.validate("Eminem", "123456789"));
        assertFalse(personDAO.validate("Marshal Mathers", "987654321"));
    }
}

