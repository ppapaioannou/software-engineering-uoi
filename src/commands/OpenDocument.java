/*
** May 2020 
**
*******************************OpenDocument*******************************
**************************************************************************
** OpenDocument uses a JFileChooser to handle the file path.
** After the file path has been set by the User, each line of the chosen
** file is added to the textArea of the editor.
** The file's attributes are also set as the currentDocument's.
** Also enables the textArea.
**
** Only .txt files are supported.
**************************************************************************
**
**
*/
package commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.UserPrincipal;


import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;


import model.Document;

public class OpenDocument extends Command{
	
	private JFrame parentFrame;
	private JTextArea textArea;
	
	private Document currentDocument;
	
	private String openFilename = "";
	
	private File openedFile;
	

	public OpenDocument(JFrame parentFrame,JTextArea textArea,Document currentDocument) {
		this.parentFrame=parentFrame;
		this.currentDocument=currentDocument;
		this.textArea=textArea;
		
		openFilename = "";
		
	}
	
	public OpenDocument(JTextArea textArea,Document currentDocument,String openFilename) {
		this.currentDocument=currentDocument;
		this.textArea=textArea;
		this.openFilename=openFilename;
		
	}

	@Override
	public void doWork() {
		JFileChooser openFileChooser = new JFileChooser();
		JTextField filename = new JTextField();
		JTextField directory = new JTextField();
		openFileChooser.setFileFilter(new FileNameExtensionFilter("Txt File","txt"));
		
		int userInput = openFileChooser.showOpenDialog(parentFrame);
		
		if(userInput == JFileChooser.APPROVE_OPTION) {
			filename.setText(openFileChooser.getSelectedFile().getName());
			directory.setText(openFileChooser.getCurrentDirectory().toString());
		    
			openFilename=directory.getText()+"/"+filename.getText();
			
			commands.add(copy());
			
			execute();
		}
		if(userInput == JFileChooser.CANCEL_OPTION) {
			filename.setText("You pressed cancel");
			directory.setText("");
		}
		
	}

	@Override
	public void execute() {
		writeFileToTextArea();
		setDocumentDetails();
		textArea.setVisible(true);
		
	}

	private void writeFileToTextArea() {
		try {
			openedFile = new File(openFilename);
			Scanner myReader = new Scanner(openedFile);
		    String data ="";
		    while (myReader.hasNextLine()) {
		    	data += myReader.nextLine()+"\n";
		    }
		    if(data.equals("")) {
			    currentDocument.setContents(data);
		    }
		    else {
		    	data = data.substring(0,data.length()-1);
			    currentDocument.setContents(data);
		    }
		    myReader.close();
		    textArea.setText(null);
		    textArea.append(currentDocument.getContentsString());
		} catch(IOException error){
			JOptionPane.showMessageDialog(parentFrame, error);
		}
		
	}
	
	private void setDocumentDetails() {
		Path path = Paths.get(openFilename);
		try {
			
			String title = openedFile.getName().substring(0,openedFile.getName().length()-4);
		    currentDocument.setTitle(title);
			
			FileOwnerAttributeView ownerAttributeView = Files.getFileAttributeView(path, FileOwnerAttributeView.class);
		    UserPrincipal owner = ownerAttributeView.getOwner();
		    currentDocument.setAuthor(owner.getName());
		    
		    
		    BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
		    currentDocument.setDateDetails(attr.creationTime(), attr.lastModifiedTime());
		    
		} catch (IOException error) {
			JOptionPane.showMessageDialog(parentFrame, error);
		}
		
	}

	@Override
	public Command copy() {
		JTextArea newTextArea = new JTextArea();
		newTextArea=this.textArea;
		Document newCurrentDocument = new Document();
		newCurrentDocument=this.currentDocument;
		return new OpenDocument(newTextArea,newCurrentDocument,new String(this.openFilename));
	}

}
