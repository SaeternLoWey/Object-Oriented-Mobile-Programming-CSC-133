package com.mycompany.a3;

import com.mycompany.a3.Collection.*;
import com.mycompany.a3.GameObjects.*;
import com.mycompany.a3.Sound.*;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;



import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;



public class GameWorld extends Observable implements IGameWorld {          //this is the world of the game
	
	private GameCollection collection;                //declare GameCollection class to hold game objects
	private boolean shipExist;                         //boolean to determine if ship exist in the world
	private int shipMissileCount;                     //amount of missile on the ship
	private int lives;                                 //amount of live the player has
	private int playerScore;                            //amount of score of player
	private int elapseTime;                              //amount of time past from beginning of game
	private static int uidFixedObject;                   //unique id for fix objects
	private boolean sound;                              //boolean to determine sound enabled
	
	private static int mapWidth;                         //map width
	private static int mapHeight; 						//map height

	private Vector<Observer> myObserverList;
	
	//private GameWorldProxy proxy = new GameWorldProxy(this);
	private GameWorldProxy proxy;

		
	
	/* This constructor sets all state values appropriately. This also creates the
	 * collection necessary to hold all game objects. */
	
	public GameWorld() {
		
		myObserverList = new Vector<Observer>();
		collection = new GameCollection();              //create GameCollection class of game objects called collection
		proxy = new GameWorldProxy(this);
		
		
		
		
		
		
		
		
		
		
		
		this.init();	                                //Then initialize the world
		
	}
		
	public void init() {                                //this initializes the game, like a constructor
		 														
		shipExist = false;                                 //ship does not exist in the start
		shipMissileCount = 0;                              //no ship no missiles
		lives = 3;                                         //start player lives
		playerScore = 0;                                   //start player score
		elapseTime = 0;                                     //zero out start time
		uidFixedObject = 0;                                 //start zero unique id
		sound = true;                                      //enable sound on
		updateWorld();
	}
	
	
	public int getScore() {                                  //return player score
		return playerScore;
	}
	
	public int getLives() {                                 //return player lives
		return lives;
	}

	public int getShipMissileCount() {							//return missile count
		return shipMissileCount; 
	}
	
	public int getElapseTime() {                               //return elapse time
		return elapseTime;
	}
	
	//Method to set the sound
	public void setSound (boolean b){
		this.sound = b;
		updateWorld();
	}
	
	//Method to get the sound boolean, true for on, false for off
	public boolean getSound() {		
		return sound;
	}
	
	public void setMapWidth(int width) {                 //set map width
		mapWidth = width;
	}
	public void setMapHeight(int height) {               //set map height
		mapHeight = height;
	}
	
	//Static method to get the width of the map
	public int getMapWidth(){
		return mapWidth;
	}	
	//Static method to get the height of the map
	public int getMapHeight(){
		return mapHeight;
	}
	
	public int getCollectionSize() {               //get collection size
		return collection.getSize();
	}
		
	//Method to get the collection of objects from game world
	public GameCollection getGameCollection() {
		
		return collection;
	}
	
	public void addObserver(Observer o) {          //add observers to observer list
		myObserverList.add(o);
	}
	
	public void notifyObservers(GameWorldProxy proxy) {                        //override the notify observers for proxy setup
	
		for(Observer o : myObserverList) {
			o.update((Observable)this, proxy);
		}
	}
	
	public GameWorldProxy getProxy() {
		return proxy;
	}

	
	//Method to tell the observers that the world has updated, like a macro
	
	public void updateWorld() {
		setChanged();   //Sets the changed flag for this Observable. After calling setChanged(), hasChanged() will return true.
		//proxy = new GameWorldProxy(this);
		notifyObservers(proxy); //Sets the changed flag for this Observable. After calling setChanged(), hasChanged() will return true.
	}
	
	public void addAsteroid() {                             //method to add asteroid to game object collection
		
		collection.add(new Asteroid(mapWidth, mapHeight));
		System.out.println("Asteroid has been added to world");
		updateWorld();
	}

