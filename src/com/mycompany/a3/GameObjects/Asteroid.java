package com.mycompany.a3.GameObjects;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Asteroid extends MoveableObject {                  //class of flying space object to get destroyed or crash into Ship
	                                
	private int size;                                          //this variable is the size of the asteroid
	 
	
	public Asteroid(int mapWidth, int mapHeight) {                  //asteroid constructor, that sets size and color
		super(mapWidth, mapHeight);
		setColor(ColorUtil.rgb(0, 255, 0));                 //this is the default color of asteroid
		setAsteroidSize();
	}

	public void setAsteroidSize() {                          //this method randomly selects asteroid size
		
		Random gen = new Random();
		size = 0 + gen.nextInt(10);
	}
	
	public int getAsteroidSize() {                          //this gets the asteroid size
		
		return size;
	}

	public String toString() {                             ////override, to print information of Asteroid object
		
		String s = "";	
		s += "Asteroid: loc=" + getXLoc() + "," + getYLoc() +" color=[" + ColorUtil.red(this.getColor()) + ",";
		s += ColorUtil.blue(this.getColor()) + "," + ColorUtil.green(this.getColor()) + "] speed=" + getSpeed();
		s += " dir=" + getDir() + " size=" + getAsteroidSize();
		return s;
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		int xLoc = pCmpRelPrnt.getX() + (int) getXLoc();// shape location relative
		int yLoc = pCmpRelPrnt.getY() + (int) getYLoc();// to parent’s origin
		//int xLoc = pCmpRelPrnt.getX();
		//int yLoc = pCmpRelPrnt.getY();
		
		g.fillRect(xLoc, yLoc, 30, 50);
		//System.out.println("asteroid running");
	}
	
}
