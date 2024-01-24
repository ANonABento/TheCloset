package ICSProject.TheCloset;

import java.io.File;
import processing.core.*;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.utils.IOUtils;

/**
 * The `systemRobot` class represents the system that handles the interaction between the user and the chatbot.
 * It uses the AIML (Artificial Intelligence Markup Language) approach for natural language processing.
 * This class implements the Runnable interface to run the chatbot in a separate thread.
 */
public class systemRobot implements Runnable {

    // variable declaration
    private static final boolean TRACE_MODE = false;
    static String botName = "super";
    private sketchRobot parent;
    String userResponse;
    
    //vice object
    voiceRobot toVoice = new voiceRobot();

    public systemRobot(sketchRobot parent) {
        this.parent = parent;
    }

    /**
     * Constructs a new `systemRobot` instance with a reference to the parent sketch.
     *
     * @param parent The sketchRobot instance that serves as the parent.
     */
    public void run() {
        try {
        	//bot setup and settings
            String resourcesPath = getResourcesPath();
            MagicBooleans.trace_mode = TRACE_MODE;
            Bot bot = new Bot("super", resourcesPath);
            Chat chatSession = new Chat(bot);
            bot.brain.nodeStats();
            String textLine;

            //keep looping
            while (true) {
                textLine = userResponse;
                System.out.println(textLine); //to future me: very important do not delete

                //if not empty
                if (textLine != null && !textLine.isEmpty()) {
                    String request = textLine;
                    //magic stuff
                    if (MagicBooleans.trace_mode) {
                        System.out.println("STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));
                    }
                    String response = chatSession.multisentenceRespond(request);
                    while (response.contains("&lt;")) {
                        response = response.replace("&lt;", "<");
                    }
                    while (response.contains("&gt;")) {
                        response = response.replace("&gt;", ">");
                    }

                    //send robot response
                    parent.changeRobotResponse(response);
                    
                    //speak
                    toVoice.speakText(response);
                    
                    //clear user response to avoid running the same response again
                    userResponse = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the path to the resources folder.
     *
     * @return The path to the resources folder.
     */
    private static String getResourcesPath() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length() - 2);
        System.out.println(path);
        String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
        return resourcesPath;
    }

    /**
     * Sets the user response for processing by the chatbot.
     *
     * @param userInput The user input to be processed by the chatbot.
     */
    public void userResponse(String userInput) {
    	//set new response from user input
        this.userResponse = userInput;
    }
}
