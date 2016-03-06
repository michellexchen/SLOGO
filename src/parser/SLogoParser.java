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
	
	/*
	 * Translates the variables in an input into their respective values; default value being 0
	 */
	private List<String> sanitateVars(List<String> commands){
		String[] commandList = new String[commands.size()];
		commandList = commands.toArray(commandList);
		List<SLogoVariable> wsl = myWorkspace.getVarList();
		//can change this to regrex
		for(int i = 0; i < wsl.size(); i++){
			String name = wsl.get(i).getName();
			if(commands.indexOf(name) > -1){
				System.out.println(commands.get(commands.indexOf(wsl.get(i).getName())));
				commandList[commands.indexOf(wsl.get(i).getName())] = "" + wsl.get(i).getValue();
			} //else if(commandList[commands.indexOf(name)].contains(":"))
				//	commandList[commands.indexOf(wsl.get(i).getName())] = "0";
		}
		List<String> results = Arrays.asList(commandList);
		//grab the list and check for globally inputed vars --> translate the var to its value
//		int idx = 0;
//		List<String>results = 
//				IntStream.range(0, commands.size())
//				.filter(s->commands.get(s).contains(":"))
//				.forEach(e->{
//					ws.getVarList().stream().filter(w->{
//						w.getName().equals(e);
//					});
//				})
//				.collect(Collectors.toList());
//			List<String> myList =
//		commands.stream()
//				.filter(s->
//					s.contains(":"))
//				.forEach(e->{
////					System.out.println(e);
//					for(Variable var: ws.getVarList()){
//						System.out.println(var);
////						if(e.equals(var.getName())) System.out.println("hello");
//					}
////					/*ws.getVarList().stream().filter(y->y.getName().equals(e)).forEach(var->{
////						System.out.println(var.getName());
////						//if(var.getName().equals(e)) System.out.println(e);//e = "" + var.getValue();
////					});*/
//				});
		
		/*commands.stream().filter(s->s.contains(":"))
				.flatMap(command->
					ws.getVarList().stream()
					.filter(var->var.equals(command))
					.forEach(System.out::println));*/
		//create all of the necessary trees to do so 
		return results;
	}
	
	/*public static void main(String[] args) throws SLogoException{
		String input = "fd add 50";
		SLogoWorkspace w = new Workspace(new MainView());
		SLogoVariable t = new Variable();
		t.setName(":t");
		t.setValue(50);
		w.addToVarList(t);
		Variable s = new Variable();
		s.setName(":s");
		s.setValue(10);
		w.addToVarList(s);
		Variable y = new Variable();
		y.setName(":y");
		y.setValue(500);
		w.addToVarList(y);
		Parser p = new Parser(w, input);
	}*/

}