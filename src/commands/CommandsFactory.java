/*
** May 2020 
**
*****************************COMMANDS_FACTORY*****************************
**************************************************************************
** Taking into account the future growth of this software
** a Parameterized Factory was implemented to make the addition
** of extra commands trivial.
**
** The factory is initialized inside the Text2SpeechEditorView main class
** with the necessary fields,
** 1 - A JFrame so that each subsequent new frame created by commands
**     is centered around the main frame.
** 2 - A JTextArea for the writing and editing commands.
** 3 - A JComboBox for the implementation of the encoding strategies.
** 4 - The Document that is currently open on the editor.
**
** Also the Text2SpeechAPI is initialized as well as the ReplayManager.
** 
** For more info about the Text2Speech part of the software go to
** the TextToSpeechAPI interface.
**************************************************************************
**
**
*/
package commands;


import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import model.Document;
import text2speechapis.TextToSpeechAPIFactory;

public class CommandsFactory {
	
	private Command currentCommand;
	
	private JFrame parentFrame;
	
	private JTextArea textArea;
	
	private JComboBox<String> comboBox;
	
	private Document currentDocument;
	
	private ReplayCommandManager replayManager;


	public CommandsFactory(JFrame parentFrame,JTextArea textArea,
			JComboBox<String> comboBox,Document currentDocument) {
		
		this.parentFrame=parentFrame;
		this.textArea=textArea;
		this.comboBox=comboBox;
		this.currentDocument=currentDocument;
		
		currentDocument.setAudioManager(TextToSpeechAPIFactory.TTSAPI("FreeTTSAdapter"));
		
		replayManager = new ReplayManager();
	}
	
	public Command CreateCommand(String command) {
		
		if(command.equals("NewDocument")) {
			currentCommand = new NewDocument(parentFrame,textArea,currentDocument);
			return currentCommand;
		}
		
		else if(command.equals("EditDocument")) {
			currentCommand = new EditDocument(textArea, currentDocument);
			return currentCommand;
		}
		
		else if(command.equals("SaveDocument")) {
			currentCommand = new SaveDocument(parentFrame,currentDocument );
			return currentCommand;
		}
		
		else if(command.equals("OpenDocument")) {
			currentCommand = new OpenDocument(parentFrame, textArea, currentDocument);
			return currentCommand;
		}
		
		else if(command.equals("DocumentToSpeechNormal")) {
			currentCommand = new DocumentToSpeech(parentFrame,currentDocument, "normal");
			return currentCommand;
		}
		
		else if(command.equals("DocumentToSpeechReversed")) {
			currentCommand = new DocumentToSpeech(parentFrame,currentDocument, "reverse");
			return currentCommand;
		}
		
		else if(command.equals("DocumentToSpeechEncoded")) {
			currentCommand = new DocumentToSpeech(parentFrame,currentDocument, "encode");
			return currentCommand;
		}
		
		else if(command.equals("LineToSpeechNormal")) {
			currentCommand = new LineToSpeech(parentFrame, currentDocument, "normal");
			return currentCommand;
		}
		
		else if(command.equals("LineToSpeechReversed")) {
			currentCommand = new LineToSpeech(parentFrame, currentDocument, "reverse");
			return currentCommand;
		}
		
		else if(command.equals("LineToSpeechEncoded")) {
			currentCommand = new LineToSpeech(parentFrame, currentDocument, "encode");
			return currentCommand;
		}
		
		else if(command.equals("TuneAudio")) {
			currentCommand = new TuneAudio(parentFrame, currentDocument);
			return currentCommand;
		}
		
		else if(command.equals("TuneEncoding")) {
			currentCommand = new TuneEncoding(comboBox, currentDocument);
			return currentCommand;
		}
		
		else if(command.equals("Replay")) {
			currentCommand = new ReplayCommand(parentFrame,replayManager);
			return currentCommand;
		}
		
		else if(command.equals("AboutThisDocument")) {
			currentCommand = new AboutThisDocument(parentFrame, currentDocument);
			return currentCommand;
		}
		
		else if(command.equals("About")) {
			currentCommand = new AboutThisProgram(parentFrame);
			return currentCommand;
		}
		
		else if(command.equals("ViewHelp")) {
			currentCommand = new ViewHelp();
			return currentCommand;
		}
		
		else {
			return null;
		}
		
	}	

}
