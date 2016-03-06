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
import javafx.util.Pair;
import Model.*;
import View.*;
import Exception.*;
import Controller.*;
import deprecated_to_be_deleted.*;
import CommandNode.*;

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
	private Node root;
	
	public TreeFactory() throws SLogoException{
		nf = new NodeFactory();
	}
	
	public CommandTree createTree(List<String> nodeList) throws SLogoException{
		myTree = new CommandTree();
		root = nf.createNode(nodeList.get(0));
		myTree.setRoot(root);
		nodeList.remove(0);
		createStructure(nodeList, root); //now root will have the whole structure attached to it
		return myTree;
	}

	private void createStructure(List<String> nodeList, Node root) throws SLogoException {
		if(nodeList.isEmpty()) return;
		for(int i = 0;  i < root.getNumChildren(); i++){
			Node curr = nf.createNode(nodeList.get(0));
			nodeList.remove(0);
			root.addChild(curr);
			createStructure(nodeList, curr);
		}
		return;
	}
	
	public Pair<CommandTree, List<Node>> makeTree(List<Node> nodeList) throws SLogoException {
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
//		if (nodeList.size() > 0)
//			remainingNodes = nf.createChildren(root, nodeList);
		Pair<CommandTree, List<Node>> tuple = new Pair<CommandTree, List<Node>>(myTree, remainingNodes);
		return tuple;
	}

}