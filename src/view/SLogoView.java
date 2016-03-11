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
import model.SLogoWorkspace;

/**
 * 
 * View class that contains GUI front-end components
 *
 */
public class SLogoView implements View {

	private final int WIDTH = 440;
	private final int HEIGHT = 440;

	private String myCommand;
	private Model myModel;
	private List<SLogoVisualizer> myVisualizers;
	private SLogoVisualizer myCurrentVisualizer;
	
	//List that keeps track of MenuItems that represent different workspaces
	//in MenuButton
	//private List<MenuItem> myMenuItems;
	private ObservableList<MenuItem> myObservableMenuItems;
	private ObservableList<String> myObservableEntries;

	public SLogoView() throws SLogoException {
		myCurrentVisualizer = new SLogoVisualizer(getModel(), WIDTH, HEIGHT);
	}

	public SLogoView(Model model) throws SLogoException {
		myModel = model;
		myVisualizers = new ArrayList<SLogoVisualizer>();
		myCurrentVisualizer = new SLogoVisualizer(getModel(), WIDTH, HEIGHT);
		
		//Initialize the list of MenuItems (workspaces)
		//No longer needed?
		myObservableMenuItems = FXCollections
							.observableArrayList(new ArrayList<MenuItem>());
		//Using combobox
		myObservableEntries = FXCollections
				.observableArrayList(new ArrayList<String>());
		
	}
	
	/**
	 * Called at start
	 * Initializes necessary classes used to visualize turtles
	 * @throws IOException 
	 */
	public void initialize() throws SLogoException, IOException {
		myCurrentVisualizer.initialize();
		myVisualizers.add(myCurrentVisualizer);
		
		
		
		//Add listener for Combobox 
		myObservableEntries.addListener((ListChangeListener) change -> {
		    //for all visualizers, make GUIController add MenuItems to Menubutton
			for (SLogoVisualizer visualizer : myVisualizers) {
				visualizer.updateComboBox(myObservableEntries);
			}
//			
//			System.out.println("CALLED");
		});
		
		
		//Add listener 
		myObservableMenuItems.addListener((ListChangeListener) change -> {
		    //for all visualizers, make GUIController add MenuItems to Menubutton
			for (SLogoVisualizer visualizer : myVisualizers) {
				visualizer.updateMenuButton(myObservableMenuItems);
			}
//			
//			System.out.println("CALLED");
		});
	}
	
//	@Override
//	public void createComboBoxEntry () {
//		//int = getView().getMenuItems();
//		
////		String newWorkspace = "Workspace " + getObservableEntries().size();
//		String newWorkspace = String.valueOf(getObservableEntries().size());
//		getObservableEntries().add(newWorkspace);
//	
//	}
	
//    @Override
//	public void createMenuItem () {
//		//int  = getView().getMenuItem().size();
//		
//		//Name the menuitem
//		MenuItem myMenuItem = new MenuItem("Workspace " + 
//										getMenuItems().size());
//		myMenuItem.setId("Workspace " + 
//										getMenuItems().size());
//		System.out.println("MenuItem creation check: " + myMenuItem.getId());
//		//Add it to List<MenuItem>
//		getMenuItems().add(myMenuItem);
//		
//		System.out.println(getMenuItems().size());
//		
//	}
//	
	
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

//	@Override
//	public List<MenuItem> getMenuItemList() {
//		// TODO Auto-generated method stub
//		return getMenuItems();
//	}

	public ObservableList<MenuItem> getMenuItems() {
		return myObservableMenuItems;
	}

	public void setMenuItems(ObservableList<MenuItem> myMenuItems) {
		this.myObservableMenuItems = myMenuItems;
	}

	/**
	 * @return the myObservableEntries
	 */
	public ObservableList<String> getObservableEntries() {
		return myObservableEntries;
	}

	/**
	 * @param myObservableEntries the myObservableEntries to set
	 */
	public void setObservableEntries(ObservableList<String> myObservableEntries) {
		this.myObservableEntries = myObservableEntries;
	}
}