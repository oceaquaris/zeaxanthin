package com.zeaxanthin.zeatest;

import com.zeaxanthin.structure.ListenableArrayList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class HelloListenableArrayList implements PropertyChangeListener
{
	public void propertyChange(PropertyChangeEvent e) {
		if(ListenableArrayList.listenName.equals(e.getPropertyName()))
		{
			System.out.println("changed!");
		}
	}
	
	public static void main(String[] args)
	{
		ListenableArrayList<String> arr = new ListenableArrayList<String>();
		
		arr.addListener(new HelloListenableArrayList());
		
		arr.add("hi");
	}
}