package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import exception.SLogoException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Model;
import model.SLogoWorkspace;

public class SLogoView implements View {

	private final int WIDTH = 440;
	private final int HEIGHT = 440;

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

	
	
	
	public void showError(SLogoException e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("Error Occurred");
		alert.setContentText("Ooops, there was an error!");

		alert.showAndWait();
		// Or restart the simulation
	}

	//////////////////////////
	// getters and setters //
	//////////////////////////

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
	public void updateVariables() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCurrentWorkspace(SLogoWorkspace workspace) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Observer getObserver() {
		// TODO Auto-generated method stub
		return getCurrentVisualizer();
	}

	@Override
	public String getLanguage() {
		return myCurrentVisualizer.getLanguage();
	}
	
	@Override
	public void switchVisualizer (int index) {
		getCurrentVisualizer().hide();
		setCurrentVisualizer(getVisualizers().get(index));
		getCurrentVisualizer().show();
	}

	@Override
	public void addVisualizer() throws SLogoException, IOException {
		SLogoVisualizer myNewVisualizer = new SLogoVisualizer(getModel(), WIDTH, HEIGHT);
		getVisualizers().add(myNewVisualizer);
		
		myNewVisualizer.initialize();
		setCurrentVisualizer(myNewVisualizer);
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