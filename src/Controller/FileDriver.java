package Controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * SLogo's highest level in File reader hierarchy
 * 
 * @author Adam Tache
 *
 */

public abstract class FileDriver {
	Properties myProperties;
	private BufferedReader myFileReader;
	private String directory;
	private String extension;

	public void load() throws IOException, SLogoException {
		String fileName = directory + "/" + extension;
		myFileReader = null;
		try {
			myFileReader = new BufferedReader(new FileReader(fileName));
		} catch (IOException e) {
			throw new FileNotFoundException(String.format("File not found: %s", fileName));
		}
		myProperties = new Properties();
		myProperties.load(myFileReader);
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getString(String key) {
		return myProperties.getProperty(key);
	}

}