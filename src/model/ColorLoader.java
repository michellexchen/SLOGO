package model;

import java.util.HashMap;
import commandnode.RGBColor;
import exception.SLogoException;
import javafx.scene.paint.Color;

/**
 * Class for loading default colors from resources file for commands
 * 
 * @author Adam Tache
 */

public class ColorLoader extends FileLoader{

    private final String colorDirectory = "resources/resources";
    private final String colorExtension = "Colors.resources";
    private HashMap<Integer, Color> myRGBMap;

    private static final double DEFAULT_OPACITY = 1.0;

    public ColorLoader() throws SLogoException{
        myRGBMap = new HashMap<Integer, Color>();
        load();
        loadDefaultColors();
    }

    /**
     * loads color resource file
     */
    public void load() throws SLogoException {
        super.load(colorDirectory, colorExtension);
    }

    /**
     * Add default colors to color map with index
     */
    private void loadDefaultColors() throws SLogoException{
        int numDefaultColors = countLines(getFileName());
        for(int x=0; x<numDefaultColors; x++){
            String rgbString = getString(x+"");
            String[] rgbStrs = rgbString.split(",");
            double[] rgb = {convertRGB(Integer.parseInt(rgbStrs[0])), convertRGB(Integer.parseInt(rgbStrs[1])), convertRGB(Integer.parseInt(rgbStrs[2]))};
            myRGBMap.put(x, new Color(rgb[0], rgb[1], rgb[2], DEFAULT_OPACITY));
        }
    }

    public Color getColor(int index) throws SLogoException{
        return myRGBMap.get(index);
    }

    public Color createColor(int r, int g, int b){
        return new Color(convertRGB(r), convertRGB(g), convertRGB(b), DEFAULT_OPACITY);
    }

    public void addRGB(RGBColor color) throws SLogoException{
        myRGBMap.put(color.getIndex(), createColor(color.getR(), color.getG(), color.getB()));
    }

    /**
     * @param value: RGB value (0:255)
     * Node representation of Repeat command, a Control Structure command using variable
     * @return Normalized RGB value (0:1)
     */

    private double convertRGB(int value){
        return value/255.0;
    }

    public HashMap<Integer, Color> getMap(){
        return myRGBMap;
    }

}