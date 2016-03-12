package commandnode;

/**
 * Abstract class that serves as a superclass for nodes
 * requiring two children
 *
 */
public abstract class BinaryVariableCommand extends VariableCommand{

	private int NUM_CHILDREN = 2;

	public BinaryVariableCommand(){
		super();
		setNumChildren(NUM_CHILDREN);
	}

}