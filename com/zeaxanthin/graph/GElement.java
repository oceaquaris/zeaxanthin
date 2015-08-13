package com.zeaxanthin.graph;

import java.awt.event.MouseEvent;

/**
 * An element in a Graph
 */
public interface GElement
{   
    /**
     * Remove this element from the graph.
     */
    abstract public void remove();
    
    /**
     * Subtypes of Graph elements must override this if it want GraphEditor
     * to be able to locate them. Only classes that can not be selected and that
     * doesn't have a popupmenu can made due with the default behavior.
     * @return <code>true</code> or <code>false</code>, indicating whether the x
     *         and y coordinates are within the <code>GraphElement</code>.
     */
    abstract public boolean contains(int x, int y);
    
    /**
     * A double click was done on this element.
     */
    abstract public void doubleClick(MouseEvent evt);

    /**
     * Post the context menu for this target.
     */
    abstract public void popupMenu(int x, int y, GraphEditor graphEditor);
    
    /**
     * Get the text to display as a tool tip when the user hovers over this element
     * @return null for no tool-tip, otherwise return the text
     */
    abstract public String getTooltipText();
    
    /**
     * Indicates whether this element shall be visible in the graph.
     * 
     * @return <code>true</code> if the element is visible, <code>false</code>
     *         otherwise.
     */
    abstract public boolean isVisible();

    /**
     * Sets the visible setting of this element.
     * 
     * @param visible
     *            The new visible setting.
     */
    abstract public void setVisible(boolean visible);
}
