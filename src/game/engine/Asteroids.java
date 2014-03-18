package game.engine;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.applet.*;
import java.net.*;
import java.awt.image.BufferedImage;

public class Asteroids extends JFrame implements KeyListener{
	
	java.util.Timer refresh; //Creates a timer to be used to refresh the GUI with the current graphics
	TimerTask ref; //Creates a timer task that will be used by the timer
	GameModel theModel; //Creates a variable that will be used to hold the model from the GameModel that calls this
	SpacePanel space; //The panel 
	BufferedImage offscreen; //Image used to buffer the display so that it doesn't flicker
	Graphics buffered; //The buffered graphics used 
     
	Asteroids(String title,GameModel model){
		
		super(title);
		
		
		offscreen = new BufferedImage(800, 600,  BufferedImage.TYPE_INT_RGB);
		
		while ((offscreen.getWidth(null) == -1) && (offscreen.getHeight(null) == -1));
		
		buffered = offscreen.getGraphics();
	
		
		
		
		
		//Sets the model and sets the timer settings
		theModel = model;

		//Sets the look and settings of the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,600);
		this.setVisible(true);
		this.setResizable(false);
		
		//Creates a space panel and adds it to the frame
		space = new SpacePanel();
		add(space);
		
		//Adds a key listener for the game mechanics
		addKeyListener(this);
		
		//Initialized the timer and sets how it will function
		refresh = new java.util.Timer();
		refresh.schedule(new RemindTask(model),new Date(),33);
		
		
	}
	
	
	public void keyPressed(KeyEvent e){
		
		
		//Checks to see if the game is over or not first
		if (!(theModel.over)){
			
				//Increaes the ships angle
				if (e.getKeyCode()==KeyEvent.VK_RIGHT){
					
					theModel.destroyer.angle-=5;
					
				}
				
				//Creates a bomb thing
				if (e.getKeyCode()==KeyEvent.VK_B){
					if (theModel.empAm<3 && theModel.bomb==null){
						theModel.bomb = new EMP(theModel);
						theModel.empAm++;
					}
						
					
				}
				
				//Decreases the ships angle
				if (e.getKeyCode()==KeyEvent.VK_LEFT){
					
					theModel.destroyer.angle+=5;
					
				}
				
				//Fires a missile
				if (e.getKeyCode()==KeyEvent.VK_SPACE){
					
					if (theModel.missAm<4){
						theModel.missAm+=1;
						theModel.miss.add(new Missile(theModel));
						theModel.sounds.currentClip= theModel.sounds.clips.get(0);
						theModel.sounds.play();	
						
					}
					
					
				}
				
				//Thrusts
				if (e.getKeyCode()==KeyEvent.VK_UP){
					
					
					
					if (theModel.destroyer.currentSpeed<7)
						theModel.destroyer.increaseSpeed();
					
					theModel.destroyer.movingAngle = theModel.destroyer.angle;
					theModel.destroyer.thrusting=true;
		
				}
		}else{
			//Resets the game
			if (e.getKeyCode()==KeyEvent.VK_ENTER){
					
					theModel.gameReset();
					
					
			}
				
		}
	}
				
	public void keyReleased(KeyEvent e){
					
	}
				
	public void keyTyped(KeyEvent e){
					
	}
	
	
	
	//An update method used to update the look of the GUI
	public void update(){
		
		buffered = offscreen.getGraphics();
		//Gets graphics of the frame
		Graphics pen = this.getGraphics();
		super.paint(buffered);

     	theModel.update();
		
		//Updates the ships invulnerability buffer
		theModel.buffer++;
		
		
		
		
		//If no lives, game over
		if(theModel.lives==0){
			theModel.gameOver();
		}
		
	
		
		//Sets the color of the score text to be white and sets it on the frame
		buffered.setColor(Color.white);
		buffered.setFont(new Font("Courier",Font.PLAIN,24));
		String theScore = "Score : " + theModel.score;
		buffered.drawString(theScore, 20,50);
		
		//Draws the current amount of lives
		int gap=0;
		if (!(theModel.over)){
			for (int i=0;i<theModel.lives;i++){
	
				
				buffered.drawLine(770-i*10-gap,30,775-i*10-gap,45);
				buffered.drawLine(770-i*10-gap,30,765-i*10-gap,45);
				gap+=10;
				
			}
		}
		
		//DRAWS THE GAME OVER SCREEN
		if (theModel.over){
			buffered.setFont(new Font("Courier",Font.PLAIN,48));
			buffered.drawString("GAMEOVER",300,260);
			buffered.setFont(new Font("Courier",Font.PLAIN,20));
			buffered.drawString("Press ENTER to start",295,330);
		}
		
	
		pen.drawImage(offscreen,0,0,this);
	
		
	}
	
	//A timer task which is used to update the GUI by calling the update method
	class RemindTask extends TimerTask {
		
		GameModel model;
		
		RemindTask(GameModel a){
			model = a;
		}
		
	    public void run() {
	      model.ast.update();
	    }
	  }

	

	
	public void paint(Graphics g){
		
		super.paint(g);
		
	}
	
	/**
	public static void main(String args[]){
		 //Other mains in GameModel, SpacePanel
		//new Asteroids("Asteroids",new GameModel());
		//new Asteroids("Asteroids",new GameModel());
		new Asteroids("Asteroids",new GameModel());
	}
	**/

	
}