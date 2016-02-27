package Model;

public class LeftNode extends TurnNode{
	private final static int NUM_CHILDREN = 1;
	
	public double calculateDir(double x, double y, CharacterState state) {
		return getChildren().get(0).evaluate(state);
	}
	
	public int getNumChildren() {
		return NUM_CHILDREN;
	}

}