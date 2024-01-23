package ICSProject.TheCloset;

import processing.core.PApplet;

public class sketchRobot extends PApplet {
	//variable declaration
	String robotResponse = "";
	int typingAnimationIndex = 0;
    boolean typingAnimationRunning = true;
    int textColor = 255;
    //user input
  	String userInput = "";
  	String userResponse = "";
	
	public void setup() {
		//start the robot system in a separate thread
        systemRobot robotSystem = new systemRobot(this);
        Thread systemThread = new Thread(robotSystem);
        systemThread.start();
	}
	
	public void settings() {
		size(400, 700);
	}

	public void draw(){
		background(64);
		
		String currentRobotResponse = robotResponse.substring(0, min(typingAnimationIndex, robotResponse.length())); //ensure index doesn't go beyond the message length
        fill(textColor); // Text color (white)
        textAlign(CENTER, CENTER);
        textSize(16);
        text(currentRobotResponse, width / 2, height / 2);
        
        if (typingAnimationRunning) {
        	//delay in the animation
        	if (frameCount % 4 == 0) {
                typingAnimationIndex++; //add a letter to the display
            }

            if (typingAnimationIndex > robotResponse.length()) {
            	typingAnimationRunning = false;  //stop the animation when it reaches the end of the string
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
	
	//method whenever keyboard is pressed
	public void keyPressed() {
		if (keyCode == BACKSPACE) {
			if (userInput.length() > 0) {
				userInput = userInput.substring(0, userInput.length() - 1);
				userResponse = userInput;		
			}
		} 
		else if (key != ENTER) {
			userInput += key;
			userResponse = userInput;	
		} 
		else if (key == ENTER) {
			userInput = "";
		}
	}
}