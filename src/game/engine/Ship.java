package game.engine;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;



public class Ship extends MovingObject{
	
	int angle; //Used to hold the current angle the ship faces
	//Variables used to keep the ships x and y points
	int shipPointsX[]; //Used to hold all the points of the ship
	int shipPointsY[];
	double currentSpeed; //Holds the ships current speed
	double shipAcc; //Acceleration
	int movingAngle; //Current direction its moving in
	GameModel model; //The model
	boolean thrusting; //Whether the ship is thrusting or not

	
	//Creates the ship
	public Ship(GameModel a){
		
		thrusting = false;
		
		angle = 180;
		
		model=a;
		
		shipAcc = 0.33;
		currentSpeed =0;
		
		//Creates an array of integers to hold the ships points
		shipPointsX = new int[4];
		shipPointsY = new int[4];
		
		//Sets the ships location to be the center of the map
		location = new Point(400,300);
		shape = new Polygon();
		//Sets the ships coordinates for the start
		
		
		
	}
	

	//Increases the speed of the ship	
	public void increaseSpeed(){
	
		currentSpeed = Math.sqrt(Math.pow(currentSpeed*Math.cos(movingAngle) + 0.33*Math.cos(angle),2) + Math.pow(currentSpeed*Math.sin(movingAngle) + 0.33*Math.sin(angle),2));
		
		movingAngle = (int)(Math.atan((int)((currentSpeed*Math.cos(movingAngle) + 0.33*Math.cos(angle)) / (currentSpeed*Math.cos(movingAngle) + 0.33*Math.cos(angle)))));
		
		
		
		if (((int)(currentSpeed*Math.cos(movingAngle) + 0.33*Math.cos(angle))==0)){
			if (((int)(currentSpeed*Math.sin(movingAngle) + 0.33*Math.sin(angle))<0)){
				movingAngle = 270;
				
			}else{
				movingAngle =0;
			}
	
		}else if (((int)(currentSpeed*Math.cos(movingAngle) + 0.33*Math.cos(angle))<0)){
			movingAngle+=180;
		}
	}
	
	//Draws the ship
	public void update(Graphics g){
		
		if (!model.over){
			if (currentSpeed>0){
				currentSpeed -=0.05;
			}
			
			if (currentSpeed<0){
				currentSpeed=0;
			}
			
			for (int i=0;i<4;i++){
				location.x += (int)(Math.sin(Math.toRadians(angle))*currentSpeed);
				location.y += (int)(Math.cos(Math.toRadians(angle))*currentSpeed);
							
			}	
			 
			
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
			
			angle= angle%360;
			
			
			shipPointsX[0] = location.x + (int)(Math.sin(Math.toRadians(angle))*12);
			shipPointsY[0] = location.y + (int)( Math.cos(Math.toRadians(angle))*12);
			
			shipPointsX[1] = location.x +(int)(Math.sin(Math.toRadians(angle+145))*12);
			shipPointsY[1] = location.y +(int)( Math.cos(Math.toRadians(angle+145))*12);
			
			shipPointsX[2] = location.x;
			shipPointsY[2] = location.y;
			
			shipPointsX[3] = location.x  +(int)(Math.sin(Math.toRadians(angle+70+145))*12);
			shipPointsY[3] = location.y + (int)(Math.cos(Math.toRadians(angle+70+145))*12);
			
			shape = new Polygon(shipPointsX,shipPointsY,4);
			
			
			if (thrusting){
				shipPointsX[0] = location.x + (int)(Math.sin(Math.toRadians(angle))*12);
				shipPointsY[0] = location.y + (int)( Math.cos(Math.toRadians(angle))*12);
				
				shipPointsX[1] = location.x +(int)(Math.sin(Math.toRadians(angle+145))*17);
				shipPointsY[1] = location.y +(int)( Math.cos(Math.toRadians(angle+145))*17);
				
				shipPointsX[2] = location.x;
				shipPointsY[2] = location.y;
				
				shipPointsX[3] = location.x  +(int)(Math.sin(Math.toRadians(angle+70+145))*17);
				shipPointsY[3] = location.y + (int)(Math.cos(Math.toRadians(angle+70+145))*17);
				
				shape = new Polygon(shipPointsX,shipPointsY,4);
				
			}
			
		
			
			g.setColor(Color.white);
			g.drawPolygon(shipPointsX,shipPointsY,4);
			
			thrusting = false;
		}
		
		
		
	
	}
	
	
	
}