	public void addBlinkingSpaceStation() {                       //method to add blinking space station to game object collection
		
		collection.add(new SpaceStation(uidFixedObject, mapWidth, mapHeight));
		uidFixedObject++;
		SpaceStation theStation = (SpaceStation) collection.get(collection.getSize() - 1);		
		System.out.println("Blink Space Station ID: " + theStation.getID() + 
				", with a blink rate of " + theStation.getBlinkRate() + ", has been added to world");
		updateWorld();
	}
	
	public void addSpaceShip() {                                      //method to add ship to game object collection
		
		if(shipExist == false)                                           //only one ship allow at a time, only make ship if no ship exists
		{
			collection.add(new Ship(mapWidth, mapHeight));
			shipExist = true;
			shipMissileCount = 10;                                        //sets global state variable for missile count
			System.out.println("Space Ship has been added to world");
			updateWorld();
		}
		else
		{
			System.out.println("Cannot have more than one ship in the game");
		}
	}

	public void increaseShipSpeed() {                                         //method to increase ship speed
																								
		boolean shipLocated = false;
		int speed;
		
		if(shipExist == true)
		{
			GameObject findShip;
			IIterator theIterator = collection.getIterator();
			while(theIterator.hasNext() && shipLocated != true)                     //loop to search for ship
	        {   
	        	findShip = (GameObject) theIterator.getNext();
	        	if(findShip instanceof Ship)
	        	{
	        		shipLocated = true;	                                                  //once ship found we downcast and change speed
	        		Ship theShip = (Ship) findShip;
	        		speed = theShip.getSpeed();
	        		System.out.println("the original speed is: " + speed);
	        		if(speed < 10)
	        		{
	        			theShip.setSpeed(speed + 1);
	        			System.out.println("the new speed is: " + theShip.getSpeed());
	        			updateWorld();
	        		}
	        		else
	        		{
	        			System.out.println("Ship is going it's maximum speed");        //ship cannot go faster than 10
	        			//updateWorld();
	        		}
	        	}
  
	        }		
		}
		else
		{
			System.out.println("Ship does not exist");
		}
	}
	
	public void decreaseShipSpeed() { 									//method to decrease ship speed
		
		boolean shipLocated = false;
		int speed;
		
		if(shipExist == true)
		{
			GameObject findShip;
			IIterator theIterator = collection.getIterator();
			while(theIterator.hasNext() && shipLocated != true)          //loop to search for ship
	        {   
	        	findShip = (GameObject) theIterator.getNext();
	        	if(findShip instanceof Ship)
	        	{
	        		shipLocated = true;
	        		Ship theShip = (Ship) findShip;                     //once ship found we downcast and change speed
	        		speed = theShip.getSpeed();
	        		System.out.println("the original speed is: " + speed);
	        		if(speed > 0)
	        		{
	        			theShip.setSpeed(speed - 1);
	        			System.out.println("the new speed is: " + theShip.getSpeed());
	        			updateWorld();
	        		}
	        		else
	        		{
	        			System.out.println("Ship is stopped");						//ship cannot go move in reverse
	        			//updateWorld();
	        		}
	        	}  	
	        }		
		}
		else
		{
			System.out.println("Ship does not exist");
		}
	}
	
	public void turnShipLeft() {                                    //method to turn ship left

		boolean shipLocated = false;
		
		if(shipExist == true)
		{
			GameObject findShip;                                                 //first find ship and then change direction
			IIterator theIterator = collection.getIterator();
			while(theIterator.hasNext() && shipLocated != true)
	        {   
	        	findShip = (GameObject) theIterator.getNext();
	        	if(findShip instanceof Ship)
	        	{
	        		shipLocated = true;	
	        		Ship theShip = (Ship) findShip;
	        		System.out.println("the original dir is: " + theShip.getDir());
	        		theShip.turnLeft(10);        		
	        		System.out.println("the new dir is: " + theShip.getDir());
	        		updateWorld();
	        	}
	        }		
		}
		else
		{
			System.out.println("Ship does not exist");
		}
	}
	
