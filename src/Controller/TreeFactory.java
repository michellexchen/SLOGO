package Controller;
import java.util.ArrayList;
import java.util.Arrays;
import Model.CommandNode;
import Model.CommandTree;

public class TreeFactory {

	public CommandTree makeTree(String text){
		String[] myNodes = text.split(" ");
		format(myNodes);
		CommandTree myTree = new CommandTree();
		CommandNode root;
		ArrayList<String> nodeList = (ArrayList<String>) Arrays.asList(myNodes);
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