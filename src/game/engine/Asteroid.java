package game.engine;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;




public class Asteroid extends MovingObject{
		
		double size; // Creates a variable used to hold the size of the asteroid
		int x[]; //Creates a variable used to hold the x points of the polygon
		int y[]; //Creates a variable used to hold the y points of the polygon
		int drawX[];
		int drawY[];
		Point location;
		int radius;
		int totX;
		int totY;
		double tempAngle;
		Color color;
		
		public 	Asteroid(){
			this(1,(Math.random()*360+1),null, null);
		}
		
		//Creates the asteroids shape, size, direction, location, color
		public Asteroid(double theSize,double theangle,Color col, Point loc){
			if (col==null){
				int rand = (int)(Math.random()*100+20);
				col = new Color(rand+104,rand+60,rand);
			}
			color = col;
			
			shape = new Polygon();
			
			if (loc ==null){
				loc = new Point((int)(Math.random()*780+20),(int)(Math.random()*580+20));
			}
			location = loc;	
			
			x = new int[16];
			y = new int[16];
			drawX = new int[16];
			drawY= new int[16];
			
			
			//Sets the direction
			tempAngle= theangle;
			direction = new Point((int)(Math.cos(Math.toRadians(tempAngle))*2),(int)(Math.sin(Math.toRadians(tempAngle))*2));
			
			
			
			
			//Sets the size to 1 for now
			size =theSize;
			
			//Sets the angle to 0
			double angle=0;
			radius =0;
			
			
			for (int count =0; count<16;count++){
				
				
				
				//Randomly sets a radius and finds the points for that radius
				radius = (int)((40+Math.random()*40*0.5)*size);
				
			
				
				x[count] = (int)(radius * Math.cos(Math.toRadians(angle)));
				y[count] = (int)(radius * Math.sin(Math.toRadians(angle)));
			
	
				//Increments the angle by 22.5 degrees
				angle+=22.5;
					
				
			}
			
			
			speed = 2+ (int)(Math.random() *1.5);
		
		
		}
		
		
					
		//Displays the asteroid on the GUI
		public void update(Graphics pen){
		
			
			if (location.x<0){
					location.x=800;	
			}
			
			if (location.y< 0){
					location.y=600;
					
			}
				
			if (location.x>800){
					location.x=0;
			}
				
			if (location.y>600){
					location.y = 0;
			}
			
			
				
			//Goes through all the coordinates and increases its direction based on speed and direction
			//Also checks to see if the asteroid has hit the boundary of the frame
			
			location.x += (((direction.x) * speed));
			location.y +=(((direction.y)) * speed);
				
				
			for (int i =0 ; i<16;i++){
			
				drawX[i] = x[i] + location.x ;
				drawY[i] = y[i] + location.y;
				
			}
			
			shape = new Polygon(drawX,drawY,16);
				
			pen.setColor(color);
			pen.fillPolygon(drawX , drawY , 16);
				
			pen.setColor(Color.white);
			pen.drawPolygon(drawX , drawY , 16);
			
				
			
			
			
		}
		
		//Checks to see if the asteroid has collided with a missile, bomb, or ship
		
		public boolean collidesWith(Missile m){
			if (!(m==null)){
				if (shape.getBounds().intersects(m.shape.getBounds())){
					return true;
				}
			}
			
				
			return false;
			
		}
		
		public boolean collidesWith(EMP e){
			
			if (shape.getBounds().intersects(e.shape.getBounds())){
				return true;
			}
				
			return false;
			
		}
		
		public boolean collidesWith(Ship m){
			if (shape.getBounds().intersects(m.shape.getBounds())){
				return true;
			}
				
			return false;
			
		}
		
		
}