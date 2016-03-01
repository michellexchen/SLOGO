package Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Model.CommandNode;
import Model.MathNode;
import Model.Node;
import Model.NumericNode;

/**
 * SLogo's Node Factory that creates and returns root node with subnodes
 * 
 * @author Adam Tache
 *
 */

public class NodeFactory {

	CommandsDriver CommandsDriver;

	public NodeFactory() {
		CommandsDriver = new CommandsDriver();
	}

	public Node createNode(String myNode) throws SLogoException {
		Node node = null;
		if (myNode.length() == 0)
			throw new SLogoException("No command entered");
		LanguagesDriver langDriver = new LanguagesDriver();
		String englishCommand = langDriver.getTranslation(myNode);
		String commandName = CommandsDriver.getString(englishCommand);
		if (commandName == null) {
			if (isNumeric(myNode)) {
				node = new NumericNode(Double.parseDouble(myNode));
			} else {
				throw new SLogoException("This command is illegal");
			}
		} else {
			String clsName = englishCommand + "Node";
			Class cls;
			try {
				cls = Class.forName("Model." + clsName);
				node = (Node) cls.newInstance();
				if(clsName.equals("MathNode")){
					((MathNode) node).setType(commandName);
				}
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new SLogoException("Command " + commandName+ " is not yet implemented");
			}
		}
		return node;
	}
	
	public HashMap<Node, List<String>> createChild(List<String> myNodes) throws SLogoException{
		HashMap<Node, List<String>> childToRemaindersMap = new HashMap<Node, List<String>>();
		List<String> remainders = new ArrayList<String>();
		// TODO: consider if child is a command
		Node child = createNode(myNodes.get(0));
		myNodes.remove(0);
		for(int x=0; x<myNodes.size(); x++){
			remainders.add(myNodes.get(x));
		}
		childToRemaindersMap.put(child, remainders);
		if(myNodes.size() > 0){
			double numChildren = child.getNumChildren();
			for(int x=0; x<numChildren; x++){
				HashMap<Node, List<String>> childChildToRemaindersMap = createChild(myNodes);
				Iterator it = childToRemaindersMap.entrySet().iterator();
				Map.Entry pair = (Map.Entry)it.next();
				Node childChild = (Node) pair.getKey();
				myNodes = (List<String>) pair.getValue();
				((CommandNode)child).addChild(child);
			}
		}
		return childToRemaindersMap;
	}

	private boolean isNumeric(String s) {
		return s.matches("[-+]?\\d*\\.?\\d+");
	}
}