package commandstests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import commands.NewDocument;
import model.Document;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.swing.JTextArea;


@RunWith(Parameterized.class)
public class NewDocumentTest {
	
	// fields used together with @Parameter must be public
    @Parameter(0)
    public String title;
    @Parameter(1)
    public String author;



    // creates the test data
    @Parameters
    public static List<Object[]> data() {
    	Object[][] data = new Object[][] { 
        	{"title","author"},
        	
        	{"001000","PC"},
        	
        	{"Soft Eng","Dr. Z"},
        	
        	{"",""},
        	
        	{"","1"},
        	
        	{"asgadsgadv",""},
        	
        	{"the the the","1245"},
        	
        	{"\n","1!!"},
        	
        	{"qq:::","@##"},
        	
        	{"><>","p"}};
        return Arrays.asList(data);
    }

	@Test
	public void newDocTest() {
		JTextArea textArea = new JTextArea();
    	Document currentDocument = new Document();
		
		
		NewDocument newDocument = new NewDocument(textArea, currentDocument, title, author);
		newDocument.execute();
		
		assertEquals(title,currentDocument.getTitle());
		assertEquals(author,currentDocument.getAuthor());
		
	}

}
