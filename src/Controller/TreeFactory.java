package Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import Model.CommandTree;
import Model.Node;
import Model.Turtle;

/**
 * SLogo's Tree Factory that creates abstract syntax tree of Nodes Creates
 * Nodes, that can either be CommandNodes (if command) or NumericNode (if
 * numerical)
 * 
 * @author Adam Tache
 *
 */

public class TreeFactory {

	public CommandTree makeTree(String text) {
		CommandTree myTree = new CommandTree();
		List<String> nodeList = format(text);
		NodeFactory NodeFactory = new NodeFactory();
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