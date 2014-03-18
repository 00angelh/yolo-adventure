package game.engine;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class ShipBit{
	
	int lifetime;
	Point direction;
	Point location;
	int travelled;
	int rotation; //How it rotates
	int currentRot; //Current location at where its rotating
	
	//Sets all the attributes
	public ShipBit(GameModel model){
		currentRot=0;
		
		rotation = (int)(Math.random()*360+1);
		lifetime = (int)(Math.random()*100+50);
		
		double tempAngle = (Math.random()*360+1);
		direction = new Point((int)(Math.cos(Math.toRadians(tempAngle))*2),(int)(Math.sin(Math.toRadians(tempAngle))*2));
		
		int randomFactor;
		randomFactor = (int)(Math.random()*5+1);
		direction.x*=randomFactor;
		randomFactor = (int)(Math.random()*5+1);
		direction.y*=randomFactor;
		
		location = model.destroyer.location;
	}
	
	//Updates where the bit is currently at
	public void update(){
		
		if (location.x>800){
			location.x=0;
		}
		if (location.x<0){
			location.x=800;
		}
			
		if (location.y>600){
			location.y=0;
		}
		if (location.y<0){
			location.y=600;
		}
			
		currentRot+=rotation;
		
		if (!(travelled>lifetime)){
			location = (new Point(location.x+direction.x,location.y+direction.y));
			travelled += Math.sqrt(Math.pow(direction.x,2)+ Math.pow(direction.y,2));
		}
		
	}
}