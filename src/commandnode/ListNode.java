package commandnode;

import java.util.ArrayList;
import java.util.List;

import exception.SLogoException;
import model.SLogoCharacterState;
import model.SLogoWorkspace;
import parser.RootEvaluator;
import parser.TreeFactory;

/**
 * @author Adam
 * Node representation of a List of commands inside of a [ ]
 * Extends CommandNode and can have unlimited commands inside
 */
public class ListNode extends CommandNode{

    private List<Node> myCommands;
    private List<String> myInnerCommands;
    private TreeFactory myTreeFactory;
    private RootEvaluator myRootEvaluator;

    public ListNode(SLogoWorkspace ws) throws SLogoException{
    	myTreeFactory = new TreeFactory(ws);
    	myRootEvaluator = new RootEvaluator(ws);
    }

    /**
     * @return Evaluation of last command
     */
    public double evaluate(SLogoCharacterState state) throws SLogoException {
        List<Node> myRoots = myTreeFactory.createRoots(clone(myInnerCommands));
        return myRootEvaluator.evaluateRoots(myRoots);
    }

    public List<Node> getCommands(){
        return myCommands;
    }

    public void setInnerCommands(List<String> myInnerCommands){
        this.myInnerCommands = myInnerCommands;
    }

    public List<String> getInnerCommands(){
        return myInnerCommands;
    }
    
    private List<String> clone(List<String> list){
        List<String> copy = new ArrayList<>();
        copy.addAll(list);
        return copy;
    }

}