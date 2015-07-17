package com.zeaxanthin.structure;

import java.io.Serializable;
import java.util.ArrayList;

public class Plant implements Serializable
{
    /**
     * The name of the plant.
     */
    protected String plantName = null;
    
    /**
     * The year the plant was grown.
     */
    protected Integer yearPlanted = null;
    
    /**
     * The seed from which the plant was grown from.
     */
    protected Seed plantPhenotype = null;
    
    /**
     * A list of all cobs on the plant.
     */
    protected ArrayList<Cob> allCobs = null;
    
    /**
     * Height of the plant.
     */
    protected Double plantHeight = null;
    
    /**
     * Unit for the height of the plant (ex: cm, ft, etc.).
     */
    protected String plantHeightUnit = null;
    
    /**
     * Miscellaneous notes about the plant.
     */
    protected String plantNotes = null;
    
    
    
    
    
    /**
     * An empty constructor.
     */
    public Plant()
    {
    }
    
    
    
    
    
    /**
     * Return the name of the plant.
     */
    public String getPlantName()
    {
        return this.plantName;
    }
    /**
     * Set the name of the plant.
     */
    public void setPlantName(String plantName)
    {
        this.plantName = plantName;
        return;
    }
    
    /**
     * Return the year in which the plant was grown.
     */
    public Integer getYearPlanted()
    {
        return this.yearPlanted;
    }
    /**
     * Set the year in which the plant was grown.
     */
    public void setYearPlanted(Integer yearPlanted)
    {
        this.yearPlanted = yearPlanted;
        return;
    }
    
    /**
     * Get the phenotype of the seed from which the plant was grown from.
     */
    public Seed getPlantPhenotype()
    {
        return this.plantPhenotype;
    }
    /**
     * Set the phenotype of the seed from which the plant was grown from.
     */
    public void setPlantPhenotype(Seed plantPhenotype)
    {
        this.plantPhenotype = plantPhenotype;
        return;
    }
}