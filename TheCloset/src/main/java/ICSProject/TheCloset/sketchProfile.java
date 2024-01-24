package ICSProject.TheCloset;

import processing.core.PApplet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class sketchProfile extends PApplet {
	
	private profileUser userProfile;
	
	public void settings() {
		size(400, 700);
		checkUser();
	}
	
	public void draw(){
		displayUserProfile();
	}
	
	private void checkUser() {
        try {
            Scanner scanner = new Scanner(new File("data/txt/userInfo"));

            userProfile = new profile();
            readUserProfile(scanner);

            // Close the scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
	
	private void readUserProfile(Scanner scanner) {
	    //check if there is another line in the file
	    if (scanner.hasNextLine()) {
	        //store that line as a string
	        String line = scanner.nextLine();
	        //split the line into two parts using ": "
	        String[] arrLine = line.split(": ", 2);
	        //extract array into two strings
	        String label = arrLine[0];
	        String value = arrLine[1];
	        //switch statement to match the label update user profile
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
	    //recursive call
	    readUserProfile(scanner);
	    }
	}
	
	private void displayUserProfile() {
        // Display user profile information on the screen
        background(255);
        textAlign(CENTER);
        textSize(20);
        fill(0);
        text("User Profile", width / 2, 50);

        // Display user information
        textSize(16);
        text("Username: " + userProfile.username, width / 2, 100);
        text("Orientation: " + userProfile.orientation, width / 2, 130);
        text("Identity: " + userProfile.identity, width / 2, 160);
        text("Status: " + userProfile.status, width / 2, 190);
    }
}
