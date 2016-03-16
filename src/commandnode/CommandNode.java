package commandnode;

import java.util.ArrayList;

import exception.SLogoException;
import model.ResourceLoader;
import model.SLogoCharacterState;

/**
 * SLogo's CommandNode, an abstract class representing any command (Turtle,
 * Query, Math, etc.) with any number of children
 *
 */
public abstract class CommandNode implements Node {

    private ArrayList<Node> myChildren;
    private int NUM_CHILDREN;

    public CommandNode(){
        myChildren = new ArrayList<>();
    }

    public ArrayList<Node> getChildren() {
        return myChildren;
    }

    public void addChild(Node child) {
        myChildren.add(child);
    }

    /**
     * @return Number of children required for command
     */
    public int numRequiredChildren(){
        return NUM_CHILDREN;
    }

    /**
     * @return Number of children currently added to command
     */
    public int numCurrentChildren(){
        return myChildren.size();
    }

    public void setNumChildren(int num){
        NUM_CHILDREN = num;
    }

    /**
     * @param child, index of desired child to evaluate
     * @param state, SLogoCharacterState to act upon if needed
     * @return evaluation of child using state
     * @throws SLogoException in subclasses if invalid command parameters
     */
    public double evaluateChild(int child, SLogoCharacterState state) throws SLogoException{
        if(myChildren.size() <= child){
            throw new SLogoException(new ResourceLoader().getString("InvalidCommandTokens"));
        }
        if(myChildren.get(child) == null){
        	throw new SLogoException(new ResourceLoader().getString("InvalidCommandTokens"));
        }
        return myChildren.get(child).evaluate(state);
    }

}