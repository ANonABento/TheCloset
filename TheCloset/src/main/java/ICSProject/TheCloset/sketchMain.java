package ICSProject.TheCloset;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class represents the main entry point for the application using Processing.
 * It displays a splash screen with the application logo and transitions to the menu after a delay.
 */
public class sketchMain extends PApplet{
	
	//variable declaration
	int sketchSelection = 0;
	PImage appLogo;
	int startTime;
	
	/**
     * Main method to start the application.
     *
     * @param args Command line arguments (not used)
     */
	public static void main(String[] args) {
		sketchMain startScreen = new sketchMain();
		PApplet.runSketch(new String[]{"ICSProject.TheCloset.sketchMain"}, startScreen);
	}
	
	/**
     * Setup method to initialize the splash screen.
     * Sets the size of the window, loads the application logo, and records the start time.
     */
	public void settings() {
		size(400, 700);
		appLogo = loadImage("images/TheClosetIcon.png");
		startTime = millis();startTime = millis(); //set start time
	}

	/**
     * Draw method to continuously update and render the splash screen.
     * Displays the application logo and author information.
     * Transitions to the menu sketch after a delay.
     */
	//splash screen
	public void draw(){
		background(0);
		image(appLogo, 0, 150, 400, 400);
		textSize(26);
		fill(255);
		text("by Kevin Jiang", 220, 580);
		
		//delay for 2000 milliseconds
		if (millis() - startTime > 2000) {
		    nextScreen(); //method to go to the menu
		    noLoop(); //stop this class's draw loop
		}
	}
	
	/**
     * Transition to the menu sketch.
     * Hides the current window and creates a new window for the menu sketch.
     */
	//go to menu
	public void nextScreen() {
	    //delete this window
	    surface.setVisible(false);
	    	
	    //create a new window of the menu sketch
	    sketchMenu sketchMenu = new sketchMenu();
	    PApplet.runSketch(new String[]{"ICSProject.TheCloset.sketchMenu"}, sketchMenu);
	}
}
