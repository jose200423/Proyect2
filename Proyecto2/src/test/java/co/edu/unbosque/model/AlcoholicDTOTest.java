package co.edu.unbosque.model;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class AlcoholicDTOTest {

    
    public void testToString() throws ParseException {
        String name = "Marshal Mathers";
        long identificationNumber = 987654321;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = dateFormat.parse("1972-10-17");
        String cityOfBorn = "St. Joseph, Missouri";
        int sessionsPresent = 10;
        String nickname = "Slim Shady";

        AlcoholicDTO alcoholicDTO = new AlcoholicDTO(name, identificationNumber, birthday, cityOfBorn, sessionsPresent, nickname);

        String expectedToString = "The name of this person is: " + name + "\n" +
                "The identification number is: " + identificationNumber + "\n" +
                "The Birthday is: " + birthday + "\n" +
                "It was borned on: " + cityOfBorn + "\n" +
                "Person has been in: " + sessionsPresent + " sessions \n" +
                "Nickname of this person is: " + nickname + "\n";

        assertEquals(expectedToString, alcoholicDTO.toString());
    }

   
    public void testGetterAndSetter() throws ParseException {
        String name = "Marshal Mathers";
        long identificationNumber = 987654321;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = dateFormat.parse("1972-10-17");
        String cityOfBorn = "St. Joseph, Missouri";
        int sessionsPresent = 10;
        String nickname = "Slim Shady";

        AlcoholicDTO alcoholicDTO = new AlcoholicDTO(name, identificationNumber, birthday, cityOfBorn, sessionsPresent, nickname);

        assertEquals(name, alcoholicDTO.getName());
        assertEquals(identificationNumber, alcoholicDTO.getIdentificationNumber());
        assertEquals(birthday, alcoholicDTO.getBirthday());
        assertEquals(cityOfBorn, alcoholicDTO.getCityOfBorn());
        assertEquals(sessionsPresent, alcoholicDTO.getSessionsPresent());
        assertEquals(nickname, alcoholicDTO.getNickname());

        int newSessionsPresent = 20;
        String newNickname = "Eminem";

        alcoholicDTO.setSessionsPresent(newSessionsPresent);
        alcoholicDTO.setNickname(newNickname);

        assertEquals(newSessionsPresent, alcoholicDTO.getSessionsPresent());
        assertEquals(newNickname, alcoholicDTO.getNickname());
    }
}
