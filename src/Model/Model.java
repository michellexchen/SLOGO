package Model;

import View.View;

public interface Model {

	public void readCommand(String command);

	public void setView(View myView);

	public void createBackend();

	
}
