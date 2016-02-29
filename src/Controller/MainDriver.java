package Controller;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

import Model.MainModel;
import View.MainView;

public class MainDriver extends Application {

	private MainModel myModel;
	private MainView myView;
	
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage myStage) throws SLogoException, IOException {
		
		
		myModel = new MainModel();
		myView = new MainView();
		
		
		try {
			myView.addProject();
			myView.getMyProjects().get(0).show();
		} catch (SLogoException e) {
			myView.showError(e);
		}
		myModel.createBackend();
	}

}