package com.mycompany.a3.GameObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Ship extends MoveableObject implements ISteerable {        //Flying space ship class
	
	private int missileCount;                              //amount of missile the ship has
	//private int shipColor;                                 
	
	 
	public Ship(int mapWidth, int mapHeight) {
		
		super(mapWidth, mapHeight);
		//shipColor = ColorUtil.rgb(255, 0, 0);
		this.setColor(ColorUtil.rgb(255, 0, 0));            //default color for all Ships
		setMissileCount(10);                             //ship constructor, set missiles to 10
		//setColor(shipColor);							//set ship color
	}
	
	public void startXLoc(int mapWidth) {                         //override, this set x to 512, instead of random
	
		setXLoc(mapWidth/2);
	}
	
	public void startYLoc(int mapHeight) {						//override, this set y to 384, instead of random
		
		setYLoc(mapHeight/2);
	}
	
	public void setStartSpeed() {						//override, this set ship speed to zero, instead of random
		
		setSpeed(0);
	}
	
	public void setStartDir() {						//override, this set ship direction to zero, instead of random
		
		setDir(0);
	}
	
	public void iSteerSetSpeed(int theSpeed) {            //ISteerable interface method to change ship speed
		
		setSpeed(theSpeed);	
	}
	
	public void turnLeft(int L) {					//ISteerable interface method to turn left by an amount L
		
		setDir(getDir() - L);
	}
	
	public void turnRight(int R) {					//ISteerable interface method to turn right by an amount R
		
		setDir(getDir() + R);
	}
	
	public void setMissileCount(int m) {           //method to set missile count
		
		missileCount = m;
	}
	
	public int getMissileCount() {                 //method to get missile count
		
		return missileCount;
	}
	
	public String toString() {                    //override, to print information of Ship object
		
		String s = "";	
		s += "Ship: loc=" + getXLoc() + "," + getYLoc() +" color=[" + ColorUtil.red(this.getColor()) + ", ";
		s += ColorUtil.blue(this.getColor()) + ", " + ColorUtil.green(this.getColor()) + "] speed=" + getSpeed();
		s += " dir=" + getDir() + " missiles=" + getMissileCount();
		return s;
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		int xLoc = pCmpRelPrnt.getX() + (int) getXLoc();// shape location relative
		int yLoc = pCmpRelPrnt.getY() + (int) getYLoc();// to parent’s origin
		//int xLoc = pCmpRelPrnt.getX();
		//int yLoc = pCmpRelPrnt.getY();
		
		g.fillTriangle(xLoc-20, yLoc-40, xLoc+20, yLoc-40, xLoc, yLoc+40);
		//(xLoc, yLoc, 30, 50);
		//System.out.println("I am running8");
		
		
		
	}
}