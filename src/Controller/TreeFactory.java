package Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import Model.CommandTree;

/**
 * SLogo's Tree Factory that creates abstract syntax tree of Nodes Creates
 * Nodes, that can either be CommandNodes (if command) or NumericNode (if
 * numerical)
 * 
 * @author Adam Tache
 *
 */

public class TreeFactory {

	public CommandTree makeTree(String text) throws SLogoException {
		CommandTree myTree = new CommandTree();
		List<String> nodeList = format(text);
		RootFactory NodeFactory = new RootFactory();
		myTree.setRoot(NodeFactory.createNode((ArrayList<String>) nodeList));
		return myTree;
	}

	public List<String> format(String text) {
		ArrayList<String> myNodes = new ArrayList<>();
		if (text != null)
			myNodes = Arrays.stream(text.split(" ")).map(String::toLowerCase)
					.collect(Collectors.toCollection(ArrayList::new));
		else {
			// throw our exception class
		}
		return myNodes;
	}

}