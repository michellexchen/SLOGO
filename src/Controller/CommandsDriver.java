package Controller;

import java.io.IOException;

/**
 * SLogo's Commands resources file reader class that extends FileDriver abstract
 * class
 * 
 * @author Adam Tache
 *
 */

public class CommandsDriver extends FileDriver {

	private final String commandDirectory = "resources/commands";
	private final String commandExtension = "Commands.resources";

	public CommandsDriver() throws SLogoException {
		load();
	}

	public void load() throws SLogoException {
		setDirectory(commandDirectory);
		setExtension(commandExtension);
		try {
			super.load();
		} catch (IOException e) {
			throw new SLogoException("Command resources file not found");
		}
	}

}