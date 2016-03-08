package commandnode;

public abstract class TernaryNode extends CommandNode{
	
	private int NUM_CHILDREN = 3;

	public TernaryNode() {
		setNumChildren(NUM_CHILDREN);
	}
}