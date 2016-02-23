package Model;

public class TurtleCommand extends Node {

	Node thisCommandsTree;
	private static final String NODETYPE = "command";

	public TurtleCommand(int noOfArgs, String command) {
		super(NODETYPE, command, noOfArgs);
	}

	@Override
	public void evaluate(String myCommand) {
		// TODO Auto-generated method stub

	}

	@Override
	public void intermediateCanvasUpdate() {
		// TODO Auto-generated method stub

	}

}
