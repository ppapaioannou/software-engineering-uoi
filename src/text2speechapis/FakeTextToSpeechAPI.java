/*
** May 2020 
**
***************************FakeTextToSpeechAPI***************************
*************************************************************************
** FakeTextToSpeechAPI implements the TextToSpeechAPI methods 
** slightly differently so that testing is easier.
*************************************************************************
**
**
*/
package text2speechapis;

import java.util.ArrayList;

public class FakeTextToSpeechAPI implements TextToSpeechAPI {
	
	private ArrayList<String> content = new ArrayList<String>();
	
	private int volume;
	
	private int rate;
	
	private int pitch;
	

	@Override
	public void play(String content) {
		this.content.add(content);

	}
	
	public String getPlay() {
		String output="";
		for(String s : content) {
			output+=s+"\n";
		}
		output = output.substring(0,output.length()-1);
		return output;
	}

	@Override
	public void setVolume(int volume) {
		this.volume=volume;

	}
	
	public int getVolume() {
		return volume;
	}

	@Override
	public void setRate(int rate) {
		this.rate=rate;

	}
	
	public int getRate() {
		return rate;
	}
	
	@Override
	public void setPitch(int pitch) {
		this.pitch=pitch;

	}
	
	public int getPitch() {
		return pitch;
	}

}
