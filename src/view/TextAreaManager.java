/*
** May 2020 
**
********************************TextAreaManager********************************
***************************************************************************
** TextAreaManager concerns the main text area. It was made a separate 
** class to ease the Text2SpeechEditorView main class.
**
** It initializes the editor's main text area and also a secondary
** text area to display the number of lines at the left-hand side
*************************************************************************
**
**
*/
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;

public class TextAreaManager {
	
	private JScrollPane scrollBar;
	private JFrame frame;
	private JTextArea textArea;
	
	public TextAreaManager(JFrame frame, JTextArea textArea) {
		this.frame=frame;
		this.textArea=textArea;
	}

	public  void initMainTextArea() {
		setTextArea(textArea,frame);
		setLineTextArea(textArea,frame);
	}
	
	private  void setTextArea(JTextArea textArea, JFrame frame) {
		textArea.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		textArea.setBackground(Color.WHITE);
		textArea.setEditable(false);
		//textArea.setFocusable(false);
		//textArea.setCaretColor(Color.WHITE);
		frame.getContentPane().add(textArea, BorderLayout.CENTER);
		
		scrollBar = new JScrollPane(textArea);
		frame.getContentPane().add(scrollBar);
		
		textArea.setVisible(false);
		
	}
	
	private  void setLineTextArea(JTextArea textArea, JFrame frame) {
		JTextArea lines = new JTextArea("1");
		lines.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lines.setBackground(Color.LIGHT_GRAY);
		lines.setEditable(false);
		//  Code to implement line numbers inside the JTextArea
		textArea.getDocument().addDocumentListener(new DocumentListener() {
			public String getText() {
				int caretPosition = textArea.getDocument().getLength();
	            Element root = textArea.getDocument().getDefaultRootElement();
	            String text = "1" + System.getProperty("line.separator");
	            for(int i = 2; i < root.getElementIndex(caretPosition) + 2; i++) {
	            	text += i + System.getProperty("line.separator");
	            }
	            return text;
	         }
	         @Override
	         public void changedUpdate(DocumentEvent de) {
	            lines.setText(getText());
	         }
	         @Override
	         public void insertUpdate(DocumentEvent de) {
	            lines.setText(getText());
	         }
	         @Override
	         public void removeUpdate(DocumentEvent de) {
	            lines.setText(getText());
	         }
	      });
		
		scrollBar.setRowHeaderView(lines);
	}
	
	

}
