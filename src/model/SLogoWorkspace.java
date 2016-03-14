package model;

import java.util.ArrayList;
import java.util.List;

import commandnode.ListNode;
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
    private List<SLogoCharacter> myCharacters;
    private List<SLogoCharacter> myActiveTurtles;
    private ObservableList<SLogoDisplayData> myObservableDataList;
    private ObservableList<SLogoVariable> myObservableVariableList;
    private ObservableList<SLogoCustomCommand> myObservableCustomList;
    private ObservableList<int[]> myObservableColorList;
    private ObservableList<String> myObservableShapeList;
    private SLogoPropertiesData myPropertiesData;
    private SLogoPenData myPenData;
    private ColorLoader myColorLoader;

    /**
     * Default constructor that initializes lists
     * 
     * @param view
     * @throws SLogoException
     */
    public SLogoWorkspace(View view) throws SLogoException {
        myView = view;
        createObservableLists();
        turtleFactory = new SLogoTurtleFactory(this);
        myCharacters = new ArrayList<>();
        myPropertiesData = myView.getCurrentVisualizer().getPropertiesData();
        myColorLoader = new ColorLoader();
    }

    /**
     * Initializes RootEvaluator and ActiveTurtles
     * 
     * @throws SLogoException
     */
    public void initialize() throws SLogoException {
        myRootEvaluator = new RootEvaluator(this);
        myActiveTurtles = new ArrayList<>();
        resetActiveTurtles();
        myActiveTurtles.add(turtleFactory.createDefaultTurtle());
    }

    /**
     * Observable lists initialized using ArrayLists
     * 
     */
    private void createObservableLists() {
        myObservableDataList = FXCollections.
                observableArrayList(new ArrayList<SLogoDisplayData>());
        myObservableVariableList =  FXCollections.
                observableArrayList(new ArrayList<SLogoVariable>());
        myObservableCustomList =  FXCollections.
                observableArrayList(new ArrayList<SLogoCustomCommand>());
    }

    /**
     * Listeners added at initialization
     * 
     */
    public void addListeners() {
        getObservableDataList().addListener((ListChangeListener) 
                   change -> getView().updateDisplayData());

        getObservableVariableList().addListener((ListChangeListener) 
                change -> getView().updateVariables(getObservableVariableList()));
        
        getObservableCustomList().addListener((ListChangeListener) 
                   change -> getView().updateCustoms(getObservableCustomList()));
    }

    /**
     * Returns a list of characters
     * 
     * @return
     */
    public List<SLogoCharacter> getCharacterList() {
        return myCharacters;
    }

    /**
     * Returns turtleFactory currently being used
     * 
     * @return
     */
    public SLogoTurtleFactory getCurrentTurtleFactory() {
        return turtleFactory;
    }

    /**
     * Adds a character to list
     * 
     * @param character
     */
    public void addNewCharacter(SLogoCharacter character) {
        myCharacters.add(character);
    }

    /**
     * Returns the ActiveTurtle List
     * 
     * @return
     */
    public List<SLogoCharacter> getActiveTurtlesList() {
        return myActiveTurtles;
    }

    /**
     * Adds an active turtle to the list
     * 
     * @param turtle
     */
    public void addActiveTurtle(SLogoCharacter turtle){
        myActiveTurtles.add(turtle);
    }

    /**
     * Clears the list
     * 
     */
    public void resetActiveTurtles(){
        myActiveTurtles.clear();
    }

    /**
     * Removes a character from list
     * 
     * @param character
     */
    public void removeCharacter(SLogoCharacter character) {
        myCharacters.remove(character);
    }

    /**
     * 
     * Adds a Variable to the observablelist
     * 
     * @param variable
     */
    public void addVariable(SLogoVariable variable){
    	myObservableVariableList.add(variable);
    }
    
    /**
     * Adds a custom command to the observablelist
     * 
     * @param variable
     */
    public void addCustomCommand(SLogoCustomCommand custom){
    	myObservableCustomList.add(custom);
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
     * Returns ObservableVariableList
     * 
     * @return
     */
    public ObservableList<SLogoVariable> getObservableVariableList() {
        return myObservableVariableList;
    }
    
    /**
     * Returns ObservableCustomList
     * 
     * @return
     */
    public ObservableList<SLogoCustomCommand> getObservableCustomList() {
        return myObservableCustomList;
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

    /**
     * @return the myObservableColorList
     */
    public ObservableList<int[]> getMyObservableColorList () {
        return myObservableColorList;
    }

    /**
     * @return the myObservableShapeList
     */
    public ObservableList<String> getMyObservableShapeList () {
        return myObservableShapeList;
    }

    /**
     * @return the myPropertiesData
     */
    public SLogoPropertiesData getMyPropertiesData () {
        return myPropertiesData;
    }

    /**
     * @return the myPenData
     */
    public SLogoPenData getMyPenData () {
        return myPenData;
    }

    /**
     * @return the myColorLoader
     */
    public ColorLoader getMyColorLoader () {
        return myColorLoader;
    }
    
    /**
     * Creates a new custom command and modifies the old custom command if needed
     * 
     * @params commandName varList, commandList
     * @return created custom command
     */
    public SLogoCustomCommand createCustomCommand(String commandName, 
                                                  ListNode varList, 
                                                  ListNode commandList){
        boolean created = false;
        SLogoCustomCommand varModified = 
                new SLogoCustomCommand(commandName, varList, commandList);
        for (SLogoCustomCommand custom : myObservableCustomList) {
            if (custom.getName().equals(commandName)) {
                ((SLogoCustomCommand) custom).setMyCommands(commandList);
                ((SLogoCustomCommand) custom).setMyVariables(varList);
                created = true;
            }
        }
        if(created) {
            getObservableCustomList().remove(varModified);
        }
        getObservableCustomList().add(
                           new SLogoCustomCommand(commandName, varList, commandList));
        return lookupCustomCommand(commandName);
	}
    
    /**
     * Variable lookup by name
     * 
     * @param varName
     * @return created custom variable
     */
    public double getVarValueByName(String varName){
        for(SLogoVariable var : myObservableVariableList){
            if(var.getName().equals(varName)) {
                return var.getValue();
            }
        }
        return 0;
    }

    /**
     * See if a Variable is currently in the list and returns if it's present
     * 
     * @param varName
     * @return
     */
    public SLogoVariable lookupVariable(String varName){
        for(SLogoVariable var : myObservableVariableList){
            if(var.getName().equals(varName)) {
                return var;
            }
        }
        return null;
    }
    
    /**
     * See if a Variable is currently in the list and returns if it's present
     * 
     * @param varName
     * @return
     */
    public SLogoCustomCommand lookupCustomCommand(String varName){
        for(SLogoCustomCommand custom : myObservableCustomList){
            if(custom.getName().equals(varName)) {
                return custom;
            }
        }
        return null;
    }

    /**
     * Creates a new variable and modifies the variable if needed
     * 
     * @param varName
     * @param varValue
     * @return
     */
    public SLogoVariable createVariable(String varName, double varValue){
        boolean created = false;
        SLogoVariable varModified = new SLogoVariable(varName, varValue);
        for(SLogoVariable var : myObservableVariableList){
            if(var.getName().equals(varName)) {
                varModified = (SLogoVariable) var;
                created = true;
            }
        }
        if(created) {
        	getObservableVariableList().remove(varModified);
        }
        getObservableVariableList().add(new SLogoVariable(varName, varValue));
        return lookupVariable(varName);
    }
}