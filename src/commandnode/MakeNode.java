package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;
import model.SLogoVariable;

public class MakeNode extends BinaryNode {

	private SLogoVariable makesVar;

	public MakeNode() {
		// makesVar.getName() =
	}

	public void addVarParam(String string) {
		// CommandNode.super.addVarParam(string);
		// int value = Integer.parseInt(string);
		makesVar = new SLogoVariable();
		makesVar.setName(string);
	}

	// @Override
	// public void addVarParam(String string) {
	//// CommandNode.super.addVarParam(string);
	// int value = Integer.parseInt(string);
	// makesVar.setValue(value);
	// }

	public void setVar(SLogoVariable var) {
		this.makesVar = var;
	}

	private String unencode(double val){
		while(true){
			String stringRepresentation = "" + val;
			System.out.println(stringRepresentation);
			if(stringRepresentation.contains("E")) stringRepresentation = stringRepresentation.substring(0,1) + stringRepresentation.substring(2,stringRepresentation.length());
			String result = "";
			for(int i = 0; i < stringRepresentation.length()-2;i+=2){
				String numRepOfChar = stringRepresentation.substring(i, i+2);
				if(numRepOfChar.equals("99")) break;
				char ch = Character.forDigit(Integer.parseInt(numRepOfChar), 36);
				System.out.println(ch);
				result += ch;
			}
		}
	}
	
	// @Override
	// public String grabType() {
	// // TODO Auto-generated method stub
	// return ControlNode.super.grabType();
	// }

	@Override
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		// TODO Auto-generated method stub
		double charAschiiRepresentation = getChildren().get(0).evaluate(state);
		return 0;
	}

}
