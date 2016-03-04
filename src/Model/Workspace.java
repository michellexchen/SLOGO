package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import CommandNode.CommandTree;
import CommandNode.DisplayData;
import CommandNode.Node;
import CommandNode.TreeFactory;
import Controller.Parser;
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

	public Workspace(View view) throws SLogoException {
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
		//		format(command);
		Parser parser = new Parser();
		List<Node> nodeList = parser.parse(command);
		while(nodeList.size() > 0){
			HashMap<CommandTree, List<Node>> tuple = myTreeFactory.makeTree(nodeList);
			Iterator it = tuple.entrySet().iterator();
			Map.Entry pair = (Map.Entry) it.next();
			CommandTree myTree = (CommandTree) pair.getKey();
			nodeList = (List<Node>) pair.getValue();
		}
	}

	public void traverse(CommandTree myTree) throws SLogoException{
		for (Character character : myCharacters) {
			myTree.traverse(character.getState(), myTree);
			getObservableDataList().get(myCharacters.indexOf(character)).updateData(character.getState());
		}
	}

	public void format(String command){
		checkForVars(command.split(" "));
	}

	public void checkForVars(String[] command){

		for(int i = 0; i < command.length; i++){
			if(command[i].equals("make")){
				Variable var = new Variable();
				var.setName(command[i+1]);

				//myTreeFactory.makeTree(text)
				i++; //skip checking my next input as it's been checked right now
			}
		}
	}

	public void addToVarList(Variable var){

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