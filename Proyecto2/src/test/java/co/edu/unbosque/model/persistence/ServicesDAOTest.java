package co.edu.unbosque.model.persistence;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import co.edu.unbosque.controller.DBConnection;
import co.edu.unbosque.model.ServicesDTO;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ServicesDAOTest {

    private ServicesDAO servicesDAO;

    
    public void setUp() {
        servicesDAO = new ServicesDAO();
    }

  
    public void testCreate() throws SQLException {
        ServicesDTO servicesDTO = new ServicesDTO("Marshal Mathers", 123456789, new Date(72, 9, 17), "St. Joseph, Missouri", 5000, 30);

        
        DBConnection dbConnection = Mockito.mock(DBConnection.class);
        servicesDAO.setDbcon(dbConnection);

    
        Mockito.when(dbConnection.getConect().prepareStatement(Mockito.anyString())).thenReturn(Mockito.mock(java.sql.PreparedStatement.class));

        boolean result = servicesDAO.create(servicesDTO);

        assertEquals(true, result);
    }


    public void testReadAll() throws SQLException {
    
        DBConnection dbConnection = Mockito.mock(DBConnection.class);
        servicesDAO.setDbcon(dbConnection);

      
        java.sql.ResultSet resultSet = Mockito.mock(java.sql.ResultSet.class);
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getString("allname")).thenReturn("Marshal Mathers");
        Mockito.when(resultSet.getLong("cc")).thenReturn(123456789L);
        Mockito.when(resultSet.getDate("birthdate")).thenReturn(new Date(72, 9, 17));
        Mockito.when(resultSet.getString("city")).thenReturn("St. Joseph, Missouri");
        Mockito.when(resultSet.getInt("salary")).thenReturn(5000);
        Mockito.when(resultSet.getInt("cleanup")).thenReturn(30);

        Mockito.when(dbConnection.getStatement().executeQuery(Mockito.anyString())).thenReturn(resultSet);

        String result = servicesDAO.readAll();

        assertEquals("The name of this person is: Marshal Mathers\n" +
                "The identification number is: 123456789\n" +
                "The Birthday is: 1972-10-17\n" +
                "It was borned on: St. Joseph, Missouri\n" +
                "The salary is: 5000\n" +
                "Number of sessions cleaned: 30\n", result);
    }
    
    


}

