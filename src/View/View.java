package View;

import java.io.IOException;
import java.util.Observer;

import Controller.SLogoException;
import Model.Workspace;

public interface View {
	
	public String getCommand();

	public Visualizer getVisualizer();
	
	public void updateDisplayData();
	
	public void updateCommandHistory();
	
	public void updateWorkspaces();
	
	public void setCurrentWorkspace(Workspace workspace);
	
	public Observer getObserver();
}
	