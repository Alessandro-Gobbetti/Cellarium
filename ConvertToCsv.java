import java.io.FileWriter;
import java.io.IOException;

public class ConvertToCsv {

   public static void generateCsvFile(String sFileName, Spreadsheet spreadsheet) {
       try {
           FileWriter writer = new FileWriter(sFileName);
           for (int row = 0; row <= spreadsheet.getMaxUsedCellRow(); row++) {
               for (int col = 0; col <= spreadsheet.getMaxUsedCellCol(); col++) {
                   writer.append(spreadsheet.exists(row, col) ? spreadsheet.getFormula(row,col) : ",");
               }
               writer.append('\n');
           }
           writer.flush();
           writer.close();
       } catch(IOException e) {
           e.printStackTrace();
       }
   }
   
   //public static Spreadsheet convertFromCsv()
}