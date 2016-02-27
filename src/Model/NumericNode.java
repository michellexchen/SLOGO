package Model;

/**
 * SLogo's NumericNode, a class representing a numerical value (evaluate returns the value) with no children
 *
 */

public class NumericNode implements Node{

	private double myValue;
	
	public NumericNode(double value){
		myValue = value;
	}
	
	public double evaluate(CharacterState state){
		return myValue;
	}
	
	public int getNumChildren(){
		return 0;
	}

}