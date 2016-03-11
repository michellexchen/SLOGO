package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import commandnode.Node;
import exception.SLogoException;
import model.SLogoCharacter;
import model.SLogoWorkspace;

/**
 * SLogoParser reads in command, formats into command parts, then creates corresponding tree(s)
 * Reads in command from MainModel's readCommand method
 * Roots of created trees evaluated and corresponding display information updated
 * @author Adam Tache
 *
 */

public class SLogoParser {

	private SLogoTreeFactory myTreeFactory;
	private SLogoWorkspace myWorkspace;

	public SLogoParser(SLogoWorkspace workspace) throws SLogoException {
		myWorkspace = workspace;
		myTreeFactory = new SLogoTreeFactory(myWorkspace);
	}
	
	public double readCommand(String command) throws SLogoException {
		System.out.println("Reading command: " + command);
		List<String> commandParts = formatCommandParts(command);
		List<Node> myTree = myTreeFactory.createNodes(commandParts);
		return evaluateNodes(myTree);
	}

	private List<String> formatCommandParts(String text) throws SLogoException {
		return Arrays.stream(text.split("\\s+")).map(String::toLowerCase)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	private double evaluateNodes(List<Node> myNodes) throws SLogoException {
		double evaluation = 0;
		List<SLogoCharacter> myCharacters = myWorkspace.getCharacterList();
		for (Node myNode : myNodes) {
			for (SLogoCharacter character : myCharacters) {
				evaluation = myNode.evaluate(character.getState());
				myWorkspace.getObservableDataList().get(myCharacters.indexOf(character))
						.updateData(character.getState());
			}
			System.out.println("Evaluation: " + evaluation);
		}
		return evaluation;
	}

}