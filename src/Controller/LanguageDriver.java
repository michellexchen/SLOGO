package controller;

import java.io.IOException;

import exception.SLogoException;

/**
 * SLogo's Languages reader class that extends FileDriver abstract class
 * 
 * @author Adam Tache
 *
 */

public class LanguageDriver extends FileDriver {

	private String langDirectory = "resources/languages";
	private String langExtension = ".properties";
	private String myLanguage;

	public void load(String language) throws SLogoException {
		String myLang = format(language);
		myLanguage = myLang;
		langExtension = myLang + langExtension;
		setDirectory(langDirectory);
		setExtension(langExtension);
		try {
			super.load();
		} catch (IOException e) {
			throw new SLogoException("Encountered an error loading the language file");
			// System.out.println(e.getMessage());
		}
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