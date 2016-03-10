package parser;

import java.io.IOException;
import exception.SLogoException;
import model.FileLoader;

/**
 * SLogo's Commands resources file reader class that extends FileDriver abstract
 * class
 * 
 * @author Adam Tache
 *
 */

public class CommandLoader extends FileLoader {

	private static final String commandDirectory = "resources/commands";
	
	public void load() throws SLogoException {
		setDirectory(commandDirectory);
		try {
			super.load();
		} catch (IOException e) {
			throw new SLogoException("Command resources file not found");
		}
	}

}