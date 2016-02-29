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

	private LanguagesDriver myLangDriver;
	//	private Group myRoot;
	//	private Scene myScene;
	//	private Stage myStage;
	private CommandHistoryViewer myHistory;
	private Project myCurrentProject;
	private List<Project> myProjects;
	
	private String myCommand;
	private Model myModel;

	public MainView() {
		myHistory = new CommandHistoryViewer();
		myProjects = new ArrayList<Project>();
		myLangDriver = new LanguagesDriver();
		String language = "English"; // Get from UI
		myLangDriver.load(language);
	}
	
	public MainView(Model model) {
		myHistory = new CommandHistoryViewer();
		myProjects = new ArrayList<Project>();
		myLangDriver = new LanguagesDriver();
		String language = "English"; // Get from UI
		myLangDriver.load(language);
		
		myModel = model;
	}

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

	public void addProject() throws IOException{
		Project myNewProject = new Project();

		try {
			myNewProject.initialize();
		} catch (SLogoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();//Remove
		}
		getMyProjects().add(myNewProject);
	}


	//////////////////////////
	// getters and setters  //
	//////////////////////////
	public LanguagesDriver getLanguagesDriver() {
		return myLangDriver;
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

	/**
	 * @return the myModel
	 */
	public Model getModel() {
		return myModel;
	}

	/**
	 * @param myModel the myModel to set
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
	 * @param myCommand the myCommand to set
	 */
	public void setCommand(String myCommand) {
		this.myCommand = myCommand;
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