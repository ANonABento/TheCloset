package ICSProject.TheCloset;

import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class represents a login screen for the application using Processing.
 * Users can enter their username and password to sign in.
 */
public class sketchLogin extends PApplet{
	
	//variable declaration
	PImage imgRainbow; //background image
	
	//track mouse pos over buttons
	boolean isExitHovered = false;
	boolean isEnterHovered = false;
	
	//user input
	String userInput = "";
	int textBox = 0;
	String nameText = "";
	String passwordText = "";
	
	//default text values
	String signInText = "Sign In";
	
	/**
     * Setup method to initialize the login screen.
     * Sets the size of the window and loads the background image.
     */
	public void settings() {
		//canvas size
		size(400, 700);
		imgRainbow = loadImage("images/rainbowBG.jpg"); //bg image
	}
	
	/**
     * Draw method to continuously update and render the login screen.
     */
	public void draw(){
		//background
		background(54);
		image(imgRainbow, 0, 0);
		
		//header
	    textAlign(CENTER);
	    textSize(34);
	    fill(0);
	    text(signInText, width / 2, 100);

	    //body
	    fill(0, 30); rect(40, 240, 320, 40, 90, 90, 90, 90);
	    textAlign(CENTER); fill(0); text("Name:", width / 2, 220);
		textAlign(LEFT); textSize(26); fill(0); text(nameText, 50, 270);
		
		fill(0, 30); rect(40, 380, 320, 40, 90, 90, 90, 90);
		textAlign(CENTER); fill(0); text("Password:", width / 2, 360);
		textAlign(LEFT); textSize(26); fill(0); text(passwordText, 50, 410);
		
		//enter button
		enterButton();
		
		//back bar
		backBar();
	}
	
	/**
     * Handle keyboard events, including backspace and enter for user input.
     */
	public void keyPressed() {
		//delete a key if backspace is hit
		if (keyCode == BACKSPACE) {
			  if (userInput.length() > 0) {
			      userInput = userInput.substring(0, userInput.length() - 1);
			      switch (textBox) {
			        case 1:
			          nameText = userInput;
			          break;
			        case 2:
			          passwordText = userInput;
			          break;
			        default:
			          break;
			      }
			  }
		} 
		//add key if its not backspace or enter
		else if (key != ENTER) {
			  userInput += key;
			  switch (textBox) {
			  	case 1:
			  		nameText = userInput;
			  		break;
			  	case 2:
			  		passwordText = userInput;
			  		break;
			  	default:
			  		break;
			  }
		  } 
		//if entered
		else if (key == ENTER) {
			  textBox = 0;
			  userInput = "";
		}
	}
	
	 /**
     * Handle mouse click events, specifically for the exit bar, input boxes, and enter button.
     */
	public void mousePressed() {
		//if clicked in a button
		if (mouseX > 40 && mouseX < 360 && mouseY > 380 & mouseY < 420) {
			userInput = "";
			textBox = 2;
		}
		else if (mouseX > 40 && mouseX < 360 && mouseY > 240 & mouseY < 280) {
			userInput = "";
			textBox = 1;
		}
		//exit bar
	    if (isExitHovered) {
	    	//delete this window
		    surface.setVisible(false);
		    
	    	//create a new window for their profile
		    sketchMenu sketchMenu = new sketchMenu();
		    PApplet.runSketch(new String[]{"ICSProject.TheCloset.sketchMenu"}, sketchMenu);
	    }
	    
	    //enter button
	    if (isEnterHovered) {
	    	checkUser();
	    }
	}
	
	/**
     * Display the enter button with appropriate hover effects.
     */
	private void enterButton() {
		//settings for display
		noStroke();
		textSize(20);

		//check if mouse is hovering over the buttons
	    if (mouseX > 250 && mouseX < 350 && mouseY > 460 && mouseY < 550) { //hovering
	    	fill(0, 204, 102, 130); rect(250, 460, 100, 40, 90, 90, 90, 90);
	        isEnterHovered = true;
	        fill(0);
	    } else {
	    	fill(0, 204, 102, 230); rect(250, 460, 100, 40, 90, 90, 90, 90);
	    	isEnterHovered = false;
	        fill(0);
	    }

	    //display
	    textAlign(CENTER, CENTER);
	    text("Enter", 300, 480);
	}
	
	/**
     * Display the back bar with appropriate hover effects.
     */
	private void backBar() {
		//settings for display
		noStroke();
		textSize(20);

		//check if mouse is hovering over the buttons
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
	
	/**
     * Check user credentials by reading from the user information file.
     * If credentials match, go to the profile window; otherwise, display retry message.
     */
	private void checkUser() {
	    try {
	        Scanner scanner = new Scanner(new File("data/txt/userInfo"));

	        //2D array to store user information
	        String[][] users = new String[2][2];

	        //add user information from the file to array
	        for (int i = 0; i < users.length; i++) {
	            String[] userInfo = scanner.nextLine().split(": ", 2);
	            users[i][0] = userInfo[0]; //label
	            users[i][1] = userInfo[1]; //info
	        }

	        //check if user and password match
	        boolean credentialsMatch = false;
	        if (users[0][1].equals(nameText) && users[1][1].equals(passwordText)) {
	           credentialsMatch = true;
	        }

	        if (credentialsMatch) {
	            //logged in so go to the profile window
	            surface.setVisible(false);
	            sketchProfile sketchProfile = new sketchProfile();
	            PApplet.runSketch(new String[]{"ICSProject.TheCloset.sketchMenu"}, sketchProfile);
	        } else {
	            //don't match so notify user
	            signInText = "Retry";
	            nameText = "";
	            passwordText = "";
	        }

	        //close the scanner
	        scanner.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}
}
