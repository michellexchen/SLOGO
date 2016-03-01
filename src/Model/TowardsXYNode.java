package Model;

public class TowardsXYNode extends TurnNode {
	private final static int NUM_CHILDREN = 2;

	public double calculateDir(double x, double y, CharacterState state) {
		double diffX = getChildren().get(0).evaluate(state) - x;
		double diffY = getChildren().get(1).evaluate(state) - y;
		return Math.atan(diffX / diffY);
	}

	public int getNumChildren() {
		return NUM_CHILDREN;
	}
}