package parser;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * This test class will test some aspects of the rules
 * of the Arith language.
 * 
 * <code>
 * EXPRESSION   ::= [ "+" | "-" ] TERM { ( "+" | "-" ) TERM }
 * TERM         ::= FACTOR { ( "*" | "/" ) FACTOR }
 * FACTOR       ::= Literal | 
 *                  Identifier| 
 *                  "(" EXPRESSION ")"
 * </code>
 */
public class ArithParserTest {

    @Test
    public void testLiteral() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "12";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Literal(12);
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testVariable() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "x";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Variable("x");
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testNegation() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "-11";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Negation(new Literal(11));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testUnaryPlus() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "+11";
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
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "12+2";
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
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "12-2";
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
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "12*2";
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
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "12/2";
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
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "(12)";
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
        final Parser parser = new ArithParser();
        // assertions
        assertEquals(null, parser.parse("+-1"));
        assertEquals(null, parser.parse("1-"));
        assertEquals(null, parser.parse("*1"));
        assertEquals(null, parser.parse("1*"));
        assertEquals(null, parser.parse("1*(1+1"));
        assertEquals(null, parser.parse("1+2)*2"));
        assertEquals(null, parser.parse("1/*2"));
    }
}