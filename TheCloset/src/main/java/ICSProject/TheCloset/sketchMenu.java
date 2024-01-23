package ICSProject.TheCloset;

import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class sketchMenu extends PApplet{
	
	PImage imgDoorOpen;
	PImage imgDoorClosed;
	PImage imgRainbow;
	
	String username;
	boolean loggedIn = false;
	
	boolean isExitHovered = false;
	
	public void settings() {
		size(400, 700);
		checkUser();
	}
	
	public void setup() {
		imgDoorClosed = loadImage("images/doorClosed.png");
		imgDoorOpen = loadImage("images/doorOpen.png");
		imgRainbow = loadImage("images/rainbowBG.jpg");
	}
	
	public void draw(){
		background(54);
		image(imgRainbow, 0, 0);
		
		//header
		fill(0, 30); rect(20, 20, 360, 120, 40, 40, 40, 40);
		welcomeUser();
		
		//text customization
		textSize(20);
		textAlign(CENTER, CENTER);
		
		//exit bar
		exitBar();
		
		//main body
		fill(0, 30); rect(70, 165, 100, 50, 90, 90, 0, 0);
		fill(0); text("Profile", 118, 185);
		image(checkHover(70, 170, 200, 380, 1), 5, 180, 220, 220);
		
		fill(0, 30); rect(235, 165, 100, 50, 90, 90, 0, 0);
		fill(0); text("Search", 283, 185);
		image(checkHover(240, 330, 200, 380, 2), 170, 180, 220, 220);
		
		fill(0, 30); rect(70, 415, 100, 50, 90, 90, 0, 0);
		fill(0); text("Chatroom", 118, 435);
		image(checkHover(70, 170, 450, 630, 3), 5, 430, 220, 220);
		
		fill(0, 30); rect(235, 415, 100, 50, 90, 90, 0, 0);
		fill(0); text("Chatbot", 283, 435);
		image(checkHover(240, 330, 450, 630, 4), 170, 430, 220, 220);
		
		
//		//output position of mouse
//		System.out.println("x: "+mouseX);
//		System.out.println("y: "+mouseY);
	}
	
	//method whenever mouse is pressed
	public void mousePressed() {
		//if clicked in a button
		if (mouseX > 70 && mouseX < 160 && mouseY > 200 & mouseY < 360) {
			//delete this window
		    surface.setVisible(false);
		    
		    if (loggedIn) {
		    	//create a new window for their profile
			    sketchProfile sketchProfile = new sketchProfile();
			    PApplet.runSketch(new String[]{"ICSProject.TheCloset.sketchProfile"}, sketchProfile);
		    }
		    else {
		    	sketchLogin sketchLogin = new sketchLogin();
			    PApplet.runSketch(new String[]{"ICSProject.TheCloset.sketchLogin"}, sketchLogin);
		    }
		}
		else if (mouseX > 240 && mouseX < 330 && mouseY > 200 & mouseY < 360) {
			//delete this window
		    surface.setVisible(false);
		    	
		    //create a new window for searching users
		    sketchSearch sketchSearch = new sketchSearch();
		    PApplet.runSketch(new String[]{"ICSProject.TheCloset.sketchRobot"}, sketchSearch);
		}
		else if (mouseX > 70 && mouseX < 160 && mouseY > 450 & mouseY < 610) {
			//delete this window
		    surface.setVisible(false);
		    	
		    //create a new window for the chatroom
		    sketchChatroom sketchChatroom = new sketchChatroom();
		    PApplet.runSketch(new String[]{"ICSProject.TheCloset.sketchChatroom"}, sketchChatroom);
		}
		else if (mouseX > 240 && mouseX < 330 && mouseY > 450 & mouseY < 610) {
			//delete this window
		    surface.setVisible(false);
		    	
		    //create a new window of the robot sketch
		    sketchRobot sketchRobot = new sketchRobot();
		    PApplet.runSketch(new String[]{"ICSProject.TheCloset.sketchRobot"}, sketchRobot);
		}
		
		//exit bar
	    if (isExitHovered) {
	        exit();
	    }
	}
	
	private PImage checkHover(int x1, int x2, int y1, int y2, int area) {
        if (mouseX > x1 && mouseX < x2 && mouseY > y1 && mouseY < y2) {
            return imgDoorOpen;
        } else {
            return imgDoorClosed;
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
	
	private void checkUser() {
        try {
        	//create a Scanner object
            Scanner scanner = new Scanner(new File("data/txt/blank"));

            //check if the file has any content
            if (scanner.hasNext()) {
            	loggedIn = true;
                //read first line which contains username
            	String[] firstLine = scanner.nextLine().split(": ", 2);
                username = firstLine[1];
            }
            else {
            	loggedIn = false;
            }

            // Close the scanner
            scanner.close();
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}
	
	private void welcomeUser() {
		String welcomeMsg;
		if (!loggedIn) {
			welcomeMsg = "Welcome!";
		}
		else {
			welcomeMsg = "Welcome back, " + username + "!";
		}
		
		textSize(28);
		textAlign(CENTER, CENTER);
		fill(0);
		text(welcomeMsg, 200, 80);
	}
}
		    
