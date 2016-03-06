package commandnode;

import java.util.List;

import exception.SLogoException;
import model.SLogoCharacterState;

public class ListNode extends CommandNode{

	private List<Node> myNodes;
	
	public ListNode(List<Node> myNodes){
		this.myNodes = myNodes;
	}
	
	public List<Node> getMyNodes(){
		return myNodes;
	}
	
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		double evaluation = 0;
		for(Node node : myNodes){
			evaluation = node.evaluate(state);
		}
		return evaluation;
	}

}