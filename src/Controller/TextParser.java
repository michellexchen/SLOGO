package Controller;

import Model.CommandNode;

public class TextParser {
	private CommandNode root;
	private String text;
	private String[] commandParts;

	public TextParser(String text) {
		this.text = text;
		parseCommands(text);

	}

	public void parseCommands(String text) {
		commandParts = text.split(" ");
	}
}