package Controller;

import Exception.SLogoException;

public class NormalCommandDriver extends CommandDriver{

	private static final String commandExtension = "Commands.resources";
	
	public NormalCommandDriver() throws SLogoException{
		load(commandExtension);
	}

}