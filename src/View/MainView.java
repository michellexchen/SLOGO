package View;

import java.io.IOException;
import java.util.Observer;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import Exception.SLogoException;
import Model.Model;
import Model.Workspace;

public class MainView implements View {

	private final int WIDTH = 331;
	private final int HEIGHT = 331;

	private String myCommand;
	private Model myModel;
	private Visualizer myVisualizer;

	public MainView() throws SLogoException {
		myVisualizer = new Visualizer(getModel(), WIDTH, HEIGHT);
	}

	public MainView(Model model) throws SLogoException {
		myModel = model;
		myVisualizer = new Visualizer(getModel(), WIDTH, HEIGHT);
	}
	
	/**
	 * Called at start
	 * Initializes necessary classes used to visualize turtles
	 * @throws IOException 
	 */
	public void initialize() throws SLogoException, IOException {
		myVisualizer.initialize();
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
	public Visualizer getVisualizer() {
		
		return myVisualizer;
	}

	@Override
	public void updateDisplayData() {
		getVisualizer().updateDisplayData();
	}

	@Override
	public void updateCommandHistory() {
		getVisualizer().updateCommandHistory();
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
	public void setCurrentWorkspace(Workspace workspace) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Observer getObserver() {
		// TODO Auto-generated method stub
		return getVisualizer();
	}

	@Override
	public String getLanguage() {
		// TODO Auto-generated method stub
		return myVisualizer.getLanguage();
	}


}