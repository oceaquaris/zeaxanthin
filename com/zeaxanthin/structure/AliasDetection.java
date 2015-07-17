package com.zeaxanthin.structure;

public interface AliasDetection
{
    /**
     * Used for comparing two objects to determine if they are aliases of each other.
     * XStream does not handle aliases well.
     */
    public boolean equals(Object anObject);
    
    /**
     * Eliminate internal redundancies which may have arose from aliases within the object during
     * XML serialization with XStream.
     */
    public void eliminateInternalRedundancies();
}
