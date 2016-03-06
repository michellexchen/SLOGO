package commandnode;
import exception.SLogoException;
import model.SLogoCharacterState;

public class AddNode extends BinaryNode {

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return getChildren().get(0).evaluate(state) + getChildren().get(1).evaluate(state);
	}

}