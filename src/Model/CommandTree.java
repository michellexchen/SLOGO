package Model;

public class CommandTree {
	
	private CommandNode root;
	
	public void traverse(CharacterState state){
		root.evaluate(state);
	}
	
	public void setRoot(CommandNode root){
		this.root = root;
	}
	
	public CommandNode getRoot(){
		return root;
	}

}