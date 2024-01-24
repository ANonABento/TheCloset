package ICSProject.TheCloset;

import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class represents the user profile screen of the application using Processing.
 * It displays the user's profile information, including username, orientation, identity, and status.
 */
public class sketchProfile extends PApplet {
	
	//images
	PImage imgRainbow3;

	//user info
    private profileUser userProfile;
    
    //buttons check
    boolean isExitHovered = false;
	boolean isEnterHovered = false;

	/**
     * Setup method to initialize the profile screen.
     * Loads the background image and retrieves the user profile information.
     */
    public void settings() {
        size(400, 700);
        imgRainbow3 = loadImage("images/rainbow3BG.jpg");
        checkUser();
    }

    /**
     * Draw method to continuously update and render the user profile screen.
     * Displays the user's profile information and provides an option to go back to the main menu.
     */
    public void draw() {
    	image(imgRainbow3, -250, 0, 900, 700);
    	
        displayUserProfile();
        
        //back bar
      	backBar();
    }

    /**
     * Method to check and retrieve the user profile information from the file.
     */
    private void checkUser() {
        try {
        	//new scanner object
            Scanner scanner = new Scanner(new File("data/txt/userInfo"));

            //read profile
            userProfile = new profileUser();
            readUserProfile(scanner);

            // Close the scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Recursive method to read the user profile information from the file.
     * Updates the user profile object accordingly.
     *
     * @param scanner Scanner object for reading from the file.
     */
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
    
    /**
     * Method called whenever the mouse is pressed.
     * Handles mouse interactions for the exit bar to return to the main menu.
     */
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

    /**
     * Method to display the user profile information on the screen.
     * Displays the user's username, orientation, identity, and status.
     */
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
    
    /**
     * Method to draw the back bar and change its appearance based on hover state.
     */
    private void backBar() {
		noStroke();
		textSize(20);

		//check hover
	    if (mouseX > 20 && mouseX < 380 && mouseY > 650 && mouseY < 690) {
	        fill(244, 240); rect(20, 650, 360, 40, 90, 90, 90, 90);
	        isExitHovered = true;
	        fill(0);
	    } else {
	        fill(244, 30); rect(20, 650, 360, 40, 90, 90, 90, 90);
	        isExitHovered = false;
	        fill(244);
	    }

	    //display
	    textAlign(CENTER, CENTER);
	    text("Back", 200, 670);
	}
}
