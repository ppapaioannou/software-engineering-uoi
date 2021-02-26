/*
** May 2020 
**
***************************EncodingStrategy***************************
**********************************************************************
** The package encodingstrategies starting from the EncodingStrategy class 
** contains everything concerning the encoding techniques.
**
** We implemented a simple strategy pattern with a parameterized factory
** and now the addition of new encoding methods is super simple.
**********************************************************************
**
**
*/
package encodingstrategies;

public interface EncodingStrategy {
	
	public String encode(String string);
	
}
