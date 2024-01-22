package ICSProject.TheCloset;

import processing.core.PApplet;

public class sketch extends PApplet {
	//variable declaration
	String robotResponse = "hello";
	int typingAnimationIndex = 0;
    boolean typingAnimationRunning = true;
	
	
	public void settings() {
		size(400, 700);
	}

	public void draw(){
		background(64);
		
		String currentRobotResponse = robotResponse.substring(0, min(typingAnimationIndex, robotResponse.length())); //ensure index doesn't go beyond the message length
        fill(255); // Text color (white)
        textAlign(CENTER, CENTER);
        textSize(16);
        text(currentRobotResponse, width / 2, height / 2);
        
        if (typingAnimationRunning) {
        	typingAnimationIndex++;

            if (typingAnimationIndex > robotResponse.length()) {
            	typingAnimationRunning = false;  // Stop the animation when it reaches the end of the string
            }
        }
	}
	
	public void changeRobotResponse(String response) {
        this.robotResponse = response;
        typingAnimationIndex = 0;
        typingAnimationRunning = true;
    }
}