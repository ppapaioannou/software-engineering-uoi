/*
** May 2020 
**
****************************FakeReplayManager****************************
*************************************************************************
** FakeReplayManager populates another ArrayList of type Command with
** each Command inside the commands ArrayList so that during the
** testing phase each called command is compared to each 
** "to-be-replayed" command.
*************************************************************************
**
**
*/
package commands;

import java.util.ArrayList;

public class FakeReplayManager extends ReplayCommandManager {
	
	private ArrayList<Command> commandsReplayed = new ArrayList<Command>();

	@Override
	public void replay() {
		for(Command command : commands) {
			commandsReplayed.add(command);
		}

	}
	
	public ArrayList<Command> getCommandsReplayed(){
		return commandsReplayed;
	}

}
