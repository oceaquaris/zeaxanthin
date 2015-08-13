package com.zeaxanthin.graph;

/**
 * Superclass for all selectable graph elements.
 */
public abstract interface SelectableGElement extends GraphElement, Selectable
{
    /**
     *
     */
    public abstract void singleSelected();
}
