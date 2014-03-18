package game.engine;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;


public class ExplosionBit{
	
	int lifetime;
	Point direction;
	Point location;
	Color color;
	int travelled;
	int size;
	
	//Sets it so that each bit has a seperate lifetime and direction they fly off in as well as speed and size
	public ExplosionBit(Asteroid ast){
		lifetime = (int)(Math.random()*200+50);
		
		double tempAngle = (Math.random()*360+1);
		direction = new Point((int)(Math.cos(Math.toRadians(tempAngle))*2),(int)(Math.sin(Math.toRadians(tempAngle))*2));
		int randomFactor;
		randomFactor = (int)(Math.random()*5+1);
		direction.x*=randomFactor;
		randomFactor = (int)(Math.random()*5+1);
		direction.y*=randomFactor;
		
		location = ast.location;
		size = (int)(Math.random()*3+1);
	}
	
	//Updates the bits location
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
		
		if (!(travelled>lifetime)){
			location = (new Point(location.x+direction.x,location.y+direction.y));
			travelled += Math.sqrt(Math.pow(direction.x,2)+ Math.pow(direction.y,2));
		}
		
	}
}