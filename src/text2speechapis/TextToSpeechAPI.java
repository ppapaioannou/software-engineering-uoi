/*
** May 2020 
**
*****************************TextToSpeechAPI*****************************
*************************************************************************
** A general Interface was implemented which interacts 
** with the rest of the software so that the external text-to-speech
** library can easily be changed without an impact on the program.
*************************************************************************
**
**
*/
package text2speechapis;

public interface TextToSpeechAPI {
	
	public void play(String string);
	
	public void setVolume(int volume);
	
	public void setRate(int rate);
	
	public void setPitch(int pitch);

}
