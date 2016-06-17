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
import java.util.Vector;
import javax.swing.JScrollPane;

/*
 * Zeaxanthin Libraries
 */
import com.zeaxanthin.io.CSVIO;
import com.zeaxanthin.io.SaveStatus;
import com.zeaxanthin.io.SaveStatusListener;
import com.zeaxanthin.io.ZeaFileIO;



/*
 * This class is not intended to be serialized.
 */
@SuppressWarnings("serial")



public abstract class ZeaSimulationPaneSingle extends JScrollPane
                                              implements ZeaSimulationPane<ZeaSimulationPaneSingle>,
                                                         SaveStatus,
                                                         SaveStatusListener
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
     * Create an empty ZeaSimulationPaneSingle.
     */
    public ZeaSimulationPaneSingle(final SaveStatusListener statusParent) {
        super();
        this.setSaveStatusListener(statusParent);
    }
    
    
    
    /**
     * Create a ZeaSimulationPaneSingle from a File.
     */
    public ZeaSimulationPaneSingle(final SaveStatusListener statusParent,
                                   File filename,
                                   ZeaFileIO loadSaver) {
        //Call the super-constructor to create a JScrollPane
        super( loadSaver.read(filename) );
        
        
        //Store loaded File information
        this.loadedFile = filename;
        
        
        //Store the io object
        this.loadSaver = loadSaver;
        
        
        //Retrieve and store the pointer to the ZeaTable we have just created.
        this.zTable = (ZeaTable)(this.getViewport().getView());
        this.zTable.setSaveStatusListener(this);
        
        
        //Set the SaveStatusListener for this object
        this.setSaveStatusListener(statusParent);
    }
    
    
    
    /*
     **********************************************************************************************
     **********************************************************************************************
     **********************************************************************************************
     */
    
    
    
    /**
     * SaveStatusListener implementation
     *
     * Add a child SaveStatus object.
     */
    public boolean addSaveStatusChild(final SaveStatus statusChild) {
        return this.saveStatusChildren.add(statusChild);
    }
    
    
    
    /**
     * Update the SaveStatusListener when a child has been modified.
     * This function should be called by the child.
     */
    public void childModified(final Object source, boolean isSaved) {
        this.setSaveStatusNotifySaveStatusListener(isSaved);
        return;
    }
    
    
    
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
     * SaveStatusListener implementation
     *
     * Get a Vector of SaveStatus children.
     */
    public Vector<SaveStatus> getSaveStatusChildren() {
        return this.saveStatusChildren;
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
     * ZeaSimulationPane implementation
     *
     * Save the ZeaSimulationPane
     */
    public void saveSimulation() {
        this.saveSimulation(this.loadedFile);
    }
    
    
    
    /**
     * ZeaSimulationPane implementation
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
                    this.loadSaver = new CSVIO();
                    this.loadSaver.write(this.zTable, filename);
                    return;
                }
                return;
            }
                
            
        }
        return;
    }
    
    
    
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
     * SaveStatus implementation
     * 
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
