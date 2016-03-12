package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import commandnode.Node;
import exception.SLogoException;
import model.SLogoWorkspace;

/**
 * SLogoParser reads in command, formats into command parts, then creates corresponding tree(s)
 * Reads in command from MainModel's readCommand method
 * Roots of created trees evaluated and corresponding display information updated
 * @author Adam Tache
 *
 */

public class SLogoParser {

	private TreeFactory myTreeFactory;
	private SLogoWorkspace myWorkspace;

	public SLogoParser(SLogoWorkspace workspace) throws SLogoException {
		myWorkspace = workspace;
		myTreeFactory = new TreeFactory(myWorkspace);
	}
	
	public List<Node> readCommand(String command) throws SLogoException {
		if (command.isEmpty()) {
			throw new SLogoException("Nothing was entered! Try again!");
		}
		System.out.println("Reading command: " + command);
		List<String> commandParts = formatCommandParts(command);
		List<Node> myRoots = myTreeFactory.createNodes(commandParts);
		return myRoots;
	}

	private List<String> formatCommandParts(String text) throws SLogoException {
		return Arrays.stream(text.split("\\s+")).map(String::toLowerCase)
				.collect(Collectors.toCollection(ArrayList::new));
	}

}