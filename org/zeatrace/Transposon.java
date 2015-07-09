package org.zeatrace;

import java.awt.Color;
//our libs
import org.zeatrace.Seed;

public class Transposon
{
    //constants
    public static final Transposon RED_DOT = new Transposon(Color.RED, "Dot");
    public static final Transposon RED_SWIRL = new Transposon(Color.RED, "Swirl");
    public static final Transposon BLUE_SPECKLE = new Transposon(Color.BLUE, "Speckle");
    
    /**
     * Instance data.
     */
    private String description;
    private Color color;
    
    /**
     * Constructor
     */
    public Transposon(Color color, String description)
    {
        this.color = color;
        this.description = description;
    }
    
    /**
     * Get methods.
     */
    public String getDescription()
    {
        return this.description;
    }
    public Color getColor()
    {
        return this.color;
    }
    
    /**
     * For comparing transposons.
     */
    public boolean equals(Transposon t)
    {
        return (this.description.equalsIgnoreCase(t.getDescription()) &&
                this.color.equals(t.getColor()) );
    }
    
    /**
     * For getting all predefined constant Transposon types.
     */
    public static Transposon[] getPredefinedTransposons()
    {
        Transposon[] all = { RED_DOT,
                             RED_SWIRL,
                             BLUE_SPECKLE };
        return all;
    }
    
    /**
     * String representation of the transposon.
     */
    public String toString()
    {
        return "\"" + Seed.colorToString(this.color) + "," + this.description + "\"";
    }
}