package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;
import model.SLogoVariable;

public class MakeNode extends BinaryNode {

	private SLogoVariable makesVar;

	public MakeNode() {
		makesVar = new SLogoVariable();
	}

	public void addVarParam(String string) {
		// CommandNode.super.addVarParam(string);
		// int value = Integer.parseInt(string);
		makesVar.setName(string);
	}

	@Override
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		// TODO Auto-generated method stub
//		double charAsciiRepresentation = getChildren().get(0).evaluate(state);
//		Context algorithm = Context()
		String variableName = getChildren().get(0).
		makesVar.setName(variableName);
		return 0;
	}

}
