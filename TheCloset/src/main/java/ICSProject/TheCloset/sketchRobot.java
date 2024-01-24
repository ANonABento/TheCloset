package ICSProject.TheCloset;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class represents the robot chat screen of the application using Processing.
 * It allows users to chat with a simulated robot character. The robot provides responses
 * with a typing animation, and the user can interact by typing in the text box.
 */
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
  	
  	//images
  	PImage imgCafe;
  	PImage robotOverlordClosed;
  	PImage robotOverlordOpen;
	
  	//button check
	boolean isExitHovered = false;
	boolean isEnterHovered = false;
	
	//objects
	systemRobot robotSystem = new systemRobot(this);
	
	/**
     * Setup method to initialize the robot chat screen.
     * Starts the robot system in a separate thread.
     */
	public void setup() {
		//start the robot system in a separate thread
        Thread systemThread = new Thread(robotSystem);
        systemThread.start();
	}
	
	/**
     * Settings method to configure the dimensions and load images for the robot chat screen.
     */
	public void settings() {
		size(400, 700);
		//load images with path
		imgCafe = loadImage("images/cafeBG.png");
		robotOverlordClosed = loadImage("images/robotClosed.png");
		robotOverlordOpen = loadImage("images/robotOpen.png");
	}

	/**
     * Draw method to continuously update and render the robot chat screen.
     * Displays the chat box, robot's mouth animation, and handles user interactions.
     */
	public void draw(){
		//background
		background(54);
		image(imgCafe, -270, 0, 1049, 700);
		
		//image of robot
		image(robotMouth(), 0, 130, 450, 579);
		
		//exit button
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
        
	  	//check if robot is talking still
        if (typingAnimationRunning) {
        	robotDone = false;
        	//delay in the animation
        	if (frameCount % 2 == 0) {
                typingAnimationIndex++; //add a letter to the display
            }

        	//once done
            if (typingAnimationIndex > robotResponse.length()) {
            	typingAnimationRunning = false;  //stop the animation when it reaches the end of the string
            	robotDone = true;
            }
        }
	}
	
	/**
     * Method to change the robot's response and reset the typing animation.
     *
     * @param response New response for the robot.
     */
	public void changeRobotResponse(String response) {
		//change response
        this.robotResponse = response;
        //reset animation
        typingAnimationIndex = 0;
        typingAnimationRunning = true;
    }
	
	/**
     * Method called whenever the mouse is pressed.
     * Handles mouse interactions for the text box and exit bar.
     */
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
	    	//delete this window
		    surface.setVisible(false);
		    
	    	//create a new window for their profile
		    sketchMenu sketchMenu = new sketchMenu();
		    PApplet.runSketch(new String[]{"ICSProject.TheCloset.sketchMenu"}, sketchMenu);
	    }
    }
	
	/**
     * Method called whenever a key is pressed.
     * Handles keyboard interactions for typing in the text box.
     */
	//method whenever keyboard is pressed
	public void keyPressed() {
		//backspace to delete
		if (keyCode == BACKSPACE) {
			if (userInput.length() > 0) {
				userInput = userInput.substring(0, userInput.length() - 1);
				userResponse = userInput;		
			}
		} 
		//regular key to add
		else if (key != ENTER) {
			userInput += key;
			userResponse = userInput;	
		} 
		//enter to send user input
		else if (key == ENTER) {
			//if robot is not talking
			if (textBoxActive == true) {
				robotSystem.userResponse(userResponse);
				userInput = "";
				textBoxActive = false;
			}
		}
	}
	
	 /**
     * Method to determine the image of the robot's mouth based on its animation state.
     *
     * @return Image of the robot's mouth (open or closed).
     */
	private PImage robotMouth() {
		//change from open to closed
		if (robotDone == true) {
			return robotOverlordOpen;
		}
		else {
			return robotOverlordClosed;
		}
	}
	
	/**
     * Method to draw the exit bar and change its appearance based on hover state.
     */
	private void exitBar() {
		noStroke();

		//hover check
	    if (mouseX > 20 && mouseX < 380 && mouseY > 650 && mouseY < 690) {
	        fill(24, 240); rect(20, 650, 360, 40, 90, 90, 90, 90);
	        isExitHovered = true;
	        fill(255);
	    } else {
	        fill(24, 30); rect(20, 650, 360, 40, 90, 90, 90, 90);
	        isExitHovered = false;
	        fill(0);
	    }
	    
	    //display
	    textAlign(CENTER, CENTER);
	    text("Back", 200, 670);
	}
}