package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import exception.SLogoException;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import view.View;

/**
 * 
 * Workspace class that serves as a container for data objects
 *
 */
public class SLogoWorkspace {

	private View myView;
	private SLogoTurtleFactory turtleFactory;

	// NEVER USE THESE: BUG ALERT!
	private List<SLogoDisplayData> myDataList;
	private List<String> myCommandHistory;
	private List<SLogoVariable> myVariableList;

	// BELOW ARE TO BE USED
	private List<SLogoCharacter> myCharacters;
	private ObservableList<SLogoDisplayData> myObservableDataList;
	private ObservableList<String> myObservableCommandHistory;
	private ObservableList<SLogoVariable> myObservableVariableList;
	private ObservableList<int[]> myObservableColorList;
	private ObservableList<String> myObservableShapeList;

	public SLogoWorkspace(View view) throws SLogoException {
		myView = view;
		turtleFactory = new SLogoTurtleFactory(this);
		myDataList = new ArrayList<SLogoDisplayData>();
		myCommandHistory = new ArrayList<String>();
		myVariableList = new ArrayList<SLogoVariable>();
		createObservableLists(myDataList, myCommandHistory, myVariableList);
		myCharacters = new ArrayList<SLogoCharacter>();
	}
	
	public void initialize() throws SLogoException {
		turtleFactory.createTurtle(turtleFactory.getDefaultID(), turtleFactory.getDefaultX(), turtleFactory.getDefaultY());
	}

	private void createObservableLists(List<SLogoDisplayData> datalist, 
									   List<String> commandhistory,
									   List<SLogoVariable> variableList) {
		myObservableDataList = FXCollections.observableArrayList(datalist);
		myObservableCommandHistory = FXCollections.observableArrayList(commandhistory);
		myObservableVariableList =  FXCollections.observableArrayList(variableList);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addListeners() {
		getObservableDataList().addListener((ListChangeListener) 
				change -> getView().updateDisplayData());

		getObservableCommandHistory().addListener((ListChangeListener) 
				change -> getView().updateCommandHistory());

		//Wrong! changed to variablelist
		getObservableVariableList().addListener((ListChangeListener) 
				change -> getView().updateVariables());
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

	public void addToVarMap(SLogoVariable variable){
		myObservableVariableList.add(variable);
//		for(String each: myObservableVariableMap.keySet()){
//			System.out.println(each + " " + myObservableVariableMap.get(each));
//		}
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
	
	public List<SLogoVariable> getMyVarList(){
		return myVariableList;
	}


	private void testerVariableList(){
		myObservableVariableList.add(new SLogoVariable("hi", 2));
	}
	public List<SLogoVariable> getVarList() {
		return myObservableVariableList;
	}

	public ObservableList<SLogoVariable> getObservableVariableList() {
		return myObservableVariableList;
	}

	public void setObservableVariableList(ObservableList<SLogoVariable> myObservableVariableList) {
		this.myObservableVariableList = myObservableVariableList;
	}

}