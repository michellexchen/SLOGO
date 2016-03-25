// This entire file is part of my masterpiece.
// Mario Oliver mao26

package controller;

import java.util.List;

import commandnode.Node;
import exception.SLogoException;
import model.*;
import model.ResourceLoader;
import model.SLogoDisplayData;
import model.SLogoModel;
import parser.RootEvaluator;
import parser.SLogoParser;
import view.SLogoView;
import view.SLogoVisualizer;
import view.*;

public class Controller {

	private SLogoModel myModel;
	private SLogoView myView;
	private SLogoVisualizer myCurrentVisualizer;

	public Controller(SLogoModel model, SLogoView view) throws SLogoException {
		this.myModel = model;
		this.myView = view;
		this.myCurrentVisualizer = new SLogoVisualizer(this);
	}

	public SLogoVisualizer getCurrentVisualizer() {
		return myCurrentVisualizer;
	}

	/**
	 * 
	 * Instead of the model knowing of the views existence and visa-versa, the controller is the intermediary between the two
	 * 
	 */
	public SLogoModel getModel() {
		return myModel;
	}

	public SLogoView getView() {
		return myView;
	}

	/**
	 * 
	 * This is an implementation of the controllers ability to do something for the front end that used to require that the view have access
	 * to the model, but now the controller implements it without the view knowing that the model exists
	 * @param visualizer
	 */
	public void updateDisplayData(SLogoVisualizer visualizer) {
		visualizer.getGUIController().getCanvas().getChildren().clear();
		for (SLogoDisplayData turtledata : myModel.getObservableDataList()) {
			visualizer.placeTurtle(turtledata);
			if (turtledata.getID() != Integer.parseInt(new ResourceLoader().getString("StampID"))) {
				turtledata.addLine(visualizer.createLine(turtledata));
				visualizer.getGUIController().addToCanvas(turtledata.getLines());
			}
			visualizer.getGUIController().updateProperties(turtledata);
			visualizer.getPropertiesData().setPaneColor(turtledata.getBGColor());
		}
	}

	/**
	 * This is an extension of how much we can do with the controller as a concrete channel for the flow of information 
	 * between the view and the model
	 * @param command
	 * @throws SLogoException
	 */
	public void readCommand(String command) throws SLogoException {
		SLogoParser parser = new SLogoParser(myModel.getCurrentWorkspace());
		List<Node> myCommandRoots = parser.readCommand(command);
		myModel.getCurrentWorkspace().getRootEvaluator().evaluateRoots(myCommandRoots);
	}

}
