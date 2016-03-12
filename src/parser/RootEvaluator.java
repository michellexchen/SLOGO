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
		List<SLogoCharacter> myCharacters = myWorkspace.getActiveTurtlesList(); //iterate only through the list of active turtles
		for (Node myRoot : myRoots) {
			for (SLogoCharacter character : myCharacters) {
//			for(SLogoCharacter character : myWorkspace.getCharacterList()) { //have not debugged so don't iterate through active turtles
				evaluation = myRoot.evaluate(character.getState());
				myWorkspace.getObservableDataList().get(myCharacters.indexOf(character))
						.updateData(character.getState());
			}
			System.out.println("Evaluation: " + evaluation);
		}
		return evaluation;
	}
	
}