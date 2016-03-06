package deprecated_to_be_deleted;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import commandnode.Node;
import exception.SLogoException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import model.Model;
import model.SLogoDisplayData;
import view.SLogoGUIController;
import view.SLogoPromptBuilder;
@Deprecated
public class WorkspaceView {
	// private List<Character> myCharacters;
	// private Canvas myCanvas;
	//private List<Node> myCommandHistory;
	// private List<>
	//private List<DisplayData> myDisplayData;

	private FXMLLoader myLoader;
	private SLogoGUIController myGUIController;
	private Parent root;

	// Visualization Primitives
	// private Group myRoot;
	private Scene myProjectScene;
	private Stage myProjectStage;

	private Model myModel;

	public WorkspaceView() {

	}

	public WorkspaceView(Model model) {
		myModel = model;
	}

	public void initialize() throws SLogoException, IOException {

		SLogoPromptBuilder myPrompt = new SLogoPromptBuilder();
		myPrompt.promptScreen();

		// GUI Initialization
		myLoader = new FXMLLoader(getClass().getResource("UI.fxml"));
		root = (Parent) myLoader.load();
		myGUIController = (SLogoGUIController) myLoader.getController();
		myGUIController.setModel(myModel);

		myProjectScene = new Scene(root);
		myProjectStage = new Stage();
		myProjectStage.setScene(myProjectScene);
		myProjectStage.setTitle("SLogo");

	}

	public void updateStates() {

	}

	public void show() throws SLogoException {
		getStage().show();
	}

	public void hide() {

	}

	public void setBackgroundColor() {

	}

	//////////////////////////
	// getters and setters //
	//////////////////////////
//	 public Collection<Character> getMyCharacters() {
//	 return myCharacters;
//	 }
//	
//	
//	 public void setMyCharacters(List<Character> myCharacters) {
//	 this.myCharacters = myCharacters;
//	 }
//	
//	
//	 public Canvas getMyCanvas () {
//	 return myCanvas;
//	 }
//	
//	
//	 public void setMyCanvas(Canvas myCanvas) {
//	 this.myCanvas = myCanvas;
//	 }

//	public Collection<Node> getMyCommandHistory() {
//		return myCommandHistory;
//	}
//
//	public void setMyCommandHistory(List<Node> myCommandHistory) {
//		this.myCommandHistory = myCommandHistory;
//	}

	public Scene getScene() {
		return myProjectScene;
	}

	public void setMyProjectScene(Scene myProjectScene) {
		this.myProjectScene = myProjectScene;
	}

	public Stage getStage() {
		return myProjectStage;
	}

	public void setMyProjectStage(Stage myProjectStage) {
		this.myProjectStage = myProjectStage;
	}

	/**
	 * @return the myLoader
	 */
	public FXMLLoader getLoader() {
		return myLoader;
	}

	/**
	 * @param myLoader
	 *            the myLoader to set
	 */
	public void setLoader(FXMLLoader myLoader) {
		this.myLoader = myLoader;
	}

	/**
	 * @return the myGUIController
	 */
	public SLogoGUIController getGUIController() {
		return myGUIController;
	}

	/**
	 * @param myGUIController
	 *            the myGUIController to set
	 */
	public void setGUIController(SLogoGUIController myGUIController) {
		this.myGUIController = myGUIController;
	}

//	/**
//	 * @return the myDisplayData
//	 */
////	public List<DisplayData> getMyDisplayData() {
////		return myDisplayData;
////	}
//
//	/**
//	 * @param myDisplayData
//	 *            the myDisplayData to set
//	 */
//	public void setMyDisplayData(List<DisplayData> myDisplayData) {
//		this.myDisplayData = myDisplayData;
//	}

	/**
	 * @return the myModel
	 */
	public Model getMyModel() {
		return myModel;
	}

	/**
	 * @param myModel
	 *            the myModel to set
	 */
	public void setMyModel(Model myModel) {
		this.myModel = myModel;
	}

}