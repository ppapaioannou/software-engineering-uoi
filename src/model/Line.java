/*
** May 2020 
**
***********************************Line***********************************
**************************************************************************
** The Line class was created to represent each line of the editor's document.
**
** This class is also responsible for turning the contents of the line
** to speech as well as setting the encoding strategies. 
**************************************************************************
**
**
*/
package model;

import java.util.ArrayList;

import encodingstrategies.EncodingStrategy;
import text2speechapis.TextToSpeechAPI;

public class Line {
	
	private ArrayList<String> words = new ArrayList<String>();
	
	private TextToSpeechAPI audioManager;
	
	private EncodingStrategy encodingStrategy;
	
	public Line(String line){
		String arr[] = line.split("((?<=\\s+)|(?=\\s+))");
		for(String s : arr) {
			words.add(s);
		}	
	}
	
	public ArrayList<String> getWords() {
		return words;
	}
	
	public String getWordsString() {
		String output = "";
		for(String word : words) {
			output+=word;
		}
		return output;
	}
	
	public void playLine() {
		String line="";
		for(String word : words) {
			line+=word;
		}
		audioManager.play(line);
	}
	
	public void playReverseLine() {
		String line="";
		for(int i=words.size()-1 ;i>=0; i--){
			String word=words.get(i);
			line+=word;
		}
		audioManager.play(line);
	}
	
	public void playEncodedLine() {
		String line="";
		for(String word : words) {
			line+=word;
		}
		audioManager.play(encodingStrategy.encode(line));
	}
	
	public void setAudioManager(TextToSpeechAPI audioManager) {
		this.audioManager=audioManager;
	}

	public void setEncodingStrategy(EncodingStrategy encodingStrategy) {
		this.encodingStrategy = encodingStrategy;
	}

}
