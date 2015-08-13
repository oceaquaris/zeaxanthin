package com.zeaxanthin.graph;

/**
 * Implemented by GElements that are selectable.
 */
public interface Selectable 
{
    /*
     * Selection
     */
    /**
     * Set the selection status to input boolean value
     *
     * @param selected New state of the selected item.
     */
    abstract public void setSelected(boolean selected);
    
    /**
     * Determine whether the GElement is selected.
     *
     * @return <code>true</code> or <code>false</code>; the selection state.
     */
    abstract public boolean isSelected();
    
    /**
     * Flip the selected state to its inverse value.
     */
    abstract public void flipSelectedState();
    
    
    /*
     * Resizing
     */
    /**
     *
     */
    abstract public boolean isHandle(int x, int y);
    /**
     * Determine whether the GElement is resizable.
     *
     * @return Whether or not the element is resizable.
     */
    abstract public boolean isResizable();
}
