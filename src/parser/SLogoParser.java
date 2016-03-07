package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import commandnode.Node;
import exception.SLogoException;
import model.SLogoCharacter;
import model.SLogoVariable;
import model.SLogoWorkspace;
import view.SLogoView;

public class SLogoParser {

	CommandLoader commandDriver;
	SLogoTreeFactory myTreeFactory;
	SLogoWorkspace myWorkspace;

	public SLogoParser(SLogoWorkspace ws) throws SLogoException{
		commandDriver = new CommandLoader();
		myTreeFactory = new SLogoTreeFactory();
		myWorkspace = ws;
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
	
	/*
	 * Translates the variables in an input into their respective values; default value being 0
	 */
	private List<String> sanitateVars(List<String> commands){
		String[] commandList = new String[commands.size()];
		commandList = commands.toArray(commandList);
		List<SLogoVariable> wsl = myWorkspace.getVarList();
		//can change this to regrex
		for(int i = 0; i < commands.size(); i++){
			
		}
		for(int i = 0; i < wsl.size(); i++){
			String name = wsl.get(i).getName();
			if(commands.indexOf(name) > -1){
				System.out.println(commands.get(commands.indexOf(wsl.get(i).getName())));
				commandList[commands.indexOf(wsl.get(i).getName())] = "" + wsl.get(i).getValue();
			} //else if(commandList[commands.indexOf(name)].contains(":"))
				//	commandList[commands.indexOf(wsl.get(i).getName())] = "0";
		}
		List<String> results = Arrays.asList(commandList);
		return results;
	}
	
//	public static void main(String[] args) throws SLogoException{
//		String input = "fd add 50";
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
//		// p.readCommand(input);
//	}

}