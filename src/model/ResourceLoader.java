package model;
import exception.SLogoException;

/**
 * SLogo's Resource loader class to load access resources
 * 
 * @author Adam Tache
 *
 */

public class ResourceLoader extends FileLoader{

    private static final String RESOURCE_PATH = "resources/resources";
    private static final String RESOURCE_EXTENSION = "slogo_resources.resources";

    /**
     * Default constructor
     * 
     */
    public ResourceLoader () throws SLogoException {
        super.load(RESOURCE_PATH, RESOURCE_EXTENSION);
    }

    /**
     * Constructor to be used with a filename
     * 
     * @param filename
     * @throws SLogoException
     */
    public ResourceLoader (String filename) throws SLogoException {
        super.load(RESOURCE_PATH, filename);
    }
    
    /**
     * Custom getString method to get the matching string value
     * 
     */
    public String getString(String key) {
        try {
            return super.getString(key);
        }
        catch (SLogoException e) {
            e.showErrorDialog("Non-match exists in resource file");
            return "";
        }
    }

}