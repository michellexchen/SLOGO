package model;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import exception.SLogoException;

public class TurtleImageLoader extends FileLoader{
	
	private final String turtleDirectory = "resources/turtle_images/";
	private final String turtleExtension = "";
	private HashMap<Integer, String> myTurtleMap;
	
	public TurtleImageLoader() throws SLogoException{
		if(myTurtleMap == null){
			myTurtleMap = new HashMap<Integer, String>();
			load();
			loadDefaultTurtles();
		}
	}
	
	public void load() throws SLogoException {
		setDirectory(turtleDirectory);
		setExtension(turtleExtension);
		try {
			super.load();
		} catch (IOException e) {
			System.out.println(getFileName());
			throw new SLogoException("SLogo Turtle Images not found");
		}
	}
	
	private void loadDefaultTurtles() throws SLogoException{
		int numDefaultTurtles = countDefaultImages();
		for(int x=0; x<numDefaultTurtles; x++){
			String turtleFile = getString(x+"");
			myTurtleMap.put(x, turtleFile);
		}
	}
	
	private int countDefaultImages(){
		File turtle_images = new File(getFileName());
		int numDefaultTurtleImages = 0;
		for(File file: turtle_images.listFiles()){
			if(file.isFile()){
				numDefaultTurtleImages++;
			}
		}
		return numDefaultTurtleImages;
	}
	
}