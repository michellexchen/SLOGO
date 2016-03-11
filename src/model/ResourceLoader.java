package model;
import exception.SLogoException;

/**
 * SLogo's Resource loader class to load access resources
 * 
 * @author Adam Tache
 *
 */

public class ResourceLoader extends FileLoader{
	private static final String resourcesDirectory = "resources/resources";
	private static final String resourcesExtension = "slogo_resources.resources";
	
	public ResourceLoader() throws SLogoException{
		load();
	}

	public void load() throws SLogoException {
		setDirectory(resourcesDirectory);
		setExtension(resourcesExtension);
		super.load();
	}
}