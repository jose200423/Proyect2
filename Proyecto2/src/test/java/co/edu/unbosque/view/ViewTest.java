package co.edu.unbosque.view;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ViewTest {
    private View view;

    @Before
    public void setUp() {
     
        String input = "123\n456789\n123.45\n678.90\nA\nTest\nThis is a test\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        view = new View();
    }

  
    public void testReadInt() {
        assertEquals(123, view.readInt());
    }

    @Test
    public void testReadLong() {
        assertEquals(123L, view.readLong());
    }

    @Test
 
    public void testReadFloat() {
        float expectedValue = 123.0f;
        float actualValue = view.readFloat();
        float delta = 0.01f;  //

        assertEquals(expectedValue, actualValue, delta);
    }


  
    public void testReadDouble() {
        assertEquals(123.45, view.readDoble(), 0.001);
    }


    @Test
    public void testReadLine() {
        assertEquals("123", view.readLine());
    }

    @Test
    public void testReadAllLine() {
    }
}
