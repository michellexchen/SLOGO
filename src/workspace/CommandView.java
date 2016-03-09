package workspace;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CommandView extends SLogoBuilder{

    private static final int PROMPTSIZE = 500;
    private Stage myStage;
	public CommandView() {
		myStage = new Stage();
		Scene myScene;
		VBox myVBox = new VBox();
		
		UIScrollPane UISP = new UIScrollPane("HISTORY", 200, 380);
		
		Label myLabel = new Label("hi");
		myVBox.getChildren().addAll(myLabel, UISP);
		
		myScene = new Scene(myVBox, PROMPTSIZE, PROMPTSIZE);
		myStage.setScene(myScene);
		
		

	}
	
	public void show() {
		myStage.showAndWait();
	}

}
