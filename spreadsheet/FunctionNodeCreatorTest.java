package spreadsheet;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

/**
 * The test class FunctionNodeCreatorTest.
 * Tests methods of FunctionNodeCreator and his subclasses.
 *
 * @author  Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class FunctionNodeCreatorTest {
    
    @Test
    public void testNullaryFunctionNodeCreator() {
        LiteralNodeCreator creator = new LiteralNodeCreator(10.0);
        Node result = creator.create(new ArrayList<Node>());
        CellValue cellValue = result.eval();
        assertEquals(10.0 ,cellValue.asNumber(), 0.0000001);
        
        Node l1 = new Literal(1.0);
        Node l2 = new Literal(2.0);
        ArrayList list = new ArrayList<Node>();
        list.add(l1);
        list.add(l2);
        Node result1 = creator.create(list);
        assertEquals("Expected no parameter, got 2 parameters.", result1.toString());
    }
    
    @Test
    public void testLiteralNodeCreator() {
        LiteralNodeCreator creator = new LiteralNodeCreator(10.0);
        CellValue cellValue = creator.nullaryCreate().eval();
        assertEquals(10.0 ,cellValue.asNumber(), 0.0000001);
    }
    
    @Test
    public void testUnaryFunctionNodeCreator() {
        SquareRootNodeCreator creator = new SquareRootNodeCreator();
        Node literal = new Literal(9.0);
        CellValue cellValue = creator.unaryCreate(literal).eval();
        assertEquals(3.0 , cellValue.asNumber(), 0.0000001);
        
        Node l1 = new Literal(1.0);
        Node l2 = new Literal(2.0);
        ArrayList list = new ArrayList<Node>();
        list.add(l1);
        list.add(l2);
        Node result1 = creator.create(list);
        assertEquals("Expected one parameter, got 2 parameters.", result1.toString());
    }
    
    @Test
    public void testSquareRootNodeCreator() {
        SquareRootNodeCreator creator = new SquareRootNodeCreator();
        Node literal = new Literal(9.0);
        CellValue cellValue = creator.unaryCreate(literal).eval();
        assertEquals(3.0 , cellValue.asNumber(), 0.0000001);
    }
    
    @Test
    public void testSineNodeCreator() {
        SineNodeCreator creator = new SineNodeCreator();
        Node literal = new Literal(1.0);
        CellValue cellValue = creator.unaryCreate(literal).eval();
        assertEquals(0.841470984807896, cellValue.asNumber(), 0.0000001);
    }
    
    @Test
    public void testCosineNodeCreator() {
        CosineNodeCreator creator = new CosineNodeCreator();
        Node literal = new Literal(1.0);
        CellValue cellValue = creator.unaryCreate(literal).eval();
        assertEquals(0.54030230586814 , cellValue.asNumber(), 0.0000001);
    }
    
    @Test
    public void testTangentNodeCreator() {
        final FunctionNodeCreator creator = new TangentNodeCreator();
        final Node literal = new Literal(0.0);
        final ArrayList<Node> parameters = new ArrayList<Node>(){{add(literal);}};
        CellValue cellValue = creator.create(parameters).eval();
        assertEquals(0.0 , cellValue.asNumber(), 0);
    }
    
    @Test
    public void testLogarithmNodeCreator() {
        final FunctionNodeCreator creator = new LogarithmNodeCreator();
        final Node literal = new Literal(10.0);
        final ArrayList<Node> parameters = new ArrayList<Node>(){{add(literal);}};
        CellValue cellValue = creator.create(parameters).eval();
        assertEquals(2.302585092994046 , cellValue.asNumber(), 00000000000001);
    }

    @Test
    public void testUnaryRangeFunctionNodeCreator() {
        MinimumNodeCreator creator = new MinimumNodeCreator();
        Spreadsheet s = new Spreadsheet();
        Parser p = new CellariumParser(s);
        CellReference referance1 = new CellReference(s, false, 0, false, 0);
        CellReference referance2 = new CellReference(s, false, 1, false, 0);
        CellReferenceRange range = new CellReferenceRange(referance1, referance2);
        
        //With CellReferanceRange as Parameter
        Cell cA1 =  s.getOrCreate(0,0); 
        cA1.setFormula(p.parse("10"));
        Node minimum = creator.unaryCreate(range);
        assertEquals(0.0 ,minimum.eval().asNumber(), 0.0000001);
        
        Cell cA2 =  s.getOrCreate(1,0);
        cA2.setFormula(p.parse("100"));
        assertEquals(10.0 ,minimum.eval().asNumber(), 0.0000001);
        
        //With CellReferance as Parameter
        Node result1 = creator.unaryCreate(referance1);
        assertEquals(10.0, result1.eval().asNumber(), 0.0000001);
        
        //With literal as Prameter
        Node l1 = new Literal(1.0);
        Node result2 = creator.unaryCreate(l1);
        assertEquals("Expected a range as parameter, got 1.0", result2.toString());
    }
    
    @Test
    public void testMinimumNodeCreator() {
        MinimumNodeCreator creator = new MinimumNodeCreator();
        Spreadsheet s = new Spreadsheet();
        Parser p = new CellariumParser(s);
        Cell cA1 =  s.getOrCreate(0,0);
        Cell cA2 =  s.getOrCreate(1,0);
        Cell cA3 =  s.getOrCreate(2,0);
        Cell cA4 =  s.getOrCreate(3,0);
        
        cA1.setFormula(p.parse("10"));
        cA2.setFormula(p.parse("100"));
        cA3.setFormula(p.parse("= A1 - A2"));
        
        CellReferenceRange range = new CellReferenceRange(new CellReference(s, false, 0, false, 0),
                                                        new CellReference(s, false, 3, false, 1));
        
        CellValue cellValue = creator.unaryCreate(range).eval();
        assertEquals(-90 , cellValue.asNumber(), 0.0000001);
    }
    
    @Test
    public void testMaximumNodeCreator() {
        MaximumNodeCreator creator = new MaximumNodeCreator();
        Spreadsheet s = new Spreadsheet();
        Parser p = new CellariumParser(s);
        Cell cA1 =  s.getOrCreate(0,0);
        Cell cA2 =  s.getOrCreate(1,0);
        Cell cA3 =  s.getOrCreate(2,0);
        
        cA1.setFormula(p.parse("-9"));
        cA2.setFormula(p.parse("-76"));
        cA3.setFormula(p.parse("= A1 - A2"));
        
        CellReferenceRange range = new CellReferenceRange(new CellReference(s, false, 0, false, 0),
                                                        new CellReference(s, false, 2, false, 0));
        
        CellValue cellValue = creator.unaryCreate(range).eval();
        assertEquals(67 , cellValue.asNumber(), 0.0000001);
    }
    
    @Test
    public void testSumNodeCreator() {
        SumNodeCreator creator = new SumNodeCreator();
        Spreadsheet s = new Spreadsheet();
        Parser p = new CellariumParser(s);
        Cell cB2 =  s.getOrCreate(1,1);
        Cell cB3 =  s.getOrCreate(2,1);
        Cell cB4 =  s.getOrCreate(3,1);
        
        cB2.setFormula(p.parse("= B3"));
        cB3.setFormula(p.parse("88"));
        cB4.setFormula(p.parse("34"));
        
        CellReferenceRange range = new CellReferenceRange(new CellReference(s, false, 1, false, 1),
                                                        new CellReference(s, false, 3, false, 1));
        
        CellValue cellValue = creator.unaryCreate(range).eval();
        assertEquals(210 , cellValue.asNumber(), 0.0000001);
    }
    
    @Test
    public void testAverageNodeCreator() {
        AverageNodeCreator creator = new AverageNodeCreator();
        Spreadsheet s = new Spreadsheet();
        Parser p = new CellariumParser(s);
        Cell cB2 =  s.getOrCreate(1,1);
        Cell cB3 =  s.getOrCreate(2,1);
        Cell cB4 =  s.getOrCreate(3,1);
        
        cB2.setFormula(p.parse("= B3"));
        cB3.setFormula(p.parse("88"));
        cB4.setFormula(p.parse("34"));
        
        CellReferenceRange range = new CellReferenceRange(new CellReference(s, false, 1, false, 1),
                                                        new CellReference(s, false, 3, false, 1));
        
        CellValue cellValue = creator.unaryCreate(range).eval();
        assertEquals(70 , cellValue.asNumber(), 0.0000001);
    }
    
    @Test
    public void testCountNodeCreator() {
        CountNodeCreator creator = new CountNodeCreator();
        Spreadsheet s = new Spreadsheet();
        Parser p = new CellariumParser(s);
        Cell cB2 =  s.getOrCreate(1,1);
        Cell cB3 =  s.getOrCreate(2,1);
        Cell cB4 =  s.getOrCreate(3,1);
        Cell cB5 =  s.getOrCreate(4,1);

        
        cB2.setFormula(p.parse("OkiDoki"));
        cB3.setFormula(p.parse("2"));
        cB5.setFormula(p.parse("3"));
        
        CellReferenceRange range = new CellReferenceRange(new CellReference(s, false, 1, false, 1),
                                                        new CellReference(s, false, 4, false, 1));
        
        CellValue cellValue = creator.unaryCreate(range).eval();
        assertEquals(3 , cellValue.asNumber(), 0.0000001);
    }
    
    
    
}
