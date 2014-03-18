package game.engine;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;


public class ShipExplosion{
	
	int bitAmount;
	ArrayList<ShipBit> bits;
	boolean gone;
	
	//Creates 4 bits to result from the explosion
	public ShipExplosion(GameModel model){
		
		
		bits = new ArrayList<ShipBit>();
		
	
		bitAmount = 4;
		
		
		for (int i =0;i<bitAmount;i++){
			bits.add(new ShipBit(model));
		}
		
		gone = false;
		
	}
	
	
	//Checks if any of the bits lifetime is over, and removes them if they are
	public void explode(){
		ArrayList<ShipBit> removal = new ArrayList<ShipBit>();
		if (!(bits==null)){
			for (ShipBit e: bits){
				if (e.travelled>e.lifetime){
					removal.add(e);
				}
			}
		}
	
		if (!(removal==null))
			bits.removeAll(removal);
		
	}
	
	//Displays the bits on the GUI and updates them
	public void update(Graphics g){
		
			explode();

			g.setColor(Color.white);
			
			if (!(bits==null)){
				
				for (ShipBit c: bits){
					c.update();
				}
				
				for (ShipBit c: bits){
					
						g.drawLine(c.location.x, c.location.y,c.location.x + (int)(Math.sin(Math.toRadians(c.currentRot)))*12,c.location.y + (int)(Math.cos(Math.toRadians(c.currentRot))*12));
				}
				
			}else{
				
				gone=true;
				
			}
			
			
			
	}
}