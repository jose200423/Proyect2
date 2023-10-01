package co.edu.unbosque.model;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

public class PsychologistDTOTest {

 
    public void testGettersAndSetters() {
     
        String name = "Marshall Mathers";
        long identificationNumber = 987654321L;
        Date birthday = new Date();  
        String cityOfBorn = "Detroit";
        int graduationYear = 2000;
        int daysSinceGraduation = 5000;
        int supportedSessions = 300;
        int salary = 7000;

        PsychologistDTO psychologistDTO = new PsychologistDTO(name, identificationNumber, birthday, cityOfBorn, graduationYear, daysSinceGraduation, supportedSessions, salary);

        assertEquals(name, psychologistDTO.getName());
        assertEquals(identificationNumber, psychologistDTO.getIdentificationNumber());
        assertEquals(birthday, psychologistDTO.getBirthday());
        assertEquals(cityOfBorn, psychologistDTO.getCityOfBorn());
        assertEquals(graduationYear, psychologistDTO.getGraduationYear());
        assertEquals(daysSinceGraduation, psychologistDTO.getDaysSinceGraduation());
        assertEquals(supportedSessions, psychologistDTO.getSupportedSessions());
        assertEquals(salary, psychologistDTO.getSalary());
    }

    
    public void testToString() {
        
        String name = "Marshall Mathers";
        long identificationNumber = 987654321L;
        Date birthday = new Date();  
        String cityOfBorn = "Detroit";
        int graduationYear = 2000;
        int daysSinceGraduation = 5000;
        int supportedSessions = 300;
        int salary = 7000;

        PsychologistDTO psychologistDTO = new PsychologistDTO(name, identificationNumber, birthday, cityOfBorn, graduationYear, daysSinceGraduation, supportedSessions, salary);

        String expectedToString = "The name of this person is: " + name + "\n" +
                "The identification number is: " + identificationNumber + "\n" +
                "The Birthday is: " + birthday + "\n" +
                "It was borned on: " + cityOfBorn + "\n" +
                "Graduation's year: " + graduationYear + "\n" +
                "Days since graduation: " + daysSinceGraduation + "\n" +
                "Number of sessions supported: " + supportedSessions + "\n" +
                "Salary is : " + salary + "\n";

        assertEquals(expectedToString, psychologistDTO.toString());
    }
}
