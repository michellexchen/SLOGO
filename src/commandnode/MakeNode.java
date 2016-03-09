package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;
import model.SLogoWorkspace;
import commandnode.VariableNode;

public class MakeNode extends BinaryNode {

	private SLogoWorkspace ws;
	
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		String varName = ((VariableNode) getChildren().get(0)).getVarName();
		double varValue = evaluateChild(1, state);
		((VariableNode) getChildren().get(0)).setValue(varValue);
		ws.getMyVarMap().put(varName, varValue);
		return varValue;
	}
	
	public void setWorkspace(SLogoWorkspace ws){
		this.ws = ws;
	}

}