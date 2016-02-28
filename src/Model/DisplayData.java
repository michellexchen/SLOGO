/**
 * 
 */
package Model;

import java.util.Observable;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * @author Hunter
 *
 */
public class DisplayData extends Observable {

	private double xCoordinate;
	private double yCoordinate;
	private double myAngle;
	
	
	private boolean penDown;
	private Color penColor;
	private ImageView myImage;
	
	
	/**
	 * Constructor that sets initial values
	 * 
	 * @param xCoordinate, yCoordinate, angle, penDown, penColor, and Image
	 */
	public DisplayData(double x, double y, double angle, boolean penDown,
			Color penColor, ImageView image) {
		
		this.xCoordinate = x;
		this.yCoordinate = y;
		this.myAngle = angle;
		this.penDown = penDown;
		this.penColor = penColor;
		this.myImage = image;
	
	}
	
	/**
	 * Set the state of DisplayData as 'changed'
	 * and notify all Observers
	 * 
	 * @param None
	 */
	private void changed() {
		setChanged();
		notifyObservers();
	}
	
	public double getxCoordinate() {
		return xCoordinate;
	}
	public void setxCoordinate(double xCoordinate) {
		changed();
		this.xCoordinate = xCoordinate;
	}
	public double getyCoordinate() {
		return yCoordinate;
	}
	public void setyCoordinate(double yCoordinate) {
		changed();
		this.yCoordinate = yCoordinate;
	}
	public double getMyAngle() {
		return myAngle;
	}
	public void setMyAngle(double myAngle) {
		changed();
		this.myAngle = myAngle;
	}
	public boolean isPenDown() {
		return penDown;
	}
	public void setPenDown(boolean penDown) {
		changed();
		this.penDown = penDown;
	}
	public Color getPenColor() {
		return penColor;
	}
	public void setPenColor(Color penColor) {
		changed();
		this.penColor = penColor;
	}
	public ImageView getMyImage() {
		return myImage;
	}
	public void setMyImage(ImageView myImage) {
		changed();
		this.myImage = myImage;
	}


	
}
