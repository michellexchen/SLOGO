package model;

public class LanguageMain {
	
//	public static void main (String[] args) {
//		System.out.println(translate("fd 50 rt 90 BACK :distance Left :angle"));
//	}
	
	private static String parseText(LanguageParser lang, String[] text) {
		String result = "";
		for (String s : text) {
			if (s.trim().length() > 0) {
				result = result + String.format("%s : %s", s, lang.getSymbol(s));
			}
		}
		return result;
	}
	
	public static String translate (String fileInput) {
		final String WHITESPACE = "\\p{Space}";
		LanguageParser lang = new LanguageParser();
		lang.addPatterns("resources/languages/English");
        lang.addPatterns("resources/languages/Syntax");
        lang.addPatterns("resources/languages/Chinese");
        lang.addPatterns("resources/languages/French");
        lang.addPatterns("resources/languages/German");
        lang.addPatterns("resources/languages/Italian");
        lang.addPatterns("resources/languages/Portugese");
        lang.addPatterns("resourves/languages/Russian");
        lang.addPatterns("resources/languages/Spanish");
        return(parseText(lang, fileInput.split(WHITESPACE)));
	}
}
