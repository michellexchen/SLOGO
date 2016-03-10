package view;

import java.util.Observable;

import javafx.scene.paint.Color;

public class SLogoPropertiesData extends Observable {
	private Color myColor; 
	
	public SLogoPropertiesData() {
		
	}
	
	public Color getColor() {
		return myColor;
	}
	

}
