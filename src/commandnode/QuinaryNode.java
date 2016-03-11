package commandnode;

/**
 * @author Adam Tache
 * Node representation of command with five children
 */
public abstract class QuinaryNode extends CommandNode{
	
	private int NUM_CHILDREN = 4;

	public QuinaryNode() {
		setNumChildren(NUM_CHILDREN);
	}
	
}