package view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.SLogoPosition;

public class LineFactory {

	private int myPaneSize;
	private double myCoordinateShift;
	private int myDirectionFlip;
	private float myDefaultStroke;
	
	/**
	 * Constructor for LineFactory class, takes in default setting
	 * 
	 * @param paneSize
	 * @param dirFlip
	 * @param defaultStroke
	 */
	public LineFactory(double paneSize, int dirFlip, float defaultStroke) {
		paneSize = myPaneSize;
		dirFlip = myDirectionFlip;
		myCoordinateShift = (double) myPaneSize / 2;
		defaultStroke = myDefaultStroke;
	}

	
	/**
	 * Creates a line based on position and color
	 * @param position
	 * @param strokeColor
	 * @return newLine
	 */
	public Line makeLine(SLogoPosition position, Color strokeColor){
		Line newLine = new Line();
		newLine.setStartX(position.getPrevX() + myCoordinateShift);
		newLine.setStartY(myDirectionFlip * position.getPrevY() + myCoordinateShift);
		newLine.setEndX(position.getX() + myCoordinateShift);
		newLine.setEndY(myDirectionFlip * position.getY() + myCoordinateShift);
		newLine.setStrokeWidth(myDefaultStroke);
		newLine.setStroke(strokeColor);
		return newLine;
	}
}
