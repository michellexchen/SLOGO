package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * SLogo's highest level in Node hierarchy
 * Includes CommandNodes and NumericNode
 *
 */

public interface Node {
    public double evaluate(SLogoCharacterState state) throws SLogoException;
    public int numRequiredChildren();
    public int numCurrentChildren();
    public String toString();
    public void addChild(Node child);

}