	public void turnShipRight() {                                      //method to turn ship left
		
		boolean shipLocated = false;
		
		if(shipExist == true)
		{
			GameObject findShip;                                                  //first find ship and then change direction
			IIterator theIterator = collection.getIterator();
			while(theIterator.hasNext() && shipLocated != true)
	        {   
	        	findShip = (GameObject) theIterator.getNext();
	        	if(findShip instanceof Ship)
	        	{
	        		shipLocated = true;
	        		Ship theShip = (Ship) findShip;
	        		System.out.println("the original dir is: " + theShip.getDir());
	        		theShip.turnRight(10);	
	        		System.out.println("the new dir is: " + theShip.getDir());
	        		updateWorld();
	        	}
	        }		
		}
		else
		{
			System.out.println("Ship does not exist");
		}	
	}

	public void fireShipMissile() {                                //method to for ship to fire missile
		Sound fireMissile = new Sound("Fire.wav");
		boolean shipLocated = false;
		
		if(shipExist == true)
		{
			GameObject findShip;
			IIterator theIterator = collection.getIterator();
			while(theIterator.hasNext() && shipLocated != true)
	        {   
	        	findShip = (GameObject) theIterator.getNext();
	        	if(findShip instanceof Ship)
	        	{
	        		shipLocated = true;	
	        		Ship theShip = (Ship) findShip;
	        		shipMissileCount = theShip.getMissileCount();                                  //first find ship and to get x, y and direction, then use that to make a missile
	        		if(shipMissileCount == 0)
	        		{
	        			System.out.println("Ship does not have any missiles");
	        		}
	        		else
	        		{
	        			collection.add(new Missile(theShip.getDir(), theShip.getXLoc(), theShip.getYLoc(), mapWidth, mapHeight));
	        			
	        			theShip.setMissileCount(shipMissileCount - 1);
	        			shipMissileCount -= 1;
		        			        			
		        		int missileIndex = collection.getSize() - 1;
		        		
		        		Missile theMissile = (Missile) collection.get(missileIndex);
		        		
		        		System.out.println("A missile has been fired and it's direction is: " + theMissile.getDir());
		        		
		        		//fireMissile.play();
		        		
		        		updateWorld();
		        		
	        		}		
	        	}
	        }		
		}
		else
		{
			System.out.println("Ship does not exist");
		}
	}

	public void shipJumpHyperspace() {                            //method to bring back ship to center of map

		boolean shipLocated = false;
		
		if(shipExist == true)
		{
			GameObject findShip;
			IIterator theIterator = collection.getIterator();
			while(theIterator.hasNext() && shipLocated != true)
	        {   
	        	findShip = (GameObject) theIterator.getNext();
	        	if(findShip instanceof Ship)
	        	{
	        		shipLocated = true;	                                     //first find ship and change its location
	        		Ship theShip = (Ship) findShip;
	        		
	        		System.out.println("the ship original x is: " + theShip.getXLoc());
	        		System.out.println("the ship original y is: " + theShip.getYLoc());
	        		
	        		theShip.setXLoc(512);
	        		theShip.setYLoc(384);
	        		
	        		System.out.println("the ship x is: " + theShip.getXLoc());        		
	        		System.out.println("the ship y is: " + theShip.getYLoc());
	        		updateWorld();
	        	}
	        }		
		}
		else
		{
			System.out.println("Ship does not exist");
		}
	}

