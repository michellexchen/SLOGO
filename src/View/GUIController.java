package View;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Exception.SLogoException;
import Model.Model;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


public class GUIController implements Initializable  {
	
	private static final String HELP_URL = "http://www.cs.duke.edu/courses/"
						+ "compsci308/spring16/assign/03_slogo/commands.php";
	private static final int POPUP_WIDTH = 900;
	private static final int POPUP_HEIGHT = 550;

	private WebView	myBrowser;
	private WebEngine myWebEngine;
	private ListView<String> myHistoryPaneView;
	
	private Model myModel;
	private View myView;
	private String myCommand;
	
	//Help button
    @FXML
    private Button myHelpButton;
	
    //Textfield
    @FXML
    private TextField myTextField;
    
    //Run button
	@FXML
	private Button myRunButton;
    
    //Main Pane
    @FXML
    private Pane myCanvas;
    
    
    //Drop-down menuButton - Choose Project
    @FXML
    private MenuButton myMenu;
   

    
    //MenuButton's MenuItem list
    private List<MenuItem> myMenuItems;

    //Command History Pane where ObservableList<CommandNode> will go
    @FXML
    private ScrollPane myCommandHistoryPane;
 
    //myVariablePane where ObservableList<Variable> will go
    @FXML
    private ScrollPane myVariablePane;
    
    //Displays the properties of a turtle
    @FXML
    private ScrollPane myPropertyPane;
    
    //This adds workspace
    @FXML
    private Button myAddWorkspaceButton;
    
    //Customize button - WHAT IS THIS FOR? FOR CHOOSING COLOR?
    @FXML
    private Button myCustomizeButton;
    
    @FXML
    private List<String> myHistory;
    


    
    
    @Override 
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
    	
    	//TODO: Separate the operations below into different methods
    	
    	myHistory = new ArrayList<String>();
    	
    	//Help button - assign action
        myHelpButton.setOnAction(e -> {
        	popup(HELP_URL);
        });
                   
        //Run button - return user input and stores it in myCommand
        
        myRunButton.setOnAction(e -> {
        	myCommand = myTextField.getText();
            myTextField.clear();
            run(myCommand);
        });  
        
        
        
