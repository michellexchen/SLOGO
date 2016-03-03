package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;
import Model.Variable;
import Model.Workspace;

/**
 * SLogo's highest level in Node hierarchy
 * 
 *
 */

public interface Node {
	boolean setvalue = false;
	
	public abstract double evaluate(CharacterState state) throws SLogoException;
	
	default public double evaluate(CharacterState state, Workspace workspace) {
		return 0;
	};

	public abstract int getNumChildren();

	public abstract String toString();

	default public void addVarParam(String string) {}

	default public void giveToVarList(Variable var) {}
	
	default public void setTrueToSetVar(boolean setvalue) {
		//this.setvalue = setvalue;
	}
	
	default public boolean getVarsValue(){
		return setvalue;
	}
}