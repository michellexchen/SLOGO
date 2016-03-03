package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;
import Model.Variable;

public class MakeNode extends CommandNode {

	private Variable makesVar;
	
	public MakeNode() {

	}
	
	public void grabVar(){
		System.out.println("yo");
	}
	
	@Override
	public double evaluate(CharacterState state) throws SLogoException {
		/*
		 * evaluate runs through the tree which holds the value that will be returned that should be equal to 
		 * our value after this point
		 */
		//makesVar.setValue(); --> the node that gets executed before this should return a value that we
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
	
	

}
