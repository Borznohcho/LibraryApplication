package entities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest {

    public static void main(String[] args) {
	System.out.println("Read/Write");

	String filename = "data/person.txt";
	FileReader fr = null;
	BufferedReader br = null;
	String lignes = "";
	StringBuilder sb = new StringBuilder();

	File f = new File(filename);

	if (f.exists()) {

	    try {
		fr = new FileReader(f);
		br = new BufferedReader(fr);

		try {
		String ligne = null;
		while ((ligne = br.readLine()) != null) {
		    sb.append(ligne);
		    sb.append("\n");
		}

		lignes = sb.toString();
		
		
		   
		} finally {
		    fr.close();
		    br.close();
		}

	    } catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();

	    }
	    

	}
	
	System.out.println(lignes);

    }
}
