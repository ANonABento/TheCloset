package ICSProject.TheCloset;

import processing.core.PApplet;

public class sketchMain extends PApplet{
	
	//variable declaration
	int sketchSelection = 0;
	
	public static void main(String[] args) {
		sketchMain startScreen = new sketchMain();
		PApplet.runSketch(new String[]{"ICSProject.TheCloset.sketchMain"}, startScreen);
	}
	
	public void settings() {
		size(400, 700);
	}
	
	public void draw(){
		background(64);
		rect(100, 100, 100, 100);
	}
	
	//method whenever mouse is pressed
	public void mousePressed() {
	    //if clicked in a button
	    if (mouseX > 100 && mouseX < 200 && mouseY > 100 & mouseY < 200) {
	    	//delete this window
	    	surface.setVisible(false);
	    	
	    	//create a new window of the menu sketch
	    	sketchMenu sketchMenu = new sketchMenu();
	    	PApplet.runSketch(new String[]{"ICSProject.TheCloset.sketchMenu"}, sketchMenu);
	    }
	}
}
