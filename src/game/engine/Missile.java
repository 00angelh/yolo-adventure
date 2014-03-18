package game.engine;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Missile extends MovingObject{
	
	int lifetime;
	int travelled;
	GameModel theModel;
	
	
	public Missile(GameModel model){
		travelled=0;
		lifetime =500;
		theModel = model;
		
		location = new Point(theModel.destroyer.location.x,theModel.destroyer.location.y);	
		
		int x = (int)(Math.sin(Math.toRadians(theModel.destroyer.angle))*8);
		int y = (int)(Math.cos(Math.toRadians(theModel.destroyer.angle))*8);
		direction = new Point(x,y);
		
		shape = new Polygon();
		shape.addPoint(x,y);
	}
	
	public void update(Graphics g){
		
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
		
		g.setColor(Color.white);
		travelled+= Math.sqrt(Math.pow(direction.x,2) + Math.pow(direction.y,2));
		
		if (theModel.destroyer.currentSpeed>1){
			location.x+=direction.x*theModel.destroyer.currentSpeed;
			location.y+=direction.y*theModel.destroyer.currentSpeed;
		}else{
			location.x+=direction.x;
			location.y+=direction.y;
		}
		
		
		
		shape = new Polygon();
		shape.addPoint(location.x,location.y);
		shape.addPoint(location.x+3,location.y+3);
		shape.addPoint(location.x-3,location.y-3);
		
		g.drawRect(location.x,location.y,2,2);
	}
}