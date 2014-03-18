package game.engine;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.applet.*;
import java.net.*;

public class GameModel{
	
	int missAm; //Used to hold the current amount of missiles fired
	int width; //Sets the width 
	int height; //Sets the height
	int score; //Sets the score
	int destroyed; //Used to hold the current amount of asteroids destroyed
	int lives; //Sets the lives 
	int level; //Sets the level 
	Asteroids ast; //Creates an asteroid frame that is used to display the GUI
	ArrayList<Asteroid> theAsts; //Holds all of the games asteroids that are displayed
	ArrayList<Missile> miss; //Holds all of the fired missiles
	ArrayList<Explosion> exp; //Holds all of the explosions of asteroids
	Ship destroyer; //Holds the ship of the game
	int astAm; //Variable used to show how many asteroids there currently are
	boolean over; //Variable used to indicate whether the game has finished or not
	int buffer; //Variable used as a buffer so that the user may escape oncoming asteroids at the start
	boolean collided; //Sets whether the ship has collided with an asteroid or not
	Sound sounds; //Sound files used in the game
	ShipExplosion blown; //This is used for when the ship collides with an asteroid
	EMP bomb; //This is kind of like a force field/bomb thing
	int empAm; //Holds the amount of emps there are
	
	//Resets any attributes concerning a new game
	public void gameOver(){
		over = true;
		lives =4;
		theAsts = new ArrayList<Asteroid>();
		miss = new ArrayList<Missile>();
		destroyer = new Ship(this);
		destroyed=0;
		astAm=0;
		empAm=0;


		
		for (int i=0;i<4;i++){
			theAsts.add(new Asteroid());
			astAm++;
		}
		
	}
	
	//Resets the score and level for when the game is restarted, and sets it so that the
	//new game screen is displayed by setting over to false
	public void gameReset(){
		level =1;
		score=0;
		over=false;
		
		
	}


	public GameModel(){
		
		
		empAm=0;
		//Creates a new sound object for game sounds
		sounds = new Sound();
		
		//Sets a variable to be used to indicate whether the game is over or not
		over = true;
		
		//Variable used to keep count of destroyed asteroids
		destroyed=0;
		
		//Variable used to indicate whether the ship has collided or not
		collided=false;
		
		//Sets the new ship buffer to 0 so it has time to move out of incoming asteroids ways
		buffer=0;
		
		
		//Sets the default values of the GameModel's attributes
		width = 800;
		height = 600;
		score = 0;
		lives = 4;
		level =1 ;
		missAm=0;//Missile amount
		
		//Sets the missiles and explosions to have a new arraylist
		miss = new ArrayList<Missile>();
		exp = new ArrayList<Explosion>();
		
		//Creates a new ship
		destroyer = new Ship(this);
		
		//Sets the bomb to be null
		bomb = null;
		
		//Creates new asteroid arraylist
		theAsts = new ArrayList<Asteroid>();
		
		//Creates a new asteroids frame
		ast = new Asteroids("Asteroids",this);
		
		//Adds new asteroids to the game
		for (int i=0; i<level+3;i++){
			theAsts.add(new Asteroid());
			astAm++;
		}
		
		
		//Plays background music
		try{
			AudioClip clip =  sounds.clips.get(4);
			clip.loop();
		}catch (Exception e){
			System.out.println("Could not find audio clip");
		}	
		
	}
	

	

	//Method used to fire the missiles
	public void fireMissile(){
	
		//Arraylist used to hold any missiles that have travelled enough
		ArrayList<Missile> removal = new ArrayList<Missile>();
		
		//Checks to see if the missiles lifetime is up, and adds it to an arraylist for removal if it has
		if (!(miss==null)){
			for (Missile m: miss){
				if (m.travelled<m.lifetime){
					
				}else{
					//Adds a removed missile to an arraylist, and decreases the missile count
					removal.add(m);
					missAm-=1;
				}
				
			}	
		}
	 	
		
		
		//Removes all missiles that are now dead
		miss.removeAll(removal);
		
		//Updates the look of all missiles 
		if (!(miss ==null)){
			for (Missile m: miss){
				if (!(m==null))
					m.update(ast.buffered);
			}
		}
		
		
	}
	
	public void explode(){
		
		//Used to hold any explosions that are now finished
		ArrayList<Explosion> removal = new ArrayList<Explosion>();
		
		//Checks each explosion to see if it's finished, and if it is it adds it to an arraylist
		for (Explosion e: exp){
			
			if (e.gone){
				removal.add(e);
			}else{
				e.update(ast.buffered);
			}
			
		}
		
		//Removes all finished explosions from the main arraylist of them
		exp.removeAll(removal);
		
	}
	
	public void shipExploded(){
		if (!(blown==null)){
			if (blown.gone){
				blown=null;
			}
		}
		
	}
	
