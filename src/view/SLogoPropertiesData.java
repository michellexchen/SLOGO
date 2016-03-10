package view;

import java.util.Observable;

import javafx.scene.paint.Color;
import model.SLogoPen;

public class SLogoPropertiesData extends Observable {

	//keeps all properties for view, updates using observable
	//
	
	private SLogoPen myPen;
	private Color myPaneColor; 
	
	public SLogoPropertiesData() {
		
	}
	
	public Color getPaneColor() {
		return myPaneColor;
	}

	/**
	 * @return the myPaneColor
	 */
	public Color getMyPaneColor() {
		return myPaneColor;
	}

	/**
	 * @param myPaneColor the myPaneColor to set
	 */
	public void setMyPaneColor(Color myPaneColor) {
		this.myPaneColor = myPaneColor;
	}
	 
	

}
