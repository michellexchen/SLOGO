package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;
import Model.Variable;

/**
 * SLogo's highest level in Node hierarchy
 * 
 *
 */

public interface Node {
	public abstract double evaluate(CharacterState state) throws SLogoException;

	public abstract int getNumChildren();

	public abstract String toString();

	default public void addVarParam(String string){
		
	}
	
	default public void giveToVarList(Variable var){
		
	}
}