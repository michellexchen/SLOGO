package Controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * SLogo's File driving highest level abstract class
 * 
 * @author Adam Tache
 *
 */

public abstract class FileDriver {
	Properties myProperties;
	private BufferedReader myFileReader;
	private String directory = "";
	private String extension = "";

	public void load() throws IOException {
		String filename = directory + "/" + extension;
		myFileReader = null;
		try {
			myFileReader = new BufferedReader(new FileReader(filename));
		} catch (IOException e) {
			throw new FileNotFoundException(String.format("File not found: %s", filename));
		}
		myProperties = new Properties();
		myProperties.load(myFileReader);
	}

	public void setDirectory(String directory){
		this.directory = directory;
	}

	public void setExtension(String extension){
		this.extension = extension;
	}

	public String getString(String key) {
		return myProperties.getProperty(key);
	}

}