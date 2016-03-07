package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SLogoCustomizer extends SLogoBuilder{
    private static final int XPROMPTSIZE = 500;
    private static final int YPROMPTSIZE = 275; 
    private static final int PADDING = 55;
	private static final int SPLASHSIZE = 400;
    private static final int COLORLABELSIZE = 202;
    private static final int PREFSIZE = 75;

	
	private Stage myCustomizerStage;
	private Scene myCustomizerScene;
	private VBox vbox;
	private ColorPicker colorPicker;
	private Label colorLabel;
	private Color myColor;
	private HBox colorHb;

	
	public SLogoCustomizer() {
		setup();
		myCustomizerStage = new Stage();
		myCustomizerScene = new Scene(setVBox(), XPROMPTSIZE, YPROMPTSIZE);
		myCustomizerStage.setScene(myCustomizerScene);
		myCustomizerStage.setTitle("CUSTOMIZER");
		myCustomizerStage.showAndWait();
	}
	
	private void setup(){
		setColorPicker();
	}
	
	private VBox setVBox(){
		vbox = new VBox();
		vbox.setPrefSize(SPLASHSIZE, SPLASHSIZE);
		vbox.setPadding(new Insets(PADDING));
		vbox.getChildren().addAll(colorHb);
		vbox.getStylesheets().add("view/splashstyle.css");
		return vbox;
	}

	private void setColorPicker(){
		colorLabel = new Label("Set background color: ");
		colorLabel.setPrefWidth(COLORLABELSIZE);
		colorPicker = new ColorPicker();
        colorPicker.setOnAction(e -> {
			myColor = colorPicker.getValue();
        });
		colorHb = new HBox();
		colorHb.getChildren().addAll(colorLabel, colorPicker);
		colorHb.setPrefSize(PREFSIZE, PREFSIZE);
	}

	/**
	 * @return the myColor
	 */
	public Color getMyColor() {
		return myColor;
	}

	/**
	 * @param myColor the myColor to set
	 */
	public void setMyColor(Color myColor) {
		this.myColor = myColor;
	}
	
	
}
