package Model;

import java.io.IOException;

import javafx.collections.ObservableList;
import Model.*;
import View.*;
import Exception.*;
import Controller.*;
import deprecated_to_be_deleted.*;
import CommandNode.*;

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

	public void addWorkspace() throws SLogoException, IOException;

	public SLogoWorkspace getCurrentWorkspace();

	public ObservableList<SLogoDisplayData> getObservableDataList();

	public void loadLanguage();

}