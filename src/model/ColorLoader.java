package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import exception.SLogoException;
import javafx.scene.paint.Color;

public class ColorLoader extends FileLoader{

	private final String colorDirectory = "resources/resources";
	private final String colorExtension = "Colors.resources";
	private HashMap<Integer, double[]> myRGBMap;
	private double DEFAULT_OPACITY = 1.0;

	public ColorLoader() throws SLogoException{
		if(myRGBMap == null){
			myRGBMap = new HashMap<Integer, double[]>();
			load();
			loadDefaultColors();
		}
	}
	
	public void load() throws SLogoException {
		setDirectory(colorDirectory);
		setExtension(colorExtension);
		try {
			super.load();
		} catch (IOException e) {
			throw new SLogoException("SLogo resources file not found");
		}
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

	public int countLines(String filename) throws SLogoException{
		LineNumberReader reader;
		try {
			reader = new LineNumberReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			throw new SLogoException("SLogo resources file not found");
		}
		int numLines = 0;
		String nextLine = "";
		try {
			while ((nextLine = reader.readLine()) != null) {}
		} catch (IOException e) {
			throw new SLogoException("SLogo resources file not found");
		}
		numLines = reader.getLineNumber(); 
		return numLines;
	}

	public Color getColor(int index) throws SLogoException{
		double[] rgb = myRGBMap.get(index);
		return new Color(rgb[0], rgb[1], rgb[2], DEFAULT_OPACITY);
	}

	public void addRGB(int r, int g, int b) throws SLogoException{
		double red = convertRGB(r);
		double green = convertRGB(g);
		double blue = convertRGB(b);
		if (red < 0 || red > 1) {
            throw new SLogoException("Color's red value (" + red + ") must be in the range 0.0-1.0");
        }
        if (green < 0 || green > 1) {
            throw new SLogoException("Color's green value (" + green + ") must be in the range 0.0-1.0");
        }
        if (blue < 0 || blue > 1) {
            throw new SLogoException("Color's blue value (" + blue + ") must be in the range 0.0-1.0");
        }
        double[] rgb = {red, green, blue};
		myRGBMap.put(myRGBMap.keySet().size(), rgb);
	}
	
	public double convertRGB(int value){
		return value/255;
	}

}