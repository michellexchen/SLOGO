package Model;

import java.util.ArrayList;
import java.util.List;

import Controller.SLogoException;
import Controller.TreeFactory;
import View.View;

public class MainModel implements Model {

	private String myCommand;
	private View myView;
	private Workspace myCurrentWorkspace;
	
	private List<Workspace> myWorkspaces;

	public MainModel() {
	}

	public MainModel(View view) {
		myView = view;
	}

	public void initialize() {	
		myWorkspaces = new ArrayList<Workspace>();
		createNewWorkspace();
		
	}

	public void readCommand(String command) throws SLogoException {
		setCommand(command);
		myCurrentWorkspace.readCommand(command);
	}

	/**
	 * @return the myCommand
	 */
	public String getCommand() {
		return myCommand;
	}

	/**
	 * @param myCommand
	 *            the myCommand to set
	 */
	public void setCommand(String myCommand) {
		this.myCommand = myCommand;
	}

	/**
	 * @return the myView
	 */
	public View getView() {
		return myView;
	}

	/**
	 * @param myView
	 *            the View to set
	 */
	public void setView(View myView) {
		this.myView = myView;
	}

	@Override
	public void createBackend() {

	}

	@Override
	public List<DisplayData> getDisplayDataList() {
		return myCurrentWorkspace.getDataList();
	}

	@Override
	public void createNewWorkspace() {
		// TODO Auto-generated method stub
		Workspace myWorkspace = new Workspace(getView());
		myWorkspace.initialize();
		getWorkspaces().add(myWorkspace);
		setCurrentWorkspace(myWorkspace);
	}


	/**
	 * @return the myCurrentWorkspace
	 */
	public Workspace getCurrentWorkspace() {
		return myCurrentWorkspace;
	}

	/**
	 * @param myCurrentWorkspace the myCurrentWorkspace to set
	 */
	public void setCurrentWorkspace(Workspace myCurrentWorkspace) {
		this.myCurrentWorkspace = myCurrentWorkspace;
	}

	/**
	 * @return the myWorkspaces
	 */
	public List<Workspace> getWorkspaces() {
		return myWorkspaces;
	}

	/**
	 * @param myWorkspaces the myWorkspaces to set
	 */
	public void setWorkspaces(List<Workspace> myWorkspaces) {
		this.myWorkspaces = myWorkspaces;
	}
}