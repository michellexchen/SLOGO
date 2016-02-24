package Controller;

import Model.Turtle;
import View.MainView;
import View.SLogoException;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Adam Tache
 *
 */

public class MainDriver extends Application {

	private LanguagesDriver languagesDriver;
	private Group myRoot;
	private Scene myScene;
	private Stage myStage;
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
		languagesDriver = new LanguagesDriver();
		MainView myView = new MainView(languagesDriver);
		try {
		myView.addProject();
		myView.getMyProject().get(0).show();
		} catch (SLogoException e) {
			myView.showError(e);
		}
		createBackend();
	}

	private void createBackend() {
		Turtle ogTurt = new Turtle("OG", VIEW_WIDTH / 2, VIEW_HEIGHT / 2, true, 0, false);
		myRoot.getChildren().add(ogTurt.getTurtle());
		simulateGettingCommand("forward 50");
	}
	
	private void simulateGettingCommand(String command){
		TextParser tp_forw = new TextParser(command);
	}
}