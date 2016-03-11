package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;
import model.SLogoVariable;
import model.SLogoWorkspace;
import commandnode.VariableNode;

public class MakeNode extends BinaryNode {

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		VariableNode varNode = ((VariableNode) getChildren().get(0));
		String varName = varNode.getVarName();
		double varValue = evaluateChild(1, state);
		SLogoWorkspace ws = varNode.getWorkspace();
		SLogoVariable var = new SLogoVariable();
		var.setName(varName);
		var.setValue(varValue);
		ws.getMyVarList().add(var);
		return varValue;
	}

}