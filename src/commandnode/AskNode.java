package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Ask, Tell's node representation
 *
 */
public class AskNode extends TellNode {

    public double evaluate(SLogoCharacterState state) throws SLogoException {
        return super.evaluate(state);
    }

}