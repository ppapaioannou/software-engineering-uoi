/*
** May 2020 
**
*********************************Document*********************************
**************************************************************************
** The Document class was created to represent the editor's document
** and hold certain attributes.
**
** The document contents are stored inside an ArrayList of Line objects
** representing each line of the main text area.
**
** This class is also responsible for turning the contents of the document
** to speech as well as setting the encoding strategies. 
**************************************************************************
**
**
*/
package model;

import java.nio.file.attribute.FileTime;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import encodingstrategies.EncodingStrategy;

import text2speechapis.TextToSpeechAPI;

public class Document {
	
	private String title;
	private String author;
	private String creationDate;
	private String lastSaveDate;
	private ArrayList<Line> contents = new ArrayList<Line>();
	
	private TextToSpeechAPI audioManager;
	
	private EncodingStrategy encodingStrategy;
	
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setCreationDate() {
		creationDate=createTimeStamp();
	}
	
	public String getCreationDate() {
		return creationDate;
	}
	
	public void setLastSaveDate() {
		lastSaveDate=createTimeStamp();
	}
	
	public String getLastSaveDate() {
		return lastSaveDate;
	}
	
	public String createTimeStamp() {
		String pattern = "yyyy-MM-dd HH:mm:ss";
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	    String timeStamp = simpleDateFormat.format( new Date( System.currentTimeMillis() ) );
		return timeStamp;
	}
	
	public void setDateDetails(FileTime creationTime, FileTime lastModifiedTime) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
	    this.creationDate = simpleDateFormat.format( new Date( creationTime.toMillis() ) );
	    this.lastSaveDate = simpleDateFormat.format( new Date( lastModifiedTime.toMillis() ) );
	}

	
	public ArrayList<Line> getContents(){
		return contents;
	}
	
	public void setContents(String content) {
		contents.clear();
		for(String line : content.split("\n")) {
			Line lineContent = new Line(line);
			contents.add(lineContent);
		}	
		
	}
	
	public String getContentsString() {
		String DocumentContents = "";
		for(Line line : contents) {
			DocumentContents += line.getWordsString()+"\n";
		}
		if(DocumentContents.equals("")) {
			return DocumentContents;
		}
		else {
			DocumentContents = DocumentContents.substring(0,DocumentContents.length()-1);
			return DocumentContents;
		}
		
	}
	
	public void playContents() {
		for(Line line : contents) {
			line.setAudioManager(audioManager);
			line.playLine();
		}
	}
	
	public void playLine(int i) {
		Line line = contents.get(i);
		line.setAudioManager(audioManager);
		line.playLine();
		
	}
	

	public void playReverseContents() {
		for(int i=contents.size()-1; i>=0; i--){
			Line line=contents.get(i);
			line.setAudioManager(audioManager);
			line.playReverseLine();
		}
		
	}
		
	
	public void playReverseLine(int i) {
		Line line = contents.get(i);
		line.setAudioManager(audioManager);
		line.playReverseLine();
		
	}

	public void playEncodedContents() {
		for(Line line : contents) {
			line.setAudioManager(audioManager);
			line.setEncodingStrategy(encodingStrategy);
			line.playEncodedLine();
		}
		
	}

	public void playEncodedLine(int i) {
		Line line = contents.get(i);
		line.setAudioManager(audioManager);
		line.setEncodingStrategy(encodingStrategy);
		line.playEncodedLine();
		
	}

	public void setAudioManager(TextToSpeechAPI audioManager) {
		this.audioManager=audioManager;		
	}
	
	public TextToSpeechAPI getAudioManager() {
		return audioManager;
	}

	public void setEncodingStrategy(EncodingStrategy encodingStrategy) {
		this.encodingStrategy = encodingStrategy;
	}

	public EncodingStrategy getEncodingStrategy() {
		return encodingStrategy;
	}

	public boolean isEmpty() {
		String output = "";
		for(Line line : contents) {
			output+=line.getWordsString();
		}
		if(output.trim().length() == 0) {
			return true;
		}
		return false;
	}

}
