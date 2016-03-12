package commandnode;

public abstract class BinaryVariableCommand extends VariableCommand{

	private int NUM_CHILDREN = 2;

	public BinaryVariableCommand(){
		super();
		setNumChildren(NUM_CHILDREN);
	}

}