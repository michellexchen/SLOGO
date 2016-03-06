package Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import Model.*;
import View.*;
import Exception.*;
import Controller.*;
import deprecated_to_be_deleted.*;
import CommandNode.*;
import CommandNode.NodeFactory;
import CommandNode.TreeFactory;
import Exception.SLogoException;
import View.View;
import javafx.util.Pair;
import CommandNode.CommandTree;
import CommandNode.EnclosureNode;
import CommandNode.Node;

public class Parser {

	private NodeFactory nf;
	private TreeFactory tf;
	private SLogoWorkspace ws;
	private SLogoWorkspace myWorkspace;
	private Controller.TreeFactory myTreeFactory = new Controller.TreeFactory();
	private List<CommandTree> executableTrees;
	private String[] bracketChars = {"{", "("};

	public Parser(SLogoWorkspace ws, String command) throws SLogoException {
		this.ws = ws; this.myWorkspace = ws;
		init();
		List<String> commands = checkThenParseCommand(command);
		createTrees(commands);
	}

	public void init() throws SLogoException{
		executableTrees = new ArrayList<CommandTree>();
		nf = new NodeFactory();
		tf = new TreeFactory();
	}
	
	public List<String> format(String text) throws SLogoException {
		return Arrays.stream(text.split("\\s+")).map(String::toLowerCase)
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	public void createTrees(List<String> commands) throws SLogoException {
		executableTrees.add(tf.createTree(commands));
	}
	
	public List<String> checkThenParseCommand(String text) throws SLogoException{
		if (text == null)
		throw new SLogoException("Did not input correct command");
		List<String> commandsWithoutVars = sanitateVars(format(text));
		return commandsWithoutVars;
	}
	
	public List<Node> parse(String text) throws SLogoException{
		List<String> nodeTextList = format(text);//checkThenParseCommand(text);
		List<Node> nodeList = new ArrayList<Node>();
		//nodeList = setUpNodeList(nodeTextList, nodeList);
		//nodeList = createNodeList(nodeTextList);
		System.out.println(nodeList);
		return nodeList;
	}
	
	public void executeCommandForChar(SLogoCharacter character) throws SLogoException{
		for(CommandTree tree: executableTrees){
			tree.traverse(character.getState());
		}
	}
	

	public double readCommand(String command) throws SLogoException {
		System.out.println("Reading command: "+command);
		List<String> commandParts = getCommandParts(command);
		List<Node> myNodes = myTreeFactory.create(commandParts);
		return evaluateNodes(myNodes);
	}

	public List<String> getCommandParts(String text) throws SLogoException {
		return Arrays.stream(text.split("\\s+")).map(String::toLowerCase)
				.collect(Collectors.toCollection(ArrayList::new));
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
		List<SLogoVariable> wsl = ws.getVarList();
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