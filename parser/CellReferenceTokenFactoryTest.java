package parser;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CellReferenceTokenFactoryTest {
    
    @Test
    public void testFoundLength1() {
        CellReferenceTokenFactory f = new CellReferenceTokenFactory();
        f.setText("$A1");
        boolean found = f.find(0);
        assertTrue(found);
        assertEquals(3, f.getTokenLength());
        assertEquals(0, f.getTokenStartPosition());
        assertEquals("$A1", f.getTokenText());
        assertEquals(TokenType.CELLREFERENCE, f.getToken().getType());
    }

}