	public void newMissileSupply() {                                    //method to reload ship missiles to max
		
		boolean shipLocated = false;
		
		if(shipExist == true)
		{
			GameObject findShip;
			IIterator theIterator = collection.getIterator();
			while(theIterator.hasNext() && shipLocated != true)
	        {   
	        	findShip = (GameObject) theIterator.getNext();                   //find ship then downcast to change it's missile count
	        	if(findShip instanceof Ship)
	        	{
	        		shipLocated = true;	
	        		Ship theShip = (Ship) findShip;
	        		
	        		System.out.println("the ship missile count is: " + theShip.getMissileCount());
	        		
	        		theShip.setMissileCount(10);
	        		shipMissileCount = 10;
	        		
	        		System.out.println("the ship missile count is: " + theShip.getMissileCount());
	        		updateWorld();
	        	}
	        }		
		}
		else
		{
			System.out.println("Ship does not exist");
		}
	}
	
	public void killedAsteroid() {                           //method for ship to kill an asteroid
		
		GameObject findObject;
        boolean objectsFound = false;
        boolean objectsDestroyed = false;
        int missileIndex = 0;
        int asteroidIndex = 0;
        
        IIterator theIterator = collection.getIterator();
        while(objectsFound != true && theIterator.hasNext())
        {   
        	findObject = (GameObject) theIterator.getNext();
        	if(findObject instanceof Missile)
        	{																			//find two asteroids to destroy, if the don't exist then don't destroy		
        		missileIndex = theIterator.getIndex();
        		System.out.println("the missile index is: " + missileIndex);
        		
        		theIterator = collection.getIterator();
        		while(objectsFound != true && theIterator.hasNext())
        	    {
        			findObject = (GameObject) theIterator.getNext();
          			if(findObject instanceof Asteroid)
        			{
        				asteroidIndex = theIterator.getIndex();       				
        				System.out.println("the asteroid index is: " + asteroidIndex);
        				objectsFound = true;
        			}
        	    }
        	}
        }
        
        theIterator = collection.getIterator();
        while(objectsFound == true && objectsDestroyed != true && theIterator.hasNext())
        {   
        	findObject = (GameObject) theIterator.getNext();
        	if(findObject instanceof Missile)
        	{																			//find two asteroids to destroy, if the don't exist then don't destroy		
        		missileIndex = theIterator.getIndex();
        		System.out.println("the missile index is: " + missileIndex);
        		theIterator.removeIt();
        		  		
        		theIterator = collection.getIterator();
        		while(objectsDestroyed != true && theIterator.hasNext())
        	    {
        			findObject = (GameObject) theIterator.getNext();
          			if(findObject instanceof Asteroid)
        			{
        				asteroidIndex = theIterator.getIndex();
        				System.out.println("the asteroid index is: " + asteroidIndex);
        			    theIterator.removeIt();
        			    playerScore += 100;
        			    System.out.println("An asteroid has been destroyed");
        			    updateWorld();
        			    objectsDestroyed = true;
        			}
        	    }
        	}	
        }
        if(objectsFound == false)
        {
        	System.out.println("Cannot find a missile and asteroid pair");
        }
	}
      
	public void shipCrashed() {                                                //method for ship to crash into asteroid and player losses life

		GameObject findObjects;			
	    boolean objectsFound = false;
	    boolean objectsDestroyed = false;

        IIterator theIterator = collection.getIterator();
        while(objectsFound != true && theIterator.hasNext())
	    {   
	       	findObjects = (GameObject) theIterator.getNext();
	       	if(findObjects instanceof Ship)
	       	{        			
        		theIterator = collection.getIterator();
	        	while(objectsFound != true && theIterator.hasNext())
	       	    {
	       			findObjects = (GameObject) theIterator.getNext();
	        		if(findObjects instanceof Asteroid)
	       			{
	        			objectsFound = true;
	       			}
	       	    }
	       	}
	    }       	
        theIterator = collection.getIterator();
	    while(objectsFound == true && objectsDestroyed != true && theIterator.hasNext())
		{   
		    findObjects = (GameObject) theIterator.getNext();
		    if(findObjects instanceof Ship)
		    {
	           	theIterator.removeIt();
	           		
	        	theIterator = collection.getIterator();
		        while(objectsDestroyed != true && theIterator.hasNext())
		       	{
		       	    findObjects = (GameObject) theIterator.getNext();
		        	if(findObjects instanceof Asteroid)
		       		{
		        	    theIterator.removeIt();		        		
		        		shipExist = false;
		        		shipMissileCount = 0;
		                System.out.println("Your ship has been destroyed");
		                lives = lives - 1;
		        		System.out.println("You have " + lives + " left.");
		        		if (lives == 0)
		        		{
		        			System.out.println("Game Over");
		        			System.exit(0);
		        		}
		        		updateWorld();
		        		objectsDestroyed = true;
		       		}
		       	}
		    }     	
	    }    
        if(objectsFound == false)
        {
        	System.out.println("Cannot find a ship and asteroid pair");
        }
	}		

