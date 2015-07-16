package org.zeatrace;

import java.io.Serializable;
import java.util.ArrayList;

public class Seed implements Serializable
{
    /**
     * Name of the seed.
     */
    protected String seedName = null;
    
    /**
     * Year the seed was produced.
     */
    protected Integer yearProduced = null;
    
    /**
     * Color of the pericarp.
     */
    protected String pericarpColor = null;
    /**
     * Color of the aleurone.
     */
    protected String aleuroneColor = null;
    /**
     * Color of the endosperm.
     */
    protected String endospermColor = null;
    
    protected String seedNotes = null;
    
    /**
     * A list of all transposable elements.
     */
    protected ArrayList<Transposon> transposons = null;
    
    public Seed()
    {
    }
}