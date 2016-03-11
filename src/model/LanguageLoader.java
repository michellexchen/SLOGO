package model;

import exception.SLogoException;

/**
 * SLogo's Languages loader class to lookup language translations for supported languages
 * 
 * @author Adam Tache
 *
 */

public class LanguageLoader extends FileLoader {

	private String langDirectory = "resources/languages";
	private String langExtension = ".properties";
	private String myLanguage;

	public void load(String language) throws SLogoException {
		String myLang = format(language);
		myLanguage = myLang;
		langExtension = myLang + langExtension;
		setDirectory(langDirectory);
		setExtension(langExtension);
		super.load();
	}

	public String getLanguage() {
		return myLanguage;
	}

	public String format(String language) {
		return Character.toString(language.charAt(0)).toUpperCase() + language.substring(1).toLowerCase();
	}

	public String getTranslation(String key) {
		return myProperties.getProperty(key);
	}
}