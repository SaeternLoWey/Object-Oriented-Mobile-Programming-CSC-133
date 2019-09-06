package com.mycompany.a3.Collection;

public interface IIterator {           //Interface for the Iterator
	
	public boolean hasNext();
	public Object getNext();
	public void removeIt();
	public int getIndex();
}
