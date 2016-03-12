package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import exception.SLogoException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import model.Model;
import model.SLogoDisplayData;

/**
 * 
 * A controller class used in conjunction with SceneBuilder's
 * UI.fxml file
 *
 */
public class SLogoGUIController implements Initializable, Observer {
	
	private static final String HELP_URL = "http://www.cs.duke.edu/courses/"
						+ "compsci308/spring16/assign/03_slogo/commands.php";
	private static final int POPUP_WIDTH = 900;
	private static final int POPUP_HEIGHT = 550;
	private static final int RGB_CONST = 255;

	private WebView	myBrowser;
	private WebEngine myWebEngine;
	private ListView<String> myHistoryPaneView;
	private ListView<String> myPropertiesPaneView = new ListView<String>();
    private ObservableList<String> myProperties;
    
    private Stage myCurrentStage;
    private Color myCanvasColor;
    private ColorPicker myColorPicker;
    private HBox myColorHBox;
    
    private SLogoCustomizerBuilder myCustomizer;
    private SLogoPropertiesData myPropertiesData;
	
	private Model myModel;
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
    private MenuButton myMenuButton;
   
        
    //MenuButton's MenuItem list
    @FXML
    private MenuItem myProject1;
    @FXML
    private MenuItem myProject2;
    @FXML
    private MenuItem myProject3;
    @FXML
    private MenuItem myProject4;
    @FXML
    private MenuItem myProject5;
    
    
    
    //Command History Pane where ObservableList<CommandNode> will go
    @FXML
    private ScrollPane myCommandHistoryPane;
 
    //myVariablePane where ObservableList<Variable> will go
    @FXML
    private ScrollPane myVariablePane;
    
    //Displays the properties of a turtle
    @FXML
    private Pane myPropertyPane;
    
    //This adds workspace
    @FXML
    private Button myAddWorkspaceButton;
    
    //Customize button - WHAT IS THIS FOR? FOR CHOOSING COLOR?
    @FXML
    private Button myCustomizeButton;
    
    
    //History
    @FXML
    private List<String> myHistory;

    /**
     * All GUI elements are initialized in this method
     * and FXML settings are read
     * 
     */
    @Override 
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
    	myHistory = new ArrayList<String>();
    	myPropertyPane.getChildren().add(myPropertiesPaneView);
		myCustomizer = new SLogoCustomizerBuilder();
		myCustomizer.hide();
		
