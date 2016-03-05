package Model;

import java.io.IOException;

import CommandNode.DisplayData;
import Exception.SLogoException;
import View.View;
import javafx.collections.ObservableList;

/**
 * Model interface - A contract for View Model in MVC Design Pattern: View can
 * only call methods defined in this interface
 * 
 * @author Hunter
 *
 */
public interface Model {

	public void readCommand(String command) throws SLogoException;

	public void setView(View myView);

	public void createBackend();

	public void AddWorkspace() throws SLogoException, IOException;

	public Workspace getCurrentWorkspace();

	public ObservableList<DisplayData> getObservableDataList();

	public void loadLanguage();

}