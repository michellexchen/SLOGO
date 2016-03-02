package Model;

import Controller.SLogoException;

public class MakeNode extends CommandNode {

	public MakeNode() {

	}
	
	public void grabVar(){
		System.out.println("yo");
	}
	
	@Override
	public double evaluate(CharacterState state) throws SLogoException {
		return 0;
	}

}
