package parser;

import java.util.ArrayList;
import java.util.List;

import commandnode.Node;
import exception.SLogoException;
import model.SLogoCharacter;
import model.SLogoWorkspace;

/**
 * RootEvaluator evaluates a list of nodes and applies the effects to Character
 * states by altering ObservableDataList
 *
 */
public class RootEvaluator {

	private SLogoWorkspace myWorkspace;

	public RootEvaluator(SLogoWorkspace myWorkspace) {
		this.myWorkspace = myWorkspace;
	}

	public double evaluateRoot(Node myRoot, SLogoCharacter character) throws SLogoException {
		double evaluation = 0;
		evaluation = myRoot.evaluate(character.getState());
		myWorkspace.getObservableDataList().get(myWorkspace.getCharacterList().indexOf(character))
		.updateData();
		System.out.println("Evaluation: " + evaluation);
		return evaluation;
	}

	public void evaluateRoots(List<Node> myRoots) throws SLogoException{
		List<SLogoCharacter> activeTurtles = myWorkspace.getActiveTurtlesList();
		List<Integer> activeIDs = getTurtleIDs(activeTurtles);
		for(Node myRoot: myRoots){
			for(SLogoCharacter character: activeTurtles){
				evaluateRoot(myRoot, character);
				List<SLogoCharacter> newActiveTurtles = myWorkspace.getActiveTurtlesList();
				List<Integer> newActiveIDs = getTurtleIDs(newActiveTurtles);
				if(!newActiveIDs.equals(activeIDs)){
					break;
				}
			}
		}
	}
	
	private List<Integer> getTurtleIDs(List<SLogoCharacter> activeTurtles){
		List<Integer> activeIDs = new ArrayList<Integer>();
		for(SLogoCharacter turtle : activeTurtles){
			activeIDs.add(turtle.getState().getID());
		}
		return activeIDs;
	}

}