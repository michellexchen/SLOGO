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

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage myStage) throws SLogoException, IOException {
		initialize();

	}
	
	public void initialize () throws SLogoException, IOException {
		myModel = new MainModel();
		myView = new MainView();
		myModel.setView(myView);
		myView.setModel(myModel);
		myModel.initialize();
		myView.initialize();
		loadLanguage();
	}
	
	public void loadLanguage () throws SLogoException {
		LanguagesDriver langDriver = new LanguagesDriver();
		// doesn't do anything
		// this.myLanguage = myLanguage;
		langDriver.load("English");
	}
}