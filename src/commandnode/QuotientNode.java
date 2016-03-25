package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of Quotient, a Math command
 */
public class QuotientNode extends BinaryNode{

    public double evaluate(SLogoCharacterState state) throws SLogoException {
        if (evaluateChild(1, state) != 0) {
            return evaluateChild(0, state) / evaluateChild(1, state);   
        }
        return 0;
    }

}