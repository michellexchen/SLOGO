package commandnode;
import model.SLogoCharacterState;

public class VariableNode extends NullaryNode{

	private String varName;

	public VariableNode(String varName){
		setVarName(varName);
	}

	public void setVarName(String varName){
		this.varName = varName;
	}
	
	public String getVarName(){
		return varName;
	}
	
	public double evaluate(SLogoCharacterState state){
		return -1;
	}
	
}