package CommandNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import Exception.SLogoException;
import Model.Variable;

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

	public HashMap<CommandTree, List<Node>> makeTree(List<Node> nodeList) throws SLogoException {
//		if (sanitate(nodeList)) {
//			root.addVarParam(nodeList.get(1));
//			nodeList.remove(1);
//		}
		Node root = nodeList.get(0);
		nodeList.remove(root);
		/*
		 * this will get removed so that it's not a conditional in the makeTree
		 * structure
		 *
		 */
		myTree.setRoot(root);
		List<Node> remainingNodes = new ArrayList<Node>();
		if (nodeList.size() > 0)
			remainingNodes = nf.createChildren(root, nodeList);
		HashMap<CommandTree, List<Node>> tuple = new HashMap<CommandTree, List<Node>>();
		tuple.put(myTree, remainingNodes);
		return tuple;
	}

	public boolean sanitate(List<String> nodeList) {
		int idxOfMake = nodeList.indexOf("make");
		if (idxOfMake > -1) {
			if (idxOfMake + 1 < nodeList.size() && checkContainsColon(nodeList.get(idxOfMake) + 1)) {
				Variable var = new Variable();
				var.setName(nodeList.get(idxOfMake));
				myTree.setMyVars(var);
				return true;
			}
		}
		return false;
	}

	public boolean checkContainsColon(String input) {
		return true;
	}

}