package Controller;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

import Model.MainModel;
import Model.Model;
import View.GUIController;
import View.MainView;
import View.View;

public class MainDriver extends Application {

	private MainModel myModel;
	private MainView myView;
	private GUIController myGUI;
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage myStage) throws SLogoException, IOException {
		
		
		myModel = new MainModel();
		myView = new MainView();
		myModel.setView(myView);
		
		try {
			myView.addProject();
			myView.getMyProjects().get(0).show();
		} catch (SLogoException e) {
			myView.showError(e);
		}
		myModel.createBackend();
	}

}