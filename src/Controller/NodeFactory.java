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

	private CommandsDriver CommandsDriver;
	private LanguagesDriver langDriver = new LanguagesDriver();

	public NodeFactory() throws SLogoException {
		CommandsDriver = new CommandsDriver();
		langDriver = new LanguagesDriver();
		langDriver.load("English");
	}

	public Node createNode(String myNode) throws SLogoException {
		Node node = null;
		if (myNode.length() == 0)
			throw new SLogoException("No command entered");
		String englishCommand = myNode;
		if(!langDriver.getLanguage().equals("English")){
			// englishCommand = langDriver.getTranslation(myNode);
			// TODO: Implement proper translation (switch value and key in current languages files)
		}
		String commandName = CommandsDriver.getString(englishCommand);
		System.out.println(englishCommand+" "+commandName);
		if (commandName == null) {
			if (isNumeric(myNode)) {
				node = new NumericNode(Double.parseDouble(myNode));
			} else {
				throw new SLogoException("The command " + englishCommand + " is illegal");
			}
		} else {
			String clsName = commandName + "Node";
			Class cls;
			try {
				cls = Class.forName("Model." + clsName);
				node = (Node) cls.newInstance();
				if(clsName.equals("MathNode")){
					((MathNode) node).setType(englishCommand);
				}
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new SLogoException("Command " + commandName+ " is not yet implemented");
			}
		}
		System.out.println("NODE CREATED "+node.toString());
		return node;
	}

	public HashMap<Node, List<String>> createChild(List<String> myNodes) throws SLogoException{
		HashMap<Node, List<String>> childToRemaindersMap = new HashMap<Node, List<String>>();
		List<String> remainders = new ArrayList<String>();
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