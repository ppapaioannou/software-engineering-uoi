/*
** May 2020 
**
****************************AboutThisDocument****************************
*************************************************************************
** AboutThisDocument is a command so that the Document info
** is easily accessible.
** It creates a frame with the title and author attributes
** as well as the creation date and the date that it was last saved
**
** Might be bothersome that the frame is too small if the title and/or 
** author values are long; then, they will not show properly.
** But this is a a simple text editor with speech capabilities, those  
** values shouldn't be that long. Go and write your novel elsewhere!
*************************************************************************
**
**
*/
package commands;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import model.Document;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Font;
import java.net.URL;

public class AboutThisDocument extends Command {
	
	private JFrame parentFrame;
	
	private Document currentDocument;
	
	private JFrame aboutDocumentFrame;
	
	public AboutThisDocument(JFrame parentFrame,Document currentDocument) {
		this.parentFrame=parentFrame;
		this.currentDocument=currentDocument;
	}

	@Override
	public void doWork() {
		execute();
		
	}

	@Override
	public void execute() {
		if(currentDocument.getCreationDate()==null) {
			JOptionPane.showMessageDialog(parentFrame, "There is no document currently open.\n"
					+ "Try opening or creating a new document.");
		}
		else {
			aboutDocumentFrame();
		}
		
		
	}

	private void aboutDocumentFrame() {
		aboutDocumentFrame = new JFrame("About this Document...");
		
		URL iconURL = getClass().getResource("/view/info.png");
		ImageIcon icon = new ImageIcon(iconURL);
		aboutDocumentFrame.setIconImage(icon.getImage());
		
		aboutDocumentFrame.setResizable(false);
		aboutDocumentFrame.setAlwaysOnTop(true);
		aboutDocumentFrame.getContentPane().setBackground(Color.LIGHT_GRAY);
		aboutDocumentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		aboutDocumentFrame.setSize(337, 221);
		aboutDocumentFrame.setLocationRelativeTo(parentFrame);
		aboutDocumentFrame.getContentPane().setLayout(null);
		
		setFrameLayout();
		
		aboutDocumentFrame.setVisible(true);
		
	}

	private void setFrameLayout() {
		setTitleField();
		
		setAuthorField();

		setCreationDateLabel();
		
		setLastSavedDateLabel();
		
	}

	private void setTitleField() {
		JTextField title = new JTextField(currentDocument.getTitle());
		title.setOpaque(false);
		title.setRequestFocusEnabled(false);
		title.setBorder(null);
		title.setEditable(false);
		title.setFocusTraversalKeysEnabled(false);
		title.setFocusable(false);
		title.setBackground(Color.LIGHT_GRAY);
		title.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		title.setBounds(22, 13, 260, 30);
		aboutDocumentFrame.getContentPane().add(title);
		
	}

	private void setAuthorField() {
		JTextField author = new JTextField("By : "+currentDocument.getAuthor());
		author.setBorder(null);
		author.setRequestFocusEnabled(false);
		author.setOpaque(false);
		author.setEditable(false);
		author.setFocusTraversalKeysEnabled(false);
		author.setFocusable(false);
		author.setBackground(Color.LIGHT_GRAY);
		author.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		author.setBounds(22, 48, 260, 30);
		aboutDocumentFrame.getContentPane().add(author);
		
	}

	private void setCreationDateLabel() {
		JLabel creationDate = new JLabel("Created - "+currentDocument.getCreationDate());
		creationDate.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		creationDate.setBounds(22, 91, 260, 30);
		aboutDocumentFrame.getContentPane().add(creationDate);
		
	}

	private void setLastSavedDateLabel() {
		JLabel lastSavedDate = new JLabel("Last saved on - "+currentDocument.getLastSaveDate());
		lastSavedDate.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lastSavedDate.setBounds(22, 120, 260, 30);
		aboutDocumentFrame.getContentPane().add(lastSavedDate);
		
	}
	
	
	/*
	 * copy not implemented because this command doesn't need to be replayed
	 */
	
	@Override
	public Command copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
