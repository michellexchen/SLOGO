/**
 * 
 */
package View;

import java.util.ResourceBundle;

/**
 * @author Hunter
 *
 */
public abstract class SLogoBuilder {
    protected static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
    protected static final String DEFAULT_LANG_PACKAGE = "resources.languages/";

    private ResourceBundle myResources;

	/**
	 * Abstract class extended by different view builders
	 */
	public SLogoBuilder () {
	}
	
    protected ResourceBundle getResources () {
        return myResources;
    }
}