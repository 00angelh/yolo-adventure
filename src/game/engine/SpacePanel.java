package game.engine;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;





public class SpacePanel extends JPanel{
	
	ArrayList<Point> stars; //Creates an arraylist used to hold each point a star is at
	
	SpacePanel(){
		
		super();
		
		stars = new ArrayList<Point>();//Initialized the arraylist for the stars
		
		//Sets the background and size of the panel
		this.setBackground(Color.black);
		this.setSize(800,600);
		this.setVisible(true);
		
		
		
		//Creates a new star at a random x location between 1 and 800, and y location of 1 and 600
		for (int i = 0; i<100;i++){
			stars.add(new Point((int)(Math.random()*800 +1),(int)(Math.random()*600 +1)));
			
		}
		
		
	}

	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		//Sets the color of the star to be dark gray
		g.setColor(Color.DARK_GRAY);
		
		//Creates a 1 pixel color star using the arraylist of points
		for (Point c: stars){
			g.fillRect(c.x,c.y,1,1);
		}
	}
	/**
	public static void main(String args[]){
		JFrame test = new JFrame("Test");
		test.setVisible(true);
		test.setSize(800,600);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.add(new SpacePanel());
		
	}
	
	**/
}