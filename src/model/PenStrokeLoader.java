package model;

import java.util.HashMap;

import exception.SLogoException;
import javafx.scene.paint.Color;

/**
 * SLogo's PenStroke loader class to access default pen stroke styles
 * 
 * @author Adam Tache, Michelle Chen
 *
 */

public class PenStrokeLoader extends FileLoader{

    private static final String STROKE_PATH = "resources/languages";
    private static final String STROKE_EXTENSION = "English.properties";

    private HashMap<Integer, String> myStrokeMap;
    
    public PenStrokeLoader() throws SLogoException {
    	myStrokeMap = new HashMap<>();
    	load();
    	loadDefaultStyles();
    }
    
    /**
     * loads stroke resource file
     */
    public void load() throws SLogoException {
        super.load(STROKE_PATH, STROKE_EXTENSION);
    }
    
    /**
     * Add default styles to style map with index
     */
    private void loadDefaultStyles() throws SLogoException {
        int numDefaultStyles = countLines(getFileName());
        for (int x = 0; x < numDefaultStyles; x++) {
        	String styleString = getString(x+"");
        	myStrokeMap.put(x, styleString);
        }
    }

    public HashMap<Integer, String> getStrokeMap() {
        return myStrokeMap;
    }

    public String getStroke(int index) {
        return myStrokeMap.get(index);
    }

}