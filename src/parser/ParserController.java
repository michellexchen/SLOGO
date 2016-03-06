package parser;

import java.util.List;

import commandnode.CommandTree;
import commandnode.Node;
import commandnode.TreeFactory;
import exception.SLogoException;
import javafx.util.Pair;
import model.SLogoCharacter;
import model.SLogoWorkspace;

public class ParserController {
	
	private TreeFactory myTreeFactory;
	private SLogoWorkspace myWorkspace;
	
	public ParserController(SLogoWorkspace ws) throws SLogoException{
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
		List<SLogoCharacter> myCharacters = myWorkspace.getCharacterList();
		for (SLogoCharacter character : myCharacters) {
			evaluation = myTree.traverse(character.getState());
			myWorkspace.getObservableDataList().get(myCharacters.indexOf(character))
								   .updateData(character.getState());
		}
		return evaluation;
	}
}
