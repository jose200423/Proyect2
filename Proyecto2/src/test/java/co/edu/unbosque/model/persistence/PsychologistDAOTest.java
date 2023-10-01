package co.edu.unbosque.model.persistence;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.Test;

import co.edu.unbosque.model.PsychologistDTO;

public class PsychologistDAOTest {

    
    public void testCreate() {
        PsychologistDAO psychologistDAO = new PsychologistDAO();

        
        String name = "Marshall Mathers";
        long identificationNumber = 987654321L;
        Date birthday = new Date(System.currentTimeMillis()); // 
        String cityOfBorn = "Detroit";
        int graduationYear = 2000;
        int daysSinceGraduation = 5000;
        int supportedSessions = 300;
        int salary = 7000;

        PsychologistDTO psychologistDTO = new PsychologistDTO(name, identificationNumber, birthday, cityOfBorn, graduationYear, daysSinceGraduation, supportedSessions, salary);

        boolean result = psychologistDAO.create(psychologistDTO);
        assertEquals(true, result);
    }

  
    public void testReadByCc() {
        PsychologistDAO psychologistDAO = new PsychologistDAO();

        
        long identificationNumber = 987654321L;

        String expectedOutput = "The name of this person is: Marshall Mathers\n" +
                "The identification number is: 987654321\n" +
                "The Birthday is: " + new Date(System.currentTimeMillis()) + "\n" +
                "It was borned on: Detroit\n" +
                "Graduation's year: 2000\n" +
                "Days since graduation: 5000\n" +
                "Number of sessions supported: 300\n" +
                "Salary is : 7000\n";

        String actualOutput = psychologistDAO.readByCc(identificationNumber);
        assertEquals(expectedOutput, actualOutput);
    }

    
    public void testUpdateByCc() {
        PsychologistDAO psychologistDAO = new PsychologistDAO();

        
        long identificationNumber = 987654321L;

        String updatedName = "Eminem";
        String updatedCity = "New York";
        int updatedGraduationYear = 2001;
        int updatedDaysSinceGraduation = 6000;
        int updatedSupportedSessions = 400;
        int updatedSalary = 8000;

        int result = psychologistDAO.updateByCc(identificationNumber, updatedName, "2003-05-01", updatedCity, String.valueOf(updatedGraduationYear), String.valueOf(updatedDaysSinceGraduation), String.valueOf(updatedSupportedSessions), String.valueOf(updatedSalary));
        assertEquals(0, result);
    }

   
    public void testDeleteByCc() {
        PsychologistDAO psychologistDAO = new PsychologistDAO();

    
        long identificationNumber = 987654321L;

        int result = psychologistDAO.deleteByCc(identificationNumber);
        assertEquals(0, result);
    }

 
    public void testReadAll() {
        PsychologistDAO psychologistDAO = new PsychologistDAO();

       
        long identificationNumber = 987654321L;

        String expectedOutput = "The name of this person is: Marshall Mathers\n" +
                "The identification number is: 987654321\n" +
                "The Birthday is: " + new Date(System.currentTimeMillis()) + "\n" +
                "It was borned on: Detroit\n" +
                "Graduation's year: 2000\n" +
                "Days since graduation: 5000\n" +
                "Number of sessions supported: 300\n" +
                "Salary is : 7000\n";

        String actualOutput = psychologistDAO.readAll();
        assertEquals(expectedOutput, actualOutput);
    }
}

