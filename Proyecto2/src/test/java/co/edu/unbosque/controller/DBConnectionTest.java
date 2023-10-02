package co.edu.unbosque.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

public class DBConnectionTest {

    private DBConnection dbConnection;

    @Before
    public void setUp() {
        dbConnection = new DBConnection();
    }

    @Test
    public void testInitConnection() throws SQLException {
        // Mock de la conexión
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);

        // Configura las respuestas simuladas
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockConnection.isValid(1)).thenReturn(true);

        // Valida la conexión
        Connection validatedConnection = validateConnection(mockConnection);

        // Llama al método de inicialización
        dbConnection.setConect(validatedConnection);
        dbConnection.initConnection();

        // Verifica que la conexión sea la misma que la mockeada
        assertSame(validatedConnection, dbConnection.getConect());
    }

    private Connection validateConnection(Connection connection) throws SQLException {
        // Valida la conexión
        if (!connection.isValid(1)) {
            throw new SQLException("La conexión no es válida.");
        }

        return connection;
    }


    @Test
    public void testClose() throws SQLException {
        // Mock de la conexión
        Connection mockConnection = mock(Connection.class);

        // Simula el cierre de la conexión
        dbConnection.setConect(mockConnection);
        dbConnection.close();

        // Verifica que el cierre de la conexión se haya llamado
        verify(mockConnection).close();
    }

    // Agrega más pruebas según lo necesites
}

