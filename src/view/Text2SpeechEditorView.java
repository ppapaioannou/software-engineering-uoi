/*
** May 2020 
**
***********************************MAIN***********************************
**************************************************************************
** This program is free software; you can redistribute it and/or modify it 
** under the terms of the GNU General Public License as published by the
** Free Software Foundation; either version 2 of the License, 
** or (at your option) any later version.
**
** This program is distributed in the hope that it will be useful
** but WITHOUT ANY WARRANTY; without even the implied warranty
** of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the 
** GNU General Public License for more details.
**************************************************************************
** This program was created for educational purposes under the study of
** Software Engineering at the Dept. of Computer Science & Engineering at
** University of Ioannina, taught by Apostolos Zarras 
** during the spring semester of 2020.
**
** Created by:
** Eleni Mouzaki             email: cs03280[at]uoi.gr
** Panagiotis Papaioannou    email: cs03309[at]uoi.gr
** Paraschos Ferrou-Graven   email: cs03359[at]uoi.gr
**************************************************************************
**************************************************************************
** Creation of the main frame for the editor as well as 
** the initialization of the text area and the menus.
**************************************************************************
**
**
*/
package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.JComboBox;

import model.Document;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.net.URL;

public class Text2SpeechEditorView {
	
	private JFrame frame = new JFrame("Text2SpeechEditor");
	private JTextArea textArea = new JTextArea();
	
	private JComboBox<String> comboBox = new JComboBox<String>();
	
	private Document currentDocument = new Document();
	
	private TextAreaManager textAreaManager = new TextAreaManager(frame,textArea);
	
	private MenuManager menus = new MenuManager(frame, textArea, comboBox, currentDocument);
	
	public Text2SpeechEditorView() {
		
		try {
			UIManager.setLookAndFeel(
		            UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e) { }
		URL iconURL = getClass().getResource("parrot.png");
		ImageIcon icon = new ImageIcon(iconURL);
		frame.setIconImage(icon.getImage());
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setSize(new Dimension(700, 700));
		frame.setForeground(Color.DARK_GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.DARK_GRAY);
		frame.setLocationRelativeTo(null);
		
		textAreaManager.initMainTextArea();
		
		
		
		setMenuBar(frame);
		
	}

	private void setMenuBar(JFrame frame2) {
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu(" File ");
		fileMenu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(fileMenu);
		
		menus.setFileMenu(fileMenu);
		
		
		JToggleButton editToggleButton = new JToggleButton("Edit");
		editToggleButton.setFocusable(false);
		editToggleButton.setFocusPainted(false);
		editToggleButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		menuBar.add(editToggleButton);
		
		menus.setEditToggleButton(editToggleButton);
		
		
		JMenu textToSpeechMenu = new JMenu(" Text To Speech ");
		textToSpeechMenu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(textToSpeechMenu);
		
		menus.setTextToSpeechMenu(textToSpeechMenu);
		
		
		
		JLabel encodedToLabel = new JLabel(" Encoded to  ");
		encodedToLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(encodedToLabel);
		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		comboBox.setFocusable(false);
		
		menuBar.add(comboBox);
		
		menus.setStrategies(comboBox, currentDocument);
		
		
		
		JButton replayButton = new JButton("Replay");

		replayButton.setFocusable(false);

		replayButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(replayButton);
		
		menus.setReplayButton(replayButton);
		
		
		JSeparator separator_4 = new JSeparator();
		menuBar.add(separator_4);
		
		
		JMenu otherMenu = new JMenu("?");
		otherMenu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(otherMenu);
		
		menus.setOtherMenu(otherMenu);

		
	}


	public static void main(String[] args) {
		Text2SpeechEditorView window = new Text2SpeechEditorView();
		window.frame.setVisible(true);

	}
	
}
