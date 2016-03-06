package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * SLogo's highest level in Node hierarchy
 * 
 *
 */

public interface Node {
	
	public abstract double evaluate(SLogoCharacterState state) throws SLogoException;
	public abstract int getNumChildren();
	public abstract String toString();
	
}