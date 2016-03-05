package View;

import java.io.IOException;
import java.util.Observer;

import Exception.SLogoException;
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
	
	public void AddVisualizer() throws SLogoException, IOException;
	
	public void switchVisualizer(int index);
	
	public void updateDisplayData();
	
	public void updateCommandHistory();
	
	public void updateWorkspaces();

	public void updateVariables();
	
	public void setCurrentWorkspace(Workspace workspace);
	
	public Observer getObserver();
	
	public String getLanguage();

}
	