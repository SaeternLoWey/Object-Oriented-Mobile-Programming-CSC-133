package com.mycompany.a3.GameObjects;

import java.util.Random;

public abstract class GameObject implements IDrawable{
	private float x, y;		             //values for location of game object
	private int color;		             //color variable for game object
	
	
	public GameObject() {
		
	}
	
	public GameObject(int mapWidth, int mapHeight) {                //game object constructor to randomly set location
		startXLoc(mapWidth);
		startYLoc(mapHeight);
	}
	
	public void startXLoc(int mapWidth) {               //method to randomly select x value		
				
		Random gen = new Random();
		x = 0 + gen.nextInt(mapWidth);		
	}
	
	public void startYLoc(int mapHeight) {              //method to randomly select y value
						
		Random gen = new Random();
		y = 0 + gen.nextInt(mapHeight);
	}
	
	public void setXLoc(float newX)	{        //method to change x value
		
		x = newX;
	}
	
	public void setYLoc(float newY)	{          //method to change y value
		
		y = newY;
	}
	
	public float getXLoc() {                 //method to get x value
		
		return x;
	}
	
	public float getYLoc() {                   //method to get y value
		
		return y;
	}
	
	public void setColor(int newColor) {          //method to set color of game object
		
		color = newColor;
	}
	
	public int getColor() {                        //method to get color of game object
		
		return color;
	}	
}
