package Controller;

import Model.CommandTree;

public class TextParser {
	private String myCommand;
	private String[] myNodes;
	CommandTree currtree;

	public TextParser(String text) {
		myCommand = text;
		parseCommands(text);
	}

	private void parseCommands(String text) {
		myNodes = text.split(" ");
		currtree = new CommandTree();
		currtree.createTree(myNodes);
	}

	public String[] getNodes() {
		return myNodes;
	}
	
	public String getOriginalCommand(){
		return myCommand;
	}

}