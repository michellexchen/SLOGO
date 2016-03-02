package CommandNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

	private NodeFactory nodeFactory;
	private CommandTree myTree;

	public CommandTree makeTree(String text) throws SLogoException {
		if (text == null)
			throw new SLogoException("Did not input correct command");
		myTree = new CommandTree();
		List<String> nodeList = format(text);
		nodeFactory = new NodeFactory();
		Node root = nodeFactory.createNode(nodeList);
		nodeList.remove(0);
		/*
		 * this will get removed so that it's not a conditional in the makeTree structure
		 *
		 */
		if(sanitate(nodeList)){
			root.addVarParam(nodeList.get(0));
			nodeList.remove(0);
		}
		myTree.setRoot(root);
		if (nodeList.size() > 0)
			createChildren(root, nodeList);
		return myTree;
	}
	
	public boolean sanitate(List<String>nodeList){
		int idxOfMake = nodeList.indexOf("make");
		if(idxOfMake == 1){
			if(idxOfMake + 1 < nodeList.size() && checkContainsColon(nodeList.get(idxOfMake)+1)){
				Variable var = new Variable();
				var.setName(nodeList.get(idxOfMake));
				myTree.setMyVars(var);
				return true;
			}
		}
		return false;
	}
	
	public boolean checkContainsColon(String input){
		return true;
	}

	public void createChildren(Node root, List<String> nodeList) throws SLogoException {
		System.out.println("CREATING "+root.getNumChildren()+" CHILDREN");
		for (int x = 0; x < root.getNumChildren(); x++) {
			HashMap<Node, List<String>> childToRemaindersMap = createChild(nodeList);
			Iterator it = childToRemaindersMap.entrySet().iterator();
			Map.Entry pair = (Map.Entry) it.next();
			Node child = (Node) pair.getKey();
			nodeList = (List<String>) pair.getValue();
			((CommandNode) root).addChild(child);
			System.out.println("NEW CHILD: " + child + " REMAINDERS: " + nodeList);
		}
	}
	
	public HashMap<Node, List<String>> createChild(List<String> myNodes) throws SLogoException {
		HashMap<Node, List<String>> childToRemaindersMap = new HashMap<Node, List<String>>();
		Node child = nodeFactory.createNode(myNodes);
		myNodes.remove(0);
		childToRemaindersMap.put(child, myNodes);
		if (myNodes.size() > 0) {
			for (int x = 0; x < child.getNumChildren(); x++) {
				HashMap<Node, List<String>> childChildToRemaindersMap = createChild(myNodes);
				Iterator it = childChildToRemaindersMap.entrySet().iterator();
				Map.Entry pair = (Map.Entry) it.next();
				Node childChild = (Node) pair.getKey();
				myNodes = (List<String>) pair.getValue();
				((CommandNode) child).addChild(childChild);
			}
		}
		return childToRemaindersMap;
	}

	public List<String> format(String text) throws SLogoException {
		// List<String> myNodes = new ArrayList<>();
		return Arrays.stream(text.split("\\s+")).map(String::toLowerCase)
				.collect(Collectors.toCollection(ArrayList::new));
	}

}