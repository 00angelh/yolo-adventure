package game.engine;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;


public class Explosion{
	
	int bitAmount; //Indicates how much debris will result from the explosion
	ArrayList<ExplosionBit> bits; //Creates a variable to hold those bits
	boolean gone; //Indicates whether the explosion is gone or not
	Color explosionColor; //Sets the color of the bits of the explosion
	
	//Sets the attributes of the explosion and adds all the bits
	public Explosion(Asteroid ast,GameModel model){
		
		explosionColor = ast.color;
		
		bits = new ArrayList<ExplosionBit>();
		
		if (ast.size==1){
			bitAmount = (int)(Math.random()*6+15);
		}else if (ast.size ==0.5){
			bitAmount = (int)(Math.random()*15+15);
		}else{
			bitAmount = (int)(Math.random()*30+15);
		}
		
		
		for (int i =0;i<bitAmount;i++){
			bits.add(new ExplosionBit(ast));
		}
		
		gone = false;
		
	}
	
	
	//Sets it so that the bits are removed if their lifetime is done
	public void explode(){
		ArrayList<ExplosionBit> removal = new ArrayList<ExplosionBit>();
		if (!(bits==null)){
			for (ExplosionBit e: bits){
				if (e.travelled>e.lifetime){
					removal.add(e);
				}
			}
		}
	
		if (!(removal==null))
			bits.removeAll(removal);
		
	}
	
	//Draws the bits and updates each individual one
	public void update(Graphics g){
		
			explode();
			
		
			g.setColor(explosionColor);
			
			if (!(bits==null)){
				
				for (ExplosionBit c: bits){
					c.update();
				}
				
				for (ExplosionBit c: bits){
					g.fillRect(c.location.x, c.location.y,c.size,c.size);
				}
				
			}else{
				
				gone=true;
				
			}
			
			
			
	}
}