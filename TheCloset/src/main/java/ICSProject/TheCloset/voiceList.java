package ICSProject.TheCloset;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class voiceList {
    private static Voice[] voices;

    public static void main(String[] args) throws Exception {
        // Set the path to the MBROLA base directory
        System.setProperty("mbrola.base", "C:\\Users\\kevin\\git\\TheCloset\\TheCloset\\mbrola");

        // Set the FreeTTS voices to use the MbrolaVoiceDirectory
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory,de.dfki.lt.freetts.en.us.MbrolaVoiceDirectory");

        VoiceManager vm;
        // VoiceManager uses the singleton approach to creating and providing an instance
        vm = VoiceManager.getInstance();
        // Get all the voices which the VoiceManager knows about
        voices = vm.getVoices();

        for (Voice voice : voices) {
            // Find out what voices are available.
            System.out.println(voice.getName() + " - " + voice.getDescription());
        }
    }
}
