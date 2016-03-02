package Controller;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

import Model.MainModel;
import View.GUIController;
import View.MainView;

/**
 * Controls the initial setup of our GUI and back end
 * 
 * @author Mario Oliver
 *
 */

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
		myView.setModel(myModel);
		myModel.initialize();
		myView.initialize();

		LanguagesDriver langDriver = new LanguagesDriver();
		// doesn't do anything
		// this.myLanguage = myLanguage;
		langDriver.load("English");

		
		
		
//		try {
//			myView.addProject(myModel);
//			myView.getMyProjects().get(0).setMyModel(myModel);
//			myView.getMyProjects().get(0).show();
//		} catch (SLogoException e) {
//			myView.showError(e);
//		}
	}

}