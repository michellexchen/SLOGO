package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class DoTimesNode extends BinaryNode {

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		int repcount = (int) evaluateChild(0, state);
		double evaluation = 0;
		for(int x=1; x<=repcount; x++){
			evaluation += evaluateChild(1, state);
		}
		return evaluation;
	}

}