/**
 * Write a description of interface SaveStatus here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
package com.zeaxanthin.io;

import com.zeaxanthin.io.SaveStatusListener;

public interface SaveStatus
{
//     /**
//      * A parent class that listens for when this object is modified.
//      */
//     protected SaveStatusListener statusParent = null;
//     
//     
//     
//     /**
//      * The save status of this object. 'true' when this object has not been modified.
//      * 'false' when this object has been modified.
//      */
//     protected boolean saveStatus = true;

    /**
     * Return the saveStatus of the SaveStatus object.
     */
    public abstract boolean getSaveStatus();
    
    
    
    /**
     * Set the saveStatus of the SaveStatus object.
     * Changes the saveStatus variable to 'isSaved' only if 'saveStatus' is different
     * from 'isSaved'.
     */
    public abstract void setSaveStatus(boolean isSaved);
    
    
    
    /**
     * Notify the bound SaveStatusListener that the object has been modified.
     * 
     * This does not modify the saveStatus variable or compare if it has changed.
     */
    public abstract void notifySaveStatusListener(boolean isSaved);
    
    
    
    /**
     *
     */
    public abstract void setSaveStatusListener(final SaveStatusListener statusParent);
}
