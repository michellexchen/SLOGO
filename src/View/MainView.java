package View;

import java.io.File;

import Controller.MainDriver;
import Controller.LanguagesDriver;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainView {
	
	private LanguagesDriver languagesDriver;
	private Group myRoot;
	private Scene myScene;
	private Stage myStage;
	
	public MainView(Scene scene, Group root, Stage stage, LanguagesDriver languagesDriver){
		this.myStage = myStage;
		this.myRoot = myRoot;
		this.myScene = myScene;
		this.languagesDriver = languagesDriver;
	}
}