package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import exception.SLogoException;
import javafx.collections.ObservableList;
import model.Model;
import model.SLogoWorkspace;

/**
 * 
 * View class that contains GUI front-end components
 *
 */
public class SLogoView implements View {

	private static final int WIDTH = 440;
	private static final int HEIGHT = 440;

	private Model myModel;
	private List<SLogoVisualizer> myVisualizers;
	private SLogoVisualizer myCurrentVisualizer;
	
	public SLogoView() throws SLogoException {
		myCurrentVisualizer = new SLogoVisualizer(getModel());
	}

	public SLogoView(Model model) throws SLogoException {
		myVisualizers = new ArrayList<SLogoVisualizer>();
		myModel = model;
		myCurrentVisualizer = new SLogoVisualizer(getModel());
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
		SLogoVisualizer myNewVisualizer = new SLogoVisualizer(getModel());
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
	public SLogoVisualizer getCurrentVisualizer() {
		return myCurrentVisualizer;
	}

	public void updateDisplayData() {
		getCurrentVisualizer().updateDisplayData();
	}

	public void updateCommandHistory() {
		getCurrentVisualizer().updateCommandHistory();
	}

	public void updateWorkspaces() {
	}
	
	public void updateVariable(ObservableList variables) {
		getCurrentVisualizer().updateVariables(variables);
	}

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

	public void setCurrentWorkspace(SLogoWorkspace workspace) {
	}

	public String getLanguage() {
		return myCurrentVisualizer.getLanguage();
	}

}