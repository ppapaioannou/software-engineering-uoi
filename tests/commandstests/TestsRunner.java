package commandstests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	NewDocumentTest.class,EditDocumentTest.class,SaveDocumentTest.class,OpenDocumentTest.class,
	DocumentToSpeechTest.class,LineToSpeechTest.class,TuneAudioTest.class,TuneEncodingTest.class,
	ReplayTest.class
})

public class TestsRunner {
	
}
