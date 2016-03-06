package CommandNode;

import Model.*;
import View.*;
import Exception.*;
import Controller.*;
import deprecated_to_be_deleted.*;
import CommandNode.*;

public class DoTimesNode extends BinaryNode {

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		int repcount = (int) getChildren().get(0).evaluate(state);
		double evaluation = 0;
		for(int x=1; x<=repcount; x++){
			evaluation += getChildren().get(1).evaluate(state);
		}
		return evaluation;
	}

}