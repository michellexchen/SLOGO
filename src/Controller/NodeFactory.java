package Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import Model.CommandNode;
import Model.Node;
import Model.NumericNode;

/**
 * SLogo's Node Factory that creates and returns root node with subnodes
 * 
 * @author Adam Tache
 *
 */

public class NodeFactory {

	private CommandsDriver CommandsDriver;
	private LanguagesDriver langDriver = new LanguagesDriver();

	public NodeFactory() throws SLogoException {
		CommandsDriver = new CommandsDriver();
		langDriver = new LanguagesDriver();
		langDriver.load("English"); // To remove
	}

	public Node createNode(String englishCommand) throws SLogoException {
		Node node = null;
		if (!langDriver.getLanguage().equals("English")) {
			// englishCommand = langDriver.getTranslation(myNode);
			// TODO: Implement proper translation (switch value and key in
			// current languages files)
		}
		if (isNumeric(englishCommand))
			return new NumericNode(Double.parseDouble(englishCommand));
		String commandName = CommandsDriver.getString(englishCommand);
		if (commandName == null)
			throw new SLogoException("The command " + englishCommand + " is illegal");
		else
			try {
				node = (Node) Class.forName("Model." + commandName + "Node").newInstance();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new SLogoException("Command " + commandName + " is not yet implemented");
			}
		System.out.println("NODE CREATED " + node.toString());
		return node;
	}

	public HashMap<Node, List<String>> createChild(List<String> myNodes) throws SLogoException {
		HashMap<Node, List<String>> childToRemaindersMap = new HashMap<Node, List<String>>();
		Node child = createNode(myNodes.get(0));
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

	private boolean isNumeric(String s) {
		return s.matches("[-+]?\\d*\\.?\\d+");
	}
}