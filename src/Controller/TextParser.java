package Controller;

import Model.CommandNode;
import Model.CommandTree;

public class TextParser {
	private String text;
	private String[] commandParts;
	CommandTree currtree;

	public TextParser(String text) {
		this.text = text;
		parseCommands(text);
		setUpTree(true);
	}

	private void parseCommands(String text) {
		commandParts = text.split(" ");
		currtree.createTree(commandParts);
	}

	public void setUpTree(boolean init) {
		// either set up the tree structure
		if (init) {
			if (currtree == null) {
				currtree = new CommandTree();
			}
		}
		// or set the tree up so that it can create a new tree structure for a
		// new set of commands
		else {
			currtree = null;
		}
	}
}