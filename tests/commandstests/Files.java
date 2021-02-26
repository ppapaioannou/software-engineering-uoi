package commandstests;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Files {
	
	public static String read(String filepath) {
		String data="";
		try {
			File file = new File(filepath);
			Scanner myReader = new Scanner(file);
		    while (myReader.hasNextLine()) {
		    	data += myReader.nextLine()+"\n";
		    }
		    data = data.substring(0,data.length()-1);
		    myReader.close();
		}
		catch(IOException error){
			System.out.println(error);
		}
		return data;
	}
}