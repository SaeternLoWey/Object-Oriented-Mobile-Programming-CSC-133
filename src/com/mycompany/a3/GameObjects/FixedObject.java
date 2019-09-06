package com.mycompany.a3.GameObjects;

public abstract class FixedObject extends GameObject {         //abstract class for fixed objects
	
	private int id; //all fixed objects have a unique id
	
	public FixedObject(int mapWidth, int mapHeight) {
		super(mapWidth, mapHeight);
	}
		
	public void setID(int theID) {             //set unique id for fixed object
		id = theID;
	}
	 
	public int getID() {                     //get unique id for object
		return id;
	}	
}
