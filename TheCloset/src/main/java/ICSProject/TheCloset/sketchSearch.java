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

public class sketchSearch extends PApplet {

    List<String> matchingNames = new ArrayList<>();
    boolean isExitHovered = false;
	boolean isEnterHovered = false;
	
	//user input
	String userInput = "";
	String searchBarInput = "";
    
    PImage imgRainbow;

    public void settings() {
        size(400, 700);
        imgRainbow = loadImage("images/rainbowBG.jpg");
    }

    public void draw() {
        background(54);
        image(imgRainbow, 0, 0);
        
        drawSearchBar();
        displayMatchingNames();
        
        //back bar
      	backBar();
    }

    private void drawSearchBar() {
        fill(255, 230);
        rect(20, 20, width - 40, 40, 10);
        fill(0);
        textAlign(LEFT, CENTER);
        textSize(16);
        text("Search: " + searchBarInput, 30, 40);
    }

    private void displayMatchingNames() {
        fill(255, 90);
        rect(20, 80, width - 40, height - 150, 10);

        fill(0);
        textAlign(LEFT, TOP);
        textSize(16);

        for (int i = 0; i < matchingNames.size(); i++) {
            text(matchingNames.get(i), 30, 90 + i * 30);
        }
    }

  //method whenever keyboard is pressed
  	public void keyPressed() {
  		if (keyCode == BACKSPACE) {
  			if (userInput.length() > 0) {
  			    userInput = userInput.substring(0, userInput.length() - 1);
  			    searchBarInput = userInput;
  			}
  		} 
  		else if (key != ENTER) {
  			userInput += key;
  			searchBarInput = userInput;
  		} 
  		else if (key == ENTER) {
  			performSearch();
  			userInput = "";
  		}
  	}

    private void performSearch() {
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
}
