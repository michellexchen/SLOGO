package model;

import java.io.File;
import java.util.HashMap;

import exception.SLogoException;

/**
 * Class that allows access to Turtle images for commands
 * 
 * @author Adam Tache
 *
 */
public class TurtleImageLoader extends FileLoader{

    private final String turtleDirectory = "resources/resources";
    private final String turtleExtension = "TurtleImages.resources";
    private final String IMAGE_PATH = "resources/turtle_images/";
    private HashMap<Integer, String> myTurtleMap;

    public TurtleImageLoader() throws SLogoException{
        myTurtleMap = new HashMap<Integer, String>();
        load();
        loadDefaultTurtles();
    }

    public void load() throws SLogoException {
        super.load(turtleDirectory, turtleExtension);
    }

    private void loadDefaultTurtles() throws SLogoException{
        int numDefaultTurtles = countDefaultImages();
        for(int x=0; x<numDefaultTurtles; x++){
            String turtleFile = getString(x+"");
            myTurtleMap.put(x, turtleFile);
        }
    }

    private int countDefaultImages(){
        File turtle_images = new File(IMAGE_PATH);
        int numDefaultTurtles = 0;
        for(File file: turtle_images.listFiles()){
            if(file.isFile())
                numDefaultTurtles++;
        }
        return numDefaultTurtles;
    }

}