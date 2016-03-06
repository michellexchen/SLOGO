package Controller;

import javafx.application.Application;
import javafx.stage.Stage;
import Model.*;
import View.*;
import Exception.*;
import Controller.*;
import deprecated_to_be_deleted.*;
import CommandNode.*;

import java.io.IOException;


/**
 * Controls the initial setup of our GUI and back end
 * 
 * @author Mario Oliver
 *
 */

public class Main extends Application {

	private SLogoModel myModel;
	private SLogoView myView;

	

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