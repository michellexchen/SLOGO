package commandnode;
import java.util.List;

import exception.SLogoException;
import model.SLogoCharacterState;
import model.SLogoVariable;

/**
 * Node representation of For command, a Control Structure command using variable
 */
public class ForNode extends BinaryVariableNode {

    private final static int CONTROL_LIST_INDEX = 0;
    private final static int COMMAND_LIST_INDEX= 1;
    private final static int VARIABLE_INDEX = 0;
    private final static int START_INDEX = 1;
    private final static int END_INDEX = 2;
    private final static int INCREMENT_INDEX = 3;

    /**
     * Children are two ListNode children
     * Child one is a ListNode with four values, variable, start, end, and increment
     * Child two is a ListNode with commands run for each value specified in range by a for-loop
     * @return value of final command in list executed
     * Loop runs from start to end inclusive using increment between each run
     */
    public double evaluate(SLogoCharacterState state) throws SLogoException {
        ListNode controlList = ((ListNode) (getChildren().get(CONTROL_LIST_INDEX)));
        int start = Integer.parseInt(controlList.getInnerCommands().get(START_INDEX));
        int end = Integer.parseInt(controlList.getInnerCommands().get(END_INDEX));
        int increment = Integer.parseInt(controlList.getInnerCommands().get(INCREMENT_INDEX));
        SLogoVariable var = getWorkspace().createVariable(controlList.getInnerCommands().get(VARIABLE_INDEX), start);
        ListNode commandList = ((ListNode) (getChildren().get(COMMAND_LIST_INDEX)));
        List<String> innerCommands = commandList.getInnerCommands();
        double evaluation = 0;
        for(int x = start; x <= end; x += increment){
            List<Node> myRoots = getTreeFactory().createRoots(clone(innerCommands));
            getRootEvaluator().evaluateRoots(myRoots);
            var.setValue(x);
        }
        return evaluation;
    }

}