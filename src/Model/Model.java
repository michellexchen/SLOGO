package Model;

import java.util.List;

import Controller.SLogoException;
import View.View;
import javafx.collections.ObservableList;

public interface Model {

	public void readCommand(String command) throws SLogoException;

	public void setView(View myView);

	public void createBackend();

	
	//Need this implemented 
	public ObservableList<DisplayData> getDisplayDataList();
	
	
	public void createNewWorkspace();
	
	public Workspace getCurrentWorkspace();
	
	
}