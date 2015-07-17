package com.zeaxanthin.gui;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class FileFilterZXT extends FileFilter
{
    public final static String ZEAXANTHIN_FILE_EXTENSION = "zxt";
    /**
     * Implementation of the FileFilter abstract class.
     */
    public boolean accept(File pathname)
    {
        if(pathname.isDirectory()) {
            return true;
        }
        
        String extension = getExtension(pathname);
        if(extension != null) {
            if(extension.equals(ZEAXANTHIN_FILE_EXTENSION) ) {
                return true;
            }
            else {
                return false;
            }
        }
        
        return false;
    }
    
    public String getDescription()
    {
        return "*.zxt";
    }
    
    /**
     * Helper Method (as well as static utility) to get the extension
     * of a file.
     */
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
}