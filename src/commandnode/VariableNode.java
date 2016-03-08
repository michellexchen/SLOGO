package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;
import model.SLogoTurtleState;
import parser.ResourceLoader;

public class VariableNode extends CommandNode {

	static final char EMPTY_CHAR = ' ';
	private char variableName = EMPTY_CHAR;
	ResourceLoader myResourcesDriver = new ResourceLoader();

	public VariableNode(String name) throws SLogoException {
		if (checkLength(name))
			variableName = name.charAt(1);
		else 
			throw new SLogoException(myResourcesDriver.getString("Variable"));
	}

	private boolean checkLength(String name) {
		return name.length() > 1;
	}

	@Override
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		int asciiRepOfVariable = Character.getNumericValue(variableName);
		System.out.println(asciiRepOfVariable + " translates to letter " + (char) asciiRepOfVariable);
		return 0;
	}
	
//	private cr
	
	public static void main(String[] args) throws SLogoException{
		double NUM_CHILDREN = 5.0;
		String varinput1 = ":t";
		String varinput2 = ":";
		String varinput3 = ":long";
		VariableNode var = new VariableNode(varinput2);
		var.evaluate(new SLogoTurtleState(NUM_CHILDREN, NUM_CHILDREN, NUM_CHILDREN, false, false, NUM_CHILDREN));
	}

}
