package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import commandnode.CommandDriver;
import commandnode.Node;
import commandnode.NodeFactory;
import exception.SLogoException;

public class Parser {

	private NodeFactory nodeFactory;
	CommandDriver commandDriver;

	public Parser() throws SLogoException{
		nodeFactory = new NodeFactory();
		commandDriver = new NormalCommandDriver();
	}

	public List<String> format(String text) throws SLogoException {
		return Arrays.stream(text.split("\\s+")).map(String::toLowerCase)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public List<String> createNodeTextList(String text) throws SLogoException{
		if (text.equals(""))
			throw new SLogoException("No command entered");
		return format(text);
	}

	public List<Node> parse(String text) throws SLogoException{
		List<String> nodeTextList = createNodeTextList(text);
		String rootNode = nodeTextList.get(0);
		String commandName = commandDriver.getString(rootNode);
		if(isSpecial(commandName))
			return createSpecialParser(commandName).parse(text);
		else
			return setUpNodeList(nodeTextList);
	}
	
	public List<Node> setUpNodeList(List<String> nodeTextList) throws SLogoException{
		List<Node> nodeList = new ArrayList<Node>();
		for(String node : nodeTextList){
			nodeList.add(nodeFactory.createNode(node));
		}
		return nodeList;
	}

	public Parser createSpecialParser(String commandName){
		Parser myParser = null;
		try{
			myParser = (Parser) Class.forName(commandName + "Parser").newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			return null;
		}
		return myParser;
	}

	public boolean isSpecial(String commandName) throws SLogoException{
		return createSpecialParser(commandName) != null ? true : false;
	}
	
	public NodeFactory getMyNodeFactory(){
		return nodeFactory;
	}

}