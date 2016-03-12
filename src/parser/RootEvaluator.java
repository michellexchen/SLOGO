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

	/**
	 * update a specific turtle due to the evaluation of a specific command 
	 * 
	 * @param Node myRoot - tree to evaluate 
	 * @param SLogoCharacter - the character whose state must be updated based on evaluation 
	 * @return double evaluation value for the command with the given turtle state
	 */
	private double evaluateRoot(Node myRoot, SLogoCharacter character) throws SLogoException {
		double evaluation = 0;
		evaluation = myRoot.evaluate(character.getState());
		myWorkspace.getObservableDataList().get(myWorkspace.getCharacterList().indexOf(character))
		.updateData();
		return evaluation;
	}

	/**
	 * iterate through the list of commands and the list of turtles evaluating each command and updatign the turtle in turn
	 * 
	 * @param List<Node> myRoot - set of commands in the form of their parse trees that will be evaluated for each turtle
	 */
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
	
	/**
	 * this turtle returns a list of id's of the specified turtles
	 * 
	 * @param List<SLogoCharacter> activeTurtles - the list of active turtles
	 * @return List<Integer> list of ID's of turtles
	 */
	private List<Integer> getTurtleIDs(List<SLogoCharacter> activeTurtles){
		List<Integer> activeIDs = new ArrayList<Integer>();
		for(SLogoCharacter turtle : activeTurtles){
			activeIDs.add(turtle.getState().getID());
		}
		return activeIDs;
	}

}