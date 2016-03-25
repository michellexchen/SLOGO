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
	
	public Controller(SLogoModel model, SLogoView view) throws SLogoException{
		this.myModel = model;
		this.myView = view;
		this.myCurrentVisualizer = new SLogoVisualizer(this);
	}
	
	public SLogoVisualizer getCurrentVisualizer(){
		return myCurrentVisualizer;
	}
	
	public SLogoModel getModel(){
		return myModel;
	}
	
	public SLogoView getView(){
		return myView;
	}
	
	public void updateDisplayData(SLogoVisualizer visualizer){
		visualizer.getGUIController().getCanvas().getChildren().clear();
		for (SLogoDisplayData turtledata : myModel.getObservableDataList()) {
			visualizer.placeTurtle(turtledata);
			if(turtledata.getID() != Integer.parseInt(new ResourceLoader().getString("StampID"))){
				turtledata.addLine(visualizer.createLine(turtledata));
				visualizer.getGUIController().addToCanvas(turtledata.getLines());
			}
			visualizer.getGUIController().updateProperties(turtledata);
			visualizer.getPropertiesData().setPaneColor(turtledata.getBGColor());
		}
	}
	
	public void readCommand(String command) throws SLogoException {
        SLogoParser parser = new SLogoParser(myModel.getCurrentWorkspace());
        List<Node> myCommandRoots = parser.readCommand(command);
        myModel.getCurrentWorkspace().getRootEvaluator().evaluateRoots(myCommandRoots);
	}
	
	

}
