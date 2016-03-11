package parser;

import exception.SLogoException;

/**
 * InstructionLoader allows access to command instructions
 * 
 * @author Adam Tache
 *
 */

public class InstructionLoader extends CommandLoader{
	
	private static final String commandExtension = "Instructions.resources";

	public InstructionLoader() throws SLogoException{
		setExtension(commandExtension);
		super.load();
	}

}