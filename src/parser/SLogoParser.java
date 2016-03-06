package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import commandnode.Node;
import exception.SLogoException;
import model.SLogoCharacter;
import model.SLogoWorkspace;

public class SLogoParser {

	CommandLoader commandDriver;
	SLogoTreeFactory myTreeFactory;
	SLogoWorkspace myWorkspace;

	public SLogoParser(SLogoWorkspace ws) throws SLogoException{
		commandDriver = new CommandLoader();
		myTreeFactory = new SLogoTreeFactory();
		myWorkspace = ws;
	}

	public List<String> getCommandParts(String text) throws SLogoException {
		return Arrays.stream(text.split("\\s+")).map(String::toLowerCase)
				.collect(Collectors.toCollection(ArrayList::new));
	}


	public double readCommand(String command) throws SLogoException {
		System.out.println("Reading command: "+command);
		List<String> commandParts = getCommandParts(command);
		List<Node> myNodes = myTreeFactory.create(commandParts);
		return evaluateNodes(myNodes);
	}

	public double evaluateNodes(List<Node> myNodes) throws SLogoException{
		double evaluation = 0;
		List<SLogoCharacter> myCharacters = myWorkspace.getCharacterList();
		for(Node myNode : myNodes){
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