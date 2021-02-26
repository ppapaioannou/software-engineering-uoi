/*
** May 2020 
**
**********************************COMMAND**********************************
***************************************************************************
** This is the main building block of the commands package.
** An abstract class that implements the ActionListener Interface.
** Every class that inherits Command implements a different
** User Story and is, in the end, turned into an ActionEvent by adding
** it as an ActionListener onto JComponents at the MenuManager class.
***************************************************************************
** A Command usually works in two ways,
** 1 - The user clicks the corresponding JComponent.
** 2 - The User clicks the Replay button.
** This is why there are both a doWork() and execute() methods.
** the doWork method handles the interaction with the User (when needed) 
** and always calls the execute method which handles the back-end of 
** each command i.e. Manipulation of the Document class' attributes.
**
** When the Replay command is called only the back-end part of the replayed
** command is needed.
** For the implementation of the Replay command a protected ArrayList 
** of type Command was created that stores the executed commands.
** Almost every command at it's doWork part adds 
** itself inside the ArrayList with the copy() method.
***************************************************************************
**
**
*/
package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class Command implements ActionListener {
	
	protected static ArrayList<Command> commands = new ArrayList<Command>();

	@Override
	public void actionPerformed(ActionEvent e) {
		doWork();
	}
	
	public abstract void doWork();
	
	public abstract void execute();
	
	public abstract Command copy();
	
}
