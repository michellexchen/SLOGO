package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Properties;

import exception.SLogoException;

/**
 * Abstract class extended by other file loaders for loading values from files
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

	public void load() throws SLogoException  {
		fileName = directory + "/" + extension;
		myFileReader = null;
		try {
			myFileReader = new BufferedReader(new FileReader(fileName));
		} catch (IOException e) {
			throw new SLogoException(String.format("File not found: %s", fileName));
		}
		myProperties = new Properties();
		try {
			myProperties.load(myFileReader);
		} catch (IOException e) {
			throw new SLogoException("File Reader not found.");
		}
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

	@SuppressWarnings("resource")
	public int countLines(String filename) throws SLogoException{
		LineNumberReader reader;
		try {
			reader = new LineNumberReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			throw new SLogoException("SLogo resources file not found");
		}
		int numLines = 0;
		@SuppressWarnings("unused")
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