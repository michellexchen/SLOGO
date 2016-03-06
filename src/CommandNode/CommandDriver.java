package commandnode;

import java.io.IOException;

import exception.SLogoException;
import model.FileDriver;

/**
 * SLogo's Commands resources file reader class that extends FileDriver abstract
 * class
 * 
 * @author Adam Tache
 *
 */

public abstract class CommandDriver extends FileDriver {

	private static final String commandDirectory = "resources/commands";

	public void load(String commandExtension) throws SLogoException {
		setDirectory(commandDirectory);
		setExtension(commandExtension);
		try {
			super.load();
		} catch (IOException e) {
			throw new SLogoException("Command resources file not found");
		}
	}

}