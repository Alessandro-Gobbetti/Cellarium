package parser;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests toString(), isConstant(), and getType() of Node subclasses.
 */
public class NodeTest {

    @Test
    public void testLiteral() {
        Node e = new Literal(5.0);
        assertTrue(e.isConstant());
        assertEquals("5.0", e.toString());
        assertEquals(Type.DOUBLE, e.getType());
    }
    
    @Test
    public void testIntVariable() {
        Node e = new Variable("x");
        assertFalse(e.isConstant());
        assertEquals("x", e.toString());
        assertEquals(Type.INVALID, e.getType());
    }

    @Test
    public void testNegation() {
        Node e = new Negation(new Literal(5.0));
        assertTrue(e.isConstant());
        assertEquals("(-5.0)", e.toString());
        assertEquals(Type.DOUBLE, e.getType());
    }
    
    @Test
    public void testAddition() {
        Node e = new Addition(new Literal(5.0), new Literal(6.0));
        Node a = new Addition(new Variable("x"), new Literal(6.0));
        Node i = new Addition(new Literal(5.0), new Variable("x"));
        assertTrue(e.isConstant());
        assertFalse(a.isConstant());
        assertFalse(i.isConstant());
        assertEquals("(5.0+6.0)", e.toString());
        assertEquals(Type.DOUBLE, e.getType());
    }

    @Test
    public void testSubtraction() {
        Node e = new Subtraction(new Literal(5.0), new Literal(6.0));
        Node a = new Subtraction(new Variable("x"), new Literal(6.0));
        Node i = new Subtraction(new Literal(5.0), new Variable("x"));
        assertTrue(e.isConstant());
        assertFalse(a.isConstant());
        assertFalse(i.isConstant());
        assertEquals("(5.0-6.0)", e.toString());
        assertEquals(Type.DOUBLE, e.getType());
    }
    
    @Test
    public void testMultiplication() {
        Node e = new Multiplication(new Literal(5.0), new Literal(6.0));
        Node a = new Multiplication(new Variable("x"), new Literal(6.0));
        Node i = new Multiplication(new Literal(5.0), new Variable("x"));
        assertTrue(e.isConstant());
        assertFalse(a.isConstant());
        assertFalse(i.isConstant());
        assertEquals("(5.0*6.0)", e.toString());
        assertEquals(Type.DOUBLE, e.getType());
    }
    
    @Test
    public void testDivision() {
        Node e = new Division(new Literal(5.0), new Literal(6.0));
        Node a = new Division(new Variable("x"), new Literal(6.0));
        Node i = new Division(new Literal(5.0), new Variable("x"));
        assertTrue(e.isConstant());
        assertFalse(a.isConstant());
        assertFalse(i.isConstant());
        assertEquals("(5.0/6.0)", e.toString());
        assertEquals(Type.DOUBLE, e.getType());
    }
    
    @Test
    public void testSin() {
        Node e = new Sin(new Literal(90.0));
        Node a = new Sin(new Variable("x"));
        assertTrue(e.isConstant());
        assertFalse(a.isConstant());
        assertEquals("(sin(90.0))", e.toString());
        assertEquals(Type.DOUBLE, e.getType());
    }
}
