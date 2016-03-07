package model;

import java.util.ArrayList;

public class Pen {
	
	private int[] penColor = new int[3];
	private int penSize;
	private boolean penDown;
	
	public void setPenColor(int index, ArrayList<int[]> colorList) {
		penColor = colorList.get(index);
	}
	
	public void setPenSize(int penSize) {
		this.penSize = penSize;
	}
	
	public int[] getPenColor() {
		return penColor;
	}
	
	public void setPenDown(boolean penDown) {
		this.penDown = penDown;
	}
	
}
