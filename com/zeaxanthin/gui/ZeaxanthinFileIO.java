package com.zeaxanthin.gui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

import java.lang.Exception;


public class ZeaxanthinFileIO<E extends Serializable>
{
    protected E object;
    protected File filePath;
    
    
    /*
     * Constructors
     */
    public ZeaxanthinFileIO(String filePath, E object)
    {
        if(filePath == null) {
            this.filePath = null;
        }
        else {
            this.filePath = new File(filePath);
        }
        this.object = object;
    }
    public ZeaxanthinFileIO(File filePath, E object)
    {
        this.filePath = filePath;
        this.object = object;
    }
    
    
    /*
     * Set methods
     */
    public void setFilePath(String filePath)
    {
        this.filePath = new File(filePath);
    }
    public void setFilePath(File filePath)
    {
        this.filePath = filePath;
    }
    public void setTargetObject(E object)
    {
        this.object = object;
    }
    
    /*
     * Get methods
     */
    public E getTargetObject()
    {
        return this.object;
    }
    public File getFilePath()
    {
        return this.filePath;
    }
    
    /*
     * Export object as a binary stream
     */
    public void serializeTargetObject()
    {
        if(object == null || filePath == null) {
            return;
        }
        
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;
        
        try {
            fout = new FileOutputStream(filePath);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(object);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        try {
            if(oos != null) {
                oos.close();
            }
            if(fout != null) {
                fout.close();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /*
     * Load object from a File
     */
    public E loadSerializedObjectFromFilePath()
    {
        if(filePath == null) {
            return null;
        }
        
        FileInputStream fin = null;
        ObjectInputStream ois = null;
        
        try {
            fin = new FileInputStream(filePath);
            ois = new ObjectInputStream(fin);
            this.object = (E)(ois.readObject());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        try {
            if(fin != null) {
                fin.close();
            }
            if(ois != null) {
                ois.close();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return this.object;
    }
}