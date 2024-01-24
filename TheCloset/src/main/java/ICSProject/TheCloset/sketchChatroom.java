package ICSProject.TheCloset;

import processing.core.PApplet;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class sketchChatroom extends PApplet {

    ArrayList<String> messages = new ArrayList<>();
    String userText = "";
    String userInput = "";

    boolean isExitHovered = false;
    boolean isEnterHovered = false;

    public void settings() {
        size(400, 700);
        setupChatRoom();
    }

    public void draw() {
        background(32);
        displayChat();
        displayMessages();

        // back bar
        backBar();

        // input box
        displayInputBox();
    }

    private void setupChatRoom() {
        new Thread(this::readMessages).start();
    }

    private void readMessages() {
        try {
            Scanner scanner = new Scanner(new File("data/txt/chatroom1"));

            while (scanner.hasNextLine()) {
                // Check for new messages in the file
                String message = scanner.nextLine();
                addMessage(message);

                // Sleep so it doesn't continuously read
                Thread.sleep(2000);
            }
        } catch (FileNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }

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

    private void displayChat() {
        // Display a simple chat interface on the screen
        fill(255);
        textSize(16);
        textAlign(TOP, LEFT);
        text("Chatroom", 20, 30);
    }

    private void displayMessages() {
        // Display messages on the screen
        fill(255);
        textSize(14);
        textAlign(TOP, LEFT);

        int yPos = 60;
        int increment = 20; // Adjust this value for the desired spacing

        for (String message : messages) {
            text(message, 20, yPos);
            yPos += increment;
        }
    }

    private void addMessage(String message) {
        // Add messages to the list
        messages.add(message);
    }

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

    private void backBar() {
        noStroke();
        textSize(20);

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

        textAlign(CENTER, CENTER);
        text("Back", 200, 670);
    }

    private void displayInputBox() {
        fill(255);
        textSize(14);
        textAlign(TOP, LEFT);
        text("Type your message:", 20, 580);

        fill(255);
        rect(20, 600, 360, 40, 5);
        fill(0);
        text(userText, 30, 620);
    }
}
