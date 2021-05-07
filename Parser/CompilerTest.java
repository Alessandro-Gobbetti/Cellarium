import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Test compile() of Node subclasses
 * (and toString() of Instruction subclasses).
 * This tests that the compile() methods generate the correct
 * sequence of Instructions.
 */
public class CompilerTest {
    
    @Test
    public void testLiteral() {
        Compiler c = new Compiler();
        Node n = new Literal(5);
        Program p = c.compile(n);
        assertEquals(1, p.getLength());
        assertTrue(p.get(0) instanceof BIPUSH);
        assertEquals(new BIPUSH(5).toString(), p.get(0).toString());
    }

    @Test
    public void testNegation() {
        Compiler c = new Compiler();
        Node n = new Negation(new Literal(5));
        Program p = c.compile(n);
        assertEquals(2, p.getLength());
        assertTrue(p.get(0) instanceof BIPUSH);
        assertEquals(new BIPUSH(5).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof INEG);
    }
    
    @Test
    public void testAddition() {
        Compiler c = new Compiler();
        Node n = new Addition(new Literal(5), new Literal(6));
        Program p = c.compile(n);
        assertEquals(3, p.getLength());
        assertTrue(p.get(0) instanceof BIPUSH);
        assertEquals(new BIPUSH(5).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof BIPUSH);
        assertEquals(new BIPUSH(6).toString(), p.get(1).toString());
        assertTrue(p.get(2) instanceof IADD);
    }
    
    @Test
    public void testSubtraction() {
        Compiler c = new Compiler();
        Node n = new Subtraction(new Literal(5), new Literal(6));
        Program p = c.compile(n);
        assertEquals(3, p.getLength());
        assertTrue(p.get(0) instanceof BIPUSH);
        assertEquals(new BIPUSH(5).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof BIPUSH);
        assertEquals(new BIPUSH(6).toString(), p.get(1).toString());
        assertTrue(p.get(2) instanceof ISUB);
    }
    
    @Test
    public void testVariable() {
        Compiler c = new Compiler();
        Node n = new Variable("x");
        Program p = c.compile(n);
        assertEquals(1, p.getLength());
        assertTrue(p.get(0) instanceof ILOAD);
        assertEquals(new ILOAD("x").toString(), p.get(0).toString());
    }
    
    @Test
    public void testMultiplication() {
        Compiler c = new Compiler();
        Node n = new Multiplication(new Literal(5), new Literal(6));
        Program p = c.compile(n);
        assertEquals(3, p.getLength());
        assertTrue(p.get(0) instanceof BIPUSH);
        assertEquals(new BIPUSH(5).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof BIPUSH);
        assertEquals(new BIPUSH(6).toString(), p.get(1).toString());
        assertTrue(p.get(2) instanceof IMUL);
    }
    
    @Test
    public void testDivision() {
        Compiler c = new Compiler();
        Node n = new Division(new Literal(10), new Literal(2));
        Program p = c.compile(n);
        assertEquals(3, p.getLength());
        assertTrue(p.get(0) instanceof BIPUSH);
        assertEquals(new BIPUSH(10).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof BIPUSH);
        assertEquals(new BIPUSH(2).toString(), p.get(1).toString());
        assertTrue(p.get(2) instanceof IDIV);
    }
    
}
