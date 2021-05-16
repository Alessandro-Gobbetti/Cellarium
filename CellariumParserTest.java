import parser.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * This test class will test some aspects of the rules
 * of the Cellarium language.
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
        final Parser parser = new CellariumParser();
        // test input
        final String sourceCode = "= 12";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Literal(12);
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }
    
    @Test
    public void testText() {
        // setup
        final Parser parser = new CellariumParser();
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
        final Parser parser = new CellariumParser();
        // test input
        String sourceCode = "=-11";
        // code under test
        Node actualRoot = parser.parse(sourceCode);
        // expected tree
        Node expectedRoot = new Negation(new Literal(11));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
        // test input
        sourceCode = "+-1";
        // code under test
        actualRoot = parser.parse(sourceCode);
        // expected tree
        expectedRoot = new Negation(new Literal(1));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testUnaryPlus() {
        // setup
        final Parser parser = new CellariumParser();
        // test input
        final String sourceCode = "=+11";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Literal(11);
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testAddition() {
        // setup
        final Parser parser = new CellariumParser();
        // test input
        final String sourceCode = "=12+2";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Addition(new Literal(12), new Literal(2));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testSubtraction() {
        // setup
        final Parser parser = new CellariumParser();
        // test input
        final String sourceCode = "=12-2";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Subtraction(new Literal(12), new Literal(2));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testMultiplication() {
        // setup
        final Parser parser = new CellariumParser();
        // test input
        final String sourceCode = "=12*2";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Multiplication(new Literal(12), new Literal(2));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testDivision() {
        // setup
        final Parser parser = new CellariumParser();
        // test input
        final String sourceCode = "= 12/2";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Division(new Literal(12), new Literal(2));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testParentheses() {
        // setup
        final Parser parser = new CellariumParser();
        // test input
        final String sourceCode = "= (12)";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Literal(12);
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }
    
    @Test
    public void testErrors() {
        // setup
        final Parser parser = new CellariumParser();
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
        final Parser parser = new CellariumParser();
        // test input
        String sourceCode = "= $A1";
        // code under test
        Node actualRoot = parser.parse(sourceCode);
        // expected tree
        Node expectedRoot = new CellReference(false, 0, true, 0);
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
        // test input
        sourceCode = "= $AB$123";
        // code under test
        actualRoot = parser.parse(sourceCode);
        // expected tree
        expectedRoot = new CellReference(true, 122, true, 27);
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }
}