package view;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.paint.Color;

/**
 * The purpose of this class is to create a map that maps the names of Colors to their 
 * JavaFx color object and vice versa.
 * 
 */
public class PaintMap {
	
	private static PaintMap instance;
	private Map<String, Color> stringToColor;
	private Map<Color, String> colorToString;

	private PaintMap() {
		colorToString = new HashMap<Color, String>();
		stringToColor = new HashMap<String, Color>();
		initStringToColorMap();
		initColorToStringMap();
	}
	/**
	 * returns instance of paintmap
	 * 
	 * @return paintmap instance
	 */
	public static synchronized PaintMap getInstance() {
		if (instance == null) {
			instance = new PaintMap();
		}
		return instance;
	}
	
	/**
	 * adds colors to STring to Color Map
	 */
	private void initStringToColorMap() {
		stringToColor.put("red", Color.RED);
		stringToColor.put("green", Color.GREEN);
		stringToColor.put("black", Color.BLACK);
		stringToColor.put("yellow", Color.YELLOW);
		stringToColor.put("blue", Color.BLUE);
		stringToColor.put("brown", Color.BROWN);
		stringToColor.put("orange", Color.ORANGE);
		stringToColor.put("purple", Color.PURPLE);
		stringToColor.put("white", Color.WHITE);
		stringToColor.put("gray", Color.GRAY);
		stringToColor.put("pink", Color.PINK);
		stringToColor.put("cyan", Color.CYAN);
	}
	
	/**
	 * adds string and colors to the color to string map
	 */
	private void initColorToStringMap() {
		colorToString.put(Color.RED, "red");
		colorToString.put(Color.GREEN, "green");
		colorToString.put(Color.BLACK, "BLACK");
		colorToString.put(Color.YELLOW, "yellow");
		colorToString.put(Color.BLUE, "blue");
		colorToString.put(Color.BROWN, "brown");
		colorToString.put(Color.PURPLE, "purple");
		colorToString.put(Color.WHITE, "white");
		colorToString.put(Color.GRAY, "gray");
		colorToString.put(Color.ORANGE, "orange");
		colorToString.put(Color.PINK, "pink");
		colorToString.put(Color.CYAN, "cyan");
	}
	
	/**
	 * returns color name given color object
	 * @param color color object
	 * @return string of color name
	 */
	public String getColorString(Color color) {
		return colorToString.get(color);
	}
	
	/**
	 * returns color given color name
	 * @param colorName string of color name
	 * @return Color object
	 */
	public Color getColor(String colorName) {
		return stringToColor.get(colorName);
	}
}

