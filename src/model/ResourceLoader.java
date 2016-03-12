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

	public String getString(String key) throws SLogoException {
		super.load(RESOURCE_PATH, RESOURCE_EXTENSION);
		return super.getString(key);
	}
	
}