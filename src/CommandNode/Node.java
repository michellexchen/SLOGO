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
	
	static final String NODE = "normal";
	
	public abstract double evaluate(CharacterState state) throws SLogoException;
	
	default public double evaluate(CharacterState state, CommandTree tree) throws SLogoException {
		return 0;
	};

	public abstract void addChild(Node node);
	
	public abstract int getNumChildren();

	public abstract String toString();
	
}