package parser;

import java.util.List;

import commandnode.Node;
import exception.SLogoException;
import model.SLogoCharacter;
import model.SLogoWorkspace;

public class RootEvaluator {
	
	private SLogoWorkspace myWorkspace;
	
	public RootEvaluator(SLogoWorkspace myWorkspace){
		this.myWorkspace = myWorkspace;
	}
	
	public double evaluateRoots(List<Node> myRoots) throws SLogoException {
		double evaluation = 0;
		System.out.println(myWorkspace);
		

		
			
			
		List<SLogoCharacter> myCharacters = myWorkspace.getCharacterList();
		for (Node myRoot : myRoots) {
			for (SLogoCharacter character : myCharacters) {
				evaluation = myRoot.evaluate(character.getState());
				myWorkspace.getObservableDataList().get(myCharacters.indexOf(character))
						.updateData(character.getState());
			}
			System.out.println("Evaluation: " + evaluation);
		}
		return evaluation;
	}
	
}