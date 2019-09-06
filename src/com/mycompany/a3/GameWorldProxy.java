package com.mycompany.a3;

import com.mycompany.a3.Collection.*;
import com.mycompany.a3.GameObjects.*;

import java.util.Observable;

public class GameWorldProxy extends Observable implements IGameWorld {  //this is a proxy class for GameWorld with limited methods
	
	private GameWorld realGameWorld;
	
	public GameWorldProxy(GameWorld gw) {
		realGameWorld = gw;			                                //holds the real GameWorld for use
	}

	public int getScore() {
		return realGameWorld.getScore();
	}
	
	public int getLives() {
		return realGameWorld.getLives();
	}

	public int getShipMissileCount() {
		return realGameWorld.getShipMissileCount(); 
	}
	
	public int getElapseTime() {
		return realGameWorld.getElapseTime();
	}
	
	public boolean getSound() {		
		return realGameWorld.getSound();
	}
	
	public int getMapWidth() {
		return realGameWorld.getMapWidth();
	}	
	
	public int getMapHeight(){
		return realGameWorld.getMapHeight();
	}
	
	public int getCollectionSize() {
		
		return realGameWorld.getCollectionSize();
	}
	
	
	public GameCollection getGameCollection() {
		
		return realGameWorld.getGameCollection();
	}
	
}
