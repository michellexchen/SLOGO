package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import exception.SLogoException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
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
public class SLogoGUIController implements Initializable  {
	
	private static final String HELP_URL = "http://www.cs.duke.edu/courses/"
						+ "compsci308/spring16/assign/03_slogo/commands.php";
	private static final int POPUP_WIDTH = 900;
	private static final int POPUP_HEIGHT = 550;

	private WebView	myBrowser;
	private WebEngine myWebEngine;
	private ListView<String> myHistoryPaneView;
	private ListView<String> myPropertiesPaneView = new ListView<String>();
    private ObservableList<String> myProperties;
    
    
    private Color myCanvasColor;
    private ColorPicker myColorPicker;
    private HBox myColorHBox;
    
    private SLogoCustomizerBuilder myCustomizer;

	
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
    private Pane myPropertyPane;
    
    //This adds workspace
    @FXML
    private Button myAddWorkspaceButton;
    
    //Customize button - WHAT IS THIS FOR? FOR CHOOSING COLOR?
    @FXML
    private Button myCustomizeButton;
    
    @FXML
    private List<String> myHistory;
    
//    @FXML
//    private List<String> myProperties;

    
    
    @Override 
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
    	//initializes properties
    	myPropertyPane.getChildren().add(myPropertiesPaneView);

    	//TODO: Separate the operations below into different methods
    	
    	myHistory = new ArrayList<String>();
    	
    	//Help button - assign action
        myHelpButton.setOnAction(e -> {
        	popup(HELP_URL);
        });
                   
        //Run button - return user input and stores it in myCommand
        //This will be refactored to a separate mmethod
        myRunButton.setOnAction(e -> {
        	myCommand = myTextField.getText();
            myTextField.clear();
            run(myCommand);
        });  
        
        //customize button
        customize();
              
        //AddWorkspace Button. Causes Model to create a new workspace
        //This will be refactored out to a separate method
        myAddWorkspaceButton.setOnAction(e -> {
        	
        	//Hide current stage
        	Stage currentStage = (Stage)myAddWorkspaceButton.getScene().getWindow();
        	currentStage.hide();
        	
        	//Get Model to create a new workspace and switch into it
        	try {
				getModel().addWorkspace();
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
          	displayProperties();
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
    
    
    public void displayProperties(){
    	myPropertiesPaneView.setItems(myProperties);
    	myPropertiesPaneView.setPrefSize(200, 150);
    }
    

    
    public void updateProperties(SLogoDisplayData displayData){
    	
    	myProperties =FXCollections.observableArrayList (
    			("Angle: " + Double.toString(displayData.getAngle())),
    			("X position: " + displayData.getX()),
    			("Y position: " + displayData.getY()),
    			("Pen Down: " + displayData.isPenDown()),
    			("Pen Color: " + displayData.getPenColor().toString())			
    			);
    	
    	myPropertiesPaneView.setPrefSize(200, 150);
    	myPropertiesPaneView.setItems(myProperties);
    }
    
    
    private void customize(){
    	myCustomizeButton.setOnMouseClicked(e -> {
    		myCustomizer = new SLogoCustomizerBuilder();
    		System.out.println("hi");
    		System.out.println(myCustomizer.getMyColor());
//        	Dialog customize = new Dialog();
//        	customize.getDialogPane().setPrefSize(500, 500);
//        	customize.setTitle("CUSTOMIZE");
//        	customize.setGraphic(chooseColor());
//        	customize.getDialogPane().getButtonTypes().add(new ButtonType("DONE", ButtonData.CANCEL_CLOSE));
//        	customize.showAndWait();
		});
    }

    private HBox chooseColor(){	//for selecting color   		
		Label colorLabel = new Label("Select background color: ");
		
		myColorPicker = new ColorPicker();
        myColorPicker.setOnAction(e -> {
        	myCanvasColor = myColorPicker.getValue();
        	System.out.println(myCanvasColor);
        });
		
		myColorHBox = new HBox();
		myColorHBox.getStylesheets().add("view/splashstyle.css");
		myColorHBox.getChildren().addAll(colorLabel, myColorPicker);

		return myColorHBox;
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
	
}