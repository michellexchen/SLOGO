package View;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
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

public class SLogoPromptBuilder extends SLogoBuilder {
    private static final int XPROMPTSIZE = 500;
    private static final int YPROMPTSIZE = 275; 
    private static final int PADDING = 55;
	private final int SPLASHSIZE = 400;
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
	private HBox langHb;
	private HBox buttonHb;
	private Label lang;
	private HBox colorHb;
	private Label colorLabel;
	private Text myActionStatus;
	private Button ok;
	private ComboBox colorCb;
	private Color myColor = Color.WHITE;
	private ColorPicker colorPicker;


//	public SLogoPromptBuilder(String language) {
//		super(language);
//	}
	
	public SLogoPromptBuilder() {

	}

	public void promptScreen () {
		prompt = new Stage();
		setup();
		setScene(promptScene);		
	}
	
	private void setup(){
		setWelcome();
		setLanguage();
		setColor();
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
		vbox.getChildren().addAll(labelHb, langHb, colorHb, buttonHb);
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
		labelHb.setPrefSize(75, 75);
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
		langHb.setPrefSize(75, 75);

	}
	
	private void setColor(){
		colorLabel = new Label("Select background color: ");
		colorLabel.setPrefWidth(202);
		colorPicker = new ColorPicker();
        colorPicker.setOnAction(e -> {
			myColor = colorPicker.getValue();
        });
		colorHb = new HBox();
		colorHb.getChildren().addAll(colorLabel, colorPicker);
		colorHb.setPrefSize(75, 75);
	}
		
	
	private void setButton(){ 
		buttonHb = new HBox();
		ok = new Button("OKAY");
		buttonHb.setAlignment(Pos.CENTER);
		buttonHb.getChildren().add(ok);
		buttonHb.setPrefSize(75, 75);
		ok.setOnMouseClicked(e -> {
			myLanguage = comboBox.getSelectionModel().getSelectedItem().toString();
			//testing
			System.out.println(myLanguage);
			System.out.println(myColor.toString());
		});
	}
	
	public String sendMyLanguage(){ //for back end
		return myLanguage;
	}

	public Color sendMyColor(){ 
		return myColor;
	}
	
}
