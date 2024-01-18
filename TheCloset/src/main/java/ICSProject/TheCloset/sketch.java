package ICSProject.TheCloset;

import processing.core.PApplet;

public class sketch extends PApplet {
	//variable declaration
	String robotResponse = "hello";
	
	
	public void settings() {
		size(500, 500);
	}

	public void draw(){
		background(64);
        fill(255); // Text color (white)
        textAlign(CENTER, CENTER);
        textSize(16);
        text(robotResponse, width / 2, height / 2);
	}
	
	public void toScreen(String response) {
        this.robotResponse = response;
    }
}