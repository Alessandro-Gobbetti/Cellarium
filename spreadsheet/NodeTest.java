package spreadsheet;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;


/**
 * Tests toString(), isConstant(), and getType() of Node subclasses.
 */
public class NodeTest {

    @Test
    public void testLiteral() {
        Node e = new Literal(5.0);
        assertTrue(e.isConstant());
        assertEquals("5.0", e.toString());
    }

    @Test
    public void testNegation() {
        Node e = new Negation(new Literal(5.0));
        assertTrue(e.isConstant());
        assertEquals("(-5.0)", e.toString());
    }
    
    @Test
    public void testAddition() {
        Node e = new Addition(new Literal(5.0), new Literal(6.0));
        assertTrue(e.isConstant());
        assertEquals("=5.0+6.0", e.toString());
    }

    @Test
    public void testSubtraction() {
        Node e = new Subtraction(new Literal(5.0), new Literal(6.0));
        assertTrue(e.isConstant());
        assertEquals("=5.0-6.0", e.toString());
    }
    
    @Test
    public void testMultiplication() {
        Node e = new Multiplication(new Literal(5.0), new Literal(6.0));
        assertTrue(e.isConstant());
        assertEquals("=5.0*6.0", e.toString());
    }
    
    @Test
    public void testDivision() {
        Node e = new Division(new Literal(5.0), new Literal(6.0));
        assertTrue(e.isConstant());
        assertEquals("=5.0/6.0", e.toString());
    }
    
    @Test
    public void testSin() {
        Node e = new Sine(new Literal(90.0));
        assertTrue(e.isConstant());
        assertEquals("sin(90.0)", e.toString());
    }
    
    @Test 
    public void testAdditionEval() {
        Spreadsheet s = new Spreadsheet();
        Node e = new Addition(new Literal(5.0), new Literal(6.0));
        Node a = new Addition(new Literal(3.0), new Literal(3.0));
        assertEquals(11.0, e.eval(s).asNumber(), 0.0);
        assertEquals(6.0, a.eval(s).asNumber(), 0.0);
    }
    
    @Test 
    public void testSubtractionEval() {
        Spreadsheet s = new Spreadsheet();
        Node e = new Subtraction(new Literal(10.0), new Literal(6.0));
        Node a = new Subtraction(new Literal(3.0), new Literal(4.0));
        assertEquals(4.0, e.eval(s).asNumber(), 0.0);
        assertEquals(-1.0, a.eval(s).asNumber(), 0.0);
    }
    
    @Test 
    public void testMultiplicationEval() {
        Spreadsheet s = new Spreadsheet();
        Node e = new Multiplication(new Literal(5.0), new Literal(6.0));
        Node a = new Multiplication(new Literal(3.0), new Literal(0.5));
        assertEquals(30.0, e.eval(s).asNumber(), 0.0);
        assertEquals(1.5, a.eval(s).asNumber(), 0.0);
    }
    
    @Test 
    public void testDivisionEval() {
        Spreadsheet s = new Spreadsheet();
        Node e = new Division(new Literal(6.0), new Literal(3.0));
        Node a = new Division(new Literal(22.5), new Literal(3.0));
        assertEquals(2.0, e.eval(s).asNumber(), 0.0);
        assertEquals(7.5, a.eval(s).asNumber(), 0.0);
    }
    
    @Test 
    public void testLiteralEval() {
        Spreadsheet s = new Spreadsheet();
        Node e = new Literal(5.0);
        Node a = new Literal(3.0);
        assertEquals(5.0, e.eval(s).asNumber(), 0.0);
        assertEquals(3.0, a.eval(s).asNumber(), 0.0);
    }
    
    @Test
    public void testSinEval() {
        Spreadsheet s = new Spreadsheet();
        Node e = new Sine(new Literal(90.0));
        Node a = new Sine(new Literal(180.0));
        Node i = new Sine(new Literal(0.0));
        Node j = new Sine(new Negation(new Literal(180.0)));
        assertEquals(0.893996663600558, e.eval(s).asNumber(), 0.0000000001);
        assertEquals(-0.80115263573383, a.eval(s).asNumber(), 0.0000000001);
        assertEquals(0.0, i.eval(s).asNumber(), 0.0);
        assertEquals(0.80115263573383, j.eval(s).asNumber(), 0.0000000001);
    }
    
    @Test
    public void testCosEval() {
        Spreadsheet s = new Spreadsheet();
        Node e = new Cosine(new Literal(90.0));
        Node a = new Cosine(new Literal(180.0));
        Node i = new Cosine(new Literal(0.0));
        Node j = new Cosine(new Negation(new Literal(180.0)));
        assertEquals(-0.44807361612917, e.eval(s).asNumber(), 0.0000000001);
        assertEquals(-0.598460069057858, a.eval(s).asNumber(), 0.0000000001);
        assertEquals(1.0, i.eval(s).asNumber(), 0.0);
        assertEquals(-0.598460069057858, j.eval(s).asNumber(), 0.0000000001);
    }
    
    @Test
    public void testNegationEval() {
        Spreadsheet s = new Spreadsheet();
        Node e = new Negation(new Literal(2.0));
        Node a = new Negation(new Literal(-18.0));
        Node i = new Negation(new Literal(0.0));
        assertEquals(-2.0, e.eval(s).asNumber(), 0.0);
        assertEquals(18.0, a.eval(s).asNumber(), 0.0);
        assertEquals(0.0, i.eval(s).asNumber(), 0.0);
    }
    
