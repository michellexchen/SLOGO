package Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Model.CommandNode;
import Model.CommandTree;
import Model.Node;

/**
 * SLogo's Tree Factory that creates abstract syntax tree of Nodes Creates
 * Nodes, that can either be CommandNodes (if command) or NumericNode (if
 * numerical)
 * 
 * @author Adam Tache
 *
 */

public class TreeFactory {

	private NodeFactory nodeFactory;

	public CommandTree makeTree(String text) throws SLogoException {
		if (text == null)
			throw new SLogoException("Did not input correct command");
		CommandTree myTree = new CommandTree();
		List<String> nodeList = format(text);
		nodeFactory = new NodeFactory();
		Node root = nodeFactory.createNode(nodeList.get(0));
		nodeList.remove(0);
		myTree.setRoot(root);
		if (nodeList.size() > 0)
			createChildren(root, nodeList);
		return myTree;
	}

	public void createChildren(Node root, List<String> nodeList) throws SLogoException {
		for (int x = 0; x < root.getNumChildren(); x++) {
			HashMap<Node, List<String>> childToRemaindersMap = nodeFactory.createChild(nodeList);
			Iterator it = childToRemaindersMap.entrySet().iterator();
			Map.Entry pair = (Map.Entry) it.next();
			Node child = (Node) pair.getKey();
			nodeList = (List<String>) pair.getValue();
			((CommandNode) root).addChild(child);
			System.out.println("NEW CHILD: " + child + " REMAINDERS: " + nodeList);
		}
	}

	public List<String> format(String text) throws SLogoException {
		// List<String> myNodes = new ArrayList<>();
		return Arrays.stream(text.split("\\s+")).map(String::toLowerCase)
				.collect(Collectors.toCollection(ArrayList::new));
	}

}