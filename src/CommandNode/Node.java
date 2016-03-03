package commandNode;

import exception.SLogoException;
import model.CharacterState;
import model.Variable;
import model.Workspace;

/**
 * SLogo's highest level in Node hierarchy
 * 
 *
 */

public interface Node {
	boolean setvalue = false;
	
	static final String NODE = "normal";
	
	public abstract double evaluate(CharacterState state) throws SLogoException;
	
	default public double evaluate(CharacterState state, CommandTree tree) throws SLogoException {
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
	
	default public String grabType(){
		return NODE;
	}
}