package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class RepeatNode extends BinaryNode {

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		int repcount = (int) getChildren().get(0).evaluate(state);
		double evaluation = 0;
		for(int x=0; x<repcount; x++){
			evaluation = getChildren().get(1).evaluate(state);
		}
		return evaluation;
	}

}