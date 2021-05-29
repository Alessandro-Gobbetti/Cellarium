package commands;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CommandProcessorTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CommandProcessorTest {

    @Test
    public void executeTest() {
        final CommandProcessor commandprocessor = new CommandProcessor();
        String[] s =  new String[]{"Hello"};
        final CommandPippoAppend pippoAppend = new CommandPippoAppend(s);
        commandprocessor.doCommand(pippoAppend);
        assertEquals("Hellopippo", s[0]);
        commandprocessor.undoLastCommand();
        assertEquals("Hello", s[0]);
        commandprocessor.undoLastCommand();
        assertEquals("Hello", s[0]);
        commandprocessor.redoLastCommand();
        assertEquals("Hellopippo", s[0]);
        commandprocessor.doCommand(pippoAppend);
        commandprocessor.doCommand(pippoAppend);
        assertEquals("Hellopippopippopippo", s[0]);
        commandprocessor.redoLastCommand();
        assertEquals("Hellopippopippopippo", s[0]);
        commandprocessor.undoLastCommand();
        assertEquals("Hellopippopippo", s[0]);
    }
}
