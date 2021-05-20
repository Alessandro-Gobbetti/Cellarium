import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * To save the spreadsheet into a csv file.
 */
public class ManageCsv {
    
    /**
     * Generate a csv file to save into the spreadsheet.
     * @param pathFileName the path of the input file.
     * @param spreadsheet the actual spreadsheet.
     */
    public static void generateCsvFile(final String pathFileName, final Spreadsheet spreadsheet) {
        try {
            final FileWriter writer = new FileWriter(pathFileName);
            for (int row = 0; row <= spreadsheet.getMaxUsedCellRow(); row++) {
                for (int col = 0; col <= spreadsheet.getMaxUsedCellCol(); col++) {
                    writer.append(
                        spreadsheet.exists(row, col) ? spreadsheet.getFormula(row,col) + "," : ","
                    );
                }
                writer.append('\n');
            } 
            writer.flush();
            writer.close();
            System.out.println("Done!");
        } catch (IOException exeption) {
            // if the given path is wrong.
            System.out.println(
                "No such file or directory found at " 
                + pathFileName 
                + ". Please insert an existing file path!"
            );
            //exeptin.printStackTrace();
        }
    }
   
    /**
     * To open a csv file in Cellarium.
     * @param pathFileName the path of the input file
     * @param spreadsheet the actual spreadsheet.
     */
    public static void openFromCsv(final String pathFileName, final Spreadsheet spreadsheet) {
        final CellariumParser parser = new CellariumParser();
        try {
            // To read a file line by line
            final BufferedReader csvReader = new BufferedReader(new FileReader(pathFileName));
            String line = csvReader.readLine();
            // Clear the current Spreadsheet.
            spreadsheet.clear();
            int currentRow = 0;
            while (line != null) {
                final String[] rowData = line.split(",");
                int currentCol = 0;
                for (final String s : rowData) {
                    if (!(",").equals(s)) {
                        final Node content = parser.parse(s);
                        final Cell c = spreadsheet.getOrCreate(currentRow, currentCol);
                        c.setFormula(content);
                    }
                    // Read next cell.
                    currentCol++;
                }
                // Read next line.
                line = csvReader.readLine();
                currentRow++;
            }
            csvReader.close();
            System.out.println("Done!");
        } catch (IOException exeption) {
            // if the file is not found:
            System.out.println(
                "No such file or directory found at " 
                + pathFileName 
                + ". Please insert an existing file path!"
            );
            //exeption.printStackTrace();
        }
    }
}