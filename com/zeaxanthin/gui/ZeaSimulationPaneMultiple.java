/**
 * Write a description of class ZeaSimulationPaneMultiple here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
package com.zeaxanthin.gui;

/*
 * Standard Java Libraries
 */
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Vector;
import javax.swing.JTabbedPane;

/*
 * Zeaxanthin Libraries
 */
import com.zeaxanthin.io.SaveStatus;
import com.zeaxanthin.io.SaveStatusListener;
import com.zeaxanthin.io.ZeaFileIO;



/*
 * This class is not intended to be serialized.
 */
@SuppressWarnings("serial")



public abstract class ZeaSimulationPaneMultiple extends JTabbedPane
                                                implements ZeaSimulationPane<ZeaSimulationPaneMultiple>,
                                                           SaveStatus,
                                                           SaveStatusListener
{
    /**
     * The File that has been loaded.
     */
    protected File loadedFile = null;
    
    
    
    /**
     * The save status of the ZeaSimulationPaneMultiple
     */
    protected boolean loadedFileSaveStatus = true;
    
    
    
    /**
     * The ZeaFileIO object that handles saving.
     */
    protected ZeaFileIO saver = null;
    
    
    
    /**
     * A parent class that listens for when this object is modified.
     */
    protected SaveStatusListener statusParent = null;
    
    
    
    /**
     * The save status of this object. 'true' when this object has not been modified.
     * 'false' when this object has been modified.
     */
    protected boolean saveStatus = true;



    /**
     * All SaveStatus children this object responds to.
     */
    protected Vector<SaveStatus> saveStatusChildren = null;



    /*
     **********************************************************************************************
     **********************************************************************************************
     **********************************************************************************************
     */
    
    
    
    /**
     * Create a ZeaSimulationPaneMultiple from a File.
     */
    
    
    
    /*
     **********************************************************************************************
     **********************************************************************************************
     **********************************************************************************************
     */
    
    
    
    /**
     * Update the SaveStatusListener when a child has been modified.
     * This function should be called by the child.
     */
    public void childModified(final Object source, boolean isSaved) {
        this.setSaveStatusNotifySaveStatusListener(isSaved);
        return;
    }
    
    
    
    /**
     * Get the loaded File represented by this ZeaSimulationPane.
     */
    public File getLoadedFile() {
        return this.loadedFile;
    }
    
    
    
    /**
     * Get the saveStatus of the ZeaSimulationPane.
     */
    public boolean getSaveStatus() {
        return this.loadedFileSaveStatus;
    }
    
    
    
    /**
     * Get the this object. This is a nasty hack to retrieve an object extending
     * a Component, but also implementing this interface. Basically a way to allow
     * for multiple inheritance.
     */
    public ZeaSimulationPaneMultiple getSelf() {
        return this;
    }
    
    
    
    /**
     * Save the ZeaSimulationPane
     */
    public abstract void saveSimulation();
    
    
    
    /**
     * Save the ZeaSimulationPane to a File.
     */
    public abstract void saveSimulation(File filename);
    
    
    
    /**
     * SaveStatus implementation
     *
     * Set the saveStatus of the SaveStatus object.
     * This DOES NOT notify the 'statusParent' of changes to this variable.
     */
    public void setSaveStatus(boolean isSaved) {
        this.loadedFileSaveStatus = isSaved;
        return;
    }
    
    
    
    /**
     * Notify the children of the SaveStatusListener that the file has been saved
     * ONLY IF 'saveStatus' and 'isSaved' are different.
     */
    public void setSaveStatusNotifySaveStatusChildren(boolean isSaved) {
        if(this.loadedFileSaveStatus != isSaved) {
            this.loadedFileSaveStatus = isSaved;
            for(SaveStatus ssobj : this.saveStatusChildren) {
                if(ssobj instanceof SaveStatusListener) {
                    ((SaveStatusListener)ssobj).setSaveStatusNotifySaveStatusChildren(isSaved);
                }
                else if(ssobj instanceof SaveStatus) {
                    ssobj.setSaveStatus(isSaved);
                }
                else {
                    return;
                }
            }
        }
    }
    
    
    
    /**
     * SaveStatus implementation
     *
     * Set the saveStatus of the SaveStatus object AND notify the SaveStatusListener
     * ONLY IF 'isSaved' and 'saveStatus' are different.
     */
    public void setSaveStatusNotifySaveStatusListener(boolean isSaved) {
        if( this.loadedFileSaveStatus != isSaved ) {
            this.loadedFileSaveStatus = isSaved;
            this.statusParent.childModified(this, this.loadedFileSaveStatus);
        }
        return;
    }
    
    
    
    /**
     * In the event that someone tries to serialize this object, throw an exception.
     */
    private void writeObject(ObjectOutputStream oos) throws IOException {
        throw new IOException("The class: " + this.getClass().getSimpleName() +
                              " is NOT serializable.");
    }
}
