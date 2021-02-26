/*
** May 2020 
**
***************************AtBashEncoding***************************
********************************************************************
** A very common, simple cipher. It was for the Hebrew alphabet 
** but modified here to work with the English alphabet. 
** Basically, when encoded, an "A" becomes a "Z", "B" turns into "Y", etc.
********************************************************************
**
**
*/
package encodingstrategies;

public class AtBashEncoding extends TemplateEncoding{

	@Override
	protected char mapCharacter (char ch) {
		char c = ch;
		if (c >= 'a' && c <= 'z') {
			c = (char) ('a' + ('z' - ch));
		}
        else if (c >= 'A' && c <= 'Z') {
        	c = (char) ('A' + ('Z' - ch));
        }
		return c;
	}

}
