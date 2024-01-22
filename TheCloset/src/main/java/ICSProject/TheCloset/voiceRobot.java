package ICSProject.TheCloset;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class voiceRobot {
	
	private static Voice voice;
	
	public voiceRobot() {
		//set the path to the MBROLA base directory
		//the link has to be the direct directory url of the mbrola library: 
		//a mbrola has already been included in this project directory so just change the user area
        System.setProperty("mbrola.base", "C:\\Users\\kevbe\\git\\Test\\TheCloset\\TheCloset\\mbrola");
        //set the FreeTTS voices to use the MbrolaVoiceDirectory
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory,de.dfki.lt.freetts.en.us.MbrolaVoiceDirectory");
        
        //set which voice to use
		voice = VoiceManager.getInstance().getVoice("mbrola_us1");
		
		//check if voice was found
		if (voice != null) {
	        voice.allocate();//allocating Voice
	        try {
	            voice.setRate(170);//rate of the voice
	            voice.setPitch(210);//pitch of the voice
	            voice.setVolume(3);//volume of the voice

	        } catch (Exception e1) {
	            e1.printStackTrace();
	        }

	    } else {
	        throw new IllegalStateException("Cannot find voice: " + voice);
	    }
	}
	
	public void SpeakText(String response) {
		//play the audio of what the text is
		voice.speak(response);
	}
}