	//Temporary method used to update the interface
	public void update(){
		
		while (ast.getGraphics()==null){
		}
		
		//Updates the look of any current asteroid explosions 
		explode();
		
		//Checks to see whether the ships explosion is gone or not
		shipExploded();
		
		//Displays the force field / bomb thing if there is one
		if (!(bomb==null)){
			bomb.update(ast.buffered);
			if (bomb.over){
				bomb = null;
			}
		}
		
		//Creates two new arraylist that are used to hold destroyed asteroids and missiles
		ArrayList<Asteroid> removal = new ArrayList<Asteroid>();
		ArrayList<Missile> removalM = new ArrayList<Missile>();
	
		//Checks to see if any of the missiles have collided with the asteroids, and also draws the asteroids
		for (Asteroid c: theAsts){
		
			c.update(ast.buffered);
			
			
			
			
			if (!(miss==null)){
				for (Missile m: miss){
					if (c.collidesWith(m)){
						
						sounds.currentClip= sounds.clips.get(2);
						sounds.play();	
						
						destroyed++;
						
						//Creates a new explosion
						exp.add(new Explosion(c,this));
						
						//Removes the missile and asteroid
						removal.add(c);
						removalM.add(m);
						missAm-=1;
						
						//Checks to see what size it was and updates the score 
						if (c.size==1){
							score+=20;
						}else if(c.size==0.5){
							score+=50;
						}else{
							score+=100;
						}
					}
					
				}
			}
			
		}
		
		//Checks the removal variable that holds destroyed asteroids, any depending on the size it creates new asteroids
		for (Asteroid c: removal){
			if (c.size==1){
				theAsts.add(new Asteroid(0.5,c.tempAngle+180,c.color,c.location));
				theAsts.add(new Asteroid(0.5,c.tempAngle,c.color,new Point(c.location.x+10,c.location.y+10)));
				astAm+=2;	
			}else if(c.size==0.5){
				theAsts.add(new Asteroid(0.25,c.tempAngle+90,c.color,c.location));
				theAsts.add(new Asteroid(0.25,c.tempAngle-90,c.color,new Point(c.location.x+5,c.location.y+5)));
				astAm+=2;
			}	
		
		}
		
		//Removes any asteroids that have been destroyed 
		theAsts.removeAll(removal);
		
		removal = new ArrayList<Asteroid>();
		
		if (!(bomb==null)){
			for (Asteroid c: theAsts){
		
			c.update(ast.buffered);
			
					if (c.collidesWith(bomb)){
						
						sounds.currentClip= sounds.clips.get(2);
						sounds.play();	
						
						destroyed++;
						
						//Creates a new explosion
						exp.add(new Explosion(c,this));
						
						//Removes the asteroid
						removal.add(c);
						
						//Checks to see what size it was and updates the score 
						if (c.size==1){
							score+=20;
						}else if(c.size==0.5){
							score+=50;
						}else{
							score+=100;
						}
					}
			}
		}
		
		
	
		
		//Checks the removal variable that holds destroyed asteroids, any depending on the size it creates new asteroids
		for (Asteroid c: removal){
			if (c.size==1){
				theAsts.add(new Asteroid(0.5,c.tempAngle+180,c.color,c.location));
				theAsts.add(new Asteroid(0.5,c.tempAngle,c.color,new Point(c.location.x+10,c.location.y+10)));
				astAm+=2;	
			}else if(c.size==0.5){
				theAsts.add(new Asteroid(0.25,c.tempAngle+90,c.color,c.location));
				theAsts.add(new Asteroid(0.25,c.tempAngle-90,c.color,new Point(c.location.x+5,c.location.y+5)));
				astAm+=2;
			}	
		
		}
		
		
		//Checks to see if any of the asteroids have collided with the ship 
		for (Asteroid c: theAsts){
			
			if(c.collidesWith(destroyer) && buffer>200 &&(!collided)){
				
				//Plays a sound if the ship has collided
				sounds.currentClip= sounds.clips.get(3);
				sounds.play();	
					
				collided=true;
					
			}
		}
		
		//Sets it so that if the ship has collided a life is lost, a buffer is set, and a new ship is placed
		if (collided){
			lives--;
			buffer=0;
			blown = new ShipExplosion(this);
			destroyer = new Ship(this);
			
		}
		
		//Checks to see so that if the ship has blown up it displays the explosion
		if (!(blown==null)){
			blown.update(ast.buffered);
		}
		
		//Resets the collision boolean
		collided=false;
		
		//Removes any missiles and asteroids that are now gone
		miss.removeAll(removalM);
		theAsts.removeAll(removal);
	
		//Calls a method that checks whether any missiles have been fired and fires them and updates them
		fireMissile();
		
		//Updates the ships look and position
		destroyer.update(ast.buffered);
	
		
		//Sets it so that if all of the asteroids are destroyed it updates the current level
		//and also creates the asteroids according to the level
			if (theAsts.isEmpty()){
				bomb = null;
				miss = new ArrayList<Missile>();
				missAm=0;
				destroyer = new Ship(this);
				buffer=0;
				score+=100;
				level++;
				for (int i=0;i<(3+level);i++){
					theAsts.add(new Asteroid());
				}
			}
		
	}
	
	
	public static void main(String args[]){
		//Space panel contains a main to test its display
		
		new GameModel();
		
		
	}
	
}