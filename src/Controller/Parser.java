package Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import CommandNode.NodeFactory;
import CommandNode.TreeFactory;
import Exception.SLogoException;
import Model.Variable;
import Model.Character;
import Model.Workspace;
import View.MainView;
import View.View;
import javafx.util.Pair;
import CommandNode.CommandTree;
import CommandNode.EnclosureNode;
import CommandNode.Node;

public class Parser {

	private NodeFactory nf;
	private TreeFactory tf;
	private Workspace ws;
	private List<CommandTree> executableTrees;
	private String[] bracketChars = {"{", "("};

	public Parser(Workspace ws, String command) throws SLogoException {
		this.ws = ws;
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
	
	public void executeCommandForChar(Character character) throws SLogoException{
		for(CommandTree tree: executableTrees){
			tree.traverse(character.getState());
		}
	}
	
	public List<Node> setUpNodeList(List<String> nodeTextList, List<Node> nodeList) throws SLogoException{
		for(int x=0; x<nodeTextList.size(); x++){
			String currNode = nodeTextList.get(x);
			if(isOpenEnclosure(currNode)){
				Pair<EnclosureNode, Integer> enclosureNodeAndEndIndex = setUpOutermostEnclosureNode(nodeTextList, x);
				EnclosureNode en = enclosureNodeAndEndIndex.getKey();
				int closedEnclosureIndex = enclosureNodeAndEndIndex.getValue();
				nodeList.add(en);
				x = closedEnclosureIndex;
			}
			else{
				nodeList.add(nf.createNode(nodeTextList.get(x)));
			}
		}
		return nodeList;
	}
	
	public Pair<EnclosureNode, Integer> setUpOutermostEnclosureNode(List<String> nodeTextList, int openEnclosureIndex){
		int openEnclosures = 1;
		int closedEnclosures = 0;
		String currNode = "";
		Pair<EnclosureNode, Integer> tuple = null;
		for(int y=openEnclosureIndex+1; y<nodeTextList.size(); y++){
			currNode = nodeTextList.get(y);
			if(isClosedEnclosure(currNode)){
				closedEnclosures++;
				if(openEnclosures == closedEnclosures){
					System.out.println("NODE TEXT LIST: " + nodeTextList);
					EnclosureNode en = nf.createEnclosureNode(openEnclosureIndex, y, nodeTextList, ws);
					tuple = new Pair<EnclosureNode, Integer>(en, y);
					break;
				}
			}
			else{
				if(isOpenEnclosure(currNode)){
					openEnclosures++;
				}
			}
		}
		return tuple;
	}

	public boolean isOpenEnclosure(String node){
		if(node.equals("(") || node.equals("["))
			return true;
		return false;
	}

	public boolean isClosedEnclosure(String node){
		if(node.equals(")") || node.equals("]"))
			return true;
		return false;
	}
	
	
	/*
	 * Translates the variables in an input into their respective values; default value being 0
	 */
	private List<String> sanitateVars(List<String> commands){
		String[] commandList = new String[commands.size()];
		commandList = commands.toArray(commandList);
		List<Variable> wsl = ws.getVarList();
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
	
	public static void main(String[] args) throws SLogoException{
		String input = "fd add 50";
		Workspace w = new Workspace(new MainView());
		Variable t = new Variable();
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
	}

}