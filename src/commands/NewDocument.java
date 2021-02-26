/*
** May 2020 
**
*******************************NewDocument*******************************
*************************************************************************
** NewDocument creates a new frame and asks for the preferred title 
** and author values and then sets them as the currentDocument's
** attributes, while also enabling the textArea of the editor.
** A keyListener checks for invalid inputs
**
** Nothing too complex here.
*************************************************************************
**
**
*/
package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import model.Document;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;

public class NewDocument extends Command {
	
	private JFrame parentFrame;
	private JTextArea textArea;
	
	private Document currentDocument;
	
	private String title;
	private String author;
	
	private JFrame newDocFrame;
	private JTextField titleTextField;
	private JTextField authorTextField;



	public NewDocument(JFrame parentFrame,JTextArea textArea,Document currentDocument) {
		this.parentFrame=parentFrame;
		this.currentDocument=currentDocument;
		this.textArea=textArea;
		
	}
	
	public NewDocument(JTextArea textArea,Document currentDocument,String title, String author) {
		this.textArea=textArea;
		this.currentDocument=currentDocument;
		this.title=title;
		this.author=author;
	}

	@Override
	public void doWork() {
		
		newDocumentFrame(parentFrame);
		
	}
	
	@Override
	public void execute() {
		currentDocument.setTitle(title);
		currentDocument.setAuthor(author);
		currentDocument.setCreationDate();
		currentDocument.setContents("");
		textArea.setVisible(true);
		textArea.setText(null);
		
	}
	
	private void newDocumentFrame(JFrame parentFrame) {
		newDocFrame = new JFrame("New Document");
		URL iconURL = getClass().getResource("/view/new-file.png");
		ImageIcon icon = new ImageIcon(iconURL);
		newDocFrame.setIconImage(icon.getImage());
		newDocFrame.setAlwaysOnTop(true);
		newDocFrame.setResizable(false);
		newDocFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		newDocFrame.setSize(456, 273);
		
		newDocFrame.setLocationRelativeTo(parentFrame);
		newDocFrame.getContentPane().setLayout(null);
		
		setFrameLayout();
		
		newDocFrame.setVisible(true);
		
	}
	
	private void setFrameLayout() {
		setTitleTextField();
		
		setAuthorTextField();
		
		JLabel noticeLabel = new JLabel("! - Title and Author fields can only contain letters and/or numbers.");
		noticeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		noticeLabel.setBounds(12, 155, 420, 16);
		newDocFrame.getContentPane().add(noticeLabel);
		
		handleButtons();
		
	}
	
	private void setTitleTextField() {
		JLabel TitleLabel = new JLabel("Title");
		TitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		TitleLabel.setBounds(56, 51, 97, 16);
		newDocFrame.getContentPane().add(TitleLabel);
		
		titleTextField = new JTextField();
		titleTextField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		titleTextField.setBounds(124, 48, 262, 25);
		newDocFrame.getContentPane().add(titleTextField);
		titleTextField.setColumns(10);
		titleTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!isAlNum(c) && (c != KeyEvent.VK_BACK_SPACE) && (c!=KeyEvent.VK_SPACE)) {
					e.consume();  // ignore event
				}
			}
		});
		
	}
	
	private void setAuthorTextField() {
		JLabel AuthorLabel = new JLabel("Author");
		AuthorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		AuthorLabel.setBounds(56, 110, 56, 16);
		newDocFrame.getContentPane().add(AuthorLabel);
		
		authorTextField = new JTextField();
		authorTextField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		authorTextField.setBounds(124, 107, 262, 25);
		newDocFrame.getContentPane().add(authorTextField);
		authorTextField.setColumns(10);
		authorTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!isAlNum(c) && (c != KeyEvent.VK_BACK_SPACE) && (c!=KeyEvent.VK_SPACE)) {
					e.consume();  // ignore event
				}
			}
		});
		
	}

	private void handleButtons() {
		setConfirmBtn();
		
		setCancelBtn();
		
	}
	
	private void setConfirmBtn() {
		JButton okayBtn = new JButton("Okay");
		okayBtn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		okayBtn.setBounds(220, 188, 97, 25);
		
		newDocFrame.getContentPane().add(okayBtn);
		okayBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				title=titleTextField.getText();
				author=authorTextField.getText();
				if(title.equals("") || author.equals("")) {
					JOptionPane.showMessageDialog(newDocFrame, "Title and Author fields cannot be empty.");
				}
				else {
					newDocFrame.dispose();
					
					execute();
					
					commands.add(copy());
					
				}
				
			}
			
		});
		
	}

	private void setCancelBtn() {
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		cancelBtn.setBounds(329, 188, 97, 25);
		newDocFrame.getContentPane().add(cancelBtn);
		cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				newDocFrame.dispose();
				
			}
			
		});
		
	}
	
	@Override
	public Command copy() {
		JTextArea newTextArea = new JTextArea();
		newTextArea = this.textArea;
		Document newCurrentDocument = new Document();
		newCurrentDocument=this.currentDocument;
		return new NewDocument(newTextArea,newCurrentDocument,new String(this.title),new String(this.author));
	}

	
	
	private boolean isLatinLetter(char c) {
	    return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
	}
	
	
	private boolean isAlNum(char c) {
		if(isLatinLetter(c) || Character.isDigit(c)) {
			return true;
		}
		else {
			return false;
		}
	}

	
}
