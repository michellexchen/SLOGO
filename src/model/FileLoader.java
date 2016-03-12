package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import exception.SLogoException;

/**
 * Abstract class extended by other file loaders for loading values from files
 * 
 * @author Adam Tache
 *
 */

public abstract class FileLoader {
	Properties myProperties;
	HashMap<String, String> myBackMap;
	private BufferedReader myFileReader;
	private String fileName;
	
	/**
	 * @param str: compound property
	 * Splits property (foreign language) using "|" as a delimiter. split("|") caused errors
	 * @return string array of split properties
	 */
	
	private String[] splitSpecial(String str) {
		ArrayList<String> resultList = new ArrayList<String>();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '|') {
				resultList.add(str.substring(0, i));
				resultList.add(str.substring(i + 1));
				break;
			}
		}
		String[] resultArray = new String[resultList.size()];
		return resultList.toArray(resultArray);

	}
	
	/**
	 * Creates a hashmap from properties with key and value swapped
	 */
	
	public void createSplitBackMap() {
		myBackMap = new HashMap<String, String>();
		Set<Object> keySet = myProperties.keySet();
		for (Object o: keySet) {
			String currentProperty = getString((String) o);
			if (currentProperty.contains("|")) {
				String[] splitProperty = splitSpecial(currentProperty);
				for (String s: splitProperty) {
					myBackMap.put(s, (String) o);
				}
			}
			else {
				myBackMap.put(currentProperty, (String) o);
			}
		}
	}
	
	/**
	 * Loads language property file into property object
	 * @throws SLogoException 
	 */
	public void load(String path, String extension) throws SLogoException{
		fileName = path + "/" + extension;
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

	public String getString(String key) {
		return myProperties.getProperty(key);
	}

	/**
	 * @param filename
	 * Counts number of lines in file
	 * @return numLines: number of lines in file
	 */
	
	@SuppressWarnings("resource")
	public int countLines(String filename) throws SLogoException{
		LineNumberReader reader;
		try {
			reader = new LineNumberReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			throw new SLogoException("File not found");
		}
		int numLines = 0;
		@SuppressWarnings("unused")
		String nextLine = "";
		try {
			while ((nextLine = reader.readLine()) != null) {}
		} catch (IOException e) {
			throw new SLogoException("File not found");
		}
		numLines = reader.getLineNumber(); 
		return numLines;
	}

}