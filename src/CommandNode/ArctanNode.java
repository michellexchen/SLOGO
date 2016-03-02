package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;

public class ArctanNode extends TrigNode {

	public double evaluate(CharacterState state) throws SLogoException {
		// For for -2pi < X < 2pi, atan(x) = x - x^3/3 + x^5/5 - x^7/7 + ...
		double radians = degToRad(getChildren().get(0).evaluate(state));
		if (radians < 1 || radians > 1) {
			throw new SLogoException("Arctan(x) domain is [-1, 1]");
		}
		double taylorSeriesSum = 0;
		for (int i = 1; i > 0; i++) {
			double taylorApproxTerm = 1;
			double prod = 1;
			for (int x = 0; x < i; x++) {
				prod *= -1;
			}
			taylorApproxTerm *= prod;
			prod = 1;
			for (int x = 0; x < 2 * i + 1; x++) {
				prod *= radians;
			}
			taylorApproxTerm *= prod;
			taylorApproxTerm /= (2 * i + 1);
			taylorSeriesSum += taylorApproxTerm;
		}
		return taylorSeriesSum;
	}

}