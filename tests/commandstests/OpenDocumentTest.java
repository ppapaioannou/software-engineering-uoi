package commandstests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import commands.OpenDocument;
import model.Document;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.swing.JTextArea;


@RunWith(Parameterized.class)
public class OpenDocumentTest {
	
	// fields used together with @Parameter must be public
    @Parameter(0)
    public String filepath;




    // creates the test data
    @Parameters
    public static List<Object> data() {
    	Object[] data = new Object[] { 
        		"file_1.txt",
        		
        		"file_2.txt",
        		
        		"file_3.txt",
        		
        		"file_4.txt",
        		
        		"file_5.txt",
        		
        		"file_6.txt",
        		
        		"file_7.txt",
        		
        		"file_8.txt",
        		
        		"file_9.txt",
        		
        		"file_10.txt"};
        return Arrays.asList(data);
    }

	@Test
	public void openDocTest() {
		JTextArea textArea = new JTextArea();
    	Document currentDocument = new Document();
    	
    	String data = Files.read(filepath);
		
		
    	OpenDocument openDocument = new OpenDocument(textArea,currentDocument, filepath);
		openDocument.execute();
		
		

		
		assertEquals(data,currentDocument.getContentsString());
		
	}
}