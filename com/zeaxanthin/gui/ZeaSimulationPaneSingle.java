/**
 * Abstract class ZeaSimulationPaneSingle - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
package com.zeaxanthin.gui;



/*
 * Standard Java Libraries
 */
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.JScrollPane;

/*
 * Zeaxanthin Libraries
 */
import com.zeaxanthin.io.FileTypeCSV;
import com.zeaxanthin.io.SaveStatus;
import com.zeaxanthin.io.SaveStatusListener;
import com.zeaxanthin.io.ZeaFileIO;



/*
 * This class is not intended to be serialized.
 */
@SuppressWarnings("serial")



public abstract class ZeaSimulationPaneSingle extends JScrollPane
                                              implements ZeaSimulationPane<ZeaSimulationPaneSingle>,
                                                         SaveStatus
{
    /**
     * The File that has been loaded.
     */
    protected File loadedFile = null;
    
    
    
    /**
     * The save status of the ZeaSimulationPaneSingle
     */
    protected boolean loadedFileSaveStatus = true;
    
    
    
    /**
     * The ZeaFileIO object that handles saving.
     */
    protected ZeaFileIO loadSaver = null;
    
    
    
    /**
     * The ZeaTable to display
     */
    protected ZeaTable zTable = null;
    
    
    
    /**
     * A parent class that listens for when this object is modified.
     */
    protected SaveStatusListener statusParent = null;
    
    
    
    /*
     **********************************************************************************************
     **********************************************************************************************
     **********************************************************************************************
     */
    
    
    
    /**
     * Create an empty ZeaSimulationPaneSingle.
     */
    public ZeaSimulationPaneSingle(final SaveStatusListener statusParent) {
        super();
        this.statusParent = statusParent;
    }
    
    
    
    /**
     * Create a ZeaSimulationPaneSingle from a File.
     */
    public ZeaSimulationPaneSingle(final SaveStatusListener statusParent,
                                   File filename,
                                   ZeaFileIO loadSaver) {
        super( loadSaver.read(filename) );//call super-constructor
        
        this.loadedFile = filename;     // Store loaded File information
        this.loadSaver = loadSaver;     // Store the io object
        
        this.zTable = (ZeaTable)(this.getViewport().getView());
        
        this.statusParent = statusParent;
    }
    
    
    
    /*
     **********************************************************************************************
     **********************************************************************************************
     **********************************************************************************************
     */
    
    
    
    /**
     * Implementation of the ZeaSimulationPane interface.
     *
     * Get the loaded File represented by this ZeaSimulationPane.
     */
    public File getLoadedFile() {
        return this.loadedFile;
    }
    
    
    
    /**
     * Implementation of the ZeaSimulationPane and SaveStatus interfaces.
     *
     * Get the saveStatus of the ZeaSimulationPane.
     */
    public boolean getSaveStatus() {
        return this.loadedFileSaveStatus;
    }
    
    
    
    /**
     * Implementation of the ZeaSimulationPane<ZeaSimulationPaneSingle> interface.
     *
     * Get the this object. This is a nasty hack to retrieve an object extending
     * a Component, but also implementing this interface. Basically a way to allow
     * for multiple inheritance.
     */
    public ZeaSimulationPaneSingle getSelf() {
        return this;
    }
    
    
    
    /**
     * Get the ZeaTable contained within this object.
     */
    public ZeaTable getZeaTable() {
        return this.zTable;
    }
    
    
    
    /**
     * Implementation of the SaveStatus interface.
     *
     * Notify a higher level gui Pane that the data in this object has been modified.
     */
    public void notifySaveStatusListener(boolean isSaved) {
        this.statusParent.updateSaveStatusListener(this, isSaved);
    }

    
    
    /**
     * Implementation of the ZeaSimulationPane interface.
     *
     * Save the ZeaSimulationPane
     */
    public void saveSimulation() {
        this.saveSimulation(this.loadedFile);
    }
    
    
    
    /**
     * Implementation of the ZeaSimulationPane interface.
     *
     * Save the ZeaSimulationPane to a File.
     * 
     * If a null 'filename' is passed to this function, this function will attempt
     * to save using 'this.loadedFile'.
     */
    public void saveSimulation(File filename) {
        if(filename == null) {
            if(this.loadedFile != null) {
                filename = this.loadedFile;
            }
            else {
                //add debug
                return;
            }
        }
        
        if( !this.loadedFileSaveStatus ) { //If loadedFile is not saved.
            
            if( this.loadSaver != null ) {
                this.loadSaver.write(this.zTable, filename);
                return;
            }
            else {
                String ext = ZeaFileIO.getExtension(filename);
                if( ext.equals("csv") ) {
                    this.loadSaver = new FileTypeCSV();
                    this.loadSaver.write(this.zTable, filename);
                    return;
                }
                return;
            }
                
            
        }
        return;
    }
    
    
    
    /**
     * Implementation of the ZeaSimulationPane and SaveStatus interfaces.
     *
     * Set the save status of the ZeaSimulationPane
     */
    public void setSaveStatus(boolean isSaved) {
        if( this.loadedFileSaveStatus != isSaved ) {
            this.notifySaveStatusListener(isSaved);
        }
        return;
    }
    
    
    
    /**
     * Set the SaveStatusListener for this object.
     */
    public void setSaveStatusListener(final SaveStatusListener statusParent) {
        this.statusParent = statusParent;
        
        this.statusParent.addSaveStatusChild(this);
        return;
    }
    
    
    
    /*
     **********************************************************************************************
     **********************************************************************************************
     **********************************************************************************************
     */
    
    
    
    /**
     * In the event that someone tries to serialize this object, throw an exception.
     */
    private void writeObject(ObjectOutputStream oos) throws IOException {
        throw new IOException("The class: " + this.getClass().getSimpleName() +
                              " is NOT serializable.");
    }
}
