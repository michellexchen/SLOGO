package commandnode;

import java.util.ArrayList;

import exception.SLogoException;
import model.SLogoCharacterState;
import parser.InstructionLoader;

/**
 * SLogo's CommandNode, an abstract class representing any command (Turtle,
 * Query, Math, etc.)
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
			try {
				throw new SLogoException("Instructions loader not found");
			} catch (SLogoException e1) {
				e1.printStackTrace();
			}
		}
		children = new ArrayList<Node>();
	}

	public ArrayList<Node> getChildren() {
		return children;
	}

	public void addChild(Node child) {
		children.add(child);
	}
	
	public int numRequiredChildren(){
		return NUM_CHILDREN;
	}
	public int numCurrentChildren(){
		return children.size();
	}
	
	public void setNumChildren(int num){
		NUM_CHILDREN = num;
	}
	
	public Node getChild(int child){
		return children.get(child);
	}
	
	public double evaluateChild(int child, SLogoCharacterState state) throws SLogoException{
		return children.get(child).evaluate(state);
	}
	
	public String getInstruction(String instruction){
		return instructionsLoader.getString(instruction);
	}
	
}