package ICSProject.TheCloset;

import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class sketchLogin extends PApplet{
	
	PImage imgRainbow;
	
	boolean isExitHovered = false;
	boolean isEnterHovered = false;
	
	//user input
	String userInput = "";
	int textBox = 0;
	String nameText = "";
	String passwordText = "";
	
	String signInText = "Sign In";
	
	public void settings() {
		size(400, 700);
		imgRainbow = loadImage("images/rainbowBG.jpg");
	}
	
	public void draw(){
		background(54);
		image(imgRainbow, 0, 0);
		
	    textAlign(CENTER);
	    textSize(34);
	    fill(0);
	    text(signInText, width / 2, 100);

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
	
	//method whenever keyboard is pressed
	public void keyPressed() {
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
		else if (key == ENTER) {
			  textBox = 0;
			  userInput = "";
		}
	}
	
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
	
	private void enterButton() {
		noStroke();
		textSize(20);

	    if (mouseX > 250 && mouseX < 350 && mouseY > 460 && mouseY < 550) { //hovering
	    	fill(0, 204, 102, 130); rect(250, 460, 100, 40, 90, 90, 90, 90);
	        isEnterHovered = true;
	        fill(0);
	    } else {
	    	fill(0, 204, 102, 230); rect(250, 460, 100, 40, 90, 90, 90, 90);
	    	isEnterHovered = false;
	        fill(0);
	    }

	    textAlign(CENTER, CENTER);
	    text("Enter", 300, 480);
	}
	
	private void backBar() {
		noStroke();
		textSize(20);

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
	    text("Back", 200, 670);
	}
	
	private void checkUser() {
		try {
			// create a Scanner object
		    Scanner scanner = new Scanner(new File("data/txt/userInfo"));

		    // read first line which contains user name
		    String[] firstLine = scanner.nextLine().split(": ", 2);
		    String[] secondLine = scanner.nextLine().split(": ", 2);
		    if (firstLine[0].equals(nameText) && secondLine[0].equals(passwordText)) {
		    	//delete this window
			    surface.setVisible(false);

		    	//create a new window for their profile
			    sketchProfile sketchProfile = new sketchProfile();
			    PApplet.runSketch(new String[]{"ICSProject.TheCloset.sketchMenu"}, sketchProfile);;
		    } 
		    else {
		    	signInText = "Retry";
		    	nameText = "";
		    	passwordText = "";
		    }

		    // Close the scanner
		    scanner.close();
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		}
	}
}
