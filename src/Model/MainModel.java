package Model;

import Controller.SLogoException;
import Controller.TreeFactory;
import View.View;

public class MainModel implements Model {

	// Command that was read in by clicking Run button
	private String myCommand;
	private View myView;

	private TreeFactory myTreeFactory;
	private Workspace myWorkspace;

	public MainModel() {
		// TODO Auto-generated constructor stub

	}

	public MainModel(View view) {
		// TODO Auto-generated constructor stub
		myView = view;
	}

	public void initialize() {
		myWorkspace = new Workspace();
		myWorkspace.createTurtle();
		myTreeFactory = new TreeFactory();
	}

	public void readCommand(String command) throws SLogoException {
		setCommand(command);
		CommandTree myTree = myTreeFactory.makeTree(command);
		for(Character character : myWorkspace.getCharacterList()){
			myTree.traverse(character.getState());
		}
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
		// TODO Auto-generated method stub

	}

}