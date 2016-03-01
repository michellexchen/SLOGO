package Controller;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

import Model.MainModel;
import View.GUIController;
import View.MainView;

public class MainDriver extends Application {

	private MainModel myModel;
	private MainView myView;
	private GUIController myGUI;
	private String myLanguage;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage myStage) throws SLogoException, IOException {

		myModel = new MainModel();
		myView = new MainView();
		myModel.setView(myView);
		myModel.initialize();
		LanguagesDriver langDriver = new LanguagesDriver();
		this.myLanguage = myLanguage;
		langDriver.load(myLanguage);

		try {
			myView.addProject(myModel);
			myView.getMyProjects().get(0).setMyModel(myModel);
			myView.getMyProjects().get(0).show();
		} catch (SLogoException e) {
			myView.showError(e);
		}
	}

}