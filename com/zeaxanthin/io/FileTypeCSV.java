/**
 * FileTypeCSV
 */

package com.zeaxanthin.io;

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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableColumn;



/*
 * opencsv parser libraries
 */
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;



/*
 * Zeaxanthin Libraries
 */
import com.zeaxanthin.gui.ZeaTable;
import com.zeaxanthin.io.ZeaFileIO;



public class FileTypeCSV extends FileFilter
                         implements ZeaFileIO
{
    /**
     * The file extension for which this FileFilter responds to.
     * The file extension for which this object can read/write.
     */
    public final static String CSV_FILE_EXT = "csv";
    
    
    
    /*
     **********************************************************************************************
     **********************************************************************************************
     **********************************************************************************************
     */
    
    /**
     * Implementation of the javax.swing.filechooser.FileFilter abstract class.
     *
     * Whether the given file is accepted by this filter.
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
    
    
    
    /**
     * Implementation of the javax.swing.filechooser.FileFilter abstract class.
     *
     * The description of this filter.
     */
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
     * Read the extension and return an ZeaTable to be displayed.
     */
    public ZeaTable read(File filename) {
        CSVReader            reader = null;
        Vector<String[]>    csvlist = null;
        String[]             header = null,
                            classes = null;
        Object[][]             data = null;
        
        
        
        /*
         * Begin the reading process. Attempt to create a CSVReader to read the file.
         */
        try {
            // Attempt to create a CSVReader
            reader = new CSVReader( new FileReader(filename) );
            
            
            /*
             * Attempt to read all lines in the *.csv file.
             */
            try {
                /* Retrieve all lines in the file and stash them into a Vector of
                 * String arrays. */
                csvlist = new Vector<String[]>( reader.readAll() );
                
                
                // If the Vector is null something went terribly wrong
                if(csvlist == null) {
                    JOptionPane.showMessageDialog(null,
                                                  "Zeaxanthin was unable to open the file: " + filename.getName() +
                                                  "\nPlease contact your friendly developer.",
                                                  "Unknown error",
                                                  JOptionPane.ERROR_MESSAGE);
                    return new ZeaTable();
                }
                
                
                /* If the size of the Vector is less than 3, there are less than
                 * 3 rows in the data file. Data has probably been lost or is non-existent. */
                if(csvlist.size() < 3) {
                    JOptionPane.showMessageDialog(null,
                                                  "Zeaxanthin was unable to open the file: " + filename.getName() +
                                                  "\nThe size of this file has less than 3 lines of parsable data." +
                                                  "\nZeaxanthin needs 3 or more parsable lines to operate correctly." +
                                                  "\nIf you are receiving this error message, your data file may " +
                                                  "\nhave been corrupted. If you modified this file using a text editor," +
                                                  "\nyou may have modified the file incorrectly.",
                                                  "Possible File Corruption",
                                                  JOptionPane.ERROR_MESSAGE);
                    return new ZeaTable();
                }
                
                
                /* Read the first element of the Vector<String[]>, which needs
                 * to be the column header titles. */
                header = csvlist.get(0);
                
                
                // Remove the first element from the Vector<String[]>
                csvlist.remove(0);
                
                
                /* Read the (formerly second) first element of the Vector<String[]>,
                 * which needs to be the column header titles. */
                classes = csvlist.get(0);
                
                
                // Remove the (formerly second) first element from the Vector<String[]>.
                csvlist.remove(0);
                
                
                /* Convert the remaining elements in the Vector<String[]> to a
                 * String[][]. */
                data = csvlist.toArray(new String[0][]);
            }
            
            
            /*
             * Catch any exceptions thrown when attempting to read the *.csv file.
             */
            catch (Exception ex) {
                System.out.println("Error in CSVReader: could not readAll()\n" +
                                   "    " + ex.getClass().getSimpleName() + "\n" +
                                   "    " + ex.getMessage() + "\n" +
                                   "    " + ex.getStackTrace() + "\n" +
                                   "A helpful printout of the variables used in\ncom.zeaxanthin.gui.FileTypeCSV.read(File) :");
                System.out.printf ("    CSVReader            reader = %d\n" +
                                   "    Vector<String[]>    csvlist = %d\n" +
                                   "    String[]             header = %d\n" +
                                   "    String[]            classes = %d\n" +
                                   "    Object[][]             data = %d\n",
                                   System.identityHashCode(reader),
                                   System.identityHashCode(csvlist),
                                   System.identityHashCode(header),
                                   System.identityHashCode(classes),
                                   System.identityHashCode(data) );
            }
        }
        
        
        /*
         * Catch any exceptions thrown when attempting to create the CSVReader.
         */
        catch (Exception ex) {
            System.out.println("Error in CSVReader: could not construct CSVReader object\n" +
                               "    " + ex.getClass().getSimpleName() + "\n" +
                               "    " + ex.getMessage() + "\n" +
                               "    " + ex.getStackTrace() + "\n" +
                               "A helpful printout of the variables used in\ncom.zeaxanthin.gui.FileTypeCSV.read(File) :");
            System.out.printf ("    CSVReader            reader = %d\n" +
                               "    Vector<String[]>    csvlist = %d\n" +
                               "    String[]             header = %d\n" +
                               "    String[]            classes = %d\n" +
                               "    Object[][]             data = %d\n",
                               System.identityHashCode(reader),
                               System.identityHashCode(csvlist),
                               System.identityHashCode(header),
                               System.identityHashCode(classes),
                               System.identityHashCode(data) );
            return new ZeaTable();
        }
        
        
        /*
         * Make sure to ***ALWAYS*** close the CSVReader when we're done using it.
         */
        finally {
        
            /*
             * Attempt to close the reader.
             */
            try{
                reader.close();
            }
            
            
            /*
             * Catch any exceptions when trying to close the reader.
             */
            catch (Exception ex) {
                System.out.println("Error in CSVReader: could not close()\n" +
                                   "    " + ex.getClass().getSimpleName() + "\n" +
                                   "    " + ex.getMessage() + "\n" +
                                   "    " + ex.getStackTrace() + "\n" +
                                   "A helpful printout of the variables used in\ncom.zeaxanthin.gui.FileTypeCSV.read(File) :");
                System.out.printf ("    CSVReader            reader = %d\n" +
                                   "    Vector<String[]>    csvlist = %d\n" +
                                   "    String[]             header = %d\n" +
                                   "    String[]            classes = %d\n" +
                                   "    Object[][]             data = %d\n",
                                   System.identityHashCode(reader),
                                   System.identityHashCode(csvlist),
                                   System.identityHashCode(header),
                                   System.identityHashCode(classes),
                                   System.identityHashCode(data) );
            }
        }
        
        /*
         * Return a new ZeaTable with our data inside it.
         */
        return new ZeaTable(data, header, classes);
    }
    
    
    
    /**
     * Write the ZeaTable to a File
     *
     * @note If there is an error in saving this file, data corruption will occur.
     */
    public void write(ZeaTable table, File filename) {
        CSVWriter writer                 = null;
        Vector<String[]> allData         = null;
        Vector<String> headers           = null,
                       classes           = null;
        TableColumnModel model           = null;
        Enumeration<TableColumn> columns = null;
        
        
        try {
            writer = new CSVWriter(
                         new BufferedWriter(
                             new FileWriter(filename)));
            
            //create a Vector to contain all of the data
            allData = new Vector<String[]>();

            //Get an Enumeration of the headers in the table
            model = table.getTableHeader()
                         .getColumnModel();
            columns = model.getColumns();
            
            //Create a Vector and stash String representations
            //of the headers into the Vector.
            headers = new Vector<String>();
            while(columns.hasMoreElements()) {
                headers.add( columns.nextElement().getHeaderValue().toString() );
            }
            
            //Convert headers to String[] and add headers to allData
            allData.add( headers.toArray(new String[0]) );
            
            //get columnClassIdentifiers
            classes = table.getZeaTableModel()
                           .getColumnClassIdentifiers();
            allData.add( classes.toArray(new String[0]) );
                        
            //Extract information from the table body
            int colCount = table.getColumnCount(),
                rowCount = table.getRowCount();
            for(int i = 0; i < rowCount; i++) { //row loop
                Vector<String> rowStrings = new Vector<String>();
                for(int j = 0; j < colCount; j++) { //column loop
                
                    //work around for null pointers
                    Object value = table.getValueAt(i, j);
                    if(value != null) {
                        rowStrings.add( value.toString() );
                    }
                    else {
                        rowStrings.add( ((String)null) );
                    }
                }
                allData.add( rowStrings.toArray(new String[0]) );
            }
            
            writer.writeAll(allData, false);
        }
        catch (Exception ex) {
            System.out.println("Error in CSVWriter: could not write to File\n" +
                               "    " + ex.getClass().getSimpleName() + "\n" +
                               "    " + ex.getMessage() + "\n" +
                               "    " + ex.getStackTrace() + "\n" +
                               "A helpful printout of the variables used in\ncom.zeaxanthin.gui.FileTypeCSV.write(ZeaTable,File) :");
            System.out.printf ("    CSVWriter writer                 = %d\n" +
                               "    Vector<String[]> allData         = %d\n" +
                               "    Vector<String> headers           = %d\n" +
                               "    Vector<String> classes           = %d\n" +
                               "    TableColumnModel model           = %d\n" +
                               "    Enumeration<TableColumn> columns = %d\n",
                               System.identityHashCode(writer),
                               System.identityHashCode(allData),
                               System.identityHashCode(headers),
                               System.identityHashCode(classes),
                               System.identityHashCode(model),
                               System.identityHashCode(columns)
                               );

        }
        finally {
            try {
                writer.close();
            }
            catch (Exception ex) {
                System.out.println("Error in CSVWriter: could not close() CSVWriter\n" +
                                   "    " + ex.getClass().getSimpleName() + "\n" +
                                   "    " + ex.getMessage() + "\n" +
                                   "    " + ex.getStackTrace() + "\n" );
            }
        }
        return;
    }

}
