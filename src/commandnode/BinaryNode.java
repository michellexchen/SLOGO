package commandnode;

public abstract class BinaryNode extends CommandNode {

	private int NUM_CHILDREN = 2;

	public BinaryNode() {
		setNumChildren(NUM_CHILDREN);
	}

}