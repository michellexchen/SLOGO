package commandnode;
import java.util.HashMap;

import model.SLogoCharacterState;
import model.SLogoVariable;
import model.SLogoWorkspace;

public class VariableNode extends NullaryNode{

	private String varName;
	private double myValue;
	private SLogoWorkspace myWorkspace;

	public VariableNode(String varName){
		setVarName(varName);
	}

	public void setVarName(String varName){
		this.varName = varName;
	}

	public String getVarName(){
		return varName;
	}

	public void setValue(double value){
		myValue = value;
	}

	public double evaluate(SLogoCharacterState state){
		return findVariable();
	}
	
	private double findVariable(){
		for(SLogoVariable variable: myWorkspace.getMyVarList()){
			if(variable.getName().equals(varName)) return variable.getValue();
		}
		return 0;
	}

	public SLogoWorkspace getWorkspace(){
		return myWorkspace;
	}

	public void setWorkspace(SLogoWorkspace ws){
		myWorkspace = ws;
	}
}