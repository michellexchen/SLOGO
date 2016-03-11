package commandnode;

public abstract class QuinaryNode extends CommandNode{
	
	private int NUM_CHILDREN = 4;

	public QuinaryNode() {
		setNumChildren(NUM_CHILDREN);
	}
	
}