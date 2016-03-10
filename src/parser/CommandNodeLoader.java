package parser;

import exception.SLogoException;

public class CommandNodeLoader extends CommandLoader{

	private static final String commandExtension = "Commands.resources";

	public CommandNodeLoader() throws SLogoException{
		setExtension(commandExtension);
		super.load();
	}

}