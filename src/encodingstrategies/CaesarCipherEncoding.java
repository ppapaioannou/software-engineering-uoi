/*
** May 2020 
**
***************************AtBashEncoding***************************
********************************************************************
** A type of substitution cipher in which each letter in 
** the plaintext is replaced by a letter some fixed number 
** of positions down the alphabet. In this application that 
** number was chosen to be +3 (A turns to D etc.)
********************************************************************
**
**
*/
package encodingstrategies;

public class CaesarCipherEncoding extends TemplateEncoding{

	@Override
	protected char mapCharacter(char ch) {
		char c = ch;
		if (Character.isUpperCase(c)) 
        { 
            c = (char)(((int)c + 3 - 65) % 26 + 65); 
        } 
        else
        { 
            c = (char)(((int)c + 3 - 97) % 26 + 97); 
        } 
		
		return c;
	}

}
