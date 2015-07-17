package com.zeaxanthin.structure;

import java.io.Serializable;

public class Transposon implements Serializable
{
    /**
     * Color of the transposon.
     */
    protected String transposonColor = null;
    
    /**
     * Description of the transposon.
     */
    protected String transposonDescription = null;

    /**
     * An empty constructor.
     */
    public Transposon()
    {
    }
    
    /**
     * Get methods.
     */
    public String getTransposonDescription()
    {
        return this.transposonDescription;
    }
    public void setTransposonDescription(String transposonDescription)
    {
        this.transposonDescription = transposonDescription;
        return;
    }
    
    public String getTransposonColor()
    {
        return this.transposonColor;
    }
    public void setTransposonColor(String transposonColor)
    {
        this.transposonColor = transposonColor;
        return;
    }
}