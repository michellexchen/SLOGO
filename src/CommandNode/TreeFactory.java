package commandnode;

import java.util.ArrayList;
import java.util.List;

import exception.SLogoException;
import javafx.util.Pair;

/**
 * SLogo's Tree Factory that creates abstract syntax tree of Nodes Creates
 * Nodes, that can either be CommandNodes (if command) or NumericNode (if
 * numerical)
 * 
 * @author Adam Tache
 *
 */

public class TreeFactory {

	private NodeFactory nf;
	private CommandTree myTree;
	
	public TreeFactory() throws SLogoException{
		nf = new NodeFactory();
		myTree = new CommandTree();
	}

	public Pair<CommandTree, List<Node>> makeTree(List<Node> nodeList) throws SLogoException {
		Node root = nodeList.get(0);
		nodeList.remove(root);
		myTree.setRoot(root);
		List<Node> remainingNodes = new ArrayList<Node>();
		if (nodeList.size() > 0)
			remainingNodes = nf.createChildren(root, nodeList);
		Pair<CommandTree, List<Node>> tuple = new Pair<CommandTree, List<Node>>(myTree, remainingNodes);
		return tuple;
	}

}