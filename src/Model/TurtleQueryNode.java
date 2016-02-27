package Model;

public abstract class TurtleQueryNode extends CommandNode {

	public void evaluate(){
		QueryEvaluate();
	}
	public abstract void QueryEvaluate();

}