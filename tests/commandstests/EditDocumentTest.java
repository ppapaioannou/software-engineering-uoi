package commandstests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import commands.EditDocument;
import model.Document;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.swing.JTextArea;



@RunWith(Parameterized.class)
public class EditDocumentTest {

    // fields used together with @Parameter must be public
    @Parameter(0)
    public String text;



    // creates the test data
    @Parameters
    public static List<Object> data() {
    	Object[] data = new Object[] { 
        		"texttobe\naddedthere",
        		
        		"!?other text",
        		
        		"the other \t\tother",
        		
        		"Star Wars is an American epic space-opera media franchise "
        		+ "created by George Lucas, which began with the eponymous "
        		+ "1977 film and quickly became a worldwide pop-culture phenomenon. "
        		+ "The franchise has been expanded into various films and other media, "
        		+ "including television series, video games, novels, comic books, "
        		+ "theme park attractions, and themed areas, comprising an all-encompassing "
        		+ "fictional universe.[b] The franchise holds a Guinness World Records title "
        		+ "for the \"Most successful film merchandising franchise\".[2] In 2020, "
        		+ "the total value of the Star Wars franchise was estimated at US$70 billion, "
        		+ "and it is currently the fifth-highest-grossing media franchise of all time.",
        		
        		"Wikipedia articles must not contain original research. "
        		+ "The phrase \"original research\" (OR) is used on Wikipedia to refer "
        		+ "to material—such as facts, allegations, and ideas—for which no reliable, "
        		+ "published sources exist.[a] ",
        		
        		"Health is the level of functional or metabolic efficiency of a "
        		+ "living organism. In humans, it is the ability of individuals or communities "
        		+ "to adapt and self-manage when facing physical, mental, or social challenges. "
        		+ "The most widely accepted definition of good health is that of the "
        		+ "World Health Organization Constitution.",
        		
        		"",
        		
        		"12589  111   111   1	124",
        		
        		"_________++++++++++-===!~3",
        		
        		"135///';vb"};
        return Arrays.asList(data);
    }


    @Test
    public void editDocTest() {
    	JTextArea textArea = new JTextArea();
    	Document currentDocument = new Document();
    	
        EditDocument editDocument = new EditDocument(textArea, currentDocument);
        textArea.append(text);
        editDocument.execute();

        assertEquals(text,currentDocument.getContentsString());
    }

}
