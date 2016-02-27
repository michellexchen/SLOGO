package Model;

import java.util.ArrayList;

public abstract class CommandNode {
	
	private ArrayList<CommandNode> children = new ArrayList<CommandNode>();
	
	public ArrayList<CommandNode> getChildren(){
		return children;
	}
	
	public void addChild(CommandNode child){
		children.add(child);
	}
	
	public abstract void evaluate(CharacterState state);
	
	public abstract int getNumChildren();

}