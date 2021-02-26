/*
** May 2020 
**
*******************************LineToSpeech*******************************
**************************************************************************
** LineToSpeech's job is to check if there are contents
** to be turned to speech, after that creates a simple frame asking the 
** User to enter the preferred line number and then 
** calls the Document method Play*Line(lineNumber).
**
** Right now the supported methods of speech are,
** 1 - PlayLine() ->        Read the chosen Line.
**
** 2 - PlayReverseLine() -> Read the chosen Line. in reverse i.e. The last
**                          word first and so on.
**
** 3 - PlayEncodedLine() -> Read the chosen Line encoded to
**                          one of the many encoding strategies.
**
** It will be really easy to add extra ways in the future to read 
** the contents with a few modifications at the following classes :
** LineToSpeech and or DocumentToSpeech, CommandsFactory and MenuManager.
**
** A keyListener checks for invalid inputs
**************************************************************************
**
**
*/
package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Document;
import java.awt.Font;

public class LineToSpeech extends Command {
	
	private JFrame parentFrame;
	
	private Document currentDocument;
	
	private String type;
	private int numLine;
	
	private JFrame lineToSpeechFrame;
	private JTextField lineNumberTextField;
	



	public LineToSpeech(JFrame parentFrame,Document currentDocument,String type) {
		this.parentFrame=parentFrame;
		this.currentDocument=currentDocument;
		this.type=type;
	}
	
	public LineToSpeech(Document currentDocument,String type,int numLine) {
		this.currentDocument=currentDocument;
		this.type=type;
		this.numLine=numLine;
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
			lineToSpeechFrame(parentFrame);
		}
	}
	

	@Override
	public void execute() {
		String lineNum=String.valueOf(numLine); //without this it can crash by replay
		if(!invalidInput(lineNum)) {			//if the previous chosen line is out of bounds
			if(type.equals("normal")) {
				currentDocument.playLine(numLine-1);
			}
			else if(type.equals("reverse")) {
				currentDocument.playReverseLine(numLine-1);
			}
			else if(type.equals("encode")) {
				if(currentDocument.getEncodingStrategy()==null) {
					JOptionPane.showMessageDialog(parentFrame, "Please set the encoding method first");
				}
				else {
					currentDocument.playEncodedLine(numLine-1);
				}	
			}
		}
		
	}
	
	
	public void lineToSpeechFrame(JFrame parentFrame) {
		lineToSpeechFrame = new JFrame("Choose Line...");
		
		URL iconURL = getClass().getResource("/view/speaker.png");
		ImageIcon icon = new ImageIcon(iconURL);
		
		lineToSpeechFrame.setIconImage(icon.getImage());
		lineToSpeechFrame.setAlwaysOnTop(true);
		lineToSpeechFrame.setResizable(false);
		lineToSpeechFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		lineToSpeechFrame.setSize(377, 162);
		lineToSpeechFrame.setLocationRelativeTo(parentFrame);
		lineToSpeechFrame.getContentPane().setLayout(null);
		
		setFrameLayout();
		
		lineToSpeechFrame.setVisible(true);
		
	}

	public void setFrameLayout() {
		JLabel lineLabel = new JLabel("Line");
		lineLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lineLabel.setBounds(71, 29, 56, 16);
		lineToSpeechFrame.getContentPane().add(lineLabel);
		
		lineNumberTextField = new JTextField();
		lineNumberTextField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lineNumberTextField.setBounds(167, 25, 145, 25);
		lineToSpeechFrame.getContentPane().add(lineNumberTextField);
		lineNumberTextField.setColumns(10);
		
		lineNumberTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isDigit(c) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();  // ignore event
				}
			}
		});
		
		handleButtons();
		
	}

	public void handleButtons() {
		setConfirmBtn();
		
		setCancelBtn();
		
	}

	private void setConfirmBtn() {
		JButton confirmButton = new JButton("Play");
		confirmButton.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		confirmButton.setBounds(153, 89, 97, 25);
		lineToSpeechFrame.getContentPane().add(confirmButton);
		confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = lineNumberTextField.getText();
				
				
				
				if(invalidInput(s)) {
					JOptionPane.showMessageDialog(lineToSpeechFrame, "Please enter a valid number.");
					
				}
				else {
					lineToSpeechFrame.dispose();
					
					numLine=Integer.parseInt(s);
										
					execute();
					
					commands.add(copy());
				}
			}
			
		});
		
	}

	private void setCancelBtn() {
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		cancelBtn.setBounds(262, 89, 97, 25);
		lineToSpeechFrame.getContentPane().add(cancelBtn);
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lineToSpeechFrame.dispose();
			}
			
		});	
		
	}

	@Override
	public Command copy() {
		Document newCurrentDocument = new Document();
		newCurrentDocument=this.currentDocument;
		int newNumLine =this.numLine+1;
		return new LineToSpeech(newCurrentDocument,new String(this.type),newNumLine);
	}
	
	private boolean invalidInput(String input) {
		ArrayList<String> lines = new ArrayList<String>();
		for(int i = 0 ; i<currentDocument.getContents().size(); i++) {
			
			lines.add(i,""+(i+1));
		}
		if(!lines.contains(input)) {
			return true;
		}
		return false;
	}
	
}
