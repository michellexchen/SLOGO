package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class LogarithmNode extends UnaryNode{
	
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		double toEvaluate = evaluateChild(0, state);
		if(toEvaluate < 0){
			throw new SLogoException(getInstruction("Logarithm"));
		}
		return Math.log(evaluateChild(0, state));	
	}

}