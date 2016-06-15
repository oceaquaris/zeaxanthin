/**
 * ZeaFileIO
 */

package com.zeaxanthin.io;

/*
 * Standard Java Libraries
 */
import java.io.File;
import javax.swing.JTable;

/*
 * Zeaxanthin Libraries
 */
import com.zeaxanthin.gui.ZeaTable;
import com.zeaxanthin.gui.ZeaSimulationPane;
 
/**
 * Interface to deal with extension names in custom made FileFilters
 */
public interface ZeaFileIO
{
    /**
     * Retreive the extension of a file (in lower case text). Extension is defined
     * as any text after the last '.' in a filename.
     * If there is no extension given, this function will return the last
     */
    public static String getExtension(File pathname) {
        //retreive the name of the file (no pathname)
        String name = pathname.getName();
        
        //retreive the last index of '.'
        int index = name.lastIndexOf('.');
        
        
        
        //if index > 0, there is a '.' in the String
        if(index > 0) {
        
            //if index > one less than the last index, there is a '.' at the end of the file.
            if(index > name.length()-1) {
            
                //return an empty string; there is no file extension.
                return "";
            }
            
            //return the file extension in toLowerCase
            return name.substring(index+1).toLowerCase();
        }
        
        //there is no extension, so return the filename.
        return name;
    }
    
    
    
    /*
     **********************************************************************************************
     **********************************************************************************************
     **********************************************************************************************
     */
    
    
    
    /**
     * Process the proposed filename that the user inputs.
     * If the filename does not have .<ext> at the end, append
     * .<ext> to the filename.
     */
    public abstract File processFilename(File filename);
    
    
    
    /**
     * Read the extension and return a ZeaTable to be displayed.
     */
    public abstract ZeaTable read(File filename);
    
    
    
    /**
     * Read a file and return a loaded ZeaSimulationPane.
     */
    public abstract ZeaSimulationPane<?> readZeaSimulationPane(File filename);
    
    
    
    /**
     * Write the ZeaTable to a File
     */
    public abstract void write(ZeaTable obj, File filename);
    
    
    
    /**
     * Write a ZeaSimulationPane to File
     */
    public abstract void writeZeaSimulationPane(ZeaSimulationPane<?> obj, File filename);
}
