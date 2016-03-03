package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import model.MainModel;
import view.MainView;
import view.SLogoPromptBuilder;

import java.io.IOException;

import exception.SLogoException;

/**
 * Controls the initial setup of our GUI and back end
 * 
 * @author Mario Oliver
 *
 */

public class Main extends Application {

	private MainModel myModel;
	private MainView myView;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage myStage) throws SLogoException, IOException {
		initialize();
	}

	public void initialize() throws SLogoException, IOException {
		myModel = new MainModel();
		myView = new MainView(myModel);
		myModel.setView(myView);
		myModel.initialize();
		myView.initialize();
		myModel.addListeners();
	}

}