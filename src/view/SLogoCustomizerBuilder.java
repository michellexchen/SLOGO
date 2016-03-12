package view;

import java.util.Observable;

import exception.SLogoException;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.ResourceLoader;
import model.SLogoWorkspace;
import parser.CommandNameLoader;

/**
 * CustomizerBuilder that builds a prompt screen when
 * Customize button is clicked
 * 
 */
public class SLogoCustomizerBuilder extends Observable {
    //TODO shapes

    private static final String CSS_PATH = "view/splashstyle.css";

    private static final int XPROMPTSIZE = 500;
    private static final int YPROMPTSIZE = 300; 
    private static final int PADDING = 55;
    private static final int SPLASHSIZE = 400;
    private static final int COLORLABELSIZE = 202;
    private static final int PREFSIZE = 40;

    private Stage myCustomizerStage;
    private Scene myCustomizerScene;
    private VBox vbox;
    private Label colorLabel;
    private Color myPaneColor;
    private HBox colorHb;
    private ColorPicker fontColorPicker;
    private Label fontColorLabel;
    private HBox fontColorHb;
    private Button myOkayButton;
    private HBox buttonHb;
    private ComboBox comboBox;
    private Label penStyleLabel;
    private HBox penStyleHb;
    private Slider thicknessSlider;
    private Label thicknessSliderLabel;
    private HBox thicknessSliderHb;
    private SLogoPropertiesData myPropertiesData;
    private SLogoCustomizerToggleSwitch switchButton;
    private HBox switchHb;
    private Label switchLabel;

    private Color myPenColor;
    private double myPenWidth;
    private String myStrokeStyle;
    private boolean isDown;

    private SLogoGUIController myGUI;
    private CommandNameLoader myCommandNameLoader;
    private ResourceLoader myResourceLoader;

    /**
     * Default constructor that initializes ResourceLoader
     * Stage and Scene set
     * 
     * @param myGUI
     * @throws SLogoException
     */
    public SLogoCustomizerBuilder(SLogoGUIController myGUI) throws SLogoException {
        this.myGUI = myGUI;
        myResourceLoader = new ResourceLoader("default.properties");
        setup();
        myCustomizerStage = new Stage();
        myCustomizerScene = new Scene(setVBox(), XPROMPTSIZE, YPROMPTSIZE);
        myCustomizerStage.setScene(myCustomizerScene);
        myCustomizerStage.setTitle(myResourceLoader.getString("Title"));
        myCommandNameLoader = new CommandNameLoader();

    }

    /**
     * Initialize the Customizer button
     * @throws SLogoException 
     * 
     */
    private void setup() throws SLogoException{
        myPaneColor = Color.WHITE;
        myPenColor = Color.BLACK;
        myPenWidth = 1;
        setColorPicker();
        setFontColor();
        setLine();
        setLineThickness();
        setPenDown();
        setButton();
    }

    /**
     * Create a VBox design to line elements up vertically
     * 
     * @return
     */
    private VBox setVBox(){
        vbox = new VBox();
        vbox.setPrefSize(SPLASHSIZE, SPLASHSIZE);
        vbox.setPadding(new Insets(PADDING));
        vbox.getChildren().addAll(colorHb, fontColorHb, 
                                  thicknessSliderHb, switchHb, buttonHb);
        vbox.getStylesheets().add(CSS_PATH);
        return vbox;
    }

    /**
     * Hide method for closing customization popup
     */
    public void hide(){
        myCustomizerStage.hide();
    }

    /**
     * Show method for opening customization popup
     */
    public void show(){
        myCustomizerStage.show();
    }

    /**
     * For observable/observer to interact with propertiesData
     * @param propertiesData
     */
    public void setPropertiesData(SLogoPropertiesData propertiesData) {
        myPropertiesData = propertiesData;
    }

    /**
     * Front end color chooser dropdown colored rectangles
     * 
     * @author Michelle
     *
     */
    static class ColorRectCell extends ListCell<Color> {
        @Override
        public void updateItem(Color color, boolean empty) {
            super.updateItem(color, empty);
            Rectangle rect = new Rectangle(100, 20);
            if (color != null) {
                rect.setFill(color);
                setGraphic(rect);
            }
        }
    }

