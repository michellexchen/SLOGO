package commandNode;

import exception.SLogoException;
import model.CharacterState;
import model.Variable;
import model.Workspace;

public class MakeNode extends CommandNode implements ControlNode {

	private Variable makesVar;
	
	public MakeNode() {

	}
	
	@Override
	public double evaluate(CharacterState state, CommandTree tree)  {
		/*
		 * evaluate runs through the tree which holds the value that will be returned that should be equal to 
		 * our value after this point
		 */
		//makesVar.setValue(); --> the node that gets executed before this should return a value that we
		tree.setMyVars(makesVar);
		System.out.println("entered right loop for make node");
		return 0;
	}
	
	@Override
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

	@Override
	public String grabType() {
		// TODO Auto-generated method stub
		return ControlNode.super.grabType();
	}

	@Override
	public double evaluate(CharacterState state) throws SLogoException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
