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
	private int myThickness;
	private boolean isDown;
	private String myStrokeStyle;
	
	private ColorLoader colorLoader;
	private PenStrokeLoader strokeLoader;
	
	private int DEFAULT_COLOR = 0;
	private int DEFAULT_THICKNESS = 5;
	private boolean DEFAULT_PEN_DOWN = true;
	private int DEFAULT_STROKE_STYLE = 0;
	
	public SLogoPen() throws SLogoException{
		colorLoader = new ColorLoader();
		// myColor = colorLoader.getColor(DEFAULT_COLOR);
		myColor = Color.BLACK; // temp
		strokeLoader = new PenStrokeLoader();
		myStrokeStyle = strokeLoader.getStroke(DEFAULT_STROKE_STYLE);
		myThickness = DEFAULT_THICKNESS;
		isDown = DEFAULT_PEN_DOWN;
	}
	
	public void setColor(int index) throws SLogoException {
		myColor = colorLoader.getColor(index);
	}
	
	public void setThickness (int thickness) {
		this.myThickness = thickness;
	}
	
	public int getThickness(){
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

	public boolean getPenDown() {
		return isDown;
	}
	
}