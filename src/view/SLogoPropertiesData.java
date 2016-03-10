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

	public void addPen(SLogoPen pen) {
		myPen = pen;
	}

}
