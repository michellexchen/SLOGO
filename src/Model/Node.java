package Model;

import Controller.SLogoException;

/**
 * SLogo's highest level in Node hierarchy
 * 
 *
 */

public interface Node {
	public abstract double evaluate(CharacterState state) throws SLogoException;
	public abstract int getNumChildren();
	public abstract String toString();
}