/*
** May 2020 
**
*******************************SaveDocument*******************************
**************************************************************************
** SaveDocument uses a JFileChooser to handle the file path.
** After the file path has been set by the User, all the contents 
** of the textArea are written to the specified file.
**
** The file name will be already set from the currentDocument's title value,
** but the User can freely change it.
**
** Only .txt files are supported.
**************************************************************************
**
**
*/
package commands;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Document;

public class SaveDocument extends Command {
	
	private JFrame parentFrame;
	
	private Document currentDocument;
	
	private String saveFilename;
	
	
	public SaveDocument(JFrame parentFrame,Document currentDocument) {
		this.parentFrame=parentFrame;
		this.currentDocument=currentDocument;
		saveFilename = "";
		
	}

	public SaveDocument(Document currentDocument, String saveFilename) {
		this.currentDocument=currentDocument;
		this.saveFilename=saveFilename;
		
	}

	@Override
	public void doWork() {
		if(currentDocument.getCreationDate()==null) {
			JOptionPane.showMessageDialog(parentFrame, "There is no document currently open.\n"
					+ "Try opening or creating a new document.");
		}
		else {
			JFileChooser saveFileChooser = new JFileChooser();
			JTextField filename = new JTextField();
			JTextField directory = new JTextField();
			
			saveFileChooser.setFileFilter(new FileNameExtensionFilter("Txt File","txt"));
			
			saveFileChooser.setSelectedFile(new File(currentDocument.getTitle()+".txt"));
			
			int userInput = saveFileChooser.showSaveDialog(parentFrame);
			
		
			
			if(userInput == JFileChooser.APPROVE_OPTION) {
				filename.setText(saveFileChooser.getSelectedFile().getName());
				directory.setText(saveFileChooser.getCurrentDirectory().toString());
				saveFilename=directory.getText()+"/"+filename.getText();
				
				if(saveFilename.contains(".txt")) {
					saveFilename=saveFilename.replace(".txt", "");
				}
				
				commands.add(copy());
				
				execute();
				
			}
			if(userInput == JFileChooser.CANCEL_OPTION) {
				filename.setText("You pressed cancel");
				directory.setText("");
			}
		}
		
	}

	@Override
	public void execute() {
		currentDocument.setLastSaveDate();
		
	    try {
	    	FileWriter myWriter = new FileWriter(saveFilename+".txt");
	    	myWriter.write(currentDocument.getContentsString());
	    	myWriter.close();
	    }
	    catch (IOException error) {
	    	JOptionPane.showMessageDialog(parentFrame, error);
	    }
		
	}

	@Override
	public Command copy() {
		Document newCurrentDocument = new Document();
		newCurrentDocument=this.currentDocument;
		return new SaveDocument(newCurrentDocument,new String(this.saveFilename));
	}

}
