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
import javax.swing.JTabbedPane;

/*
 * Zeaxanthin Libraries
 */
import com.zeaxanthin.io.SaveStatus;
import com.zeaxanthin.io.ZeaFileIO;



/*
 * This class is not intended to be serialized.
 */
@SuppressWarnings("serial")



public abstract class ZeaSimulationPaneMultiple extends JTabbedPane
                                                implements ZeaSimulationPane<ZeaSimulationPaneMultiple>,
                                                           SaveStatus
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
    
    
    
    public abstract void notifySaveStatusListener();

    
    
    /**
     * Save the ZeaSimulationPane
     */
    public abstract void saveSimulation();
    
    
    
    /**
     * Save the ZeaSimulationPane to a File.
     */
    public abstract void saveSimulation(File filename);
    
    
    
    /**
     * Set the save status of the ZeaSimulationPane
     */
    public void setSaveStatus(boolean isSaved) {
        this.loadedFileSaveStatus = isSaved;
    }
    
    
    
    /**
     * In the event that someone tries to serialize this object, throw an exception.
     */
    private void writeObject(ObjectOutputStream oos) throws IOException {
        throw new IOException("The class: " + this.getClass().getSimpleName() +
                              " is NOT serializable.");
    }
}
