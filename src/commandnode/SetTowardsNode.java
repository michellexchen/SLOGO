package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of TowardsXY, a Turtle Command
 */
public class SetTowardsNode extends TurnNode {

    private static final int NUM_CHILDREN = 2;
    private static final int MAX_DEGREES = 180;
    private static final int MIN_DEGREES = 0;

    public SetTowardsNode() throws SLogoException{
        setNumChildren(NUM_CHILDREN);
    }

    /**
     * @param state
     * Points turtle in the direction of point (x, y)
     * @return new direction (degrees)
     */

    public double calculateDir(SLogoCharacterState state) throws SLogoException {
        double diffX = evaluateChild(0, state) - state.getXCoor();
        double diffY = evaluateChild(1, state) - state.getYCoor();
        if (diffY < MIN_DEGREES) {
            return Math.toDegrees(Math.atan(diffX/diffY)) - MAX_DEGREES;
        }
        if (diffX == MIN_DEGREES && diffY == MIN_DEGREES) {
            return state.getDirection();
        }
        return Math.toDegrees(Math.atan(diffX/diffY));
    }

}