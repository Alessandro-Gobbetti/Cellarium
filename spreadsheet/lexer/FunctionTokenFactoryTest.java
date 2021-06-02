package spreadsheet.lexer;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class FunctionTokenFactoryTest {
    
       
    @Test
    public void testFoundLength4() {
        FunctionTokenFactory f = new FunctionTokenFactory();
        f.setText("SUM(A1:B3)");
        boolean found = f.find(0);
        assertTrue(found);
        assertEquals(4, f.getTokenLength());
        assertEquals(0, f.getTokenStartPosition());
        assertEquals("SUM(", f.getTokenText());
        assertEquals(TokenType.FUNCTION, f.getToken().getType());
    }
    
    @Test
    public void testNoMatchNotFound() {
        FunctionTokenFactory f = new FunctionTokenFactory();
        f.setText("123=456");
        boolean found = f.find(4);
        assertFalse(found);
    }

}
