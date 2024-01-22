package ICSProject.TheCloset;

import processing.core.PApplet;

public class sketchRobot extends PApplet {
	//variable declaration
	String robotResponse = "";
	int typingAnimationIndex = 0;
    boolean typingAnimationRunning = true;
    int textColor = 255;  // Initial text color (white)
	
	
	public void settings() {
		size(400, 700);
		//start the robot system
		systemRobot systemRobot = new systemRobot();
		systemRobot.startSystem();
	}

	public void draw(){
		background(64);
		
		String currentRobotResponse = robotResponse.substring(0, min(typingAnimationIndex, robotResponse.length())); //ensure index doesn't go beyond the message length
        fill(textColor); // Text color (white)
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
	
	public void mousePressed() {
        // Change text color when the mouse is pressed
        textColor = color(random(255), random(255), random(255));
    }
}