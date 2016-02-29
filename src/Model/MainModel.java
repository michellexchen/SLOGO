package Model;

import Controller.TreeFactory;
import View.View;

public class MainModel implements Model {

	//TO BE REMOVED
	private final int VIEW_WIDTH = 1000;
	private final int VIEW_HEIGHT = 750;
	
	//Command that was read in by clicking Run button
	private String myCommand;
	private View myView;
	
	public MainModel() {
		// TODO Auto-generated constructor stub

	}
	
	public MainModel(View view) {
		// TODO Auto-generated constructor stub
		myView = view;
	}
	
	
	public void createBackend() {
		Turtle ogTurt = new Turtle("OG", VIEW_WIDTH / 2, VIEW_HEIGHT / 2, true, 0, false);
		//myRoot.getChildren().add(ogTurt.getTurtle());
		TreeFactory tf = new TreeFactory();
		String command = "forward 50";
		CommandTree myTree = tf.makeTree(command);
		myTree.traverse(ogTurt.getState());
	}


	@Override
	public void readCommand(String command) {
		// TODO Auto-generated method stub
		setCommand(command);
		
		//TEST
		System.out.println(getCommand());
	}

	//public void 
	
	
	/**
	 * @return the myCommand
	 */
	public String getCommand() {
		return myCommand;
	}

	/**
	 * @param myCommand the myCommand to set
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
	 * @param myView the View to set
	 */
	public void setView(View myView) {
		this.myView = myView;
	}


}