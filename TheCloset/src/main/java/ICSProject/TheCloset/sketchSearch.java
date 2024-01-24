package ICSProject.TheCloset;

import processing.core.PApplet;
import processing.core.PImage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the search screen of the application using Processing.
 * It allows users to search for names in a database and displays matching results.
 */
public class sketchSearch extends PApplet {

	//variable declaration
	//array list for names that match
    List<String> matchingNames = new ArrayList<>();
    
    //button check
    boolean isExitHovered = false;
	boolean isEnterHovered = false;
	
	//user input
	String userInput = "";
	String searchBarInput = "";
    
	//images
    PImage imgRainbow2;

    /**
     * Setup method to initialize the search screen.
     * Loads the background image.
     */
    public void settings() {
        size(400, 700);
        imgRainbow2 = loadImage("images/rainbow2BG.jpg");
    }

    /**
     * Draw method to continuously update and render the search screen.
     * Displays the search bar, matching names, and handles user interactions.
     */
    public void draw() {
        background(54);
        image(imgRainbow2, 0, 0, 600, 700);
        
        //display all gui
        drawSearchBar();
        displayMatchingNames();
        
        //back bar
      	backBar();
    }

    /**
     * Method to draw the search bar on the screen.
     */
    private void drawSearchBar() {
    	//settings
        fill(255, 230);
        rect(20, 20, width - 40, 40, 10);
        fill(0);
        textAlign(LEFT, CENTER);
        textSize(16);
        text("Search: " + searchBarInput, 30, 40); //search bar on top
    }

    /**
     * Method to display the matching names on the screen.
     */
    private void displayMatchingNames() {
        fill(255, 170);
        rect(20, 80, width - 40, height - 150, 10);

        //settings
        fill(0);
        textAlign(LEFT, TOP);
        textSize(16);

        //display all matching names
        for (int i = 0; i < matchingNames.size(); i++) {
            text(matchingNames.get(i), 30, 90 + i * 30);
        }
    }

    /**
     * Method called whenever a key is pressed.
     * Handles keyboard interactions for typing in the search bar.
     */
    //method whenever keyboard is pressed
  	public void keyPressed() {
  		//backspace to delete
  		if (keyCode == BACKSPACE) {
  			if (userInput.length() > 0) {
  			    userInput = userInput.substring(0, userInput.length() - 1);
  			    searchBarInput = userInput;
  			}
  		} 
  		//add key
  		else if (key != ENTER) {
  			userInput += key;
  			searchBarInput = userInput;
  		} 
  		//perform the search if entered
  		else if (key == ENTER) {
  			performSearch();
  			userInput = "";
  		}
  	}

  	/**
     * Method to perform the search based on the user input.
     * Reads the database and adds matching names to the list.
     */
    private void performSearch() {
    	//clear display
        matchingNames.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(new File("data/txt/database")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Check if the line contains the search input
                if (line.toLowerCase().contains(searchBarInput.toLowerCase())) {
                    matchingNames.add(line);
                    System.out.println(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Method called whenever the mouse is pressed.
     * Handles mouse interactions for clearing the search and navigating back.
     */
    public void mousePressed() {
    	searchBarInput = "";
    	userInput = "";
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
     * Method to draw the back bar and change its appearance based on hover state.
     */
    private void backBar() {
		noStroke();
		textSize(20);
		
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
