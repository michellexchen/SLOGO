package model;
import exception.SLogoException;
import javafx.scene.paint.Color;


/**
 * 
 * Container for pen-related info
 * Only used in DisplayData--Character still has private fields that
 * keep relevant information
 *
 */

public class SLogoPen {
	
	private Color myColor;
	private double myThickness;
	private boolean isDown;
	private String myStrokeStyle;
	private int colorIndex;
	
	private ColorLoader colorLoader;
	private PenStrokeLoader strokeLoader;
	
	private int DEFAULT_PEN_INDEX = 0;
	private int DEFAULT_THICKNESS = 1;
	private boolean DEFAULT_PEN_DOWN = true;
	private int DEFAULT_STROKE_STYLE = 0;
	
	public SLogoPen() throws SLogoException{
		colorLoader = new ColorLoader();
		colorIndex = DEFAULT_PEN_INDEX;
		myColor = colorLoader.getColor(colorIndex);
		strokeLoader = new PenStrokeLoader();
		myStrokeStyle = strokeLoader.getStroke(DEFAULT_STROKE_STYLE);
		myThickness = DEFAULT_THICKNESS;
		isDown = DEFAULT_PEN_DOWN;
	}
	
	public int getColorIndex() {
		return colorIndex;
	}
	
	public void setColor(int penIndex) throws SLogoException {
		this.colorIndex = penIndex;
		myColor = colorLoader.getColor(penIndex);
	}
	
	public void setThickness(double thickness) {
		this.myThickness = thickness;
	}
	
	public double getThickness(){
		return myThickness;
	}
	
	public Color getPenColor() {
		return myColor;
	}
	
	public void setPenColor(Color myColor){
		this.myColor = myColor;
	}
	
	public void setPenDown(boolean penDown) {
		this.isDown = penDown;
	}

	/**
	 * @return the myStrokeStyle
	 */
	public String getPenStrokeStyle() {
		return myStrokeStyle;
	}

	/**
	 * @param myStrokeStyle the myStrokeStyle to set
	 */
	public void setPenStrokeStyle(String myStrokeStyle) {
		this.myStrokeStyle = myStrokeStyle;
	}

	public boolean isPenDown() {
		return isDown;
	}
	
}