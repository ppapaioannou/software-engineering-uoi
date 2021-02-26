/*
** May 2020 
**
**************************StrategiesFactory**************************
*********************************************************************
** A simple Parameterized Factory method to create new encoding
** strategies.
*********************************************************************
**
**
*/
package encodingstrategies;

public class StrategiesFactory {
	
	private static EncodingStrategy encodingStrategy;
	
	public static EncodingStrategy createStrategy(String strategy) {
		if(strategy.equals("AtBash")){
			encodingStrategy = new AtBashEncoding();
			return encodingStrategy;
		}
		else if(strategy.equals("Rot13")) {
			encodingStrategy = new Rot13Encoding();
			return encodingStrategy;
		}
		else if(strategy.equals("CaesarCipher")) {
			encodingStrategy = new CaesarCipherEncoding();
			return encodingStrategy;
		}
		else {
			return null;
		}
		
	}

}
