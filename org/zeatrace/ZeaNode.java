package org.zeatrace;

import java.util.ArrayList;
import java.awt.Color;

public abstract class ZeaNode
{
    //various constants
    public static final Integer CONNECTIONTYPE_MATE = 0;
    public static final Integer CONNECTIONTYPE_OFFSPRING = 1;
    public static final Integer CONNECTIONTYPE_CULTIVATION = 2;
    
    /**
     * if nodeConnections is null, has not been initialized
     * if type == -1, has not been initialized
     */
    //index of this database node
    protected int index;
    //type this node is
    protected int type;
    //coordinates
    protected int x, y;
    //list of pointers node is connected to
    protected ArrayList<Integer> nodeConnections;
    //type of connection a nodeConnection is
    protected ArrayList<Integer> nodeConnectionsTypes;
    //color
    protected Color color;
    
    //data will be implemented in inherited structures

    
    public ZeaNode(int index, int x, int y)
    {
        this.index = index;
        this.nodeConnections = new ArrayList<Integer>();
        this.nodeConnectionsTypes = new ArrayList<Integer>();
        this.type = -1;
    }
    
    public ZeaNode(int index, int type, int x, int y)
    {
        this.index = index;
        this.nodeConnections = new ArrayList<Integer>();
        this.nodeConnectionsTypes = new ArrayList<Integer>();
        this.type = type;
    }
    
    public ZeaNode(int index, int type, int x, int y,
                   ArrayList<Integer> nodeConnections,
                   ArrayList<Integer> nodeConnectionsTypes)
    {
        this.index = index;
        this.nodeConnections = nodeConnections;
        this.nodeConnectionsTypes = nodeConnectionsTypes;
        this.type = type;
    }
    
    public ZeaNode(int index, int x, int y, 
                   ArrayList<Integer> nodeConnections,
                   ArrayList<Integer> nodeConnectionsTypes)
    {
        this.index = index;
        this.nodeConnections = nodeConnections;
        this.nodeConnectionsTypes = nodeConnectionsTypes;
        this.type = -1;
    }
    
    /**
     * Get methods
     */
    public int getIndex()
    {
        return this.index;
    }
    public ArrayList<Integer> getNodeConnections()
    {
        return this.nodeConnections;
    }
    public ArrayList<Integer> getNodeConnectionsTypes()
    {
        return this.nodeConnectionsTypes;
    }
    public ArrayList[] getNodes()
    {
        ArrayList[] nodes = { this.nodeConnections,
                              this.nodeConnectionsTypes };
        return nodes;
    }
    public int getType()
    {
        return this.type;
    }
    public int getX()
    {
        return this.x;
    }
    public int getY()
    {
        return this.y;
    }
    public Color getColor()
    {
        return this.color;
    }
    
    /**
     * Set methods.
     */
    public void setIndex(int index)
    {
        this.index = index;
    }
    public void setNodeConnections(ArrayList<Integer> nodeConnections)
    {
        if(this.nodeConnectionsTypes.size() != nodeConnections.size())
            return;
        this.nodeConnections = nodeConnections;
        return;
    }
    public void setNodeConnectionsTypes(ArrayList<Integer> nodeConnectionsTypes)
    {
        if(this.nodeConnections.size() != nodeConnectionsTypes.size())
            return;
        this.nodeConnectionsTypes = nodeConnectionsTypes;
        return;
    }
    public void setNodes(ArrayList<Integer> nodeConnections, ArrayList<Integer> nodeConnectionsTypes)
    {
        if(nodeConnections.size() != nodeConnectionsTypes.size())
            return;
        this.nodeConnections = nodeConnections;
        this.nodeConnectionsTypes = nodeConnectionsTypes;
        return;
    }
    public void setNodes(ArrayList[] node)
    {
        if(node.length != 2)
            return;
        this.nodeConnections = node[0];
        this.nodeConnectionsTypes = node[1];
        return;
    }
    public void setType(int type)
    {
        this.type = type;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public void setColor(Color color)
    {
        this.color = color;
    }
    public void setCoords(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Methods dealing with node pointers
     */
    public boolean addNodeConnection(Integer additionalNode, Integer additionalNodeType)
    {
        return (this.nodeConnections.add(additionalNode) &&
                this.nodeConnectionsTypes.add(additionalNodeType));
    }
    public void clearNodeConnections()
    {
        this.nodeConnections.clear();
        this.nodeConnectionsTypes.clear();
    }
    public Integer[] getNodeConnection(int index)
    {
        Integer[] node = { this.nodeConnections.get(index),
                           this.nodeConnectionsTypes.get(index) };
        return node;
    }
    public Integer[] removeNodeConnection(int index)
    {
        Integer[] node = { this.nodeConnections.remove(index),
                           this.nodeConnectionsTypes.remove(index) };
        return node;
    }
    
    /**
     * Method for converting an ArrayList to a String
     * String returned is capped by double quotes; String is comma separated
     */
    public static String ArrayListToString(ArrayList<Integer> list)
    {
        if(list.size() == 0)
            return "\"\"";
        
        String output = new String("\"");
        
        for(int i = 0; i < list.size()-1; i++) {
            output += list.get(i) + ",";
        }
        output += list.get(list.size()-1) + "\"";
        
        return output;
    }
    
    /**
     * Methods used for writing object to disk
     */
    public abstract String toString();
}