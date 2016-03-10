package parser;

import exception.SLogoException;

public class CommandInstructionLoader extends CommandLoader{
	
	private static final String commandExtension = "Instructions.resources";

	public CommandInstructionLoader() throws SLogoException{
		setExtension(commandExtension);
		super.load();
	}

}