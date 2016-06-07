package com.zeaxanthin.gui;

/*
 * Standard Java Libraries
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import javax.swing.filechooser.FileFilter;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableColumn;

/*
 * opencsv parser libraries
 */
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class FileTypeCSV extends FileFilter implements ZeaFileIO
{
    public final static String CSV_FILE_EXT = "csv";
    
    
    
    /**
     * Implementation of the FileFilter abstract class.
     */
    public boolean accept(File pathname) {
        if(pathname.isDirectory()) {
            return true;
        }
        
        String extension = ZeaFileIO.getExtension(pathname);
        if(extension != null && extension.equalsIgnoreCase(CSV_FILE_EXT)) {
            return true;
        }
        
        return false;
    }
    
    
    
    public String getDescription() {
        return "Comma Separated Value files (*.csv)";
    }
    
    
    
    /**
     * Process the proposed filename that the user inputs.
     * If the filename does not have .csv at the end, append
     * .csv to the filename.
     */
    public File processFilename(File filename) {
        if(filename == null)
            return null;
        
        if(ZeaFileIO.getExtension(filename).equalsIgnoreCase(CSV_FILE_EXT) ) {
            return filename;
        }
        else{
            return new File( filename.toString() + "." + CSV_FILE_EXT );
        }
    }
    
    
    
    /**
     * Read the extension and return an array of objects.
     * Contained within the array (in this order):
     *      JTable
     */
    public ZeaTable read(File filename) {
        CSVReader            reader = null;
        Vector<String[]>    csvlist = null;
        String[]             header = null,
                            classes = null;
        Object[][]             data = null;
        
        try {
            reader = new CSVReader( new FileReader(filename) );
            try {
                csvlist = new Vector<String[]>( reader.readAll() );
                
                header = csvlist.get(0);
                csvlist.remove(0);
                classes = csvlist.get(0);
                csvlist.remove(0);
                data = csvlist.toArray(new String[0][]);
            }
            catch (Exception ex) {
                System.out.println("Error in CSVReader: could not readAll()");
                System.out.println("    " + ex.getMessage());
                System.out.println("    " + ex.getStackTrace());
            }
        }
        catch (Exception ex) {
            System.out.println("Error in CSVReader: could not construct object");
            System.out.println("    " + ex.getMessage());
            System.out.println("    " + ex.getStackTrace());
            return null;
        }
        finally {
            try{
                reader.close();
            }
            catch (Exception ex) {
                System.out.println("Error in CSVReader: could not close()");
                System.out.println("    " + ex.getMessage());
                System.out.println("    " + ex.getStackTrace());
                System.out.printf("CSVReader reader = %d\n" +
                                  "List<String[]> csvlist = %d\n" +
                                  "String[] header = %d\n" +
                                  "String[] classes = %d\n" +
                                  "Object[][] data = %d\n",
                                  System.identityHashCode(reader),
                                  System.identityHashCode(csvlist),
                                  System.identityHashCode(header),
                                  System.identityHashCode(classes),
                                  System.identityHashCode(data));
            }
            finally {
                return new ZeaTable(data, header, classes);
            }
        }
    }
    
    
    
    /**
     * Write the ZeaTable to a File
     */
    public void write(ZeaTable table, File filename) {
        CSVWriter writer = null;
        try {
            writer = new CSVWriter(
                         new BufferedWriter(
                             new FileWriter(filename)));
            
            //create a Vector to contain all of the data
            Vector<String[]> allData = new Vector<String[]>();

            //Get an Enumeration of the headers in the table
            TableColumnModel model = table.getTableHeader()
                                          .getColumnModel();
            Enumeration<TableColumn> columns = model.getColumns();
            
            //Create a Vector and stash String representations
            //of the headers into the Vector.
            Vector<String> headers = new Vector<String>();
            while(columns.hasMoreElements()) {
                headers.add( columns.nextElement().getHeaderValue().toString() );
            }
            
            //Convert headers to String[] and add headers to allData
            allData.add( headers.toArray(new String[0]) );
            
            //get columnClassIdentifiers
            Vector<String> classes = table.getZeaTableModel()
                                          .getColumnClassIdentifiers();
            allData.add( classes.toArray(new String[0]) );
                        
            //Extract information from the table body
            int colCount = table.getColumnCount(),
                rowCount = table.getRowCount();
            for(int i = 0; i < rowCount; i++) { //row loop
                Vector<String> rowStrings = new Vector<String>();
                for(int j = 0; j < colCount; j++) { //column loop
                    rowStrings.add( table.getValueAt(i, j).toString() );
                }
                allData.add( rowStrings.toArray(new String[0]) );
            }
            
            writer.writeAll(allData, false);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace());
        }
        finally {
            try {
                writer.close();
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println(ex.getStackTrace());
            }
        }
        return;
    }

}