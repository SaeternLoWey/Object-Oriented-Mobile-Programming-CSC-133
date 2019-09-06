package com.mycompany.a3.GameObjects;

public interface ISteerable {                 //class interface to allow controls of moveable object
	
	public void iSteerSetSpeed(int newSpeed);  //this method will set the speed of moveable object
	public void turnLeft(int L);               //this method will turn make the moveable object turn left
	public void turnRight(int R);               //this method will turn make the moveable object turn right
}
