package game.engine;import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.applet.*;
import java.net.*;


public class Sound implements Runnable{
	
	ArrayList<AudioClip> clips;
	AudioClip currentClip;
	
	//Places all of the clips in an arraylist so that they may be played
	Sound(){
		
		clips = new ArrayList<AudioClip>();
		AudioClip clip=null ;
		
		try{
			clip =  Applet.newAudioClip(new URL("file:" +"beam.wav"));
		}catch (Exception e){
			System.out.println("Could not find audio clip");
		}
		clips.add(clip);
		
		try{
			clip =  Applet.newAudioClip(new URL("file:" +"shock.wav"));
		}catch (Exception e){
			System.out.println("Could not find audio clip");
		}
		clips.add(clip);
		
		try{
			clip =  Applet.newAudioClip(new URL("file:" +"exp1.wav"));
		}catch (Exception e){
			System.out.println("Could not find audio clip");
		}
		clips.add(clip);
		
		try{
			clip =  Applet.newAudioClip(new URL("file:" +"exp2.wav"));
		}catch (Exception e){
			System.out.println("Could not find audio clip");
		}
		clips.add(clip);
		
		
		try{
			clip =  Applet.newAudioClip(new URL("file:" +"doku.wav"));
		}catch (Exception e){
			System.out.println("Could not find audio clip");
		}
		clips.add(clip);
		
		
		currentClip = clips.get(0);					
		
	}
	
	//Creates a new thread to play the sound clip
	public void play(){
		new Thread(this).start(); 
	}
	
	public void run(){
		currentClip.play();
	}
}
