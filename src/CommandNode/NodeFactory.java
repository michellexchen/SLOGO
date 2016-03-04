package CommandNode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Controller.CommandDriver;
import Controller.LanguageDriver;
import Exception.SLogoException;

/**
 * SLogo's Node Factory that creates and returns root node with subnodes
 * 
 * @author Adam Tache
 *
 */

public class NodeFactory {

	private CommandDriver CommandsDriver;
	private LanguageDriver langDriver = new LanguageDriver();

	public NodeFactory() throws SLogoException {
		CommandsDriver = new CommandDriver();
		langDriver = new LanguageDriver();
		langDriver.load("English"); // To remove
	}

	public Node createNode(String strNode) throws SLogoException {
		//sanitate(nodeList);
		Node node = null;
		if (!langDriver.getLanguage().equals("English")) {
			// englishCommand = langDriver.getTranslation(myNode);
			// TODO: Implement proper translation (switch value and key in
			// current languages files)
		}
		if (isNumeric(strNode))
			return new NumericNode(Double.parseDouble(strNode));
		String commandName = CommandsDriver.getString(strNode);
		if (commandName == null)
			throw new SLogoException("The command \"" + strNode + "\" is illegal!");
		else
			try {
				node = (Node) Class.forName("CommandNode." + commandName + "Node").newInstance();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new SLogoException("Command " + commandName + " is not yet implemented");
			}
		System.out.println("NODE CREATED " + node.toString());
		return node;
	}

	public void sanitate(List<String>nodeList){
		int idxOfMake = nodeList.indexOf("make");
		if(idxOfMake > 0){
			
		}
	}

	private boolean isNumeric(String s) {
		return s.matches("[-+]?\\d*\\.?\\d+");
	}
	
	public List<Node> createChildren(Node root, List<Node> nodeList) throws SLogoException {
		for (int x = 0; x < root.getNumChildren(); x++) {
			HashMap<Node, List<Node>> childToRemaindersMap = createChild(nodeList);
			Iterator it = childToRemaindersMap.entrySet().iterator();
			Map.Entry pair = (Map.Entry) it.next();
			Node child = (Node) pair.getKey();
			nodeList = (List<Node>) pair.getValue();
			((CommandNode) root).addChild(child);
			System.out.println("NEW CHILD: " + child + " REMAINDERS: " + nodeList);
		}
		return nodeList;
	}

	public HashMap<Node, List<Node>> createChild(List<Node> myNodes) throws SLogoException {
		HashMap<Node, List<Node>> childToRemaindersMap = new HashMap<Node, List<Node>>();
		Node child = myNodes.get(0);
		myNodes.remove(0);
		childToRemaindersMap.put(child, myNodes);
		if (myNodes.size() > 0) {
			for (int x = 0; x < child.getNumChildren(); x++) {
				HashMap<Node, List<Node>> childChildToRemaindersMap = createChild(myNodes);
				Iterator it = childChildToRemaindersMap.entrySet().iterator();
				Map.Entry pair = (Map.Entry) it.next();
				Node childChild = (Node) pair.getKey();
				myNodes = (List<Node>) pair.getValue();
				((CommandNode) child).addChild(childChild);
			}
		}
		return childToRemaindersMap;
	}
}