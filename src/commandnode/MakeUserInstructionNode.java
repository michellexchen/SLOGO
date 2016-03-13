package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class MakeUserInstructionNode extends TernaryVariableNode{

    public double evaluate(SLogoCharacterState state) throws SLogoException {
        String commandName = (((CustomCommandNode) getChildren().get(0)).getName());
        ListNode varNodeList = ((ListNode) getChildren().get(1));
        ListNode commandList = ((ListNode) getChildren().get(2));
        getWorkspace().createCustomCommand(commandName, varNodeList, commandList);
        return 1;
    }

}