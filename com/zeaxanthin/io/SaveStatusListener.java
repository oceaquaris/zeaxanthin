/**
 * Write a description of interface SaveStatusListener here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
package com.zeaxanthin.io;

/*
 * Standard Java Libraries
 */
import java.util.Vector;

/*
 * Zeaxanthin Libraries
 */
import com.zeaxanthin.io.SaveStatus;


public interface SaveStatusListener
{
//     /**
//      * All SaveStatus children this object responds to.
//      */
//     protected Vector<SaveStatus> saveStatusChildren = null;

    /**
     * Add a child SaveStatus object.
     */
    public abstract boolean addSaveStatusChild(final SaveStatus statusChild);
    
    
    
    /**
     * Get a Vector of SaveStatus children.
     */
    public abstract Vector<SaveStatus> getSaveStatusChildren();
    
    
    
    /**
     * Update the SaveStatusListener when a child has been modified.
     * This function should be called by the child.
     */
    public abstract void childModified(final Object source, boolean isSaved);
    
    
    
    /**
     * Notify the children of the SaveStatusListener that the file has been saved
     * AND set the 'saveStatus' to 'isSaved' ONLY IF 'saveStatus' is different from
     * 'isSaved'.
     */
    public abstract void setSaveStatusNotifySaveStatusChildren(boolean isSaved);
}
