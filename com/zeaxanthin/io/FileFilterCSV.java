/**
 * Write a description of class FileFilterCSV here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
package com.zeaxanthin.io;


import java.io.File;
import javax.swing.filechooser.FileFilter;


public class FileFilterCSV extends FileFilter
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
     * Constructor for objects of class FileFilterCSV
     */
    public FileFilterCSV() {
        super();
    }

    
    
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
        return "Comma Separated Value files (*." + CSV_FILE_EXT + ")";
    }
}
