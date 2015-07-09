package org.zeatrace;


public class Phenotype
{
    private Seed seedType;
    private int seedCount;
    
    public Phenotype(Seed seed, int count)
    {
        this.seedType = seed;
        this.seedCount = count;
    }
    
    public Seed getSeedType()
    {
        return this.seedType;
    }
    public int getSeedCount()
    {
        return this.seedCount;
    }
    
    public void setSeedType(Seed newSeed)
    {
        this.seedType = newSeed;
    }
    public void setSeedCount(int newCount)
    {
        this.seedCount = newCount;
    }
    
    public String toString()
    {
        String output = new String("\"");
        
        output += this.seedType.toString() + ",";
        output += seedCount + "\"";
        
        return output;
    }
}