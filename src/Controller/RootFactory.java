package Controller;

import java.util.ArrayList;

import Model.CommandNode;
import Model.Node;
import Model.NumericNode;

/**
 * SLogo's Node Factory that creates and returns root node with subnodes
 * 
 * @author Adam Tache
 *
 */

public class RootFactory {

	CommandsDriver CommandsDriver;

	public RootFactory() {
		CommandsDriver = new CommandsDriver();
	}

	public Node createNode(ArrayList<String> myNodes) throws SLogoException {
		Node node = null;
		if (myNodes.size() == 0)
			return null;
		String currNode = myNodes.get(0);
		LanguagesDriver langDriver = new LanguagesDriver();
		String englishCommand = langDriver.getTranslation(currNode);
		String commandName = CommandsDriver.getString(englishCommand);
		if (commandName == null) {
			if (isNumeric(currNode)) {
				node = new NumericNode(Double.parseDouble(currNode));
			} else {
				throw new SLogoException("This command is illegal");
			}
		} else {
			String clsName = englishCommand + "Node";
			Class cls;
			try {
				cls = Class.forName("Model." + clsName);
				node = (Node) cls.newInstance();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new SLogoException("Command " + commandName+ " is not yet implemented");
			}
		}
		myNodes.remove(0);
		int numChildren = node.getNumChildren();
		for (int x = 0; x < numChildren; x++) {
			((CommandNode) node).addChild(createNode(myNodes));
		}
		return node;
	}

	private boolean isNumeric(String s) {
		return s.matches("[-+]?\\d*\\.?\\d+");
	}
}