    ObservableList<Color> data = FXCollections.observableArrayList(
                                 Color.WHITE, Color.BLACK, Color.RED, 
                                 Color.ORANGE, Color.YELLOW, Color.GREEN, 
                                 Color.BLUE, Color.PURPLE);

    /**
     * To select color for background
     * 
     * @return
     */
    private ComboBox setColorDropdown(){

        ComboBox<Color> cb = new ComboBox<Color>();

        cb.setItems(data);

        Callback<ListView<Color>, ListCell<Color>> factory = new Callback<ListView<Color>, ListCell<Color>>() {
            @Override
            public ListCell<Color> call(ListView<Color> list) {
                return new ColorRectCell();
            }
        };

        cb.setCellFactory(factory);
        cb.setButtonCell(factory.call(null));

        return cb;
    }
    /**
     * Creates an instance of colorpicker to use
     * 
     */
    private void setColorPicker(){
        ComboBox myCB = setColorDropdown();
        myCB.getSelectionModel().select(0);
        StackPane root = new StackPane();
        root.getChildren().add(myCB);

        myCB.getSelectionModel().selectedItemProperty().addListener( 
                                          new ChangeListener<Color>() {
            @Override
            public void changed(ObservableValue<? extends Color> observable, 
                                Color oldValue, Color newValue) {
                System.out.println("CHANGED");
                myPaneColor = newValue;
                System.out.println(myPaneColor);

                System.out.println("I'M HERE");
                System.out.println(data.indexOf(myPaneColor));
            }
        });	    

        colorLabel = new Label(getResourceLoader().getString("ColorPickerLabel"));
        colorLabel.setPrefWidth(COLORLABELSIZE);

        colorHb = new HBox();
        colorHb.getChildren().addAll(colorLabel, root);
        colorHb.setPrefSize(PREFSIZE, PREFSIZE);

    }

    /**
     * Sets a new font color
     * 
     */
    private void setFontColor(){

        ComboBox myCB2 = setColorDropdown();
        myCB2.getSelectionModel().select(1); //default value
        StackPane root = new StackPane();
        root.getChildren().add(myCB2);

        myCB2.getSelectionModel().selectedItemProperty().addListener(
                                         new ChangeListener<Color>() {
            @Override
            public void changed(ObservableValue<? extends Color> observable, 
                                Color oldValue, Color newValue) {
                System.out.println("CHANGED");
                myPenColor = newValue;
                System.out.println(myPenColor);

                System.out.println("ME TOO");
                System.out.println(data.indexOf(myPenColor));
            }
        });	    

        fontColorLabel = new Label(getResourceLoader().getString("FontLabel"));
        fontColorLabel.setPrefWidth(COLORLABELSIZE);

        fontColorHb = new HBox();
        fontColorHb.getChildren().addAll(fontColorLabel, root);
        fontColorHb.setPrefSize(PREFSIZE, PREFSIZE);
    }

    /**
     * Sets line style
     * 
     */
    private void setLine(){
        penStyleLabel = new Label(getResourceLoader().getString("PenLabel"));
        penStyleLabel.setPrefWidth(COLORLABELSIZE);

        ObservableList<String> options = 
                FXCollections.observableArrayList(
                                                  "SOLID",
                                                  "DASHED",
                                                  "DOTTED"						
                        );
        comboBox = new ComboBox(options);
        comboBox.setValue(getResourceLoader().getString("PenDefault"));
        penStyleHb = new HBox();
        penStyleHb.getChildren().addAll(penStyleLabel, comboBox);
        penStyleHb.setPrefSize(PREFSIZE, PREFSIZE);
    }

