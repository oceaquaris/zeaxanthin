/**
 * FileTypeCSVHW
 */

 
package com.zeaxanthin.io;

/*
 * Standard Java Libraries
 */
import java.io.File;

/*
 * Zeaxanthin Libraries
 */
import com.zeaxanthin.io.FileTypeCSV;


public class FileTypeCSVHW extends FileTypeCSV
{
    /**
     * The file extension for which this FileFilter responds to.
     * The file extension for which this object can read/write.
     */
    public static final String CSVHW_FILE_EXT = "csvhw";
    
    
    
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
    @Override
    public boolean accept(File pathname) {
        if(pathname.isDirectory()) {
            return true;
        }
        
        String extension = ZeaFileIO.getExtension(pathname);
        if(extension != null && extension.equalsIgnoreCase(CSVHW_FILE_EXT)) {
            return true;
        }
        
        return false;
    }
    
    
    
    /**
     * Implementation of the javax.swing.filechooser.FileFilter abstract class.
     *
     * The description of this filter.
     */
    @Override
    public String getDescription() {
        return "CSV Hardy-Weinburg files (*."+CSVHW_FILE_EXT+")";
    }
    
    
    
    /**
     * Process the proposed filename that the user inputs.
     * If the filename does not have .csvhw at the end, append
     * .csvhw to the filename.
     */
    @Override
    public File processFilename(File filename) {
        if(filename == null)
            return null;
        
        if(ZeaFileIO.getExtension(filename).equalsIgnoreCase(CSVHW_FILE_EXT) ) {
            return filename;
        }
        else{
            return new File( filename.toString() + "." + CSVHW_FILE_EXT );
        }
    }
}
