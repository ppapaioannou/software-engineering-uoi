/*
** May 2020 
**
******************************ReplayManager******************************
*************************************************************************
** ReplayManager's replay() method is pretty straightforward.
** The ArrayList of type Command is iterated and each Command
** is executed again.
**
** This is why the execute() method is important.
*************************************************************************
**
**
*/
package commands;

public class ReplayManager extends ReplayCommandManager {

	@Override
	public void replay() {
		for(Command command : commands) {
			command.execute();
		}

	}
	
}