	public void twoAsteriodsCollided() {                                             //method to destroy 2 asteroids that crashed into each other
		GameObject findAsteroid;
        int asteroidsFound = 0;
        int asteroidsDestroyed = 0;
        
        IIterator theIterator = collection.getIterator();
        while(asteroidsFound < 2 && theIterator.hasNext())
        {   
        	findAsteroid = (GameObject) theIterator.getNext();                              //find if two asteroids exist
        	if(findAsteroid instanceof Asteroid)
        	{
        			asteroidsFound += 1;       			
        	}
        }
        theIterator = collection.getIterator();
        while(asteroidsFound == 2 && asteroidsDestroyed < 2 && theIterator.hasNext())
        {   
        	findAsteroid = (GameObject) theIterator.getNext();                              //find if two asteroids exist
        	if(findAsteroid instanceof Asteroid)
        	{
        			theIterator.removeIt();
        			asteroidsDestroyed += 1;
        	}
        }
        if(asteroidsFound < 2)
        {
        	System.out.println("Two asteroids do not exist in the world");
        }
        else
        {
        	System.out.println("Two asteroids have collided and are destroyed");
        	updateWorld();
        }       
	}

	public void gameClockHasTicked() {                                     //method to increment game clock one tick
				
		elapseTime += 1;
		GameObject searchObjects;
		
		System.out.println("World has incremented 1 time unit");
		
		IIterator theIterator = collection.getIterator();
		while(theIterator.hasNext())
	    {   
	       	searchObjects = (GameObject) theIterator.getNext();
	       	if(searchObjects instanceof MoveableObject)
	       	{
	       		MoveableObject moveObject = (MoveableObject) searchObjects;    //makes all moveable objects move
	       		moveObject.move();
	       		if(moveObject instanceof Missile)
	       		{
	       			Missile mis = (Missile) moveObject;
	       			if(mis.getFuelLevel() == 0)
	       			{
	       				theIterator.removeIt();
	       			}				
	       		}
	       	}
	    }
		
		theIterator = collection.getIterator();
		while(theIterator.hasNext())
		{
			searchObjects = (GameObject) theIterator.getNext();
			if(searchObjects instanceof SpaceStation)
			{
				SpaceStation ss = (SpaceStation) searchObjects;
				ss.stationTimeTick();
				if(ss.getBlinkToggle() == true)
				{
					System.out.println("Space station with ID: " + ss.getID() + " is visible");
				}
				else
				{
					System.out.println("Space station with ID: " + ss.getID() + " is not visible");
				}	
			}
		}
		updateWorld();
	}
	
	public void printDisplay() {                                                //method to display state values of the game, score, missile count, elapse time
		System.out.println("Player Score: " + playerScore);
		System.out.println("Ship Missile Count: " + shipMissileCount);
		System.out.println("Elapse Time: " + elapseTime);
	
	}

	public void printMap() {                                                //method to display information on all objects in the game world
				
		IIterator theIterator = collection.getIterator();
		while(theIterator.hasNext())
		{
			GameObject obj = (GameObject) theIterator.getNext();
			System.out.println(obj);
		}
	}

	public void quitGame() {	                                        //method to quit the game, not used in AS2
		System.exit(0);
	} 
}
