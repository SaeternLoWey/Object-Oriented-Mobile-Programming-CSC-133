package com.mycompany.a3.Views;

import com.mycompany.a3.*;
import com.mycompany.a3.Collection.*;
import java.util.Observer;
import java.util.Observable;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.charts.util.ColorUtil;

import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;


public class PointsView extends Container implements Observer{                    //Class to show game state value on display
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ F I E L D S ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	private CLabel score;
	private CLabel lives;
	private CLabel missileCount;
	private CLabel time;
	private CLabel sound;
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~ C O N S T R U C T O R S ~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	
	/* This is the default constructor. This will create instances of all labels and
	 * then add them to the points view in a simple flow layout.
	 */
	public PointsView(){
		this.setLayout(new FlowLayout(Component.CENTER));
		this.getAllStyles().setBgColor(ColorUtil.WHITE);
		this.getAllStyles().setBgTransparency(255);
		
		//Instantiate all necessary labels
		score =  new CLabel();
		lives =  new CLabel();
		missileCount =  new CLabel();
		time =  new CLabel();
		sound = new CLabel();
		
		//Then add all components to the container
		this.add(new CLabel("Score: "));
		this.add(score);
		this.add(new CLabel("Lives: "));
		this.add(lives);
		this.add(new CLabel("Missiles: "));
		this.add(missileCount);
		this.add(new CLabel("Sound: "));
		this.add(sound);
		this.add(new CLabel("Time: "));
		this.add(time);

	
	}
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ M E T H O D S ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	
	//This will update labels to the screen when needed
	public void update( Observable o, Object gwp ){
		//Cast the Observable objects as the GameWorld first to access variables
		
		//GameWorld gw = (GameWorld)o;   //we don't use this anymore be we are using proxy

		score.setText(Integer.toString(((IGameWorld) gwp).getScore()));
		lives.setText(Integer.toString(((IGameWorld) gwp).getLives()));
		missileCount.setText(Integer.toString(((IGameWorld) gwp).getShipMissileCount()));
		time.setText(Integer.toString(((IGameWorld) gwp).getElapseTime()));
		if (((IGameWorld) gwp).getSound() == true){
			sound.setText( "ON" );
		}
		else{
			sound.setText( "OFF" );
		}
		//repaint();
	}
}
