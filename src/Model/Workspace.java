package Model;

import java.util.ArrayList;
import java.util.List;

import Controller.SLogoException;
import Controller.TreeFactory;
import View.View;
import View.Visualizer;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * @author Adam
 *
 */
public class Workspace {

	private View myView;


	private List<DisplayData> myDataList;
	private List<String> myCommandHistory;
	
	ObservableList<DisplayData> myObservableDataList;
	ObservableList<String> myObservableCommandHistory;
	
	
	private List<Character> myCharacters;
	private TreeFactory myTreeFactory;
	private Visualizer myVisualizer;

	public Workspace() {

	}
	public Workspace(View view) {
		myView = view;
	}
	
	public void initialize () {
		myDataList = new ArrayList<DisplayData>();
		myCommandHistory = new ArrayList<String>();		
		
		myCharacters = new ArrayList<Character>();
		myTreeFactory = new TreeFactory();
		
		createTurtle();
	}
	
	private void createObservableLists (List<DisplayData> datalist, 
			List<String> commandhistory) {
		myObservableDataList = FXCollections.observableList(datalist);
		myObservableDataList.addListener(new ListChangeListener() {
			@Override
			public void onChanged(ListChangeListener.Change change) {
				//DO When turtles change
				getView().updateDisplayData();
				
			}
			
		});
		myObservableCommandHistory = FXCollections.observableList(commandhistory);
		myObservableCommandHistory.addListener(new ListChangeListener() {
			@Override
			public void onChanged(ListChangeListener.Change change) {
				//Update the commandHistory
				getView().updateCommandHistory();
			}
			
		});

	}
	

	public void createTurtle() {
		Turtle myTurtle = new Turtle("OG", 0, 0, true, 0, false, 0);
		myCharacters.add(myTurtle);
		DisplayData turtleData = new DisplayData(myTurtle.getState());
		//turtleData.addObserver(myVisualizer);
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
	
	
	/**
	 * @return the myView
	 */
	public View getView() {
		return myView;
	}
	/**
	 * @param myView the myView to set
	 */
	public void setView(View myView) {
		this.myView = myView;
	}
}