package commandnode;

import java.util.ArrayList;

import exception.SLogoException;
import model.SLogoCharacterState;
import parser.InstructionLoader;

/**
 * SLogo's CommandNode, an abstract class representing any command (Turtle,
 * Query, Math, etc.) with any number of children
 *
 */

public abstract class CommandNode implements Node {
	
	private ArrayList<Node> children;
	private int NUM_CHILDREN;
	private InstructionLoader instructionsLoader;
	
	public CommandNode(){
		try {
			instructionsLoader = new InstructionLoader();
		} catch (SLogoException e) {
			new SLogoException("Instruction loader not loading.");
		}
		children = new ArrayList<Node>();
	}

	public ArrayList<Node> getChildren() {
		return children;
	}

	public void addChild(Node child) {
		children.add(child);
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
		return children.size();
	}
	
	public void setNumChildren(int num){
		NUM_CHILDREN = num;
	}
	
	public Node getChild(int child){
		return children.get(child);
	}
	
	/**
	 * @param child, index of desired child to evaluate
	 * @param state, SLogoCharacterState to act upon if needed
	 * @return evaluation of child using state
	 * @throws SLogoException in subclasses if invalid command parameters
	 */
	public double evaluateChild(int child, SLogoCharacterState state) throws SLogoException{
		return children.get(child).evaluate(state);
	}
	
	/**
	 * @param instruction, key for instructions resources file lookup
	 * @return instruction from resources file
	 */
	public String getInstruction(String instruction){
		return instructionsLoader.getString(instruction);
	}
	
}