package view;

import java.util.Observable;

import javafx.scene.paint.Color;

public class SLogoPropertiesData extends Observable {

	//keeps all properties for view, updates using observable
	
	private Color myPaneColor; 

	/**
	 * @return the myPaneColor
	 */
	public Color getPaneColor() {
		return myPaneColor;
	}

	/**
	 * @param myPaneColor the myPaneColor to set
	 */
	public void setPaneColor(Color myPaneColor) {
		this.myPaneColor = myPaneColor;
		setChanged();
	}

}