/*
** May 2020 
**
*****************************DocumentToSpeech*****************************
**************************************************************************
** DocumentToSpeech's job is to check if there are contents
** to be turned to speech and then calls the Document method
** Play*Contents().
**
** Right now the supported methods of speech are,
** 1 - PlayContents() ->        Read the contents from beginning to end.
**
** 2 - PlayReverseContents() -> Read the Document in reverse i.e. The last
**                              word of the last sentence and so on.
**
** 3 - PlayEncodedContents() -> Read the contents encoded to
**                              one of the many encoding strategies
**                              from beginning to end.
**
** It will be really easy to add extra ways in the future to read 
** the contents with a few modifications at the following classes :
** DocumentToSpeech and/or LineToSpeech, CommandsFactory and MenuManager.
**************************************************************************
**
**
*/
package commands;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Document;

public class DocumentToSpeech extends Command {
	
	JFrame parentFrame;
	
	Document currentDocument;
	String type;
	
	public DocumentToSpeech(JFrame parentFrame,Document currentDocument,String type) {
		this.parentFrame=parentFrame;
		this.currentDocument=currentDocument;
		this.type=type;
	}
	
	public DocumentToSpeech(Document currentDocument,String type) {
		this.currentDocument=currentDocument;
		this.type=type;
	}


	@Override
	public void doWork() {
		if(currentDocument.getCreationDate()==null) {
			JOptionPane.showMessageDialog(parentFrame, "There is no document currently open.\n"
					+ "Try opening or creating a new document.");
		}
		else if(currentDocument.isEmpty()) {
			JOptionPane.showMessageDialog(parentFrame, "Nothing for Kevin to say!"
					+ "\nThe Document is empty. Make sure you have opened \n"
					+ "and/or edited a document ('Edit' is a toggle button).");
		}
		else {
			execute();
			commands.add(copy());
		}
		
	}

	@Override
	public void execute() {
		if(type.equals("normal")) {
			currentDocument.playContents();	
		}
		else if(type.equals("reverse")) {
			currentDocument.playReverseContents();
		}
		else if(type.equals("encode")) {
			if(currentDocument.getEncodingStrategy()==null) {
				JOptionPane.showMessageDialog(parentFrame, "Please set the encoding method first");
			}
			else {
				currentDocument.playEncodedContents();
			}
			
		}
		
	}

	@Override
	public Command copy() {
		Document newCurrentDocument = new Document();
		newCurrentDocument=this.currentDocument;
		return new DocumentToSpeech(newCurrentDocument,new String(this.type));
	}

}
