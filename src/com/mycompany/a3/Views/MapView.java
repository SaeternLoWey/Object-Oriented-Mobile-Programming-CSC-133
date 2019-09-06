package com.mycompany.a3.Views;

import com.mycompany.a3.GameObjects.*;
import com.mycompany.a3.*;
import com.mycompany.a3.Collection.*;
import java.util.Observer;
import java.util.Observable;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class MapView extends Container implements Observer{            //Observer Class to display map, no graphics yet
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ F I E L D S ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	GameWorldProxy gwp;
	GameCollection gcp;
	GameObject obj;

	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~ C O N S T R U C T O R S ~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	
	/* This is the default constructor.
	 */
	public MapView(GameWorldProxy gwp){
		
		this.gwp = gwp;
		gcp = gwp.getGameCollection();
	}
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ M E T H O D S ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	
	public void paint(Graphics g) {
		super.paint(g);
		Point pCmpRelPrnt = new Point(getX(), getY());
		
		System.out.println("paint running!");
		
		IIterator paintGameIterator = gcp.getIterator();
		while ( paintGameIterator.hasNext() ) {
		obj = (GameObject) paintGameIterator.getNext();
		obj.draw(g, pCmpRelPrnt);
		
		} 

	}
	
	
	//This will update the map eventually. For now it displays all the game objects
	public void update(Observable o, Object gwp ){
		this.repaint();
		System.out.println("update running!");
		
		//Cast the Observable objects as the GameWorld first to access variables		
		//GameWorld gw = (GameWorld)o;  //we don't use this anymore be we are using proxy
		
		System.out.println( "Map Width: " + ((IGameWorld) gwp).getMapWidth() + "  Map Height: "
				+ ((IGameWorld) gwp).getMapHeight());
		
		gcp = ((IGameWorld) gwp).getGameCollection();
		IIterator consoleGameIterator = gcp.getIterator();
		while ( consoleGameIterator.hasNext() ){
			System.out.println( consoleGameIterator.getNext() );
		}
	}
	
}
