package com.mycompany.a3.GameObjects;

import java.util.Random;
import java.lang.Math;

public abstract class MoveableObject extends GameObject implements IMoveable {   //this abstract class for missile, ship, asteroid, for moveable objects
	
	private int speed;                                 //this is speed to moveable object
	private int dir;                                   //this is the direction of moveable object
	
	 
	public MoveableObject(int mapWidth, int mapHeight) {                         //moveable object constructor, sets start speed and direction
		super(mapWidth, mapHeight);
		setStartSpeed();
		setStartDir();
	}
	
	public void setStartSpeed() {                     //method to randomly generate moveable object speed
		
		Random gen = new Random();
		speed = 1 + gen.nextInt(10);
	}
	
	public void setStartDir() {                       //method to randomly generate moveable object direction
		
		Random gen = new Random();
		dir = 0 + gen.nextInt(359);
	}
	
	public void setSpeed(int newSpeed) {                   //method to set speed of moveable object
		
		speed = newSpeed;
	}
	
	public int getSpeed() {                           //method to get speed of moveable object
		
		return speed;
	}
	
	public void setDir(int newDir) {                   //method to set direction of moveable object
		
		dir = newDir;
	}
	
	public int getDir() {                              //method to get direction of moveable object
		
		return dir;
	}
	
	public void move() {                              //implementation of IMoveable interface method to move objects
		
		double theta = 90 - dir;
		float xNew = getXLoc() + (float)(Math.cos(Math.toRadians(theta))*speed);
		setXLoc(xNew);
		float yNew = getYLoc() + (float)(Math.sin(Math.toRadians(theta))*speed);
		setYLoc(yNew);
	}
}
