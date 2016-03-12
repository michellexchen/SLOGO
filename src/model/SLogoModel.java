package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import commandnode.Node;
import exception.SLogoException;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import parser.RootEvaluator;
import parser.SLogoParser;
import view.SLogoView;
import view.View;

/**
 * 
 * Model class that houses workspaces and saves metadata
 * for SLogo IDE
 *
 */
public class SLogoModel implements Model {
	//private static final int NUM_WORKSPACES = 5;
	private View myView;
	private SLogoWorkspace myCurrentWorkspace;
	private LanguageLoader myLanguageDriver;
	private List<SLogoWorkspace> myWorkspaces;
	private ObservableList<SLogoWorkspace> myObservableWorkspaces;
	private RootEvaluator myRootEvaluator;

	public SLogoModel() throws SLogoException {
		myWorkspaces = new ArrayList<SLogoWorkspace>();
		myObservableWorkspaces = FXCollections.observableArrayList(myWorkspaces);
	}

	@Override
	public void loadLanguage () {
		myLanguageDriver = new LanguageLoader();
		try {
			myLanguageDriver.load(getView().getLanguage());
		} catch (SLogoException e) {
			//TODO: Display error
		}
	}

	public void initialize() throws SLogoException {	
		createNewWorkspace();
		myRootEvaluator = new RootEvaluator(myCurrentWorkspace);
	}

	/**
	 * Adds Listeners to all the Observable lists that are present
	 * in model
	 * 
	 * Called at initialization
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addListeners () {
		myObservableWorkspaces.addListener((ListChangeListener) c -> {
			getView().updateWorkspaces();
			getView().setCurrentWorkspace(getCurrentWorkspace());
		});
		myCurrentWorkspace.addListeners();

		getView().updateDisplayData();
		getView().updateCommandHistory();
		getView().updateWorkspaces();
	}

	/**
	 * Creates a parser instance and feeds the command to the parser
	 * called by View through interface
	 * 
	 */
	@Override
	public void readCommand(String command) throws SLogoException {
		SLogoParser parser = new SLogoParser(myCurrentWorkspace);
		List<Node> myRoots = parser.readCommand(command);
		myRootEvaluator.evaluateRoots(myRoots);
	}

	/**
	 * Switches workspaces given the index
	 * Used in conjunction with switchVisualizer
	 * 
	 */
	@Override
	public void switchWorkspace(int index) throws SLogoException {
		if (index >= getObservableWorkspaces().size()) {
			throw new SLogoException
				("This project doesn't exist!\nPlease click + button first!");
		}
		setCurrentWorkspace(getObservableWorkspaces().get(index));
		getView().switchVisualizer(index);
	}
	
	/**
	 * @return the myView
	 */
	public View<?> getView() {
		return myView;
	}

	@Override
	public ObservableList<SLogoDisplayData> getObservableDataList() {
		return myCurrentWorkspace.getObservableDataList();
	}

	/**
	 * Create a new workspace and set it as current workspace
	 * @throws SLogoException 
	 */
	//	@Override
	public void createNewWorkspace() throws SLogoException {
		SLogoWorkspace myWorkspace = new SLogoWorkspace(getView());
		myWorkspace.initialize();
		getObservableWorkspaces().add(myWorkspace);
		setCurrentWorkspace(myWorkspace);
	}

	/**
	 * Adds a new workspace and initializes it
	 * Also calls addVisualizer that adds a visualizer in the frontend
	 * 
	 */
	@Override
	public void addWorkspace() throws SLogoException, IOException {
		createNewWorkspace();
		getView().addVisualizer();
		getCurrentWorkspace().addListeners();
		getView().updateDisplayData();
	}

	/**
	 * @return the myCurrentWorkspace
	 */
	@Override
	public SLogoWorkspace getCurrentWorkspace() {
		return myCurrentWorkspace;
	}

	/**
	 * @param myCurrentWorkspace the myCurrentWorkspace to set
	 */
	public void setCurrentWorkspace(SLogoWorkspace myCurrentWorkspace) {
		this.myCurrentWorkspace = myCurrentWorkspace;
	}

	/**
	 * @return the myObservableWorkspaces
	 */
	public ObservableList<SLogoWorkspace> getObservableWorkspaces() {
		return myObservableWorkspaces;
	}

	/**
	 * @param myObservableWorkspaces the myObservableWorkspaces to set
	 */
	public void setObservableWorkspaces(ObservableList<SLogoWorkspace> myObservableWorkspaces) {
		this.myObservableWorkspaces = myObservableWorkspaces;
	}

	/**
	 * @return the myLanguageDriver
	 */
	public LanguageLoader getMyLanguageDriver() {
		return myLanguageDriver;
	}

	/**
	 * @param myLanguageDriver the myLanguageDriver to set
	 */
	public void setMyLanguageDriver(LanguageLoader myLanguageDriver) {
		this.myLanguageDriver = myLanguageDriver;
	}

	/**
	 * @param myView the myView to set
	 */
	public void setView(View myView) {
		this.myView = myView;
	}
}