package parser;

import exception.SLogoException;

/**
 * InstructionLoader allows access to command instructions
 * 
 * @author Adam Tache
 *
 */

public class InstructionLoader extends CommandLoader{
	
	private static final String COMMAND_EXTENSION = "instructions.resources";

	public InstructionLoader() throws SLogoException{
		setExtension(COMMAND_EXTENSION);
		super.load();
	}

}