package ICSProject.TheCloset;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class voiceTest {
	
	private static Voice voice;
	
	public voiceTest() {
		// Set the path to the MBROLA base directory
        System.setProperty("mbrola.base", "C:\\Users\\kevin\\git\\TheCloset\\TheCloset\\mbrola");
        // Set the FreeTTS voices to use the MbrolaVoiceDirectory
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory,de.dfki.lt.freetts.en.us.MbrolaVoiceDirectory");
        
		voice = VoiceManager.getInstance().getVoice("mbrola_us1");
		
		if (voice != null) {
	        voice.allocate();// Allocating Voice
	        try {
	            voice.setRate(170);// Setting the rate of the voice
	            voice.setPitch(210);// Setting the Pitch of the voice
	            voice.setVolume(3);// Setting the volume of the voice

	        } catch (Exception e1) {
	            e1.printStackTrace();
	        }

	    } else {
	        throw new IllegalStateException("Cannot find voice: kevin16");
	    }
	}
	
	public static void SpeakText(String response) {
		voice.speak(response);
	}
}
