package commandnode;

import java.util.List;

import exception.SLogoException;
import model.SLogoCharacterState;

public class ListNode extends CommandNode{

	private List<Node> myCommands;
	
	public ListNode(List<Node> myCommands){
		this.myCommands = myCommands;
	}
	
	public ListNode(String[] myParenthesisCommands){
		if(myParenthesisCommands.length > 1){
		for(int i = 2; i < myParenthesisCommands.length; i++){
			myParenthesisCommands[1] += myParenthesisCommands[i];
		}
		}	
	}
	
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		double evaluation = 0;
		for(Node node : myCommands){
			evaluation = node.evaluate(state);
		}
		return evaluation;
	}
	
	public List<Node> getCommands(){
		return myCommands;
	}

}