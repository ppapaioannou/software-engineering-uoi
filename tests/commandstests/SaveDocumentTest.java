package commandstests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import commands.SaveDocument;
import model.Document;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;


@RunWith(Parameterized.class)
public class SaveDocumentTest {
	
	// fields used together with @Parameter must be public
    @Parameter(0)
    public String filepath;
    @Parameter(1)
    public String contents;



    // creates the test data
    @Parameters
    public static List<Object[]> data() {
    	Object[][] data = new Object[][] { 
        	{"file_1",
        		"Hello World!\n\tToday is a good day."},
        	
        	{"file_2",
        		"A computer file is a computer resource for recording data discretely in a computer "
        			+ "storage device. Just as words can be written to paper, so can information be "
        	        + "written to a computer file. Files can be edited and transferred through the "
	        	    + "internet on that particular computer system.\n\n"
	        	    + "There are different types of computer files, designed for different purposes. "
	        	    + "A file may be designed to store a picture, a written message, a video, a computer "
	        	    + "program, or a wide variety of other kinds of data. Some types of files can "
	        	    + "store several types of information at once.\n\n"
	        	    + "By using computer programs, a person can open, read, change, save, and "
	        	    + "close a computer file. Computer files may be reopened, modified, and "
	        	    + "copied an arbitrary number of times.\n\n"
	        	    + "Typically, files are organised in a file system, which keeps "
	        	    + "track of where the files are located on disk and enables user access."},
        	
        	{"file_3",
        		 "!@#$$&^%&()**SKJAKNKADadjfajsbfusdouejdjsd<>?:''asd12"},
        	
        	{"file_4",
    		"Health � Health is a state of complete physical, mental and social well-being. "
    		+ "This is a level of functional and (or) metabolic efficiency of a "
    		+ "person in mind, body and spirit; being free from illness, "
    		+ "injury or pain (as in �good health� or �healthy�). "
    		+ "The World Health Organization (WHO) defined health in its broader sense "
    		+ "in 1946 as \"a state of complete physical, mental, and social well-being "
    		+ "and not merely the absence of disease or infirmity.\""},
        	
        	{"file_5",
    		"a"},
        	
        	{"file_6",
    		"a"},
        	
        	{"file_7",
    		"asasfadpfjpewfwe\nasdafh13ury293128\nsdfsdv"},
        	
        	{"file_8",
    		" "},
        	
        	{"file_9",
    		"I am a robot beep boop"},
        	
        	{"file_10",
    		"Exterminate! Exterminate!"}};
        return Arrays.asList(data);
    }

	@Test
	public void saveDocTest() {
    	Document currentDocument = new Document();
    	
    	currentDocument.setContents(contents);
		SaveDocument saveDocument = new SaveDocument(currentDocument, filepath);
		saveDocument.execute();
		
		String data = Files.read(filepath+".txt");
		
		assertEquals(currentDocument.getContentsString(),data);
		
	}
	
}