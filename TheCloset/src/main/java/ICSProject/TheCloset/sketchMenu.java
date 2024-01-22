package ICSProject.TheCloset;

import processing.core.PApplet;

public class sketchMenu extends PApplet{
	
	public void settings() {
		size(400, 700);
	}
	
	public void draw(){
		background(24);
		
		rect(200, 200, 100, 100);
	}
	
	//method whenever mouse is pressed
		public void mousePressed() {
		    //if clicked in a button
		    if (mouseX > 200 && mouseX < 300 && mouseY > 200 & mouseY < 300) {
		    	//delete this window
		    	surface.setVisible(false);
		    	
		    	//create a new window of the menu sketch
		    	sketchRobot sketchRobot = new sketchRobot();
		    	PApplet.runSketch(new String[]{"ICSProject.TheCloset.sketchRobot"}, sketchRobot);
		    }
		}
}
