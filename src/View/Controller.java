package View;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
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


public class Controller

    implements Initializable {
	
	private WebView browser;
	private WebEngine webEngine;
	private ListView<String> myHistoryView;
	private List<String> myHistory;
	
    @FXML 
    private Hyperlink helpHyperlink;
    
    @FXML
    private TextField command;
    
    @FXML
    private Button run;
    
    @FXML
    private ScrollPane history;
    
    @Override 
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
    	myHistory = new ArrayList<String>(); //intialize history
    	helpLink(); //help button
        runCommand();
    }

    private void helpLink(){
    	helpHyperlink.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				popup("http://www.cs.duke.edu/courses/compsci308/spring16/assign/03_slogo/commands.php");	            
			}
    	});
    }
    
    private void popup(String link){
		browser = new WebView();
        webEngine = browser.getEngine();
        webEngine.load(link);
    	VBox vbox = new VBox();
        Scene scene = new Scene(vbox);
        Stage stage = new Stage();        
        stage.setTitle("SLOGO Basic command");
        stage.setWidth(900);
        stage.setHeight(550);
        vbox.getChildren().addAll(browser);
        VBox.setVgrow(browser, Priority.ALWAYS);        
        stage.setScene(scene);
        stage.show();
    }

    private void runCommand(){
    	run.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
	    		sendCommand();
	    		myHistory.add(command.getText());
	    		displayHistory();
	    		command.clear();
			}
    	});
    }
    
    public String sendCommand(){ //to send to backend?
    	return command.getText();
    }
    
    public void displayHistory(){
    	myHistoryView = new ListView<String>();
    	ObservableList<String> items =FXCollections.observableArrayList (
    			myHistory);
    	myHistoryView.setItems(items);
    	history.setContent(myHistoryView);
    }
        
}
