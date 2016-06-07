package com.zeaxanthin.gui;

import java.io.File;
import javax.swing.JTable;

/**
 * Interface to deal with extension names in custom made FileFilters
 */
public interface ZeaFileIO
{
    public static String getExtension(File pathname)
    {
        String ext = new String("");
        String s = pathname.getName();
        int i = s.lastIndexOf('.');
        
        if (i > 0 && i < s.length() - 1) {
            ext = s.substring( i+1 ).toLowerCase();
        }
        return ext;
    }
    
    
    
    /**
     * Process the proposed filename that the user inputs.
     * If the filename does not have .<ext> at the end, append
     * .<ext> to the filename.
     */
    public abstract File processFilename(File filename);
    
    
    
    /**
     * Read the extension and return an array of objects.
     * Contained within the array (in this order):
     *      JTable
     */
    public abstract ZeaTable read(File filename);
    
    
    
    /**
     * Write the ZeaTable to a File
     */
    public abstract void write(ZeaTable obj, File filename);
}
