package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;
import commandnode.VariableNode;

/**
 * Node representation of Make command, to create SLogoVariables
 */
public class MakeNode extends BinaryVariableCommand {

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		VariableNode varNode = ((VariableNode) getChildren().get(0));
		String varName = varNode.getName();
		double varValue = evaluateChild(1, state);
		getWorkspace().createVariable(varName, varValue);
		return varValue;
	}

}