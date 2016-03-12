package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 * Subclass of SLogoBuilder that generates a prompt screen
 * that greets the user and takes in necessary information 
 * such as language and pane color
 * 
 */
public class SLogoPromptBuilder extends SLogoBuilder {
    private static final int XPROMPTSIZE = 500;
    private static final int YPROMPTSIZE = 275; 
    private static final int PADDING = 55;
	private static final int SPLASHSIZE = 400;
    private static final int LABEL_FONTSIZE = 32;
//    private static final int TEXT_FONTSIZE = 20;
    private static final String FONT = "Georgia";
    private static final int PREFSIZE = 75;
    private static final int COLORLABELSIZE = 202;

    private String myLanguage;
    private Stage prompt;
	private Scene promptScene;
	private VBox vbox;
	private Label label;
	private ComboBox comboBox;
	private HBox labelHb;
	private HBox langHb;
	private HBox buttonHb;
	private Label lang;
	private HBox colorHb;
	private Label colorLabel;
	private Text myActionStatus;
	private Button myOkayButton;
	private ComboBox colorCb;
	private Color myColor;
	private ColorPicker colorPicker;
	private SLogoPropertiesData myPropertiesData;
	
	public SLogoPromptBuilder(SLogoPropertiesData propertiesData) {
		myColor = Color.WHITE;
		myPropertiesData = propertiesData;
	}

	public void promptScreen () {
		prompt = new Stage();
		setup();
		setScene(promptScene);		
	}
	
	private void setup(){
		setWelcome();
		setLanguage();
		setButton();
	}
	
	private void setScene(Scene promptScene) {
		vbox = setVBox();
		promptScene = new Scene(vbox, XPROMPTSIZE, YPROMPTSIZE);
		prompt.setScene(promptScene);
		prompt.showAndWait();
	}
	
	private VBox setVBox(){
		vbox = new VBox();
		vbox.setPrefSize(SPLASHSIZE, SPLASHSIZE);
		vbox.setPadding(new Insets(PADDING));
		vbox.getChildren().addAll(labelHb, langHb, buttonHb);
		vbox.getStylesheets().add("View/splashstyle.css");
		return vbox;
	}
	
	private void setWelcome(){ 
		label = new Label("WELCOME TO SLOGO!");
		label.setTextFill(Color.DARKBLUE);
		label.setFont(Font.font(FONT, FontWeight.BOLD, LABEL_FONTSIZE));
		labelHb = new HBox();
		labelHb.setAlignment(Pos.CENTER);
		labelHb.getChildren().add(label);
		labelHb.setPrefSize(PREFSIZE, PREFSIZE);
	}
	
	private void setLanguage(){ 
		lang = new Label("Select language:  ");
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
		comboBox.setValue("English");
		langHb = new HBox();
		langHb.setAlignment(Pos.CENTER);
		langHb.getChildren().addAll(lang, comboBox);
		langHb.setPrefSize(PREFSIZE, PREFSIZE);

	}
	

		
	
	private void setButton(){ 
		buttonHb = new HBox();
		myOkayButton = new Button("OKAY");
		buttonHb.setAlignment(Pos.CENTER);
		buttonHb.getChildren().add(myOkayButton);
		buttonHb.setPrefSize(PREFSIZE, PREFSIZE);
		myOkayButton.setOnMouseClicked(e -> {
			myLanguage = comboBox.getSelectionModel().getSelectedItem().toString();
			getPrompt().hide();
		});
	}
	
	public String sendMyLanguage(){ //for back end
		return myLanguage;
	}

	public Color sendMyColor(){ 
		return myColor;
	}
	
	/**
	 * @return the prompt
	 */
	public Stage getPrompt() {
		return prompt;
	}

	/**
	 * @param prompt the prompt to set
	 */
	public void setPrompt(Stage prompt) {
		this.prompt = prompt;
	}
	
}