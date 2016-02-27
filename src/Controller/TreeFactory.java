package Controller;
import java.util.ArrayList;
import java.util.Arrays;

import Model.CommandTree;
import Model.Node;
import Model.Turtle;

/**
 * SLogo's Tree Factory that creates abstract syntax tree of Nodes
 * Creates Nodes, that can either be CommandNodes (if command) or NumericNode (if numerical)
 * @author Adam Tache
 *
 */

public class TreeFactory {

	public CommandTree makeTree(String text){
		String[] myNodes = text.split(" ");
		format(myNodes);
		CommandTree myTree = new CommandTree();
		Node root;
		ArrayList<String> nodeList = new ArrayList<String>(Arrays.asList(myNodes));
		NodeFactory NodeFactory = new NodeFactory();
		root = NodeFactory.createNode(nodeList);
		myTree.setRoot(root);
		return myTree;
	}

	public void format(String[] myNodes){
		if(myNodes != null){
			for(int x=0; x<myNodes.length; x++){
				myNodes[x] = myNodes[x].toLowerCase();
			}
		}
	}

}