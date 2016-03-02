package View;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Controller.SLogoException;
import Model.MainModel;
import Model.Model;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


public class GUIController implements Initializable  {
	
	private WebView	myBrowser;
	private WebEngine myWebEngine;
	private ListView<String> myHistoryPaneView;
	
	//Model to interact with
	private Model myModel;
	
	
	//View to interact with
	private View myView;
	
	
	
	//Command string
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
    private AnchorPane myCommandHistoryPane;
    
    
    //myVariablePane where ObservableList<Variable> will go
    @FXML
    private AnchorPane myVariablePane;
    
    
    //Customize button - WHAT IS THIS FOR? FOR CHOOSING COLOR?
    @FXML
    private Button myCustomizeButton;
    
    
//    
//	
//    @FXML 
//    private Hyperlink helpHyperlink;   
//
//
//    @FXML
//    private List<String> myHistoryPane;
//    
//
//    

    
    @Override 
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
    	
    	//TODO: Separate the operations below into different methods
    	
    	
    	//Help button - assign action
        myHelpButton.setOnAction(e -> {
        	popup("http://www.cs.duke.edu/courses/compsci308/spring16/assign/03_slogo/commands.php");
        });
        

        
        
        //Run button - return user input and stores it in myCommand
        myRunButton.setOnAction(e -> {
        	String myCommand = myTextField.getText();
        	myTextField.clear();
        	setCommand(myCommand);
        	
        	/*
        	 * TODO: Call Model's readCommand that calls
        	 * View's getCommand
        	 * and passes the command to the parser
        	 */
        	try {
				getModel().readCommand(myCommand);
			} catch (SLogoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        });
        
        
        //Initialize main Pane-myCanvas
        //myCanvas = new Pane(); ALREADY INITIALIZED?
        //This is how to add line objects
        //canvas.getChildren().addAll(circle,rectangle);
        
        
        //

        
    }
        
    
    
        
    	//myHistoryPane = new ArrayList<String>(); //initialize myHistoryPane
    	//helpLink(); //help button
        //myRunButtonmyTextField(); //myRunButton button

//    private void helpLink(){
//    	helpHyperlink.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				popup("http://www.cs.duke.edu/courses/compsci308/spring16/assign/03_slogo/myTextFields.php");	            
//			}
//    	});
//    }
    
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
        stage.setWidth(900);
        stage.setHeight(550);
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
//    private void displayHistoryPane(){
//    	myHistoryPaneView = new ListView<String>();
//    	ObservableList<String> items =FXCollections.observableArrayList (
//    			mymyHistoryPane);
//    	myHistoryPaneView.setItems(items);
//    	((ScrollPane) myHistoryPane).setContent(myHistoryPaneView);
//    }
    
//    @Override
//	public String getCommand() {
//		return myCommand;
//	}

	public void setCommand(String myCommand) {
		this.myCommand = myCommand;
	}


	public Pane getCanvas() {
		return myCanvas;
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



//
//	/**
//	 * @return the myModel
//	 */
//	public MainModel getModel() {
//		return myModel;
//	}




//	/**
//	 * @param myModel the myModel to set
//	 */
//	public void setModel(MainModel myModel) {
//		this.myModel = myModel;
//	}
//
//
////
////
////	/**
////	 * @return the myView
////	 */
////	public MainView getView() {
////		return myView;
////	}
////
//
//
//
//	/**
//	 * @param myView the myView to set
//	 */
//	public void setView(MainView myView) {
//		this.myView = myView;
//	}




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




//	@Override
//	public Visualizer getVisualizer() {
//		// TODO Auto-generated method stub
//		return null;
//	}    
        
}