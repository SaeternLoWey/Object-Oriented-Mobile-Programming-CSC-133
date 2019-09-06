package com.mycompany.a3.GameObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Missile extends MoveableObject {                    //missile class to destroy asteroid
	
	private int fuelLevel;                                         //amount of fuel of missile
	private int missileColor;                                    //the default color of the missile
	
	 
	public Missile(int theDir, float theX, float theY, int mapWidth, int mapHeight) {             //constructor for missile, this uses the ship direction, x and y for arguments missile	
		super(mapWidth, mapHeight);
		missileColor = ColorUtil.rgb(255, 0, 0);
		fuelLevel = 100;
		setDir(theDir);     				   			 
		setXLoc(theX);
	    setYLoc(theY);
		setColor(missileColor);		
	}
		
	public void startXLoc(int mapWidth) {                           //override the parent method because ship does not start at random location
	}
	
	public void startYLoc(int mapHeight) {                           //override the parent method because ship does not start at random location	
	}
	
	public void setStartSpeed()	{                          //this is overriding moveable setStartSpeed, and setting speed instead of random
		
		setSpeed(25);
	}
	
	public void setStartDir() {                            //this is overriding moveable setStartDir to do nothing, because the constructor will determine the missile dir		
	}
		
	public int getFuelLevel() {                               //this gets the fuel level of the missile
		
		return fuelLevel;
	}
	
	public void move()	{                              //this is overriding IMoveable interface method in MoveableObject class, because it includes fuel calculation
		
		double theta = 90 - getDir();
		float xNew = getXLoc() + (float)(Math.cos(Math.toRadians(theta))*getSpeed());
		setXLoc(xNew);
		float yNew = getYLoc() + (float)(Math.sin(Math.toRadians(theta))*getSpeed());
		setYLoc(yNew);		
		fuelLevel = fuelLevel - 1;	
	}

	public String toString() {                               //override, to print information of Missile object
		
		String s = "";	
		s += "Missile: loc=" + getXLoc() + "," + getYLoc() +"color=[" + ColorUtil.red(missileColor) + ",";
		s += ColorUtil.blue(missileColor) + "," + ColorUtil.green(missileColor) + "] speed=" + getSpeed();
		s += " dir=" + getDir() + " fuel=" + getFuelLevel();
		return s;
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		int xLoc = pCmpRelPrnt.getX() + (int) getXLoc();// shape location relative
		int yLoc = pCmpRelPrnt.getY() + (int) getYLoc();// to parent’s origin
		//int xLoc = pCmpRelPrnt.getX();
		//int yLoc = pCmpRelPrnt.getY();
		
		g.fillRect(xLoc, yLoc, 5, 50);
		
		
	}
	
}
