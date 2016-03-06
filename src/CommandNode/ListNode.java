package CommandNode;

import java.util.List;
import Model.*;
import View.*;
import Exception.*;
import Controller.*;
import deprecated_to_be_deleted.*;
import CommandNode.*;

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