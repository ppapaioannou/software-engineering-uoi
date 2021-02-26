/*
** May 2020 
**
*******************************ViewHelp*******************************
**********************************************************************
** ViewHelp opens the User Manual in the system's default PDF viewer.
**********************************************************************
**
**
*/
package commands;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class ViewHelp extends Command {

	@Override
	public void doWork() {
		execute();

	}

	@Override
	public void execute() {
		if (Desktop.isDesktopSupported()) {
		    try {
		    	URL pdf = getClass().getResource("/view/Text To Speech Editor with Kevin the Parrot.pdf");
		    	File file = new File(pdf.toURI());
		        Desktop.getDesktop().open(file);
		    } catch (URISyntaxException | IOException ex) {
		        // no application registered for PDFs
		    }
		}

	}

	@Override
	public Command copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
