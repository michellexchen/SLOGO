package Controller;

import java.io.IOException;

/**
 * SLogo's Commands driving class that extends FileDriver abstract class
 * 
 * @author Adam Tache
 *
 */

public class CommandsDriver extends FileDriver{

	private final String commandDirectory = "resources";
	private final String commandExtension = "CommandInfo.txt";

	public void load(){
		setDirectory(commandDirectory);
		setExtension(commandExtension);
		try {
			super.load();
		} catch (IOException e) {
			// Throw {CommandInfo.txt} file not found Error message on UI
		}
	}

}