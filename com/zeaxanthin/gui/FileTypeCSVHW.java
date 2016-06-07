package com.zeaxanthin.gui;

import java.io.File;

/**
 * Write a description of class FileTypeCSVHW here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FileTypeCSVHW extends FileTypeCSV
{
    public static final String CSVHW_FILE_EXT = "csvhw";
    
    
    
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
