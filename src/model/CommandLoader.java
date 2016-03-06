package model;
import java.io.IOException;
import exception.SLogoException;

/**
 * SLogo's Commands resources file reader class that extends FileDriver abstract
 * class
 * 
 * @author Adam Tache
 *
 */

public class CommandLoader extends FileLoader {

	private static final String commandDirectory = "resources/commands";
	private static final String commandExtension = "Commands.resources";
	
	public CommandLoader() throws SLogoException{
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