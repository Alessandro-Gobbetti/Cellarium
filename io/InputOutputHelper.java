package io;

import spreadsheet.Cell;
import spreadsheet.CellariumParser;
import spreadsheet.Node;
import spreadsheet.Spreadsheet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;


/**
 * To manage imput/output for Cellarium.
 * Supports .csv and .cellarium files.
 * 
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class InputOutputHelper {
    
    private static final String NO_FILE_FIND = "No such file or directory found at ";
    private static final String INSERT_VALID_PATH = ". Please insert an existing file path!";
    
    /**
     * Constructor for ManageCsv objects.
     */
    private InputOutputHelper() {
    }
    
    /**
     * Generate a csv file to save into the spreadsheet.
     * @param pathFileName the path of the input file.
     * @param spreadsheet the actual spreadsheet.
     */
    public static void generateCsvFile(final String pathFileName, final Spreadsheet spreadsheet) {
        try {
            // removing any file extension and adding .csv 
            final String fileName =
                (pathFileName + ".").substring(0, (pathFileName + ".").indexOf('.')) + ".csv";
            final FileWriter writer = new FileWriter(fileName);
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
        } catch (IOException exeption) {
            // if the given path is wrong.
            System.out.println(
                NO_FILE_FIND 
                + pathFileName 
                + INSERT_VALID_PATH
            );
            //exeptin.printStackTrace();
        }
    }
    
    /**
     * Save the spreadsheet into the Cellarium standart:
     * an index associated to the formula of the cell.
     * 
     * @param pathFileName the path of the input file.
     * @param spreadsheet the actual spreadsheet.
     */
    public static void save(final String pathFileName, final Spreadsheet spreadsheet) {
        try {
            // removing any file extension and adding .csv 
            final String fileName =
                (pathFileName + ".").substring(0, (pathFileName + ".").indexOf('.')) + ".cellarium";
            final FileWriter writer = new FileWriter(fileName);
            final HashMap<Integer,Cell> cellMap = spreadsheet.getCellMap();
            for (final int index : cellMap.keySet()) {
                writer.append(index + " " + cellMap.get(index).getFormula() + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException exeption) {
            // if the given path is wrong.
            System.out.println(
                NO_FILE_FIND 
                + pathFileName 
                + INSERT_VALID_PATH
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
        final CellariumParser parser = new CellariumParser(spreadsheet);
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
        } catch (IOException exeption) {
            // if the file is not found:
            System.out.println(
                NO_FILE_FIND 
                + pathFileName 
                + INSERT_VALID_PATH
            );
            //exeption.printStackTrace();
        }
    }
    
    
    /**
     * To open a csv file in Cellarium.
     * @param pathFileName the path of the input file
     * @param spreadsheet the actual spreadsheet.
     */
    public static void open(final String pathFileName, final Spreadsheet spreadsheet) {
        final CellariumParser parser = new CellariumParser(spreadsheet);
        try {
            // To read a file line by line
            final BufferedReader csvReader = new BufferedReader(new FileReader(pathFileName));
            String line = csvReader.readLine();
            // Clear the current Spreadsheet.
            spreadsheet.clear();
            while (line != null) {
                // split the line in two: the index from the rest.
                final String[] arr = line.split(" ", 2);
                final String stringIndex = arr[0];
                final String formula = arr[1];
                // parse the formula
                final Node cellContent = parser.parse(formula);
                // get the cell coordinates
                final int index = Integer.parseInt(stringIndex);
                final int row = spreadsheet.rowFromIndex(index);
                final int col = spreadsheet.colFromIndex(index);
                // set the cell
                final Cell cell = spreadsheet.getOrCreate(row, col);
                cell.setFormula(cellContent);
                line = csvReader.readLine();
            }
            csvReader.close();
        } catch (IOException exeption) {
            // if the file is not found:
            System.out.println(
                NO_FILE_FIND 
                + pathFileName 
                + INSERT_VALID_PATH
            );
            //exeption.printStackTrace();
        }
    }
}