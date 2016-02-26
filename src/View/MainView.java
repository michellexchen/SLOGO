package View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Controller.LanguagesDriver;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


public class MainView {

	private LanguagesDriver myLanguagesDriver;
//	private Group myRoot;
//	private Scene myScene;
//	private Stage myStage;
	private CommandHistoryViewer myHistory;
	private Project myCurrentProject;
	private List<Project> myProjects;
	
	public MainView () {
		myHistory = new CommandHistoryViewer();
		myProjects = new ArrayList<Project>();
	}
	
	public MainView(LanguagesDriver languagesDriver) {
//		this.myStage = myStage;
//		this.myRoot = myRoot;
//		this.myScene = myScene;
		myLanguagesDriver = languagesDriver;
		myHistory = new CommandHistoryViewer();
		myProjects = new ArrayList<Project>();

	}
	
//	@Override
//	public void start(Stage arg0) throws Exception {
//		// TODO Auto-generated method stub
//		
//	}
	


	public void showProject(Project project) throws SLogoException {
		project.show();
		
	}
	
	public void showError(SLogoException e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("Error Occurred");
		alert.setContentText("Ooops, there was an error!");

		alert.showAndWait();
		//Or restart the simulation
	}
	
	/*
	 * clear() wipes out all the projects we have and restarts
	 */
	public void clear() {
		getMyProjects().clear();
		//TODO: Code for restasrting
	}
	
	public void addProject() throws SLogoException{
		Project myNewProject = new Project();

		try {
			myNewProject.initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getMyProjects().add(myNewProject);
	}
	
	
	//////////////////////////
	// getters and setters  //
	//////////////////////////
	public LanguagesDriver getLanguagesDriver() {
		return myLanguagesDriver;
	}


	public void setLanguagesDriver(LanguagesDriver languagesDriver) {
		myLanguagesDriver = languagesDriver;
	}


//	public Group getMyRoot() {
//		return myRoot;
//	}
//
//
//	public void setMyRoot(Group myRoot) {
//		this.myRoot = myRoot;
//	}
//
//
//	public Scene getMyScene() {
//		return myScene;
//	}
//
//
//	public void setMyScene(Scene myScene) {
//		this.myScene = myScene;
//	}
//
//
//	public Stage getMyStage() {
//		return myStage;
//	}
//
//
//	public void setMyStage(Stage myStage) {
//		this.myStage = myStage;
//	}


	public CommandHistoryViewer getMyHistory() {
		return myHistory;
	}


	public void setMyHistory(CommandHistoryViewer myHistory) {
		this.myHistory = myHistory;
	}


	public Project getMyCurrentProject() {
		return myCurrentProject;
	}


	public void setMyCurrentProject(Project myCurrentProject) {
		this.myCurrentProject = myCurrentProject;
	}


	public List<Project> getMyProjects() {
		return myProjects;
	}


	public void setMyProject(List<Project> myProject) {
		this.myProjects = myProject;
	}
	
	
	
	
	
	/*
	 * View: for unit testing purposes
	 */
//	
//	public static void main(String[] args) {
//		MainView myView = new MainView();
//		myView.addProject();
//		myView.getMyProject().get(0).show();
//		
//	}


	
}

