package commandstests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import commands.TuneAudio;
import model.Document;
import text2speechapis.FakeTextToSpeechAPI;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;


@RunWith(Parameterized.class)
public class TuneAudioTest {
	
	// fields used together with @Parameter must be public
    @Parameter(0)
    public int volume;
    @Parameter(1)
    public int rate;
    @Parameter(2)
    public int pitch;



    // creates the test data
    @Parameters
    public static List<Object[]> data() {
    	Object[][] data = new Object[][] { 
        	{100,40,20},
        	
        	{20,200,300},
        	
        	{1,1,1},
        	
        	{0,0,20},
        	
        	{2,5,20},
        	
        	{222,1212,20},
        	
        	{51,40,2},
        	
        	{666,7,21455551},
        	
        	{1251,1111,20},
        	
        	{100,40,124}};
        return Arrays.asList(data);
    }

	@Test
	public void tuneAudioTest() {
    	Document currentDocument = new Document();
		
		
    	FakeTextToSpeechAPI fakeTSAPI = new FakeTextToSpeechAPI();
		
    	currentDocument.setAudioManager(fakeTSAPI);

		
		TuneAudio tuneAudio = new TuneAudio(currentDocument, volume, rate, pitch);
		
		tuneAudio.execute();
		
		assertEquals(volume,fakeTSAPI.getVolume());
		assertEquals(rate,fakeTSAPI.getRate());
		assertEquals(pitch,fakeTSAPI.getPitch());
		
	}

}