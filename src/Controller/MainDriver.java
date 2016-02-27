package Controller;

import Model.CommandTree;
import Model.Turtle;
import View.MainView;
import View.SLogoException;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;

public class MainDriver extends Application {

	private Group myRoot;
	private final int VIEW_WIDTH = 1000;
	private final int VIEW_HEIGHT = 750;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage myStage) throws Exception {
		//		myStage.setTitle("SLogo Integrated Development Environment");
		//		this.myStage = myStage;
		//		myRoot = new Group();
		//		myScene = new Scene(myRoot, VIEW_WIDTH, VIEW_HEIGHT);
		//		myStage.setScene(myScene);
		MainView myView = new MainView();
		try {
			myView.addProject();
			myView.getMyProjects().get(0).show();
		} catch (SLogoException e) {
			myView.showError(e);
		}
		//createBackend();
	}

	private void createBackend() {
		Turtle ogTurt = new Turtle("OG", VIEW_WIDTH / 2, VIEW_HEIGHT / 2, true, 0, false);
		myRoot.getChildren().add(ogTurt.getTurtle());
		simulateGettingCommand("forward 50");
	}

	private void simulateGettingCommand(String command){
		TextParser parser = new TextParser();
		parser.parse(command);
		CommandTree myTree = parser.getTree();
		myTree.traverse();
	}
}