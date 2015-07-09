package org.zeatrace;

import java.awt.Color;
import java.util.ArrayList;

public class CobNode extends ZeaNode
{
    public static final int TYPE_COBNODE = 1;
    public static final Color COLOR_COBNODE = Color.YELLOW;
    
    /**
     * Instance data
     */
    private ArrayList<Phenotype> phenotypes;
    
    /**
     * Constructors
     */
    public CobNode(int index, int x, int y)
    {
        super(index, TYPE_COBNODE, x, y);
        this.color = COLOR_COBNODE;
    }
    public CobNode(int index, int x, int y, 
                   ArrayList<Integer> nodeConnections, 
                   ArrayList<Integer> nodeConnectionsTypes )
    {
        super(index, TYPE_COBNODE, x, y, nodeConnections, nodeConnectionsTypes);
        this.color = COLOR_COBNODE;
    }
    
    public void addPhenotype(Phenotype newPhenotype)
    {
        this.phenotypes.add(newPhenotype);
        return;
    }
    public Phenotype removePhenotype(int index)
    {
        return this.phenotypes.remove(index);
    }
    
    private String ArrayList_Phenotype_ToString(ArrayList<Phenotype> list)
    {
        if(list.size() == 0)
            return "\"\"";
        
        String output = new String("\"");
        
        for(int i = 0; i < list.size()-1; i++) {
            output += list.get(i).toString() + ",";
        }
        output += list.get(list.size()-1).toString() + "\"";
        
        return output;
    }
    
    public String toString()
    {
        return "" + this.index + "," + this.type + "," + this.x + "," + this.y + "," +
               ZeaNode.ArrayListToString(this.nodeConnections) + "," +
               ZeaNode.ArrayListToString(this.nodeConnectionsTypes) + "," +
               this.ArrayList_Phenotype_ToString(this.phenotypes);
    }
}