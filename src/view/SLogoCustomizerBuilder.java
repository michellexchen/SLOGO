package view;

import java.util.Observable;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * 
 * CustomizerBuilder that builds a prompt screen when
 * Customize button is clicked
 */
public class SLogoCustomizerBuilder extends Observable {
	
    private static final int XPROMPTSIZE = 500;
    private static final int YPROMPTSIZE = 275; 
    private static final int PADDING = 55;
	private static final int SPLASHSIZE = 400;
    private static final int COLORLABELSIZE = 202;
    private static final int PREFSIZE = 35;
	
	private Stage myCustomizerStage;
	private Scene myCustomizerScene;
	private VBox vbox;
	private ColorPicker colorPicker;
	private Label colorLabel;
	private Color myPaneColor;
	private HBox colorHb;
	private ColorPicker fontColorPicker;
	private Label fontColorLabel;
	private Color myFontColor;
	private HBox fontColorHb;
	
	private Button myOkayButton;
	private HBox buttonHb;
	private ComboBox comboBox;
	private Label penStyleLabel;
	private HBox penStyleHb;
	private String myLineStyle;
	private Slider thicknessSlider;
	private Label thicknessSliderLabel;
	private HBox thicknessSliderHb;
	private double myLineThickness;
	private SLogoPropertiesData myPropertiesData;
	
	public SLogoCustomizerBuilder() {
		setup();
		myCustomizerStage = new Stage();
		myCustomizerScene = new Scene(setVBox(), XPROMPTSIZE, YPROMPTSIZE);
		myCustomizerStage.setScene(myCustomizerScene);
		myCustomizerStage.setTitle("CUSTOMIZER");
	}
	
	private void setup(){
		setColorPicker();
		setFontColor();
		setLine();
		setLineThickness();
		setButton();
	}
	
	private VBox setVBox(){
		vbox = new VBox();
		vbox.setPrefSize(SPLASHSIZE, SPLASHSIZE);
		vbox.setPadding(new Insets(PADDING));
		vbox.getChildren().addAll(colorHb, fontColorHb, penStyleHb, 
										thicknessSliderHb, buttonHb);
		vbox.getStylesheets().add("view/splashstyle.css");
		return vbox;
	}
	
	public void hide(){
		myCustomizerStage.hide();
	}
	
	public void show(){
		myCustomizerStage.show();
	}
	
	public void setPropertiesData(SLogoPropertiesData propertiesData) {
		myPropertiesData = propertiesData;
	}

	/**
	 * Creates an instance of colorpicker to use
	 * 
	 */
	private void setColorPicker(){
		colorLabel = new Label("Set background color: ");
		colorLabel.setPrefWidth(COLORLABELSIZE);
		colorPicker = new ColorPicker(Color.BLUE);
        colorPicker.setOnAction(e -> {
        	myPaneColor = colorPicker.getValue();
        });
		colorHb = new HBox();
		colorHb.getChildren().addAll(colorLabel, colorPicker);
		colorHb.setPrefSize(PREFSIZE, PREFSIZE);
	}
	
	/**
	 * Sets a new font color
	 * 
	 */
	private void setFontColor(){
		fontColorLabel = new Label("Set font color: ");
		fontColorLabel.setPrefWidth(COLORLABELSIZE);
		//TODO get current font color, set fontColorPicker/myFontColor to that color 
		fontColorPicker = new ColorPicker(Color.BLACK);
		fontColorPicker.setOnAction(e -> {
			myFontColor = fontColorPicker.getValue();
        });
        fontColorHb = new HBox();
        fontColorHb.getChildren().addAll(fontColorLabel, fontColorPicker);
        fontColorHb.setPrefSize(PREFSIZE, PREFSIZE);
	}
	
	/**
	 * Sets line style
	 * 
	 */
	private void setLine(){
		penStyleLabel = new Label("Set pen properties: ");
		penStyleLabel.setPrefWidth(COLORLABELSIZE);
		
		ObservableList<String> options = 
				FXCollections.observableArrayList(
						"SOLID",
						"DASHED",
						"DOTTED"						
				);
		comboBox = new ComboBox(options);
		comboBox.setValue("SOLID");
		penStyleHb = new HBox();
		penStyleHb.getChildren().addAll(penStyleLabel, comboBox);
        penStyleHb.setPrefSize(PREFSIZE, PREFSIZE);
	}
	
	/**
	 * Sets new line thickness
	 * 
	 */
	private void setLineThickness(){
		thicknessSliderLabel = new Label("Set line width: ");
		thicknessSliderLabel.setPrefWidth(COLORLABELSIZE);
			thicknessSlider = new Slider(0, 10, 1);
			thicknessSlider.setShowTickLabels(true);
			thicknessSlider.setMajorTickUnit(0.25f);
			thicknessSlider.setBlockIncrement(0.1f);
			
			thicknessSlider.valueProperty().addListener(new ChangeListener<Number>() {
		            public void changed(ObservableValue<? extends Number> ov,
		                Number old_val, Number new_val) {
		            		myLineThickness = (double) new_val;
		            }
		        });
		thicknessSliderHb = new HBox();
		thicknessSliderHb.getChildren().addAll(thicknessSliderLabel, thicknessSlider);
		thicknessSliderHb.setPrefSize(PREFSIZE, PREFSIZE);

	}
	
	/**
	 * Set button that applies changes to the current working environment
	 * 
	 */
	private void setButton(){
		buttonHb = new HBox();
		myOkayButton = new Button("OKAY");
		buttonHb.setAlignment(Pos.CENTER);
		buttonHb.getChildren().add(myOkayButton);
		buttonHb.setPrefSize(PREFSIZE, PREFSIZE);
		myOkayButton.setOnMouseClicked(e -> {
			System.out.println("new pane color: ");
			System.out.println(myPaneColor);
			myPropertiesData.setPaneColor(myPaneColor);
			System.out.println("new font color: ");
			System.out.println(myFontColor);
			myLineStyle = comboBox.getSelectionModel().getSelectedItem().toString();
			System.out.println("new pen style: ");
			System.out.println(myLineStyle);
			System.out.println("my new line thickness: ");
			System.out.println(myLineThickness);
			myPropertiesData.notifyObservers();
			myCustomizerStage.hide();
		});
	}
	
	public void update(Observable observable, Object arg1) {
		
	}
		
	/**
	 * @return the myColor
	 */
	public Color getMyPaneColor() {
		return myPaneColor;
	}

	/**
	 * @param myColor the myColor to set
	 */
	public void setMyPaneColor(Color myColor) {
		this.myPaneColor = myColor;
	}
}
