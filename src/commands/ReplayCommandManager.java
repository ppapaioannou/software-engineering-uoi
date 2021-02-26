/*
** May 2020 
**
***************************ReplayCommandManager***************************
**************************************************************************
** The abstract ReplayCommandManager class was implemented
** to make the Testing easier.
**
** To further understand the replay() method go to ReplayManager
**************************************************************************
**
**
*/
package commands;

import java.util.ArrayList;

public abstract class ReplayCommandManager {
	
	protected ArrayList<Command> commands;

	public abstract void replay();
	
	public void setCommands(ArrayList<Command> commands) {
		this.commands=commands;
	}

}
