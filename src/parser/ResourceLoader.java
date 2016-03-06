package parser;

import java.io.IOException;

import exception.SLogoException;
import model.FileLoader;

public class ResourceLoader extends FileLoader{
	private static final String resourcesDirectory = "resources/resources";
	private static final String resourcesExtension = "slogo_resources.resources";
	
	public ResourceLoader() throws SLogoException{
		load();
	}

	public void load() throws SLogoException {
		setDirectory(resourcesDirectory);
		setExtension(resourcesExtension);
		try {
			super.load();
		} catch (IOException e) {
			throw new SLogoException("SLogo resources file not found");
		}
	}
}