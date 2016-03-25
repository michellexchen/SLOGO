// This entire file is part of my masterpiece.
// Mario Oliver mao26

package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import controller.Controller;
import exception.SLogoException;
import javafx.collections.ObservableList;
import model.Model;

/**
 * View class that contains GUI front-end components
 * Implements View interface
 *
 * @author Hunter
 */
public class SLogoView implements View<Object> {

    private List<SLogoVisualizer> myVisualizers;
    private SLogoVisualizer myCurrentVisualizer;
    private Controller controller;

    /**
     * Default constructor that receives a Model Interface
     * to ensure separation of Model and View (by the use of
     * interfaces, not references to actual Model and View
     * objects)
     * 
     * @param model
     * @throws SLogoException
     */
    public SLogoView() throws SLogoException {
        myVisualizers = new ArrayList<>();
    }
    
    public void setControllerAndVisualizer(Controller controller){
    	this.controller = controller;
    	myCurrentVisualizer = controller.getCurrentVisualizer();
    }

    /**
     * Called at start
     * Initializes necessary classes used to visualize turtles
     * @throws IOException 
     * 
     */
    public void initialize() throws SLogoException, IOException {
        myVisualizers.add(myCurrentVisualizer);
    }

    /**
     * Adds a new Visualizer that contains and instantiates
     * a new GUIController to enable multiple workspaces
     * 
     */
    @Override
    public void addVisualizer() throws SLogoException, IOException {
        SLogoVisualizer myNewVisualizer = new SLogoVisualizer(controller);
        getVisualizers().add(myNewVisualizer);
        setCurrentVisualizer(myNewVisualizer);
    }

    /**
     * Method that switches GUI to the Visualizer with the given index
     * Used in conjunction with switchWorkspace
     * 
     */
    @Override
    public void switchVisualizer (int index) {
        getCurrentVisualizer().hide();
        setCurrentVisualizer(getVisualizers().get(index));
        getCurrentVisualizer().show();
    }

    /**
     * Retrieves the current visualizer that is showing
     * 
     */
    @Override
    public SLogoVisualizer getCurrentVisualizer() {
        return myCurrentVisualizer;
    }
    
    /**
     * Applies the updated display information gathered from
     * newly updated DisplayData objects
     * 
     */
    @Override
    public void updateDisplayData () {
        getCurrentVisualizer().updateDisplayData();
    }
    
    /**
     * Notifies Visualizer to update custom commands
     * 
     */
    public void updateCustoms(ObservableList customs) {
        getCurrentVisualizer().updateCustomCommands(customs);
    }
    
    /**
     * Notifies Visualizer to update customs (variables and custom commands)
     * 
     */
    public void updateVariables(ObservableList variables) {
        getCurrentVisualizer().updateVariables(variables);
    }

    /**
     * Retrieves an Observer for Model to use to assign to its Observables
     * 
     */
    public Observer getObserver() {
        return getCurrentVisualizer();
    }
    
    /**
     * @return the myVisualizers
     */
    public List<SLogoVisualizer> getVisualizers() {
        return myVisualizers;
    }

    /**
     * @param myVisualizers the myVisualizers to set
     */
    public void setVisualizers(List<SLogoVisualizer> myVisualizers) {
        this.myVisualizers = myVisualizers;
    }

    /**
     * @param myCurrentVisualizer the myCurrentVisualizer to set
     */
    public void setCurrentVisualizer(SLogoVisualizer myCurrentVisualizer) {
        this.myCurrentVisualizer = myCurrentVisualizer;
    }

    /**
     * Returns current language
     * 
     */
    public String getLanguage() {
        return myCurrentVisualizer.getLanguage();
    }

}