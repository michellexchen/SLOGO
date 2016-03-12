package model;

import java.io.IOException;

import exception.SLogoException;
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

	public void addWorkspace() throws SLogoException, IOException;

	public SLogoWorkspace getCurrentWorkspace();

	public ObservableList<SLogoDisplayData> getObservableDataList();

	public void loadLanguage();
	
	public void switchWorkspace(int index) throws SLogoException;
}