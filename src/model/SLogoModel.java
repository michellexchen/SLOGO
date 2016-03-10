package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import exception.SLogoException;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.MenuItem;
import parser.SLogoParser;
import view.View;

/**
 * 
 * Model class that houses workspaces and saves metadata
 * for SLogo IDE
 *
 */
public class SLogoModel implements Model {

	private View myView;
	private SLogoWorkspace myCurrentWorkspace;
	private LanguageLoader myLanguageDriver;
	private List<SLogoWorkspace> myWorkspaces;
	private ObservableList<SLogoWorkspace> myObservableWorkspaces;
	
	//Question: What is this private variable doing?
	private int numHistoricalTurtles;

	public SLogoModel() throws SLogoException {
		myWorkspaces = new ArrayList<SLogoWorkspace>();
		myObservableWorkspaces = FXCollections.observableArrayList(myWorkspaces);
	}

	public SLogoModel(View view) {
		myView = view;
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
	}

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

	public void readCommand(String command) throws SLogoException {
		SLogoParser parser = new SLogoParser(myCurrentWorkspace);
		parser.readCommand(command);
	}

	/**
	 * @return the myView
	 */
	public View<?> getView() {
		return myView;
	}

	/**
	 * @param myView
	 *            the View to set
	 */
	public void setView(View myView) {
		this.myView = myView;
	}

	@Override
	public void createBackend() {

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
		
		//ADDED
		numHistoricalTurtles++;
		
		
		getObservableWorkspaces().add(myWorkspace);
		setCurrentWorkspace(myWorkspace);
		
		//Also add a MenuItem
		getView().createMenuItem();
	}

//	private void createMenuItem () {
//		//int  = getView().getMenuItem().size();
//		
//		//Name the menuitem
//		MenuItem myMenuItem = new MenuItem("Workspace " + 
//										getView().getMenuItems().size());
//		//Add it to List<MenuItem>
//		getView().getMenuItems().add(myMenuItem);
//		
//		
//		
//	}
	
	@Override
	public void addWorkspace() throws SLogoException, IOException {
		//Need to get View to create a new Visualizer for this workspace
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


}