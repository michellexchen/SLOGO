package parser;

import java.util.ArrayList;
import java.util.List;

import commandnode.ListNode;
import commandnode.Node;
import commandnode.NumericNode;
import commandnode.TellNode;
import commandnode.VariableCommand;
import exception.SLogoException;
import model.ResourceLoader;
import model.SLogoWorkspace;
import commandnode.VariableNode;

/**
 * SLogo's Tree Factory that creates abstract syntax tree of Nodes Creates
 * Nodes, that can either be CommandNodes (if command) or NumericNode (if
 * numerical)
 * 
 * @author Adam Tache
 *
 */

public class TreeFactory {
	
	/*
	 * resources used by tree factory class
	 */
	private CommandNameLoader myCommandNodeLoader;
	private ResourceLoader myResourcesLoader;
	private SLogoWorkspace myWorkspace;
	
	/*
	 *  constants used to determine active turtles
	 */
	private static final String TURTLECREATE = "tell";
	private static final String TURTLECREATE2 = "ask";
	private static final String TURTLESID = "TURTLES";

	public TreeFactory(SLogoWorkspace ws) throws SLogoException {
		myCommandNodeLoader = new CommandNameLoader();
		myResourcesLoader = new ResourceLoader();
		myWorkspace = ws;
	}

	/**
	 * Create our tree structure and necessary nodes with the list of parsed and formatted user input
	 * 
	 * @param List<String> commandParts - a list of formated input parsed and ready to be used for creation of Nodes
	 */
	public List<Node> createNodes(List<String> commandParts) throws SLogoException {
		List<Node> myRoots = new ArrayList<Node>();
		while (!commandParts.isEmpty()) {
			String currCommand = commandParts.remove(0);
			Node myNode = createNode(currCommand);
			while (myNode.numCurrentChildren() != myNode.numRequiredChildren()) {
				myNode.addChild(createChild(commandParts));
			}
			myRoots.add(myNode);
		}
		return myRoots;
	}

	/**
	 * Create children of an already process Node
	 * 
	 * @param List<String> commandParts - a list of formated input parsed and ready to be used for creation of Nodes
	 */
	private Node createChild(List<String> commandParts) throws SLogoException {
		if(commandParts.isEmpty()) return null;
		String currCommand = commandParts.remove(0);
		if(isOpenBracket(currCommand) || isOpenParenthesis(currCommand)) {
			System.out.println("AT BRACKET, Command Parts: " + commandParts);
			List<String> innerCommands = createCommandList(commandParts);
			List<String> innerCommandsClone = new ArrayList<String>();
			for(String command : innerCommands){
				innerCommandsClone.add(command);
			}
			ListNode listNode;
			if(isOpenParenthesis(currCommand)){
				listNode = new ListNode(innerCommands.toArray(new String[innerCommands.size()]));
			}
			else{
				listNode = new ListNode(createNodes(innerCommands));
				listNode.setInnerCommands(innerCommandsClone);
				return listNode;
			}
		}
		if(isVariable(currCommand)) {
			VariableNode myVar = new VariableNode(currCommand);
			myVar.setWorkspace(myWorkspace);
			return myVar;
		} else{
			Node myChild = createNode(currCommand);
			while (myChild.numCurrentChildren() != myChild.numRequiredChildren()) {
				myChild.addChild(createChild(commandParts));
			}
			return myChild;
		}
	}

	
	/**
	 * Create necessary tree processing when user inputs a list contained within brackets
	 * 
	 * @param List<String> commandParts - a list of formated input parsed and ready to be used for creation of Nodes
	 */
	private List<String> createCommandList(List<String> commandParts) throws SLogoException {
		//3/11 @ 945 checking it's legitimacy 
//		if(commandParts.isEmpty()){
//			System.out.println("let me know");
//		}
		List<String> innerCommands = new ArrayList<String>();
		int openBrackets = 1;
		int closedBrackets = 0;
		String currCommand = "";
		while (openBrackets != closedBrackets) {
			currCommand = commandParts.remove(0);
			if (isOpenBracket(currCommand) || isOpenParenthesis(currCommand))
				openBrackets++;
			else if (isClosedBracket(currCommand) || isClosedParenthesis(currCommand))
				closedBrackets++;
			else
				innerCommands.add(currCommand);
		}
		return innerCommands;
	}
	/**
	 * Create node
	 * 
	 * @param String strNode - create the necessary node from the string passed
	 */
	private Node createNode(String strNode) throws SLogoException {
		Node node = null;
		if (isNumeric(strNode))
			return new NumericNode(Double.parseDouble(strNode));
		String commandName = myCommandNodeLoader.getString(strNode);
		if(isCreateTurtle(commandName)) {
			TellNode myChild = new TellNode();
			myChild.setWorkspace(myWorkspace);
			return myChild;
		}
		if (commandName == null)
			throw new SLogoException(
					myResourcesLoader.getString("TheCommand") + strNode + myResourcesLoader.getString("Illegal"));
		else
			try {
				node = (Node) Class.forName(
						myResourcesLoader.getString("CommandNode") + commandName + myResourcesLoader.getString("Node"))
						.newInstance();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new SLogoException(myResourcesLoader.getString("Command") + commandName
						+ myResourcesLoader.getString("Implemented"));
			}
		if(node instanceof VariableCommand){
			((VariableCommand)node).setWorkspace(myWorkspace);
		}
		return node;
	}

	/**
	 * Determine if the string inputed has a string regrex equal to numeric notation
	 * 
	 * @param String str - string to be compared
	 */
	private boolean isNumeric(String str) {
		return str.matches("[-+]?\\d*\\.?\\d+");
	}

	/**
	 * Determine if the string inputed is an open parenthesis
	 * 
	 * @param String str - string to be compared
	 */
	private boolean isOpenBracket(String str) {
		return str.equals("[");
	}

	/**
	 * Determine if the string inputed is an open parenthesis
	 * 
	 * @param String str - string to be compared
	 */
	private boolean isOpenParenthesis(String str) {
		return str.equals("(");
	}

	/**
	 * Determine if the string inputed is regrex equal to variable notation
	 * 
	 * @param String str - string to be compared
	 */
	private boolean isVariable(String str) {
		return str.matches(":[a-zA-Z_]+");
	}

	/**
	 * Determine if the string inputed is a closed bracket
	 * 
	 * @param String str - string to be compared
	 */
	private boolean isClosedBracket(String str) {
		return str.equals("]");
	}

	/**
	 * Determine if the string inputed is a closed parenthesis
	 * 
	 * @param String str - string to be compared
	 */
	private boolean isClosedParenthesis(String currCommand) {
		return currCommand.equals(")");
	}
	
	/**
	 * Determine if the string inputed is used to create a turtle command
	 * 
	 * @param String str - string to be compared
	 */
	private boolean isCreateTurtle(String str) {
		return str.toLowerCase().matches(TURTLECREATE2) || str.toLowerCase().matches(TURTLECREATE) || str.toUpperCase().matches(TURTLESID);
	}

}