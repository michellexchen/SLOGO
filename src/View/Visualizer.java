package View;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import Model.DisplayData;
import Model.Position;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Visualizer implements Observer {
	private List<DisplayData> myDataList;
	
	private int myWidth;
	private int myHeight;
	
	//private DisplayData myDisplayData;
	public Visualizer(int width, int height) {
		//myDisplayData = data;
		myWidth = width;
		myHeight = height;
	}
	
	public Visualizer() {
		//myDisplayData = data;
		myWidth = 331;
		myHeight = 331;
	}


	@Override
	public void update(Observable observable, Object arg1) {
		// TODO 
		myDisplayData = (DisplayData) observable;
		myDisplayData.addLine(createLine(myDisplayData.getPosition()));
		//update rotation (angle)
		//update penDown
		//update penColor
		//update Image
	}
	
	public void setImage (ImageView image) {
		
		
	}

	public void setPenDown (boolean penDown) {
		
		
	}
	
	public void setPenColor (Color color) {
		
	}
	
	public void rotate (DisplayData displaydata) {
		
	}
	
	public Line createLine(Position position) {
		Line newLine = new Line();
		newLine.setStartX(position.xPrevious());
		newLine.setStartY(position.yPrevious());
		newLine.setEndX(position.xCurrent());
		newLine.setEndY(position.yCurrent());
		newLine.setFill(Color.BLACK);
		newLine.setStrokeWidth(1.0f);
		return newLine;
	}
	
	
	
	public int getWidth() {
		return myWidth;
	}

	public void setWidth(int width) {
		this.myWidth = width;
	}

	public int getHeight() {
		return myHeight;
	}

	public void setHeight(int height) {
		this.myHeight = height;
	}

	
}
