package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import commandnode.MakeNode;
import commandnode.Node;
import commandnode.ToNode;
import commandnode.VariableNode;
import exception.SLogoException;
import model.SLogoCharacter;
import model.SLogoVariable;
import model.SLogoWorkspace;

public class SLogoParser {

	private SLogoTreeFactory myTreeFactory;
	private SLogoWorkspace myWorkspace;

	public SLogoParser(SLogoWorkspace workspace) throws SLogoException {
		myWorkspace = workspace;
		myTreeFactory = new SLogoTreeFactory(myWorkspace);
	}

	public List<String> formatCommandParts(String text) throws SLogoException {
		return Arrays.stream(text.split("\\s+")).map(String::toLowerCase)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public double readCommand(String command) throws SLogoException {
		System.out.println("Reading command: " + command);
		List<String> commandParts = formatCommandParts(command);
		List<Node> myTree = myTreeFactory.createNodes(commandParts);
		return evaluateNodes(myTree);
	}

	public double evaluateNodes(List<Node> myNodes) throws SLogoException {
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

	public boolean isVar(String str) {
		return str.matches(":[a-zA-Z_]+");
	}

	//
	// public static void main(String[] args) throws SLogoException{
	// String input = "fd add :s 100";
	// String makeInput = "make :k 789";
	// SLogoWorkspace w = new SLogoWorkspace(new SLogoView());
	// SLogoVariable t = new SLogoVariable();
	// t.setName(":t");
	// t.setValue(50);
	// w.addToVarList(t);
	// SLogoVariable s = new SLogoVariable();
	// s.setName(":s");
	// s.setValue(10);
	// w.addToVarList(s);
	// SLogoVariable y = new SLogoVariable();
	// y.setName(":y");
	// y.setValue(500);
	// w.addToVarList(y);
	// SLogoParser p = new SLogoParser(w);
	// p.readCommand(makeInput);
	// }

	private boolean isMake(String string) {
		return string.equals("make");
	}

}