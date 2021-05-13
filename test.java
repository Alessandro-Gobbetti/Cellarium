import java.util.HashMap;

/**
 * Write a description of class test here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class test {
    // instance variables - replace the example below with your own

    private HashMap<Integer,Integer> cellMap;

    /**
     * Constructor for objects of class test
     */
    public test()
    {
        // initialise instance variables
        cellMap = new HashMap<Integer,Integer>();
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void remove(int y)
    {
        cellMap.remove(y);
    }
    
    public void add(int y, int x)
    {
        cellMap.put(y, x);
    }
}
