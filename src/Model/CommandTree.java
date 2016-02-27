package Model;

public class CommandTree {
	
	private CommandNode root;
	
	public void traverse(){
		root.evaluate();
	}
	
	public void setRoot(CommandNode root){
		this.root = root;
	}
	
	public CommandNode getRoot(){
		return root;
	}

}