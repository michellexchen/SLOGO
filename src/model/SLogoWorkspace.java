package model;

import java.util.ArrayList;
import java.util.List;

import exception.SLogoException;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import view.View;

/**
 * 
 * Workspace class that serves as a container for data objects
 *
 */
public class SLogoWorkspace {

	private View myView;

	// NEVER USE THESE: BUG ALERT!
	private List<SLogoDisplayData> myDataList;
	private List<String> myCommandHistory;
	private List<SLogoVariable> myVariableList;

	// BELOW ARE TO BE USED
	private List<SLogoCharacter> myCharacters;
	private ObservableList<SLogoDisplayData> myObservableDataList;
	private ObservableList<String> myObservableCommandHistory;
	private ObservableList<SLogoVariable> myObservableVariableList;

	public SLogoWorkspace(View view) throws SLogoException {
		myView = view;

		myDataList = new ArrayList<SLogoDisplayData>();
		myCommandHistory = new ArrayList<String>();
		myVariableList = new ArrayList<SLogoVariable>();
		createObservableLists(myDataList, myCommandHistory, myVariableList);

		myCharacters = new ArrayList<SLogoCharacter>();
	}
	
	

	public void initialize() {
		createTurtle();
	}

	private void createObservableLists(List<SLogoDisplayData> datalist, 
									   List<String> commandhistory,
									   List<SLogoVariable> variablelist) {
		myObservableDataList = FXCollections.observableArrayList(datalist);
		myObservableCommandHistory = FXCollections.observableArrayList(commandhistory);
		myObservableVariableList = FXCollections.observableArrayList(variablelist);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addListeners() {
		getObservableDataList().addListener((ListChangeListener) 
				change -> getView().updateDisplayData());

		getObservableCommandHistory().addListener((ListChangeListener) 
				change -> getView().updateCommandHistory());

		getObservableCommandHistory().addListener((ListChangeListener) 
				change -> getView().updateVariables());
	}

	public void createTurtle() {
		SLogoTurtle myTurtle = new SLogoTurtle("OG", 0, 0, true, 0, false, 0);
		myCharacters.add(myTurtle);
		SLogoDisplayData turtleData = new SLogoDisplayData(myTurtle.getState());

		// Add Observer (Visualizer)
		turtleData.addObserver(getView().getObserver());
		getObservableDataList().add(turtleData);
	}

	public List<SLogoDisplayData> getDataList() {
		return myDataList;
	}

	public List<String> getCommandHistory() {
		return myCommandHistory;
	}

	public List<SLogoCharacter> getCharacterList() {
		return myCharacters;
	}

	public void addNewCharacter(SLogoCharacter character) {
		myCharacters.add(character);
	}

	public void removeCharacter(SLogoCharacter character) {
		myCharacters.remove(character);
	}

	public void addNewHistoryCommand(String command) {
		myCommandHistory.add(command);
	}

	public void addDisplayData(SLogoDisplayData displayData) {
		myDataList.add(displayData);
	}

	public void format(String command){
		checkForVars(command.split(" "));
	}

	public void checkForVars(String[] command){

		for(int i = 0; i < command.length; i++){
			if(command[i].equals("make")){
				SLogoVariable var = new SLogoVariable();
				var.setName(command[i+1]);

				//myTreeFactory.makeTree(text)
				i++; //skip checking my next input as it's been checked right now
			}
		}
	}

	public void addToVarList(SLogoVariable var){

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
	public ObservableList<SLogoDisplayData> getObservableDataList() {
		return myObservableDataList;
	}

	/**
	 * @param myObservableDataList
	 *            the myObservableDataList to set
	 */
	public void setObservableDataList(ObservableList<SLogoDisplayData> myObservableDataList) {
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


	public List<SLogoVariable> getVarList() {
		return myObservableVariableList;
	}
}