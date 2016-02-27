package Controller;

import java.util.ArrayList;

import Model.CommandNode;
import Model.NumericNode;

public class NodeFactory {

	CommandsDriver CommandsDriver;

	public NodeFactory(){
		CommandsDriver = new CommandsDriver();
	}

	public CommandNode createNode(ArrayList<String> myNodes){
		CommandNode node = null;
		if(myNodes.size() == 0)
			return null;
		String currNode = myNodes.get(0);
		String CommandName = CommandsDriver.getString(currNode);
		if(CommandName == null){
			if(isNumeric(CommandName)){
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
				node = (CommandNode) cls.newInstance();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				// Throw command not implemented exception
			}
		}
		int numChildren = node.getNumChildren();
		for(int x=0; x<numChildren; x++){
			myNodes.remove(0);
			node.addChild(createNode(myNodes));
		}
		return node;
	}

	private boolean isNumeric(String s) {
		return s.matches("[-+]?\\d*\\.?\\d+");
	}
}