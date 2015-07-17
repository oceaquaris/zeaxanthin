package com.zeaxanthin.structure;

import java.util.Collection;
import java.util.ArrayList;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class ListenableArrayList<T> extends ArrayList<T>
{
	ArrayList<PropertyChangeListener> listeners = new ArrayList<PropertyChangeListener>();
	public boolean addListener(PropertyChangeListener listener)
	{
		return listeners.add(listener);
	}
	public boolean removeListener(PropertyChangeListener listener)
	{
		return listeners.remove(listener);
	}
	
	public static final String listenName = "array";
	
	public ListenableArrayList() { super(); }
	
	public ListenableArrayList(Collection<? extends T> c) { super(c); }
	
	@Override
	public boolean add(T t)
	{
		ListenableArrayList<T> old = new ListenableArrayList<T>(this);
		boolean out = super.add(t);
		for(PropertyChangeListener listener : listeners)
		{
			listener.propertyChange(new PropertyChangeEvent(this, listenName, old, this));
		}
		return out;
	}
	
	@Override
	public void add(int i, T t)
	{
		ListenableArrayList<T> old = new ListenableArrayList<T>(this);
		super.add(i, t);
		for(PropertyChangeListener listener : listeners)
		{
			listener.propertyChange(new PropertyChangeEvent(this, listenName, old, this));
		}
	}
	
	@Override
	public boolean addAll(Collection<? extends T> c)
	{
		ListenableArrayList<T> old = new ListenableArrayList<T>(this);
		boolean out = super.addAll(c);
		for(PropertyChangeListener listener : listeners)
		{
			listener.propertyChange(new PropertyChangeEvent(this, listenName, old, this));
		}
		return out;
	}
	
	@Override
	public boolean addAll(int i, Collection<? extends T> c)
	{
		ListenableArrayList<T> old = new ListenableArrayList<T>(this);
		boolean out = super.addAll(i, c);
		for(PropertyChangeListener listener : listeners)
		{
			listener.propertyChange(new PropertyChangeEvent(this, listenName, old, this));
		}
		return out;
	}
	
	@Override
	public void clear()
	{
		ListenableArrayList<T> old = new ListenableArrayList<T>(this);
		super.clear();
		for(PropertyChangeListener listener : listeners)
		{
			listener.propertyChange(new PropertyChangeEvent(this, listenName, old, this));
		}
	}
	
	@Override
	public boolean remove(Object o)
	{
		return super.remove(o);
	}
}