        //AddWorkspace Button. Causes Model to create a new workspace
        myAddWorkspaceButton.setOnAction(e -> {
        	
        	//Hide current stage
        	Stage currentStage = (Stage)myAddWorkspaceButton.getScene().getWindow();
        	currentStage.hide();
        	
        	//Get Model to create a new workspace and switch into it
        	try {
				getModel().AddWorkspace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace(); 
				//GET RID OF THIS
			}
        });
    }
        
    private void run(String command){
        setCommand(myCommand);
          	/*
          	 * TODO: Call Model's readCommand that calls
          	 * View's getCommand
          	 * and passes the command to the parser
          	 */
          	try {
  				getModel().readCommand(command);
  			} catch (SLogoException e1) {
  				command = "ERROR: " + command;
  			}
          	
          	myHistory.add(command);
          	displayHistory();
    }
    
    private void popup(String link){
		myBrowser = new WebView();
        myWebEngine = myBrowser.getEngine();
        myWebEngine.load(link);
    	VBox vbox = new VBox();
        Scene scene = new Scene(vbox);
        Stage stage = new Stage();        
        vbox.getChildren().addAll(myBrowser);
        VBox.setVgrow(myBrowser, Priority.ALWAYS);
        stage.setTitle("SLOGO Basic Commands");
        stage.setWidth(POPUP_WIDTH);
        stage.setHeight(POPUP_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
    
    
//    private void myRunButtonmyTextField(){
//    	myRunButton.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//	    		sendmyTextField();
//	    		myHistoryPane.add(myTextField.getText());
//	    		displayHistoryPane();
//	    		myTextField.clear();
//			}
//    	});
//    }
//    
//    public String sendmyTextField(){ //to send to backend?
//    	return myTextField.getText();
//    }
//    
//    private void displayHistory(){
//    	myHistoryPaneView = new ListView<String>();
//    	ObservableList<String> items =FXCollections.observableArrayList (
//    			myHistoryPane);
//    	myHistoryPaneView.setItems(items);
//    	((ScrollPane) myHistoryPane).setContent(myHistoryPaneView);
//    }
    
    private void displayHistory(){
    	myHistoryPaneView = new ListView<String>();
    	ObservableList<String> items =FXCollections.observableArrayList (
    			myHistory);
    	myHistoryPaneView.setItems(items);
    	myCommandHistoryPane.setContent(myHistoryPaneView);
//    	myHistoryPaneView.setOnMouseClicked(e-> {
//    		System.out.println("*");
//    		System.out.println(e.toString());
//    		run(myCommand);
//    	});
    	
    	myHistoryPaneView.getSelectionModel().selectedItemProperty().addListener
    												(new ChangeListener<String>() {
    	    @Override
    	    public void changed(ObservableValue<? extends String> observable, 
    	    									String oldValue, String newValue) {
    	    	if(newValue.contains("ERROR")) {
    	    		//TODO: Do something about this magic number '7'
    	    		myCommand = newValue.substring(7);
    	    	} else {
    	    		myCommand = newValue;
    	    	}
    	        run(myCommand);
    	    }
    	});
    }    

	public void setCommand(String myCommand) {
		this.myCommand = myCommand;
	}


	public Pane getCanvas() {
		return myCanvas;
	}

	public void addToCanvas(Node list) {
		getCanvas().getChildren().add(list);
	}
	
	public void addToCanvas(List<Line> nodelist) {
		getCanvas().getChildren().addAll(nodelist);
	}


	public void setCanvas(Pane myCanvas) {
		this.myCanvas = myCanvas;
	}

	public MenuButton getMyMenu() {
		return myMenu;
	}

	public void setMyMenu(MenuButton myMenu) {
		this.myMenu = myMenu;
	}

	public List<MenuItem> getMyMenuItems() {
		return myMenuItems;
	}

	public void setMyMenuItems(List<MenuItem> myMenuItems) {
		this.myMenuItems = myMenuItems;
	}

	/**
	 * @return the myModel
	 */
	public Model getModel() {
		return myModel;
	}

	/**
	 * @param myModel the myModel to set
	 */
	public void setModel(Model myModel) {
		this.myModel = myModel;
	}
	
	/**
	 * @return the myView
	 */
	public View getView() {
		return myView;
	}

	/**
	 * @param myView the myView to set
	 */
	public void setView(View myView) {
		this.myView = myView;
	}

	/**
	 * @return the myBrowser
	 */
	public WebView getMyBrowser() {
		return myBrowser;
	}

	/**
	 * @param myBrowser the myBrowser to set
	 */
	public void setMyBrowser(WebView myBrowser) {
		this.myBrowser = myBrowser;
	}

	/**
	 * @return the myWebEngine
	 */
	public WebEngine getMyWebEngine() {
		return myWebEngine;
	}

	/**
	 * @param myWebEngine the myWebEngine to set
	 */
	public void setMyWebEngine(WebEngine myWebEngine) {
		this.myWebEngine = myWebEngine;
	}

	/**
	 * @return the myHistoryPaneView
	 */
	public ListView<String> getMyHistoryPaneView() {
		return myHistoryPaneView;
	}

	/**
	 * @param myHistoryPaneView the myHistoryPaneView to set
	 */
	public void setMyHistoryPaneView(ListView<String> myHistoryPaneView) {
		this.myHistoryPaneView = myHistoryPaneView;
	}

	/**
	 * @return the myModel
	 */
	public Model getMyModel() {
		return myModel;
	}

	/**
	 * @param myModel the myModel to set
	 */
	public void setMyModel(Model myModel) {
		this.myModel = myModel;
	}

	/**
	 * @return the myView
	 */
	public View getMyView() {
		return myView;
	}

	/**
	 * @param myView the myView to set
	 */
	public void setMyView(View myView) {
		this.myView = myView;
	}

	/**
	 * @return the myCommand
	 */
	public String getMyCommand() {
		return myCommand;
	}

	/**
	 * @param myCommand the myCommand to set
	 */
	public void setMyCommand(String myCommand) {
		this.myCommand = myCommand;
	}

	/**
	 * @return the myHelpButton
	 */
	public Button getMyHelpButton() {
		return myHelpButton;
	}

	/**
	 * @param myHelpButton the myHelpButton to set
	 */
	public void setMyHelpButton(Button myHelpButton) {
		this.myHelpButton = myHelpButton;
	}

	/**
	 * @return the myTextField
	 */
	public TextField getMyTextField() {
		return myTextField;
	}

	/**
	 * @param myTextField the myTextField to set
	 */
	public void setMyTextField(TextField myTextField) {
		this.myTextField = myTextField;
	}

	/**
	 * @return the myRunButton
	 */
	public Button getMyRunButton() {
		return myRunButton;
	}

	/**
	 * @param myRunButton the myRunButton to set
	 */
	public void setMyRunButton(Button myRunButton) {
		this.myRunButton = myRunButton;
	}

	/**
	 * @return the myCanvas
	 */
	public Pane getMyCanvas() {
		return myCanvas;
	}

	/**
	 * @param myCanvas the myCanvas to set
	 */
	public void setMyCanvas(Pane myCanvas) {
		this.myCanvas = myCanvas;
	}

	/**
	 * @return the myCommandHistoryPane
	 */
	public ScrollPane getMyCommandHistoryPane() {
		return myCommandHistoryPane;
	}

	/**
	 * @param myCommandHistoryPane the myCommandHistoryPane to set
	 */
	public void setMyCommandHistoryPane(ScrollPane myCommandHistoryPane) {
		this.myCommandHistoryPane = myCommandHistoryPane;
	}

	/**
	 * @return the myVariablePane
	 */
	public ScrollPane getMyVariablePane() {
		return myVariablePane;
	}

	/**
	 * @param myVariablePane the myVariablePane to set
	 */
	public void setMyVariablePane(ScrollPane myVariablePane) {
		this.myVariablePane = myVariablePane;
	}

	/**
	 * @return the myCustomizeButton
	 */
	public Button getMyCustomizeButton() {
		return myCustomizeButton;
	}

	/**
	 * @param myCustomizeButton the myCustomizeButton to set
	 */
	public void setMyCustomizeButton(Button myCustomizeButton) {
		this.myCustomizeButton = myCustomizeButton;
	}

	/**
	 * @return the myHistory
	 */
	public List<String> getMyHistory() {
		return myHistory;
	}

	/**
	 * @param myHistory the myHistory to set
	 */
	public void setMyHistory(List<String> myHistory) {
		this.myHistory = myHistory;
	}
}