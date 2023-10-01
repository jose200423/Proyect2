package co.edu.unbosque.model;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class PsychologistDTOTest {

    @Test
    public void testToString() {
        // Información de prueba
        String name = "Marshall Mathers";
        long identificationNumber = 1234567890L;
        Date birthday = new Date();
        String cityOfBorn = "New York";
        Date graduationYear = new Date();
        int daysSinceGraduation = 100;
        int supportedSessions = 50;
        int salary = 8000;
       

        // Crear un objeto PsychologistDTO
        PsychologistDTO psychologistDTO = new PsychologistDTO(name, identificationNumber, birthday, cityOfBorn,
                graduationYear, daysSinceGraduation, supportedSessions, salary);

     

        // Resultado esperado
        String expected = "The name of this person is: " + name + "\n" +
                "The identification number is: " + identificationNumber + "\n" +
                "The Birthday is: " + birthday + "\n" +
                "It was borned on: " + cityOfBorn + "\n" +
                "Graduation's year: " + graduationYear + "\n" +
                "Days since graduation: " + daysSinceGraduation + "\n" +
                "Number of sessions supported: " + supportedSessions + "\n" +
                "Salary is : " + salary + "\n";

        // Obtener el resultado de toString() del objeto PsychologistDTO
        String actual = psychologistDTO.toString();

        // Comparar el resultado actual con el esperado
        assertEquals(expected, actual);
    }
}

