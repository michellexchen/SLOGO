package Model;

import java.util.List;

import CommandNode.Node;
import Model.Parser;
import Exception.SLogoException;
import javafx.util.Pair;

public class ParserController {
	
	private TreeFactory myTreeFactory;
	private Workspace myWorkspace;
	
	public ParserController(Workspace ws) throws SLogoException{
		myTreeFactory = new TreeFactory();
		myWorkspace = ws;
	}
	
	public double readCommand(String command) throws SLogoException {
		Parser parser = new Parser();
		List<Node> uncreatedNodes = parser.parse(command);
		double evaluation = 0;
		while(uncreatedNodes.size() > 0){
			Pair<CommandTree, List<Node>> tuple = myTreeFactory.makeTree(uncreatedNodes);
			CommandTree myTree = tuple.getKey();
			uncreatedNodes = tuple.getValue();
			evaluation = evaluateTree(myTree);
		}
		return evaluation;
	}
	
	public double evaluateTree(CommandTree myTree) throws SLogoException{
		double evaluation = 0;
		List<Character> myCharacters = myWorkspace.getCharacterList();
		for (Character character : myCharacters) {
			evaluation = myTree.traverse(character.getState());
			myWorkspace.getObservableDataList().get(myCharacters.indexOf(character))
								   .updateData(character.getState());
		}
		return evaluation;
	}
}
