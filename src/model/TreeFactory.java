package model;

import java.util.ArrayList;
import java.util.List;

import commandnode.ListNode;
import commandnode.Node;
import commandnode.NumericNode;
import exception.SLogoException;

/**
 * SLogo's Tree Factory that creates abstract syntax tree of Nodes Creates
 * Nodes, that can either be CommandNodes (if command) or NumericNode (if
 * numerical)
 * 
 * @author Adam Tache
 *
 */

public class TreeFactory {

	CommandLoader myCommandDriver;
	ResourcesLoader myResourcesDriver;

	public TreeFactory() throws SLogoException{
		myCommandDriver = new CommandLoader();
		myResourcesDriver = new ResourcesLoader();
	}

	public List<Node> create(List<String> commandParts) throws SLogoException {
		List<Node> myRoots = new ArrayList<Node>();
		while(!commandParts.isEmpty()){
			String currCommand = commandParts.remove(0);
			Node myNode = createNode(currCommand);
			while(myNode.numCurrentChildren() != myNode.numRequiredChildren() ){
				myNode.addChild(createChild(commandParts));
			}
			myRoots.add(myNode);
		}
		return myRoots;
	}

	public Node createChild(List<String> commandParts) throws SLogoException{
		String currCommand = commandParts.remove(0);
		if(isNumeric(currCommand)){
			return createNode(currCommand);
		}
		if(isOpenBracket(currCommand)){
			List<Node> commandList = createCommandList(commandParts);
			ListNode listNode = new ListNode(commandList);
			return listNode;
		}
		else{
			Node myChild = createNode(currCommand);
			while(myChild.numCurrentChildren() != myChild.numRequiredChildren() ){
				myChild.addChild(createChild(commandParts));
			}
			return myChild;
		}
	}

	public List<Node> createCommandList(List<String> commandParts) throws SLogoException{
		List<String> innerCommands = new ArrayList<String>();
		int openBrackets = 1;
		int closedBrackets = 0;
		String currCommand = "";
		while(openBrackets != closedBrackets){
			currCommand = commandParts.remove(0);
			if(isOpenBracket(currCommand))
				openBrackets++;
			if(isClosedBracket(currCommand))
				closedBrackets++;
			else
				innerCommands.add(currCommand);
		}
		return create(innerCommands);
	}

	public Node createNode(String strNode) throws SLogoException {
		Node node = null;
		if (isNumeric(strNode))
			return new NumericNode(Double.parseDouble(strNode));
		String commandName = myCommandDriver.getString(strNode);
		if (commandName == null)
			throw new SLogoException(myResourcesDriver.getString("TheCommand") + strNode + myResourcesDriver.getString("Illegal"));
		else
			try {
				node = (Node) Class.forName(myResourcesDriver.getString("CommandNode") + commandName + myResourcesDriver.getString("Node")).newInstance();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new SLogoException(myResourcesDriver.getString("Command") + commandName + myResourcesDriver.getString("Implemented"));
			}
		return node;
	}

	private boolean isNumeric(String str){
		return str.matches("[-+]?\\d*\\.?\\d+");
	}

	private boolean isOpenBracket(String str){
		return str.equals("[");
	}

	private boolean isClosedBracket(String str){
		return str.equals("]");
	}

}