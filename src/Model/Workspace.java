package Model;

import java.util.ArrayList;
import java.util.List;

import Controller.SLogoException;
import Controller.TreeFactory;

/**
 * @author Adam
 *
 */
public class Workspace {

	private List<DisplayData> myDataList;
	private List<String> myCommandHistory;
	private List<Character> myCharacters;
	private TreeFactory myTreeFactory;

	public Workspace() {
		myDataList = new ArrayList<DisplayData>();
		myCommandHistory = new ArrayList<String>();
		myCharacters = new ArrayList<Character>();
		myTreeFactory = new TreeFactory();
	}

	public void createTurtle() {
		Turtle myTurtle = new Turtle("OG", 0, 0, true, 0, false, 0);
		myCharacters.add(myTurtle);
		DisplayData turtleData = new DisplayData(myTurtle.getState());
		myDataList.add(turtleData);
	}

	public List<DisplayData> getDataList() {
		return myDataList;
	}

	public List<String> getCommandHistory() {
		return myCommandHistory;
	}

	public List<Character> getCharacterList() {
		return myCharacters;
	}

	public void addNewCharacter(Character character) {
		myCharacters.add(character);
	}

	public void removeCharacter(Character character) {
		myCharacters.remove(character);
	}

	public void addNewHistoryCommand(String command) {
		myCommandHistory.add(command);
	}

	public void addDisplayData(DisplayData displayData) {
		myDataList.add(displayData);
	}

	public void readCommand(String command) throws SLogoException {
		CommandTree myTree = myTreeFactory.makeTree(command);
		for (Character character : myCharacters) {
			myTree.traverse(character.getState());
			myDataList.get(myCharacters.indexOf(character)).updateData(character.getState());
		}
	}
}