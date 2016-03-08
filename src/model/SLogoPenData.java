package model;

import java.util.ArrayList;

public class SLogoPenData {
	
	//Why is this an int array of 3 elements?
	//Are there only 3 colors available? Why?
	//TODO: Fix this magic number 3
	private int[] myPenColor = new int[3];
	
	private int myPenThickness;
	private boolean myPenDown;
	private double[] myStrokeStyle;
	
	public void setPenColor(int index, ArrayList<int[]> colorList) {
		myPenColor = colorList.get(index);
	}
	
	public void setPenThickness (int thickness) {
		this.myPenThickness = thickness;
	}
	
	public int[] getPenColor() {
		return myPenColor;
	}
	
	public void setPenDown(boolean penDown) {
		this.myPenDown = penDown;
	}
	
	
	//Also need to add pen style
	
	//http://stackoverflow.com/questions/12786363/javafx-2-x-how-to-draw-dashed-or-dotted-lines
//	Line line1 = new Line(20, 40, 270, 40);
//	line1.getStrokeDashArray().addAll(25d, 20d, 5d, 20d);
//
//	Line line2 = new Line(20, 60, 270, 60);
//	line2.getStrokeDashArray().addAll(50d, 40d);
//
//	Line line3 = new Line(20, 80, 270, 80);
//	line3.getStrokeDashArray().addAll(25d, 10d);
//
//	Line line4 = new Line(20, 100, 270, 100);
//	line4.getStrokeDashArray().addAll(2d);
//
//	Line line5 = new Line(20, 120, 270, 120);
//	line5.getStrokeDashArray().addAll(2d, 21d);
	
	//TODO: Design decision: can this be in this class
	//Or should Pen class have a flag so that createLine should know
	//which stroke style to create?
	
	//I think Pen class should have a flag
	
	

	/**
	 * @return the myStrokeStyle
	 */
	public double[] getPenStrokeStyle() {
		return myStrokeStyle;
	}

	/**
	 * @param myStrokeStyle the myStrokeStyle to set
	 */
	public void setPenStrokeStyle(double[] myStrokeStyle) {
		this.myStrokeStyle = myStrokeStyle;
	}
	
}
