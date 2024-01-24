package ICSProject.TheCloset;

import java.io.File;
import processing.core.*;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.utils.IOUtils;

public class systemRobot implements Runnable {

    // variable declaration
    private static final boolean TRACE_MODE = false;
    static String botName = "super";
    private sketchRobot parent;
    String userResponse;
    
    voiceRobot toVoice = new voiceRobot();

    public systemRobot(sketchRobot parent) {
        this.parent = parent;
    }

    public void run() {
        try {
            String resourcesPath = getResourcesPath();
            MagicBooleans.trace_mode = TRACE_MODE;
            Bot bot = new Bot("super", resourcesPath);
            Chat chatSession = new Chat(bot);
            bot.brain.nodeStats();
            String textLine;

            while (true) {
                textLine = userResponse;
                System.out.println(textLine);

                if (textLine != null && !textLine.isEmpty()) {
                    String request = textLine;
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

    private static String getResourcesPath() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length() - 2);
        System.out.println(path);
        String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
        return resourcesPath;
    }

    public synchronized void userResponse(String userInput) {
        this.userResponse = userInput;
    }
}
