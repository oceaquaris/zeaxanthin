/**
 * Write a description of interface ZeaSimulationPane here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

package com.zeaxanthin.gui;

/*
 * Standard Java Libraries
 */
import java.io.File;
import java.awt.Component;

import com.zeaxanthin.io.SaveStatus;

public interface ZeaSimulationPane<E extends Component>
{
    /* Suggested Instance Data
    
    private File loadedFile;
    private boolean loadedFileSaveStatus = false;
    private ZeaFileIO saver;
    
    
     */
    /**
     * Get the loaded File represented by this ZeaSimulationPane.
     */
    public abstract File getLoadedFile();
    
    
    
    /**
     * Get the saveStatus of the ZeaSimulationPane.
     */
    public abstract boolean getSaveStatus();
    
    
    
    /**
     * Get the this object. This is a nasty hack to retrieve an object extending
     * a Component, but also implementing this interface. Basically a way to allow
     * for multiple inheritance.
     */
    public abstract E getSelf();
    
    
    
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
    public abstract void setSaveStatus(boolean isSaved);
}
