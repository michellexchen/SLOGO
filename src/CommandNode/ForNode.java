package CommandNode;
import Exception.SLogoException;
import Model.CharacterState;

public class ForNode extends CommandNode {

	private int NUM_CHILDREN = 2;

	public ForNode() {
		setNumChildren(NUM_CHILDREN);
	}

	public double evaluate(CharacterState state) throws SLogoException {
		// TODO: Error checking
		EnclosureNode controlBracket = (EnclosureNode) getChildren().get(0);
		String[] arr = controlBracket.getBracketContent().split("\\s+");
		int start = Integer.parseInt(arr[0]);
		int end = Integer.parseInt(arr[1]);
		int increment = Integer.parseInt(arr[2]);
		double evaluation = 0;
		for(int x=start; x<=end; x += increment){
			evaluation += getChildren().get(1).evaluate(state);
		}
		return evaluation;
	}

}