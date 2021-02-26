/*
** May 2020 
**
******************************MenuManager******************************
***********************************************************************
** MenuManager utilizes an extreme method of abstraction.              
** The commands that are implemented are added to the corresponding    
** String array that represents an existing menu and then              
** a specific method for that array populates the selected menu        
** and turns the commands to ActionListeners                           
**                                                                     
** This class takes into consideration the future growth of the software
** and makes the extensibility an easy task. In most cases to implement
** a new command, apart from the creation of the specific Command class
** and the CommandsFactory implementation of said command, you have
** to add that specific command to an existing array below.
**
** If there is need for a more specific menu create a new array.
***********************************************************************
**
**
*/
package view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

import commands.CommandsFactory;

import model.Document;

public class MenuManager {
	
	
	private CommandsFactory commandsFactory;
	
	private String[] fileManipulationCommands = 
		{"New Document","Open Document","Save Document","About This Document"};
	
	private String[] TextToSpeechMainCommands =
		{"Play Document","Play Line","Tune Audio"};
	
	private String[] TextToSpeechSubCommands =
		{"Normal","Reversed","Encoded"};
	
	private String[] encodingStrategies = 
		{"AtBash","Rot13","Caesar Cipher"};
	
	private String[] otherCommands = 
		{"About","View Help"};
	
	
	
	

	public MenuManager(JFrame parentFrame,JTextArea textArea,
			JComboBox<String> comboBox,Document currentDocument) {
		commandsFactory = new CommandsFactory(parentFrame, textArea, comboBox, currentDocument);
	}

	public void setFileMenu(JMenu fileMenu) {
		for(String command : fileManipulationCommands) {
			JMenuItem currentCommand = new JMenuItem(command);
			fileMenu.add(currentCommand);
			setFileMenuActionListeners(currentCommand,command);
			
		}
		
	}
	
	private void setFileMenuActionListeners(JMenuItem currentCommand, String command) {
		String commandType = command.replace(" ","");
		currentCommand.addActionListener(commandsFactory.CreateCommand(commandType));
	}
	
	public void setEditToggleButton(JToggleButton editToggleButton) {
		editToggleButton.addActionListener(commandsFactory.CreateCommand("EditDocument"));
	}

	public void setTextToSpeechMenu(JMenu textToSpeechMenu) {
		for(String mainCommand : TextToSpeechMainCommands) {
			if(mainCommand.contains("Play")) {
				JMenu speechCommand = new JMenu(mainCommand);
				textToSpeechMenu.add(speechCommand);
				if(mainCommand.equals("Play Document")) {
					setTextToSpeechSubMenu(speechCommand);
				}
				
				else if(mainCommand.equals("Play Line")) {
					setTextToSpeechSubMenu(speechCommand);
				}
			}
			
			else if(mainCommand.contains("Tune")) {
				JMenuItem tuneAudio = new JMenuItem("Tune Audio");
				textToSpeechMenu.add(tuneAudio);
				tuneAudio.addActionListener(commandsFactory.CreateCommand("TuneAudio"));
			}
			
		}
		
	}
	
	private void setTextToSpeechSubMenu(JMenu currentMenu) {
		for(String subCommand : TextToSpeechSubCommands) {
			JMenuItem currentSubCommand = new JMenuItem(subCommand);
			currentMenu.add(currentSubCommand);
			if(currentMenu.getText().contains("Document")) {
				setTextToSpeechActionListener(currentSubCommand, "Document", subCommand);
			}
			else if(currentMenu.getText().contains("Line")) {
				setTextToSpeechActionListener(currentSubCommand, "Line", subCommand);
			}
			
		}
		
	}
	
	private void setTextToSpeechActionListener(JMenuItem currentSubCommand, String command, String type) {
		String commandType="";
		if(command.equals("Document")) {
			commandType = "DocumentToSpeech"+type;
		}
		else if(command.equals("Line")) {
			commandType = "LineToSpeech"+type;
		}
		currentSubCommand.addActionListener(commandsFactory.CreateCommand(commandType));
	}
	
	public void setStrategies(JComboBox<String> comboBox,Document currentDocument) {
		for(String strategy : encodingStrategies) {
			comboBox.addItem(strategy);
		}
		comboBox.setSelectedIndex(-1);
		
		comboBox.addActionListener(commandsFactory.CreateCommand("TuneEncoding"));
		
	}
	
	public void setReplayButton(JButton replayButton) {
		replayButton.addActionListener(commandsFactory.CreateCommand("Replay"));
		
	}
	
	public void setOtherMenu(JMenu otherMenu) {
		for(String command : otherCommands) {
			JMenuItem currentCommand = new JMenuItem(command);
			otherMenu.add(currentCommand);
			setOtherMenuActionListeners(currentCommand,command);
		}
		
	}
	
	private void setOtherMenuActionListeners(JMenuItem currentCommand,String command) {
		String commandType = command.replace(" ","");
		currentCommand.addActionListener(commandsFactory.CreateCommand(commandType));
	}

	

}
