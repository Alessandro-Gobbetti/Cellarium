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
        Node e = new Sine(new Literal(90.0));
        Node a = new Sine(new Variable("x"));
        assertTrue(e.isConstant());
        assertFalse(a.isConstant());
        assertEquals("sin(90.0)", e.toString());
        assertEquals(Type.DOUBLE, e.getType());
    }
    
    @Test 
    public void testAdditionEval() {
        Node e = new Addition(new Literal(5.0), new Literal(6.0));
        Node a = new Addition(new Literal(3.0), new Literal(3.0));
        assertEquals(11.0, e.eval(), 0.0);
        assertEquals(6.0, a.eval(), 0.0);
    }
    
    @Test 
    public void testSubtractionEval() {
        Node e = new Subtraction(new Literal(10.0), new Literal(6.0));
        Node a = new Subtraction(new Literal(3.0), new Literal(4.0));
        assertEquals(4.0, e.eval(), 0.0);
        assertEquals(-1.0, a.eval(), 0.0);
    }
    
    @Test 
    public void testMultiplicationEval() {
        Node e = new Multiplication(new Literal(5.0), new Literal(6.0));
        Node a = new Multiplication(new Literal(3.0), new Literal(0.5));
        assertEquals(30.0, e.eval(), 0.0);
        assertEquals(1.5, a.eval(), 0.0);
    }
    
    @Test 
    public void testDivisionEval() {
        Node e = new Division(new Literal(6.0), new Literal(3.0));
        Node a = new Division(new Literal(22.5), new Literal(3.0));
        assertEquals(2.0, e.eval(), 0.0);
        assertEquals(7.5, a.eval(), 0.0);
    }
    
    @Test 
    public void testLiteralEval() {
        Node e = new Literal(5.0);
        Node a = new Literal(3.0);
        assertEquals(5.0, e.eval(), 0.0);
        assertEquals(3.0, a.eval(), 0.0);
    }
    
    @Test
    public void testSinEval() {
        Node e = new Sine(new Literal(90.0));
        Node a = new Sine(new Literal(180.0));
        Node i = new Sine(new Literal(0.0));
        Node j = new Sine(new Negation(new Literal(180.0)));
        assertEquals(0.893996663600558, e.eval(), 0.0000000001);
        assertEquals(-0.80115263573383, a.eval(), 0.0000000001);
        assertEquals(0.0, i.eval(), 0.0);
        assertEquals(0.80115263573383, j.eval(), 0.0000000001);
    }
    
    @Test
    public void testCosEval() {
        Node e = new Cosine(new Literal(90.0));
        Node a = new Cosine(new Literal(180.0));
        Node i = new Cosine(new Literal(0.0));
        Node j = new Cosine(new Negation(new Literal(180.0)));
        assertEquals(-0.44807361612917, e.eval(), 0.0000000001);
        assertEquals(-0.598460069057858, a.eval(), 0.0000000001);
        assertEquals(1.0, i.eval(), 0.0);
        assertEquals(-0.598460069057858, j.eval(), 0.0000000001);
    }
    
    @Test
    public void testNegationEval() {
        Node e = new Negation(new Literal(2.0));
        Node a = new Negation(new Literal(-18.0));
        Node i = new Negation(new Literal(0.0));
        assertEquals(-2.0, e.eval(), 0.0);
        assertEquals(18.0, a.eval(), 0.0);
        assertEquals(0.0, i.eval(), 0.0);
    }
    
    @Test
    public void testSqrtEval() {
        Node e = new SquareRoot(new Literal(9.0));
        Node a = new SquareRoot(new Literal(5.0));
        Node i = new SquareRoot(new Literal(0.0));
        assertEquals(3.0, e.eval(), 0.0);
        assertEquals(2.23606797749979, a.eval(), 0.000000000001);
        assertEquals(0.0, i.eval(), 0.0);
    }
}
