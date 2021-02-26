/*
** May 2020 
**
****************************Rot13Encoding****************************
*********************************************************************
** A simple letter substitution cipher that replaces a letter 
** with the 13th letter after it, in the alphabet.
** ROT13 is a special case of the Caesar cipher
*********************************************************************
**
**
*/
package encodingstrategies;

public class Rot13Encoding extends TemplateEncoding{

	@Override
	protected char mapCharacter (char ch) {
		char c = ch;
		
        if (c >= 'a' && c <= 'm') {
        	c += 13;
        }
        else if (c >= 'A' && c <= 'M') {
        	c += 13;
        }
        else if (c >= 'n' && c <= 'z') {
        	c -= 13;
        }
        else if (c >= 'N' && c <= 'Z') {
        	c -= 13;
        }
        return c;
	}

}
