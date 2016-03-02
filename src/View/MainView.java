package View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import Controller.LanguagesDriver;
import Controller.SLogoException;
import Model.Model;

public class MainView implements View {

	private final int WIDTH = 331;
	private final int HEIGHT = 331;

	
	private LanguagesDriver myLangDriver;

	private CommandHistoryViewer myHistory;
//	private WorkspaceView myCurrentWorkspaceView;
//	private List<WorkspaceView> myProjects;

	
	
	private String myCommand;
	private Model myModel;
	
	
	private Visualizer myVisualizer;
	

	public MainView() throws SLogoException {
//		myHistory = new CommandHistoryViewer();
//		myProjects = new ArrayList<Project>();
		myLangDriver = new LanguagesDriver();
		String language = "English"; // Get from UI
		myLangDriver.load(language);
	}

	public MainView(Model model) throws SLogoException {
//		myHistory = new CommandHistoryViewer();
//		myProjects = new ArrayList<Project>();
//		String language = "English"; // Get from UI
		myModel = model;
	}

	/**
	 * Called at start
	 * Initializes necessary classes used to visualize turtles
	 */
	public void initialize() throws SLogoException {
		myVisualizer = new Visualizer(WIDTH, HEIGHT);
		myLangDriver = new LanguagesDriver();

		String language = "English"; // Get from UI

		myLangDriver.load(language);


	}
	
	public void showProject(WorkspaceView project) throws SLogoException {
		project.show();

	}

	public void showError(SLogoException e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("Error Occurred");
		alert.setContentText("Ooops, there was an error!");

		alert.showAndWait();
		// Or restart the simulation
	}

	/*
	 * clear() wipes out all the projects we have and restarts
	 */
//	public void clear() {
//		getMyProjects().clear();
//		// TODO: Code for deleting all the projects existent
//	}

	public void addProject() throws IOException, SLogoException {
		WorkspaceView myNewProject = new WorkspaceView();

		try {
			myNewProject.initialize();
		} catch (SLogoException e) {
			throw new SLogoException("project did not initialize");
		}
		getMyProjects().add(myNewProject);
	}

	public void addProject(Model model) throws IOException, SLogoException {
		WorkspaceView myNewProject = new WorkspaceView(model);

		try {
			myNewProject.initialize();
		} catch (SLogoException e) {
			throw new SLogoException("project did not initialize");
		}
		getMyProjects().add(myNewProject);
	}

	//////////////////////////
	// getters and setters //
	//////////////////////////
	public LanguagesDriver getLanguagesDriver() {
		return myLangDriver;
	}

	public CommandHistoryViewer getMyHistory() {
		return myHistory;
	}

	public void setMyHistory(CommandHistoryViewer myHistory) {
		this.myHistory = myHistory;
	}

//	public WorkspaceView getMyCurrentProject() {
//		return myCurrentProject;
//	}
//
//	public void setMyCurrentProject(WorkspaceView myCurrentProject) {
//		this.myCurrentProject = myCurrentProject;
//	}
//
//	public List<WorkspaceView> getMyProjects() {
//		return myProjects;
//	}
//
//	public void setMyProject(List<WorkspaceView> myProject) {
//		this.myProjects = myProject;
//	}

	/**
	 * @return the myModel
	 */
	public Model getModel() {
		return myModel;
	}

	/**
	 * @param myModel
	 *            the myModel to set
	 */
	public void setModel(Model myModel) {
		this.myModel = myModel;
	}

	@Override
	/**
	 * @return the myCommand
	 */
	public String getCommand() {
		return myCommand;
	}

	/**
	 * @param myCommand
	 *            the myCommand to set
	 */
	public void setCommand(String myCommand) {
		this.myCommand = myCommand;
	}

	@Override
	public Visualizer getVisualizer() {
		
		return myVisualizer;
	}

	@Override
	public void updateDisplayData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCommandHistory() {
		// TODO Auto-generated method stub
		
	}

	/*
	 * View: for unit testing purposes
	 * 
	 * 
	 * public static void main(String[] args) { MainView myView = new
	 * MainView(); myView.addProject(); myView.getMyProject().get(0).show();
	 * 
	 * }
	 */

}