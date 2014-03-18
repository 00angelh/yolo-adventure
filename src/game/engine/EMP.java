package game.engine;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;


public class EMP{
	
	int lifetime; //Sets the lifetime of it
	Point location; 
	Color color;
	int travelled;
	int radius;
	GameModel theModel;
	boolean over;
	Polygon shape;
	int x[];
	int y[];
	int angle;
	
	//Sets its attributes
	public EMP(GameModel model){
		
		x = new int[360];
		y = new int[360];
		
		over=false;
		
		radius=1;
		
		travelled=0;
		
		theModel = model;
		
		lifetime = 150;
		
		location = theModel.destroyer.location;
		
		angle =0;
		
		
			
	}
	
	//Updates all the points and draws the bomb thing based on it
	public void update(Graphics pen){
		
		angle=0;
		theModel.sounds.currentClip = theModel.sounds.clips.get(1);
		theModel.sounds.play();
		
		for (int count =0; count<360;count++){

				//Creates the points
				x[count] = location.x + (int)(radius * Math.cos(Math.toRadians(angle)));
				y[count] = location.y + (int)(radius * Math.sin(Math.toRadians(angle)));
				angle+=1;
				
		}
		
		shape = new Polygon(x,y,360);
		
		if (!(travelled>lifetime)){
			radius+=1;
			travelled+=1;
		}else{
			over=true;
		}
		
		pen.setColor(Color.blue);
		pen.drawPolygon(x,y,360);
		
	}
}