    @Test
    public void testSqrtEval() {
        Spreadsheet s = new Spreadsheet();
        Node e = new SquareRoot(new Literal(9.0));
        Node a = new SquareRoot(new Literal(5.0));
        Node i = new SquareRoot(new Literal(0.0));
        assertEquals(3.0, e.eval(s).asNumber(), 0.0);
        assertEquals(2.23606797749979, a.eval(s).asNumber(), 0.000000000001);
        assertEquals(0.0, i.eval(s).asNumber(), 0.0);
    }
    
    @Test
    public void testCellReference() {
        Spreadsheet s = new Spreadsheet();
        CellReference c = new CellReference(true, 1, false, 2);
        assertFalse(c.isConstant());
        assertEquals(0.0, c.eval(s).asNumber(), 0.0);
        assertEquals("C$2", c.toString());
        assertEquals(1, c.getRow(1), 0.0);
        assertEquals(3, c.getCol(1), 0.0);
    }
    
    @Test
    public void testAverage() {
        Spreadsheet s = new Spreadsheet();
        Parser p = new CellariumParser();
        Cell cA1 =  s.getOrCreate(0,0);
        Cell cA2 =  s.getOrCreate(1,0);
        Cell cA3 =  s.getOrCreate(2,0);
        
        cA1.setFormula(p.parse("10"));
        cA2.setFormula(p.parse("= A1 + 5.0"));
        cA3.setFormula(p.parse("= A1 * A2"));

        Node e = new Average(new CellReferenceRange(new CellReference(false, 0, false, 0),
                                                    new CellReference(false, 2, false, 0)));
        assertEquals(58.333333333, e.eval(s).asNumber(), 0.0000001);
        assertEquals("AVERAGE(A1:A3)", e.toString());
    }
    
    @Test
    public void testCount() {
        Spreadsheet s = new Spreadsheet();
        Parser p = new CellariumParser();
        Cell cA1 =  s.getOrCreate(0,0);
        Cell cA2 =  s.getOrCreate(1,0);
        Cell cA3 =  s.getOrCreate(2,0);
        Cell cA4 =  s.getOrCreate(3,0);
        
        cA1.setFormula(p.parse("3"));
        cA2.setFormula(p.parse("= A1 + 5.0"));
        cA3.setFormula(p.parse("= A1 * A2"));

        Node e = new Count(new CellReferenceRange(new CellReference(false, 0, false, 0),
                                                    new CellReference(false, 3, false, 0)));
        assertEquals(3, e.eval(s).asNumber(), 0.0000001);
        assertEquals("COUNT(A1:A4)", e.toString());
        
        cA4.setFormula(p.parse("Hello!"));
        
        assertEquals(4, e.eval(s).asNumber(), 0.0000001);
    }
    
    @Test
    public void testSum() {
        Spreadsheet s = new Spreadsheet();
        Parser p = new CellariumParser();
        Cell cA1 =  s.getOrCreate(0,0);
        Cell cA2 =  s.getOrCreate(1,0);
        Cell cA3 =  s.getOrCreate(2,0);
        
        cA1.setFormula(p.parse("10"));
        cA2.setFormula(p.parse("= A1 + 5.0"));
        cA3.setFormula(p.parse("= A1 * A2"));

        Node e = new Sum(new CellReferenceRange(new CellReference(false, 0, false, 0),
                                                new CellReference(false, 2, false, 0)));
        assertEquals(175, e.eval(s).asNumber(), 0.0000001);
        assertEquals("SUM(A1:A3)", e.toString());
    }
    
    @Test
    public void testMaximum() {
        Spreadsheet s = new Spreadsheet();
        Parser p = new CellariumParser();
        Cell cA1 =  s.getOrCreate(0,0);
        Cell cA2 =  s.getOrCreate(1,0);
        Cell cA3 =  s.getOrCreate(2,0);
        
        cA1.setFormula(p.parse("-1"));
        cA2.setFormula(p.parse("= A1 + 5.0"));
        cA3.setFormula(p.parse("= A1 * A2"));

        Node e = new Maximum(new CellReferenceRange(new CellReference(false, 0, false, 0),
                                                    new CellReference(false, 2, false, 0)));
        assertEquals(4, e.eval(s).asNumber(), 0.0000001);
        assertEquals("MAX(A1:A3)", e.toString());
    }
    
    @Test
    public void testMinimum() {
        Spreadsheet s = new Spreadsheet();
        Parser p = new CellariumParser();
        Cell cA1 =  s.getOrCreate(0,0);
        Cell cA2 =  s.getOrCreate(1,0);
        Cell cA3 =  s.getOrCreate(2,0);
        
        cA1.setFormula(p.parse("-2"));
        cA2.setFormula(p.parse("= A1 - 5.0"));
        cA3.setFormula(p.parse("= A1 * A2"));

        Node e = new Minimum(new CellReferenceRange(new CellReference(false, 0, false, 0),
                                                    new CellReference(false, 2, false, 0)));
        assertEquals(-7, e.eval(s).asNumber(), 0.0000001);
        assertEquals("MIN(A1:A3)", e.toString());
    }
}
