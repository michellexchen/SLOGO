package model;

import java.io.IOException;
import java.util.HashMap;
import exception.SLogoException;
import javafx.scene.paint.Color;

public class ColorLoader extends FileLoader{

	private final String colorDirectory = "resources/resources";
	private final String colorExtension = "Colors.properties";
	private HashMap<Integer, double[]> myRGBMap = new HashMap<Integer, double[]>();
	
	public Color getColor(int index) throws SLogoException{
		double[] rgb = myRGBMap.get(index);
		Color myColor = new Color(rgb[0], rgb[1], rgb[2], 1);
		setDirectory(colorDirectory);
		setExtension(colorExtension);
		try {
			super.load();
		} catch (IOException e) {
			throw new SLogoException("Encountered an error loading the Color file");
		}
		return myColor;
	}
	
	public void addRGB(int r, int g, int b){
		double[] rgb = {r, g, b};
		myRGBMap.put(myRGBMap.keySet().size(), rgb);
	}
	
}