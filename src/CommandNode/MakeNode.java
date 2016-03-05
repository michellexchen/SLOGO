package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;
import Model.Variable;

public class MakeNode extends BinaryNode{

	private Variable makesVar;
	
	public void addVarParam(String string) {
//		CommandNode.super.addVarParam(string);
//		int value = Integer.parseInt(string);
		makesVar = new Variable();
		makesVar.setName(string);
	}
	
	
//	@Override
//	public void addVarParam(String string) {
////		CommandNode.super.addVarParam(string);
//		int value = Integer.parseInt(string);
//		makesVar.setValue(value);
//	}
	
	public void setVar(Variable var){
		this.makesVar = var;
	}

//	@Override
//	public String grabType() {
//		// TODO Auto-generated method stub
//		return ControlNode.super.grabType();
//	}

	@Override
	public double evaluate(CharacterState state) throws SLogoException {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
