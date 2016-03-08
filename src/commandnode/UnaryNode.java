package commandnode;

public abstract class UnaryNode extends CommandNode {

	private static final int NUM_CHILDREN = 1;

	public UnaryNode() {
		setNumChildren(NUM_CHILDREN);
	}

}