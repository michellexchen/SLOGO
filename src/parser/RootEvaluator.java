package parser;

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
//		System.out.println(myWorkspace);
		evaluation = myRoot.evaluate(character.getState());
		// copy tree structure so that each active character can execute a tree -- (num of characters determines if we evaluate the tree copy)			evaluation = myRoot.evaluate(character.getState());
		myWorkspace.getObservableDataList().get(myWorkspace.getCharacterList().indexOf(character))
		.updateData();
		System.out.println("Evaluation: " + evaluation);
		return evaluation;
	}

}