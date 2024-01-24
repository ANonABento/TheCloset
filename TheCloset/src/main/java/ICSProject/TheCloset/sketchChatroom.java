package ICSProject.TheCloset;

import processing.core.PApplet;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents a simple chatroom application using Processing
 * Users can view messages, type their own messages, and navigate back to a menu
 */
public class sketchChatroom extends PApplet {

	//variables
    ArrayList<String> messages = new ArrayList<>(); //list to store indefinite messages
    
    //userinput
    String userText = "";
    String userInput = "";

    //track mouse position over back button
    boolean isExitHovered = false;

    /**
     * Setup method to initialize the chatroom application
     */
    public void settings() {
        size(400, 700);
        setupChatRoom();
    }

    /**
     * Draw method to continuously update and render the chatroom interface
     */
    public void draw() {
        background(32);
        displayChat();
        displayMessages();

        // back bar
        backBar();

        // input box
        displayInputBox();
    }

    /**
     * Initialize the chatroom by starting a thread to read messages
     */
    private void setupChatRoom() {
        new Thread(this::readMessages).start();
    }

    /**
     * Read messages from a file and add them to the messages list
     */
    private void readMessages() {
        try {
        	//new scanner object
            Scanner scanner = new Scanner(new File("data/txt/chatroom1"));

            //while there's next line in file
            while (scanner.hasNextLine()) {
                //check for new messages in the file
                String message = scanner.nextLine();
                addMessage(message);

                //sleep so it doesn't continuously read
                Thread.sleep(2000);
            }
        } catch (FileNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write user input to a file and add it to the messages list
     *
     * @param input The user input message to be written and displayed
     */
    private void writeMessages(String input) {
        if (!userText.isEmpty()) {
            try {
                FileWriter writer = new FileWriter("data/txt/chatroom1", true);

                // Write user input to the file and add a new line
                writer.write(input + "\n");

                // Close the writer
                writer.close();

                // Add user input to the messages list
                addMessage(input);

                // Clear userText after writing
                userInput = "";
                userText = "";

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Display the chatroom title on the screen
     */
    private void displayChat() {
        // Display a simple chat interface on the screen
        fill(255);
        textSize(16);
        textAlign(TOP, LEFT);
        text("Chatroom", 20, 30);
    }

    /**
     * Display chat messages on the screen
     */
    private void displayMessages() {
        // Display messages on the screen
        fill(255);
        textSize(14);
        textAlign(TOP, LEFT);

        int yPos = 60;
        int increment = 20; //desired spacing

        //add message
        for (String message : messages) {
            text(message, 20, yPos);
            yPos += increment;
        }
    }

    /**
     * Add a message to the messages list
     *
     * @param message The message to be added to the list
     */
    private void addMessage(String message) {
        // Add messages to the list
        messages.add(message);
    }

    /**
     * Handle mouse click events, specifically for the exit bar
     */
    public void mousePressed() {
    	userInput = "";
    	userText = "";
        // exit bar
        if (isExitHovered) {
            // delete this window
            surface.setVisible(false);

            // create a new window for their profile
            sketchMenu sketchMenu = new sketchMenu();
            PApplet.runSketch(new String[]{"ICSProject.TheCloset.sketchMenu"}, sketchMenu);
        }
    }

    // method whenever keyboard is pressed
    /**
     * Handle keyboard events, including backspace and enter for user input
     */
    public void keyPressed() {
        if (keyCode == BACKSPACE) {
            if (userInput.length() > 0) {
                userInput = userInput.substring(0, userInput.length() - 1);
                userText = userInput;
            }
        } else if (key != ENTER && key != '\n') {
            userInput += key;
            userText = userInput;
        } else if (key == ENTER) {
            writeMessages(userInput);
        }
    }

    /**
     * Display the back bar with appropriate hover effects
     */
    private void backBar() {
        noStroke();
        textSize(20);

        //check hover
        if (mouseX > 20 && mouseX < 380 && mouseY > 650 && mouseY < 690) {
            fill(24, 240);
            rect(20, 650, 360, 40, 90, 90, 90, 90);
            isExitHovered = true;
            fill(255);
        } else {
            fill(24, 30);
            rect(20, 650, 360, 40, 90, 90, 90, 90);
            isExitHovered = false;
            fill(0);
        }

        //display
        textAlign(CENTER, CENTER);
        text("Back", 200, 670);
    }

    /**
     * Display the input box for the user to type messages
     */
    private void displayInputBox() {
    	//header
        fill(255);
        textSize(14);
        textAlign(TOP, LEFT);
        text("Type your message:", 20, 580);

        //body
        fill(255);
        rect(20, 600, 360, 40, 5);
        fill(0);
        text(userText, 30, 620);
    }
}
