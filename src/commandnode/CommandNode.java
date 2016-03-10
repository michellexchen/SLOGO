package commandnode;

import java.util.ArrayList;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * SLogo's CommandNode, an abstract class representing any command (Turtle,
 * Query, Math, etc.)
 *
 */

public abstract class CommandNode implements Node {
	
	private static final int PANE_SIZE = 450;
	private static final int PADDING = 40;
	private ArrayList<Node> children = new ArrayList<Node>();
	private int NUM_CHILDREN;
	
	public double transformXCoor(double myCoor) {
		 return (PANE_SIZE/2 + myCoor);
	}
	
	public double transformYCoor(double myCoor) {
		 return (PANE_SIZE/2 - myCoor);
	}

	public double boundCoor(double myCoor) {
		 if (myCoor > PANE_SIZE - PADDING) {
			 myCoor = PANE_SIZE - PADDING;
		 }
		 else if(myCoor < PADDING) {
			 myCoor = PADDING;
		 }
		 	return myCoor;
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
	
}