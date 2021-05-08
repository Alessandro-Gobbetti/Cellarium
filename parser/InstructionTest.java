package parser;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests toString() and execute() of Instruction subclasses.
 */
public class InstructionTest {

    @Test
    public void testToStringBIPUSH() {
        Instruction i = new BIPUSH(1);
        assertEquals("BIPUSH 1.0", i.toString());
    }
    
    @Test
    public void testExecuteBIPUSH() {
        OperandStack os = new OperandStack();
        VariableTable vt = new VariableTable();
        Storage s = new Storage(os, vt);
        Instruction i = new BIPUSH(1);
        i.execute(s);
        assertEquals(1, os.pop(), 0.0);
    }
    
    
    @Test
    public void testToStringNEG() {
        Instruction i = new NEG();
        assertEquals("NEG", i.toString());
    }
    
    @Test
    public void testExecuteNEG() {
        OperandStack os = new OperandStack();
        VariableTable vt = new VariableTable();
        Storage s = new Storage(os, vt);
        os.push(1.0);
        Instruction i = new NEG();
        i.execute(s);
        assertEquals(-1.0, os.pop(), 0.0);
    }

    
    @Test
    public void testToStringADD() {
        Instruction i = new ADD();
        assertEquals("ADD", i.toString());
    }
    
    @Test
    public void testExecuteADD() {
        OperandStack os = new OperandStack();
        VariableTable vt = new VariableTable();
        Storage s = new Storage(os, vt);
        os.push(1.0);
        os.push(2.0);
        Instruction i = new ADD();
        i.execute(s);
        assertEquals(3.0, os.pop(), 0.0);
    }

    
    @Test
    public void testToStringSUB() {
        Instruction i = new SUB();
        assertEquals("SUB", i.toString());
    }
    
    @Test
    public void testExecuteSUB() {
        OperandStack os = new OperandStack();
        VariableTable vt = new VariableTable();
        Storage s = new Storage(os, vt);
        os.push(3.0);
        os.push(2.0);
        Instruction i = new SUB();
        i.execute(s);
        assertEquals(1.0, os.pop(), 0.0);
    }

    
    @Test
    public void testToStringLOAD() {
        Instruction i = new LOAD("x");
        assertEquals("LOAD x", i.toString());
    }
    
    @Test
    public void testExecuteLOAD() {
        OperandStack os = new OperandStack();
        VariableTable vt = new VariableTable();
        vt.set("x", 1.0);
        Storage s = new Storage(os, vt);
        Instruction i = new LOAD("x");
        i.execute(s);
        assertEquals(1.0, os.pop(), 0.0);
    }

    
    @Test
    public void testToStringMUL() {
        Instruction i = new MUL();
        assertEquals("MUL", i.toString());
    }
    
    @Test
    public void testExecuteMUL() {
        OperandStack os = new OperandStack();
        VariableTable vt = new VariableTable();
        Storage s = new Storage(os, vt);
        os.push(2.0);
        os.push(3.0);
        Instruction i = new MUL();
        i.execute(s);
        assertEquals(6, os.pop(), 0.0);
    }
    
    
    @Test
    public void testToStringDIV() {
        Instruction i = new DIV();
        assertEquals("DIV", i.toString());
    }
    
    @Test
    public void testExecuteDIV() {
        OperandStack os = new OperandStack();
        VariableTable vt = new VariableTable();
        Storage s = new Storage(os, vt);
        os.push(6.0);
        os.push(3.0);
        Instruction i = new DIV();
        i.execute(s);
        assertEquals(2.0, os.pop(), 0.0);
    }
    
    @Test
    public void testToStringSIN() {
        Instruction i = new SIN();
        assertEquals("SIN", i.toString());
    }
    
    @Test
    public void testExecuteSIN() {
        OperandStack os = new OperandStack();
        VariableTable vt = new VariableTable();
        Storage s = new Storage(os, vt);
        os.push(Math.PI/2);
        Instruction i = new SIN();
        i.execute(s);
        os.push(90.0d);
        i.execute(s);
        assertEquals(0.89399666, os.pop(), 0.001);
        assertEquals(1.0, os.pop(), 0.0);
    }
}

