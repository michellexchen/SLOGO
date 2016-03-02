package View;

import java.util.Observer;
import Model.Workspace;

public interface View {
	
	public String getCommand();

	public Visualizer getVisualizer();
	
	public void updateDisplayData();
	
	public void updateCommandHistory();
	
	public void updateWorkspaces();

	public void updateVariables();
	
	public void setCurrentWorkspace(Workspace workspace);
	
	public Observer getObserver();

}
	