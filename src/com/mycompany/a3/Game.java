package com.mycompany.a3;

import com.mycompany.a3.Commands.*;
import com.mycompany.a3.Views.*;

import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
//   
public class Game extends Form  implements Runnable{
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ F I E L D S ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	
	private GameWorld gw;	//Create a new gameworld
	private MapView mv;	//My two views
	private PointsView pv;
	private UITimer timer;

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~ C O N S T R U C T O R S ~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	
	/* This constructor is the default constructor. It instantiates a GameWorld object,
	 * creates all the necessary buttons and their placements, and then calls init() in
	 * GameWorld to build the world which events will take place. */
	
	public Game(){
		
		timer = new UITimer(this);
		timer.schedule(20, true, this);
		
		this.setLayout( new BorderLayout() );	//Set main layout to Border

		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ NORTH Container ~~~~~~~~~~~~~~~~~~~~~~~~~~~//
		//commented out, points view class handles the layout
		//Container northContainer = new Container();
		//northContainer.setLayout( new FlowLayout() );
		//northContainer = FlowLayout.encloseCenter( sv );	//Places everything in center
		//Then attach to the NORTH border
		
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ WEST Container ~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
		Container westContainer = new Container();
		//westContainer.setLayout( new FlowLayout() );	//Set layout
		
		//Add the new buttons that will be on the west border
		Button addAsteroid = new CButton ( "Add Asteroid" );
		Button addSpaceShip = new CButton ( "Add SpaceShip" );
		Button addStation = new CButton ( "Add Station" );
		Button addDecreaseShipSpeed = new CButton ( "Decrease Ship Speed" );
		Button addExit = new CButton ( "Exit" );
		Button addFireShipMissile = new CButton ( "Fire Ship Missile" );
		Button addIncreaseShipSpeed = new CButton ( "Increase Ship Speed" );
		Button addKilledAsteroid = new CButton ( "Killed Asteroid" );
		Button addNewMissileSupply = new CButton ( "New Missile Supply" );
		Button addShipCrashed = new CButton ( "Ship Crashed" );
		Button addShipJumpHyperspace = new CButton ( "Ship Jump Hyperspace" );
		Button addSoundCheck = new CButton ( "Sound Check" );
		Button addTickClock = new CButton ( "Tick Clock" );
		Button addTurnShipLeft = new CButton ( "Turn Ship Left" );
		Button addTurnShipRight = new CButton ( "Turn Ship Right" );
		Button addTwoAsteriodsCollided = new CButton ( "TwoAsteriodsCollided" );
						
		//Then add all the buttons to a temporary container
		//Container westContainer = new Container();
		westContainer.setLayout( new BoxLayout( BoxLayout.Y_AXIS) );
		
		westContainer.add( addAsteroid );
		westContainer.add( addSpaceShip );
		westContainer.add( addStation );
		westContainer.add( addDecreaseShipSpeed );
		westContainer.add( addExit );
		westContainer.add( addFireShipMissile );
		westContainer.add( addIncreaseShipSpeed );
		westContainer.add( addKilledAsteroid );
		westContainer.add( addNewMissileSupply );
		westContainer.add( addShipCrashed );
		westContainer.add( addShipJumpHyperspace );
		//westContainer.add( addSoundCheck );
		westContainer.add( addTickClock );
		westContainer.add( addTurnShipLeft );
		westContainer.add( addTurnShipRight );
		westContainer.add( addTwoAsteriodsCollided );
		
		//Then attach to the WEST border
		westContainer = FlowLayout.encloseCenterMiddle( westContainer );
		westContainer.getAllStyles().setBorder( Border.createLineBorder
				( 4, ColorUtil.rgb( 0, 0, 0 ) ) );
		this.add( BorderLayout.WEST, westContainer );
		
		
		
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ CENTER Container ~~~~~~~~~~~~~~~~~~~~~~~~~~~//
		//Attach the map to what's left in the CENTER border
		//Container centerContainer = new Container();
		//centerContainer.getAllStyles().setBorder(
		//Border.createLineBorder( 4, ColorUtil.rgb(0, 0, 0) ) );
		//this.add( BorderLayout.CENTER, centerContainer );
		
		//Then initialize a game world at the end and attach the observers
		gw = new GameWorld();
		
		//Instantiate the observers
		mv = new MapView(gw.getProxy());
		pv = new PointsView();
		
		gw.addObserver(mv);
		gw.addObserver(pv);
		
		
		this.add( BorderLayout.CENTER, mv );
		
		
		
		//centerContainer.add(mv);
		this.add( BorderLayout.NORTH, pv );
				
		//Then show it all at the end
		this.show();

		gw.setMapWidth(mv.getWidth());
		gw.setMapHeight(mv.getHeight());
		System.out.println("Width of centerContainer: " + mv.getWidth() + 
				" Height of centerContainer: " + mv.getHeight());
		
		
		//~~~~~~~~~~~~~~~~~~~~~~ ALL COMMANDS BELOW ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
		
		//Declare all the needed commands for the buttons, keys, and side bar
		AboutCommand myAbout = new AboutCommand(gw);
		AddAsteroidCommand myAddAsteroid = new AddAsteroidCommand(gw);
		AddSpaceShipCommand myAddSpaceShip = new AddSpaceShipCommand(gw);
		AddStationCommand myAddStation = new AddStationCommand(gw);
		DecreaseShipSpeedCommand myDecreaseShipSpeed = new DecreaseShipSpeedCommand(gw);
		ExitCommand myExit = new ExitCommand();
		FireShipMissileCommand myFireShipMissile = new FireShipMissileCommand(gw);
		IncreaseShipSpeedCommand myIncreaseShipSpeed = new IncreaseShipSpeedCommand(gw);
		KilledAsteroidCommand myKilledAsteroid = new KilledAsteroidCommand(gw);
		NewMissileSupplyCommand myNewMissileSupply = new NewMissileSupplyCommand(gw);
		ShipCrashedCommand myShipCrashed = new ShipCrashedCommand(gw);
		ShipJumpHyperspaceCommand myShipJumpHyperspace = new ShipJumpHyperspaceCommand(gw);
		SoundCheckCommand mySoundCheck = new SoundCheckCommand(gw);
		TickClockCommand myTickClock = new TickClockCommand(gw);
		TurnShipLeftCommand myTurnShipLeft = new TurnShipLeftCommand(gw);
		TurnShipRightCommand myTurnShipRight = new TurnShipRightCommand(gw);
		TwoAsteriodsCollidedCommand myTwoAsteriodsCollided = new TwoAsteriodsCollidedCommand(gw);
		
		
		//assign command to button
		addAsteroid.setCommand( myAddAsteroid );
		addSpaceShip.setCommand( myAddSpaceShip );
		addStation.setCommand( myAddStation );
		addDecreaseShipSpeed.setCommand( myDecreaseShipSpeed );
		addExit.setCommand( myExit );
		addFireShipMissile.setCommand( myFireShipMissile );
		addIncreaseShipSpeed.setCommand( myIncreaseShipSpeed );
		addKilledAsteroid.setCommand( myKilledAsteroid );
		addNewMissileSupply.setCommand( myNewMissileSupply );
		addShipCrashed.setCommand( myShipCrashed );
		addShipJumpHyperspace.setCommand( myShipJumpHyperspace );
		addSoundCheck.setCommand( mySoundCheck );
		addTickClock.setCommand( myTickClock );
		addTurnShipLeft.setCommand( myTurnShipLeft );
		addTurnShipRight.setCommand( myTurnShipRight );
		addTwoAsteriodsCollided.setCommand( myTwoAsteriodsCollided );
		
		//Then the commands to the keys
		addKeyListener( 'a', myAddAsteroid );
		addKeyListener( 's', myAddSpaceShip );
		addKeyListener( 'b', myAddStation );
		addKeyListener( 'd', myDecreaseShipSpeed );
		addKeyListener( 'q', myExit );
		addKeyListener( 'f', myFireShipMissile );
		addKeyListener( 'i', myIncreaseShipSpeed );
		addKeyListener( 'k', myKilledAsteroid );
		addKeyListener( 'n', myNewMissileSupply );
		addKeyListener( 'c', myShipCrashed );
		addKeyListener( 'j', myShipJumpHyperspace );
	 	addKeyListener( 'd', mySoundCheck );
		addKeyListener( 't', myTickClock );
		addKeyListener( 'l', myTurnShipLeft );
		addKeyListener( 'r', myTurnShipRight );
		addKeyListener( 'x', myTwoAsteriodsCollided );
		
		
		
		
		
		
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Natural Title Bar ~~~~~~~~~~~~~~~~~~~~~~~~~~~//
		//Create a new toolbar to act as the title bar
		Toolbar tb = new Toolbar();
		setToolbar(tb); 	//Set the toolbar to this form
		//First create the title
		tb.setTitle( "Asteroid Game" );
		tb.setTitleCentered( true ); //Center it
			
		
		//create button for menu
		CButton newGame = new CButton("New");
		CButton save = new CButton("Save");	
		CButton undo = new CButton("Undo");
		CButton about = new CButton("About");
		CButton quit = new CButton("Quit");
		
		CheckBox soundCheck = new CheckBox();	
		soundCheck.getAllStyles().setBgTransparency( 255 );
		soundCheck.getAllStyles().setBgColor( ColorUtil.rgb( 150, 150, 150 ) ); //Mild Gray
		soundCheck.setText( "Turn Sound OFF / ON" );
		
		//create set commands to buttons
		soundCheck.setCommand(mySoundCheck);
		about.setCommand(myAbout);
		quit.setCommand(myExit);
		
		//add button to the menu
		tb.addComponentToSideMenu(newGame);
		tb.addComponentToSideMenu(save);
		tb.addComponentToSideMenu(undo);
		tb.addComponentToSideMenu(soundCheck);
		tb.addComponentToSideMenu(about);
		tb.addComponentToSideMenu(quit);
		
		tb.addCommandToOverflowMenu(myAddAsteroid);
		tb.addCommandToOverflowMenu(myAddSpaceShip);
		tb.addCommandToOverflowMenu(myAddStation);
		//tb.addCommandToOverflowMenu(myDecreaseShipSpeed);
		tb.addCommandToOverflowMenu(myFireShipMissile);
		//tb.addCommandToOverflowMenu(myIncreaseShipSpeed);
		tb.addCommandToOverflowMenu(myKilledAsteroid);
		tb.addCommandToOverflowMenu(myNewMissileSupply);
		tb.addCommandToOverflowMenu(myShipCrashed);
		//tb.addCommandToOverflowMenu(myShipJumpHyperspace);
		tb.addCommandToOverflowMenu(myTickClock);
		//tb.addCommandToOverflowMenu(myTurnShipLeft);
		//tb.addCommandToOverflowMenu(myTurnShipRight);
		tb.addCommandToOverflowMenu(myTwoAsteriodsCollided);
		

			
		
		//At the very end update the world
		gw.updateWorld();
	}

	
	public void run() {
		// TODO Auto-generated method stub
		gw.gameClockHasTicked();
		
	}
	
	
}
