package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;
import model.SLogoWorkspace;

public class TellNode extends BinaryNode {
	
	private SLogoWorkspace myWorkspace;

	public TellNode() {
		
	}

	@Override
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		
		return 0;
	}
	
	public SLogoWorkspace getWorkspace(){
		return myWorkspace;
	}

	public void setWorkspace(SLogoWorkspace ws){
		myWorkspace = ws;
	}

	
}
