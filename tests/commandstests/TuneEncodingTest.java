package commandstests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import commands.TuneEncoding;
import encodingstrategies.EncodingStrategy;
import model.Document;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.List;


@RunWith(Parameterized.class)
public class TuneEncodingTest {
	
	// fields used together with @Parameter must be public
    @Parameter(0)
    public String strategy;



    // creates the test data
    @Parameters
    public static List<Object> data() {
    	Object[] data = new Object[] {
    			"Rot13",
    			
    			"AtBash",
    			
    			"Rot13",
    			
    			"Rot13",
    			
    			"Rot13",
    			
    			"Rot13",
    			
    			"CaesarCipher",
    			
    			"AtBash",
    			
    			"Rot13",
    			
    			"Rot13",
    			
    			"AtBash",};
        return Arrays.asList(data);
    }

	@Test
	public void tuneEncodingTest() {
    	Document currentDocument = new Document();
		

		EncodingStrategy initialStrategy = currentDocument.getEncodingStrategy();
		TuneEncoding tuneEncoding = new TuneEncoding(currentDocument, strategy);
		tuneEncoding.execute();
		
		assertNotEquals(initialStrategy,currentDocument.getEncodingStrategy());
		
	}

}