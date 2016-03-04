package CommandNode;
import java.util.List;

import Controller.CommandDriver;
import Controller.LanguageDriver;
import Exception.SLogoException;
import Model.Workspace;
import javafx.util.Pair;

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
			Pair<Node, List<Node>> childAndRemainderNodes = createChild(nodeList);
			if(childAndRemainderNodes != null){
				Node child = childAndRemainderNodes.getKey();
				nodeList = childAndRemainderNodes.getValue();
				((CommandNode) root).addChild(child);
			}
		}
		return nodeList;
	}

	public Pair<Node, List<Node>> createChild(List<Node> myNodes) throws SLogoException {
		Pair<Node, List<Node>> childAndRemaindersPair = null;
		Node child = null;
		if(myNodes.size() > 0){
			child = myNodes.get(0);
			myNodes.remove(0);
			childAndRemaindersPair = new Pair<Node, List<Node>>(child, myNodes);
			if (myNodes.size() > 0) {
				for (int x = 0; x < child.getNumChildren(); x++) {
					Pair<Node, List<Node>> childChildToRemaindersPair = createChild(myNodes);
					Node childChild = childChildToRemaindersPair.getKey();
					myNodes = childChildToRemaindersPair.getValue();
					((CommandNode) child).addChild(childChild);
				}
			}
		}
		return childAndRemaindersPair;
	}

	public EnclosureNode createEnclosureNode(int enclosureStart, int enclosureEnd, List<String> nodeTextList, Workspace ws){
		String enclosureContent = "";
		System.out.println(nodeTextList+" "+enclosureStart+" "+enclosureEnd);
		for(int x=enclosureStart; x<=enclosureEnd; x++){
			String currNode = nodeTextList.get(x);
			if(x!=enclosureEnd)
				enclosureContent += currNode+" ";
			else
				enclosureContent += currNode;
		}
		EnclosureNode en = new EnclosureNode();
		en.setBracketContent(enclosureContent);
		en.setWorkspace(ws);
		return en;
	}

	public boolean isOpenEnclosure(String node){
		if(node.equals("(") || node.equals("["))
			return true;
		return false;
	}

	public boolean isClosedEnclosure(String node){
		if(node.equals(")") || node.equals("]"))
			return true;
		return false;
	}
}