package Model;

public abstract class TurtleCommandNode extends CommandNode {

	public void evaluate(){
		TurtleEvaluate();
	}
	
	public abstract double TurtleEvaluate();

}