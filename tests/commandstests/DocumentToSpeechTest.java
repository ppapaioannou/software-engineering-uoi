package commandstests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import commands.DocumentToSpeech;
import encodingstrategies.EncodingStrategy;
import encodingstrategies.StrategiesFactory;
import model.Document;
import model.Line;
import text2speechapis.FakeTextToSpeechAPI;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;




@RunWith(Parameterized.class)
public class DocumentToSpeechTest {

    // fields used together with @Parameter must be public
    @Parameter(0)
    public String content;



    // creates the test data
    @Parameters
    public static List<Object> data() {
    	Object[] data = new Object[] { 
        		"Good Morning, campers\nGood Morning, Mr. Zarras",
        		
        		"Banksia speciosa, the showy banksia, is a large shrub or small "
        		+ "tree in the family Proteaceae. First collected and described by "
        		+ "Robert Brown in the early 19th century, the species occurs on the "
        		+ "south coast of Western Australia between Hopetoun and "
        		+ "the Great Australian Bight, growing on white or grey sand in shrubland.\n"
        		+ "\tReaching up to 8 m (26 ft) in height, it is a single-stemmed plant "
        		+ "that has thin leaves with prominent triangular \"teeth\" along each margin, "
        		+ "which are 20–45 cm (7.9–17.7 in) long and 2–4 cm (0.8–1.6 in) wide. "
        		+ "The prominent cream-yellow flower spikes appear throughout the year, "
        		+ "developing up to 20 follicles each that store seeds until "
        		+ "opened by bushfire. Though widely occurring, the species is "
        		+ "highly sensitive to dieback.\nThe flowers attract nectar- and "
        		+ "insect-feeding birds, particularly honeyeaters. In cultivation, "
        		+ "B. speciosa grows well in a sunny location on well-drained soil in areas "
        		+ "with dry summers.",
        		
        		"the text to be played is this",
        		
        		"Gardening would be very fun! It wouldn't be intimidating to start growing trees.",
        		
        		"",
        		
        		"",
        		
        		"Dune is a 1965 science fiction novel by American author Frank Herbert, "
        		+ "originally published as two separate serials in Analog magazine. "
        		+ "It tied with Roger Zelazny's This Immortal for the Hugo Award in 1966,[2] and it won "
        		+ "the inaugural Nebula Award for Best Novel.[3] It is the first installment of the Dune saga, "
        		+ "and in 2003 was cited as the world's best-selling science fiction novel.[4][5]",
        		
        		"I never got it until moving to North America."
        		+ "In my home country, temperatures seldom get so high people prefer shade, "
        		+ "and never so high people require shade. It's often windy and the wind is always cold. "
        		+ "I would read about people sitting in the shade of a tree, on a hill with a "
        		+ "breeze, and I'd think that sounds cold and inconvenient. "
        		+ "Usually we're looking for a sunny spot in a lowered area shielded from wind. "
        		+ "The idea of a warm breeze just didn't register with me. "
        		+ "I thought it was an artistic way to say, like, gentle? The first time I felt a "
        		+ "warm breeze was surreal. First time opening a window and it's warmer outside than "
        		+ "inside was panic, how do I cool down if even outside isn't cool? "
        		+ "It's like being trapped in a giant oven, slowly cooking alive. "
        		+ "My college roommate had to teach me how to use a thermostat."
        		+ "I understand the purpose of ceiling fans now. Also sandals.",
        		
        		"A story of cosmic terror about The Gardners, a family who moves "
        		+ "to a remote farmstead in rural New England to escape the hustle "
        		+ "of the 21st century. They are busy adapting to their new life when "
        		+ "a meteorite crashes into their front yard. The mysterious aerolite "
        		+ "seems to melt into the earth, infecting both the land and the "
        		+ "properties of space-time with a strange, otherworldly color.",
        		
        		"A secluded farm is struck by a strange meteorite which has apocalyptic "
        		+ "consequences for the family living there and possibly the world."};
        return Arrays.asList(data);
    }
    
    Document document = new Document();
    
    FakeTextToSpeechAPI fakeTSAPI = new FakeTextToSpeechAPI();
    
    DocumentToSpeech documentToSpeech;

	@Test
	public void docToSpeechNormalTest() {
		document.setContents(content);
		
		document.setAudioManager(fakeTSAPI);
		
		documentToSpeech = new DocumentToSpeech(document, "normal");
		
		documentToSpeech.execute();
		
		assertEquals(document.getContentsString(),fakeTSAPI.getPlay());
		
	}
	
	@Test
	public void docToSpeechReverseTest() {
		document.setContents(content);
		
		document.setAudioManager(fakeTSAPI);
		
		documentToSpeech = new DocumentToSpeech(document, "reverse");
		
		documentToSpeech.execute();
		
		for(Line line : document.getContents()) {
			Collections.reverse(line.getWords());
		}
		Collections.reverse(document.getContents());
		
		assertEquals(document.getContentsString(),fakeTSAPI.getPlay());
		
	}
	
	@Test
	public void docToSpeechEncodeTest() {
		document.setContents(content);
		
		EncodingStrategy encodingStrategy;
		
		
		FakeTextToSpeechAPI fakeTSAPIAtBash = new FakeTextToSpeechAPI();
		
		document.setAudioManager(fakeTSAPIAtBash);
		
		encodingStrategy = StrategiesFactory.createStrategy("AtBash");
		document.setEncodingStrategy(encodingStrategy);
		
		String atBash = encodingStrategy.encode(document.getContentsString());
		
		documentToSpeech = new DocumentToSpeech(document, "encode");
		documentToSpeech.execute();
		
		String atBashPlay = fakeTSAPIAtBash.getPlay();
		
		assertEquals(atBash,atBashPlay);
		

		FakeTextToSpeechAPI fakeTSAPIRot13 = new FakeTextToSpeechAPI();
		
		document.setAudioManager(fakeTSAPIRot13);
		
		encodingStrategy = StrategiesFactory.createStrategy("Rot13");
		document.setEncodingStrategy(encodingStrategy);
		
		String rot13 = encodingStrategy.encode(document.getContentsString());
		
		documentToSpeech = new DocumentToSpeech(document, "encode");
		documentToSpeech.execute();
		
		String rot13Play = fakeTSAPIRot13.getPlay();

		assertEquals(rot13,rot13Play);
		
	}
	
}
