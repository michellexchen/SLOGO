package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;
import model.SLogoVariable;

public class MakeNode extends BinaryNode{

	private SLogoVariable makesVar;
	
	public void addVarParam(String string) {
//		CommandNode.super.addVarParam(string);
//		int value = Integer.parseInt(string);
		makesVar = new SLogoVariable();
		makesVar.setName(string);
	}
	
	
//	@Override
//	public void addVarParam(String string) {
////		CommandNode.super.addVarParam(string);
//		int value = Integer.parseInt(string);
//		makesVar.setValue(value);
//	}
	
	public void setVar(SLogoVariable var){
		this.makesVar = var;
	}

//	@Override
//	public String grabType() {
//		// TODO Auto-generated method stub
//		return ControlNode.super.grabType();
//	}

	@Override
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
