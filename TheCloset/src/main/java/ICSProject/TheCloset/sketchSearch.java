package ICSProject.TheCloset;

import processing.core.PApplet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class sketchSearch extends PApplet {

    List<String> matchingNames = new ArrayList<>();
    String searchBarInput = "";

    public void settings() {
        size(400, 700);
    }

    public void draw() {
        background(54);
        drawSearchBar();
        displayMatchingNames();
    }

    private void drawSearchBar() {
        fill(255);
        rect(20, 20, width - 40, 40, 10);
        fill(0);
        textAlign(LEFT, CENTER);
        textSize(16);
        text("Search: " + searchBarInput, 30, 40);
    }

    private void displayMatchingNames() {
        fill(255);
        rect(20, 80, width - 40, height - 100, 10);

        fill(0);
        textAlign(LEFT, TOP);
        textSize(16);

        for (int i = 0; i < matchingNames.size(); i++) {
            text(matchingNames.get(i), 30, 90 + i * 30);
        }
    }

    public void keyPressed() {
        // Handle key presses in the search bar
        if (keyCode == BACKSPACE && searchBarInput.length() > 0) {
            searchBarInput = searchBarInput.substring(0, searchBarInput.length() - 1);
        } else if (keyCode != ENTER && key != BACKSPACE) {
            searchBarInput += key;
        }

        // Perform search when Enter key is pressed
        if (keyCode == ENTER) {
            performSearch();
        }
    }

    private void performSearch() {
        matchingNames.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(new File("data/txt/database.txt")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Check if the line contains the search input
                if (line.toLowerCase().contains(searchBarInput.toLowerCase())) {
                    matchingNames.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
