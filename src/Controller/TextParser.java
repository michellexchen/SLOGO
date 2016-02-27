package Controller;

import Model.CommandTree;

public class TextParser {
	private String myCommand;
	private String[] myNodes;
	CommandTree myTree;

	public void parse(String text) {
		myNodes = text.split(" ");
		myTree = new CommandTree();
		myTree.createTree(myNodes);
	}
	
	public CommandTree getTree(){
		return myTree;
	}

	public String[] getNodes() {
		return myNodes;
	}
	
	public String getCommand(){
		return myCommand;
	}

}