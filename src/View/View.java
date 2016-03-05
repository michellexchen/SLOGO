package View;

import java.util.Observer;
import Model.Workspace;

/**
 * View interface - A contract for Model
 * View in MVC Design Pattern: Model can only call methods
 * defined in this interface
 * 
 * @author Hunter
 *
 */
public interface View {
	
	public String getCommand();

	public Visualizer getCurrentVisualizer();
	
	public void switchVisualizer();
	
	public void updateDisplayData();
	
	public void updateCommandHistory();
	
	public void updateWorkspaces();

	public void updateVariables();
	
	public void setCurrentWorkspace(Workspace workspace);
	
	public Observer getObserver();
	
	public String getLanguage();

}
	