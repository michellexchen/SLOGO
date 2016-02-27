package Controller;

import javafx.application.Application;
import javafx.stage.Stage;
import Model.MainModel;
import View.MainView;
import View.SLogoException;

public class MainDriver extends Application {


	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage myStage) throws SLogoException {
		MainView myView = new MainView();
		try {
			myView.addProject();
			myView.getMyProjects().get(0).show();
		} catch (SLogoException e) {
			myView.showError(e);
		}
		MainModel myModel = new MainModel();
		myModel.createBackend();
	}


}