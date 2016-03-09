package commandnode;

import java.util.List;

import exception.SLogoException;
import model.SLogoCharacterState;
import model.SLogoWorkspace;

public class ToNode extends TernaryNode {
	
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		List<Node> myVariables = ((ListNode) (getChildren().get(0))).getCommands();
		List<Node> myCommands = ((ListNode) (getChildren().get(1))).getCommands();
		if(myVariables.size() != myCommands.size()){
			return 0;
		}
		SLogoWorkspace ws = ((VariableNode)myVariables.get(0)).getWorkspace();
		for(int x=0; x<myCommands.size(); x++){
			ws.getMyVarMap().put(((VariableNode) (myVariables.get(x))).getVarName(), (Double)myCommands.get(x).evaluate(state));
		}
		return 1;
	}

}