package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class MakeUserInstructionNode extends TernaryVariableNode{

    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;
    private static final int INDEX_TWO = 2;
    
    public double evaluate(SLogoCharacterState state) throws SLogoException {
        String commandName = (((CustomCommandNode) getChildren().get(INDEX_ZERO)).getName());
        ListNode varNodeList = ((ListNode) getChildren().get(INDEX_ONE));
        ListNode commandList = ((ListNode) getChildren().get(INDEX_TWO));
        getWorkspace().createCustomCommand(commandName, varNodeList, commandList);
        return 1;
    }

}