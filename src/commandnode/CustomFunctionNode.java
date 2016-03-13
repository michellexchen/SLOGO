package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;
import model.SLogoCustomCommand;

public class CustomFunctionNode extends CommandNode{

	private SLogoCustomCommand myCommand;
	
	public CustomFunctionNode(SLogoCustomCommand myCommand){
		this.myCommand = myCommand;
		setNumChildren(myCommand.getVariableNames().size());
	}
	
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return myCommand.getMyCommands().evaluate(state);
	}

}