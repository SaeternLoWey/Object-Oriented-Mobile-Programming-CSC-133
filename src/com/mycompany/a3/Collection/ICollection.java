package com.mycompany.a3.Collection;

public interface ICollection {         //Interface for Collection to have add and Iterator
	
	public void add( Object newObj );
	public IIterator getIterator();
}
