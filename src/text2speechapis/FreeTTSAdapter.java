/*
** May 2020 
**
******************************FreeTTSAdapter******************************
**************************************************************************
** The text-to-speech part of the editor is implemented with an external
** software library, specifically the FreeTTS 1.2.3 library
**
** FreeTTSAdapter implements the TextToSpeechAPI methods and it follows 
** the Adapter pattern so that the external library can be easily switched.
**************************************************************************
**
**
*/
package text2speechapis;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FreeTTSAdapter implements TextToSpeechAPI {
	
	VoiceManager freeVM;
	Voice voice;
	
	public FreeTTSAdapter() {
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		voice = VoiceManager.getInstance().getVoice("kevin16");
		voice.allocate();
	}

	@Override
	public void play(String string) {
		voice.speak(string);
	}

	@Override
	public void setVolume(int volume) {
        voice.setVolume((float)volume/100);
	}

	@Override
	public void setRate(int rate) {
		voice.setRate(rate);
	}
	
	@Override
	public void setPitch(int pitch) {
        voice.setPitch(pitch);
	}

}
