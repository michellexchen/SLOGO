package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Properties;

import exception.SLogoException;

/**
 * SLogo's highest level in File reader hierarchy
 * 
 * @author Adam Tache
 *
 */

public abstract class FileLoader {
	Properties myProperties;
	private BufferedReader myFileReader;
	private String directory;
	private String extension;
	private String fileName;

	public void load() throws IOException, SLogoException {
		fileName = directory + "/" + extension;
		myFileReader = null;
		try {
			myFileReader = new BufferedReader(new FileReader(fileName));
		} catch (IOException e) {
			throw new FileNotFoundException(String.format("File not found: %s", fileName));
		}
		myProperties = new Properties();
		myProperties.load(myFileReader);
	}

	public String getFileName(){
		return fileName;
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

	public int countLines(String filename) throws SLogoException{
		LineNumberReader reader;
		try {
			reader = new LineNumberReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			throw new SLogoException("SLogo resources file not found");
		}
		int numLines = 0;
		String nextLine = "";
		try {
			while ((nextLine = reader.readLine()) != null) {}
		} catch (IOException e) {
			throw new SLogoException("SLogo resources file not found");
		}
		numLines = reader.getLineNumber(); 
		return numLines;
	}

}