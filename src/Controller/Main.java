package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import model.SLogoModel;
import view.SLogoView;

import java.io.IOException;

import exception.SLogoException;

/**
 * Controls the initial setup of our GUI and back end
 * 
 * @author Mario Oliver
 *
 */

public class Main extends Application {

	private SLogoModel myModel;
	private SLogoView myView;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage myStage) throws SLogoException, IOException {
		initialize();
	}

	public void initialize() throws SLogoException, IOException {
		myModel = new SLogoModel();
		myView = new SLogoView(myModel);
		myModel.setView(myView);
		myModel.initialize();
		myView.initialize();
		myModel.addListeners();
	}

}