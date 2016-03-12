package model;

import java.util.HashMap;
import commandnode.RGBColor;
import exception.SLogoException;
import javafx.scene.paint.Color;

/**
 * Class for loading default colors from resources file for commands
 * 
 * @author Adam Tache
 *
 */

public class ColorLoader extends FileLoader{

	private static final String COLOR_PATH = "resources/resources";
	private static final String COLOR_EXTENSION = "colors.resources";
	private HashMap<Integer, double[]> myRGBMap;
	private double DEFAULT_OPACITY = 1.0;

	public ColorLoader() throws SLogoException{
		myRGBMap = new HashMap<Integer, double[]>();
		load();
		loadDefaultColors();
	}

	public void load() throws SLogoException {
		setDirectory(COLOR_PATH);
		setExtension(COLOR_EXTENSION);
		super.load();
	}

	private void loadDefaultColors() throws SLogoException{
		int numDefaultColors = countLines(getFileName());
		for(int x=0; x<numDefaultColors; x++){
			String rgbString = getString(x+"");
			String[] rgbStrs = rgbString.split(",");
			double[] rgbVals = {convertRGB(Integer.parseInt(rgbStrs[0])), convertRGB(Integer.parseInt(rgbStrs[1])), convertRGB(Integer.parseInt(rgbStrs[2]))};
			myRGBMap.put(x, rgbVals);
		}
	}

	public Color getColor(int index) throws SLogoException{
		double[] rgb = myRGBMap.get(index);
		return new Color(rgb[0], rgb[1], rgb[2], DEFAULT_OPACITY);
	}

	public void addRGB(RGBColor color) throws SLogoException{
		double red = convertRGB(color.getR());
		double green = color.getG();
		double blue = color.getB();
		double[] rgb = {red, green, blue};
		myRGBMap.put(color.getIndex(), rgb);
	}

	private double convertRGB(int value){
		return value/255.0;
	}

}