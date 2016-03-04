package CommandNode;

public abstract class NullaryNode extends CommandNode{
	private int NUM_CHILDREN = 0;

	public NullaryNode() {
		setNumChildren(NUM_CHILDREN);
	}
}