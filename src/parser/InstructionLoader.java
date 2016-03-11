package parser;

import exception.SLogoException;

public class InstructionLoader extends CommandLoader{
	
	private static final String commandExtension = "Instructions.resources";

	public InstructionLoader() throws SLogoException{
		setExtension(commandExtension);
		super.load();
	}

}