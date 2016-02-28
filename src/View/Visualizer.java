package View;

import java.util.Observable;
import java.util.Observer;

import Model.DisplayData;

public class Visualizer implements Observer {
	private DisplayData myDisplayData;
	public Visualizer(DisplayData data) {
		myDisplayData = data;
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