    /**
     * Sets new line thickness
     * 
     */
    private void setLineThickness(){
        thicknessSliderLabel = new Label(getResourceLoader().getString("SliderLabel"));
        thicknessSliderLabel.setPrefWidth(COLORLABELSIZE);
        thicknessSlider = new Slider(0, 10, 1);
        thicknessSlider.setShowTickLabels(true);
        thicknessSlider.setMajorTickUnit(0.25f);
        thicknessSlider.setBlockIncrement(0.1f);

        thicknessSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                myPenWidth = (double) new_val;
            }
        });
        thicknessSliderHb = new HBox();
        thicknessSliderHb.getChildren().addAll(thicknessSliderLabel, thicknessSlider);
        thicknessSliderHb.setPrefSize(PREFSIZE, PREFSIZE);
    }

    /**
     * Updates penDown boolean value
     * 
     */
    private void setPenDown(){
        switchHb = new HBox();
        switchLabel = new Label(getResourceLoader().getString("SwitchLabel"));
        switchButton = new SLogoCustomizerToggleSwitch();
        switchHb.setAlignment(Pos.CENTER);
        switchHb.getChildren().addAll(switchLabel, switchButton);
        switchHb.setPrefSize(PREFSIZE, PREFSIZE);
    }

    /**
     * Set button that applies changes to the current working environment
     * 
     */
    private void setButton() throws SLogoException{
        buttonHb = new HBox();
        myOkayButton = new Button(getResourceLoader().getString("OkayButton"));
        buttonHb.setAlignment(Pos.CENTER);
        buttonHb.getChildren().add(myOkayButton);
        buttonHb.setPrefSize(PREFSIZE, PREFSIZE);
        myOkayButton.setOnMouseClicked(e -> {

            myStrokeStyle = comboBox.getSelectionModel().getSelectedItem().toString();

            try {
                myGUI.run(new CommandNameLoader().getString("setbg") + " " 
                                                        + data.indexOf(myPaneColor));
            } catch (Exception e1) { e1.printStackTrace(); }
            try {
                myGUI.run(myCommandNameLoader.getString("setpc") + " " 
                                                        + data.indexOf(myPenColor));
            } catch (Exception e1) { e1.printStackTrace(); }
            try {
                myGUI.run(myCommandNameLoader.getString("setpensize") + " " 
                                                        + myPenWidth);
            } catch (Exception e1) { e1.printStackTrace(); }
            if (switchButton.isDown()) {
                try {
                    myGUI.run(myCommandNameLoader.getString("pd"));
                } catch (Exception e1) { e1.printStackTrace(); }
            } else {
                try {
                    myGUI.run(myCommandNameLoader.getString("pu"));
                } catch (Exception e1) { e1.printStackTrace(); }
            }
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
    /**
     * @return the myPenColor
     */
    public Color getMyPenColor() {
        return myPenColor;
    }

    /**
     * @param myPenColor the myPenColor to set
     */
    public void setMyPenColor(Color myPenColor) {
        this.myPenColor = myPenColor;
    }

    /**
     * @return the myPenWidth
     */
    public double getMyPenWidth() {
        return myPenWidth;
    }

    /**
     * @param myPenWidth the myPenWidth to set
     */
    public void setMyPenWidth(double myPenWidth) {
        this.myPenWidth = myPenWidth;
    }

    /**
     * @return the myStrokeStyle
     */
    public String getMyStrokeStyle() {
        return myStrokeStyle;
    }

    /**
     * @param myStrokeStyle the myStrokeStyle to set
     */
    public void setMyStrokeStyle(String myStrokeStyle) {
        this.myStrokeStyle = myStrokeStyle;
    }

    /**
     * @return the isDown
     */
    public boolean isDown() {
        return isDown;
    }

    /**
     * @param isDown the isDown to set
     */
    public void setDown(boolean isDown) {
        this.isDown = isDown;
    }

    /**
     * @return the myResourceLoader
     */
    public ResourceLoader getResourceLoader () {
        return myResourceLoader;
    }

    /**
     * @param myResourceLoader the myResourceLoader to set
     */
    public void setResourceLoader (ResourceLoader myResourceLoader) {
        this.myResourceLoader = myResourceLoader;
    }
}