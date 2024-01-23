package ICSProject.TheCloset;

import processing.core.PApplet;
import processing.core.PImage;

public class sketchMain extends PApplet{
	
	//variable declaration
	int sketchSelection = 0;
	PImage appLogo;
	int startTime;
	
	public static void main(String[] args) {
		sketchMain startScreen = new sketchMain();
		PApplet.runSketch(new String[]{"ICSProject.TheCloset.sketchMain"}, startScreen);
	}
	
	public void settings() {
		size(400, 700);
		appLogo = loadImage("images/TheClosetIcon.png");
		startTime = millis();startTime = millis();
	}

	
	//splash screen
	public void draw(){
		background(0);
		image(appLogo, 0, 150, 400, 400);
		textSize(26);
		fill(255);
		text("by Kevin Jiang", 220, 580);
		
		//delay for 2000 milliseconds
		if (millis() - startTime > 000) {
		    nextScreen();
		    noLoop(); //stop the draw loop
		}
	}
	
	//go to menu
	public void nextScreen() {
	    //delete this window
	    surface.setVisible(false);
	    	
	    //create a new window of the menu sketch
	    sketchMenu sketchMenu = new sketchMenu();
	    PApplet.runSketch(new String[]{"ICSProject.TheCloset.sketchMenu"}, sketchMenu);
	}
}
