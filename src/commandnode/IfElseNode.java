package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;


/**
 * Node representation of IfElse command
 */
public class IfElseNode extends TernaryNode {

    private static final int FIRST = 1;
    private static final int SECOND = 2;
    
    /**
     * @param state
     * Checks evaluation of 0th child (NumericNode) for 0
     * If not 0, runs true commands, represented as child 1 as a ListCommand
     * Otherwise runs false commands, represented as child 2 as a ListCommand
     * @return value of final command executed or 0 if none executed
     */
    public double evaluate(SLogoCharacterState state) throws SLogoException {
        return evaluateChild(0, state) != 0 ? evaluateChild(FIRST, state) : 
                                                    evaluateChild(SECOND, state);
    }

}