/*
** May 2020 
**
***************************TemplateEncoding***************************
**********************************************************************
** This abstract class converts the given string from PlayLine()
** to the current strategy.
**********************************************************************
**
**
*/
package encodingstrategies;

public abstract class TemplateEncoding implements EncodingStrategy {
	
	public String encode(String string) {
		String output = "";
		for (int i = 0; i < string.length(); i++) {
			output += mapCharacter(string.charAt(i));
		}
		return output;	
	}
	
	protected abstract char mapCharacter(char ch);

}
