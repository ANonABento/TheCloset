package ICSProject.TheCloset;

import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class sketchProfile extends PApplet {
	
	PImage imgRainbow3;

    private profileUser userProfile;
    boolean isExitHovered = false;
	boolean isEnterHovered = false;

    public void settings() {
        size(400, 700);
        imgRainbow3 = loadImage("images/rainbow3BG.jpg");
        checkUser();
    }

    public void draw() {
    	image(imgRainbow3, -250, 0, 900, 700);
    	
        displayUserProfile();
        
        //back bar
      	backBar();
    }

    private void checkUser() {
        try {
            Scanner scanner = new Scanner(new File("data/txt/userInfo"));

            userProfile = new profileUser();
            readUserProfile(scanner);

            // Close the scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readUserProfile(Scanner scanner) {
        // check if there is another line in the file
        if (scanner.hasNextLine()) {
            // store that line as a string
            String line = scanner.nextLine();
            // split the line into two parts using ": "
            String[] arrLine = line.split(": ", 2);
            // extract array into two strings
            String label = arrLine[0];
            String value = arrLine[1];
            // switch statement to match the label update user profile
            switch (label) {
                case "Username":
                    userProfile.username = value;
                    break;
                case "Password":
                    userProfile.password = value;
                    break;
                case "Orientation":
                    userProfile.orientation = value;
                    break;
                case "Status":
                    userProfile.toStatus(value);
                    break;
                case "Identity":
                    userProfile.identity = value;
                    break;
            }
            // recursive call
            readUserProfile(scanner);
        }
    }
    
    public void mousePressed() {
		//exit bar
	    if (isExitHovered) {
	    	//delete this window
		    surface.setVisible(false);
		    
	    	//create a new window for their profile
		    sketchMenu sketchMenu = new sketchMenu();
		    PApplet.runSketch(new String[]{"ICSProject.TheCloset.sketchMenu"}, sketchMenu);
	    }
    }

    private void displayUserProfile() {
        // Display user profile information on the screen
        textAlign(CENTER);
        textSize(20);
        fill(255);
        text("User Profile", width / 2, 50);

        // Display user information
        textSize(28);
        text("Username: " + userProfile.username, width / 2, 200);
        text("Orientation: " + userProfile.orientation, width / 2, 240);
        text("Identity: " + userProfile.identity, width / 2, 280);
        text("Status: " + userProfile.status, width / 2, 320);
    }
    
    private void backBar() {
		noStroke();
		textSize(20);

	    if (mouseX > 20 && mouseX < 380 && mouseY > 650 && mouseY < 690) {
	        fill(244, 240); rect(20, 650, 360, 40, 90, 90, 90, 90);
	        isExitHovered = true;
	        fill(0);
	    } else {
	        fill(244, 30); rect(20, 650, 360, 40, 90, 90, 90, 90);
	        isExitHovered = false;
	        fill(244);
	    }

	    textAlign(CENTER, CENTER);
	    text("Back", 200, 670);
	}
}
