package View;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SLogoPromptBuilder extends SLogoBuilder {
    private static final int XPROMPTSIZE = 500;
    private static final int YPROMPTSIZE = 300;
    private static final int PADDING = 50;
	private final int SPLASHSIZE = 400;
	//private final int SIZE = 500;
    private static final int LABEL_FONTSIZE = 32;
    private static final int TEXT_FONTSIZE = 20;
    private static final String FONT = "Georgia";

    private String myLanguage;
    private Stage prompt;
	private Scene promptScene;
	private VBox vbox;
	private Label label;
	private ComboBox comboBox;
	private HBox labelHb;
	private Text myActionStatus;
    
//	public SLogoPromptBuilder(String language) {
//		super(language);
//	}
	public SLogoPromptBuilder() {

	}

	public void promptScreen () {

		prompt = new Stage();
		setup(); //sets labelHb, myActionstatus
		setScene(promptScene);
		readInput();

//		Label label = new Label(getResources().getString("Select"));

//		Button btn1 = new Button(getResources().getString("FileChoose"));
//		btn1.setOnAction(e -> showSingleFileChooser(prompt, myActionStatus));
//		HBox buttonHb1 = new HBox(PADDING);
//		buttonHb1.setAlignment(Pos.CENTER);
//		buttonHb1.getChildren().addAll(btn1);		

	}
	
	private void setup(){
		setLabel();
		setLangCombobox(); //sets language list
		setHBox();
		setText();
	}
	
	private void setScene(Scene promptScene) {
		vbox = setVBox();
		promptScene = new Scene(vbox, XPROMPTSIZE, YPROMPTSIZE);
		prompt.setScene(promptScene);
		prompt.showAndWait();
		promptScene.getStylesheets().add("View/style.css");	
	}
	

	private VBox setVBox(){
		vbox = new VBox();
		vbox.setPrefSize(SPLASHSIZE, SPLASHSIZE);
//		vbox.setLayoutX((SPLASHSIZE) / 2);
//		vbox.setLayoutY((SIZE - SPLASHSIZE) / 2);
		vbox.setPadding(new Insets(PADDING));
//		vbox.getChildren().addAll(labelHb, buttonHb1, myActionStatus);
		vbox.getChildren().addAll(labelHb, myActionStatus, lang, comboBox);
		vbox.getStylesheets().add("View/style.css");
		return vbox;
	}
	
	private Label lang;
	
	private void setLangCombobox(){
		lang = new Label("Select Language:");
		ObservableList<String> options = 
				FXCollections.observableArrayList(
						"English",
						"French",
						"Chinese",
						"German",
						"Italian",
						"Portuguese",
						"Russian",
						"Spanish"
				);
		comboBox = new ComboBox(options);
	}
	
	private void setLabel(){
		label = new Label("Welcome to SLOGO!");
		label.setTextFill(Color.DARKBLUE);
		label.setFont(Font.font(FONT, FontWeight.BOLD, LABEL_FONTSIZE));
	}
	
	private void setHBox(){
		labelHb = new HBox();
		labelHb.setAlignment(Pos.CENTER);
		labelHb.getChildren().add(label);
	}
	
	private void setText(){
		myActionStatus = new Text();
		myActionStatus.setFont(Font.font(FONT, FontWeight.NORMAL, TEXT_FONTSIZE));
		myActionStatus.setFill(Color.FIREBRICK);
	}

	private void readInput(){
        comboBox.valueProperty().addListener(new ChangeListener<String>() {
        @Override 
        public void changed(ObservableValue ov, String t, String t1) {                
            //address = t1; 
        	System.out.println("changed");
        }    
    });
	//TODO: Save the option
	}
}
