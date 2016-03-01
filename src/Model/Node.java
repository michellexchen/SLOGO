package Model;

/**
 * SLogo's highest level in Node hierarchy
 * 
 *
 */

public interface Node {
	public abstract double evaluate(CharacterState state);

	public abstract int getNumChildren();
}