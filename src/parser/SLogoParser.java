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

	CommandLoader myCommandDriver;
	SLogoTreeFactory myTreeFactory;
	SLogoWorkspace myWorkspace;

	public SLogoParser(SLogoWorkspace workspace) throws SLogoException{
		myCommandDriver = new CommandLoader();
		myTreeFactory = new SLogoTreeFactory();
		myWorkspace = workspace;
	}

	public List<String> formatCommandParts(String text) throws SLogoException {
		return Arrays.stream(text.split("\\s+")).map(String::toLowerCase)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public double readCommand(String command) throws SLogoException {
		System.out.println("Reading command: "+command);
		List<String> commandParts = formatCommandParts(command);
		List<Node> myTree = myTreeFactory.createNodes(commandParts);
		return evaluateNodes(myTree);
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
	
	public boolean isVar(String str){
		return str.matches(":[a-zA-Z_]+");
	}
	
	public boolean isMake(String str){
		return str.equals("make");
	}
	
//	private SLogoVariable checkGlobalVars(String possibleVar){
//		for(SLogoVariable var: myWorkspace.getVarList()) {
//			if(var.getName().equals(possibleVar)) return var;
//		}
//		return null;
//	}
	
	/*
	 * Translates the variables in an input into their respective values; default value being 0
	 */
//	private List<String> sanitateVars(List<String> commands){
//		String[] commandList = new String[commands.size()];
//		commandList = commands.toArray(commandList);
//		for(int i = 0; i < commands.size(); i++){
//			if(isMake(commands.get(i))) i+= 2;
//			if(isVar(commands.get(i))){
//				SLogoVariable globalVar = checkGlobalVars(commands.get(i));
//				if(globalVar != null){
//					commandList[i] = "" + globalVar.getValue();
//				}
//			}
//		}
//		List<String> results = new ArrayList<String>(Arrays.asList(commandList));
////		System.out.println(results);
//		return results;
//	}
//	
//	public static void main(String[] args) throws SLogoException{
//		String input = "fd add :s 100";
//		String makeInput = "make :k 789";
//		SLogoWorkspace w = new SLogoWorkspace(new SLogoView());
//		SLogoVariable t = new SLogoVariable();
//		t.setName(":t");
//		t.setValue(50);
//		w.addToVarList(t);
//		SLogoVariable s = new SLogoVariable();
//		s.setName(":s");
//		s.setValue(10);
//		w.addToVarList(s);
//		SLogoVariable y = new SLogoVariable();
//		y.setName(":y");
//		y.setValue(500);
//		w.addToVarList(y);
//		SLogoParser p = new SLogoParser(w);
//		p.readCommand(makeInput);
//	}
	

}