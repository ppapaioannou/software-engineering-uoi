package commandstests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import commands.Command;
import commands.DocumentToSpeech;
import commands.EditDocument;
import commands.FakeReplayManager;
import commands.NewDocument;
import commands.OpenDocument;
import commands.ReplayCommand;
import commands.TuneAudio;

import model.Document;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JTextArea;


@RunWith(Parameterized.class)
public class ReplayTest {
	
	// fields used together with @Parameter must be public
    @Parameter(0)
    public Command firstCommand;
    @Parameter(1)
    public Command secondCommand;
    @Parameter(2)
    public Command thirdCommand;
    
    static Document currentDocument = new Document();
    static JTextArea textArea = new JTextArea();
    
    
    static NewDocument newDocument = new NewDocument(textArea, currentDocument, "title", "author");
    
    static EditDocument editDocument = new EditDocument(textArea, currentDocument);
    
    static OpenDocument openDocument = new OpenDocument(textArea, currentDocument, "file_2.txt");
    
    static DocumentToSpeech documentToSpeech = new DocumentToSpeech(currentDocument, "reverse");
    
    static TuneAudio tuneAudio = new TuneAudio(currentDocument, 100, 200, 150);



    // creates the test data
    @Parameters
    public static List<Object[]> data() {
    	Object[][] data = new Object[][] {
    		{newDocument,editDocument,openDocument},
    		{editDocument,documentToSpeech,tuneAudio},
    		{tuneAudio,newDocument,openDocument},
    		{documentToSpeech,documentToSpeech,documentToSpeech},
    		{newDocument,documentToSpeech,tuneAudio},
    		{editDocument,documentToSpeech,editDocument},
    		{openDocument,documentToSpeech,openDocument},
    		{tuneAudio,editDocument,tuneAudio},
    		{tuneAudio,documentToSpeech,documentToSpeech},
    		{tuneAudio,openDocument,openDocument}
    		
    	};
        return Arrays.asList(data);
    }

	@Test
	public void replayTest() {
		ArrayList<Command> commands = new ArrayList<Command>();
		
		commands.add(firstCommand);
		commands.add(secondCommand);
		commands.add(thirdCommand);
		
		FakeReplayManager replayManager = new FakeReplayManager();
		
		ReplayCommand replayCommand = new ReplayCommand(null,replayManager);
		
		
		replayManager.setCommands(commands);
		
		replayCommand.execute();
		
		ArrayList<Command> replayedCommmands = new ArrayList<Command>();
		replayedCommmands=replayManager.getCommandsReplayed();
		
		
		for(int i=0; i<replayedCommmands.size(); i++) {
			Command trueCommand = commands.get(i);
			Command replayedCommand = replayedCommmands.get(i);
			assertEquals(trueCommand,replayedCommand);
		}
		
	}

}