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
	
	public ResourceLoader() throws SLogoException{
		load();
	}

	public void load() throws SLogoException {
		setDirectory(RESOURCE_PATH);
		setExtension(RESOURCE_EXTENSION);
		super.load();
	}
}