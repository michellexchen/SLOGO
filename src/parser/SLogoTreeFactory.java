package parser;

import java.util.ArrayList;
import java.util.List;

import commandnode.ListNode;
import commandnode.Node;
import commandnode.NumericNode;
import commandnode.TellNode;
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

public class SLogoTreeFactory {

	private CommandNameLoader myCommandNodeLoader;
	private ResourceLoader myResourcesLoader;
	private SLogoWorkspace myWorkspace;
	private static final String TURTLECREATE = "tell";
	private static final String TURTLECREATE2 = "ask";

	public SLogoTreeFactory(SLogoWorkspace ws) throws SLogoException {
		myCommandNodeLoader = new CommandNameLoader();
		myResourcesLoader = new ResourceLoader();
		myWorkspace = ws;
	}

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

	private Node createChild(List<String> commandParts) throws SLogoException {
		if(commandParts.isEmpty()) return null;
		String currCommand = commandParts.remove(0);
		if (isOpenBracket(currCommand) || isOpenParenthesis(currCommand)) {
			List<String> innerCommands = createCommandList(commandParts);
			ListNode listNode;
			if(isOpenParenthesis(currCommand)) listNode = new ListNode(innerCommands.toArray(new String[innerCommands.size()]));
			else listNode = new ListNode(createNodes(innerCommands));
			return listNode;
		}
		if (isVariable(currCommand)) {
			VariableNode myVar = new VariableNode(currCommand);
			myVar.setWorkspace(myWorkspace);
			return myVar;
		} 
		 else {
			Node myChild = createNode(currCommand);
			while (myChild.numCurrentChildren() != myChild.numRequiredChildren()) {
				myChild.addChild(createChild(commandParts));
			}
			return myChild;
		}
	}

	private List<String> createCommandList(List<String> commandParts) throws SLogoException {
		if(commandParts.isEmpty()){
			System.out.println("let me know");
		}
		List<String> innerCommands = new ArrayList<String>();
		int openBrackets = 1;
		int closedBrackets = 0;
		String currCommand = "";
		while (openBrackets != closedBrackets) {
			currCommand = commandParts.remove(0);
			if (isOpenBracket(currCommand) || isOpenParenthesis(currCommand))
				openBrackets++;
			if (isClosedBracket(currCommand) || isClosedParenthesis(currCommand))
				closedBrackets++;
			else
				innerCommands.add(currCommand);
		}
		return innerCommands;
	}

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
		return node;
	}

	private boolean isNumeric(String str) {
		return str.matches("[-+]?\\d*\\.?\\d+");
	}

	private boolean isOpenBracket(String str) {
		return str.equals("[");
	}

	private boolean isOpenParenthesis(String str) {
		return str.equals("(");
	}

	private boolean isVariable(String str) {
		return str.matches(":[a-zA-Z_]+");
	}

	private boolean isClosedBracket(String str) {
		return str.equals("]");
	}

	private boolean isClosedParenthesis(String currCommand) {
		return currCommand.equals(")");
	}
	
	private boolean isCreateTurtle(String str) {
		return str.toLowerCase().matches(TURTLECREATE2) || str.toLowerCase().matches(TURTLECREATE);
	}

}