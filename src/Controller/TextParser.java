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
	}

	private void parseCommands(String text) {
		commandParts = text.split(" ");
		currtree = new CommandTree();
		currtree.createTree(commandParts);
	}

	public String[] grabCurrCommands() {
		return commandParts;
	}

}