package parser;
import exception.SLogoException;
import model.FileLoader;

/**
 * CommandLoader initiates file loading from commands resources files
 * 
 * @author Adam Tache
 *
 */

public class CommandLoader extends FileLoader {

	private static final String COMMAND_PATH = "resources/commands";
	
	public void load() throws SLogoException {
		setDirectory(COMMAND_PATH);
		super.load();
	}

}