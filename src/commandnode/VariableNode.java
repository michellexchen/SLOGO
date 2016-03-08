package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;
import model.SLogoPenData;
import model.SLogoTurtleState;
import parser.ResourceLoader;

public class VariableNode extends CommandNode {

	private String variableName = "";
	private double val = -1;
	ResourceLoader myResourcesDriver = new ResourceLoader();

	public VariableNode(String name) throws SLogoException {
		if (checkLength(name)){
			this.variableName = name;
			this.val = createNumericRepOfVar(name);
		}
		else 
			throw new SLogoException(myResourcesDriver.getString("Variable"));
	}

	private boolean checkLength(String name) {
		return name.length() > 1;
	}

	@Override
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		System.out.println(val);
		return val;
	}
	
	
	private double createNumericRepOfVar(String name){
		String datagram = "";
		int encodingBitStuff = 99;
		for(int i = 1; i < name.length(); i++){
			if(i > 7) break;
			char ch = name.charAt(i);
			if(ch == '_') System.out.println("used underscore");
			int asciiRepOfVariable = Character.getNumericValue(ch);
			System.out.println(ch + " translates to letter " + asciiRepOfVariable);
//			asciiRepOfVariable -= 44;
//			if(i == name.length()/2) datagram += ".";
			datagram += asciiRepOfVariable;
			System.out.println("datagram : " + datagram);
		}
		datagram += encodingBitStuff;
		double i = Double.parseDouble(datagram);
		return i;
	}
	
	public static void main(String[] args) throws SLogoException{
		double NUM_CHILDREN = 5.0;
		String varinput1 = ":t";
		String varinput2 = ":";
		String varinput3 = ":lOdsdtfd";
		VariableNode var = new VariableNode(varinput3);
		var.evaluate(null);
//		var.evaluate(new SLogoTurtleState(new SLogoPenData(), NUM_CHILDREN, NUM_CHILDREN, NUM_CHILDREN, false, false, NUM_CHILDREN));
	}

}
