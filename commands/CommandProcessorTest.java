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
        final CommandPippoAppend pippoAppend = new CommandPippoAppend("Hello");
        commandprocessor.execute(pippoAppend);
        assertEquals("Hellopippo", pippoAppend.getString());
        commandprocessor.unexecute();
        assertEquals("Hello", pippoAppend.getString());
        commandprocessor.unexecute();
        assertEquals("Hello", pippoAppend.getString());
        commandprocessor.reexecute();
        assertEquals("Hellopippo", pippoAppend.getString());
        commandprocessor.execute(pippoAppend);
        commandprocessor.execute(pippoAppend);
        assertEquals("Hellopippopippopippo", pippoAppend.getString());
        commandprocessor.reexecute();
        assertEquals("Hellopippopippopippo", pippoAppend.getString());
        commandprocessor.unexecute();
        assertEquals("Hellopippopippo", pippoAppend.getString());
    }
}
