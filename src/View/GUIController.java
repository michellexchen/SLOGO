package View;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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


public class GUIController implements Initializable {
	
	private WebView	myBrowser;
	private WebEngine myWebEngine;
	private ListView<String> myHistoryPaneView;
	private List<String> mymyHistoryPane;
	
    @FXML 
    private Hyperlink helpHyperlink;   
    @FXML
    private TextField myCommand;
    @FXML
    private Button myRunButton;
    @FXML
    private ScrollPane myHistoryPane;
    
    @FXML
    private MenuButton myMenu;
    
    @Override 
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
    	mymyHistoryPane = new ArrayList<String>(); //initialize myHistoryPane
    	helpLink(); //help button
        myRunButtonmyCommand(); //myRunButton button
    }

    private void helpLink(){
    	helpHyperlink.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				popup("http://www.cs.duke.edu/courses/compsci308/spring16/assign/03_slogo/myCommands.php");	            
			}
    	});
    }
    
    private void popup(String link){
		myBrowser = new WebView();
        myWebEngine = myBrowser.getEngine();
        myWebEngine.load(link);
    	VBox vbox = new VBox();
        Scene scene = new Scene(vbox);
        Stage stage = new Stage();        
        stage.setTitle("SLOGO Basic myCommand");
        stage.setWidth(900);
        stage.setHeight(550);
        vbox.getChildren().addAll(myBrowser);
        VBox.setVgrow(myBrowser, Priority.ALWAYS);        
        stage.setScene(scene);
        stage.show();
    }

    private void myRunButtonmyCommand(){
    	myRunButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
	    		sendmyCommand();
	    		mymyHistoryPane.add(myCommand.getText());
	    		displayHistoryPane();
	    		myCommand.clear();
			}
    	});
    }
    
    public String sendmyCommand(){ //to send to backend?
    	return myCommand.getText();
    }
    
    private void displayHistoryPane(){
    	myHistoryPaneView = new ListView<String>();
    	ObservableList<String> items =FXCollections.observableArrayList (
    			mymyHistoryPane);
    	myHistoryPaneView.setItems(items);
    	myHistoryPane.setContent(myHistoryPaneView);
    }    
        
}