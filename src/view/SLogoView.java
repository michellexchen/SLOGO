package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import exception.SLogoException;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import model.Model;
import model.SLogoVariable;
import model.SLogoWorkspace;

/**
 * 
 * View class that contains GUI front-end components
 *
 */
public class SLogoView implements View {

	private static final int WIDTH = 440;
	private static final int HEIGHT = 440;

	private String myCommand;
	private Model myModel;
	private List<SLogoVisualizer> myVisualizers;
	private SLogoVisualizer myCurrentVisualizer;
	
	public SLogoView() throws SLogoException {
		myCurrentVisualizer = new SLogoVisualizer(getModel(), WIDTH, HEIGHT);
	}

	public SLogoView(Model model) throws SLogoException {
		myModel = model;
		myVisualizers = new ArrayList<SLogoVisualizer>();
		myCurrentVisualizer = new SLogoVisualizer(getModel(), WIDTH, HEIGHT);
	}
	
	/**
	 * Called at start
	 * Initializes necessary classes used to visualize turtles
	 * @throws IOException 
	 */
	public void initialize() throws SLogoException, IOException {
		myCurrentVisualizer.initialize();
		myVisualizers.add(myCurrentVisualizer);
	}
	

	/**
	 * Adds a new Visualizer that contains and instantiates
	 * a new GUIController to enable multiple workspaces
	 */
	@Override
	public void addVisualizer() throws SLogoException, IOException {
		SLogoVisualizer myNewVisualizer = new SLogoVisualizer(getModel(), WIDTH, HEIGHT);
		getVisualizers().add(myNewVisualizer);

		myNewVisualizer.initialize();
		setCurrentVisualizer(myNewVisualizer);
	}

	/**
	 * 
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
	 * @return the myModel
	 */
	public Model getModel() {
		return myModel;
	}

	/**
	 * @param myModel
	 *            the myModel to set
	 */
	public void setModel(Model myModel) {
		this.myModel = myModel;
	}

	@Override
	/**
	 * @return the myCommand
	 */
	public String getCommand() {
		return myCommand;
	}

	/**
	 * @param myCommand
	 *            the myCommand to set
	 */
	public void setCommand(String myCommand) {
		this.myCommand = myCommand;
	}

	@Override
	public SLogoVisualizer getCurrentVisualizer() {
		
		return myCurrentVisualizer;
	}

	@Override
	public void updateDisplayData() {
		getCurrentVisualizer().updateDisplayData();
	}

	@Override
	public void updateCommandHistory() {
		getCurrentVisualizer().updateCommandHistory();
	}

	@Override
	public void updateWorkspaces() {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void updateVariable(ObservableList variables) {
		// TODO Auto-generated method stub {
		getCurrentVisualizer().updateVariables(variables);
		System.out.println("Happening");
	}
	
	
	@Override
	public void setCurrentWorkspace(SLogoWorkspace workspace) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Observer getObserver() {
		return getCurrentVisualizer();
	}

	@Override
	public String getLanguage() {
		return myCurrentVisualizer.getLanguage();
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


}