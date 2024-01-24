package ICSProject.TheCloset;

import processing.core.PApplet;
import processing.core.PImage;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class sketchChatroom extends PApplet {

    ConcurrentLinkedQueue<String> messageQueue = new ConcurrentLinkedQueue<>();
    
    public void settings() {
        size(400, 700);
        setupChatRoom();
    }

    public void draw() {
    	background(32);
        displayChat();
        displayMessages();
    }

    private void setupChatRoom() {
        // Start two threads, one for reading messages and one for writing messages
        new Thread(this::readMessages).start();
        new Thread(this::writeMessages).start();
    }

    private void readMessages() {
        try {
            Scanner scanner = new Scanner(new File("data/txt/chatroom1"));

            while (scanner.hasNextLine()) {
                //check for new messages in file
                String message = scanner.nextLine();
                displayMessage(message);

                //sleep so it doesn't continuously read
                Thread.sleep(2000);
            }
        } 
        catch (FileNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void writeMessages() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/txt/chatroom1", true));

            while (true) {
                // Read user input and write it to the file
                String userInput = reader.readLine();
                writer.write(userInput);
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayChat() {
        // Display a simple chat interface on the screen
        fill(255);
        textSize(16);
        textAlign(TOP, LEFT);
        text("Chat Room", 20, 30);
    }
    
    private void displayMessages() {
        // Display messages on the screen
        fill(255);
        textSize(14);
        textAlign(TOP, LEFT);

        int yPos = 60;
        while (!messageQueue.isEmpty()) {
            String message = messageQueue.poll();
            text(message, 20, yPos);
            yPos += 20;
        }
    }

    private void displayMessage(String message) {
        // Add messages to the queue
        messageQueue.add(message);
    }
}
