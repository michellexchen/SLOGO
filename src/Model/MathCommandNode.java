package Model;
import Model.CommandNode;

public abstract class MathCommandNode extends CommandNode {

	public void evaluate(){
		MathEvaluate();
	}
	
	public abstract double MathEvaluate();

}