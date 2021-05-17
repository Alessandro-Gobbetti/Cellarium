import java.util.ArrayList;

/**
 * A Text is an AST node that 
 * corresponds to a text value
 * (a String in the source code).
 */
public class Text extends Node {
    
    private final String text;
    
    
    /**
     * Create a new Text node.
     * @param text the text value this node evaluates to
     */
    public Text(final String text) {
        super();
        this.text = text;
    }

    @Override
    public boolean isConstant() {
        return true;
    }

    @Override
    public String toString() {
        return text;
    }
    
    @Override
    public CellValue eval(final Spreadsheet spreadsheet) {
        return new StringCellValue(text);
    }
    
    @Override
    public void addDependencies(final Spreadsheet spreadsheet, final ArrayList<Cell> list) {
        //FIXME
    }
}
