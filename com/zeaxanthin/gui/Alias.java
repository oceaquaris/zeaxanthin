package com.zeaxanthin.gui;

import java.io.Serializable;
import java.util.ArrayList;
import java.awt.Canvas;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.*;

public class Alias implements Serializable
{
    public static int count = 1;
   /**
    * Instance Data.
    */
    String s = null;
    String y = null;


   /**
    * Constructors.
    */
    public Alias()
    {
    }
    public Alias(String s)
    {
        this.s = s;
        this.y = this.s;
    }


   /**
    * Methods.
    */
    public String getS()
    {
        return this.s;
    }
    public String getY()
    {
        return this.y;
    }
    public void setS(String s)
    {
        this.s = s;
    }
    public void setY(String y)
    {
        this.y = y;
    }
    public static int getCount()
    {
        return count;
    }
    public static void setCount(int x)
    {
        count = x;
    }
    
    public static void main(String args[])
    {
        XStream x = new XStream();
        
        Alias can = new Alias("egg");
        
        String xml = x.toXML(can);
        
        System.out.println(xml);
        
        return;
    }
}