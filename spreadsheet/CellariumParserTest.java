package spreadsheet;

import lexer.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * This test class will test some aspects of the rules
 * of the Cellarium language.
 * 
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 * 
 * <code>
 * CELL         ::= Literal | 
 *                  Text |
 *                  [ "=" ] EXPRESSION
 * EXPRESSION   ::= [ "+" | "-" ] TERM { ( "+" | "-" ) TERM }
 * TERM         ::= FACTOR { ( "*" | "/" ) FACTOR }
 * FACTOR       ::= Literal |
 *                  Cell reference |
 *                  "(" EXPRESSION ")"
 * </code>
 */
public class CellariumParserTest {

    @Test
    public void testLiteral() {
        // setup
        final Spreadsheet s = new Spreadsheet();
        final Parser parser = new CellariumParser(s);
        // test input
        final String sourceCode = "= 12";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Assign(new Literal(12));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }
    
    @Test
    public void testText() {
        // setup
        final Spreadsheet s = new Spreadsheet();
        final Parser parser = new CellariumParser(s);
        // test input
        final String sourceCode = "Hello World!";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Text(sourceCode);
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testNegation() {
        // setup
        final Spreadsheet s = new Spreadsheet();
        final Parser parser = new CellariumParser(s);
        // test input
        String sourceCode = "=-11";
        // code under test
        Node actualRoot = parser.parse(sourceCode);
        // expected tree
        Node expectedRoot = new Assign(new Negation(new Literal(11)));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
        // test input
        sourceCode = "+-1";
        // code under test
        actualRoot = parser.parse(sourceCode);
        // expected tree
        expectedRoot = new Assign(new Negation(new Literal(1)));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testUnaryPlus() {
        // setup
        final Spreadsheet s = new Spreadsheet();
        final Parser parser = new CellariumParser(s);
        // test input
        final String sourceCode = "=+11";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Assign(new Literal(11));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testAddition() {
        // setup
        final Spreadsheet s = new Spreadsheet();
        final Parser parser = new CellariumParser(s);
        // test input
        final String sourceCode = "=12+2";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Assign(new Addition(new Literal(12), new Literal(2)));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testSubtraction() {
        // setup
        final Spreadsheet s = new Spreadsheet();
        final Parser parser = new CellariumParser(s);
        // test input
        final String sourceCode = "=12-2";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Assign(new Subtraction(new Literal(12), new Literal(2)));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testMultiplication() {
        // setup
        final Spreadsheet s = new Spreadsheet();
        final Parser parser = new CellariumParser(s);
        // test input
        final String sourceCode = "=12*2";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Assign(new Multiplication(new Literal(12), new Literal(2)));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testDivision() {
        // setup
        final Spreadsheet s = new Spreadsheet();
        final Parser parser = new CellariumParser(s);
        // test input
        final String sourceCode = "= 12/2";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Assign(new Division(new Literal(12), new Literal(2)));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testParentheses() {
        // setup
        final Spreadsheet s = new Spreadsheet();
        final Parser parser = new CellariumParser(s);
        // test input
        final String sourceCode = "= (12)";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Assign(new Literal(12));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }
    
    @Test
    public void testErrors() {
        // setup
        final Spreadsheet s = new Spreadsheet();
        final Parser parser = new CellariumParser(s);
        // assertions
        assertTrue(parser.parse("= +-1").isError());
        assertTrue(parser.parse("= 1-").isError());
        assertTrue(parser.parse("=*1").isError());
        assertTrue(parser.parse("=  1*").isError());
        assertTrue(parser.parse("=1 *(1 +1").isError());
        assertTrue(parser.parse("=1+2)*2 ").isError());
        assertTrue(parser.parse("=1/*2  ").isError());
        assertTrue(parser.parse("= 1 + ( * 2 ) ").isError());
    }
    
    @Test
    public void testCellReference() {
        // setup
        final Spreadsheet s = new Spreadsheet();
        final Parser parser = new CellariumParser(s);
        // test input
        String sourceCode = "= $A1";
        // code under test
        Node actualRoot = parser.parse(sourceCode);
        // expected tree
        Node expectedRoot = new Assign(new CellReference(s, false, 0, true, 0));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
        // test input
        sourceCode = "= $AB$123";
        // code under test
        actualRoot = parser.parse(sourceCode);
        // expected tree
        expectedRoot = new Assign(new CellReference(s, true, 122, true, 27));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }
    
    @Test 
    public void testCellariumParser1() {
        // setup
        final Spreadsheet s = new Spreadsheet();
        final Parser parser = new CellariumParser(s);
        
        // test input
        final String sourceCode = "2 2";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Text(sourceCode);
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }
    
    @Test 
    public void testCellariumParser2() {
        // setup
        final Spreadsheet s = new Spreadsheet();
        final Parser parser = new CellariumParser(s);
        
        // test input
        final String sourceCode = "$A1";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Text(sourceCode);
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }
    
    /**
    @Test 
    public void testCellariumParser3() {
        // setup
        final Spreadsheet s = new Spreadsheet();
        final Parser parser = new CellariumParser(s);
        
        // test input
        final String sourceCode = "= $A1 $A2";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Text(sourceCode);
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }
    */
}
