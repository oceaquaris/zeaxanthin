package org.zeatrace;

import java.io.Serializable;

public class Seeds implements Serializable
{
    /**
     * Phenotype of the Seeds.
     */
    protected Seed seedPhenotype = null;
    
    /**
     * Number of seeds of the specified phenotype.
     */
    protected Integer seedCount = null;

    /**
     * An empty constructor.
     */
    public Seeds()
    {
    }
    
    public Seed getSeedPhenotype()
    {
        return this.seedPhenotype;
    }
    public void setSeedPhenotype(Seed seedPhenotype)
    {
        this.seedPhenotype = seedPhenotype;
        return;
    }
    
    public Integer getSeedCount()
    {
        return this.seedCount;
    }
    public void setSeedCount(Integer seedCount)
    {
        this.seedCount = seedCount;
        return;
    }
}