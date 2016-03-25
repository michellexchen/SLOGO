package view;

import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.SLogoDisplayData;

public class SLogoTurtleImage extends ImageView {

	private String myImagePath;
	private double myCoordinateShift;
	private int myPaneSize;
	private int myDirectionFlip;
	private int myTurtleSize;
	private int myPadding;
	private SLogoDisplayData myDisplayData;
	private ResourceBundle myResourceBundle;
	
	/**
	 * Constructor for SLogoTurtleImage class, reads in properties file from SLogoPropertiesVisualizer
	 * @param resources
	 * @param displaydata
	 */
	public SLogoTurtleImage(ResourceBundle resources, SLogoDisplayData displaydata) {
		myDisplayData = displaydata;
		myResourceBundle = resources;
		myImagePath = myResourceBundle.getString("ImagePath");
		myPaneSize = Integer.parseInt(myResourceBundle.getString("PaneSize"));
		myDirectionFlip = Integer.parseInt(myResourceBundle.getString("DirectionFlip"));
		myCoordinateShift = (double) myPaneSize / 2;
		myTurtleSize= Integer.parseInt(myResourceBundle.getString("TurtleSize"));
		myPadding= myTurtleSize / 2;
	}
	
	/**
	 * Initializes turtle image on screen, sets image, visibility, size, and rotation
	 */
	public void initialize(){
		this.setImage(new Image(myImagePath + myDisplayData.getImage()));
		this.setVisible(!myDisplayData.getTurtleHidden());
		turtleResize();
		this.setLayoutX(myDisplayData.getX() + myCoordinateShift - myPadding);
		this.setLayoutY(myDirectionFlip * myDisplayData.getY() 
				+ myCoordinateShift - myPadding);
		this.setRotate(myDirectionFlip * myDisplayData.getPrevDirection());
		this.setRotate(myDisplayData.getDirection());
	}

	/**
	 * resizes turtle image image
	 * 
	 * @param turtle
	 */
	private void turtleResize() {
		this.setFitWidth(myTurtleSize);
		this.setPreserveRatio(true);
		this.setSmooth(true);
		this.setCache(true);
	}
}
