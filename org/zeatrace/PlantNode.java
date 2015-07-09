package org.zeatrace;

import java.util.ArrayList;
import java.awt.Color;
//our libs
import org.zeatrace.Seed;

public class PlantNode extends ZeaNode
{
    //various constants for this object type
    public static final int TYPE_PLANTNODE = 0;
    public static final Color COLOR_PLANTNODE = Color.GREEN;
    
    /**
     * Instance data
     */
    private Seed seedPhenotype;
    
    public PlantNode(int index, int x, int y, Seed seed)
    {
        super(index, TYPE_PLANTNODE, x, y);
        this.color = COLOR_PLANTNODE;
        this.seedPhenotype = seed;
    }
    public PlantNode(int index, int x, int y, 
                     ArrayList<Integer> nodeConnections, 
                     ArrayList<Integer> nodeConnectionsTypes,
                     Seed seed)
    {
        super(index, TYPE_PLANTNODE, x, y, nodeConnections, nodeConnectionsTypes);
        this.color = COLOR_PLANTNODE;
        this.seedPhenotype = seed;
    }

    
    
    public String toString()
    {
        return "" + this.index + "," + this.type + "," + this.x + "," + this.y + "," +
               ZeaNode.ArrayListToString(this.nodeConnections) + "," +
               ZeaNode.ArrayListToString(this.nodeConnectionsTypes) + "," +
               this.seedPhenotype.toString();
    }
}