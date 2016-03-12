package model;

import exception.SLogoException;

/**
 * SLogo's Languages loader class to lookup language translations for supported languages
 * 
 * @author Adam Tache
 *
 */

public class LanguageLoader extends FileLoader {

	private static final String LANG_PATH = "resources/languages";
	private static final String LANG_DIRECTORY = ".properties";
	private String myLanguage;
	
	/**
	 * @param language: user-selected language as String
	 * Loads appropriate language file and creates swapped map
	 */
	
	public void load(String language) throws SLogoException {
		myLanguage = format(language);
		setDirectory(LANG_PATH);
		setExtension(myLanguage + LANG_DIRECTORY);
		super.load();
		createSplitBackMap();
		
	}

	public String getLanguage() {
		return myLanguage;
	}
	
	/**
	 * @param language: user-selected language as String
	 * Formats input language string with first letter capitalized
	 * @return formatted language
	 */

	public String format(String language) {
		return Character.toString(language.charAt(0)).toUpperCase() + language.substring(1).toLowerCase();
	}
	
	/**
	 * @param foreignWord: string to be assessed/translated
	 * Node representation of Repeat command, a Control Structure command using variable
	 * @return translation or input string (if not specified in language file - e.g. number)
	 */

	public String getTranslation(String foreignWord) {
		if (myBackMap.containsKey(foreignWord)) {
			return myBackMap.get(foreignWord);
		}
		return foreignWord;
	}
}