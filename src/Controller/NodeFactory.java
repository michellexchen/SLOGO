package Controller;

import java.util.ArrayList;

import Model.CommandNode;
import Model.Node;
import Model.NumericNode;
import Model.Turtle;
import Model.TurtleStateCommand;

/**
 * SLogo's Node Factory that creates first Node in list of unparsed nodes in String form
 * Updates unparsed nodes list after node is created
 * Creates sub-nodes if necessary
 * @author Adam Tache
 *
 */

public class NodeFactory {

	CommandsDriver CommandsDriver;

	public NodeFactory(){
		CommandsDriver = new CommandsDriver();
	}

	public Node createNode(ArrayList<String> myNodes, Turtle turtle){
		Node node = null;
		if(myNodes.size() == 0)
			return null;
		String currNode = myNodes.get(0);
		String CommandName = CommandsDriver.getString(currNode);
		if(CommandName == null){
			if(isNumeric(currNode)){
				node = new NumericNode(Double.parseDouble(currNode));
			}
			else{
				// Throw illegal command exception
			}
		}
		else{
			String clsName = CommandName+"Node";
			Class cls;
			try {
				cls = Class.forName(clsName);
				node = (Node) cls.newInstance();
				if(node instanceof TurtleStateCommand){
					((TurtleStateCommand) node).setTurtle(turtle);
				}
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				// Throw command not implemented exception
			}
		}
		myNodes.remove(0);
		int numChildren = node.getNumChildren();
		for(int x=0; x<numChildren; x++){
			((CommandNode) node).addChild(createNode(myNodes, turtle));
		}
		return node;
	}

	private boolean isNumeric(String s) {
		return s.matches("[-+]?\\d*\\.?\\d+");
	}
}