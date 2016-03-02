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
		myDataList = new ArrayList<DisplayData>();
		myCommandHistory = new ArrayList<String>();		
		createObservableLists(myDataList, myCommandHistory);
		
		myCharacters = new ArrayList<Character>();
		myTreeFactory = new TreeFactory();
	}
	public Workspace(View view) {
		myView = view;
		
		myDataList = new ArrayList<DisplayData>();
		myCommandHistory = new ArrayList<String>();		
		createObservableLists(myDataList, myCommandHistory);
		
		myCharacters = new ArrayList<Character>();
		myTreeFactory = new TreeFactory();
	}
	
	public void initialize () {
//		myDataList = new ArrayList<DisplayData>();
//		myCommandHistory = new ArrayList<String>();		
//		createObservableLists(myDataList, myCommandHistory);
//		
//		myCharacters = new ArrayList<Character>();
//		myTreeFactory = new TreeFactory();
		
		createTurtle();
//		getView().updateDisplayData();
//		getView().updateCommandHistory();
	}
	
	private void createObservableLists (List<DisplayData> datalist, 
			List<String> commandhistory) {
		
		
		myObservableDataList = FXCollections.observableArrayList(datalist);

//		System.out.println("I ran");
//		System.out.println(getObservableDataList());
//		myObservableDataList.addListener(new ListChangeListener() {
//			@Override
//			public void onChanged(ListChangeListener.Change change) {
//				//DO When turtles change
//				getView().updateDisplayData();
//				
//			}
//			
//		});
		
		myObservableCommandHistory = FXCollections.observableArrayList(commandhistory);


	}
//	public void onChanged(ListChangeListener.Change change) {
//		//Update the commandHistory
//		getView().updateCommandHistory();
//	}
//	
//});
//
//}

	
	public void addListeners () {
		getObservableDataList().addListener
		((ListChangeListener) change -> {
		System.out.println("I got updated");
		getView().updateDisplayData();
		
		});
		getObservableCommandHistory().addListener
		((ListChangeListener) change -> getView().updateCommandHistory());
//		for (DisplayData displaydata : getObservableDataList()) {
//			System.out.println("Observer added to Displaydata");
//			displaydata.addObserver(getView().getObserver());
//		}
	}

	public void createTurtle() {
		Turtle myTurtle = new Turtle("OG", 0, 0, true, 0, false, 0);
		myCharacters.add(myTurtle);
		DisplayData turtleData = new DisplayData(myTurtle.getState());
		
		//add observer
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
			myTree.traverse(character.getState());
			getObservableDataList().get(myCharacters.indexOf(character))
										.updateData(character.getState());
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
	/**
	 * @return the myObservableDataList
	 */
	public ObservableList<DisplayData> getObservableDataList() {
		return myObservableDataList;
	}
	/**
	 * @param myObservableDataList the myObservableDataList to set
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
	 * @param myObservableCommandHistory the myObservableCommandHistory to set
	 */
	public void setObservableCommandHistory(ObservableList<String> myObservableCommandHistory) {
		this.myObservableCommandHistory = myObservableCommandHistory;
	}
}