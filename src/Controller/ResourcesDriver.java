package Controller;

import java.io.IOException;
import Model.*;
import View.*;
import Exception.*;
import Controller.*;
import deprecated_to_be_deleted.*;
import CommandNode.*;

public class ResourcesDriver extends FileDriver{
	private static final String resourcesDirectory = "resources/resources";
	private static final String resourcesExtension = "slogo_resources.resources";
	
	public ResourcesDriver() throws SLogoException{
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