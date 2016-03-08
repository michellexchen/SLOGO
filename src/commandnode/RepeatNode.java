package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class RepeatNode extends BinaryNode {

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		int repcount = (int) evaluateChild(0, state);
		double evaluation = 0;
		for(int x=0; x<repcount; x++){
			evaluation = evaluateChild(1, state);
		}
		return evaluation;
	}

}