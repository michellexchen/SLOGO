package Model;

import java.util.ArrayList;
import java.util.List;

import CommandNode.CommandTree;
import CommandNode.DisplayData;
import CommandNode.TreeFactory;
import Exception.SLogoException;
import View.View;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * @author Adam
 *
 */
public class Workspace {

	private View myView;

	// NEVER USE THESE: BUG ALERT!
	private List<DisplayData> myDataList;
	private List<String> myCommandHistory;
	private List<Variable> myVariableList;

	// BELOW ARE TO BE USED
	private List<Character> myCharacters;
	private TreeFactory myTreeFactory;
	private ObservableList<DisplayData> myObservableDataList;
	private ObservableList<String> myObservableCommandHistory;
	private ObservableList<Variable> myObservableVariableList;

	public Workspace(View view) {
		myView = view;

		myDataList = new ArrayList<DisplayData>();
		myCommandHistory = new ArrayList<String>();
		myVariableList = new ArrayList<Variable>();
		createObservableLists(myDataList, myCommandHistory, myVariableList);

		myCharacters = new ArrayList<Character>();
		myTreeFactory = new TreeFactory();
	}

	public void initialize() {
		createTurtle();
	}

	private void createObservableLists(List<DisplayData> datalist, List<String> commandhistory,
			List<Variable> variablelist) {
		myObservableDataList = FXCollections.observableArrayList(datalist);
		myObservableCommandHistory = FXCollections.observableArrayList(commandhistory);
		myObservableVariableList = FXCollections.observableArrayList(variablelist);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addListeners() {
		getObservableDataList().addListener((ListChangeListener) change -> getView().updateDisplayData());

		getObservableCommandHistory().addListener((ListChangeListener) change -> getView().updateCommandHistory());

		getObservableCommandHistory().addListener((ListChangeListener) change -> getView().updateVariables());
	}

	public void createTurtle() {
		Turtle myTurtle = new Turtle("OG", 0, 0, true, 0, false, 0);
		myCharacters.add(myTurtle);
		DisplayData turtleData = new DisplayData(myTurtle.getState());

		// Add Observer (Visualizer)
		turtleData.addObserver(getView().getObserver());
		getObservableDataList().add(turtleData);
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
			myTree.traverse(character.getState(), this);
			getObservableDataList().get(myCharacters.indexOf(character)).updateData(character.getState());
		}
	}

	/**
	 * @return the myView
	 */
	public View getView() {
		return myView;
	}

	/**
	 * @param myView
	 *            the myView to set
	 */
	public void setView(View myView) {
		this.myView = myView;
	}

	/**
	 * @return the myObservableDataList
	 */
	public ObservableList<DisplayData> getObservableDataList() {
		return myObservableDataList;
	}

	/**
	 * @param myObservableDataList
	 *            the myObservableDataList to set
	 */
	public void setObservableDataList(ObservableList<DisplayData> myObservableDataList) {
		this.myObservableDataList = myObservableDataList;
	}

	/**
	 * @return the myObservableCommandHistory
	 */
	public ObservableList<String> getObservableCommandHistory() {
		return myObservableCommandHistory;
	}

	/**
	 * @param myObservableCommandHistory
	 *            the myObservableCommandHistory to set
	 */
	public void setObservableCommandHistory(ObservableList<String> myObservableCommandHistory) {
		this.myObservableCommandHistory = myObservableCommandHistory;
	}
}