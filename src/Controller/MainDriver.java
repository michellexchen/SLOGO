package Controller;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

import Exception.SLogoException;
import Model.MainModel;
import View.MainView;
import View.SLogoPromptBuilder;

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
		loadLanguage();
		myModel = new MainModel();
		myView = new MainView(myModel);
		myModel.setView(myView);
		myModel.initialize();
		myView.initialize();
		myModel.addListeners();
	}
	
	public void loadLanguage () throws SLogoException {
		SLogoPromptBuilder prompt = new SLogoPromptBuilder();
		LanguagesDriver langDriver = new LanguagesDriver();
		// doesn't do anything
		// this.myLanguage = myLanguage;
		langDriver.load("English");//prompt.sendMyLanguage());
	}
}