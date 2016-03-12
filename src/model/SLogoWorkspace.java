package model;

import java.util.ArrayList;
import java.util.List;
import exception.SLogoException;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import view.SLogoPenData;
import parser.RootEvaluator;
import view.SLogoPropertiesData;
import view.View;

/**
 * Workspace class that serves as a container for data objects
 *
 */
public class SLogoWorkspace {
	private View myView;
	private SLogoTurtleFactory turtleFactory;
	private RootEvaluator myRootEvaluator;
	
	// Lists declared for Observable List initialization, not to be used
	private List<SLogoDisplayData> myDataList;
	private List<SLogoVariable> myVariableList;

	
	private List<SLogoCharacter> myCharacters;
	private List<SLogoCharacter> myActiveTurtles;
	private ObservableList<SLogoDisplayData> myObservableDataList;
	private ObservableList<SLogoVariable> myObservableVariableList;
	private ObservableList<int[]> myObservableColorList;
	private ObservableList<String> myObservableShapeList;
	private SLogoPropertiesData myPropertiesData;
	private SLogoPenData myPenData;
	private ColorLoader myColorLoader;
	
	public SLogoWorkspace(View view) throws SLogoException {
		myView = view;
		turtleFactory = new SLogoTurtleFactory(this);
		myDataList = new ArrayList<SLogoDisplayData>();
		myVariableList = new ArrayList<SLogoVariable>();
		createObservableLists(myDataList, myVariableList);
		myCharacters = new ArrayList<SLogoCharacter>();
		myPropertiesData = myView.getCurrentVisualizer().getPropertiesData();
		myColorLoader = new ColorLoader();
	}

	public void initialize() throws SLogoException {
		myRootEvaluator = new RootEvaluator(this);
		resetActiveTurtles();
		myActiveTurtles.add(turtleFactory.createDefaultTurtle());
	}

	/**
	 * Observable lists initialized using currently-existing ArrayLists
	 * 
	 * @param datalist
	 * @param variableList
	 */
	private void createObservableLists(List<SLogoDisplayData> datalist, List<SLogoVariable> variableList) {
		myObservableDataList = FXCollections.observableArrayList(datalist);
		myObservableVariableList =  FXCollections.observableArrayList(variableList);
	}

	/**
	 * Listeners added at initialization
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addListeners() {
		getObservableDataList().addListener((ListChangeListener) 
				change -> getView().updateDisplayData());

		getObservableVariableList().addListener((ListChangeListener) 
				change -> getView().updateVariable(getObservableVariableList()));
	}

	public List<SLogoDisplayData> getDataList() {
		return myDataList;
	}


	public List<SLogoCharacter> getCharacterList() {
		return myCharacters;
	}
	
	public SLogoTurtleFactory getCurrentTurtleFactory() {
		return turtleFactory;
	}

	public void addNewCharacter(SLogoCharacter character) {
		myCharacters.add(character);
	}
	
	public List<SLogoCharacter> getActiveTurtlesList() {
		return myActiveTurtles;
	}
	
	public void addActiveTurtle(SLogoCharacter turtle){
		myActiveTurtles.add(turtle);
	}
	
	public void resetActiveTurtles(){
		myActiveTurtles = new ArrayList<SLogoCharacter>();
	}

	public void removeCharacter(SLogoCharacter character) {
		myCharacters.remove(character);
	}

	public void addDisplayData(SLogoDisplayData displayData) {
		myDataList.add(displayData);
	}

	public void addToVarMap(SLogoVariable variable){
		myObservableVariableList.add(variable);
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

	public List<SLogoVariable> getMyVarList(){
		return myVariableList;
	}

	public double getVarValueByName(String varName){
		for(SLogoVariable var : myObservableVariableList){
			if(var.getName().equals(varName))
				return var.getValue();
		}
		return 0;
	}

	public SLogoVariable lookupVariable(String varName){
		for(SLogoVariable var : myObservableVariableList){
			if(var.getName().equals(varName)){
				return var;
			}
		}
		return null;
	}

	public SLogoVariable createVariable(String varName, double varValue){
		boolean created = false;
		for(SLogoVariable var : myObservableVariableList){
			if(var.getName().equals(varName)){
				var.setValue(varValue);
				created = true;
			}
		}
		if(!created){
			getObservableVariableList().add(new SLogoVariable(varName, varValue));
		}
		return lookupVariable(varName);
	}

	public ObservableList<SLogoVariable> getObservableVariableList() {
		return myObservableVariableList;
	}

	public void setObservableVariableList(ObservableList<SLogoVariable> myObservableVariableList) {
		this.myObservableVariableList = myObservableVariableList;
	}

	/**
	 * @return the myRootEvaluator
	 */
	public RootEvaluator getRootEvaluator() {
		return myRootEvaluator;
	}

	/**
	 * @param myRootEvaluator the myRootEvaluator to set
	 */
	public void setRootEvaluator(RootEvaluator myRootEvaluator) {
		this.myRootEvaluator = myRootEvaluator;
	}
}