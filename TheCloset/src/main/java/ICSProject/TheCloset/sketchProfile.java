package ICSProject.TheCloset;

import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class sketchProfile extends PApplet {
	
	private profile userProfile;
	
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

	        //empty profile
	        String[] arrProfile = new String[]{"", "", "", "", ""};
	        
	        //look through whole file
	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            String[] arrLine = line.split(": ", 2);

	            if (arrLine.length == 2) {
	                String label = arrLine[0];
	                String value = arrLine[1];

	                switch (label) {
	                    case "Username":
	                    	arrProfile[0] = value;
	                        break;
	                    case "Password":
	                    	arrProfile[1] = value;
	                        break;
	                    case "GenderOrientation":
	                    	arrProfile[2] = value;
	                        break;
	                    case "Status":
	                    	arrProfile[3] = value;
	                        break;
	                    case "GenderIdentity":
	                    	arrProfile[4] = value;
	                        break;
	                }
	            }
	        }

	        // Close the scanner
	        scanner.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
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
        text("Gender Orientation: " + userProfile.orientation, width / 2, 130);
        text("Gender Identity: " + userProfile.identity, width / 2, 160);
        text("Status: " + userProfile.status, width / 2, 190);
    }
}
