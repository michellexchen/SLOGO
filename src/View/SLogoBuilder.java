/**
 * 
 */
package View;

import java.util.ResourceBundle;

/**
 * @author Confidence
 *
 */
public abstract class SLogoBuilder {
    protected static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
    protected static final String DEFAULT_LANG_PACKAGE = "resources.languages/";

    private ResourceBundle myResources;
    private ResourceBundle myLanguageResources;



	/**
	 * Abstract class extended by different view builders
	 */
//	public SLogoBuilder(String language) {
	public SLogoBuilder () {

//        myLanguageResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
	}
	
	
    protected ResourceBundle getResources () {
        return myResources;
    }
}
