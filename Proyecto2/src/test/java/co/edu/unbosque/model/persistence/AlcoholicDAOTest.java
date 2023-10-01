package co.edu.unbosque.model.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import co.edu.unbosque.model.AlcoholicDTO;
import co.edu.unbosque.model.persistence.AlcoholicDAO;

public class AlcoholicDAOTest {

    public void testCreate() throws ParseException {
        String name = "Marshal Mathers";
        long identificationNumber = 987654321;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = dateFormat.parse("1972-10-17");
        String cityOfBorn = "St. Joseph, Missouri";
        int sessionsPresent = 10;
        String nickname = "Slim Shady";

        AlcoholicDTO alcoholicDTO = new AlcoholicDTO(name, identificationNumber, birthday, cityOfBorn, sessionsPresent, nickname);
        AlcoholicDAO alcoholicDAO = new AlcoholicDAO();

      
        assertTrue(alcoholicDAO.create(alcoholicDTO));

        String expectedToString = "The name of this person is: " + name + "\n" +
                "The identification number is: " + identificationNumber + "\n" +
                "The Birthday is: " + birthday + "\n" +
                "It was borned on: " + cityOfBorn + "\n" +
                "Person has been in: " + sessionsPresent + " sessions \n" +
                "Nickname of this person is: " + nickname + "\n";

        assertEquals(expectedToString, alcoholicDAO.readByCc(identificationNumber));
    }

 
    public void testDeleteByCc() throws ParseException {
        String name = "Marshal Mathers";
        long identificationNumber = 987654321;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = dateFormat.parse("1972-10-17");
        String cityOfBorn = "St. Joseph, Missouri";
        int sessionsPresent = 10;
        String nickname = "Slim Shady";

        AlcoholicDTO alcoholicDTO = new AlcoholicDTO(name, identificationNumber, birthday, cityOfBorn, sessionsPresent, nickname);
        AlcoholicDAO alcoholicDAO = new AlcoholicDAO();

        
        alcoholicDAO.create(alcoholicDTO);

      
        assertEquals(alcoholicDTO.toString(), alcoholicDAO.readByCc(identificationNumber));

      
        assertEquals(0, alcoholicDAO.deleteByCc(identificationNumber));

    
        assertEquals("NO INFO", alcoholicDAO.readByCc(identificationNumber));
    }


    public void testReadByCcNonExistent() {
        long nonExistentCc = 123456789;  

        AlcoholicDAO alcoholicDAO = new AlcoholicDAO();

        assertEquals("NO INFO", alcoholicDAO.readByCc(nonExistentCc));
    }

    public void testUpdateByCc() throws ParseException {
        String name = "Marshal Mathers";
        long identificationNumber = 987654321;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = dateFormat.parse("1972-10-17");
        String cityOfBorn = "St. Joseph, Missouri";
        int sessionsPresent = 10;
        String nickname = "Slim Shady";

        AlcoholicDTO alcoholicDTO = new AlcoholicDTO(name, identificationNumber, birthday, cityOfBorn, sessionsPresent, nickname);
        AlcoholicDAO alcoholicDAO = new AlcoholicDAO();

        
        alcoholicDAO.create(alcoholicDTO);

        String updatedName = "Eminem";
        String updatedCity = "Detroit, Michigan";
        int updatedSessions = 15;

      
        assertEquals(0, alcoholicDAO.updateByCc(identificationNumber, updatedName, String.valueOf(identificationNumber),
                "1980-10-17", updatedCity, String.valueOf(updatedSessions), "updated_nickname"));

        String expectedUpdatedToString = "The name of this person is: " + updatedName + "\n" +
                "The identification number is: " + identificationNumber + "\n" +
                "The Birthday is: 1980-10-17\n" +
                "It was borned on: " + updatedCity + "\n" +
                "Person has been in: " + updatedSessions + " sessions \n" +
                "Nickname of this person is: updated_nickname\n";

   
        assertEquals(expectedUpdatedToString, alcoholicDAO.readByCc(identificationNumber));
    }

    
    public void testValidate() throws ParseException {
        String name = "Marshal Mathers";
        long identificationNumber = 987654321;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = dateFormat.parse("1972-10-17");
        String cityOfBorn = "St. Joseph, Missouri";
        int sessionsPresent = 10;
        String nickname = "Slim Shady";

        AlcoholicDTO alcoholicDTO = new AlcoholicDTO(name, identificationNumber, birthday, cityOfBorn, sessionsPresent, nickname);
        AlcoholicDAO alcoholicDAO = new AlcoholicDAO();

       
        alcoholicDAO.create(alcoholicDTO);

        
        assertTrue(alcoholicDAO.validate(name, String.valueOf(identificationNumber)));

        
        assertFalse(alcoholicDAO.validate("Non Existent", "123456789"));
    }
}
