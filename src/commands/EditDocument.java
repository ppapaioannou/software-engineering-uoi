/*
** May 2020 
**
*******************************EditDocument*******************************
**************************************************************************
** The way that we choose to implement this class in the 
** editor is with a toggle button.
**
** The User can click the toggle button once to be able to edit
** the contents of the editor. 
** When the toggle button is pressed again
** the TextArea in now uneditable and the Document contents will be 
** updated and can then be turned to speech 
**************************************************************************
**
**
*/
package commands;


import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import model.Document;

public class EditDocument extends Command{
	
	private JTextArea textArea;
	
	private Document currentDocument;
	
	private boolean editableTextArea = false;
	
	public EditDocument(JTextArea textArea,Document currentDocument) {
		this.textArea=textArea;
		this.currentDocument=currentDocument;
	}

	@Override
	public void doWork() {
		
		if(textArea.isVisible()) {
			nextState();
		}
		else {
			JOptionPane.showMessageDialog(null, "There is no document currently open to edit.\n"
					+ "Try opening or creating a new document.");
			nextState();
		}		
		
	}
	
	public void nextState() {
		if(editableTextArea==true) {
			editableTextArea=false;
			textArea.setEditable(false);
			textArea.setCaretColor(Color.WHITE);
			execute();
		}
		else {
			editableTextArea=true;
			textArea.setEditable(true);
			textArea.setCaretColor(Color.BLACK);
		}
	}

	@Override
	public void execute() {
		currentDocument.setContents(textArea.getText());
	}

	@Override
	public Command copy() {
		// TODO Auto-generated method stub
		return null;
	}
	

	
}