    	assignMenuAction();
    	assignHelpAction();
        assignRunAction();
        customize();
        assignAddWorkspaceAction();
    }
        
    /**
     * Assigns an action to Run button
     * 
     */
    private void assignRunAction () {
        myRunButton.setOnAction(e -> {
        	myCommand = myTextField.getText();
            myTextField.clear();
            run(myCommand);
        });  
    }
    
    /**
     * Assigns an action to addWorkspace Button
     * Dependency is the model interface
     * 
     */
    private void assignAddWorkspaceAction () {
    	myAddWorkspaceButton.setOnAction(e -> {
    		myCurrentStage = (Stage) myAddWorkspaceButton.getScene().getWindow();
    		myCurrentStage.hide();
    		try {
    			getModel().addWorkspace();
    		} catch (Exception e1) {}
    	});
    }
    
    /**
     * Assigns an action to the HELP Button
     */
    private void assignHelpAction () {
        myHelpButton.setOnAction(e -> {
        	popup(HELP_URL);
        });
    }
    
    /**
     * Manually assigns an action to each MenuItem
     * 
     * This is not the optimal design, but it was done this way
     * because FXML (created by SceneBuilder) does not support
     * dynamic addition of MenuItems to MenuButton
     * 
     */
    private void assignMenuAction () {
    	myProject1.setOnAction(e -> {
    		try {
				getModel().switchWorkspace(0);
			} catch (Exception e1) {}
    	});
    	myProject2.setOnAction(e -> {
    		try {
				getModel().switchWorkspace(1);
			} catch (Exception e1) {}
    	});
    	myProject3.setOnAction(e -> {
    		try {
				getModel().switchWorkspace(2);
			} catch (Exception e1) {}
    	});
    	myProject4.setOnAction(e -> {
    		try {
				getModel().switchWorkspace(3);
			} catch (Exception e1) {}
    	});
    	myProject5.setOnAction(e -> {
    		try {
				getModel().switchWorkspace(4);
			} catch (Exception e1) {}
    	});
    }

    /**
     * Defines and assigns an action to Run button
     * Notifies Model that there is a command to be processed
     * 
     * @param command
     */
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
          	displayProperties();
    }
    
    /**
     * Lets the user view the commands
     * 
     * NEEDS TO BE REVISED!!!
     * 
     * @param link
     */
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
    
    /**
     * Lists commands in the commandhistory pane
     * 
     */
    private void displayHistory(){
       	myHistoryPaneView = new ListView<String>();
    	ObservableList<String> items =FXCollections.observableArrayList (
    			myHistory);
    	myHistoryPaneView.setItems(items);
    	myCommandHistoryPane.setContent(myHistoryPaneView);

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
    
    /**
     * Displays properties
     * 
     */
    public void displayProperties(){
    	myPropertiesPaneView.setItems(myProperties);
    	myPropertiesPaneView.setPrefSize(200, 150);
    }
    
    /**
     * Updates property info on the bottom left side of the screen
     * 
     * @param displayData
     */
    public void updateProperties(SLogoDisplayData displayData){
    	
    	myProperties =FXCollections.observableArrayList (
    			("Direction: " + Double.toString(displayData.getDirection())),
    			("X position: " + displayData.getX()),
    			("Y position: " + displayData.getY()),
    			("Pen Down: " + displayData.getPen().getDown()),
    			("Pen Color: " + displayData.getPen().getColorIndex()),
    			("Pen Size: " + displayData.getPen().getSize())
    			);
    	
    	myPropertiesPaneView.setPrefSize(200, 150);
    	myPropertiesPaneView.setItems(myProperties);
    }
    
    /**
     * Assigns an action to the customize button
     * 
     */
    private void customize(){
    	myCustomizeButton.setOnMouseClicked(e -> {
    		myCustomizer.setPropertiesData(myPropertiesData);
    		myCustomizer.show();    		
		});
    }

    /**
     * Converts Color object to its hex string
     * 
     * @param color
     * @return
     */
    public String toRGBCode (Color color) {
    	return String.format( "#%02X%02X%02X",
    			(int) (color.getRed() * RGB_CONST),
    			(int) (color.getGreen() * RGB_CONST),
    			(int) (color.getBlue() * RGB_CONST));
    }
    
    /**
     * Getter for Customizer Button
     * 
     * @return
     */
    public SLogoCustomizerBuilder getCustomizer() {
    	return myCustomizer;
    }

    /**
     * Colorpicker method for Customize button
     * 
     * @return
     */
    private HBox chooseColor(){	//for selecting color   		
		Label colorLabel = new Label("Select background color: ");
		
		myColorPicker = new ColorPicker();
        myColorPicker.setOnAction(e -> {
        	myCanvasColor = myColorPicker.getValue();
        });
		
		myColorHBox = new HBox();
		myColorHBox.getStylesheets().add("view/splashstyle.css");
		myColorHBox.getChildren().addAll(colorLabel, myColorPicker);

		return myColorHBox;
    }
    
    public Color getPaneColor(){
    	return myCanvasColor;
    }
    
    public void setPaneColor(){
    	this.myCanvasColor = new SLogoCustomizerBuilder().getMyPaneColor();
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

    public void setPropertiesData(SLogoPropertiesData propertiesData) {
        this.myPropertiesData = propertiesData;
        myPropertiesData.addObserver(this);
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
	 * @return the myMenuButton
	 */
	public MenuButton getMenuButton() {
		return myMenuButton;
	}

	/**
	 * @param myMenuButton the myMenuButton to set
	 */
	public void setMenuButton(MenuButton myMenuButton) {
		this.myMenuButton = myMenuButton;
	}

	/**
	 * updates canvas color
	 */
	@Override
	public void update(Observable o, Object arg) {
		getCanvas().setStyle("-fx-background-color: " + toRGBCode(myPropertiesData.getPaneColor()));
	}

	/**
	 * @return the myCommand
	 */
	public String getCommand() {
		return myCommand;
	}

}