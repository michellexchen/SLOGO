// This is part of my code masterpiece as it's a component of the refactoring of the command execution flow.
// This class was refactored to return tokens instead of already created Nodes, which didn't seem to fit the role of a "parser" well.  
// This class also demonstrates basic abstraction of functionality to specific methods, error checking using a resources file, and lambda expression usage for formatting tokens.

package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import exception.SLogoException;
import model.ResourceLoader;

/**
 * SLogoParser reads in command, formats into command parts, then creates corresponding tree(s)
 * Reads in command from MainModel's readCommand method
 * Roots of created trees evaluated and corresponding display information updated
 * @author Adam Tache
 *
 */
public class SLogoParser {

	/**
	 * Formats a command into tokens separated by spaces
	 * @return formatted tokens
	 * 
	 */
	public List<String> readCommand(String command) throws SLogoException {
		if (invalidInputCheck(command)) {
			throw new SLogoException(new ResourceLoader().getString("NoCommandEntered"));
		}
		return formatTokens(command);
	}

	/**
	 * Parses String into tokens based on regex free space
	 * 
	 */
	private List<String> formatTokens(String text) throws SLogoException {
		return Arrays.stream(text.split("\\s+")).map(String::toLowerCase)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * Checks for invalid input
	 * 
	 */
	private boolean invalidInputCheck (String command) {
		return command.isEmpty() || command == null;
	}
	
}