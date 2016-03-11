package parser;

import exception.SLogoException;

/**
 * CommandNameLoader allows Node creation via Reflection using resources file
 * 
 * @author Adam Tache
 *
 */

public class CommandNameLoader extends CommandLoader{

	private static final String commandExtension = "Commands.resources";

	public CommandNameLoader() throws SLogoException{
		setExtension(commandExtension);
		super.load();
	}

}