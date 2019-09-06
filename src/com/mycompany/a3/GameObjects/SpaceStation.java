package com.mycompany.a3.GameObjects;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class SpaceStation extends FixedObject {                       //station class to refill ship missiles
	
	private int stationColor;                                        //default color of space station  
	private boolean blinkToggle;                                   //this is the flag that will make the space station to blink
	private int blinkRate;                                          //this is the blink rate of the space station
	private int blinkTimer;                                         //this is the timer for when the station to blink
	
	                                                                    
	public SpaceStation(int uid, int mapWidth, int mapHeight) {                                //space station constructor, sets the blink rate and color
		super(mapWidth, mapHeight);
		stationColor = ColorUtil.rgb(255, 255, 0);
		blinkToggle = true;
		setBlinkRate();
        setID(uid);
        setColor(stationColor);
	}
		
	public void setBlinkRate() {                                 //method that sets station blink rate
		
		Random gen = new Random();
		blinkRate = 0 + gen.nextInt(10);
		blinkTimer = blinkRate;
	}
	
	public int getBlinkRate() {                                  //method that returns blink rate
		
		return blinkRate;
	}
	
	public void blinkToggle() {                                //method that toggle station to blink
		
		blinkToggle = !blinkToggle;
	}
	
	public boolean getBlinkToggle() {                             //method to return state of blink toggle
		
		return blinkToggle;
	}
	
	public void stationTimeTick() {                                      //this method increments the time for station and determines when to blink
		
		if(blinkRate > 0)
		{
			if(blinkTimer == 1)
			{
				blinkToggle();
				blinkTimer = blinkRate;
			}
			else
			{
				blinkTimer -= 1;
			}		
		}
		
	}
	
	public void setXLoc() {                                           //overriding setXLoc in GameObject, because this cannot move
		
		System.out.println("Space Station cannot change location");
	}
	
	public void setYLoc() {											//overriding setYLoc in GameObject, because this cannot move
		
		System.out.println("Space Station cannot change location");
	}
		
	public String toString() {                                                      // //override, to print information of Space Station object
		
		String s = "";	
		s += "Station: loc=" + getXLoc() + "," + getYLoc() +"color=[" + ColorUtil.red(stationColor) + ",";
		s += ColorUtil.blue(stationColor) + "," + ColorUtil.green(stationColor) + "] rate=" + getBlinkRate();
		return s;
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		
	}
	
}
