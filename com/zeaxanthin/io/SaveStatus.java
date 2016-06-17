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
     * 
     * This DOES NOT notify the 'statusParent' of changes to this variable.
     */
    public abstract void setSaveStatus(boolean isSaved);
    
    
    
    /**
     * Bind a SaveStatusListener to the SaveStatus object.
     */
    public abstract void setSaveStatusListener(SaveStatusListener statusParent);
    
    
    
    /**
     * Set the saveStatus of the SaveStatus object AND notify the SaveStatusListener
     * ONLY IF 'isSaved' and 'saveStatus' are different.
     */
    public abstract void setSaveStatusNotifySaveStatusListener(boolean isSaved);
}
