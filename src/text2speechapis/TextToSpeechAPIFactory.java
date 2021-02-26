/*
** May 2020 
**
************************TextToSpeechAPIFactory************************
**********************************************************************
** A simple Parameterized Factory method to create the needed
** TextToSpeechAPI object.
**********************************************************************
**
**
*/
package text2speechapis;

public class TextToSpeechAPIFactory {
	
	private static TextToSpeechAPI ttsAPI;
	
	public static TextToSpeechAPI TTSAPI(String adapterType) {
		if(adapterType.equals("FreeTTSAdapter")) {
			ttsAPI = new FreeTTSAdapter();
			return ttsAPI;
		}
		else if(adapterType.equals("FakeTextToSpeechAPI")) {
			ttsAPI = new FakeTextToSpeechAPI();
			return ttsAPI;
		}
		else {
			return null;
		}
	}

}
