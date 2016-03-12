package parser;

import exception.SLogoException;

/**
 * CommandNameLoader allows Node creation via Reflection using resources file
 * 
 * @author Adam Tache
 *
 */

public class CommandNameLoader extends CommandLoader{

	private static final String COMMAND_EXTENSION = "commands.resources";

	public CommandNameLoader() throws SLogoException{
		super.load(super.getPath(), COMMAND_EXTENSION);
	}

}