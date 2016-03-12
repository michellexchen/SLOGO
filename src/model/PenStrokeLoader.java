package model;

import java.util.HashMap;

/**
 * SLogo's PenStroke loader class to access default pen stroke styles
 * 
 * @author Adam Tache
 *
 */

public class PenStrokeLoader extends FileLoader{

    private static final String STROKE_PATH = "resources/resources";
    private static final String STROKE_EXTENSION = "penstrokes.properties";

    public HashMap<Integer, String> getStrokeMap() {
        return null;
    }

    public String getStroke(int index) {
        return null;
    }

}