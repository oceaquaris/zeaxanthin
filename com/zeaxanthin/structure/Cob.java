package com.zeaxanthin.structure;

import java.io.Serializable;
import java.util.ArrayList;

public class Cob implements Serializable
{
    protected String cobName = null;
    
    protected ArrayList<Seeds> allSeedPhenotypes = null;
    
    protected Double cobLength = null;
    
    protected String cobLengthUnit = null;
    
    protected String cobNotes = null;
    
    
    
    
    
    public Cob()
    {
    }
    
    
    
    
    
    public String getCobName()
    {
        return this.cobName;
    }
    public void setCobName(String cobName)
    {
        this.cobName = cobName;
        return;
    }
    
    public ArrayList<Seeds> getAllSeedPhenotypes()
    {
        return this.allSeedPhenotypes;
    }
    public void setAllSeedPhenotypes(ArrayList<Seeds> allSeedPhenotypes)
    {
        this.allSeedPhenotypes = allSeedPhenotypes;
        return;
    }
    
    public Double getCobLength()
    {
        return this.cobLength;
    }
    public void setCobLength(Double cobLength)
    {
        this.cobLength = cobLength;
        return;
    }
    
    public String getCobLengthUnit()
    {
        return this.cobLengthUnit;
    }
    public void setCobLengthUnit(String cobLengthUnit)
    {
        this.cobLengthUnit = cobLengthUnit;
    }
    
    public String getCobNotes()
    {
        return this.cobNotes;
    }
    public void setCobNotes(String cobNotes)
    {
        this.cobNotes = cobNotes;
        return;
    }
    
    public String toString()
    {
        return "Testing...";
    }
}