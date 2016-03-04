package Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import CommandNode.NodeFactory;
import CommandNode.TreeFactory;
import Exception.SLogoException;
import CommandNode.CommandTree;
import CommandNode.Node;

public class Parser {
	
	private NodeFactory nf;
	
	public Parser() throws SLogoException{
		nf = new NodeFactory();
	}
	
	public List<String> format(String text) throws SLogoException {
		return Arrays.stream(text.split("\\s+")).map(String::toLowerCase)
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	public List<Node> parse(String text) throws SLogoException{
		if (text == null)
			throw new SLogoException("Did not input correct command");
		List<String> nodeTextList = format(text);
		List<Node> nodeList = new ArrayList<Node>();
		for(int x=0; x<nodeTextList.size(); x++){
			nodeList.add(nf.createNode(nodeTextList.get(x)));
		}
		return nodeList;
	}
}