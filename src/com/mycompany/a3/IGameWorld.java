package com.mycompany.a3;

import com.mycompany.a3.Collection.GameCollection;

public interface IGameWorld { 				//Interface for IGameWorld
	
	public int getScore();
	public int getLives();
	public int getShipMissileCount();
	public int getElapseTime();
	public boolean getSound();
	public int getMapWidth();
	public int getMapHeight();
	public int getCollectionSize();
	public GameCollection getGameCollection();
}
