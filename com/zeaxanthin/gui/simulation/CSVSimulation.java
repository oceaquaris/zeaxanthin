/**
 * Write a description of class CSVSimulation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
package com.zeaxanthin.gui.simulation;



/*
 * Standard Java Libraries
 */
import java.io.File;

/*
 * Zeaxanthin Libraries
 */
import com.zeaxanthin.gui.ZeaSimulationPaneSingle;
import com.zeaxanthin.io.SaveStatusListener;
import com.zeaxanthin.io.ZeaFileIO;



/*
 * This class is not intended to be serialized.
 */
@SuppressWarnings("serial")



public class CSVSimulation extends ZeaSimulationPaneSingle
{
    /**
     * Constructor for objects of class CSVSimulation
     */
    public CSVSimulation(final SaveStatusListener statusParent,
                         File filename,
                         ZeaFileIO loadSaver) {
        super(statusParent, filename, loadSaver);
    }
}
