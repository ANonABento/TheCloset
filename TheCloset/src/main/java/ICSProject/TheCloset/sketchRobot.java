package ICSProject.TheCloset;

import processing.core.PApplet;
import processing.core.PImage;

public class sketchRobot extends PApplet {
	//variable declaration
	String robotResponse = "";
	int typingAnimationIndex = 0;
    boolean typingAnimationRunning = true;
    int textColor = 255;
    
    //user input
  	String userInput = "Click to chat in here!";
  	String userResponse = "";
  	boolean textBoxActive = true;
  	String currentText;
  	boolean robotDone = true;
  	
  	PImage imgCafe;
  	PImage robotOverlordClosed;
  	PImage robotOverlordOpen;
	
	boolean isExitHovered = false;
	boolean isEnterHovered = false;
	
	systemRobot robotSystem = new systemRobot(this);
	
	public void setup() {
		//start the robot system in a separate thread
        Thread systemThread = new Thread(robotSystem);
        systemThread.start();
	}
	
	public void settings() {
		size(400, 700);
		imgCafe = loadImage("images/cafeBG.png");
		robotOverlordClosed = loadImage("images/robotClosed.png");
		robotOverlordOpen = loadImage("images/robotOpen.png");
	}

	public void draw(){
		background(54);
		image(imgCafe, -270, 0, 1049, 700);
		
		image(robotMouth(), 0, 130, 450, 579);
		
		exitBar();
	    
		//robot turn to talk
		if (textBoxActive == false) {
			currentText = robotResponse.substring(0, min(typingAnimationIndex, robotResponse.length())); //ensure index doesn't go beyond the message length
		}
		else {
			currentText = userInput;
		}
	
	    //chat box
		stroke(255, 148, 184); strokeWeight(5);
	  	fill(255, 209, 221, 200); rect(20, 500, 360, 130, 20, 20, 20, 20);
	  	textAlign(LEFT); fill(0); textSize(18); text(currentText, 40, 540);
		
	  	//robot name box
	  	fill(255, 148, 184, 255); rect(270, 470, 100, 40, 40, 40, 40, 40);
	  	textAlign(CENTER); textSize(24); fill(0); text("Robot", 320, 497);
        
        if (typingAnimationRunning) {
        	robotDone = false;
        	//delay in the animation
        	if (frameCount % 2 == 0) {
                typingAnimationIndex++; //add a letter to the display
            }

            if (typingAnimationIndex > robotResponse.length()) {
            	typingAnimationRunning = false;  //stop the animation when it reaches the end of the string
            	robotDone = true;
            }
        }
	}
	
	public void changeRobotResponse(String response) {
        this.robotResponse = response;
        typingAnimationIndex = 0;
        typingAnimationRunning = true;
    }
	
	public void mousePressed() {
		// if clicked in text box
		if (mouseX > 20 && mouseX < 380 && mouseY > 500 & mouseY < 630) {
			// if robot is not talking
	        if (textBoxActive == true || robotDone == true) {
	        	userInput = "";
	        	textBoxActive = true;
	        }
        }
		
		//exit bar
	    if (isExitHovered) {
	        exit();
	    }
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
			//if robot is not talking
			if (textBoxActive == true) {
				robotSystem.userResponse(userResponse);
				userInput = "";
				textBoxActive = false;
			}
		}
	}
	
	private PImage robotMouth() {
		if (robotDone == true) {
			return robotOverlordOpen;
		}
		else {
			return robotOverlordClosed;
		}
	}
	
	private void exitBar() {
		noStroke();

	    if (mouseX > 20 && mouseX < 380 && mouseY > 650 && mouseY < 690) {
	        fill(24, 240); rect(20, 650, 360, 40, 90, 90, 90, 90);
	        isExitHovered = true;
	        fill(255);
	    } else {
	        fill(24, 30); rect(20, 650, 360, 40, 90, 90, 90, 90);
	        isExitHovered = false;
	        fill(0);
	    }

	    textAlign(CENTER, CENTER);
	    text("Exit", 200, 670);
	}
}