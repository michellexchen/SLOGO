package Model;

import Controller.TreeFactory;

public class MainModel implements Model {

	//TO BE REMOVED
	private final int VIEW_WIDTH = 1000;
	private final int VIEW_HEIGHT = 750;
	
	//Command that was read in by clicking Run button
	private String myCommand;
	
	public MainModel() {
		// TODO Auto-generated constructor stub
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
		
	}
}