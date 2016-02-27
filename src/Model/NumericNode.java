package Model;

public class NumericNode extends CommandNode{

	private double myValue;
	
	public NumericNode(double value){
		myValue = value;
	}
	
	public void evaluate(CharacterState state){
		NumericEvaluate();
	}
	
	public double NumericEvaluate(){
		return myValue;
	}
	
	public int getNumChildren(){
		return 0;
	}

}