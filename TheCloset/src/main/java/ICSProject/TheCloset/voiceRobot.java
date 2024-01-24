package ICSProject.TheCloset;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

/**
 * The `voiceRobot` class represents a robot voice system that uses FreeTTS for speech synthesis.
 * It initializes the voice parameters and provides a method to speak a given text.
 */
public class voiceRobot {
	
	//voice object
	private static Voice voice;
	
	/**
     * Constructs a new `voiceRobot` instance and initializes the FreeTTS voice for speech synthesis.
     * The voice parameters such as rate, pitch, and volume are set during initialization.
     */
	public voiceRobot() {
		//set the path to the MBROLA base directory (I included in the project but if it doesn't work, then change to a direct path)
        System.setProperty("mbrola.base", "mbrola");
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
	
	/**
     * Speaks the given text using the configured voice parameters.
     *
     * @param response The text to be spoken.
     */
	public void speakText(String response) {
		//play the audio of what the text is
		voice.speak(response);
	}
}
