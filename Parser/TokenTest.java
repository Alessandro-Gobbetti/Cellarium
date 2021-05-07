import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TokenTest {
    
    @Test
    public void testLength1() {
        Token t = new Token(TokenType.PLUS, "+", 0);
        assertEquals(TokenType.PLUS, t.getType());
        assertEquals("+", t.getText());
        assertEquals(0, t.getStartPosition());
        assertEquals(1, t.getEndPosition());
    }
    
    @Test
    public void testLength2() {
        Token t = new Token(TokenType.IDENTIFIER, "id", 3);
        assertEquals(TokenType.IDENTIFIER, t.getType());
        assertEquals("id", t.getText());
        assertEquals(3, t.getStartPosition());
        assertEquals(5, t.getEndPosition());
    }
    
    @Test
    public void testLength3() {
        Token t = new Token(TokenType.LITERAL, "456", 60);
        assertEquals(TokenType.LITERAL, t.getType());
        assertEquals("456", t.getText());
        assertEquals(60, t.getStartPosition());
        assertEquals(63, t.getEndPosition());
    }
    
}
