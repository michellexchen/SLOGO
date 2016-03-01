package Model;

public class SetXYNode extends TurtleCommandNode {
	private final static int NUM_CHILDREN = 2;

	public double evaluate(CharacterState currentState) {
		double distance = calculateDistance(currentState.getXCoor(), currentState.getYCoor(), currentState);
		currentState.setXCoor(getChildren().get(0).evaluate(currentState));
		currentState.setYCoor(getChildren().get(1).evaluate(currentState));
		return distance;
	}

	private double calculateDistance(double x, double y, CharacterState state) {
		return Math.sqrt(Math.pow(getChildren().get(0).evaluate(state) - x, 2)
				- Math.pow(getChildren().get(1).evaluate(state) - y, 2));
	}

	public int getNumChildren() {
		return NUM_CHILDREN;
	}
}